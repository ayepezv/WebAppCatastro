
package aguapotable.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Ciclo {

    private int sr_id_ciclo;
    private String chv_nombre;
    private String ch_estado_logico;
    private Timestamp ts_fecha_registro;
    private Timestamp ts_fecha_actualizacion;
    private Timestamp ts_fecha_baja;
    private Usuario id_usuario;
    private int int_num_ciclo;
    private int int_mes_ciclo;
    private int int_anio_ciclo;
    private int int_paso_ciclo;
    private Timestamp ts_fecha_facturacion;
    private Timestamp ts_fecha_digitacion;
    private Timestamp ts_fecha_validacion;
    private Timestamp ts_fecha_actualiza_datos;
    private Timestamp ts_fecha_fin_prefacturacion;
    private Timestamp ts_fecha_validar_promedios;
    private Timestamp ts_fecha_validar_esferas;
    private Timestamp ts_fecha_validar_negativos;
    private Timestamp ts_fecha_validar_puerta_cerrada;

public Ciclo() {
        id_usuario = new Usuario();
    }

    /**
     * @return the sr_id_ciclo
     */
    public int getSr_id_ciclo() {
        return sr_id_ciclo;
    }

    /**
     * @param sr_id_ciclo the sr_id_ciclo to set
     */
    public void setSr_id_ciclo(int sr_id_ciclo) {
        this.sr_id_ciclo = sr_id_ciclo;
    }

    /**
     * @return the chv_nombre
     */
    public String getChv_nombre() {
        return chv_nombre;
    }

    /**
     * @param chv_nombre the chv_nombre to set
     */
    public void setChv_nombre(String chv_nombre) {
        this.chv_nombre = chv_nombre;
    }

    /**
     * @return the ch_estado_logico
     */
    public String getCh_estado_logico() {
        return ch_estado_logico;
    }

    /**
     * @param ch_estado_logico the ch_estado_logico to set
     */
    public void setCh_estado_logico(String ch_estado_logico) {
        this.ch_estado_logico = ch_estado_logico;
    }

    /**
     * @return the ts_fecha_registro
     */
    public Timestamp getTs_fecha_registro() {
        return ts_fecha_registro;
    }

    /**
     * @param ts_fecha_registro the ts_fecha_registro to set
     */
    public void setTs_fecha_registro(Timestamp ts_fecha_registro) {
        this.ts_fecha_registro = ts_fecha_registro;
    }

    /**
     * @return the ts_fecha_actualizacion
     */
    public Timestamp getTs_fecha_actualizacion() {
        return ts_fecha_actualizacion;
    }

    /**
     * @param ts_fecha_actualizacion the ts_fecha_actualizacion to set
     */
    public void setTs_fecha_actualizacion(Timestamp ts_fecha_actualizacion) {
        this.ts_fecha_actualizacion = ts_fecha_actualizacion;
    }

    /**
     * @return the ts_fecha_baja
     */
    public Timestamp getTs_fecha_baja() {
        return ts_fecha_baja;
    }

    /**
     * @param ts_fecha_baja the ts_fecha_baja to set
     */
    public void setTs_fecha_baja(Timestamp ts_fecha_baja) {
        this.ts_fecha_baja = ts_fecha_baja;
    }

    /**
     * @return the id_usuario
     */
    public Usuario getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the int_num_ciclo
     */
    public int getInt_num_ciclo() {
        return int_num_ciclo;
    }

    /**
     * @param int_num_ciclo the int_num_ciclo to set
     */
    public void setInt_num_ciclo(int int_num_ciclo) {
        this.int_num_ciclo = int_num_ciclo;
    }

    /**
     * @return the int_mes_ciclo
     */
    public int getInt_mes_ciclo() {
        return int_mes_ciclo;
    }

    /**
     * @param int_mes_ciclo the int_mes_ciclo to set
     */
    public void setInt_mes_ciclo(int int_mes_ciclo) {
        this.int_mes_ciclo = int_mes_ciclo;
    }

    /**
     * @return the int_anio_ciclo
     */
    public int getInt_anio_ciclo() {
        return int_anio_ciclo;
    }

    /**
     * @param int_anio_ciclo the int_anio_ciclo to set
     */
    public void setInt_anio_ciclo(int int_anio_ciclo) {
        this.int_anio_ciclo = int_anio_ciclo;
    }

    /**
     * @return the int_paso_ciclo
     */
    public int getInt_paso_ciclo() {
        return int_paso_ciclo;
    }

    /**
     * @param int_paso_ciclo the int_paso_ciclo to set
     */
    public void setInt_paso_ciclo(int int_paso_ciclo) {
        this.int_paso_ciclo = int_paso_ciclo;
    }

    /**
     * @return the ts_fecha_facturacion
     */
    public Timestamp getTs_fecha_facturacion() {
        return ts_fecha_facturacion;
    }

    /**
     * @param ts_fecha_facturacion the ts_fecha_facturacion to set
     */
    public void setTs_fecha_facturacion(Timestamp ts_fecha_facturacion) {
        this.ts_fecha_facturacion = ts_fecha_facturacion;
    }

    /**
     * @return the ts_fecha_digitacion
     */
    public Timestamp getTs_fecha_digitacion() {
        return ts_fecha_digitacion;
    }

    /**
     * @param ts_fecha_digitacion the ts_fecha_digitacion to set
     */
    public void setTs_fecha_digitacion(Timestamp ts_fecha_digitacion) {
        this.ts_fecha_digitacion = ts_fecha_digitacion;
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
     * @return the ts_fecha_actualiza_datos
     */
    public Timestamp getTs_fecha_actualiza_datos() {
        return ts_fecha_actualiza_datos;
    }

    /**
     * @param ts_fecha_actualiza_datos the ts_fecha_actualiza_datos to set
     */
    public void setTs_fecha_actualiza_datos(Timestamp ts_fecha_actualiza_datos) {
        this.ts_fecha_actualiza_datos = ts_fecha_actualiza_datos;
    }

    /**
     * @return the ts_fecha_fin_prefacturacion
     */
    public Timestamp getTs_fecha_fin_prefacturacion() {
        return ts_fecha_fin_prefacturacion;
    }

    /**
     * @param ts_fecha_fin_prefacturacion the ts_fecha_fin_prefacturacion to set
     */
    public void setTs_fecha_fin_prefacturacion(Timestamp ts_fecha_fin_prefacturacion) {
        this.ts_fecha_fin_prefacturacion = ts_fecha_fin_prefacturacion;
    }

    /**
     * @return the ts_fecha_validar_promedios
     */
    public Timestamp getTs_fecha_validar_promedios() {
        return ts_fecha_validar_promedios;
    }

    /**
     * @param ts_fecha_validar_promedios the ts_fecha_validar_promedios to set
     */
    public void setTs_fecha_validar_promedios(Timestamp ts_fecha_validar_promedios) {
        this.ts_fecha_validar_promedios = ts_fecha_validar_promedios;
    }

    /**
     * @return the ts_fecha_validar_esferas
     */
    public Timestamp getTs_fecha_validar_esferas() {
        return ts_fecha_validar_esferas;
    }

    /**
     * @param ts_fecha_validar_esferas the ts_fecha_validar_esferas to set
     */
    public void setTs_fecha_validar_esferas(Timestamp ts_fecha_validar_esferas) {
        this.ts_fecha_validar_esferas = ts_fecha_validar_esferas;
    }

    /**
     * @return the ts_fecha_validar_negativos
     */
    public Timestamp getTs_fecha_validar_negativos() {
        return ts_fecha_validar_negativos;
    }

    /**
     * @param ts_fecha_validar_negativos the ts_fecha_validar_negativos to set
     */
    public void setTs_fecha_validar_negativos(Timestamp ts_fecha_validar_negativos) {
        this.ts_fecha_validar_negativos = ts_fecha_validar_negativos;
    }

    /**
     * @return the ts_fecha_validar_puerta_cerrada
     */
    public Timestamp getTs_fecha_validar_puerta_cerrada() {
        return ts_fecha_validar_puerta_cerrada;
    }

    /**
     * @param ts_fecha_validar_puerta_cerrada the ts_fecha_validar_puerta_cerrada to set
     */
    public void setTs_fecha_validar_puerta_cerrada(Timestamp ts_fecha_validar_puerta_cerrada) {
        this.ts_fecha_validar_puerta_cerrada = ts_fecha_validar_puerta_cerrada;
    }
}
