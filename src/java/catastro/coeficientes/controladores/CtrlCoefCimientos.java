/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoeficienteCimientos;
import catastro.coeficientes.funciones.FCoefCimientos;
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
public class CtrlCoefCimientos implements Serializable {

    private List<CoeficienteCimientos> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoeficienteCimientos objCoefCimientos;
    private CoeficienteCimientos coefcimientosSel;
    private String msgBD;

    public CtrlCoefCimientos() {
        obtenerCoeficientes();
        objCoefCimientos = new CoeficienteCimientos();
        coefcimientosSel = new CoeficienteCimientos();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefCimientos.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefCimientos.registrarCoeficiente(objCoefCimientos);
            objCoefCimientos = new CoeficienteCimientos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCimientos:tblListaCoefCimientos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefCimientos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefCimientos.actualizarCoeficiente(coefcimientosSel);
            coefcimientosSel = new CoeficienteCimientos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCimientos:tblListaCoefCimientos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefCimientos').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefCimientos.eliminarCoeficiente(coefcimientosSel);
            coefcimientosSel = new CoeficienteCimientos();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefCimientos:tblListaCoefCimientos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefCimientos').hide()");
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

    public List<CoeficienteCimientos> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoeficienteCimientos> lstCoeficientes) {
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

    public CoeficienteCimientos getObjCoefCimientos() {
        return objCoefCimientos;
    }

    public void setObjCoefCimientos(CoeficienteCimientos objCoefCimientos) {
        this.objCoefCimientos = objCoefCimientos;
    }

    public CoeficienteCimientos getCoefcimientosSel() {
        return coefcimientosSel;
    }

    public void setCoefcimientosSel(CoeficienteCimientos coefcimientosSel) {
        this.coefcimientosSel = coefcimientosSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
