/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria.catastro.beans;

import auditoria.catastro.servicios.FLogsPredios;
import auditoria.logica.entidades.LogPredio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlCambiosPredios implements Serializable {

    private ArrayList<LogPredio> lstCambios;
    private Date fechaInicio;
    private Date fechaFin;
    private LogPredio predioSel;
    private int band;
    private int zona;
    private int sector;
    private int manzana;
    private int predio;

    public void obtenerCambios() {
        try {
            java.sql.Date sqlFechaInicio = Util.sqlDate(getFechaInicio());
            java.sql.Date sqlFechaFin = Util.sqlDate(getFechaFin());
            setLstCambios(FLogsPredios.listarLogsPredio(sqlFechaInicio, sqlFechaFin));
            System.out.println("Total de prdios obtenidos: " + getLstCambios().size());
        } catch (Exception e) {
            System.out.println("public void obtenerCambios() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerCambios() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            lstCambios = new ArrayList<>();
            fechaInicio = null;
            fechaFin = null;
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstCambios
     */
    public ArrayList<LogPredio> getLstCambios() {
        return lstCambios;
    }

    /**
     * @param lstCambios the lstCambios to set
     */
    public void setLstCambios(ArrayList<LogPredio> lstCambios) {
        this.lstCambios = lstCambios;
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
     * @return the predioSel
     */
    public LogPredio getPredioSel() {
        return predioSel;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(LogPredio predioSel) {
        this.predioSel = predioSel;
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

}
