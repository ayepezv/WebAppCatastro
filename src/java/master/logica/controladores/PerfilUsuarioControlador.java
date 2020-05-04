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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
@ManagedBean
public class PerfilUsuarioControlador {

    private Usuario usuario;
    private Usuario usuarioSel;

    //private Usuario usuarioLogueado;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String path;
    private String foto;
    private String cedula;
    //manejo de imagenes
    private String nombreImagen;
    private UploadedFile archivoImagen;
//    //cargar configuracion del  path
    java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public PerfilUsuarioControlador() {
        usuario = new Usuario();
        usuarioSel = new Usuario();
        //usuarioLogueado= new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerUsuario();

    }

    public void obtenerUsuario() {
        try {
            try {
                Usuario us = new Usuario();
                us = (Usuario) httpServletRequest.getSession().getAttribute("usLogueado");
                usuarioSel = ServiciosUsuario.buscarUsuarioDadoId(us.getIdPersona());
            } catch (Exception e) {
                System.out.println(" public void obtenerUsuario() dice: " + e.getMessage());
                Util.addErrorMessage(e.getMessage());

            }
        } catch (Exception e) {
            Util.addErrorMessage("public void crearUsuario() dice: " + e.getMessage());
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
            usuario.setFoto(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            path = usuario.getFoto();
            path = path.replace('\\', '/');
            //usuario.setFoto(path);
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
                foto = path;
                Util.addSuccessMessage("Imagen guardada con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar la imagen");
            }
        } catch (IOException ex) {
            Logger.getLogger(RolCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarImagen() {
        System.out.println(foto);

        try {

            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            usuario.setIdPersona(intIdUsuario);
            usuario.setFoto(foto);
            System.out.println(usuario.getFoto());
            String respuesta = ServiciosLogin.actualizarFoto(usuario);
            Util.addSuccessMessage(respuesta);
//            obtenerPersona();
            //resetearFitrosTabla("frmRoles:tblRoles");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizarImagen').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    public void actualizarPerfil() {
        try {
            //usuario.setFechaNacimiento(recursos.ConvertirFechas.devolverFecha(fechaNacimiento));
            String msgBd = ServiciosLogin.actualizarPerfil(usuario);
            Util.addSuccessMessage(msgBd);
            //obtenerUsuarios();
            //resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarPerfil() dice: " + e.getMessage());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    public void setConfiguracion(ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
    }

}
