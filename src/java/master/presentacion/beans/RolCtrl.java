package master.presentacion.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Modulo;
import master.logica.entidades.Rol;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosModulo;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosUsuario;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class RolCtrl implements Serializable {

    private ArrayList<Rol> lstRoles;
    private ArrayList<Modulo> lstModulos;
    private Rol rol;
    private Rol rolSel;
    private int idModuloSel;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String path;
    //manejo de imagenes
    private String nombreImagen;
    private UploadedFile archivoImagen;
    //cargar configuracion del  path
    java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public RolCtrl() {
        rol = new Rol();
        rolSel = new Rol();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerRoles();
        obtenerModulos();

    }
    
    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerRoles() {
        try {
            lstRoles = ServiciosRoles.obtenerRolesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRoles() dice: " + e.getMessage());
        }
    }

    public void obtenerModulos() {
        try {
            lstModulos = ServiciosModulo.obtenerModulosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerModulos() dice:" + e.getMessage());
        }
    }

    public void registrar() {
        try {
            rol.setSessionUsuario(sessionUsuario);
            rol.getIdModulo().setIdModulo(idModuloSel);
            String respuesta = ServiciosRoles.registrarRol(rol);
            Util.addSuccessMessage(respuesta);
            obtenerRoles();
            resetearFitrosTabla("frmRoles:tblRoles");
            rol = new Rol();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void actualizar() {
        try {
            //rol.getIdModulo().setIdModulo(idModuloSel);
            rolSel.setSessionUsuario(sessionUsuario);
            String respuesta = ServiciosRoles.actualizarRol(rolSel);
            Util.addSuccessMessage(respuesta);
            obtenerRoles();
            resetearFitrosTabla("frmRoles:tblRoles");
            rolSel = new Rol();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            //rol.getIdModulo().setIdModulo(idModuloSel);
            rolSel.setSessionUsuario(sessionUsuario);
            String respuesta = ServiciosRoles.elimianrRol(rolSel);
            Util.addSuccessMessage(respuesta);
            obtenerRoles();
            resetearFitrosTabla("frmRoles:tblRoles");
            rolSel = new Rol();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminar() dice: " + e.getMessage());
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
        String rutaImagenes = Configuracion.getString("rutaImagenes");
        int longitudRelativa = Integer.valueOf(Configuracion.getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //agrego el path del icono del rol
            rolSel.setIcono(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            path = rolSel.getIcono();
            path = path.replace('\\', '/');
            rolSel.setIcono(path);
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
            String respuesta = ServiciosRoles.actualizarIcono(rolSel);
            Util.addSuccessMessage(respuesta);
            obtenerRoles();
            resetearFitrosTabla("frmRoles:tblRoles");
            rolSel = new Rol();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizarImagen').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">   
    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
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

    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public ArrayList<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(ArrayList<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRolSel() {
        return rolSel;
    }

    public void setRolSel(Rol rolSel) {
        this.rolSel = rolSel;
    }

    public int getIdModuloSel() {
        return idModuloSel;
    }

    public void setIdModuloSel(int idModuloSel) {
        this.idModuloSel = idModuloSel;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
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
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>
}
