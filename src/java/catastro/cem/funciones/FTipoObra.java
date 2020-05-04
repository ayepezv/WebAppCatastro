package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.TipoObra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FTipoObra {

    public static List<TipoObra> obtenerTiposObras() throws Exception {
        List<TipoObra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        TipoObra tipoObra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_tipo_obras();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipoObra = new TipoObra();
                tipoObra.setIdTipoObra(resultSet.getInt("sr_id_tipo_obra"));
                tipoObra.setTipoObra(resultSet.getString("chv_tipo_obra"));
                tipoObra.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoObra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoObra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoObra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoObra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                tipoObra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_usuario")));
                lst.add(tipoObra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static List<TipoObra> obtenerTiposObrasInactivas() throws Exception {
        List<TipoObra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        TipoObra tipoObra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_tipo_obras_inactivas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                tipoObra = new TipoObra();
                tipoObra.setIdTipoObra(resultSet.getInt("sr_id_tipo_obra"));
                tipoObra.setTipoObra(resultSet.getString("chv_tipo_obra"));
                tipoObra.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoObra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoObra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoObra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoObra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                tipoObra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_usuario")));
                lst.add(tipoObra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }


    public static TipoObra obtenerTipoObraDadoCodigo(int codigo) throws Exception {
        TipoObra tipoObra = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_tipo_obra_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                tipoObra = new TipoObra();
                tipoObra.setIdTipoObra(resultSet.getInt("sr_id_tipo_obra"));
                tipoObra.setTipoObra(resultSet.getString("chv_tipo_obra"));
                tipoObra.setDescripcion(resultSet.getString("chv_descripcion"));
                tipoObra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tipoObra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tipoObra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                tipoObra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                tipoObra.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return tipoObra;
    }

    public static String registrarTipoObra(TipoObra tipoObra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_tipo_obra(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, tipoObra.getTipoObra());
            prstm.setString(2, tipoObra.getDescripcion());
            prstm.setInt(3, tipoObra.getSessionUsuario().getIdPersona());
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

    public static String actualizarTipoObra(TipoObra tipoObra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_tipo_obra(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, tipoObra.getTipoObra());
            prstm.setString(2, tipoObra.getDescripcion());
            prstm.setInt(3, tipoObra.getIdTipoObra());
            prstm.setInt(4, tipoObra.getSessionUsuario().getIdPersona());
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

    public static String desacticarTipoObra(int idTipoObra,int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_tipo_obra(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idTipoObra);
            prstm.setInt(2, idUsuario);
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
