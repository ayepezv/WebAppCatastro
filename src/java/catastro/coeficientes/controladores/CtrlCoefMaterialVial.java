/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefMaterialVial;
import catastro.coeficientes.funciones.FCoefMaterialVial;
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
public class CtrlCoefMaterialVial implements Serializable {

    private List<CoefMaterialVial> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefMaterialVial objCoefMatvial;
    private CoefMaterialVial coefmatvialSel;
    private String msgBD;

    public CtrlCoefMaterialVial() {
        obtenerCoeficientes();
        objCoefMatvial = new CoefMaterialVial();
        coefmatvialSel = new CoefMaterialVial();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefMaterialVial.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefMaterialVial.registrarCoeficiente(objCoefMatvial);
            objCoefMatvial = new CoefMaterialVial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefMatvial:tblListaCoefMatvial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefMatvial').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefMaterialVial.actualizarCoeficiente(coefmatvialSel);
            coefmatvialSel = new CoefMaterialVial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefMatvial:tblListaCoefMatvial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefMatvial').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefMaterialVial.eliminarCoeficiente(coefmatvialSel);
            coefmatvialSel = new CoefMaterialVial();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefMatvial:tblListaCoefMatvial");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefMatvial').hide()");
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

    public List<CoefMaterialVial> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefMaterialVial> lstCoeficientes) {
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

    public CoefMaterialVial getObjCoefMatvial() {
        return objCoefMatvial;
    }

    public void setObjCoefMatvial(CoefMaterialVial objCoefMatvial) {
        this.objCoefMatvial = objCoefMatvial;
    }

    public CoefMaterialVial getCoefmatvialSel() {
        return coefmatvialSel;
    }

    public void setCoefmatvialSel(CoefMaterialVial coefmatvialSel) {
        this.coefmatvialSel = coefmatvialSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
