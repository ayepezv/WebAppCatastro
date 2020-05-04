package catastro.logica.entidades;

import java.sql.Timestamp;

public class Bloque2 {

    private int idBloque;
    private PredioV2 predio;
    private String descripcion;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private double areaBloque;
    private String bloque;

    public Bloque2() {
        predio = new PredioV2();
    }

    /**
     * @return the idBloque
     */
    public int getIdBloque() {
        return idBloque;
    }

    /**
     * @param idBloque the idBloque to set
     */
    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
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
     * @return the areaBloque
     */
    public double getAreaBloque() {
        return areaBloque;
    }

    /**
     * @param areaBloque the areaBloque to set
     */
    public void setAreaBloque(double areaBloque) {
        this.areaBloque = areaBloque;
    }

    /**
     * @return the bloque
     */
    public String getBloque() {
        return bloque;
    }

    /**
     * @param bloque the bloque to set
     */
    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

}
