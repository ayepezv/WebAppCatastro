
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefVidaUtil;
import catastro.coeficientes.funciones.FCoefVidaUtil;
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
public class CtrCoefVidaUtil {
    
    private List<CoefVidaUtil> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefVidaUtil objCoefVidaUtil;
    private CoefVidaUtil coefvidautilSel;
    private String msgBD;
    
    public CtrCoefVidaUtil() {
        obtenerCoeficientes();
        objCoefVidaUtil = new CoefVidaUtil();
        coefvidautilSel = new CoefVidaUtil();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }
    
    public void obtenerCoeficientes() {
        try {
            setLstCoeficientes(FCoefVidaUtil.obtenerCoeficientes());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void insertar() {
        try {
            setMsgBD(FCoefVidaUtil.registrarCoeficiente(getObjCoefVidaUtil()));
            setObjCoefVidaUtil(new CoefVidaUtil());
            obtenerCoeficientes();
            Util.addSuccessMessage(getMsgBD());
            resetearFitrosTabla("frmListaCoefVidaUtil:tblListaCoefVidaUtil");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefVidaUtil').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void actualizar() {
        try {
            setMsgBD(FCoefVidaUtil.actualizarCoeficiente(coefvidautilSel));
            setObjCoefVidaUtil(new CoefVidaUtil());
            obtenerCoeficientes();
            Util.addSuccessMessage(getMsgBD());
            resetearFitrosTabla("frmListaCoefVidaUtil:tblListaCoefVidaUtil");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefVidaUtil').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void eliminar() {
        try {
            setMsgBD(FCoefVidaUtil.eliminarCoeficiente(getCoefvidautilSel()));
            setCoefvidautilSel(new CoefVidaUtil());
            obtenerCoeficientes();
            Util.addSuccessMessage(getMsgBD());
            resetearFitrosTabla("frmListaCoefVidaUtil:tblListaCoefVidaUtil");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefVidaUtil').hide()");
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
     * @return the lstCoeficientes
     */
    public List<CoefVidaUtil> getLstCoeficientes() {
        return lstCoeficientes;
    }

    /**
     * @param lstCoeficientes the lstCoeficientes to set
     */
    public void setLstCoeficientes(List<CoefVidaUtil> lstCoeficientes) {
        this.lstCoeficientes = lstCoeficientes;
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
     * @return the objCoefVidaUtil
     */
    public CoefVidaUtil getObjCoefVidaUtil() {
        return objCoefVidaUtil;
    }

    /**
     * @param objCoefVidaUtil the objCoefVidaUtil to set
     */
    public void setObjCoefVidaUtil(CoefVidaUtil objCoefVidaUtil) {
        this.objCoefVidaUtil = objCoefVidaUtil;
    }

    /**
     * @return the coefvidautilSel
     */
    public CoefVidaUtil getCoefvidautilSel() {
        return coefvidautilSel;
    }

    /**
     * @param coefvidautilSel the coefvidautilSel to set
     */
    public void setCoefvidautilSel(CoefVidaUtil coefvidautilSel) {
        this.coefvidautilSel = coefvidautilSel;
    }

    /**
     * @return the msgBD
     */
    public String getMsgBD() {
        return msgBD;
    }

    /**
     * @param msgBD the msgBD to set
     */
    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
}
