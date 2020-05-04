/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny Cudco
 */
public class DetalleUsoSuelo {

    private int idDetalle;
    private PredioV2 predio;
    private double areaMapInfo;
    private double areaParcialMapInfo;
    private double valorMinimo;
    private double frente1;
    private double fondoRelativo;
    private double frenteFondo;
    private double sumaAlicuota;
    private double factMatVial;
    private double factAguaPot;
    private double factEnergElect;
    private double factAlcant;
    private double factAceras;
    private double factBordillos;
    private double factLocalizacion;
    private double factTopografia;
    private double valorMetro;
    private double valorDepresMetro;
    private String procesada;
    private double factconstruccion;
    private double total1;
    private double total2;
    private String estadoLogico;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Timestamp fechaRegistro;
    private Usuario sessionUsuario;

    public DetalleUsoSuelo() {
        predio = new PredioV2();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idDetalle
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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
     * @return the areaMapInfo
     */
    public double getAreaMapInfo() {
        return areaMapInfo;
    }

    /**
     * @param areaMapInfo the areaMapInfo to set
     */
    public void setAreaMapInfo(double areaMapInfo) {
        this.areaMapInfo = areaMapInfo;
    }

    /**
     * @return the areaParcialMapInfo
     */
    public double getAreaParcialMapInfo() {
        return areaParcialMapInfo;
    }

    /**
     * @param areaParcialMapInfo the areaParcialMapInfo to set
     */
    public void setAreaParcialMapInfo(double areaParcialMapInfo) {
        this.areaParcialMapInfo = areaParcialMapInfo;
    }

    /**
     * @return the valorMinimo
     */
    public double getValorMinimo() {
        return valorMinimo;
    }

    /**
     * @param valorMinimo the valorMinimo to set
     */
    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    /**
     * @return the frente1
     */
    public double getFrente1() {
        return frente1;
    }

    /**
     * @param frente1 the frente1 to set
     */
    public void setFrente1(double frente1) {
        this.frente1 = frente1;
    }

    /**
     * @return the fondoRelativo
     */
    public double getFondoRelativo() {
        return fondoRelativo;
    }

    /**
     * @param fondoRelativo the fondoRelativo to set
     */
    public void setFondoRelativo(double fondoRelativo) {
        this.fondoRelativo = fondoRelativo;
    }

    /**
     * @return the frenteFondo
     */
    public double getFrenteFondo() {
        return frenteFondo;
    }

    /**
     * @param frenteFondo the frenteFondo to set
     */
    public void setFrenteFondo(double frenteFondo) {
        this.frenteFondo = frenteFondo;
    }

    /**
     * @return the sumaAlicuota
     */
    public double getSumaAlicuota() {
        return sumaAlicuota;
    }

    /**
     * @param sumaAlicuota the sumaAlicuota to set
     */
    public void setSumaAlicuota(double sumaAlicuota) {
        this.sumaAlicuota = sumaAlicuota;
    }

    /**
     * @return the factMatVial
     */
    public double getFactMatVial() {
        return factMatVial;
    }

    /**
     * @param factMatVial the factMatVial to set
     */
    public void setFactMatVial(double factMatVial) {
        this.factMatVial = factMatVial;
    }

    /**
     * @return the factAguaPot
     */
    public double getFactAguaPot() {
        return factAguaPot;
    }

    /**
     * @param factAguaPot the factAguaPot to set
     */
    public void setFactAguaPot(double factAguaPot) {
        this.factAguaPot = factAguaPot;
    }

    /**
     * @return the factEnergElect
     */
    public double getFactEnergElect() {
        return factEnergElect;
    }

    /**
     * @param factEnergElect the factEnergElect to set
     */
    public void setFactEnergElect(double factEnergElect) {
        this.factEnergElect = factEnergElect;
    }

    /**
     * @return the factAlcant
     */
    public double getFactAlcant() {
        return factAlcant;
    }

    /**
     * @param factAlcant the factAlcant to set
     */
    public void setFactAlcant(double factAlcant) {
        this.factAlcant = factAlcant;
    }

    /**
     * @return the factAceras
     */
    public double getFactAceras() {
        return factAceras;
    }

    /**
     * @param factAceras the factAceras to set
     */
    public void setFactAceras(double factAceras) {
        this.factAceras = factAceras;
    }

    /**
     * @return the factBordillos
     */
    public double getFactBordillos() {
        return factBordillos;
    }

    /**
     * @param factBordillos the factBordillos to set
     */
    public void setFactBordillos(double factBordillos) {
        this.factBordillos = factBordillos;
    }

    /**
     * @return the factLocalizacion
     */
    public double getFactLocalizacion() {
        return factLocalizacion;
    }

    /**
     * @param factLocalizacion the factLocalizacion to set
     */
    public void setFactLocalizacion(double factLocalizacion) {
        this.factLocalizacion = factLocalizacion;
    }

    /**
     * @return the factTopografia
     */
    public double getFactTopografia() {
        return factTopografia;
    }

    /**
     * @param factTopografia the factTopografia to set
     */
    public void setFactTopografia(double factTopografia) {
        this.factTopografia = factTopografia;
    }

    /**
     * @return the valorMetro
     */
    public double getValorMetro() {
        return valorMetro;
    }

    /**
     * @param valorMetro the valorMetro to set
     */
    public void setValorMetro(double valorMetro) {
        this.valorMetro = valorMetro;
    }

    /**
     * @return the valorDepresMetro
     */
    public double getValorDepresMetro() {
        return valorDepresMetro;
    }

    /**
     * @param valorDepresMetro the valorDepresMetro to set
     */
    public void setValorDepresMetro(double valorDepresMetro) {
        this.valorDepresMetro = valorDepresMetro;
    }

    /**
     * @return the procesada
     */
    public String getProcesada() {
        return procesada;
    }

    /**
     * @param procesada the procesada to set
     */
    public void setProcesada(String procesada) {
        this.procesada = procesada;
    }

    /**
     * @return the factconstruccion
     */
    public double getFactconstruccion() {
        return factconstruccion;
    }

    /**
     * @param factconstruccion the factconstruccion to set
     */
    public void setFactconstruccion(double factconstruccion) {
        this.factconstruccion = factconstruccion;
    }

    /**
     * @return the total1
     */
    public double getTotal1() {
        return total1;
    }

    /**
     * @param total1 the total1 to set
     */
    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    /**
     * @return the total2
     */
    public double getTotal2() {
        return total2;
    }

    /**
     * @param total2 the total2 to set
     */
    public void setTotal2(double total2) {
        this.total2 = total2;
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

}
