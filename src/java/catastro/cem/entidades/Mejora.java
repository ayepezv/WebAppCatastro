package catastro.cem.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class Mejora {

    private int idMejora;
    private String nombre;
    private Date fechaInicio;
    private int anioAvaluo;
    private double costo;
    private int plazo; //tiempo de pago
    private FormaPago formaPago;
    private Date fechaInicialPago;
    private String observaciones;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sesionUsuario;

    public Mejora() {
        formaPago = new FormaPago();
        sesionUsuario = new Usuario();
    }

    /**
     * @return the idMejora
     */
    public int getIdMejora() {
        return idMejora;
    }

    /**
     * @param idMejora the idMejora to set
     */
    public void setIdMejora(int idMejora) {
        this.idMejora = idMejora;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the anioAvaluo
     */
    public int getAnioAvaluo() {
        return anioAvaluo;
    }

    /**
     * @param anioAvaluo the anioAvaluo to set
     */
    public void setAnioAvaluo(int anioAvaluo) {
        this.anioAvaluo = anioAvaluo;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the plazo
     */
    public int getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    /**
     * @return the formaPago
     */
    public FormaPago getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the fechaInicialPago
     */
    public Date getFechaInicialPago() {
        return fechaInicialPago;
    }

    /**
     * @param fechaInicialPago the fechaInicialPago to set
     */
    public void setFechaInicialPago(Date fechaInicialPago) {
        this.fechaInicialPago = fechaInicialPago;
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
     * @return the sesionUsuario
     */
    public Usuario getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(Usuario sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }

}
