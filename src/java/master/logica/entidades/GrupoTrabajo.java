package master.logica.entidades;

import java.sql.Timestamp;

public class GrupoTrabajo {

    private int idGrupoTrabajo;
    private String grupo;
    private String descripcion;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario SessionUsuario;

    public GrupoTrabajo() {
        SessionUsuario = new Usuario();
    }

    /**
     * @return the idGrupoTrabajo
     */
    public int getIdGrupoTrabajo() {
        return idGrupoTrabajo;
    }

    /**
     * @param idGrupoTrabajo the idGrupoTrabajo to set
     */
    public void setIdGrupoTrabajo(int idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
     * @return the SessionUsuario
     */
    public Usuario getSessionUsuario() {
        return SessionUsuario;
    }

    /**
     * @param SessionUsuario the SessionUsuario to set
     */
    public void setSessionUsuario(Usuario SessionUsuario) {
        this.SessionUsuario = SessionUsuario;
    }
    
    
}
