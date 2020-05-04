package master.logica.entidades;

import java.sql.Timestamp;

public class GrupoTrabajoRol {

    private GrupoTrabajo grupo;
    private Rol rol;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private Usuario SessionUsuario;

    public GrupoTrabajoRol() {
        grupo = new GrupoTrabajo();
        rol = new Rol();
    }

    /**
     * @return the grupo
     */
    public GrupoTrabajo getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoTrabajo grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
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
