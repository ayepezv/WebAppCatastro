package catastro.logica.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class Predio {

    private int idPredio;
    private Parroquia parroquia;
    private String divPolitica;
    private Manzana manzana;
    private int numPredio;
    private TipoLote tipoLote;
    private double propHorizontal;
    private double derechosAcciones;
    private String claveCatastral;
    private String claveCatAnterior;
    private Barrio barrio;
    private Calle callePrincipal;
    private Calle calleSecundaria;
    private String nombreEdificio;
    private TipoPersoneria tipoPersoneria;
    private Usuario propietario;
    private ExencionEspecial excenEspecial;
    private FormaAdquisicion formaAdq;
    private Date fechaEscrituracion;
    private Date fechaRegistroPredio;
    private double cuantia;
    private Prestamo prestamo;
    private String condPedio;
    private String patio;
    private String cerramiento;
    private String muros;
    private String piscina;
    private String cisterna;
    private int numBanios;
    private String pathCroquis;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public Predio() {
        parroquia = new Parroquia();
        manzana = new Manzana();
        tipoLote = new TipoLote();
        barrio = new Barrio();
        callePrincipal = new Calle();
        calleSecundaria = new Calle();
        tipoPersoneria = new TipoPersoneria();
        propietario = new Usuario();
        excenEspecial = new ExencionEspecial();
        formaAdq = new FormaAdquisicion();
        prestamo = new Prestamo();
    }

    public int getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(int idPredio) {
        this.idPredio = idPredio;
    }

    public Parroquia getParroquia() {
        return parroquia;
    }

    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }

    public String getDivPolitica() {
        return divPolitica;
    }

    public void setDivPolitica(String divPolitica) {
        this.divPolitica = divPolitica;
    }

    public Manzana getManzana() {
        return manzana;
    }

    public void setManzana(Manzana manzana) {
        this.manzana = manzana;
    }

    public int getNumPredio() {
        return numPredio;
    }

    public void setNumPredio(int numPredio) {
        this.numPredio = numPredio;
    }

    public TipoLote getTipoLote() {
        return tipoLote;
    }

    public void setTipoLote(TipoLote tipoLote) {
        this.tipoLote = tipoLote;
    }

    public double getPropHorizontal() {
        return propHorizontal;
    }

    public void setPropHorizontal(double propHorizontal) {
        this.propHorizontal = propHorizontal;
    }

    public double getDerechosAcciones() {
        return derechosAcciones;
    }

    public void setDerechosAcciones(double derechosAcciones) {
        this.derechosAcciones = derechosAcciones;
    }

    public String getClaveCatastral() {
        return claveCatastral;
    }

    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    public String getClaveCatAnterior() {
        return claveCatAnterior;
    }

    public void setClaveCatAnterior(String claveCatAnterior) {
        this.claveCatAnterior = claveCatAnterior;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Calle getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(Calle callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public Calle getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(Calle calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public TipoPersoneria getTipoPersoneria() {
        return tipoPersoneria;
    }

    public void setTipoPersoneria(TipoPersoneria tipoPersoneria) {
        this.tipoPersoneria = tipoPersoneria;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public ExencionEspecial getExcenEspecial() {
        return excenEspecial;
    }

    public void setExcenEspecial(ExencionEspecial excenEspecial) {
        this.excenEspecial = excenEspecial;
    }

    public FormaAdquisicion getFormaAdq() {
        return formaAdq;
    }

    public void setFormaAdq(FormaAdquisicion formaAdq) {
        this.formaAdq = formaAdq;
    }

    public Date getFechaEscrituracion() {
        return fechaEscrituracion;
    }

    public void setFechaEscrituracion(Date fechaEscrituracion) {
        this.fechaEscrituracion = fechaEscrituracion;
    }

    public Date getFechaRegistroPredio() {
        return fechaRegistroPredio;
    }

    public void setFechaRegistroPredio(Date fechaRegistroPredio) {
        this.fechaRegistroPredio = fechaRegistroPredio;
    }

    public double getCuantia() {
        return cuantia;
    }

    public void setCuantia(double cuantia) {
        this.cuantia = cuantia;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public String getCondPedio() {
        return condPedio;
    }

    public void setCondPedio(String condPedio) {
        this.condPedio = condPedio;
    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
        this.patio = patio;
    }

    public String getCerramiento() {
        return cerramiento;
    }

    public void setCerramiento(String cerramiento) {
        this.cerramiento = cerramiento;
    }

    public String getMuros() {
        return muros;
    }

    public void setMuros(String muros) {
        this.muros = muros;
    }

    public String getPiscina() {
        return piscina;
    }

    public void setPiscina(String piscina) {
        this.piscina = piscina;
    }

    public String getCisterna() {
        return cisterna;
    }

    public void setCisterna(String cisterna) {
        this.cisterna = cisterna;
    }

    public int getNumBanios() {
        return numBanios;
    }

    public void setNumBanios(int numBanios) {
        this.numBanios = numBanios;
    }

    public String getPathCroquis() {
        return pathCroquis;
    }

    public void setPathCroquis(String pathCroquis) {
        this.pathCroquis = pathCroquis;
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
