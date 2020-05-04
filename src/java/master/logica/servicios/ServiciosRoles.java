package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Modulo;
import master.logica.entidades.Rol;

public class ServiciosRoles {

    public static Rol obtenerRolDadoCodigo(int codigo) throws Exception {
        Rol rol = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from  master.f_select_rol_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdModulo(new Modulo());
                rol.getIdModulo().setIdModulo(resultSet.getInt("int_id_modulo"));
                rol.setIdRol(resultSet.getInt("int_id_rol"));
                rol.setNombre(resultSet.getString("chv_nombre"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return rol;
    }

    public static ArrayList<Rol> obtenerRolesDadoEstado(String estado) throws Exception {
        ArrayList<Rol> lst = new ArrayList<Rol>();
        Rol rol;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdModulo(new Modulo());
                rol.setIdModulo(ServiciosModulo.obtenerModuloDadoCodigo(resultSet.getInt("int_id_modulo")));
                rol.setIdRol(resultSet.getInt("int_id_rol"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
                rol.setNombre(resultSet.getString("chv_nombre"));
                rol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rol.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                rol.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                rol.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                rol.setIcono(resultSet.getString("chv_icono"));
                lst.add(rol);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Rol obtenerRolDadoCodigoMenuCero(int codigo) throws Exception {
        Rol rol = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_selecionar_rol__dado_menu_modulo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdModulo(new Modulo());
                rol.setIdModulo(ServiciosModulo.obtenerModuloDadoCodigo(resultSet.getInt("int_id_modulo")));
                rol.setIdRol(resultSet.getInt("int_id_rol"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
                rol.setNombre(resultSet.getString("chv_nombre"));
                rol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rol.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                rol.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                rol.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                rol.setIcono(resultSet.getString("chv_icono"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return rol;
    }

    
    
    
    public static String registrarRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_rol(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, rol.getDescripcion());
            prstm.setString(2, rol.getNombre());
            prstm.setInt(3, rol.getIdModulo().getIdModulo());
            prstm.setInt(4,rol.getSessionUsuario().getIdPersona());
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
    
    public static String actualizarRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_rol(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, rol.getDescripcion());
            prstm.setString(2, rol.getNombre());
            prstm.setInt(3, rol.getIdModulo().getIdModulo());
            prstm.setInt(4, rol.getIdRol());
            prstm.setInt(5, rol.getSessionUsuario().getIdPersona());
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
    
    public static String elimianrRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_rol(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);          
            prstm.setInt(1, rol.getIdRol());
            prstm.setInt(2, rol.getSessionUsuario().getIdPersona());
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
    
    // actualizar imagen rol
    public static String actualizarIcono(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_imagen_rol(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);          
            prstm.setInt(1, rol.getIdRol());
            prstm.setString(2, rol.getIcono());
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
