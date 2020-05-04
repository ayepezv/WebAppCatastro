package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefAcabadosCubierta;
import catastro.coeficientes.funciones.FCoefAcabCubierta;
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

public class CtrlCoefAcabCubierta implements Serializable {

    private List<CoefAcabadosCubierta> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefAcabadosCubierta objCoefCubierta;
    private CoefAcabadosCubierta coefcubiertaSel;
    private String msgBD;

    public CtrlCoefAcabCubierta() {
        obtenerCoeficientes();
        objCoefCubierta = new CoefAcabadosCubierta();
        coefcubiertaSel = new CoefAcabadosCubierta();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabCubierta.obtenerCoefAcabadosCubierta();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabCubierta.registrarCoeficiente(objCoefCubierta);
            objCoefCubierta = new CoefAcabadosCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAcCubierta:tblListaCoefAcCubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefAcCubierta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefAcabCubierta.actualizarCoeficiente(coefcubiertaSel);
            coefcubiertaSel = new CoefAcabadosCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAcCubierta:tblListaCoefAcCubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefAcCubierta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefAcabCubierta.eliminarCoeficiente(coefcubiertaSel);
            coefcubiertaSel = new CoefAcabadosCubierta();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefAcCubierta:tblListaCoefAcCubierta");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefAcCubierta').hide()");
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

    public List<CoefAcabadosCubierta> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefAcabadosCubierta> lstCoeficientes) {
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

    public CoefAcabadosCubierta getObjCoefCubierta() {
        return objCoefCubierta;
    }

    public void setObjCoefCubierta(CoefAcabadosCubierta objCoefCubierta) {
        this.objCoefCubierta = objCoefCubierta;
    }

    public CoefAcabadosCubierta getCoefcubiertaSel() {
        return coefcubiertaSel;
    }

    public void setCoefcubiertaSel(CoefAcabadosCubierta coefcubiertaSel) {
        this.coefcubiertaSel = coefcubiertaSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
