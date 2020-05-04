/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;

/**
 *
 * @author Geovanny Cudco
 */
public class DetalleValorConstruccion {

    private int idValConstruccion;
    private Piso2 idPiso;
    private int anioConstruc;
    private double areaConstrucc;
    private double estadoPiso;
    private double estructCimientos;
    private double estructColumnas;
    private double estructVigas;
    private double estructEntrepisos;
    private double estructParedes;
    private double estructCubierta;
    private double estructEscaleras;
    private double acabPisos;
    private double acabPuertas;
    private double acabVentanas;
    private double acabCubrevent;
    private double acabTumbados;
    private double acabCubierta;
    private double acabBanios;
    private double acabCocina;
    private double acabClosets;
    private double acabRevInterior;
    private double acabRevExt;
    private double fde;
    private double total1;
    private double total2;
    private double total3;
    private PredioV2 idPredio;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;
    private double estructCadenas;
    private double acabPuertasInterior;
    private double acabVidrios;
    private double acabEnlucidos;
    private double acabEnergiaElectrica;
    private double acabSanitarias;
    private double acabEspeciales;
    private double acabContraIncendio;

    public DetalleValorConstruccion() {
        idPiso = new Piso2();
        idPredio = new PredioV2();
    }

    /**
     * @return the idValConstruccion
     */
    public int getIdValConstruccion() {
        return idValConstruccion;
    }

    /**
     * @param idValConstruccion the idValConstruccion to set
     */
    public void setIdValConstruccion(int idValConstruccion) {
        this.idValConstruccion = idValConstruccion;
    }

    /**
     * @return the idPiso
     */
    public Piso2 getIdPiso() {
        return idPiso;
    }

    /**
     * @param idPiso the idPiso to set
     */
    public void setIdPiso(Piso2 idPiso) {
        this.idPiso = idPiso;
    }

    /**
     * @return the anioConstruc
     */
    public int getAnioConstruc() {
        return anioConstruc;
    }

    /**
     * @param anioConstruc the anioConstruc to set
     */
    public void setAnioConstruc(int anioConstruc) {
        this.anioConstruc = anioConstruc;
    }

    /**
     * @return the areaConstrucc
     */
    public double getAreaConstrucc() {
        return areaConstrucc;
    }

    /**
     * @param areaConstrucc the areaConstrucc to set
     */
    public void setAreaConstrucc(double areaConstrucc) {
        this.areaConstrucc = areaConstrucc;
    }

    /**
     * @return the estadoPiso
     */
    public double getEstadoPiso() {
        return estadoPiso;
    }

    /**
     * @param estadoPiso the estadoPiso to set
     */
    public void setEstadoPiso(double estadoPiso) {
        this.estadoPiso = estadoPiso;
    }

    /**
     * @return the estructCimientos
     */
    public double getEstructCimientos() {
        return estructCimientos;
    }

    /**
     * @param estructCimientos the estructCimientos to set
     */
    public void setEstructCimientos(double estructCimientos) {
        this.estructCimientos = estructCimientos;
    }

    /**
     * @return the estructColumnas
     */
    public double getEstructColumnas() {
        return estructColumnas;
    }

    /**
     * @param estructColumnas the estructColumnas to set
     */
    public void setEstructColumnas(double estructColumnas) {
        this.estructColumnas = estructColumnas;
    }

    /**
     * @return the estructVigas
     */
    public double getEstructVigas() {
        return estructVigas;
    }

    /**
     * @param estructVigas the estructVigas to set
     */
    public void setEstructVigas(double estructVigas) {
        this.estructVigas = estructVigas;
    }

    /**
     * @return the estructEntrepisos
     */
    public double getEstructEntrepisos() {
        return estructEntrepisos;
    }

    /**
     * @param estructEntrepisos the estructEntrepisos to set
     */
    public void setEstructEntrepisos(double estructEntrepisos) {
        this.estructEntrepisos = estructEntrepisos;
    }

