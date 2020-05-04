package catastro.logica.entidades;

import java.sql.Timestamp;

public class Instalaciones {

    private int idInstalaciones;
    private Piso piso;
    private String energElectr;
    private String sanitarias;
    private String especiales;
    private String sistemContraIncend;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public Instalaciones() {
        piso = new Piso();
    }

    public int getIdInstalaciones() {
        return idInstalaciones;
    }

    public void setIdInstalaciones(int idInstalaciones) {
        this.idInstalaciones = idInstalaciones;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public String getEnergElectr() {
        return energElectr;
    }

    public void setEnergElectr(String energElectr) {
        this.energElectr = energElectr;
    }

    public String getSanitarias() {
        return sanitarias;
    }

    public void setSanitarias(String sanitarias) {
        this.sanitarias = sanitarias;
    }

    public String getEspeciales() {
        return especiales;
    }

    public void setEspeciales(String especiales) {
        this.especiales = especiales;
    }

    public String getSistemContraIncend() {
        return sistemContraIncend;
    }

    public void setSistemContraIncend(String sistemContraIncend) {
        this.sistemContraIncend = sistemContraIncend;
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
