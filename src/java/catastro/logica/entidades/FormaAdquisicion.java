package catastro.logica.entidades;

import java.sql.Timestamp;

public class FormaAdquisicion {

    private int idFormaAdq;
    private String formaAdq;
    private String descripcion;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public int getIdFormaAdq() {
        return idFormaAdq;
    }

    public void setIdFormaAdq(int idFormaAdq) {
        this.idFormaAdq = idFormaAdq;
    }

    public String getFormaAdq() {
        return formaAdq;
    }

    public void setFormaAdq(String formaAdq) {
        this.formaAdq = formaAdq;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
