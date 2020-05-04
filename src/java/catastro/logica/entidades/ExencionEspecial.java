/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;

/**
 *
 * @author Geovanny
 */
public class ExencionEspecial {

    private int idExccencion;
    private String excencion;
    private String descripcion;
    private String porcentajeTexto;
    private Double porcentajeNumerico;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String formaUso;

    /**
     * @return the idExccencion
     */
    public int getIdExccencion() {
        return idExccencion;
    }

    /**
     * @param idExccencion the idExccencion to set
     */
    public void setIdExccencion(int idExccencion) {
        this.idExccencion = idExccencion;
    }

    /**
     * @return the excencion
     */
    public String getExcencion() {
        return excencion;
    }

    /**
     * @param excencion the excencion to set
     */
    public void setExcencion(String excencion) {
        this.excencion = excencion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the porcentajeTexto
     */
    public String getPorcentajeTexto() {
        return porcentajeTexto;
    }

    /**
     * @param porcentajeTexto the porcentajeTexto to set
     */
    public void setPorcentajeTexto(String porcentajeTexto) {
        this.porcentajeTexto = porcentajeTexto;
    }

    /**
     * @return the porcentajeNumerico
     */
    public Double getPorcentajeNumerico() {
        return porcentajeNumerico;
    }

    /**
     * @param porcentajeNumerico the porcentajeNumerico to set
     */
    public void setPorcentajeNumerico(Double porcentajeNumerico) {
        this.porcentajeNumerico = porcentajeNumerico;
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
     * @return the formaUso
     */
    public String getFormaUso() {
        return formaUso;
    }

    /**
     * @param formaUso the formaUso to set
     */
    public void setFormaUso(String formaUso) {
        this.formaUso = formaUso;
    }

}
