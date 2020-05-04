package auditoria.logica.entidades;

import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.TipoObra;
import java.sql.Time;
import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class AObras {

    private int idAudObras;
    private int idObra;
    private String nombreObra;
    private TipoObra tipoObra;
    private boolean excluyente;
    private double valorObra;
    private double valorDirecto;
    private Mejora mejora;
    private double porcentajeMunicipio;
    private double porcentajeBenefDirecto;
    private double frenteBenefDirecto;
    private double avalBenefDirecto;
    private double porcentajeBenefIndirecto;
    private double frenteBenefIndirecto;
    private double avalBenefIndirecto;
    private double areaLoteMedio;
    private double areaConstrucMedia;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario usuarioSistema;
    private Timestamp fechaCreacion;
    private Time horaEvento;
    private String accionEjecutada;

    public AObras() {
        tipoObra = new TipoObra();
        mejora = new Mejora();
        usuarioSistema = new Usuario();
    }

    /**
     * @return the idAudObras
     */
    public int getIdAudObras() {
        return idAudObras;
    }

    /**
     * @param idAudObras the idAudObras to set
     */
    public void setIdAudObras(int idAudObras) {
        this.idAudObras = idAudObras;
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
     * @return the porcentajeMunicipio
     */
    public double getPorcentajeMunicipio() {
        return porcentajeMunicipio;
    }

    /**
     * @param porcentajeMunicipio the porcentajeMunicipio to set
     */
    public void setPorcentajeMunicipio(double porcentajeMunicipio) {
        this.porcentajeMunicipio = porcentajeMunicipio;
    }

    /**
     * @return the porcentajeBenefDirecto
     */
    public double getPorcentajeBenefDirecto() {
        return porcentajeBenefDirecto;
    }

    /**
     * @param porcentajeBenefDirecto the porcentajeBenefDirecto to set
     */
    public void setPorcentajeBenefDirecto(double porcentajeBenefDirecto) {
        this.porcentajeBenefDirecto = porcentajeBenefDirecto;
    }

    /**
     * @return the frenteBenefDirecto
     */
    public double getFrenteBenefDirecto() {
        return frenteBenefDirecto;
    }

    /**
     * @param frenteBenefDirecto the frenteBenefDirecto to set
     */
    public void setFrenteBenefDirecto(double frenteBenefDirecto) {
        this.frenteBenefDirecto = frenteBenefDirecto;
    }

    /**
     * @return the avalBenefDirecto
     */
    public double getAvalBenefDirecto() {
        return avalBenefDirecto;
    }

    /**
     * @param avalBenefDirecto the avalBenefDirecto to set
     */
    public void setAvalBenefDirecto(double avalBenefDirecto) {
        this.avalBenefDirecto = avalBenefDirecto;
    }

    /**
     * @return the porcentajeBenefIndirecto
     */
    public double getPorcentajeBenefIndirecto() {
        return porcentajeBenefIndirecto;
    }

    /**
     * @param porcentajeBenefIndirecto the porcentajeBenefIndirecto to set
     */
    public void setPorcentajeBenefIndirecto(double porcentajeBenefIndirecto) {
        this.porcentajeBenefIndirecto = porcentajeBenefIndirecto;
    }

    /**
     * @return the frenteBenefIndirecto
     */
    public double getFrenteBenefIndirecto() {
        return frenteBenefIndirecto;
    }

    /**
     * @param frenteBenefIndirecto the frenteBenefIndirecto to set
     */
    public void setFrenteBenefIndirecto(double frenteBenefIndirecto) {
        this.frenteBenefIndirecto = frenteBenefIndirecto;
    }

    /**
     * @return the avalBenefIndirecto
     */
    public double getAvalBenefIndirecto() {
        return avalBenefIndirecto;
    }

    /**
     * @param avalBenefIndirecto the avalBenefIndirecto to set
     */
    public void setAvalBenefIndirecto(double avalBenefIndirecto) {
        this.avalBenefIndirecto = avalBenefIndirecto;
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
     * @return the usuarioSistema
     */
    public Usuario getUsuarioSistema() {
        return usuarioSistema;
    }

    /**
     * @param usuarioSistema the usuarioSistema to set
     */
    public void setUsuarioSistema(Usuario usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
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
     * @return the horaEvento
     */
    public Time getHoraEvento() {
        return horaEvento;
    }

    /**
     * @param horaEvento the horaEvento to set
     */
    public void setHoraEvento(Time horaEvento) {
        this.horaEvento = horaEvento;
    }

    /**
     * @return the accionEjecutada
     */
    public String getAccionEjecutada() {
        return accionEjecutada;
    }

    /**
     * @param accionEjecutada the accionEjecutada to set
     */
    public void setAccionEjecutada(String accionEjecutada) {
        this.accionEjecutada = accionEjecutada;
    }

}
