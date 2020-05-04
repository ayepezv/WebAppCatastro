
package catastro.webservice.entidades;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Andres ayepezv@gmail.com
 */
public class ReportePrediosClaveCatastral {
    
private int id;
private int parroquia;
private int zona;
private int sector;
private int manzana;
private int predio;
private double propiedadHorizontal;
private int propiedadHorPiso;
private int  propiedadHorUnidad;
private String claveCatastral;
private String propietario;
private String cedula;
private String direccion;
private String numeroTitulo;
private Timestamp fechaTitulo;
private int anio;
private double valorPredial;
private double valorBomberos;
private double valorDescuento;
private double valorRecargo;
private double valorInteres;
private double valorEmision;
private double valorTitulo;
private Timestamp fechaPago;
private Date fechaIniWS;
private Date fechaFinWS;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the parroquia
     */
    public int getParroquia() {
        return parroquia;
    }

    /**
     * @param parroquia the parroquia to set
     */
    public void setParroquia(int parroquia) {
        this.parroquia = parroquia;
    }

    /**
     * @return the zona
     */
    public int getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(int zona) {
        this.zona = zona;
    }

    /**
     * @return the sector
     */
    public int getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(int sector) {
        this.sector = sector;
    }

    /**
     * @return the manzana
     */
    public int getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    /**
     * @return the predio
     */
    public int getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(int predio) {
        this.predio = predio;
    }

    /**
     * @return the propiedadHorizontal
     */
    public double getPropiedadHorizontal() {
        return propiedadHorizontal;
    }

    /**
     * @param propiedadHorizontal the propiedadHorizontal to set
     */
    public void setPropiedadHorizontal(double propiedadHorizontal) {
        this.propiedadHorizontal = propiedadHorizontal;
    }

    /**
     * @return the propiedadHorPiso
     */
    public int getPropiedadHorPiso() {
        return propiedadHorPiso;
    }

    /**
     * @param propiedadHorPiso the propiedadHorPiso to set
     */
    public void setPropiedadHorPiso(int propiedadHorPiso) {
        this.propiedadHorPiso = propiedadHorPiso;
    }

    /**
     * @return the propiedadHorUnidad
     */
    public int getPropiedadHorUnidad() {
        return propiedadHorUnidad;
    }

    /**
     * @param propiedadHorUnidad the propiedadHorUnidad to set
     */
    public void setPropiedadHorUnidad(int propiedadHorUnidad) {
        this.propiedadHorUnidad = propiedadHorUnidad;
    }

    /**
     * @return the claveCatastral
     */
    public String getClaveCatastral() {
        return claveCatastral;
    }

    /**
     * @param claveCatastral the claveCatastral to set
     */
    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    /**
     * @return the propietario
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the numeroTitulo
     */
    public String getNumeroTitulo() {
        return numeroTitulo;
    }

    /**
     * @param numeroTitulo the numeroTitulo to set
     */
    public void setNumeroTitulo(String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    /**
     * @return the fechaTitulo
     */
    public Timestamp getFechaTitulo() {
        return fechaTitulo;
    }

    /**
     * @param fechaTitulo the fechaTitulo to set
     */
    public void setFechaTitulo(Timestamp fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
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
     * @return the valorPredial
     */
    public double getValorPredial() {
        return valorPredial;
    }

    /**
     * @param valorPredial the valorPredial to set
     */
    public void setValorPredial(double valorPredial) {
        this.valorPredial = valorPredial;
    }

    /**
     * @return the valorBomberos
     */
    public double getValorBomberos() {
        return valorBomberos;
    }

    /**
     * @param valorBomberos the valorBomberos to set
     */
    public void setValorBomberos(double valorBomberos) {
        this.valorBomberos = valorBomberos;
    }

    /**
     * @return the valorDescuento
     */
    public double getValorDescuento() {
        return valorDescuento;
    }

    /**
     * @param valorDescuento the valorDescuento to set
     */
    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    /**
     * @return the valorRecargo
     */
    public double getValorRecargo() {
        return valorRecargo;
    }

    /**
     * @param valorRecargo the valorRecargo to set
     */
    public void setValorRecargo(double valorRecargo) {
        this.valorRecargo = valorRecargo;
    }

    /**
     * @return the valorInteres
     */
    public double getValorInteres() {
        return valorInteres;
    }

    /**
     * @param valorInteres the valorInteres to set
     */
    public void setValorInteres(double valorInteres) {
        this.valorInteres = valorInteres;
    }

    /**
     * @return the valorEmision
     */
    public double getValorEmision() {
        return valorEmision;
    }

    /**
     * @param valorEmision the valorEmision to set
     */
    public void setValorEmision(double valorEmision) {
        this.valorEmision = valorEmision;
    }

    /**
     * @return the valorTitulo
     */
    public double getValorTitulo() {
        return valorTitulo;
    }

    /**
     * @param valorTitulo the valorTitulo to set
     */
    public void setValorTitulo(double valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    /**
     * @return the fechaPago
     */
    public Timestamp getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the fechaIniWS
     */
    public Date getFechaIniWS() {
        return fechaIniWS;
    }

    /**
     * @param fechaIniWS the fechaIniWS to set
     */
    public void setFechaIniWS(Date fechaIniWS) {
        this.fechaIniWS = fechaIniWS;
    }

    /**
     * @return the fechaFinWS
     */
    public Date getFechaFinWS() {
        return fechaFinWS;
    }

    /**
     * @param fechaFinWS the fechaFinWS to set
     */
    public void setFechaFinWS(Date fechaFinWS) {
        this.fechaFinWS = fechaFinWS;
    }

    

    
}
