package catastro.recaudacion.entidades;

import catastro.logica.entidades.PredioV2;
import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Titulo {

    private int idTitulo;
    private int anioTitulo;
    private int numTitulo;
    private String tituloNumero;
    private Timestamp fechaEmision;
    private PredioV2 predio;
    private Usuario propietarioIndex;
    private double valorTerreno;
    private double valorConstruccion;
    private double avaluoComercial;
    private double excenciones;
    private String causaExcencion;
    private double avaluoImponible;
    private double impuestoPredial;
    private double recolectBasura;
    private double cuerpoBomberos;
    private double recargo;
    private double totalTitulo;
    private double interesesMora;
    private double totalPagar;
    private boolean pagado;
    private Usuario usuarioSistema;
    private Transanccion transacion;
    private double descuento;
    private double iva;
    private String estadoLogico;
    private Timestamp fechaActualizacion;
    private String observaciones;
    private double procesamiento;
    private Usuario sessionUsuario;
    private double pagoTardio;
    private String razonBaja;
    private Timestamp fechaBaja;
    private Timestamp fechaRegistro;
    private String tipoEmision;
    private Timestamp fechaEmisionIndividual;

    public Titulo() {
        usuarioSistema = new Usuario();
        sessionUsuario = new Usuario();
        predio = new PredioV2();
        propietarioIndex = new Usuario();
        transacion = new Transanccion();
    }

    /**
     * @return the idTitulo
     */
    public int getIdTitulo() {
        return idTitulo;
    }

    /**
     * @param idTitulo the idTitulo to set
     */
    public void setIdTitulo(int idTitulo) {
        this.idTitulo = idTitulo;
    }

    /**
     * @return the anioTitulo
     */
    public int getAnioTitulo() {
        return anioTitulo;
    }

    /**
     * @param anioTitulo the anioTitulo to set
     */
    public void setAnioTitulo(int anioTitulo) {
        this.anioTitulo = anioTitulo;
    }

    /**
     * @return the numTitulo
     */
    public int getNumTitulo() {
        return numTitulo;
    }

    /**
     * @param numTitulo the numTitulo to set
     */
    public void setNumTitulo(int numTitulo) {
        this.numTitulo = numTitulo;
    }

    /**
     * @return the tituloNumero
     */
    public String getTituloNumero() {
        return tituloNumero;
    }

    /**
     * @param tituloNumero the tituloNumero to set
     */
    public void setTituloNumero(String tituloNumero) {
        this.tituloNumero = tituloNumero;
    }

    /**
     * @return the fechaEmision
     */
    public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
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
     * @return the propietarioIndex
     */
    public Usuario getPropietarioIndex() {
        return propietarioIndex;
    }

    /**
     * @param propietarioIndex the propietarioIndex to set
     */
    public void setPropietarioIndex(Usuario propietarioIndex) {
        this.propietarioIndex = propietarioIndex;
    }

    /**
     * @return the valorTerreno
     */
    public double getValorTerreno() {
        return valorTerreno;
    }

    /**
     * @param valorTerreno the valorTerreno to set
     */
    public void setValorTerreno(double valorTerreno) {
        this.valorTerreno = valorTerreno;
    }

    /**
     * @return the valorConstruccion
     */
    public double getValorConstruccion() {
        return valorConstruccion;
    }

    /**
     * @param valorConstruccion the valorConstruccion to set
     */
    public void setValorConstruccion(double valorConstruccion) {
        this.valorConstruccion = valorConstruccion;
    }

    /**
     * @return the avaluoComercial
     */
    public double getAvaluoComercial() {
        return avaluoComercial;
    }

    /**
     * @param avaluoComercial the avaluoComercial to set
     */
    public void setAvaluoComercial(double avaluoComercial) {
        this.avaluoComercial = avaluoComercial;
    }

    /**
     * @return the excenciones
     */
    public double getExcenciones() {
        return excenciones;
    }

    /**
     * @param excenciones the excenciones to set
     */
    public void setExcenciones(double excenciones) {
        this.excenciones = excenciones;
    }

    /**
     * @return the causaExcencion
     */
    public String getCausaExcencion() {
        return causaExcencion;
    }

    /**
     * @param causaExcencion the causaExcencion to set
     */
    public void setCausaExcencion(String causaExcencion) {
        this.causaExcencion = causaExcencion;
    }

    /**
     * @return the avaluoImponible
     */
    public double getAvaluoImponible() {
        return avaluoImponible;
    }

    /**
     * @param avaluoImponible the avaluoImponible to set
     */
    public void setAvaluoImponible(double avaluoImponible) {
        this.avaluoImponible = avaluoImponible;
    }

    /**
     * @return the impuestoPredial
     */
    public double getImpuestoPredial() {
        return impuestoPredial;
    }

    /**
     * @param impuestoPredial the impuestoPredial to set
     */
    public void setImpuestoPredial(double impuestoPredial) {
        this.impuestoPredial = impuestoPredial;
    }

    /**
     * @return the recolectBasura
     */
    public double getRecolectBasura() {
        return recolectBasura;
    }

    /**
     * @param recolectBasura the recolectBasura to set
     */
    public void setRecolectBasura(double recolectBasura) {
        this.recolectBasura = recolectBasura;
    }

    /**
     * @return the cuerpoBomberos
     */
    public double getCuerpoBomberos() {
        return cuerpoBomberos;
    }

    /**
     * @param cuerpoBomberos the cuerpoBomberos to set
     */
    public void setCuerpoBomberos(double cuerpoBomberos) {
        this.cuerpoBomberos = cuerpoBomberos;
    }

    /**
     * @return the recargo
     */
    public double getRecargo() {
        return recargo;
    }

    /**
     * @param recargo the recargo to set
     */
    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    /**
     * @return the totalTitulo
     */
    public double getTotalTitulo() {
        return totalTitulo;
    }

    /**
     * @param totalTitulo the totalTitulo to set
     */
    public void setTotalTitulo(double totalTitulo) {
        this.totalTitulo = totalTitulo;
    }

    /**
     * @return the interesesMora
     */
    public double getInteresesMora() {
        return interesesMora;
    }

    /**
     * @param interesesMora the interesesMora to set
     */
    public void setInteresesMora(double interesesMora) {
        this.interesesMora = interesesMora;
    }

    /**
     * @return the totalPagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * @param totalPagar the totalPagar to set
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    /**
     * @return the pagado
     */
    public boolean isPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(boolean pagado) {
        this.pagado = pagado;
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
     * @return the transacion
     */
    public Transanccion getTransacion() {
        return transacion;
    }

    /**
     * @param transacion the transacion to set
     */
    public void setTransacion(Transanccion transacion) {
        this.transacion = transacion;
    }

    /**
     * @return the descuento
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the iva
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(double iva) {
        this.iva = iva;
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
     * @return the procesamiento
     */
    public double getProcesamiento() {
        return procesamiento;
    }

    /**
     * @param procesamiento the procesamiento to set
     */
    public void setProcesamiento(double procesamiento) {
        this.procesamiento = procesamiento;
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
     * @return the pagoTardio
     */
    public double getPagoTardio() {
        return pagoTardio;
    }

    /**
     * @param pagoTardio the pagoTardio to set
     */
    public void setPagoTardio(double pagoTardio) {
        this.pagoTardio = pagoTardio;
    }

    /**
     * @return the razonBaja
     */
    public String getRazonBaja() {
        return razonBaja;
    }

    /**
     * @param razonBaja the razonBaja to set
     */
    public void setRazonBaja(String razonBaja) {
        this.razonBaja = razonBaja;
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
     * @return the tipoEmision
     */
    public String getTipoEmision() {
        return tipoEmision;
    }

    /**
     * @param tipoEmision the tipoEmision to set
     */
    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    /**
     * @return the fechaEmisionIndividual
     */
    public Timestamp getFechaEmisionIndividual() {
        return fechaEmisionIndividual;
    }

    /**
     * @param fechaEmisionIndividual the fechaEmisionIndividual to set
     */
    public void setFechaEmisionIndividual(Timestamp fechaEmisionIndividual) {
        this.fechaEmisionIndividual = fechaEmisionIndividual;
    }

}
