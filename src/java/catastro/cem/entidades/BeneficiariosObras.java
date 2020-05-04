package catastro.cem.entidades;

import catastro.logica.entidades.PredioV2;
import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class BeneficiariosObras {

    private int idBeneficiario;
    private int numBeneficiario;
    private Obra obra;
    private PredioV2 predio;
    private double frente;
    private double areaTerreno;
    private double areaConstruccion;
    private double avaluoTerreno;
    private double avaluo;
    private int anioAvaluo;
    private double valorMejora;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private String observaciones;
    private String tipo;
    private Usuario sessionUsuario;
    private Mejora mejora;
    private double factor;

    public BeneficiariosObras() {
        mejora = new Mejora();
        sessionUsuario = new Usuario();
        obra = new Obra();
        predio = new PredioV2();
    }

    /**
     * @return the idBeneficiario
     */
    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * @param idBeneficiario the idBeneficiario to set
     */
    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    /**
     * @return the numBeneficiario
     */
    public int getNumBeneficiario() {
        return numBeneficiario;
    }

    /**
     * @param numBeneficiario the numBeneficiario to set
     */
    public void setNumBeneficiario(int numBeneficiario) {
        this.numBeneficiario = numBeneficiario;
    }

    /**
     * @return the obra
     */
    public Obra getObra() {
        return obra;
    }

    /**
     * @param obra the obra to set
     */
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    /**
     * @return the predio
     */
    public PredioV2 getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(PredioV2 predio) {
        this.predio = predio;
    }

    /**
     * @return the frente
     */
    public double getFrente() {
        return frente;
    }

    /**
     * @param frente the frente to set
     */
    public void setFrente(double frente) {
        this.frente = frente;
    }

    /**
     * @return the areaTerreno
     */
    public double getAreaTerreno() {
        return areaTerreno;
    }

    /**
     * @param areaTerreno the areaTerreno to set
     */
    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
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
     * @return the avaluo
     */
    public double getAvaluo() {
        return avaluo;
    }

    /**
     * @param avaluo the avaluo to set
     */
    public void setAvaluo(double avaluo) {
        this.avaluo = avaluo;
    }

    /**
     * @return the anioAvaluo
     */
    public int getAnioAvaluo() {
        return anioAvaluo;
    }

    /**
     * @param anioAvaluo the anioAvaluo to set
     */
    public void setAnioAvaluo(int anioAvaluo) {
        this.anioAvaluo = anioAvaluo;
    }

    /**
     * @return the valorMejora
     */
    public double getValorMejora() {
        return valorMejora;
    }

    /**
     * @param valorMejora the valorMejora to set
     */
    public void setValorMejora(double valorMejora) {
        this.valorMejora = valorMejora;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the avaluoTerreno
     */
    public double getAvaluoTerreno() {
        return avaluoTerreno;
    }

    /**
     * @param avaluoTerreno the avaluoTerreno to set
     */
    public void setAvaluoTerreno(double avaluoTerreno) {
        this.avaluoTerreno = avaluoTerreno;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the factor
     */
    public double getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(double factor) {
        this.factor = factor;
    }

}
