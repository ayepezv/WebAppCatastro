package aguapotable.logica.entidades;

import java.sql.Timestamp;
import java.util.Date;
import master.logica.entidades.Usuario;

public class Lectura {

    private int idLectura;
    private Medidor medidor;
    private Date fechaLectura;
    private double lecturaAnterior;
    private double lecturaActual;
    private double consumo;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;
    private Usuario lector;
    private int int_proceso_mes;
    private int int_proceso_anio;
    private Timestamp ts_fecha_digitada; 
    private Timestamp ts_fecha_validacion; 
    private int int_novedad;
    private int int_lectura_aplicada;
    private int int_lectura_accion;
    private int int_lectura_verificada;
    private int int_lectura_final;
    private Usuario int_usuario_verificador;
    private Usuario int_usuario_digitador;
    private int int_lectura_calculada;
    private String chv_observacion;
    private String by_medidor_lectura;
    private String by_medidor;
    private String by_observacion_1;
    private String by_observacion_2;
    private String by_fachada; 
    private double db_coordenada_x;
    private double db_coordenada_y;

    public Lectura() {
        medidor = new Medidor();
        sessionUsuario = new Usuario();
        lector = new Usuario();
        int_usuario_verificador = new Usuario();
        int_usuario_digitador = new Usuario();
    }

    /**
     * @return the idLectura
     */
    public int getIdLectura() {
        return idLectura;
    }

