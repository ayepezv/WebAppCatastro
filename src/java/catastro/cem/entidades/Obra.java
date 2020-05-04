package catastro.cem.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class Obra {

    private int idObra;
    private String nombreObra;
    private TipoObra tipoObra;
    private boolean excluyente;
    private double valorObra;
    private double valorDirecto;
    private Mejora mejora;
    private double porctMunicipio;
    private double porctBenefDirect;
    private double frenteBenefDirect;
    private double avaluoBenefDirect;
    private double porctBenefIndirect;
    private double frentBenefIndirect;
    private double avaluoBenefIndirect;
    private double areaLoteMedio;
    private double areaConstrucMedia;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;
    private int rangoAfectacion;
    private double valorMetroDirecto;
    private double valorMetroIndirecto;
    private FormaPago formaPago;
    private int plazo;
    private Date fechaInicialPago;
    private String calleAfectacion1;
    private String calleAfectacion2;
    private String calleAfectacion3;
    private String calleAfectacion4;
    private int rangoAfectInd;

    public Obra() {
        formaPago = new FormaPago();
        tipoObra = new TipoObra();
        mejora = new Mejora();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idObra
     */
    public int getIdObra() {
        return idObra;
    }

    /**
     * @param idObra the idObra to set
     */
    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    /**
     * @return the nombreObra
     */
    public String getNombreObra() {
        return nombreObra;
    }

    /**
     * @param nombreObra the nombreObra to set
     */
    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    /**
     * @return the tipoObra
     */
    public TipoObra getTipoObra() {
        return tipoObra;
    }

    /**
     * @param tipoObra the tipoObra to set
     */
    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }

    /**
     * @return the excluyente
     */
    public boolean isExcluyente() {
        return excluyente;
    }

    /**
     * @param excluyente the excluyente to set
     */
    public void setExcluyente(boolean excluyente) {
        this.excluyente = excluyente;
    }

    /**
     * @return the valorObra
     */
    public double getValorObra() {
        return valorObra;
    }

    /**
     * @param valorObra the valorObra to set
     */
    public void setValorObra(double valorObra) {
        this.valorObra = valorObra;
    }

    /**
     * @return the valorDirecto
     */
    public double getValorDirecto() {
        return valorDirecto;
    }

    /**
     * @param valorDirecto the valorDirecto to set
     */
    public void setValorDirecto(double valorDirecto) {
        this.valorDirecto = valorDirecto;
    }

    /**
     * @return the mejora
     */
    public Mejora getMejora() {
        return mejora;
    }

    /**
     * @param mejora the mejora to set
     */
    public void setMejora(Mejora mejora) {
        this.mejora = mejora;
    }

    /**
     * @return the porctMunicipio
     */
    public double getPorctMunicipio() {
        return porctMunicipio;
    }

    /**
     * @param porctMunicipio the porctMunicipio to set
     */
    public void setPorctMunicipio(double porctMunicipio) {
        this.porctMunicipio = porctMunicipio;
    }

    /**
     * @return the frenteBenefDirect
     */
    public double getFrenteBenefDirect() {
        return frenteBenefDirect;
    }

    /**
     * @param frenteBenefDirect the frenteBenefDirect to set
     */
    public void setFrenteBenefDirect(double frenteBenefDirect) {
        this.frenteBenefDirect = frenteBenefDirect;
    }

    /**
     * @return the avaluoBenefDirect
     */
    public double getAvaluoBenefDirect() {
        return avaluoBenefDirect;
    }

    /**
     * @param avaluoBenefDirect the avaluoBenefDirect to set
     */
    public void setAvaluoBenefDirect(double avaluoBenefDirect) {
        this.avaluoBenefDirect = avaluoBenefDirect;
    }

    /**
     * @return the porctBenefIndirect
     */
    public double getPorctBenefIndirect() {
        return porctBenefIndirect;
    }

    /**
     * @param porctBenefIndirect the porctBenefIndirect to set
     */
    public void setPorctBenefIndirect(double porctBenefIndirect) {
        this.porctBenefIndirect = porctBenefIndirect;
    }

    /**
     * @return the frentBenefIndirect
     */
    public double getFrentBenefIndirect() {
        return frentBenefIndirect;
    }

    /**
     * @param frentBenefIndirect the frentBenefIndirect to set
     */
    public void setFrentBenefIndirect(double frentBenefIndirect) {
        this.frentBenefIndirect = frentBenefIndirect;
    }

    /**
     * @return the avaluoBenefIndirect
     */
    public double getAvaluoBenefIndirect() {
        return avaluoBenefIndirect;
    }

    /**
     * @param avaluoBenefIndirect the avaluoBenefIndirect to set
     */
    public void setAvaluoBenefIndirect(double avaluoBenefIndirect) {
        this.avaluoBenefIndirect = avaluoBenefIndirect;
    }

    /**
     * @return the areaLoteMedio
     */
    public double getAreaLoteMedio() {
        return areaLoteMedio;
    }

    /**
     * @param areaLoteMedio the areaLoteMedio to set
     */
    public void setAreaLoteMedio(double areaLoteMedio) {
        this.areaLoteMedio = areaLoteMedio;
    }

    /**
     * @return the areaConstrucMedia
     */
    public double getAreaConstrucMedia() {
        return areaConstrucMedia;
    }

    /**
     * @param areaConstrucMedia the areaConstrucMedia to set
     */
    public void setAreaConstrucMedia(double areaConstrucMedia) {
        this.areaConstrucMedia = areaConstrucMedia;
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
     * @return the porctBenefDirect
     */
    public double getPorctBenefDirect() {
        return porctBenefDirect;
    }

    /**
     * @param porctBenefDirect the porctBenefDirect to set
     */
    public void setPorctBenefDirect(double porctBenefDirect) {
        this.porctBenefDirect = porctBenefDirect;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the rangoAfectacion
     */
    public int getRangoAfectacion() {
        return rangoAfectacion;
    }

    /**
     * @param rangoAfectacion the rangoAfectacion to set
     */
    public void setRangoAfectacion(int rangoAfectacion) {
        this.rangoAfectacion = rangoAfectacion;
    }

    /**
     * @return the valorMetroDirecto
     */
    public double getValorMetroDirecto() {
        return valorMetroDirecto;
    }

    /**
     * @param valorMetroDirecto the valorMetroDirecto to set
     */
    public void setValorMetroDirecto(double valorMetroDirecto) {
        this.valorMetroDirecto = valorMetroDirecto;
    }

    /**
     * @return the valorMetroIndirecto
     */
    public double getValorMetroIndirecto() {
        return valorMetroIndirecto;
    }

    /**
     * @param valorMetroIndirecto the valorMetroIndirecto to set
     */
    public void setValorMetroIndirecto(double valorMetroIndirecto) {
        this.valorMetroIndirecto = valorMetroIndirecto;
    }

    /**
     * @return the formaPago
     */
    public FormaPago getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the plazo
     */
    public int getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    /**
     * @return the fechaInicialPago
     */
    public Date getFechaInicialPago() {
        return fechaInicialPago;
    }

    /**
     * @param fechaInicialPago the fechaInicialPago to set
     */
    public void setFechaInicialPago(Date fechaInicialPago) {
        this.fechaInicialPago = fechaInicialPago;
    }

    /**
     * @return the calleAfectacion1
     */
    public String getCalleAfectacion1() {
        return calleAfectacion1;
    }

    /**
     * @param calleAfectacion1 the calleAfectacion1 to set
     */
    public void setCalleAfectacion1(String calleAfectacion1) {
        this.calleAfectacion1 = calleAfectacion1;
    }

    /**
     * @return the calleAfectacion2
     */
    public String getCalleAfectacion2() {
        return calleAfectacion2;
    }

    /**
     * @param calleAfectacion2 the calleAfectacion2 to set
     */
    public void setCalleAfectacion2(String calleAfectacion2) {
        this.calleAfectacion2 = calleAfectacion2;
    }

    /**
     * @return the calleAfectacion3
     */
    public String getCalleAfectacion3() {
        return calleAfectacion3;
    }

    /**
     * @param calleAfectacion3 the calleAfectacion3 to set
     */
    public void setCalleAfectacion3(String calleAfectacion3) {
        this.calleAfectacion3 = calleAfectacion3;
    }

    /**
     * @return the calleAfectacion4
     */
    public String getCalleAfectacion4() {
        return calleAfectacion4;
    }

    /**
     * @param calleAfectacion4 the calleAfectacion4 to set
     */
    public void setCalleAfectacion4(String calleAfectacion4) {
        this.calleAfectacion4 = calleAfectacion4;
    }

    /**
     * @return the rangoAfectInd
     */
    public int getRangoAfectInd() {
        return rangoAfectInd;
    }

    /**
     * @param rangoAfectInd the rangoAfectInd to set
     */
    public void setRangoAfectInd(int rangoAfectInd) {
        this.rangoAfectInd = rangoAfectInd;
    }

}
