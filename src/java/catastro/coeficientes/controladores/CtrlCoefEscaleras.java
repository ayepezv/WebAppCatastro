/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.coeficientes.controladores;

import catastro.coeficientes.entidades.CoefEscaleras;
import catastro.coeficientes.funciones.FCoefEscaleras;
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
public class CtrlCoefEscaleras implements Serializable {
    private List<CoefEscaleras> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefEscaleras objCoefEscaleras;
    private CoefEscaleras coefescalerasSel;
    private String msgBD;

    public CtrlCoefEscaleras() {
        obtenerCoeficientes();
        objCoefEscaleras = new CoefEscaleras();
        coefescalerasSel = new CoefEscaleras();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefEscaleras.obtenerCoeficientes();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefEscaleras.registrarCoeficiente(objCoefEscaleras);
            objCoefEscaleras = new CoefEscaleras();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEscaleras:tblListaCoefEscaleras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefEscaleras').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizar() {
        try {
            msgBD = FCoefEscaleras.actualizarCoeficiente(coefescalerasSel);
            coefescalerasSel = new CoefEscaleras();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEscaleras:tblListaCoefEscaleras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefEscaleras').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            msgBD = FCoefEscaleras.eliminarCoeficiente(coefescalerasSel);
            coefescalerasSel = new CoefEscaleras();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefEscaleras:tblListaCoefEscaleras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefEscaleras').hide()");
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

    public List<CoefEscaleras> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefEscaleras> lstCoeficientes) {
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

    public CoefEscaleras getObjCoefEscaleras() {
        return objCoefEscaleras;
    }

    public void setObjCoefEscaleras(CoefEscaleras objCoefEscaleras) {
        this.objCoefEscaleras = objCoefEscaleras;
    }

    public CoefEscaleras getCoefescalerasSel() {
        return coefescalerasSel;
    }

    public void setCoefescalerasSel(CoefEscaleras coefescalerasSel) {
        this.coefescalerasSel = coefescalerasSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
}
