package master.presentacion.beans;

import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import master.logica.entidades.Persona;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosLogin;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.ObtenerIPs;
import recursos.Util;

@Named
@ManagedBean
@RequestScoped
public class LoginCtrl {

    private Usuario usuario;
    private Usuario usuarioSel;

    //private Usuario usuarioLogueado;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
   
    public LoginCtrl() {
        usuario = new Usuario();
        //usuarioLogueado= new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

    }

//    @PostConstruct
//    public void init() {
//        obtenerPersona();
//
//    }
    public void login() {
        //String redireccion = null;
        try {
            Usuario usuarioLogueado = ServiciosLogin.loginUsuario(usuario.getMail(), usuario.getPassword());
            
            if (usuarioLogueado != null) {
                httpServletRequest.getSession().setAttribute("usLogueado", usuarioLogueado);
                httpServletRequest.getSession().setAttribute("foto", usuarioLogueado.getFoto());
                httpServletRequest.getSession().setAttribute("idUsuarioLog", usuarioLogueado.getIdPersona());
                httpServletRequest.getSession().setAttribute("nombre", usuarioLogueado.getNombres().toUpperCase() + " " + usuarioLogueado.getApellidos().toUpperCase());
                httpServletRequest.getSession().setAttribute("ultimoAcceso", usuarioLogueado.getUltimoAcceso());
                httpServletRequest.getSession().setAttribute("ultimaIp", usuarioLogueado.getUltimaIp());
                faceContext.getExternalContext().redirect("seguridad/rol.xhtml");
                //redireccion = "seguridad/rol";    
                registarAcceso();
                
            } else {
                faceContext.getExternalContext().redirect("login.jsf");
                //redireccion = "index";
            }
        } catch (Exception e) {
            Util.addErrorMessage("Iniciar session dice: " + e.getMessage());
        }
        // return redireccion;

    }

    public void registarAcceso() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipLocal = request.getRemoteAddr();
            String hostname = request.getRemoteHost();
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            String registro = ServiciosAccesosUsuario.registrarAccesoSistema(intIdUsuario, ipLocal, ipLocal, hostname);
            System.out.println(registro);

        } catch (Exception e) {
            System.out.println("public void registarAcceso() dice: " + e.getMessage());
        }
    }

    public void cerrarSesion() {
        try {
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            String registro = ServiciosAccesosUsuario.registrarSalidaSistema(intIdUsuario);
            System.out.println(registro);
            httpServletRequest.getSession().removeAttribute("usLogueado");
            httpServletRequest.getSession().removeAttribute("nombre");
            httpServletRequest.getSession().removeAttribute("ultimaIp");
            httpServletRequest.getSession().removeAttribute("ultimoAcceso");

            System.gc();  //limpiar todo
            FacesContext fc = FacesContext.getCurrentInstance();

            Util.addSuccessMessage("Sesión cerrada");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();

            //fc.getExternalContext().redirect("/faces/index.xhtml");
            fc.getExternalContext().redirect("/WebAppCatastro/");            
            fc.getExternalContext().invalidateSession();

        } catch (Exception e) {
            System.out.println("Cerra Sesion dice: " + e.getMessage());
            Util.addErrorMessage("Cerra Sesion dice: " + e.getMessage());
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
      
    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

}
