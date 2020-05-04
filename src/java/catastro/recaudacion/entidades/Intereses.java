package catastro.recaudacion.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Intereses {

    private int idInteres;
    private int anio;
    private String mes;
    private String trimestre;
    private int numTrimestre;
    private double interesMensual;
    private double interesMostrar;
    private double interesAcumAnual;
    private double interesTotalAcum;
    private double InteresPorMora;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;

    public Intereses(String trimestre, int numTrimestre) {
        this.trimestre = trimestre;
        this.numTrimestre = numTrimestre;
        sessionUsuario = new Usuario();
    }

    public Intereses() {
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idInteres
     */
    public int getIdInteres() {
        return idInteres;
    }

    /**
     * @param idInteres the idInteres to set
     */
    public void setIdInteres(int idInteres) {
        this.idInteres = idInteres;
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
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the trimestre
     */
    public String getTrimestre() {
        return trimestre;
    }

    /**
     * @param trimestre the trimestre to set
     */
    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * @return the numTrimestre
     */
    public int getNumTrimestre() {
        return numTrimestre;
    }

    /**
     * @param numTrimestre the numTrimestre to set
     */
    public void setNumTrimestre(int numTrimestre) {
        this.numTrimestre = numTrimestre;
    }

    /**
     * @return the interesMensual
     */
    public double getInteresMensual() {
        return interesMensual;
    }

    /**
     * @param interesMensual the interesMensual to set
     */
    public void setInteresMensual(double interesMensual) {
        this.interesMensual = interesMensual;
    }

    /**
     * @return the interesAcumAnual
     */
    public double getInteresAcumAnual() {
        return interesAcumAnual;
    }

    /**
     * @param interesAcumAnual the interesAcumAnual to set
     */
    public void setInteresAcumAnual(double interesAcumAnual) {
        this.interesAcumAnual = interesAcumAnual;
    }

    /**
     * @return the interesTotalAcum
     */
    public double getInteresTotalAcum() {
        return interesTotalAcum;
    }

    /**
     * @param interesTotalAcum the interesTotalAcum to set
     */
    public void setInteresTotalAcum(double interesTotalAcum) {
        this.interesTotalAcum = interesTotalAcum;
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
     * @return the InteresPorMora
     */
    public double getInteresPorMora() {
        return InteresPorMora;
    }

    /**
     * @param InteresPorMora the InteresPorMora to set
     */
    public void setInteresPorMora(double InteresPorMora) {
        this.InteresPorMora = InteresPorMora;
    }

    /**
     * @return the interesMostrar
     */
    public double getInteresMostrar() {
        return interesMostrar;
    }

    /**
     * @param interesMostrar the interesMostrar to set
     */
    public void setInteresMostrar(double interesMostrar) {
        this.interesMostrar = interesMostrar;
    }

}