    /**
     * @return the estructParedes
     */
    public double getEstructParedes() {
        return estructParedes;
    }

    /**
     * @param estructParedes the estructParedes to set
     */
    public void setEstructParedes(double estructParedes) {
        this.estructParedes = estructParedes;
    }

    /**
     * @return the estructCubierta
     */
    public double getEstructCubierta() {
        return estructCubierta;
    }

    /**
     * @param estructCubierta the estructCubierta to set
     */
    public void setEstructCubierta(double estructCubierta) {
        this.estructCubierta = estructCubierta;
    }

    /**
     * @return the estructEscaleras
     */
    public double getEstructEscaleras() {
        return estructEscaleras;
    }

    /**
     * @param estructEscaleras the estructEscaleras to set
     */
    public void setEstructEscaleras(double estructEscaleras) {
        this.estructEscaleras = estructEscaleras;
    }

    /**
     * @return the acabPisos
     */
    public double getAcabPisos() {
        return acabPisos;
    }

    /**
     * @param acabPisos the acabPisos to set
     */
    public void setAcabPisos(double acabPisos) {
        this.acabPisos = acabPisos;
    }

    /**
     * @return the acabPuertas
     */
    public double getAcabPuertas() {
        return acabPuertas;
    }

    /**
     * @param acabPuertas the acabPuertas to set
     */
    public void setAcabPuertas(double acabPuertas) {
        this.acabPuertas = acabPuertas;
    }

    /**
     * @return the acabVentanas
     */
    public double getAcabVentanas() {
        return acabVentanas;
    }

    /**
     * @param acabVentanas the acabVentanas to set
     */
    public void setAcabVentanas(double acabVentanas) {
        this.acabVentanas = acabVentanas;
    }

    /**
     * @return the acabCubrevent
     */
    public double getAcabCubrevent() {
        return acabCubrevent;
    }

    /**
     * @param acabCubrevent the acabCubrevent to set
     */
    public void setAcabCubrevent(double acabCubrevent) {
        this.acabCubrevent = acabCubrevent;
    }

    /**
     * @return the acabTumbados
     */
    public double getAcabTumbados() {
        return acabTumbados;
    }

    /**
     * @param acabTumbados the acabTumbados to set
     */
    public void setAcabTumbados(double acabTumbados) {
        this.acabTumbados = acabTumbados;
    }

    /**
     * @return the acabCubierta
     */
    public double getAcabCubierta() {
        return acabCubierta;
    }

    /**
     * @param acabCubierta the acabCubierta to set
     */
    public void setAcabCubierta(double acabCubierta) {
        this.acabCubierta = acabCubierta;
    }

    /**
     * @return the acabBanios
     */
    public double getAcabBanios() {
        return acabBanios;
    }

    /**
     * @param acabBanios the acabBanios to set
     */
    public void setAcabBanios(double acabBanios) {
        this.acabBanios = acabBanios;
    }

    /**
     * @return the acabCocina
     */
    public double getAcabCocina() {
        return acabCocina;
    }

    /**
     * @param acabCocina the acabCocina to set
     */
    public void setAcabCocina(double acabCocina) {
        this.acabCocina = acabCocina;
    }

    /**
     * @return the acabClosets
     */
    public double getAcabClosets() {
        return acabClosets;
    }

    /**
     * @param acabClosets the acabClosets to set
     */
    public void setAcabClosets(double acabClosets) {
        this.acabClosets = acabClosets;
    }

    /**
     * @return the acabRevInterior
     */
    public double getAcabRevInterior() {
        return acabRevInterior;
    }

    /**
     * @param acabRevInterior the acabRevInterior to set
     */
    public void setAcabRevInterior(double acabRevInterior) {
        this.acabRevInterior = acabRevInterior;
    }

    /**
     * @return the acabRevExt
     */
    public double getAcabRevExt() {
        return acabRevExt;
    }

