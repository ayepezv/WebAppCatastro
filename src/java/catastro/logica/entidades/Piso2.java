package catastro.logica.entidades;

import java.sql.Timestamp;

public class Piso2 {

    private int idPiso;
    private Bloque2 bloque;
    private int anioConstruccion;
    private double areaConstruccion;
    private String estadoPiso;
    private String estructCimientos;
    private String estructColumnas;
    private String estructVigas;
    private String estructEntrepisos;
    private String estructParedes;
    private String estructCubierta;
    private String estructEscaleras;
    private String acabPisos;
    private String acabPuertas;
    private String acabVentanas;
    private String acabCubrevent;
    private String acabTumbados;
    private String acabCubierta;
    private String acabBanios;
    private String acabCocina;
    private String acabClosets;
    private String acabRevInterior;
    private String acabRevExt;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String piso;
    private String descripcion;

    public Piso2() {
        bloque = new Bloque2();
    }

    /**
     * @return the idPiso
     */
    public int getIdPiso() {
        return idPiso;
    }

    /**
     * @param idPiso the idPiso to set
     */
    public void setIdPiso(int idPiso) {
        this.idPiso = idPiso;
    }

    /**
     * @return the bloque
     */
    public Bloque2 getBloque() {
        return bloque;
    }

    /**
     * @param bloque the bloque to set
     */
    public void setBloque(Bloque2 bloque) {
        this.bloque = bloque;
    }

    /**
     * @return the anioConstruccion
     */
    public int getAnioConstruccion() {
        return anioConstruccion;
    }

    /**
     * @param anioConstruccion the anioConstruccion to set
     */
    public void setAnioConstruccion(int anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }

    /**
     * @return the areaConstruccion
     */
    public double getAreaConstruccion() {
        return areaConstruccion;
    }

    /**
     * @param areaConstruccion the areaConstruccion to set
     */
    public void setAreaConstruccion(double areaConstruccion) {
        this.areaConstruccion = areaConstruccion;
    }

    /**
     * @return the estadoPiso
     */
    public String getEstadoPiso() {
        return estadoPiso;
    }

    /**
     * @param estadoPiso the estadoPiso to set
     */
    public void setEstadoPiso(String estadoPiso) {
        this.estadoPiso = estadoPiso;
    }

    /**
     * @return the estructCimientos
     */
    public String getEstructCimientos() {
        return estructCimientos;
    }

    /**
     * @param estructCimientos the estructCimientos to set
     */
    public void setEstructCimientos(String estructCimientos) {
        this.estructCimientos = estructCimientos;
    }

    /**
     * @return the estructColumnas
     */
    public String getEstructColumnas() {
        return estructColumnas;
    }

    /**
     * @param estructColumnas the estructColumnas to set
     */
    public void setEstructColumnas(String estructColumnas) {
        this.estructColumnas = estructColumnas;
    }

    /**
     * @return the estructVigas
     */
    public String getEstructVigas() {
        return estructVigas;
    }

    /**
     * @param estructVigas the estructVigas to set
     */
    public void setEstructVigas(String estructVigas) {
        this.estructVigas = estructVigas;
    }

    /**
     * @return the estructEntrepisos
     */
    public String getEstructEntrepisos() {
        return estructEntrepisos;
    }

    /**
     * @param estructEntrepisos the estructEntrepisos to set
     */
    public void setEstructEntrepisos(String estructEntrepisos) {
        this.estructEntrepisos = estructEntrepisos;
    }

    /**
     * @return the estructParedes
     */
    public String getEstructParedes() {
        return estructParedes;
    }

    /**
     * @param estructParedes the estructParedes to set
     */
    public void setEstructParedes(String estructParedes) {
        this.estructParedes = estructParedes;
    }

    /**
     * @return the estructCubierta
     */
    public String getEstructCubierta() {
        return estructCubierta;
    }

    /**
     * @param estructCubierta the estructCubierta to set
     */
    public void setEstructCubierta(String estructCubierta) {
        this.estructCubierta = estructCubierta;
    }

    /**
     * @return the estructEscaleras
     */
    public String getEstructEscaleras() {
        return estructEscaleras;
    }

    /**
     * @param estructEscaleras the estructEscaleras to set
     */
    public void setEstructEscaleras(String estructEscaleras) {
        this.estructEscaleras = estructEscaleras;
    }

    /**
     * @return the acabPisos
     */
    public String getAcabPisos() {
        return acabPisos;
    }

    /**
     * @param acabPisos the acabPisos to set
     */
    public void setAcabPisos(String acabPisos) {
        this.acabPisos = acabPisos;
    }

    /**
     * @return the acabPuertas
     */
    public String getAcabPuertas() {
        return acabPuertas;
    }

    /**
     * @param acabPuertas the acabPuertas to set
     */
    public void setAcabPuertas(String acabPuertas) {
        this.acabPuertas = acabPuertas;
    }

    /**
     * @return the acabVentanas
     */
    public String getAcabVentanas() {
        return acabVentanas;
    }

    /**
     * @param acabVentanas the acabVentanas to set
     */
    public void setAcabVentanas(String acabVentanas) {
        this.acabVentanas = acabVentanas;
    }

    /**
     * @return the acabCubrevent
     */
    public String getAcabCubrevent() {
        return acabCubrevent;
    }

    /**
     * @param acabCubrevent the acabCubrevent to set
     */
    public void setAcabCubrevent(String acabCubrevent) {
        this.acabCubrevent = acabCubrevent;
    }

    /**
     * @return the acabTumbados
     */
    public String getAcabTumbados() {
        return acabTumbados;
    }

    /**
     * @param acabTumbados the acabTumbados to set
     */
    public void setAcabTumbados(String acabTumbados) {
        this.acabTumbados = acabTumbados;
    }

    /**
     * @return the acabCubierta
     */
    public String getAcabCubierta() {
        return acabCubierta;
    }

    /**
     * @param acabCubierta the acabCubierta to set
     */
    public void setAcabCubierta(String acabCubierta) {
        this.acabCubierta = acabCubierta;
    }

    /**
     * @return the acabBanios
     */
    public String getAcabBanios() {
        return acabBanios;
    }

    /**
     * @param acabBanios the acabBanios to set
     */
    public void setAcabBanios(String acabBanios) {
        this.acabBanios = acabBanios;
    }

    /**
     * @return the acabCocina
     */
    public String getAcabCocina() {
        return acabCocina;
    }

    /**
     * @param acabCocina the acabCocina to set
     */
    public void setAcabCocina(String acabCocina) {
        this.acabCocina = acabCocina;
    }

    /**
     * @return the acabClosets
     */
    public String getAcabClosets() {
        return acabClosets;
    }

    /**
     * @param acabClosets the acabClosets to set
     */
    public void setAcabClosets(String acabClosets) {
        this.acabClosets = acabClosets;
    }

    /**
     * @return the acabRevInterior
     */
    public String getAcabRevInterior() {
        return acabRevInterior;
    }

    /**
     * @param acabRevInterior the acabRevInterior to set
     */
    public void setAcabRevInterior(String acabRevInterior) {
        this.acabRevInterior = acabRevInterior;
    }

    /**
     * @return the acabRevExt
     */
    public String getAcabRevExt() {
        return acabRevExt;
    }

    /**
     * @param acabRevExt the acabRevExt to set
     */
    public void setAcabRevExt(String acabRevExt) {
        this.acabRevExt = acabRevExt;
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
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
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

}
