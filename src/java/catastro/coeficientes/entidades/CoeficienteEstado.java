package catastro.coeficientes.entidades;

import java.sql.Timestamp;

public class CoeficienteEstado {

    private int idCoeficiente;
    private String coeficiente;
    private int codigo;
    private double valorCoeficiente;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String descripcion;

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
     * @return the idCoeficiente
     */
    public int getIdCoeficiente() {
        return idCoeficiente;
    }

    /**
     * @param idCoeficiente the idCoeficiente to set
     */
    public void setIdCoeficiente(int idCoeficiente) {
        this.idCoeficiente = idCoeficiente;
    }

    /**
     * @return the coeficiente
     */
    public String getCoeficiente() {
        return coeficiente;
    }

    /**
     * @param coeficiente the coeficiente to set
     */
    public void setCoeficiente(String coeficiente) {
        this.coeficiente = coeficiente;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the valorCoeficiente
     */
    public double getValorCoeficiente() {
        return valorCoeficiente;
    }

    /**
     * @param valorCoeficiente the valorCoeficiente to set
     */
    public void setValorCoeficiente(double valorCoeficiente) {
        this.valorCoeficiente = valorCoeficiente;
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
