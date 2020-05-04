/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny
 */
public class PredioExcencion {

    private PredioV2 predio;
    private ExencionEspecial excencion;
    private Date fechaInicio;
    private Date fechaFin;
    private double porcentaje;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;
    private String observaciones;
    private double avaluoPredio;
    private double dsctoExcencion;
    private double avaluoImponible;
    private double capitalBiess;

    public PredioExcencion(Date fechaInicio) {
        predio = new PredioV2();
        excencion = new ExencionEspecial();
        sessionUsuario = new Usuario();
        this.fechaInicio = fechaInicio;
    }

    public PredioExcencion() {
        predio = new PredioV2();
        excencion = new ExencionEspecial();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the predio
     */
    public PredioV2 getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(PredioV2 predio) {
        this.predio = predio;
    }

    /**
     * @return the excencion
     */
    public ExencionEspecial getExcencion() {
        return excencion;
    }

    /**
     * @param excencion the excencion to set
     */
    public void setExcencion(ExencionEspecial excencion) {
        this.excencion = excencion;
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
     * @return the porcentaje
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * @return the estadoLogico
     */
    public String getEstadoLogico() {
        return estadoLogico;
    }

    /**
     * @param estadoLogico the estadoLogico to set
     */
    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaActualizacion
     */
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the fechaBaja
     */
    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the avaluoPredio
     */
    public double getAvaluoPredio() {
        return avaluoPredio;
    }

    /**
     * @param avaluoPredio the avaluoPredio to set
     */
    public void setAvaluoPredio(double avaluoPredio) {
        this.avaluoPredio = avaluoPredio;
    }

    /**
     * @return the dsctoExcencion
     */
    public double getDsctoExcencion() {
        return dsctoExcencion;
    }

    /**
     * @param dsctoExcencion the dsctoExcencion to set
     */
    public void setDsctoExcencion(double dsctoExcencion) {
        this.dsctoExcencion = dsctoExcencion;
    }

    /**
     * @return the avaluoImponible
     */
    public double getAvaluoImponible() {
        return avaluoImponible;
    }

    /**
     * @param avaluoImponible the avaluoImponible to set
     */
    public void setAvaluoImponible(double avaluoImponible) {
        this.avaluoImponible = avaluoImponible;
    }

    /**
     * @return the capitalBiess
     */
    public double getCapitalBiess() {
        return capitalBiess;
    }

    /**
     * @param capitalBiess the capitalBiess to set
     */
    public void setCapitalBiess(double capitalBiess) {
        this.capitalBiess = capitalBiess;
    }

}
