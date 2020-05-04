
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Dominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FDominio {
    
    public static List<Dominio> obtenerDominio() throws Exception {
        List<Dominio> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Dominio coeficiente;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_dominio();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                coeficiente = new Dominio();
                coeficiente.setIdDominio(resultSet.getInt("sr_id_dominio"));
                coeficiente.setDominio(resultSet.getString("chv_dominio"));
                coeficiente.setDescripcion(resultSet.getString("chv_descripcion"));
                coeficiente.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                coeficiente.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                coeficiente.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                coeficiente.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(coeficiente);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarDominio(Dominio coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_t_dominio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, coeficiente.getDominio());
            prstm.setString(2, coeficiente.getDescripcion());
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

    public static String actualizarDominio(Dominio coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_t_dominio(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, coeficiente.getDominio());
            prstm.setString(2, coeficiente.getDescripcion());
            prstm.setInt(3, coeficiente.getIdDominio());
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

    public static String eliminarDominio(Dominio coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_t_dominio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, coeficiente.getIdDominio());
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
