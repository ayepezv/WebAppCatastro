package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefCocina;
import catastro.coeficientes.funciones.FCoefAcabCocina;
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
public class CtrlCoefAcabCocina implements Serializable {
    
    private List<CoefCocina> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefCocina objCoefCocina;
    private CoefCocina coefcocinaSel;
    private String msgBD;

    public CtrlCoefAcabCocina(){
        obtenerCoeficientes();
        objCoefCocina = new CoefCocina();
        coefcocinaSel = new CoefCocina();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabCocina.obtenerCoefAcabadosCocina();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabCocina.registrarCoeficiente(objCoefCocina);
            objCoefCocina = new CoefCocina();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCocina:tblListaCoefCocina");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefCocina').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void actualizar() {
        try {
            msgBD = FCoefAcabCocina.actualizarCoeficiente(coefcocinaSel);
            coefcocinaSel = new CoefCocina();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCocina:tblListaCoefCocina");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefCocina').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

     public void eliminar() {
        try {
            msgBD = FCoefAcabCocina.eliminarCoeficiente(coefcocinaSel);
            coefcocinaSel = new CoefCocina();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCocina:tblListaCoefCocina");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefCocina').hide()");
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

    public List<CoefCocina> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefCocina> lstCoeficientes) {
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

    public CoefCocina getObjCoefCocina() {
        return objCoefCocina;
    }

    public void setObjCoefCocina(CoefCocina objCoefCocina) {
        this.objCoefCocina = objCoefCocina;
    }

    public CoefCocina getCoefcocinaSel() {
        return coefcocinaSel;
    }

    public void setCoefcocinaSel(CoefCocina coefcocinaSel) {
        this.coefcocinaSel = coefcocinaSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
