/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.reportes.controladores;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CTitulosEmitidosAnualmente implements Serializable {

    private List<Titulo> lstAnios;
    private int anio;
    private int anioActual;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public CTitulosEmitidosAnualmente() {
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        anioActual = Calendar.getInstance().get(Calendar.YEAR);
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

    public void obtenerTitulos() {
        try {
            setLstAnios(FTitulos.consultarTitulosDadoAnio(anio));
        } catch (Exception e) {
            System.out.println("public void obtenerAnios() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerAnios() dice: " + e.getMessage());
        }
    }

    public void verReporteLibroPredial() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {            
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//LibroPredial.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.getReporteLibroPredial(ruta, anio);
            reporte=null;
        } catch (Exception e) {
            System.out.println("public void verReporteLibroPredial() dice: " + e.getMessage());
            Util.addErrorMessage("public void verReporteLibroPredial() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstAnios
     */
    public List<Titulo> getLstAnios() {
        return lstAnios;
    }

    /**
     * @param lstAnios the lstAnios to set
     */
    public void setLstAnios(List<Titulo> lstAnios) {
        this.lstAnios = lstAnios;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
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
     * @return the anioActual
     */
    public int getAnioActual() {
        return anioActual;
    }

    /**
     * @param anioActual the anioActual to set
     */
    public void setAnioActual(int anioActual) {
        this.anioActual = anioActual;
    }

}
