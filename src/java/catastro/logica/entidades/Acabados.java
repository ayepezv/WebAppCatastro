package catastro.logica.entidades;

import java.sql.Timestamp;

public class Acabados {

    private int idAcabado;
    private Piso piso;
    private String pisos;
    private String puertasExteriores;
    private String puertasInteriores;
    private String ventanas;
    private String vidrios;
    private String protecVentanas;
    private String enlucidos;
    private String tumbados;
    private String cubiertas;
    private String piezasSanitarias;
    private String cocina;
    private String closets;
    private String pintura;
    private String fachada;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public Acabados() {
        piso = new Piso();

    }

    public int getIdAcabado() {
        return idAcabado;
    }

    public void setIdAcabado(int idAcabado) {
        this.idAcabado = idAcabado;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public String getPisos() {
        return pisos;
    }

    public void setPisos(String pisos) {
        this.pisos = pisos;
    }

    public String getPuertasExteriores() {
        return puertasExteriores;
    }

    public void setPuertasExteriores(String puertasExteriores) {
        this.puertasExteriores = puertasExteriores;
    }

    public String getPuertasInteriores() {
        return puertasInteriores;
    }

    public void setPuertasInteriores(String puertasInteriores) {
        this.puertasInteriores = puertasInteriores;
    }

    public String getVentanas() {
        return ventanas;
    }

    public void setVentanas(String ventanas) {
        this.ventanas = ventanas;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getProtecVentanas() {
        return protecVentanas;
    }

    public void setProtecVentanas(String protecVentanas) {
        this.protecVentanas = protecVentanas;
    }

    public String getEnlucidos() {
        return enlucidos;
    }

    public void setEnlucidos(String enlucidos) {
        this.enlucidos = enlucidos;
    }

    public String getTumbados() {
        return tumbados;
    }

    public void setTumbados(String tumbados) {
        this.tumbados = tumbados;
    }

    public String getCubiertas() {
        return cubiertas;
    }

    public void setCubiertas(String cubiertas) {
        this.cubiertas = cubiertas;
    }

    public String getPiezasSanitarias() {
        return piezasSanitarias;
    }

    public void setPiezasSanitarias(String piezasSanitarias) {
        this.piezasSanitarias = piezasSanitarias;
    }

    public String getCocina() {
        return cocina;
    }

    public void setCocina(String cocina) {
        this.cocina = cocina;
    }

    public String getClosets() {
        return closets;
    }

    public void setClosets(String closets) {
        this.closets = closets;
    }

    public String getPintura() {
        return pintura;
    }

    public void setPintura(String pintura) {
        this.pintura = pintura;
    }

    public String getFachada() {
        return fachada;
    }

    public void setFachada(String fachada) {
        this.fachada = fachada;
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
