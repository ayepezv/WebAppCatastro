
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefCubreventanas;
import catastro.coeficientes.funciones.FCoefAcabCubreventanas;
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
public class CtrlCoefAcabCubreVentanas implements Serializable {
     private List<CoefCubreventanas> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefCubreventanas objCoefCubreventanas;
    private CoefCubreventanas coefcubreventanasSel;
    private String msgBD;

    public CtrlCoefAcabCubreVentanas() {
        obtenerCoeficientes();
        objCoefCubreventanas = new CoefCubreventanas();
        coefcubreventanasSel = new CoefCubreventanas();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabCubreventanas.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabCubreventanas.registrarCoeficiente(objCoefCubreventanas);
            objCoefCubreventanas = new CoefCubreventanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCubreventanas:tblListaCoefCubreventanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefCubreventanas').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAcabCubreventanas.actualizarCoeficiente(coefcubreventanasSel);
            coefcubreventanasSel = new CoefCubreventanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCubreventanas:tblListaCoefCubreventanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefCubreventanas').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAcabCubreventanas.eliminarCoeficiente(coefcubreventanasSel);
            coefcubreventanasSel = new CoefCubreventanas();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCubreventanas:tblListaCoefCubreventanas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefCubreventanas').hide()");
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

    public List<CoefCubreventanas> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefCubreventanas> lstCoeficientes) {
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

    public CoefCubreventanas getObjCoefCubreventanas() {
        return objCoefCubreventanas;
    }

    public void setObjCoefCubreventanas(CoefCubreventanas objCoefCubreventanas) {
        this.objCoefCubreventanas = objCoefCubreventanas;
    }

    public CoefCubreventanas getCoefcubreventanasSel() {
        return coefcubreventanasSel;
    }

    public void setCoefcubreventanasSel(CoefCubreventanas coefcubreventanasSel) {
        this.coefcubreventanasSel = coefcubreventanasSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
