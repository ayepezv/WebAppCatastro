
package catastro.coeficientes.entidades;

import java.sql.Timestamp;

/**
 *
 * @author gecudcop
 */
public class CoefVidaUtil {
    
    private int id_coef_vida_util;
    private String uso;
    private String material;
    private int vida_util; 
    private String residual;
    private Timestamp fecha_registro;
    private Timestamp fecha_actualizacion; 
    private Timestamp fecha_baja;
    private String estado_logico;
    private String descripcion;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the id_coef_vida_util
     */
    public int getId_coef_vida_util() {
        return id_coef_vida_util;
    }

    /**
     * @param id_coef_vida_util the id_coef_vida_util to set
     */
    public void setId_coef_vida_util(int id_coef_vida_util) {
        this.id_coef_vida_util = id_coef_vida_util;
    }

    /**
     * @return the uso
     */
    public String getUso() {
        return uso;
    }

    /**
     * @param uso the uso to set
     */
    public void setUso(String uso) {
        this.uso = uso;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the vida_util
     */
    public int getVida_util() {
        return vida_util;
    }

    /**
     * @param vida_util the vida_util to set
     */
    public void setVida_util(int vida_util) {
        this.vida_util = vida_util;
    }

    /**
     * @return the residual
     */
    public String getResidual() {
        return residual;
    }

    /**
     * @param residual the residual to set
     */
    public void setResidual(String residual) {
        this.residual = residual;
    }

    /**
     * @return the fecha_registro
     */
    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    /**
     * @param fecha_registro the fecha_registro to set
     */
    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * @return the fecha_actualizacion
     */
    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    /**
     * @param fecha_actualizacion the fecha_actualizacion to set
     */
    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    /**
     * @return the fecha_baja
     */
    public Timestamp getFecha_baja() {
        return fecha_baja;
    }

    /**
     * @param fecha_baja the fecha_baja to set
     */
    public void setFecha_baja(Timestamp fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    /**
     * @return the estado_logico
     */
    public String getEstado_logico() {
        return estado_logico;
    }

    /**
     * @param estado_logico the estado_logico to set
     */
    public void setEstado_logico(String estado_logico) {
        this.estado_logico = estado_logico;
    }

    
    
}
