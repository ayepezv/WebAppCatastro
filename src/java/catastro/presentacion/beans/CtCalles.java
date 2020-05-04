/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.Calle;
import catastro.logica.servicios.ServiciosCalles;
import java.io.Serializable;
import java.util.ArrayList;
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

@ManagedBean
@ViewScoped
public class CtCalles implements Serializable {

    private Calle objCalle;
    private Calle calleSel;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private ArrayList<Calle> lstCalles;

    public CtCalles() {
        calleSel = new Calle();
        objCalle = new Calle();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerCalles();
    }

    public void obtenerCalles() {
        try {
            setLstCalles(ServiciosCalles.obtenerCalles());
        } catch (Exception e) {
            System.out.println("public void obtenerCalles() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    public void registrar() {
        try {
            objCalle.setSessionUsuario(sessionUsuario);
            String msg = ServiciosCalles.registrarCalle(objCalle);
            objCalle = new Calle();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaCalle').hide()");
            obtenerCalles();
            resetearFitrosTabla("frmPrincipal:tblCalles");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción exitosa", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

     public void actualizar() {
        try {
            calleSel.setSessionUsuario(sessionUsuario);
            String msg = ServiciosCalles.actualizarCalle(calleSel);
            calleSel = new Calle();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarCalle').hide()");
            obtenerCalles();
            resetearFitrosTabla("frmPrincipal:tblCalles");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición exitosa", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
      public void eliminar() {
        try {
            calleSel.setSessionUsuario(sessionUsuario);
            String msg = ServiciosCalles.eliminarCalle(calleSel);
            calleSel = new Calle();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarCalle').hide()");
            obtenerCalles();
            resetearFitrosTabla("frmPrincipal:tblCalles");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación exitosa", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    /**
     * @return the objCalle
     */
    public Calle getObjCalle() {
        return objCalle;
    }

    /**
     * @param objCalle the objCalle to set
     */
    public void setObjCalle(Calle objCalle) {
        this.objCalle = objCalle;
    }

    /**
     * @return the calleSel
     */
    public Calle getCalleSel() {
        return calleSel;
    }

    /**
     * @param calleSel the calleSel to set
     */
    public void setCalleSel(Calle calleSel) {
        this.calleSel = calleSel;
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
     * @return the lstCalles
     */
    public ArrayList<Calle> getLstCalles() {
        return lstCalles;
    }

    /**
     * @param lstCalles the lstCalles to set
     */
    public void setLstCalles(ArrayList<Calle> lstCalles) {
        this.lstCalles = lstCalles;
    }

    
    
    
}
