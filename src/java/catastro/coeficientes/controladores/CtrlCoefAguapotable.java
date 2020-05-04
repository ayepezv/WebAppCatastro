package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefAguaPotable;
import catastro.coeficientes.funciones.FCoefAguaPotable;
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
public class CtrlCoefAguapotable implements Serializable {

    private List<CoefAguaPotable> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefAguaPotable objCoefAguapotable;
    private CoefAguaPotable coefaguapotableSel;
    private String msgBD;

    public CtrlCoefAguapotable() {
        obtenerCoeficientes();
        objCoefAguapotable = new CoefAguaPotable();
        coefaguapotableSel = new CoefAguaPotable();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAguaPotable.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAguaPotable.registrarCoeficiente(objCoefAguapotable);
            objCoefAguapotable = new CoefAguaPotable();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAguapotable:tblListaCoefAguapotable");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefAguapotable').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAguaPotable.actualizarCoeficiente(coefaguapotableSel);
            coefaguapotableSel = new CoefAguaPotable();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAguapotable:tblListaCoefAguapotable");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefAguapotable').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAguaPotable.eliminarCoeficiente(coefaguapotableSel);
            coefaguapotableSel = new CoefAguaPotable();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAguapotable:tblListaCoefAguapotable");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefAguapotable').hide()");
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

    public List<CoefAguaPotable> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefAguaPotable> lstCoeficientes) {
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

    public CoefAguaPotable getObjCoefAguapotable() {
        return objCoefAguapotable;
    }

    public void setObjCoefAguapotable(CoefAguaPotable objCoefAguapotable) {
        this.objCoefAguapotable = objCoefAguapotable;
    }

    public CoefAguaPotable getCoefaguapotableSel() {
        return coefaguapotableSel;
    }

    public void setCoefaguapotableSel(CoefAguaPotable coefaguapotableSel) {
        this.coefaguapotableSel = coefaguapotableSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
