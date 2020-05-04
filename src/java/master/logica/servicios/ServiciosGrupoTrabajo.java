package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.GrupoTrabajo;
import master.logica.entidades.Usuario;

public class ServiciosGrupoTrabajo {

    public static GrupoTrabajo obtenerGrupoDadoCodigo(int codigo) throws Exception {
        GrupoTrabajo grupo = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_grupos_trabajo_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                grupo = new GrupoTrabajo();
                grupo.setSessionUsuario(new Usuario());
                grupo.setIdGrupoTrabajo(resultSet.getInt("sr_id_grupo"));
                grupo.setGrupo(resultSet.getString("chv_grupo"));
                grupo.setDescripcion(resultSet.getString("chv_descripcion"));
                grupo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                grupo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                grupo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                grupo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                grupo.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return grupo;
    }

    public static ArrayList<GrupoTrabajo> obtenerGruposTrabajo() throws Exception {
        ArrayList<GrupoTrabajo> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        GrupoTrabajo grupo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from master.f_seleccionar_grupos_trabajo();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                grupo = new GrupoTrabajo();
                grupo.setSessionUsuario(new Usuario());
                grupo.setIdGrupoTrabajo(resultSet.getInt("sr_id_grupo"));
                grupo.setGrupo(resultSet.getString("chv_grupo"));
                grupo.setDescripcion(resultSet.getString("chv_descripcion"));
                grupo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                grupo.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                grupo.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                grupo.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                grupo.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                lst.add(grupo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarGrupoTrabajo(GrupoTrabajo grupo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_grupo_trabajo(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, grupo.getGrupo());
            prstm.setString(2, grupo.getDescripcion());
            prstm.setInt(3, grupo.getSessionUsuario().getIdPersona());
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

    public static String editarGrupoTrabajo(GrupoTrabajo grupo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_grupo_trabajo(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, grupo.getGrupo());
            prstm.setString(2, grupo.getDescripcion());
            prstm.setInt(3, grupo.getSessionUsuario().getIdPersona());
            prstm.setInt(4, grupo.getIdGrupoTrabajo());
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

    public static String eliminarGrupoTrabajo(GrupoTrabajo grupo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_grupo_trabajo(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, grupo.getSessionUsuario().getIdPersona());
            prstm.setInt(2, grupo.getIdGrupoTrabajo());
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
