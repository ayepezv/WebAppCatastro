/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;

public class Piso {

    private int idPiso;
    private Bloque bloque;
    private String piso;
    private String descripcion;
    private double areaPiso;
    private String condFisica;
    private double valorUnidad;
    private int anioEdificacion;
    private int anioReconstruccion;
    private String estado;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public Piso() {
        bloque = new Bloque();
    }

    public int getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(int idPiso) {
        this.idPiso = idPiso;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getAreaPiso() {
        return areaPiso;
    }

    public void setAreaPiso(double areaPiso) {
        this.areaPiso = areaPiso;
    }

    public String getCondFisica() {
        return condFisica;
    }

    public void setCondFisica(String condFisica) {
        this.condFisica = condFisica;
    }

    public double getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(double valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    public int getAnioEdificacion() {
        return anioEdificacion;
    }

    public void setAnioEdificacion(int anioEdificacion) {
        this.anioEdificacion = anioEdificacion;
    }

    public int getAnioReconstruccion() {
        return anioReconstruccion;
    }

    public void setAnioReconstruccion(int anioReconstruccion) {
        this.anioReconstruccion = anioReconstruccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
