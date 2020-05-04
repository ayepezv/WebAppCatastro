package catastro.recaudacion.entidades;

import catastro.logica.entidades.PredioV2;
import java.sql.Timestamp;

public class RecaudacionPredial {

    private int id;
    private PredioV2 predio;
    private double avaluoCatastral;
    private double exenciones;
    private double impuestoPredial;
    private double cuerpoBomberos;
    private double solarNoEdificado;
    private double procesamiento;
    private double descuentos;
    private double intereses;
    private double total;
    private int anioEmision;
    private String emitida;
    private String pagada;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public RecaudacionPredial() {
        predio = new PredioV2();
    }

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
     * @return the avaluoCatastral
     */
    public double getAvaluoCatastral() {
        return avaluoCatastral;
    }

    /**
     * @param avaluoCatastral the avaluoCatastral to set
     */
    public void setAvaluoCatastral(double avaluoCatastral) {
        this.avaluoCatastral = avaluoCatastral;
    }

    /**
     * @return the exenciones
     */
    public double getExenciones() {
        return exenciones;
    }

    /**
     * @param exenciones the exenciones to set
     */
    public void setExenciones(double exenciones) {
        this.exenciones = exenciones;
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
     * @return the cuerpoBomberos
     */
    public double getCuerpoBomberos() {
        return cuerpoBomberos;
    }

    /**
     * @param cuerpoBomberos the cuerpoBomberos to set
     */
    public void setCuerpoBomberos(double cuerpoBomberos) {
        this.cuerpoBomberos = cuerpoBomberos;
    }

    /**
     * @return the solarNoEdificado
     */
    public double getSolarNoEdificado() {
        return solarNoEdificado;
    }

    /**
     * @param solarNoEdificado the solarNoEdificado to set
     */
    public void setSolarNoEdificado(double solarNoEdificado) {
        this.solarNoEdificado = solarNoEdificado;
    }

    /**
     * @return the procesamiento
     */
    public double getProcesamiento() {
        return procesamiento;
    }

    /**
     * @param procesamiento the procesamiento to set
     */
    public void setProcesamiento(double procesamiento) {
        this.procesamiento = procesamiento;
    }

    /**
     * @return the descuentos
     */
    public double getDescuentos() {
        return descuentos;
    }

    /**
     * @param descuentos the descuentos to set
     */
    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    /**
     * @return the intereses
     */
    public double getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(double intereses) {
        this.intereses = intereses;
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

    /**
     * @return the anioEmision
     */
    public int getAnioEmision() {
        return anioEmision;
    }

    /**
     * @param anioEmision the anioEmision to set
     */
    public void setAnioEmision(int anioEmision) {
        this.anioEmision = anioEmision;
    }

    /**
     * @return the emitida
     */
    public String getEmitida() {
        return emitida;
    }

    /**
     * @param emitida the emitida to set
     */
    public void setEmitida(String emitida) {
        this.emitida = emitida;
    }

    /**
     * @return the pagada
     */
    public String getPagada() {
        return pagada;
    }

    /**
     * @param pagada the pagada to set
     */
    public void setPagada(String pagada) {
        this.pagada = pagada;
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
}
