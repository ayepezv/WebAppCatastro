/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import recursos.Util;

/**
 *
 * @author Geovanny
 */
@ManagedBean
@ViewScoped
public class CtRentasRecaudacionPredial implements Serializable {

    private List<Titulo> lstTransacciones;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fecha;
    private int band;
    private double total;

    public CtRentasRecaudacionPredial() {
        obtenerTransacciones();
    }

    public void obtenerTransacciones() {
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            System.out.println("Fecha de hoy: " + sqlDate);
            setLstTransacciones(FTitulos.obtenerRecaudacionesPredialDadoFecha(sqlDate));
            setTotal(0);
            for (int i = 0; i < getLstTransacciones().size(); i++) {
                setTotal(getTotal() + getLstTransacciones().get(i).getTotalPagar());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se recaudó ", String.valueOf(getTotal()) + " USD"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println("public void obtenerTransacciones() dice: " + e.getMessage());
        }
    }

    public void obtenerTransaccionesDadoFecha() {
        try {
            setLstTransacciones(FTitulos.obtenerRecaudacionesPredialDadoFecha(Util.sqlDate(getFecha())));
            setTotal(0);
            for (int i = 0; i < getLstTransacciones().size(); i++) {
                setTotal(getTotal() + getLstTransacciones().get(i).getTotalPagar());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se recaudó ", String.valueOf(getTotal()) + " USD"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void obtenerTransaccionesDadoRangoFecha() {
        try {
            setLstTransacciones(FTitulos.obtenerRecaudacionesPredialDadoRangoFechas(Util.sqlDate(getFechaInicio()), Util.sqlDate(getFechaFin())));
            setTotal(0);
            for (int i = 0; i < getLstTransacciones().size(); i++) {
                setTotal(getTotal() + getLstTransacciones().get(i).getTotalPagar());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se recaudó ", String.valueOf(getTotal()) + " USD"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    /**
     * @return the lstTransacciones
     */
    public List<Titulo> getLstTransacciones() {
        return lstTransacciones;
    }

    /**
     * @param lstTransacciones the lstTransacciones to set
     */
    public void setLstTransacciones(List<Titulo> lstTransacciones) {
        this.lstTransacciones = lstTransacciones;
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
