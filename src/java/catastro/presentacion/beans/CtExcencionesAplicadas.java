/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioExcencion;
import catastro.logica.servicios.FPredioExcencion;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtExcencionesAplicadas implements Serializable {

    private int band;
    private String criterio;
    private Date fechaInicio;
    private Date fechaFin;
    private List<PredioExcencion> lstExcenciones;
    private PredioExcencion objExcencion;
    private PredioExcencion excencionSel;

    public CtExcencionesAplicadas() {
        objExcencion = new PredioExcencion();
        excencionSel = new PredioExcencion();
    }

    public void obtenerExcencionesCriterio() {
        try {
            setLstExcenciones(FPredioExcencion.obtenerExcecnionesDadoCriterio(getCriterio()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    String.valueOf(getLstExcenciones().size()),
                    "Excenciones aplicadas"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error",
                    e.getMessage()));
        }
    }

    public void obtenerExcenciones() {
        try {
            System.out.println("Fecha de inicio " + getFechaInicio() + " fecha fin: " + getFechaFin());
            setLstExcenciones(FPredioExcencion.obtenerExcecnionesDadoFecha(Util.sqlDate(getFechaInicio()), Util.sqlDate(getFechaFin())));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    String.valueOf(getLstExcenciones().size()),
                    "Excenciones aplicadas"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error",
                    e.getMessage()));
        }
    }

    public void verReporteExcenciones() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/excenciones_por_fechas.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.reporteExcencionesFechas(ruta, getFechaInicio(), getFechaFin());
        } catch (Exception e) {
            System.out.println("ERROR public void verReporteExcencionesCriterio() dice: " + e.getMessage());
        }

    }

    public void verReporteExcencionesCriterio() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/excencionesPorCriterio.jasper");
            FacesContext.getCurrentInstance().responseComplete();
            reporte.reporteExcencionesPorCriterio(ruta, getCriterio());
        } catch (Exception e) {
            System.out.println("ERROR public void verReporteExcencionesCriterio() dice: " + e.getMessage());
        }

    }

    /**
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
    }

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
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
     * @return the lstExcenciones
     */
    public List<PredioExcencion> getLstExcenciones() {
        return lstExcenciones;
    }

    /**
     * @param lstExcenciones the lstExcenciones to set
     */
    public void setLstExcenciones(List<PredioExcencion> lstExcenciones) {
        this.lstExcenciones = lstExcenciones;
    }

    /**
     * @return the objExcencion
     */
    public PredioExcencion getObjExcencion() {
        return objExcencion;
    }

    /**
     * @param objExcencion the objExcencion to set
     */
    public void setObjExcencion(PredioExcencion objExcencion) {
        this.objExcencion = objExcencion;
    }

    /**
     * @return the excencionSel
     */
    public PredioExcencion getExcencionSel() {
        return excencionSel;
    }

    /**
     * @param excencionSel the excencionSel to set
     */
    public void setExcencionSel(PredioExcencion excencionSel) {
        this.excencionSel = excencionSel;
    }

}
