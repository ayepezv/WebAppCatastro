package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefBanios;
import catastro.coeficientes.funciones.FCoefAcabBanios;
import static catastro.coeficientes.funciones.FCoefAcabBanios.obtenerCoefAcabadosBanios;
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
public class CtrlCoefAcabBanios implements Serializable {

    private List<CoefBanios> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefBanios objCoefBanios;
    private CoefBanios coefbaniosSel;
    private String msgBD;

    public CtrlCoefAcabBanios() {
        obtenerCoeficientes();
        objCoefBanios = new CoefBanios();
        coefbaniosSel = new CoefBanios();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabBanios.obtenerCoefAcabadosBanios();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabBanios.registrarCoeficiente(objCoefBanios);
            objCoefBanios = new CoefBanios();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefBanios:tblListaCoefBanios");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefBanios').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void actualizar() {
        try {
            msgBD = FCoefAcabBanios.actualizarCoeficiente(coefbaniosSel);
            objCoefBanios = new CoefBanios();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefBanios:tblListaCoefBanios");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefBanios').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

     public void eliminar() {
        try {
            msgBD = FCoefAcabBanios.eliminarCoeficiente(coefbaniosSel);
            coefbaniosSel = new CoefBanios();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefBanios:tblListaCoefBanios");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefBanios').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
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

    public List<CoefBanios> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefBanios> lstCoeficientes) {
        this.lstCoeficientes = lstCoeficientes;
    }

    public CoefBanios getObjCoefBanios() {
        return objCoefBanios;
    }

    public void setObjCoefBanios(CoefBanios objCoefBanios) {
        this.objCoefBanios = objCoefBanios;
    }

    public CoefBanios getCoefbaniosSel() {
        return coefbaniosSel;
    }

    public void setCoefbaniosSel(CoefBanios coefbaniosSel) {
        this.coefbaniosSel = coefbaniosSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
