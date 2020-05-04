
package aguapotable.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Sector {
    
    private int sr_id_sector;
    private int int_id_ciclo;
    private String chv_nombre;
    private int int_secuencia_sector;
    private String ch_estado_logico; 
    private Timestamp ts_fecha_logico; 
    private Timestamp ts_fecha_registro; 
    private Timestamp ts_fecha_actualizacion; 
    private Timestamp ts_fecha_baja; 
    private Usuario session_usuario;
    
    public Sector () {
        session_usuario = new Usuario();
    }

    /**
     * @return the sr_id_sector
     */
    public int getSr_id_sector() {
        return sr_id_sector;
    }

    /**
     * @param sr_id_sector the sr_id_sector to set
     */
    public void setSr_id_sector(int sr_id_sector) {
        this.sr_id_sector = sr_id_sector;
    }

    /**
     * @return the int_id_ciclo
     */
    public int getInt_id_ciclo() {
        return int_id_ciclo;
    }

    /**
     * @param int_id_ciclo the int_id_ciclo to set
     */
    public void setInt_id_ciclo(int int_id_ciclo) {
        this.int_id_ciclo = int_id_ciclo;
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
     * @return the int_secuencia_sector
     */
    public int getInt_secuencia_sector() {
        return int_secuencia_sector;
    }

    /**
     * @param int_secuencia_sector the int_secuencia_sector to set
     */
    public void setInt_secuencia_sector(int int_secuencia_sector) {
        this.int_secuencia_sector = int_secuencia_sector;
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
     * @return the ts_fecha_logico
     */
    public Timestamp getTs_fecha_logico() {
        return ts_fecha_logico;
    }

    /**
     * @param ts_fecha_logico the ts_fecha_logico to set
     */
    public void setTs_fecha_logico(Timestamp ts_fecha_logico) {
        this.ts_fecha_logico = ts_fecha_logico;
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
    
}
