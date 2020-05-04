package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefRevInterior;
import catastro.coeficientes.funciones.FCoefRevInt;
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
public class CtrlCoefRevInt implements Serializable {

    private List<CoefRevInterior> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefRevInterior objCoefRevint;
    private CoefRevInterior coefrevintSel;
    private String msgBD;

    public CtrlCoefRevInt() {
        obtenerCoeficientes();
        objCoefRevint = new CoefRevInterior();
        coefrevintSel = new CoefRevInterior();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefRevInt.obtenerCoefAcabadosRevInterior();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefRevInt.registrarCoeficiente(objCoefRevint);
            objCoefRevint = new CoefRevInterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevint:tblListaCoefRevint");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefRevint').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefRevInt.actualizarCoeficiente(coefrevintSel);
            coefrevintSel = new CoefRevInterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevint:tblListaCoefRevint");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefRevint').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefRevInt.eliminarCoeficiente(coefrevintSel);
            coefrevintSel = new CoefRevInterior();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefRevint:tblListaCoefRevint");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefRevint').hide()");
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

    public List<CoefRevInterior> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefRevInterior> lstCoeficientes) {
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

    public CoefRevInterior getObjCoefRevint() {
        return objCoefRevint;
    }

    public void setObjCoefRevint(CoefRevInterior objCoefRevint) {
        this.objCoefRevint = objCoefRevint;
    }

    public CoefRevInterior getCoefrevintSel() {
        return coefrevintSel;
    }

    public void setCoefrevintSel(CoefRevInterior coefrevintSel) {
        this.coefrevintSel = coefrevintSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
