package catastro.cem.controladores;

import catastro.cem.entidades.TipoObra;
import catastro.cem.funciones.FTipoObra;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorConfigMejoras implements Serializable {

    private List<TipoObra> lsTipoObras;
    private List<TipoObra> lsTipoObrasInact;
    private TipoObra objTipoObra;
    private TipoObra tipoObraSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerTiposObras();
    }

    public ControladorConfigMejoras() {
        objTipoObra = new TipoObra();
        tipoObraSel = new TipoObra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void registrar() {
        try {
            this.objTipoObra.setSessionUsuario(sessionUsuario);
            String msg = FTipoObra.registrarTipoObra(objTipoObra);
            this.objTipoObra = new TipoObra();
            obtenerTiposObras();
            Util.addSuccessMessage(msg);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide();");
            resetearFitrosTabla("frmTipoObras:tabView:tabActivos");            
        } catch (Exception e) {
            System.out.println("public void registrar() dice: " + e.getMessage());
        }
    }

    public void obtenerTiposObras() {
        try {
            this.setLsTipoObras(FTipoObra.obtenerTiposObras());
        } catch (Exception e) {
            System.out.println("public void obtenerTiposObras() dice: " + e.getMessage());
        }
    }

    public void obtenerTiposObrasInact() {
        try {
            this.setLsTipoObrasInact(FTipoObra.obtenerTiposObrasInactivas());
        } catch (Exception e) {
            System.out.println("public void obtenerTiposObrasInact() dice: " + e.getMessage());
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
     * @return the lsTipoObras
     */
    public List<TipoObra> getLsTipoObras() {
        return lsTipoObras;
    }

    /**
     * @param lsTipoObras the lsTipoObras to set
     */
    public void setLsTipoObras(List<TipoObra> lsTipoObras) {
        this.lsTipoObras = lsTipoObras;
    }

    /**
     * @return the lsTipoObrasInact
     */
    public List<TipoObra> getLsTipoObrasInact() {
        return lsTipoObrasInact;
    }

    /**
     * @param lsTipoObrasInact the lsTipoObrasInact to set
     */
    public void setLsTipoObrasInact(List<TipoObra> lsTipoObrasInact) {
        this.lsTipoObrasInact = lsTipoObrasInact;
    }

    /**
     * @return the objTipoObra
     */
    public TipoObra getObjTipoObra() {
        return objTipoObra;
    }

    /**
     * @param objTipoObra the objTipoObra to set
     */
    public void setObjTipoObra(TipoObra objTipoObra) {
        this.objTipoObra = objTipoObra;
    }

    /**
     * @return the tipoObraSel
     */
    public TipoObra getTipoObraSel() {
        return tipoObraSel;
    }

    /**
     * @param tipoObraSel the tipoObraSel to set
     */
    public void setTipoObraSel(TipoObra tipoObraSel) {
        this.tipoObraSel = tipoObraSel;
    }
}
