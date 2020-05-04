
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoeficienteEstado;
import catastro.coeficientes.funciones.FCoefEstado;
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
public class CtrlCoefEstado implements Serializable {
        private List<CoeficienteEstado> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoeficienteEstado objCoefEstado;
    private CoeficienteEstado coefestadoSel;
    private String msgBD;

    public CtrlCoefEstado() {
        obtenerCoeficientes();
        objCoefEstado = new CoeficienteEstado();
        coefestadoSel = new CoeficienteEstado();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefEstado.coeficientesEstado();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefEstado.registrarCoeficiente(objCoefEstado);
            objCoefEstado = new CoeficienteEstado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstadoconst:tblListaCoefEstadoconst");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefEstadoconst').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefEstado.actualizarCoeficiente(coefestadoSel);
            coefestadoSel = new CoeficienteEstado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstadoconst:tblListaCoefEstadoconst");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefEstadoconst').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefEstado.eliminarCoeficiente(coefestadoSel);
            coefestadoSel = new CoeficienteEstado();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstadoconst:tblListaCoefEstadoconst");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefEstadoconst').hide()");
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

    public List<CoeficienteEstado> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoeficienteEstado> lstCoeficientes) {
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

    public CoeficienteEstado getObjCoefEstado() {
        return objCoefEstado;
    }

    public void setObjCoefEstado(CoeficienteEstado objCoefEstado) {
        this.objCoefEstado = objCoefEstado;
    }

    public CoeficienteEstado getCoefestadoSel() {
        return coefestadoSel;
    }

    public void setCoefestadoSel(CoeficienteEstado coefestadoSel) {
        this.coefestadoSel = coefestadoSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
