
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefRevExterior;
import catastro.coeficientes.funciones.FCoefRevExt;
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
public class CtrlCoefRevExt implements Serializable {
    private List<CoefRevExterior> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefRevExterior objCoefRevexterior;
    private CoefRevExterior coefrevexteriorSel;
    private String msgBD;

    public CtrlCoefRevExt() {
        obtenerCoeficientes();
        objCoefRevexterior = new CoefRevExterior();
        coefrevexteriorSel = new CoefRevExterior();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefRevExt.obtenerCoefAcabadosRevExterior();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefRevExt.registrarCoeficiente(objCoefRevexterior);
            objCoefRevexterior = new CoefRevExterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevext:tblListaCoefRevext");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefRevext').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefRevExt.actualizarCoeficiente(coefrevexteriorSel);
            coefrevexteriorSel = new CoefRevExterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevext:tblListaCoefRevext");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefRevext').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefRevExt.eliminarCoeficiente(coefrevexteriorSel);
            coefrevexteriorSel = new CoefRevExterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevext:tblListaCoefRevext");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefRevext').hide()");
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

    public List<CoefRevExterior> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefRevExterior> lstCoeficientes) {
        this.lstCoeficientes = lstCoeficientes;
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

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    public CoefRevExterior getObjCoefRevexterior() {
        return objCoefRevexterior;
    }

    public void setObjCoefRevexterior(CoefRevExterior objCoefRevexterior) {
        this.objCoefRevexterior = objCoefRevexterior;
    }

    public CoefRevExterior getCoefrevexteriorSel() {
        return coefrevexteriorSel;
    }

    public void setCoefrevexteriorSel(CoefRevExterior coefrevexteriorSel) {
        this.coefrevexteriorSel = coefrevexteriorSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
