/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.reportes.controladores;

import catastro.reportes.entidades.ReportePredios;
import catastro.reportes.entidades.ReporteValoresRecaudados;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosRolUsuario;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny Cudco
 */
@ManagedBean
@ViewScoped
public class CtValoresRecaudados implements Serializable {

    private Date fechaInicio;
    private Date fechaFin;
    private ArrayList<RolUsuario> lstRecaudadores;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private RolUsuario objRolUsuario;
    private int idRec;
    private Date fechaMax;

    public CtValoresRecaudados() {
        objRolUsuario = new RolUsuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerRecaudadores();
        fechaMax=new Date();
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerRecaudadores() {
        try {
            setLstRecaudadores(ServiciosRolUsuario.listarUsuariosDadoRol(7));
        } catch (Exception e) {
            System.out.println("public void obtenerRecaudadores() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void verReporteRecaudacion() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {

            System.out.println("bamndera: " + getFechaInicio() + " ----- " + getFechaFin() + " ------ us " + getIdRec());
            ReporteValoresRecaudados reporte = new ReporteValoresRecaudados();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//RecaudacionRangoFechas.jasper");           
            reporte.valoresRecaudados(ruta, getIdRec(), getFechaInicio(), getFechaFin());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("public void verReporteRecaudacion() dice: " + e.getMessage());
        }
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the lstRecaudadores
     */
    public ArrayList<RolUsuario> getLstRecaudadores() {
        return lstRecaudadores;
    }

    /**
     * @param lstRecaudadores the lstRecaudadores to set
     */
    public void setLstRecaudadores(ArrayList<RolUsuario> lstRecaudadores) {
        this.lstRecaudadores = lstRecaudadores;
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
     * @return the objRolUsuario
     */
    public RolUsuario getObjRolUsuario() {
        return objRolUsuario;
    }

    /**
     * @param objRolUsuario the objRolUsuario to set
     */
    public void setObjRolUsuario(RolUsuario objRolUsuario) {
        this.objRolUsuario = objRolUsuario;
    }

    /**
     * @return the idRec
     */
    public int getIdRec() {
        return idRec;
    }

    /**
     * @param idRec the idRec to set
     */
    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    /**
     * @return the fechaMax
     */
    public Date getFechaMax() {
        return fechaMax;
    }

    /**
     * @param fechaMax the fechaMax to set
     */
    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    
    
    
    
    
    
    
    
}