    /**
     * @param idLectura the idLectura to set
     */
    public void setIdLectura(int idLectura) {
        this.idLectura = idLectura;
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
     * @return the fechaLectura
     */
    public Date getFechaLectura() {
        return fechaLectura;
    }

    /**
     * @param fechaLectura the fechaLectura to set
     */
    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    /**
     * @return the lecturaAnterior
     */
    public double getLecturaAnterior() {
        return lecturaAnterior;
    }

    /**
     * @param lecturaAnterior the lecturaAnterior to set
     */
    public void setLecturaAnterior(double lecturaAnterior) {
        this.lecturaAnterior = lecturaAnterior;
    }

    /**
     * @return the lecturaActual
     */
    public double getLecturaActual() {
        return lecturaActual;
    }

    /**
     * @param lecturaActual the lecturaActual to set
     */
    public void setLecturaActual(double lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    /**
     * @return the consumo
     */
    public double getConsumo() {
        return consumo;
    }

    /**
     * @param consumo the consumo to set
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /**
     * @return the inicioPeriodo
     */
    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    /**
     * @param inicioPeriodo the inicioPeriodo to set
     */
    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
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

    /**
     * @return the lector
     */
    public Usuario getLector() {
        return lector;
    }

    /**
     * @param lector the lector to set
     */
    public void setLector(Usuario lector) {
        this.lector = lector;
    }

    /**
     * @return the finPeriodo
     */
    public Date getFinPeriodo() {
        return finPeriodo;
    }

    /**
     * @param finPeriodo the finPeriodo to set
     */
    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    /**
     * @return the int_proceso_mes
     */
    public int getInt_proceso_mes() {
        return int_proceso_mes;
    }

    /**
     * @param int_proceso_mes the int_proceso_mes to set
     */
    public void setInt_proceso_mes(int int_proceso_mes) {
        this.int_proceso_mes = int_proceso_mes;
    }

    /**
     * @return the int_proceso_anio
     */
    public int getInt_proceso_anio() {
        return int_proceso_anio;
    }

    /**
     * @param int_proceso_anio the int_proceso_anio to set
     */
    public void setInt_proceso_anio(int int_proceso_anio) {
        this.int_proceso_anio = int_proceso_anio;
    }

   

    /**
     * @return the ts_fecha_validacion
     */
    public Timestamp getTs_fecha_validacion() {
        return ts_fecha_validacion;
    }

    /**
     * @param ts_fecha_validacion the ts_fecha_validacion to set
     */
    public void setTs_fecha_validacion(Timestamp ts_fecha_validacion) {
        this.ts_fecha_validacion = ts_fecha_validacion;
    }

    /**
     * @return the int_novedad
     */
    public int getInt_novedad() {
        return int_novedad;
    }

    /**
     * @param int_novedad the int_novedad to set
     */
    public void setInt_novedad(int int_novedad) {
        this.int_novedad = int_novedad;
    }

    /**
     * @return the int_lectura_aplicada
     */
    public int getInt_lectura_aplicada() {
        return int_lectura_aplicada;
    }

    /**
     * @param int_lectura_aplicada the int_lectura_aplicada to set
     */
    public void setInt_lectura_aplicada(int int_lectura_aplicada) {
        this.int_lectura_aplicada = int_lectura_aplicada;
    }

    /**
     * @return the int_lectura_accion
     */
    public int getInt_lectura_accion() {
        return int_lectura_accion;
    }

    /**
     * @param int_lectura_accion the int_lectura_accion to set
     */
    public void setInt_lectura_accion(int int_lectura_accion) {
        this.int_lectura_accion = int_lectura_accion;
    }

    /**
     * @return the int_lectura_verificada
     */
    public int getInt_lectura_verificada() {
        return int_lectura_verificada;
    }

    /**
     * @param int_lectura_verificada the int_lectura_verificada to set
     */
    public void setInt_lectura_verificada(int int_lectura_verificada) {
        this.int_lectura_verificada = int_lectura_verificada;
    }

    /**
     * @return the int_lectura_final
     */
    public int getInt_lectura_final() {
        return int_lectura_final;
    }

    /**
     * @param int_lectura_final the int_lectura_final to set
     */
    public void setInt_lectura_final(int int_lectura_final) {
        this.int_lectura_final = int_lectura_final;
    }

    /**
     * @return the int_usuario_verificador
     */
    public Usuario getInt_usuario_verificador() {
        return int_usuario_verificador;
    }

    /**
     * @param int_usuario_verificador the int_usuario_verificador to set
     */
    public void setInt_usuario_verificador(Usuario int_usuario_verificador) {
        this.int_usuario_verificador = int_usuario_verificador;
    }

    /**
     * @return the int_usuario_digitador
     */
    public Usuario getInt_usuario_digitador() {
        return int_usuario_digitador;
    }

    /**
     * @param int_usuario_digitador the int_usuario_digitador to set
     */
    public void setInt_usuario_digitador(Usuario int_usuario_digitador) {
        this.int_usuario_digitador = int_usuario_digitador;
    }

    /**
     * @return the int_lectura_calculada
     */
    public int getInt_lectura_calculada() {
        return int_lectura_calculada;
    }

    /**
     * @param int_lectura_calculada the int_lectura_calculada to set
     */
    public void setInt_lectura_calculada(int int_lectura_calculada) {
        this.int_lectura_calculada = int_lectura_calculada;
    }

    /**
     * @return the chv_observacion
     */
    public String getChv_observacion() {
        return chv_observacion;
    }

    /**
     * @param chv_observacion the chv_observacion to set
     */
    public void setChv_observacion(String chv_observacion) {
        this.chv_observacion = chv_observacion;
    }

    /**
     * @return the by_medidor_lectura
     */
    public String getBy_medidor_lectura() {
        return by_medidor_lectura;
    }

    /**
     * @param by_medidor_lectura the by_medidor_lectura to set
     */
    public void setBy_medidor_lectura(String by_medidor_lectura) {
        this.by_medidor_lectura = by_medidor_lectura;
    }

    /**
     * @return the by_medidor
     */
    public String getBy_medidor() {
        return by_medidor;
    }

    /**
     * @param by_medidor the by_medidor to set
     */
    public void setBy_medidor(String by_medidor) {
        this.by_medidor = by_medidor;
    }

    /**
     * @return the by_observacion_1
     */
    public String getBy_observacion_1() {
        return by_observacion_1;
    }

    /**
     * @param by_observacion_1 the by_observacion_1 to set
     */
    public void setBy_observacion_1(String by_observacion_1) {
        this.by_observacion_1 = by_observacion_1;
    }

    /**
     * @return the by_observacion_2
     */
    public String getBy_observacion_2() {
        return by_observacion_2;
    }

    /**
     * @param by_observacion_2 the by_observacion_2 to set
     */
    public void setBy_observacion_2(String by_observacion_2) {
        this.by_observacion_2 = by_observacion_2;
    }

    /**
     * @return the by_fachada
     */
    public String getBy_fachada() {
        return by_fachada;
    }

    /**
     * @param by_fachada the by_fachada to set
     */
    public void setBy_fachada(String by_fachada) {
        this.by_fachada = by_fachada;
    }

    /**
     * @return the db_coordenada_x
     */
    public double getDb_coordenada_x() {
        return db_coordenada_x;
    }

    /**
     * @param db_coordenada_x the db_coordenada_x to set
     */
    public void setDb_coordenada_x(double db_coordenada_x) {
        this.db_coordenada_x = db_coordenada_x;
    }

    /**
     * @return the db_coordenada_y
     */
    public double getDb_coordenada_y() {
        return db_coordenada_y;
    }

    /**
     * @param db_coordenada_y the db_coordenada_y to set
     */
    public void setDb_coordenada_y(double db_coordenada_y) {
        this.db_coordenada_y = db_coordenada_y;
    }

    /**
     * @return the ts_fecha_digitada
     */
    public Timestamp getTs_fecha_digitada() {
        return ts_fecha_digitada;
    }

    /**
     * @param ts_fecha_digitada the ts_fecha_digitada to set
     */
    public void setTs_fecha_digitada(Timestamp ts_fecha_digitada) {
        this.ts_fecha_digitada = ts_fecha_digitada;
    }

}
