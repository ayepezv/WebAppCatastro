package catastro.recaudacion.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class TitulosHistoricos {

    private int idTituloHistorico;
    private Titulo titulo;
    private String razonBaja;
    private String observaciones;
    private String pathRespaldo;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;

    public TitulosHistoricos() {
        titulo = new Titulo();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idTituloHistorico
     */
    public int getIdTituloHistorico() {
        return idTituloHistorico;
    }

    /**
     * @param idTituloHistorico the idTituloHistorico to set
     */
    public void setIdTituloHistorico(int idTituloHistorico) {
        this.idTituloHistorico = idTituloHistorico;
    }

    /**
     * @return the titulo
     */
    public Titulo getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the razonBaja
     */
    public String getRazonBaja() {
        return razonBaja;
    }

    /**
     * @param razonBaja the razonBaja to set
     */
    public void setRazonBaja(String razonBaja) {
        this.razonBaja = razonBaja;
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
     * @return the pathRespaldo
     */
    public String getPathRespaldo() {
        return pathRespaldo;
    }

    /**
     * @param pathRespaldo the pathRespaldo to set
     */
    public void setPathRespaldo(String pathRespaldo) {
        this.pathRespaldo = pathRespaldo;
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

}
