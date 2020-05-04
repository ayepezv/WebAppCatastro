
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefCubierta;
import catastro.coeficientes.funciones.FCoefEstructCubierta;
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
public class CtrlCoefEstructuraCubierta implements Serializable {
        private List<CoefCubierta> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefCubierta objCoefCubierta;
    private CoefCubierta coefcubiertaSel;
    private String msgBD;

    public CtrlCoefEstructuraCubierta() {
        obtenerCoeficientes();
        objCoefCubierta = new CoefCubierta();
        coefcubiertaSel = new CoefCubierta();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefEstructCubierta.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefEstructCubierta.registrarCoeficiente(objCoefCubierta);
            objCoefCubierta = new CoefCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstcubierta:tblListaCoefEstcubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefEstcubierta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefEstructCubierta.actualizarCoeficiente(coefcubiertaSel);
            coefcubiertaSel = new CoefCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstcubierta:tblListaCoefEstcubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefEstcubierta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefEstructCubierta.eliminarCoeficiente(coefcubiertaSel);
            coefcubiertaSel = new CoefCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEstcubierta:tblListaCoefEstcubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefEstcubierta').hide()");
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

    public List<CoefCubierta> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefCubierta> lstCoeficientes) {
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

    public CoefCubierta getObjCoefCubierta() {
        return objCoefCubierta;
    }

    public void setObjCoefCubierta(CoefCubierta objCoefCubierta) {
        this.objCoefCubierta = objCoefCubierta;
    }

    public CoefCubierta getCoefcubiertaSel() {
        return coefcubiertaSel;
    }

    public void setCoefcubiertaSel(CoefCubierta coefcubiertaSel) {
        this.coefcubiertaSel = coefcubiertaSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
