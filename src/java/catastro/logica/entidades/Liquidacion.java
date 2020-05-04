package catastro.logica.entidades;

import java.sql.Timestamp;

public class Liquidacion {

    private int idLiquidacion;
    private int anio;
    private PredioV2 predio;
    private double costoPiso1;
    private double costoPiso2;
    private double costoPiso3;
    private double costoPiso4;
    private double totalConstruccion;
    private double totalUsoSuelo;
    private double impuestoPredial;
    private double avaluo;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    public Liquidacion() {
        predio = new PredioV2();
    }

    /**
     * @return the idLiquidacion
     */
    public int getIdLiquidacion() {
        return idLiquidacion;
    }

    /**
     * @param idLiquidacion the idLiquidacion to set
     */
    public void setIdLiquidacion(int idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
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
     * @return the costoPiso1
     */
    public double getCostoPiso1() {
        return costoPiso1;
    }

    /**
     * @param costoPiso1 the costoPiso1 to set
     */
    public void setCostoPiso1(double costoPiso1) {
        this.costoPiso1 = costoPiso1;
    }

    /**
     * @return the costoPiso2
     */
    public double getCostoPiso2() {
        return costoPiso2;
    }

    /**
     * @param costoPiso2 the costoPiso2 to set
     */
    public void setCostoPiso2(double costoPiso2) {
        this.costoPiso2 = costoPiso2;
    }

    /**
     * @return the costoPiso3
     */
    public double getCostoPiso3() {
        return costoPiso3;
    }

    /**
     * @param costoPiso3 the costoPiso3 to set
     */
    public void setCostoPiso3(double costoPiso3) {
        this.costoPiso3 = costoPiso3;
    }

    /**
     * @return the costoPiso4
     */
    public double getCostoPiso4() {
        return costoPiso4;
    }

    /**
     * @param costoPiso4 the costoPiso4 to set
     */
    public void setCostoPiso4(double costoPiso4) {
        this.costoPiso4 = costoPiso4;
    }

    /**
     * @return the totalConstruccion
     */
    public double getTotalConstruccion() {
        return totalConstruccion;
    }

    /**
     * @param totalConstruccion the totalConstruccion to set
     */
    public void setTotalConstruccion(double totalConstruccion) {
        this.totalConstruccion = totalConstruccion;
    }

    /**
     * @return the totalUsoSuelo
     */
    public double getTotalUsoSuelo() {
        return totalUsoSuelo;
    }

    /**
     * @param totalUsoSuelo the totalUsoSuelo to set
     */
    public void setTotalUsoSuelo(double totalUsoSuelo) {
        this.totalUsoSuelo = totalUsoSuelo;
    }

    /**
     * @return the impuestoPredial
     */
    public double getImpuestoPredial() {
        return impuestoPredial;
    }

    /**
     * @param impuestoPredial the impuestoPredial to set
     */
    public void setImpuestoPredial(double impuestoPredial) {
        this.impuestoPredial = impuestoPredial;
    }

    /**
     * @return the avaluo
     */
    public double getAvaluo() {
        return avaluo;
    }

    /**
     * @param avaluo the avaluo to set
     */
    public void setAvaluo(double avaluo) {
        this.avaluo = avaluo;
    }

    /**
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
}
