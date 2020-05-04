package auditoria.logica.entidades;

import java.sql.Time;
import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class AccesoSistema {

    private int idAccessoUsuario;
    private Usuario usuario;
    private Timestamp fechaEvento;
    private Time horaIngreso;
    private Time horaSalida;
    private Time duracion;
    private String ipPrivada;
    private String ipPublica;
    private String estadoSession;
    private String observaciones;
    private String nombreEquipo;

    public AccesoSistema() {
        usuario = new Usuario();
    }

    public int getIdAccessoUsuario() {
        return idAccessoUsuario;
    }

    public void setIdAccessoUsuario(int idAccessoUsuario) {
        this.idAccessoUsuario = idAccessoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Timestamp getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Timestamp fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public String getIpPrivada() {
        return ipPrivada;
    }

    public void setIpPrivada(String ipPrivada) {
        this.ipPrivada = ipPrivada;
    }

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getEstadoSession() {
        return estadoSession;
    }

    public void setEstadoSession(String estadoSession) {
        this.estadoSession = estadoSession;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

}
