/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Rol;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny
 */
public class ServiciosRolUsuario {

    public static ArrayList<RolUsuario> listarRolesUsuario(int idUsuario) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.getIdRol().setIdRol(resultSet.getInt("int_id_rol"));
                rolUsuario.getIdRol().setNombre(resultSet.getString("rol"));
                rolUsuario.getIdRol().setIcono(resultSet.getString("icono_rol"));
                rolUsuario.getIdUsuario().setIdPersona(resultSet.getInt("int_id_usuario"));
                rolUsuario.getIdRol().getIdModulo().setNombre(resultSet.getString("modulo"));
                rolUsuario.getIdRol().getIdModulo().setIdModulo(resultSet.getInt("int_id_modulo"));

                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<RolUsuario> listarRolesUsuarioDadoEstado(String estado) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_usuarios_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                rolUsuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rolUsuario.setSeleccionar(resultSet.getInt("seleccionar"));
                rolUsuario.setInsertar(resultSet.getInt("insertar"));
                rolUsuario.setActualizar(resultSet.getInt("actualizar"));
                rolUsuario.setEliminar(resultSet.getInt("eliminar"));
                rolUsuario.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                rolUsuario.setStyleClass(resultSet.getString("style_class"));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarRolUsuario(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_rol_usuario(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getSeleccionar());
            prstm.setInt(4, ru.getInsertar());
            prstm.setInt(5, ru.getActualizar());
            prstm.setInt(6, ru.getEliminar());
            prstm.setInt(7, ru.getSessionUsuario().getIdPersona());
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

    public static String registrarUsuarioDadoRol(RolUsuario ru, int idSessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_persona_dado_rol(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, ru.getIdUsuario().getCedula());
            prstm.setString(2, ru.getIdUsuario().getNombres());
            prstm.setString(3, ru.getIdUsuario().getApellidos());
            prstm.setString(4, ru.getIdUsuario().getCelular());
            prstm.setString(5, ru.getIdUsuario().getSexo());
            prstm.setTimestamp(6, new java.sql.Timestamp(ru.getIdUsuario().getFechaNacimiento().getTime()));
            prstm.setString(7, ru.getIdUsuario().getEstadoCivil());
            prstm.setString(8, ru.getIdUsuario().getRuc());
            prstm.setString(9, ru.getIdUsuario().getDireccionDom());
            prstm.setString(10, ru.getIdUsuario().getNick());
            prstm.setString(11, ru.getIdUsuario().getMail());
            prstm.setInt(12, ru.getIdRol().getIdRol());
            prstm.setInt(13, idSessionUsuario);
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

    public static ArrayList<RolUsuario> listarRolesDadoUsuario(int idUsuario) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_dado_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                rolUsuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rolUsuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rolUsuario.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                rolUsuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rolUsuario.setSeleccionar(resultSet.getInt("seleccionar"));
                rolUsuario.setInsertar(resultSet.getInt("insertar"));
                rolUsuario.setActualizar(resultSet.getInt("actualizar"));
                rolUsuario.setEliminar(resultSet.getInt("eliminar"));
                rolUsuario.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                rolUsuario.setStyleClass(resultSet.getString("style_class"));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<RolUsuario> encontrarRolesDadoUsuario(String parametro) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_encontrar_roles_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                rolUsuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rolUsuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rolUsuario.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                rolUsuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rolUsuario.setSeleccionar(resultSet.getInt("seleccionar"));
                rolUsuario.setInsertar(resultSet.getInt("insertar"));
                rolUsuario.setActualizar(resultSet.getInt("actualizar"));
                rolUsuario.setEliminar(resultSet.getInt("eliminar"));
                rolUsuario.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                rolUsuario.setStyleClass(resultSet.getString("style_class"));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String cambiarPrivSelect(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_cambiar_privilegio_select(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getSeleccionar());
            prstm.setInt(4, ru.getSessionUsuario().getIdPersona());
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

    public static String cambiarPrivInsert(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_cambiar_privilegio_insert(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getInsertar());
            prstm.setInt(4, ru.getSessionUsuario().getIdPersona());
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

    public static String cambiarPrivUpdate(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_cambiar_privilegio_update(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getActualizar());
            prstm.setInt(4, ru.getSessionUsuario().getIdPersona());
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

    public static String cambiarPrivDelete(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_cambiar_privilegio_delete(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getEliminar());
            prstm.setInt(4, ru.getSessionUsuario().getIdPersona());
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

    public static RolUsuario obtenerRolUsuario(int rol, int usuario) throws Exception {
        RolUsuario rolUsuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_obtener_rol_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, rol);
            prstm.setInt(2, usuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                rolUsuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rolUsuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rolUsuario.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                rolUsuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rolUsuario.setSeleccionar(resultSet.getInt("seleccionar"));
                rolUsuario.setInsertar(resultSet.getInt("insertar"));
                rolUsuario.setActualizar(resultSet.getInt("actualizar"));
                rolUsuario.setEliminar(resultSet.getInt("eliminar"));
                rolUsuario.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return rolUsuario;
    }

    public static ArrayList<RolUsuario> listarRolesUsuarioDadoGrupoTrabajo(int idGrupoTrabajo) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_obtener_usuarios_dado_grupo_trabajo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idGrupoTrabajo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<RolUsuario> listarUsuariosDadoGrupoTrabajoUsuario(int idGrupoTrabajo, int idUsuario) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_obtener_usuarios_dado_grupo_trabajo_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idGrupoTrabajo);
            prstm.setInt(2, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String eliminarRolUsuario(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_rol_usuario(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            prstm.setInt(3, ru.getSessionUsuario().getIdPersona());
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

    public static ArrayList<RolUsuario> listarUsuariosDadoRol(int idRol) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_obtener_usuarios_dado_rol(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
