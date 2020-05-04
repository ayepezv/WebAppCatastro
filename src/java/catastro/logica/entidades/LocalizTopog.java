package catastro.logica.entidades;

import java.sql.Timestamp;

public class LocalizTopog {

    private int idLocalizTop;
    private Predio predio;
    private String localizacion;
    private String topografia;
    private String materialVial;
    private String construcSuelo;
    private String bordillos;
    private String aceras;
    private String aguaPotable;
    private String alcantPluvial;
    private String alcantSanitario;
    private String energiaElectrica;
    private String redTelefonica;
    private String alumbPublico;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public LocalizTopog() {
        predio = new Predio();
    }

    public int getIdLocalizTop() {
        return idLocalizTop;
    }

    public void setIdLocalizTop(int idLocalizTop) {
        this.idLocalizTop = idLocalizTop;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getTopografia() {
        return topografia;
    }

    public void setTopografia(String topografia) {
        this.topografia = topografia;
    }

    public String getMaterialVial() {
        return materialVial;
    }

    public void setMaterialVial(String materialVial) {
        this.materialVial = materialVial;
    }

    public String getConstrucSuelo() {
        return construcSuelo;
    }

    public void setConstrucSuelo(String construcSuelo) {
        this.construcSuelo = construcSuelo;
    }

    public String getBordillos() {
        return bordillos;
    }

    public void setBordillos(String bordillos) {
        this.bordillos = bordillos;
    }

    public String getAceras() {
        return aceras;
    }

    public void setAceras(String aceras) {
        this.aceras = aceras;
    }

    public String getAguaPotable() {
        return aguaPotable;
    }

    public void setAguaPotable(String aguaPotable) {
        this.aguaPotable = aguaPotable;
    }

    public String getAlcantPluvial() {
        return alcantPluvial;
    }

    public void setAlcantPluvial(String alcantPluvial) {
        this.alcantPluvial = alcantPluvial;
    }

    public String getAlcantSanitario() {
        return alcantSanitario;
    }

    public void setAlcantSanitario(String alcantSanitario) {
        this.alcantSanitario = alcantSanitario;
    }

    public String getEnergiaElectrica() {
        return energiaElectrica;
    }

    public void setEnergiaElectrica(String energiaElectrica) {
        this.energiaElectrica = energiaElectrica;
    }

    public String getRedTelefonica() {
        return redTelefonica;
    }

    public void setRedTelefonica(String redTelefonica) {
        this.redTelefonica = redTelefonica;
    }

    public String getAlumbPublico() {
        return alumbPublico;
    }

    public void setAlumbPublico(String alumbPublico) {
        this.alumbPublico = alumbPublico;
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
