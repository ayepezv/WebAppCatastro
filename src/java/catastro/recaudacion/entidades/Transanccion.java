package catastro.recaudacion.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Transanccion {

    private int idTransaccion;
    private int numTransaccion;
    private Timestamp fechaEmision;
    private String recaudacion;
    private double valorTransaccion;
    private String estadoTransacion;
    private Usuario cliente;
    private Usuario recaudador;
    private Timestamp fechaActualizacion;
    private String observaciones;
    private FormaRecaudacion formaRecaudacion;

    public Transanccion() {
        cliente = new Usuario();
        recaudador = new Usuario();
        formaRecaudacion = new FormaRecaudacion();
    }

    /**
     * @return the idTransaccion
     */
    public int getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * @param idTransaccion the idTransaccion to set
     */
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * @return the numTransaccion
     */
    public int getNumTransaccion() {
        return numTransaccion;
    }

    /**
     * @param numTransaccion the numTransaccion to set
     */
    public void setNumTransaccion(int numTransaccion) {
        this.numTransaccion = numTransaccion;
    }

    /**
     * @return the fechaEmision
     */
    public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the recaudacion
     */
    public String getRecaudacion() {
        return recaudacion;
    }

    /**
     * @param recaudacion the recaudacion to set
     */
    public void setRecaudacion(String recaudacion) {
        this.recaudacion = recaudacion;
    }

    /**
     * @return the valorTransaccion
     */
    public double getValorTransaccion() {
        return valorTransaccion;
    }

    /**
     * @param valorTransaccion the valorTransaccion to set
     */
    public void setValorTransaccion(double valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    /**
     * @return the estadoTransacion
     */
    public String getEstadoTransacion() {
        return estadoTransacion;
    }

    /**
     * @param estadoTransacion the estadoTransacion to set
     */
    public void setEstadoTransacion(String estadoTransacion) {
        this.estadoTransacion = estadoTransacion;
    }

    /**
     * @return the cliente
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the recaudador
     */
    public Usuario getRecaudador() {
        return recaudador;
    }

    /**
     * @param recaudador the recaudador to set
     */
    public void setRecaudador(Usuario recaudador) {
        this.recaudador = recaudador;
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
     * @return the formaRecaudacion
     */
    public FormaRecaudacion getFormaRecaudacion() {
        return formaRecaudacion;
    }

    /**
     * @param formaRecaudacion the formaRecaudacion to set
     */
    public void setFormaRecaudacion(FormaRecaudacion formaRecaudacion) {
        this.formaRecaudacion = formaRecaudacion;
    }

}
