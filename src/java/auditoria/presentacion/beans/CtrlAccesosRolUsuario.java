package auditoria.presentacion.beans;

import auditoria.logica.servicios.FAccesosRol;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.GrupoTrabajo;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosGrupoTrabajo;
import master.logica.servicios.ServiciosRolUsuario;
import master.logica.servicios.ServiciosUsuario;

@ManagedBean
@ViewScoped
public class CtrlAccesosRolUsuario implements Serializable {

    private ArrayList<GrupoTrabajo> lstGruposTrabajo;
    private int idGrupoTrabajo;
    private int idUsuario;
    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<RolUsuario> roles;
    private String json;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtrlAccesosRolUsuario() {
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
        } catch (Exception e) {
            System.out.println("public void obtenerUsuarios() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void obtenerUsuarios() dice:", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void generarJson() {
        try {
            setRoles(ServiciosRolUsuario.listarUsuariosDadoGrupoTrabajoUsuario(getIdGrupoTrabajo(), getIdUsuario()));
            setJson("[");
            int idRol, idUsu;
            for (int i = 0; i < getRoles().size(); i++) {
                idRol = getRoles().get(i).getIdRol().getIdRol();
                idUsu = getRoles().get(i).getIdUsuario().getIdPersona();
                setJson(getJson() + "{'Rol':'" + getRoles().get(i).getIdRol().getNombre() + "',");
                setJson(getJson() + "'Accesos':'" + FAccesosRol.listarAccesosDadoRolUsuario(idUsu, idRol).size() + "'");
                if (i != getRoles().size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("json: " + getJson());
        } catch (Exception e) {
            System.out.println("public void generarJson() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void generarJson() dice:", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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
     * @return the roles
     */
    public ArrayList<RolUsuario> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(ArrayList<RolUsuario> roles) {
        this.roles = roles;
    }
}
