package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.GrupoTrabajo;
import master.logica.entidades.GrupoTrabajoRol;
import master.logica.entidades.Rol;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosGrupoTrabajo;
import master.logica.servicios.ServiciosGrupoTrabajoRol;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorGrupoTrabajoRol implements Serializable {

    private List<GrupoTrabajoRol> lstGrupos;
    private GrupoTrabajoRol grupo;
    private GrupoTrabajoRol grupoSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private ArrayList<Rol> lstRoles;
    private ArrayList<GrupoTrabajo> lstGruposTrabajo;

    public ControladorGrupoTrabajoRol() {
        grupo = new GrupoTrabajoRol();
        grupoSel = new GrupoTrabajoRol();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerGrupos();
        obtenerGruposTrabajo();
        obtenerRoles();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerRoles() {
        try {
            setLstRoles(ServiciosRoles.obtenerRolesDadoEstado("A"));
        } catch (Exception e) {
            System.out.println("public void obtenerRoles() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerGruposTrabajo() {
        try {
            setLstGruposTrabajo(ServiciosGrupoTrabajo.obtenerGruposTrabajo());
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void obtenerGrupos() {
        try {
            setLstGrupos(ServiciosGrupoTrabajoRol.obtenerGruposRoles());
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void registrar() {
        try {
            grupo.setSessionUsuario(sessionUsuario);
            String msg = ServiciosGrupoTrabajoRol.asignarRolGrupo(grupo);
            Util.addSuccessMessage(msg);
            obtenerGrupos();
            grupo = new GrupoTrabajoRol();
            resetearFitrosTabla("frmPrincipal:tblGruposTrabjo");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            System.out.println("public void registrar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstGrupos
     */
    public List<GrupoTrabajoRol> getLstGrupos() {
        return lstGrupos;
    }

    /**
     * @param lstGrupos the lstGrupos to set
     */
    public void setLstGrupos(List<GrupoTrabajoRol> lstGrupos) {
        this.lstGrupos = lstGrupos;
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
     * @return the grupo
     */
    public GrupoTrabajoRol getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoTrabajoRol grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the grupoSel
     */
    public GrupoTrabajoRol getGrupoSel() {
        return grupoSel;
    }

    /**
     * @param grupoSel the grupoSel to set
     */
    public void setGrupoSel(GrupoTrabajoRol grupoSel) {
        this.grupoSel = grupoSel;
    }

    /**
     * @return the lstRoles
     */
    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
    }

    /**
     * @param lstRoles the lstRoles to set
     */
    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
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

}
