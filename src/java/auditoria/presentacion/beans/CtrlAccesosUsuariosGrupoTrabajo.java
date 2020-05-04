package auditoria.presentacion.beans;

import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.GrupoTrabajo;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosGrupoTrabajo;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlAccesosUsuariosGrupoTrabajo implements Serializable {

    private ArrayList<GrupoTrabajo> lstGruposTrabajo;
    private int idGrupoTrabajo;
    private ArrayList<Usuario> lstUsuarios;
    private String json;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtrlAccesosUsuariosGrupoTrabajo() {
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerGruposTrabajo();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getNick());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerGruposTrabajo() {
        try {
            setLstGruposTrabajo(ServiciosGrupoTrabajo.obtenerGruposTrabajo());
        } catch (Exception e) {
            System.out.println("public void obtenerGruposTrabajo() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void obtenerGruposTrabajo() dice:", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerUsuarios() {
        try {
            setLstUsuarios(ServiciosUsuario.obtenerUsuariosDadoGrupoTrabajo(getIdGrupoTrabajo()));
            /*
             generar json
             */
            setJson("[");
            ArrayList<Usuario> usuarios = ServiciosUsuario.obtenerUsuariosDadoGrupoTrabajo(idGrupoTrabajo);
            for (int i = 0; i < usuarios.size(); i++) {
                setJson(getJson() + "{'Usuario':'" + usuarios.get(i).getNick() + "',");
                setJson(getJson() + "'Accesos':");
                setJson(getJson() + "'" + ServiciosAccesosUsuario.obtenerAccesosDadoUsuario(usuarios.get(i).getIdPersona()).size() + "'");
                if (i != usuarios.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json: "+json);
            Util.addSuccessMessage(json);
        } catch (Exception e) {
            System.out.println("public void obtenerGruposTrabajo() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void obtenerUsuarios() dice:", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
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
     * @return the lstGruposTrabajo
     */
    public ArrayList<GrupoTrabajo> getLstGruposTrabajo() {
        return lstGruposTrabajo;
    }

    /**
     * @param lstGruposTrabajo the lstGruposTrabajo to set
     */
    public void setLstGruposTrabajo(ArrayList<GrupoTrabajo> lstGruposTrabajo) {
        this.lstGruposTrabajo = lstGruposTrabajo;
    }

    /**
     * @return the idGrupoTrabajo
     */
    public int getIdGrupoTrabajo() {
        return idGrupoTrabajo;
    }

    /**
     * @param idGrupoTrabajo the idGrupoTrabajo to set
     */
    public void setIdGrupoTrabajo(int idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
    }

    /**
     * @return the lstUsuarios
     */
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    /**
     * @param lstUsuarios the lstUsuarios to set
     */
    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    /**
     * @return the json
     */
    public String getJson() {
        return json;
    }

    /**
     * @param json the json to set
     */
    public void setJson(String json) {
        this.json = json;
    }
}
