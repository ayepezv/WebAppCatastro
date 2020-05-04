package master.presentacion.beans;

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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorGrupoTrabajo implements Serializable {

    private ArrayList<GrupoTrabajo> lstGruposTrabajo;
    private GrupoTrabajo objGrupoTrabajo;
    private GrupoTrabajo grupoTrabajoSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private String grupo;

    public ControladorGrupoTrabajo() {
        objGrupoTrabajo = new GrupoTrabajo();
        grupoTrabajoSel = new GrupoTrabajo();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerGrupos();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void obtenerGrupos() {
        try {
            this.setLstGruposTrabajo(ServiciosGrupoTrabajo.obtenerGruposTrabajo());
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void insertar() {
        try {
            objGrupoTrabajo.setSessionUsuario(sessionUsuario);
            String msg = ServiciosGrupoTrabajo.registrarGrupoTrabajo(getObjGrupoTrabajo());
            Util.addSuccessMessage(msg);
            obtenerGrupos();
            setObjGrupoTrabajo(new GrupoTrabajo());
            resetearFitrosTabla("frmGrupos:tblGruposTrabjo");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizar() {
        try {
            String msg = ServiciosGrupoTrabajo.editarGrupoTrabajo(getGrupoTrabajoSel());
            Util.addSuccessMessage(msg);
            obtenerGrupos();
            setGrupoTrabajoSel(new GrupoTrabajo());
            resetearFitrosTabla("frmAcciones:tblAcciones");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void eliminar() {
        try {
            String msg = ServiciosGrupoTrabajo.eliminarGrupoTrabajo(getGrupoTrabajoSel());
            Util.addSuccessMessage(msg);
            obtenerGrupos();
            setGrupoTrabajoSel(new GrupoTrabajo());
            resetearFitrosTabla("frmAcciones:tblAcciones");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
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
     * @return the objGrupoTrabajo
     */
    public GrupoTrabajo getObjGrupoTrabajo() {
        return objGrupoTrabajo;
    }

    /**
     * @param objGrupoTrabajo the objGrupoTrabajo to set
     */
    public void setObjGrupoTrabajo(GrupoTrabajo objGrupoTrabajo) {
        this.objGrupoTrabajo = objGrupoTrabajo;
    }

    /**
     * @return the grupoTrabajoSel
     */
    public GrupoTrabajo getGrupoTrabajoSel() {
        return grupoTrabajoSel;
    }

    /**
     * @param grupoTrabajoSel the grupoTrabajoSel to set
     */
    public void setGrupoTrabajoSel(GrupoTrabajo grupoTrabajoSel) {
        this.grupoTrabajoSel = grupoTrabajoSel;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
