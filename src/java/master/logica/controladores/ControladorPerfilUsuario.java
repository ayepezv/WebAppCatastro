/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.controladores;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosLogin;
import master.logica.servicios.ServiciosUsuario;
import master.presentacion.beans.RolCtrl;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

/**
 *
 * @author ayepe
 */
@ManagedBean
@ViewScoped
public class ControladorPerfilUsuario {

    private int idUsuario;
    private Usuario us;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;
    private String path;
    private String foto;
    private Date fechaN;
    //manejo de imagenes
    private String nombreImagen;
    private UploadedFile archivoImagen;
//    //cargar configuracion del  path
    java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public ControladorPerfilUsuario() {
        us = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerUsuario();
    }

    public void obtenerUsuario() {
        try {
            setIdUsuario((int) getHttpServletRequest().getSession().getAttribute("idUsuario"));
            setUs(ServiciosUsuario.buscarUsuarioDadoId(getIdUsuario()));
            fechaN = us.getFechaNacimiento();
        } catch (Exception e) {
            System.out.println(" public void obtenerUsuario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());

        }
    }

    // manejo de imagenes
    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public boolean guardarArchivo(String nombre, byte[] contenido) {
        //int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
        String rutaImagenes = Configuracion.getString("rutaFotos");
        int longitudRelativa = Integer.valueOf(Configuracion.getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //agrego el path del icono del rol
            us.setFoto(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            path = us.getFoto();
            path = path.replace('\\', '/');
            us.setFoto(path);
            System.out.println(path);
            System.out.println("cargar objeto fos ");
            FileOutputStream fos = new FileOutputStream(f);
            System.out.println("escribir fos ");
            fos.write(contenido);
            return true;
        } catch (Exception e) {
            Util.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    public void cargarArchivoImagen(FileUploadEvent e) {
        System.out.println("Entra al método cargar imagen");
        UploadedFile file = e.getFile();
        this.archivoImagen = file;
        System.out.println(file.getContentType());
        //objImagen.setTipo(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getFileName());
        nombreImagen = file.getFileName();
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido)) {
//                foto = path;
                Util.addSuccessMessage("Imagen guardada con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar la imagen");
            }
        } catch (IOException ex) {
            Logger.getLogger(RolCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarImagen() {
        try {
            String respuesta = ServiciosLogin.actualizarFoto(us);
            Util.addSuccessMessage(respuesta);
             DefaultRequestContext.getCurrentInstance().update("frmPerfilUsuario:fotoUsuario");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizarImagen').hide()");           
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar Imagen() dice: " + e.getMessage());
        }
    }

    public void actualizarPerfil() {
        try {
            us.setFechaNacimiento(recursos.ConvertirFechas.devolverFecha(fechaN));
            String msgBd = ServiciosLogin.actualizarPerfil(us);
            Util.addSuccessMessage(msgBd);
            //obtenerUsuario();
            //resetearFitrosTabla("frmUsuarios:tblUsuarios");
            //us = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarPerfil() dice: " + e.getMessage());
        }
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    public void setConfiguracion(ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the us
     */
    public Usuario getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(Usuario us) {
        this.us = us;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    /**
     * @return the facesMessage
     */
    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    /**
     * @param facesMessage the facesMessage to set
     */
    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }
}
