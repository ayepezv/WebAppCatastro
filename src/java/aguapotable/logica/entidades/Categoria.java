
package aguapotable.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

/**
 *
 * @author Andres
 */
public class Categoria {
    
    private int sr_int_categoria;
    private String chv_descripcion_categoria;
    private double db_multa_categoria;
    private String ch_estado_logico;
    private Timestamp ts_fecha_registro;
    private Timestamp ts_fecha_actualizacion;
    private Timestamp ts_fecha_baja;
    private Usuario session_usuario;
    
    public Categoria () {
        session_usuario = new Usuario();
    }

    /**
     * @return the sr_int_categoria
     */
    public int getSr_int_categoria() {
        return sr_int_categoria;
    }

    /**
     * @param sr_int_categoria the sr_int_categoria to set
     */
    public void setSr_int_categoria(int sr_int_categoria) {
        this.sr_int_categoria = sr_int_categoria;
    }

    /**
     * @return the chv_descripcion_categoria
     */
    public String getChv_descripcion_categoria() {
        return chv_descripcion_categoria;
    }

    /**
     * @param chv_descripcion_categoria the chv_descripcion_categoria to set
     */
    public void setChv_descripcion_categoria(String chv_descripcion_categoria) {
        this.chv_descripcion_categoria = chv_descripcion_categoria;
    }

    /**
     * @return the db_multa_categoria
     */
    public double getDb_multa_categoria() {
        return db_multa_categoria;
    }

    /**
     * @param db_multa_categoria the db_multa_categoria to set
     */
    public void setDb_multa_categoria(double db_multa_categoria) {
        this.db_multa_categoria = db_multa_categoria;
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
}
