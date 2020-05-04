
package aguapotable.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;


public class Ruta {
    
    private int sr_id_ruta;
    private int int_id_sector; 
    private String chv_nombre_ruta; 
    private int int_numero_ruta; 
    private Timestamp ts_fecha_digitacion_ruta; 
    private Timestamp ts_fecha_verificar_ruta; 
    private Timestamp ts_fecha_validar_ruta; 
    private boolean bl_imprimir_ruta; 
    private boolean bl_lectura_ruta; 
    private String ch_estado_logico; 
    private Timestamp ts_fecha_registro; 
    private Timestamp ts_fecha_actualizacion;
    private Timestamp ts_fecha_baja; 
    private Usuario session_usuario;
    
    public Ruta () {
        session_usuario = new Usuario();
    }

    /**
     * @return the sr_id_ruta
     */
    public int getSr_id_ruta() {
        return sr_id_ruta;
    }

    /**
     * @param sr_id_ruta the sr_id_ruta to set
     */
    public void setSr_id_ruta(int sr_id_ruta) {
        this.sr_id_ruta = sr_id_ruta;
    }

    /**
     * @return the int_id_sector
     */
    public int getInt_id_sector() {
        return int_id_sector;
    }

    /**
     * @param int_id_sector the int_id_sector to set
     */
    public void setInt_id_sector(int int_id_sector) {
        this.int_id_sector = int_id_sector;
    }

    /**
     * @return the chv_nombre_ruta
     */
    public String getChv_nombre_ruta() {
        return chv_nombre_ruta;
    }

    /**
     * @param chv_nombre_ruta the chv_nombre_ruta to set
     */
    public void setChv_nombre_ruta(String chv_nombre_ruta) {
        this.chv_nombre_ruta = chv_nombre_ruta;
    }

    /**
     * @return the int_numero_ruta
     */
    public int getInt_numero_ruta() {
        return int_numero_ruta;
    }

    /**
     * @param int_numero_ruta the int_numero_ruta to set
     */
    public void setInt_numero_ruta(int int_numero_ruta) {
        this.int_numero_ruta = int_numero_ruta;
    }

    /**
     * @return the ts_fecha_digitacion_ruta
     */
    public Timestamp getTs_fecha_digitacion_ruta() {
        return ts_fecha_digitacion_ruta;
    }

    /**
     * @param ts_fecha_digitacion_ruta the ts_fecha_digitacion_ruta to set
     */
    public void setTs_fecha_digitacion_ruta(Timestamp ts_fecha_digitacion_ruta) {
        this.ts_fecha_digitacion_ruta = ts_fecha_digitacion_ruta;
    }

    /**
     * @return the ts_fecha_verificar_ruta
     */
    public Timestamp getTs_fecha_verificar_ruta() {
        return ts_fecha_verificar_ruta;
    }

    /**
     * @param ts_fecha_verificar_ruta the ts_fecha_verificar_ruta to set
     */
    public void setTs_fecha_verificar_ruta(Timestamp ts_fecha_verificar_ruta) {
        this.ts_fecha_verificar_ruta = ts_fecha_verificar_ruta;
    }

    /**
     * @return the ts_fecha_validar_ruta
     */
    public Timestamp getTs_fecha_validar_ruta() {
        return ts_fecha_validar_ruta;
    }

    /**
     * @param ts_fecha_validar_ruta the ts_fecha_validar_ruta to set
     */
    public void setTs_fecha_validar_ruta(Timestamp ts_fecha_validar_ruta) {
        this.ts_fecha_validar_ruta = ts_fecha_validar_ruta;
    }

    /**
     * @return the bl_imprimir_ruta
     */
    public boolean isBl_imprimir_ruta() {
        return bl_imprimir_ruta;
    }

    /**
     * @param bl_imprimir_ruta the bl_imprimir_ruta to set
     */
    public void setBl_imprimir_ruta(boolean bl_imprimir_ruta) {
        this.bl_imprimir_ruta = bl_imprimir_ruta;
    }

    /**
     * @return the bl_lectura_ruta
     */
    public boolean isBl_lectura_ruta() {
        return bl_lectura_ruta;
    }

    /**
     * @param bl_lectura_ruta the bl_lectura_ruta to set
     */
    public void setBl_lectura_ruta(boolean bl_lectura_ruta) {
        this.bl_lectura_ruta = bl_lectura_ruta;
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
     * @return the session_usuario
     */
    public Usuario getSession_usuario() {
        return session_usuario;
    }

    /**
     * @param session_usuario the session_usuario to set
     */
    public void setSession_usuario(Usuario session_usuario) {
        this.session_usuario = session_usuario;
    }

    public void setSessionUsuario(Usuario buscarUsuarioDadoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
