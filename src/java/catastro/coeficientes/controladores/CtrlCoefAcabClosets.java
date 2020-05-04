/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.coeficientes.controladores;


import catastro.coeficientes.entidades.CoefClosets;
import catastro.coeficientes.funciones.FCoefAcabClosets;
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
public class CtrlCoefAcabClosets implements Serializable {
    
    private List<CoefClosets> lstCoeficientes;

    private HttpServletRequest httpServletRequest;//s
    private FacesContext faceContext;//s
    private Usuario sessionUsuario; //s

    private CoefClosets objCoefClosets;
    private CoefClosets coefclosetsSel;
    private String msgBD;

    public CtrlCoefAcabClosets(){
        obtenerCoeficientes();
        objCoefClosets = new CoefClosets();
        coefclosetsSel = new CoefClosets();
        sessionUsuario = new Usuario();//S
        faceContext = FacesContext.getCurrentInstance();//s
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();//S
    }

    public void obtenerCoeficientes() {
        try {
            lstCoeficientes = FCoefAcabClosets.obtenerCoefAcabadosClosets();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            msgBD = FCoefAcabClosets.registrarCoeficiente(objCoefClosets);
            objCoefClosets = new CoefClosets();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefClosets:tblListaCoefClosets");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarCoefClosets').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void actualizar() {
        try {
            msgBD = FCoefAcabClosets.actualizarCoeficiente(coefclosetsSel);
            coefclosetsSel = new CoefClosets();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefClosets:tblListaCoefClosets");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCoefClosets').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

     public void eliminar() {
        try {
            msgBD = FCoefAcabClosets.eliminarCoeficiente(coefclosetsSel);
            coefclosetsSel = new CoefClosets();
            obtenerCoeficientes();
            Util.addSuccessMessage(msgBD);
            resetearFitrosTabla("frmListaCoefClosets:tblListaCoefClosets");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCoefClosets').hide()");
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

    public List<CoefClosets> getLstCoeficientes() {
        return lstCoeficientes;
    }

    public void setLstCoeficientes(List<CoefClosets> lstCoeficientes) {
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

    public CoefClosets getObjCoefClosets() {
        return objCoefClosets;
    }

    public void setObjCoefClosets(CoefClosets objCoefClosets) {
        this.objCoefClosets = objCoefClosets;
    }

    public CoefClosets getCoefclosetsSel() {
        return coefclosetsSel;
    }

    public void setCoefclosetsSel(CoefClosets coefclosetsSel) {
        this.coefclosetsSel = coefclosetsSel;
    }

    public String getMsgBD() {
        return msgBD;
    }

    public void setMsgBD(String msgBD) {
        this.msgBD = msgBD;
    }
    
    
    
}
