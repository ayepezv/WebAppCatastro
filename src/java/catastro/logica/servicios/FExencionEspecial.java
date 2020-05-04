
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.ExencionEspecial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FExencionEspecial {
       public static List<ExencionEspecial> obtenerExEspecial() throws Exception {
        List<ExencionEspecial> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        ExencionEspecial coeficiente;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_exenciones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                coeficiente = new ExencionEspecial();
                coeficiente.setIdExccencion(resultSet.getInt("sr_id_excencion"));
                coeficiente.setExcencion(resultSet.getString("chv_excencion"));
                coeficiente.setDescripcion(resultSet.getString("chv_descripcion"));
                coeficiente.setPorcentajeTexto(resultSet.getString("chv_porcentaje"));
                coeficiente.setPorcentajeNumerico(resultSet.getDouble("db_porcentaje"));
                coeficiente.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                coeficiente.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                coeficiente.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                coeficiente.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                coeficiente.setFormaUso(resultSet.getString("ts_forma_uso"));
                lst.add(coeficiente);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
        public static String registrarExEspecial(ExencionEspecial coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_t_excenciones(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, coeficiente.getExcencion());
            prstm.setString(2, coeficiente.getDescripcion());
            prstm.setString(3, coeficiente.getPorcentajeTexto());
            prstm.setDouble(4, coeficiente.getPorcentajeNumerico());
            prstm.setString(5, coeficiente.getFormaUso());
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

    public static String actualizarExEspecial(ExencionEspecial coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_t_excenciones(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            
            prstm.setString(1, coeficiente.getExcencion());
            prstm.setString(2, coeficiente.getDescripcion());
            prstm.setString(3, coeficiente.getPorcentajeTexto());
            prstm.setDouble(4, coeficiente.getPorcentajeNumerico());
            prstm.setInt(5, coeficiente.getIdExccencion());
            prstm.setString(6, coeficiente.getFormaUso());
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

    public static String eliminarExEspecial(ExencionEspecial coeficiente) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_t_excenciones(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, coeficiente.getIdExccencion());
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
