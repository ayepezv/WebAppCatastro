package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Modulo;

public class ServiciosModulo {

    public static Modulo obtenerModuloDadoCodigo(int codigo) throws Exception {
        Modulo modulo = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from  master.f_seleccionar_modulo_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                modulo = new Modulo();
                modulo.setIdModulo(resultSet.getInt("int_id_modulo"));
                modulo.setDescripcion(resultSet.getString("chv_descripcion"));
                modulo.setNombre(resultSet.getString("chv_nombre"));
                modulo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                modulo.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                modulo.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                modulo.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                modulo.setIcono(resultSet.getString("chv_icono"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return modulo;
    }

    public static ArrayList<Modulo> obtenerModulosDadoEstado(String estado) throws Exception {
        ArrayList<Modulo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Modulo modulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_modulo_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                modulo = new Modulo();
                modulo.setIdModulo(resultSet.getInt("int_id_modulo"));
                modulo.setNombre(resultSet.getString("chv_nombre"));
                modulo.setDescripcion(resultSet.getString("chv_descripcion"));
                modulo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                modulo.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                modulo.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                modulo.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                modulo.setIcono(resultSet.getString("chv_icono"));
                lst.add(modulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarModulo(Modulo modulo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_modulo(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, modulo.getDescripcion());
            prstm.setString(2, modulo.getNombre());
            prstm.setInt(3, modulo.getSessionUsuario().getIdPersona());
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

    public static String actualizarModulo(Modulo modulo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_modulo(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, modulo.getDescripcion());
            prstm.setString(2, modulo.getNombre());
            prstm.setInt(3, modulo.getIdModulo());
            prstm.setInt(4, modulo.getSessionUsuario().getIdPersona());
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
    
    public static String eliminarModulo(int modulo, Modulo modulos) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_modulo(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);            
            prstm.setInt(1, modulo);
            prstm.setInt(2, modulos.getSessionUsuario().getIdPersona());
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
    
    public static String activarModulo(int modulo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_activar_modulo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);            
            prstm.setInt(1, modulo);
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
