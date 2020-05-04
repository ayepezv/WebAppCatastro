/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aguapotable.presentacion.controladores;

import aguapotable.logica.entidades.Cuenta;
import aguapotable.logica.funciones.FCuenta;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class ControladorCuentaAP implements Serializable {

    private List<Cuenta> lstCuentas;
    private Cuenta objCuenta;
    private Cuenta cuentaSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private String claveCatastral;
    private PredioV2 objPredio;
    private ArrayList<PredioV2> lstPredios;

    public ControladorCuentaAP() {
        objPredio = new PredioV2();
        objCuenta = new Cuenta();
        cuentaSel = new Cuenta();
        obtenerCuentas();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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

    public void encontrarCuenta() {
        try {
            this.lstCuentas = FCuenta.encontrarCuenta(claveCatastral);
        } catch (Exception e) {
            System.out.println("public void encontrarCuenta() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void buscarPredio() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(claveCatastral));
            System.out.println("total de predios: " + lstPredios.size());
        } catch (Exception e) {
            System.out.println("public void buscarPredio() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void registrarCuentaDadoPredio() {
        try {
            objCuenta.setPredio(objPredio);
            objCuenta.setSessionUsuario(sessionUsuario);
            String msg = FCuenta.registrarCuentaDadoPredio(getObjCuenta());
            Util.addSuccessMessage(msg);
            objCuenta = new Cuenta();
            obtenerCuentas();
            resetearFitrosTabla("frmPrincipal:tblCuentas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaCuentaPredio').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void registrarCuentaDadoPredio() dice: " + e.getMessage());
        }
    }

    public void obtenerCuentas() {
        try {
            setLstCuentas(FCuenta.obtenerCuentas());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void obtenerCuentas() dice: " + e.getMessage());
        }
    }

    public void registrarCuenta() {
        try {
            objCuenta.setSessionUsuario(sessionUsuario);
            String msg = FCuenta.registrarCuentaCompleta(getObjCuenta());
            Util.addSuccessMessage(msg);
            objCuenta = new Cuenta();
            obtenerCuentas();
            resetearFitrosTabla("frmPrincipal:tblCuentas");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaCuenta').hide()");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            System.out.println("public void registrarCuenta() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstCuentas
     */
    public List<Cuenta> getLstCuentas() {
        return lstCuentas;
    }

    /**
     * @param lstCuentas the lstCuentas to set
     */
    public void setLstCuentas(List<Cuenta> lstCuentas) {
        this.lstCuentas = lstCuentas;
    }

    /**
     * @return the objCuenta
     */
    public Cuenta getObjCuenta() {
        return objCuenta;
    }

    /**
     * @param objCuenta the objCuenta to set
     */
    public void setObjCuenta(Cuenta objCuenta) {
        this.objCuenta = objCuenta;
    }

    /**
     * @return the cuentaSel
     */
    public Cuenta getCuentaSel() {
        return cuentaSel;
    }

    /**
     * @param cuentaSel the cuentaSel to set
     */
    public void setCuentaSel(Cuenta cuentaSel) {
        this.cuentaSel = cuentaSel;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the claveCatastral
     */
    public String getClaveCatastral() {
        return claveCatastral;
    }

    /**
     * @param claveCatastral the claveCatastral to set
     */
    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    /**
     * @return the objPredio
     */
    public PredioV2 getObjPredio() {
        return objPredio;
    }

    /**
     * @param objPredio the objPredio to set
     */
    public void setObjPredio(PredioV2 objPredio) {
        this.objPredio = objPredio;
    }

    /**
     * @return the lstPredios
     */
    public ArrayList<PredioV2> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(ArrayList<PredioV2> lstPredios) {
        this.lstPredios = lstPredios;
    }

}
