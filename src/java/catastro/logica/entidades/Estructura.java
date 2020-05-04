package catastro.logica.entidades;

import java.sql.Timestamp;

public class Estructura {

    private int idEstructura;
    private Piso piso;
    private String cimientos;
    private String cadenas;
    private String columnas;
    private String vigas;
    private String entrepiso;
    private String paredes;
    private String cubierta;
    private String escaleras;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public Estructura() {
        piso = new Piso();
    }

    public int getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(int idEstructura) {
        this.idEstructura = idEstructura;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public String getCimientos() {
        return cimientos;
    }

    public void setCimientos(String cimientos) {
        this.cimientos = cimientos;
    }

    public String getCadenas() {
        return cadenas;
    }

    public void setCadenas(String cadenas) {
        this.cadenas = cadenas;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public String getVigas() {
        return vigas;
    }

    public void setVigas(String vigas) {
        this.vigas = vigas;
    }

    public String getEntrepiso() {
        return entrepiso;
    }

    public void setEntrepiso(String entrepiso) {
        this.entrepiso = entrepiso;
    }

    public String getParedes() {
        return paredes;
    }

    public void setParedes(String paredes) {
        this.paredes = paredes;
    }

    public String getCubierta() {
        return cubierta;
    }

    public void setCubierta(String cubierta) {
        this.cubierta = cubierta;
    }

    public String getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(String escaleras) {
        this.escaleras = escaleras;
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
