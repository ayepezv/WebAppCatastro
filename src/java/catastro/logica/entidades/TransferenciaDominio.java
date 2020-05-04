package catastro.logica.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class TransferenciaDominio {

    private int idTransferencia;
    private Usuario propietario;
    private Usuario comprador;
    private PredioV2 predio;
    private String tipoTocumento;
    private String numeroDocumento;
    private Date fechaDocumento;
    private String observaciones;
    private String excencionesEsp;
    private String razonExcencion;
    private String formaAdquisicion;
    private String notaria;
    private Date fechaEscritura;
    private Date fechaRegistroTitulo;
    private double areaEscritura;
    private double avaluoProp;
    private String prestamo;
    private String entidad;
    private String objeto;
    private double montoCredito;
    private int plazo;
    private Date fechaConsec;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;

    public TransferenciaDominio() {
        sessionUsuario = new Usuario();
        propietario = new Usuario();
        comprador = new Usuario();
        predio = new PredioV2();
    }

    /**
     * @return the idTransferencia
     */
    public int getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * @param idTransferencia the idTransferencia to set
     */
    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * @return the propietario
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the comprador
     */
    public Usuario getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
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
     * @return the tipoTocumento
     */
    public String getTipoTocumento() {
        return tipoTocumento;
    }

    /**
     * @param tipoTocumento the tipoTocumento to set
     */
    public void setTipoTocumento(String tipoTocumento) {
        this.tipoTocumento = tipoTocumento;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the fechaDocumento
     */
    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    /**
     * @param fechaDocumento the fechaDocumento to set
     */
    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
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
     * @return the excencionesEsp
     */
    public String getExcencionesEsp() {
        return excencionesEsp;
    }

    /**
     * @param excencionesEsp the excencionesEsp to set
     */
    public void setExcencionesEsp(String excencionesEsp) {
        this.excencionesEsp = excencionesEsp;
    }

    /**
     * @return the razonExcencion
     */
    public String getRazonExcencion() {
        return razonExcencion;
    }

    /**
     * @param razonExcencion the razonExcencion to set
     */
    public void setRazonExcencion(String razonExcencion) {
        this.razonExcencion = razonExcencion;
    }

    /**
     * @return the formaAdquisicion
     */
    public String getFormaAdquisicion() {
        return formaAdquisicion;
    }

    /**
     * @param formaAdquisicion the formaAdquisicion to set
     */
    public void setFormaAdquisicion(String formaAdquisicion) {
        this.formaAdquisicion = formaAdquisicion;
    }

    /**
     * @return the notaria
     */
    public String getNotaria() {
        return notaria;
    }

    /**
     * @param notaria the notaria to set
     */
    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    /**
     * @return the fechaEscritura
     */
    public Date getFechaEscritura() {
        return fechaEscritura;
    }

    /**
     * @param fechaEscritura the fechaEscritura to set
     */
    public void setFechaEscritura(Date fechaEscritura) {
        this.fechaEscritura = fechaEscritura;
    }

    /**
     * @return the fechaRegistroTitulo
     */
    public Date getFechaRegistroTitulo() {
        return fechaRegistroTitulo;
    }

    /**
     * @param fechaRegistroTitulo the fechaRegistroTitulo to set
     */
    public void setFechaRegistroTitulo(Date fechaRegistroTitulo) {
        this.fechaRegistroTitulo = fechaRegistroTitulo;
    }

    /**
     * @return the areaEscritura
     */
    public double getAreaEscritura() {
        return areaEscritura;
    }

    /**
     * @param areaEscritura the areaEscritura to set
     */
    public void setAreaEscritura(double areaEscritura) {
        this.areaEscritura = areaEscritura;
    }

    /**
     * @return the avaluoProp
     */
    public double getAvaluoProp() {
        return avaluoProp;
    }

    /**
     * @param avaluoProp the avaluoProp to set
     */
    public void setAvaluoProp(double avaluoProp) {
        this.avaluoProp = avaluoProp;
    }

    /**
     * @return the prestamo
     */
    public String getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo the prestamo to set
     */
    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the objeto
     */
    public String getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the montoCredito
     */
    public double getMontoCredito() {
        return montoCredito;
    }

    /**
     * @param montoCredito the montoCredito to set
     */
    public void setMontoCredito(double montoCredito) {
        this.montoCredito = montoCredito;
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
     * @return the fechaConsec
     */
    public Date getFechaConsec() {
        return fechaConsec;
    }

    /**
     * @param fechaConsec the fechaConsec to set
     */
    public void setFechaConsec(Date fechaConsec) {
        this.fechaConsec = fechaConsec;
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

    
    
}
