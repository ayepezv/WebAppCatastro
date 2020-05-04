/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefEnergiaElectrica;
import catastro.coeficientes.funciones.FCoefEnergiaElectrica;
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
public class CtrlCoefEnergiaElectrica implements Serializable {

    private List<CoefEnergiaElectrica> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefEnergiaElectrica objCoefEnergiaelectrica;
    private CoefEnergiaElectrica coefenergiaelectricaSel;
    private String msgBD;

    public CtrlCoefEnergiaElectrica() {
        obtenerCoeficientes();
        objCoefEnergiaelectrica = new CoefEnergiaElectrica();
        coefenergiaelectricaSel = new CoefEnergiaElectrica();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefEnergiaElectrica.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefEnergiaElectrica.registrarCoeficiente(objCoefEnergiaelectrica);
            objCoefEnergiaelectrica = new CoefEnergiaElectrica();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEnergiaelectrica:tblListaCoefEnergiaelectrica");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefEnergiaelectrica').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefEnergiaElectrica.actualizarCoeficiente(coefenergiaelectricaSel);
            coefenergiaelectricaSel = new CoefEnergiaElectrica();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEnergiaelectrica:tblListaCoefEnergiaelectrica");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefEnergiaelectrica').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefEnergiaElectrica.eliminarCoeficiente(coefenergiaelectricaSel);
            coefenergiaelectricaSel = new CoefEnergiaElectrica();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEnergiaelectrica:tblListaCoefEnergiaelectrica");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefEnergiaelectrica').hide()");
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

    public List<CoefEnergiaElectrica> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefEnergiaElectrica> lstCoeficientes) {
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

    public CoefEnergiaElectrica getObjCoefEnergiaelectrica() {
        return objCoefEnergiaelectrica;
    }

    public void setObjCoefEnergiaelectrica(CoefEnergiaElectrica objCoefEnergiaelectrica) {
        this.objCoefEnergiaelectrica = objCoefEnergiaelectrica;
    }

    public CoefEnergiaElectrica getCoefenergiaelectricaSel() {
        return coefenergiaelectricaSel;
    }

    public void setCoefenergiaelectricaSel(CoefEnergiaElectrica coefenergiaelectricaSel) {
        this.coefenergiaelectricaSel = coefenergiaelectricaSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }

}
