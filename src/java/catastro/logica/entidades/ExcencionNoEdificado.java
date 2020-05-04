
package catastro.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

/**
 *
 * @author gcudcop
 */
public class ExcencionNoEdificado {
    
    private int predioNoEdificado;
    private PredioV2 predio;
    private String razon;
    private int permanente;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private String estadoLogico;
    private int activado;
    private String reclamo;
    private String responsable;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private Timestamp fechaActualizacion;
    private Usuario sessionUsuario;
    private String observaciones;
    private Double avaluo_Predio;
    private Double descuentoNoEdificado;
    private Double avaluoImponible;
    private String temporalidad;
    
    
    
    public ExcencionNoEdificado() {
        predio = new PredioV2();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the predioNoEdificado
     */
    public int getPredioNoEdificado() {
        return predioNoEdificado;
    }

    /**
     * @param predioNoEdificado the predioNoEdificado to set
     */
    public void setPredioNoEdificado(int predioNoEdificado) {
        this.predioNoEdificado = predioNoEdificado;
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
     * @return the razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * @param razon the razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * @return the permanente
     */
    public int getPermanente() {
        return permanente;
    }

    /**
     * @param permanente the permanente to set
     */
    public void setPermanente(int permanente) {
        this.permanente = permanente;
    }

    /**
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Timestamp getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
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
     * @return the activado
     */
    public int getActivado() {
        return activado;
    }

    /**
     * @param activado the activado to set
     */
    public void setActivado(int activado) {
        this.activado = activado;
    }

    /**
     * @return the reclamo
     */
    public String getReclamo() {
        return reclamo;
    }

    /**
     * @param reclamo the reclamo to set
     */
    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
     * @return the avaluo_Predio
     */
    public Double getAvaluo_Predio() {
        return avaluo_Predio;
    }

    /**
     * @param avaluo_Predio the avaluo_Predio to set
     */
    public void setAvaluo_Predio(Double avaluo_Predio) {
        this.avaluo_Predio = avaluo_Predio;
    }

    /**
     * @return the descuentoNoEdificado
     */
    public Double getDescuentoNoEdificado() {
        return descuentoNoEdificado;
    }

    /**
     * @param descuentoNoEdificado the descuentoNoEdificado to set
     */
    public void setDescuentoNoEdificado(Double descuentoNoEdificado) {
        this.descuentoNoEdificado = descuentoNoEdificado;
    }

    /**
     * @return the avaluoImponible
     */
    public Double getAvaluoImponible() {
        return avaluoImponible;
    }

    /**
     * @param avaluoImponible the avaluoImponible to set
     */
    public void setAvaluoImponible(Double avaluoImponible) {
        this.avaluoImponible = avaluoImponible;
    }

    /**
     * @return the temporalidad
     */
    public String getTemporalidad() {
        return temporalidad;
    }

    /**
     * @param temporalidad the temporalidad to set
     */
    public void setTemporalidad(String temporalidad) {
        this.temporalidad = temporalidad;
    }
    
}
