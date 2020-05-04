package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefVentanas;
import catastro.coeficientes.funciones.FCoefAcabVentanas;
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
public class CtrlCoefAcabVentanas implements Serializable {

    private List<CoefVentanas> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefVentanas objCoefVentanas;
    private CoefVentanas coefventanasSel;
    private String msgBD;

    public CtrlCoefAcabVentanas() {
        obtenerCoeficientes();
        objCoefVentanas = new CoefVentanas();
        coefventanasSel = new CoefVentanas();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabVentanas.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabVentanas.registrarCoeficiente(objCoefVentanas);
            objCoefVentanas = new CoefVentanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefVentanas:tblListaCoefVentanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefVentanas').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAcabVentanas.actualizarCoeficiente(coefventanasSel);
            coefventanasSel = new CoefVentanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefVentanas:tblListaCoefVentanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefVentanas').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAcabVentanas.eliminarCoeficiente(coefventanasSel);
            coefventanasSel = new CoefVentanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefVentanas:tblListaCoefVentanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefVentanas').hide()");
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

    public List<CoefVentanas> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefVentanas> lstCoeficientes) {
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

    public CoefVentanas getObjCoefVentanas() {
        return objCoefVentanas;
    }

    public void setObjCoefVentanas(CoefVentanas objCoefVentanas) {
        this.objCoefVentanas = objCoefVentanas;
    }

    public CoefVentanas getCoefventanasSel() {
        return coefventanasSel;
    }

    public void setCoefventanasSel(CoefVentanas coefventanasSel) {
        this.coefventanasSel = coefventanasSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
}
