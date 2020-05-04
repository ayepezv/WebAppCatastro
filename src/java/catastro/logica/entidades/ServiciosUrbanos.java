package catastro.logica.entidades;

import java.sql.Timestamp;

public class ServiciosUrbanos {

    private int idServcUrbanos;
    private Predio predio;
    private String medAguaPot;
    private String medElectric;
    private String recolBasura;
    private String aseoPublico;
    private String transpUrbano;
    private String telefono;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public ServiciosUrbanos() {
        predio = new Predio();
    }

    public int getIdServcUrbanos() {
        return idServcUrbanos;
    }

    public void setIdServcUrbanos(int idServcUrbanos) {
        this.idServcUrbanos = idServcUrbanos;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public String getMedAguaPot() {
        return medAguaPot;
    }

    public void setMedAguaPot(String medAguaPot) {
        this.medAguaPot = medAguaPot;
    }

    public String getMedElectric() {
        return medElectric;
    }

    public void setMedElectric(String medElectric) {
        this.medElectric = medElectric;
    }

    public String getRecolBasura() {
        return recolBasura;
    }

    public void setRecolBasura(String recolBasura) {
        this.recolBasura = recolBasura;
    }

    public String getAseoPublico() {
        return aseoPublico;
    }

    public void setAseoPublico(String aseoPublico) {
        this.aseoPublico = aseoPublico;
    }

    public String getTranspUrbano() {
        return transpUrbano;
    }

    public void setTranspUrbano(String transpUrbano) {
        this.transpUrbano = transpUrbano;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
