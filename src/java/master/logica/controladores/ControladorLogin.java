package master.logica.controladores;

import auditoria.logica.entidades.AccesosRol;
import auditoria.logica.servicios.FAccesosRol;
import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosLogin;
import master.logica.servicios.ServiciosRolUsuario;
import recursos.Util;

@RequestScoped
@ManagedBean
public class ControladorLogin {

    private Usuario usuario;
    private RolUsuario rolUsuario;
    private ArrayList<RolUsuario> lstRoles;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;
    private Date fechaActual;

    public ControladorLogin() {
        fechaActual = new Date();
        usuario = new Usuario();
        rolUsuario = new RolUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    public void iniciarSession() throws IOException {
        try {
            Usuario usuarioLogueado = ServiciosLogin.loginUsuario(usuario.getMail(), usuario.getPassword());

            faceContext.getExternalContext().getSessionMap().put("sessionUsuario", usuarioLogueado);
            httpServletRequest.getSession().setAttribute("fechaActual", fechaActual);
            httpServletRequest.getSession().setAttribute("idUsuarioLog", usuarioLogueado.getIdPersona());
            httpServletRequest.getSession().setAttribute("fotoUsuario", usuarioLogueado.getFoto());
            httpServletRequest.getSession().setAttribute("nombre", usuarioLogueado.getNombres().toUpperCase() + " " + usuarioLogueado.getApellidos().toUpperCase());
            httpServletRequest.getSession().setAttribute("idUsuario", usuarioLogueado.getIdPersona());
            httpServletRequest.getSession().setAttribute("usuarioLogueado", usuarioLogueado);
            httpServletRequest.getSession().setAttribute("mailUsuarioLogueado", usuarioLogueado.getMail());

            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");

            lstRoles = ServiciosRolUsuario.listarRolesDadoUsuario(intIdUsuario);
            Util.addSuccessMessage("total de roles: " + lstRoles.size());
            System.out.println("total de roles: " + lstRoles.size());
            if (lstRoles.size() > 1) {
                faceContext.getExternalContext().redirect("seguridad/selectRol.xhtml");
                registarAcceso();
            } else if (lstRoles.isEmpty()) {
                Util.addErrorMessage("No tiene roles en el sistema");
                this.cerrarSesion();
            } else {
                int intIdRol = lstRoles.get(0).getIdRol().getIdRol();
                rolUsuario = lstRoles.get(0);
                httpServletRequest.getSession().setAttribute("privSelect", rolUsuario.getSeleccionar());
                httpServletRequest.getSession().setAttribute("privInsert", rolUsuario.getInsertar());
                httpServletRequest.getSession().setAttribute("privUpdate", rolUsuario.getActualizar());
                httpServletRequest.getSession().setAttribute("privDelete", rolUsuario.getEliminar());

                httpServletRequest.getSession().setAttribute("idRol", intIdRol);
                switch (intIdRol) {
                    case 1:
                        System.out.println("Módulo Super Administrador");
                        faceContext.getExternalContext().redirect("seguridad/master/home.xhtml");
                        break;
                    case 2:
                        System.out.println("Módulo Auditoria");
                        faceContext.getExternalContext().redirect("seguridad/auditoria/home.xhtml");
                        break;
                    case 4:
                        System.out.println("Módulo Catastro");
                        faceContext.getExternalContext().redirect("seguridad/catastro/home.xhtml");
                        break;
                    case 5:
                        System.out.println("Módulo Contribuyentes");
                        faceContext.getExternalContext().redirect("seguridad/propietarios/home.xhtml");
                        break;

                    case 6:
                        System.out.println("Módulo Agua Potable");
                        faceContext.getExternalContext().redirect("seguridad/agua_potable/home.xhtml");
                        break;

                    case 7:
                        System.out.println("Módulo Recaudacion");
                        faceContext.getExternalContext().redirect("seguridad/recaudacion/home.xhtml");
                        break;

                    case 8:
                        System.out.println("Módulo Municipio");
                        faceContext.getExternalContext().redirect("seguridad/municipio/home.xhtml");
                        break;

                    case 13:
                        System.out.println("Módulo CEM");
                        faceContext.getExternalContext().redirect("seguridad/cem/home.xhtml");
                        break;

                    case 14:
                        System.out.println("Módulo Registro de la Propiedad");
                        faceContext.getExternalContext().redirect("seguridad/registro_propiedad/home.xhtml");
                        break;

                    case 15:
                        System.out.println("Módulo Tesorería");
                        faceContext.getExternalContext().redirect("seguridad/tesorero/home.xhtml");
                        break;

                    case 21:
                        System.out.println("Módulo Rentas");
                        faceContext.getExternalContext().redirect("seguridad/rentas/home.xhtml");
                        break;

                    default:
                        System.out.println("Módulo en Desarrollo");
                        faceContext.getExternalContext().redirect("seguridad/test.xhtml");
//                        this.cerrarSesion();
                        break;
                }
            }

        } catch (Exception ex) {
            Util.addErrorMessage(ex.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void iniciarSesionRol() {
        try {
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
            int intIdRol = rolUsuario.getIdRol().getIdRol();
            httpServletRequest.getSession().setAttribute("idRol", intIdRol);
            // privilegios del usuario
            RolUsuario ru = ServiciosRolUsuario.obtenerRolUsuario(intIdRol, intIdUsuario);
            httpServletRequest.getSession().setAttribute("privSelect", ru.getSeleccionar());
            httpServletRequest.getSession().setAttribute("privInsert", ru.getInsertar());
            httpServletRequest.getSession().setAttribute("privUpdate", ru.getActualizar());
            httpServletRequest.getSession().setAttribute("privDelete", ru.getEliminar());
            /// fin de los privilegios            
            // faceContext.getExternalContext().redirect("test.xhtml");

            switch (intIdRol) {
                case 1:
                    System.out.println("Módulo Super Administrador");
                    faceContext.getExternalContext().redirect("master/home.xhtml");
                    break;
                case 2:
                    System.out.println("Módulo Auditoria");
                    faceContext.getExternalContext().redirect("auditoria/home.xhtml");
                    break;
                case 4:
                    System.out.println("Módulo Catastro");
                    faceContext.getExternalContext().redirect("catastro/home.xhtml");
                    break;
                case 5:
                    System.out.println("Módulo Contribuyentes");
                    faceContext.getExternalContext().redirect("propietarios/home.xhtml");
                    break;

                case 6:
                    System.out.println("Módulo Agua Potable");
                    faceContext.getExternalContext().redirect("agua_potable/home.xhtml");
                    break;

                case 7:
                    System.out.println("Módulo Recaudacion");
                    faceContext.getExternalContext().redirect("recaudacion/home.xhtml");
                    break;

                case 8:
                    System.out.println("Módulo Municipio");
                    faceContext.getExternalContext().redirect("municipio/home.xhtml");
                    break;

                case 13:
                    System.out.println("Módulo CEM");
                    faceContext.getExternalContext().redirect("cem/home.xhtml");
                    break;

                case 14:
                    System.out.println("Módulo Registro de la Propiedad");
                    faceContext.getExternalContext().redirect("registro_propiedad/home.xhtml");
                    break;

                case 15:
                    System.out.println("Módulo Tesorería");
                    faceContext.getExternalContext().redirect("tesorero/home.xhtml");
                    break;

                case 19:
                    System.out.println("Módulo Regulación Urbana");
                    faceContext.getExternalContext().redirect("regulacion_urbana/home.xhtml");
                    break;

                case 21:
                    System.out.println("Módulo Rentas");
                    faceContext.getExternalContext().redirect("rentas/home.xhtml");
                    break;

                default:
                    System.out.println("Módulo en Desarrollo");
                    faceContext.getExternalContext().redirect("test.xhtml");
                    break;
            }

            /*
             * registro acceso rol - usuario           
             */
            HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
            request.getHeaderNames();
            AccesosRol acces = new AccesosRol();
            acces.setRolUsuario(rolUsuario);
            acces.setIpAcceso(request.getRemoteAddr());
            acces.setHost(request.getRemoteHost());
            String msnAcceso = FAccesosRol.registrarAccesoRol(acces);
            Util.addSuccessMessage(msnAcceso);
            System.out.println("public void iniciarSesionRol() dice: " + msnAcceso);

        } catch (Exception ex) {
            Util.addErrorMessage(ex.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void cerrarSesion() throws Exception {
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

            fc.getExternalContext().redirect("/WebAppCatastro");
            fc.getExternalContext().invalidateSession();
        } catch (Exception ex) {
            Util.addErrorMessage(ex.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void cambiarRol() throws Exception {
        try {
            faceContext.getExternalContext().redirect("/WebAppCatastro/faces/seguridad/selectRol.xhtml");
        } catch (Exception ex) {
            Util.addErrorMessage(ex.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void registarAcceso() {
        ControladorLogin cl = new ControladorLogin();
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipLocal = request.getRemoteAddr();
            String hostname = request.getRemoteHost();
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            String registro = ServiciosAccesosUsuario.registrarAccesoSistema(intIdUsuario, ipLocal, ipLocal, hostname);
            System.out.println(registro);

            String mailUsuario = (String) httpServletRequest.getSession().getAttribute("mailUsuarioLogueado");
            //envio de mail por ingreso al sistema
            cl.enviarMensaje(mailUsuario, ipLocal);

        } catch (Exception e) {
            System.out.println("public void registarAcceso() dice: " + e.getMessage());
        }
    }

    public void enviarMensaje(String strEmail, String ipLocal) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.host", "192.168.1.92");
        props.put("mail.smtp.port", "587");
        //props.put("mail.smtp.port", "7071");
        final String strUsername = "unaxfree@gmail.com";
        final String strPassWord = "mayo*1989";
        String To = strEmail;
        String Subject = "Acceso al sistema";
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(strUsername, strPassWord);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(strUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setContent(" <html style=\"font-family:calibri\">\n"
                    + "     <div style=\"-webkit-box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3);\n"
                    + "-moz-box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3);\n"
                    + "box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3); width:600px; \">\n"
                    + "        <div style=\"background-color:#2B2E2E; width:auto; marging-left:10px\"><img style=\"padding-left:250px; padding-top:20px; padding-bottom:20px;width:100px;\" src=\"http://186.42.120.2/WebAppCatastro/resources/imagenes/logo_archidona_1.png\">\n"
                    + "    </div>\n"
                    + "        <div style=\"background-color:#F5F4F3;\">\n"
                    + "<br>"
                    + "        <h2 style=\"text-align:center;\">Acceso al sistema</h2>\n"
                    + "            <p style=\"padding-left:40px; padding-right:40; text-align:justify;\">\n"
                    + "             <b>Se ha registrado el acceso exitoso al Sigcat</b><br>"
                    + "                <b>USUARIO:</b> " + strEmail + "\n"
                    + "                <br>\n"
                    + "                <b>Ip Local:</b> " + ipLocal + "\n"
                    + "                <br>\n"
                    + "                <b>Fecha de acceso:</b> " + new Date() + "\n"
                    + "                <br>\n"
                    + "                <b>NOTA:</b> En caso de no reconocer este acceso al sistema contacto con el administrador del sistema.\n"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "                 Ingreso al Sistema <a href=http://catastro.archidona.gob.ec/WebAppCatastro/>Sigcat</a></p>\n"
                    + "            <br>\n"
                    + "                \n"
                    + "            </p>\n"
                    + "        </div>\n"
                    + "        <div style=\"background-color:#2B2E2E; width:600px; color:#fff; padding-left:150px\">Sigcat - Derechos Reservados &#169;\n"
                    + "    </div>\n"
                    + "         <br>\n"
                    + "    </div>  \n"
                    + "    \n"
                    + "</html>", "text / html");
            Transport.send(message);
        } catch (MessagingException e) {
            Util.addErrorMessage("Problemas de conexión a Internet, no se ha enviado su correo electrónico, Intentelo más tarde.");
            throw new RuntimeException(e);
        }
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

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }

    public ArrayList<RolUsuario> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(ArrayList<RolUsuario> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

}
