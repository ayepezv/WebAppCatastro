package auditoria.logica.entidades;

import catastro.cem.entidades.FormaPago;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class AMejoras {

    private int idAudMejoras;
    private int idMejora;
    private String nombre;
    private Date fechaInicio;
    private int anioAvaluo;
    private double costo;
    private int tiempoPago;
    //private FormaPago formaPago;
    private Date fechaInicialPago;
    private String observaciones;
    private String accionRealizada;
    //private Usuario usuarioSistema;    
    private Timestamp fechaEvento;
    private String sessionUsuario;
    private String formaPago;

    public AMejoras() {
      //  formaPago = new FormaPago();
//        usuarioSistema = new Usuario();
    }

    /**
     * @return the idAudMejoras
     */
    public int getIdAudMejoras() {
        return idAudMejoras;
    }

    /**
     * @param idAudMejoras the idAudMejoras to set
     */
    public void setIdAudMejoras(int idAudMejoras) {
        this.idAudMejoras = idAudMejoras;
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
     * @return the tiempoPago
     */
    public int getTiempoPago() {
        return tiempoPago;
    }

    /**
     * @param tiempoPago the tiempoPago to set
     */
    public void setTiempoPago(int tiempoPago) {
        this.tiempoPago = tiempoPago;
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
     * @return the accionRealizada
     */
    public String getAccionRealizada() {
        return accionRealizada;
    }

    /**
     * @param accionRealizada the accionRealizada to set
     */
    public void setAccionRealizada(String accionRealizada) {
        this.accionRealizada = accionRealizada;
    }



    /**
     * @return the fechaEvento
     */
    public Timestamp getFechaEvento() {
        return fechaEvento;
    }

    /**
     * @param fechaEvento the fechaEvento to set
     */
    public void setFechaEvento(Timestamp fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * @return the sessionUsuario
     */
    public String getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(String sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }


}
