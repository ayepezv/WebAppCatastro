package catastro.cem.entidades;

import catastro.logica.entidades.PredioV2;
import catastro.recaudacion.entidades.Transanccion;
import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class DetalleCuotasCem {

    private int codigo;
    private Timestamp fechaEmision;
    private int numCouta;
    private Obra obra;
    private PredioV2 predio;
    private double cuota;
    private String pago;
    private Date fechaPago;
    private double valorRestante;
    private boolean pagado;
    private Timestamp fechaCobro;
    private Transanccion transaccion;
    private Timestamp fechaActualizacion;
    private Usuario sessionUsuario;
    private String observaciones;
    private double interes;
    private double totalPagar;

    public DetalleCuotasCem() {
        obra = new Obra();
        predio = new PredioV2();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
     * @return the numCouta
     */
    public int getNumCouta() {
        return numCouta;
    }

    /**
     * @param numCouta the numCouta to set
     */
    public void setNumCouta(int numCouta) {
        this.numCouta = numCouta;
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
     * @return the cuota
     */
    public double getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    /**
     * @return the pago
     */
    public String getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(String pago) {
        this.pago = pago;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the valorRestante
     */
    public double getValorRestante() {
        return valorRestante;
    }

    /**
     * @param valorRestante the valorRestante to set
     */
    public void setValorRestante(double valorRestante) {
        this.valorRestante = valorRestante;
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
     * @return the fechaCobro
     */
    public Timestamp getFechaCobro() {
        return fechaCobro;
    }

    /**
     * @param fechaCobro the fechaCobro to set
     */
    public void setFechaCobro(Timestamp fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    /**
     * @return the transaccion
     */
    public Transanccion getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(Transanccion transaccion) {
        this.transaccion = transaccion;
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
     * @return the interes
     */
    public double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(double interes) {
        this.interes = interes;
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

}
