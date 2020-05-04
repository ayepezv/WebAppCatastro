
package catastro.coeficientes.funciones;

import accesoDatos.AccesoDatos;
import catastro.coeficientes.entidades.CoefEscaleras;
import catastro.coeficientes.entidades.CoefVidaUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ayepe
 */
public class FCoefVidaUtil {
    
    
    public static List<CoefVidaUtil> obtenerCoeficientes() throws Exception {
        List<CoefVidaUtil> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        CoefVidaUtil coeficiente;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_coefiencientes_vida_util();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                coeficiente = new CoefVidaUtil();
                coeficiente.setId_coef_vida_util(resultSet.getInt("sr_id_coef_vida_util"));
                coeficiente.setUso(resultSet.getString("chv_uso"));
                coeficiente.setMaterial(resultSet.getString("chv_material"));
                coeficiente.setVida_util(resultSet.getInt("int_vida_util"));
                coeficiente.setResidual(resultSet.getString("ch_estado_logico"));
                coeficiente.setFecha_registro(resultSet.getTimestamp("ts_fecha_registro"));
                coeficiente.setFecha_actualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                coeficiente.setFecha_baja(resultSet.getTimestamp("ts_fecha_baja"));
                coeficiente.setEstado_logico(resultSet.getString("ch_estado_logico"));
                coeficiente.setDescripcion(resultSet.getString("chv_descripcion"));
                lst.add(coeficiente);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static String registrarCoeficiente(CoefVidaUtil coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_coef_vida_util(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, coeficiente.getUso());
            prstm.setString(2, coeficiente.getMaterial());
            prstm.setInt(3, coeficiente.getVida_util());
            prstm.setString(4, coeficiente.getDescripcion());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static String actualizarCoeficiente(CoefVidaUtil coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_coef_vida_util(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, coeficiente.getUso());
            prstm.setString(2, coeficiente.getMaterial());
            prstm.setInt(3, coeficiente.getVida_util());
            prstm.setInt(4, coeficiente.getId_coef_vida_util());
            prstm.setString(5, coeficiente.getDescripcion());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public static String eliminarCoeficiente(CoefVidaUtil coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_coef_vida_util(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, coeficiente.getId_coef_vida_util());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
