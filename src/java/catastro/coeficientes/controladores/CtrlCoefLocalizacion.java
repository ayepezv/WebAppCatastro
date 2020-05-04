
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefLocalizacion;
import catastro.coeficientes.funciones.FCoefLocalizacion;
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
public class CtrlCoefLocalizacion implements Serializable{
     private List<CoefLocalizacion> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefLocalizacion objCoefLocalizacion;
    private CoefLocalizacion coeflocalizacionSel;
    private String msgBD;

    public CtrlCoefLocalizacion() {
        obtenerCoeficientes();
        objCoefLocalizacion = new CoefLocalizacion();
        coeflocalizacionSel = new CoefLocalizacion();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefLocalizacion.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefLocalizacion.registrarCoeficiente(objCoefLocalizacion);
            objCoefLocalizacion = new CoefLocalizacion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefLocalizacion:tblListaCoefLocalizacion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefLocalizacion').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefLocalizacion.actualizarCoeficiente(coeflocalizacionSel);
            coeflocalizacionSel = new CoefLocalizacion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefLocalizacion:tblListaCoefLocalizacion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefLocalizacion').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefLocalizacion.eliminarCoeficiente(coeflocalizacionSel);
            coeflocalizacionSel = new CoefLocalizacion();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefLocalizacion:tblListaCoefLocalizacion");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefLocalizacion').hide()");
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

    public List<CoefLocalizacion> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefLocalizacion> lstCoeficientes) {
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

    public CoefLocalizacion getObjCoefLocalizacion() {
        return objCoefLocalizacion;
    }

    public void setObjCoefLocalizacion(CoefLocalizacion objCoefLocalizacion) {
        this.objCoefLocalizacion = objCoefLocalizacion;
    }

    public CoefLocalizacion getCoeflocalizacionSel() {
        return coeflocalizacionSel;
    }

    public void setCoeflocalizacionSel(CoefLocalizacion coeflocalizacionSel) {
        this.coeflocalizacionSel = coeflocalizacionSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
