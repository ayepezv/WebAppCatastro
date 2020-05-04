package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefAlcantarillado;
import catastro.coeficientes.funciones.FCoefAlcantarillado;
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
public class CtrlCoefAlcantarillado implements Serializable {

    private List<CoefAlcantarillado> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefAlcantarillado objCoefAlcantarillado;
    private CoefAlcantarillado coefalcantarilladoSel;
    private String msgBD;

    public CtrlCoefAlcantarillado() {
        obtenerCoeficientes();
        objCoefAlcantarillado = new CoefAlcantarillado();
        coefalcantarilladoSel = new CoefAlcantarillado();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAlcantarillado.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAlcantarillado.registrarCoeficiente(objCoefAlcantarillado);
            objCoefAlcantarillado = new CoefAlcantarillado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAlcantarillado:tblListaCoefAlcantarillado");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefAlcantarillado').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAlcantarillado.actualizarCoeficiente(coefalcantarilladoSel);
            coefalcantarilladoSel = new CoefAlcantarillado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAlcantarillado:tblListaCoefAlcantarillado");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefAlcantarillado').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAlcantarillado.eliminarCoeficiente(coefalcantarilladoSel);
            coefalcantarilladoSel = new CoefAlcantarillado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAlcantarillado:tblListaCoefAlcantarillado");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefAlcantarillado').hide()");
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

    public List<CoefAlcantarillado> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefAlcantarillado> lstCoeficientes) {
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

    public CoefAlcantarillado getObjCoefAlcantarillado() {
        return objCoefAlcantarillado;
    }

    public void setObjCoefAlcantarillado(CoefAlcantarillado objCoefAlcantarillado) {
        this.objCoefAlcantarillado = objCoefAlcantarillado;
    }

    public CoefAlcantarillado getCoefalcantarilladoSel() {
        return coefalcantarilladoSel;
    }

    public void setCoefalcantarilladoSel(CoefAlcantarillado coefalcantarilladoSel) {
        this.coefalcantarilladoSel = coefalcantarilladoSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
