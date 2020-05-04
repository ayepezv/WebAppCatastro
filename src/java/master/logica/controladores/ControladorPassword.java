/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.controladores;

import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorPassword implements Serializable {

    private String passwdActual;
    private String nuevoPasswd;
    private String verifPasswd;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public ControladorPassword() {
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
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

    public void cambairContrasenia() {
        ControladorPassword cp = new ControladorPassword();
        try {
            String msg = ServiciosUsuario.cambiarContrasenia(sessionUsuario.getIdPersona(), getPasswdActual(), getNuevoPasswd(), getVerifPasswd());
            if (msg.isEmpty()) {

            } else {
                cp.enviarMensaje(sessionUsuario.getMail(), getNuevoPasswd());

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización exitosa", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                faceContext = FacesContext.getCurrentInstance();
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                faceContext.getExternalContext().redirect("/WebAppCatastro/faces/login.xhtml");
            }

        } catch (Exception e) {
            System.out.println("public void cambairContrasenia() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void enviarMensaje(String strEmail, String strPass) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        final String strUsername = "unaxfree@gmail.com";
        final String strPassWord = "mayo*1989";
        String To = strEmail;
        String Subject = "Cambio de Contraseña";
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
                    + "        <h2 style=\"text-align:center;\">Cambio de Contraseña</h2>\n"
                    + "            <p style=\"padding-left:40px; padding-right:40; text-align:justify;\">\n"
                    + "                USUARIO: " + strEmail + "\n"
                    + "                <br>\n"
                    + "                CONTRASEÑA: " + strPass + "\n"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "                NOTA: Debe ingresar al sistema utilizando su nueva contraseña.\n"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "                 Ingreso al Sistema <a href=http://186.42.120.2/WebAppCatastro/>Sigcat</a></p>\n"
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
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the passwdActual
     */
    public String getPasswdActual() {
        return passwdActual;
    }

    /**
     * @param passwdActual the passwdActual to set
     */
    public void setPasswdActual(String passwdActual) {
        this.passwdActual = passwdActual;
    }

    /**
     * @return the nuevoPasswd
     */
    public String getNuevoPasswd() {
        return nuevoPasswd;
    }

    /**
     * @param nuevoPasswd the nuevoPasswd to set
     */
    public void setNuevoPasswd(String nuevoPasswd) {
        this.nuevoPasswd = nuevoPasswd;
    }

    /**
     * @return the verifPasswd
     */
    public String getVerifPasswd() {
        return verifPasswd;
    }

    /**
     * @param verifPasswd the verifPasswd to set
     */
    public void setVerifPasswd(String verifPasswd) {
        this.verifPasswd = verifPasswd;
    }
}
