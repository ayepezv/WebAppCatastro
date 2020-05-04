package aguapotable.logica.entidades;

import catastro.recaudacion.entidades.Transanccion;
import java.sql.Timestamp;
import java.util.Date;

public class Emisiones {

    private int idEmision;
    private Timestamp fechaEmision;
    private Medidor medidor;
    private Cuenta cuenta;
    private Date fechaPagar;
    private double valorPagar;
    private double intereses;
    private double totalPagar;
    private boolean pagado;
    private Timestamp fechaCobro;
    private Transanccion transaccion;

    public Emisiones() {
        medidor = new Medidor();
        cuenta = new Cuenta();
        transaccion = new Transanccion();
    }

    /**
     * @return the idEmision
     */
    public int getIdEmision() {
        return idEmision;
    }

    /**
     * @param idEmision the idEmision to set
     */
    public void setIdEmision(int idEmision) {
        this.idEmision = idEmision;
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
     * @return the medidor
     */
    public Medidor getMedidor() {
        return medidor;
    }

    /**
     * @param medidor the medidor to set
     */
    public void setMedidor(Medidor medidor) {
        this.medidor = medidor;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the fechaPagar
     */
    public Date getFechaPagar() {
        return fechaPagar;
    }

    /**
     * @param fechaPagar the fechaPagar to set
     */
    public void setFechaPagar(Date fechaPagar) {
        this.fechaPagar = fechaPagar;
    }

    /**
     * @return the valorPagar
     */
    public double getValorPagar() {
        return valorPagar;
    }

    /**
     * @param valorPagar the valorPagar to set
     */
    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    /**
     * @return the intereses
     */
    public double getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(double intereses) {
        this.intereses = intereses;
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
    
    

}