    /**
     * @param acabRevExt the acabRevExt to set
     */
    public void setAcabRevExt(double acabRevExt) {
        this.acabRevExt = acabRevExt;
    }

    /**
     * @return the fde
     */
    public double getFde() {
        return fde;
    }

    /**
     * @param fde the fde to set
     */
    public void setFde(double fde) {
        this.fde = fde;
    }

    /**
     * @return the total1
     */
    public double getTotal1() {
        return total1;
    }

    /**
     * @param total1 the total1 to set
     */
    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    /**
     * @return the total2
     */
    public double getTotal2() {
        return total2;
    }

    /**
     * @param total2 the total2 to set
     */
    public void setTotal2(double total2) {
        this.total2 = total2;
    }

    /**
     * @return the total3
     */
    public double getTotal3() {
        return total3;
    }

    /**
     * @param total3 the total3 to set
     */
    public void setTotal3(double total3) {
        this.total3 = total3;
    }

    /**
     * @return the idPredio
     */
    public PredioV2 getIdPredio() {
        return idPredio;
    }

    /**
     * @param idPredio the idPredio to set
     */
    public void setIdPredio(PredioV2 idPredio) {
        this.idPredio = idPredio;
    }

    /**
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
     * @return the estructCadenas
     */
    public double getEstructCadenas() {
        return estructCadenas;
    }

    /**
     * @param estructCadenas the estructCadenas to set
     */
    public void setEstructCadenas(double estructCadenas) {
        this.estructCadenas = estructCadenas;
    }

    /**
     * @return the acabPuertasInterior
     */
    public double getAcabPuertasInterior() {
        return acabPuertasInterior;
    }

    /**
     * @param acabPuertasInterior the acabPuertasInterior to set
     */
    public void setAcabPuertasInterior(double acabPuertasInterior) {
        this.acabPuertasInterior = acabPuertasInterior;
    }

    /**
     * @return the acabVidrios
     */
    public double getAcabVidrios() {
        return acabVidrios;
    }

    /**
     * @param acabVidrios the acabVidrios to set
     */
    public void setAcabVidrios(double acabVidrios) {
        this.acabVidrios = acabVidrios;
    }

    /**
     * @return the acabEnlucidos
     */
    public double getAcabEnlucidos() {
        return acabEnlucidos;
    }

    /**
     * @param acabEnlucidos the acabEnlucidos to set
     */
    public void setAcabEnlucidos(double acabEnlucidos) {
        this.acabEnlucidos = acabEnlucidos;
    }

    /**
     * @return the acabEnergiaElectrica
     */
    public double getAcabEnergiaElectrica() {
        return acabEnergiaElectrica;
    }

    /**
     * @param acabEnergiaElectrica the acabEnergiaElectrica to set
     */
    public void setAcabEnergiaElectrica(double acabEnergiaElectrica) {
        this.acabEnergiaElectrica = acabEnergiaElectrica;
    }

    /**
     * @return the acabSanitarias
     */
    public double getAcabSanitarias() {
        return acabSanitarias;
    }

    /**
     * @param acabSanitarias the acabSanitarias to set
     */
    public void setAcabSanitarias(double acabSanitarias) {
        this.acabSanitarias = acabSanitarias;
    }

    /**
     * @return the acabEspeciales
     */
    public double getAcabEspeciales() {
        return acabEspeciales;
    }

    /**
     * @param acabEspeciales the acabEspeciales to set
     */
    public void setAcabEspeciales(double acabEspeciales) {
        this.acabEspeciales = acabEspeciales;
    }

    /**
     * @return the acabContraIncendio
     */
    public double getAcabContraIncendio() {
        return acabContraIncendio;
    }

    /**
     * @param acabContraIncendio the acabContraIncendio to set
     */
    public void setAcabContraIncendio(double acabContraIncendio) {
        this.acabContraIncendio = acabContraIncendio;
    }

   

}
