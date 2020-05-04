package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import master.logica.entidades.Menu;
import master.logica.entidades.Modulo;

public class ServiciosMenu {

    public static ArrayList<Menu> obtenerGruposMenusNivelCeroDadoRol(int codRol) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {

            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_menu_cero_dado_rol(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("id_menu"));
                menu.setNombre(resultSet.getString("nombre_menu"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerGruposMenusDadoPadre(int codigoPadre) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_menu_cero_dado_rol(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPadre);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("id_menu"));
                menu.setNombre(resultSet.getString("nombre_menu"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerSubmenusDadoRolUsuarioGrupoMenuCero(int idRol, int idUsuario, int idMenuCero) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_submenus_dado_rol_menu_cero(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idRol);
            prstm.setInt(2, idUsuario);
            prstm.setInt(3, idMenuCero);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("id_submenu"));
                menu.setNombre(resultSet.getString("submenu"));
                menu.setIcono(resultSet.getString("icono_submenu"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerSubmenusDadoRol(int idRol) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_submenus_dado_rol_2(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("id_submenu"));
                menu.setNombre(resultSet.getString("submenu"));
                menu.setIcono(resultSet.getString("icono_submenu"));
                menu.setOrden(resultSet.getInt("int_orden"));
                menu.setIndex(resultSet.getString("ch_index"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerItemsDadoSubmenu(int idSubmenu) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_items_menu_dado_submenu(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idSubmenu);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("sr_id_menu"));
                menu.setNombre(resultSet.getString("chv_nombre"));
                menu.setIcono(resultSet.getString("chv_icono"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerMenusModulos(String estado) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_menus_cero_modulo_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("sr_id_menu"));
                menu.setNombre(resultSet.getString("chv_nombre"));
                menu.setDescripcion(resultSet.getString("chv_descripcion"));
                menu.setIcono(resultSet.getString("chv_icono"));
                menu.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                menu.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                menu.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                menu.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Menu> obtenerMenusDadoPadreEstado(int codigoPadre, String estado) throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_menu_dado_padre_estado(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoPadre);
            prstm.setString(2, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("sr_id_menu"));
                menu.setNombre(resultSet.getString("chv_nombre"));
                menu.setDescripcion(resultSet.getString("chv_descripcion"));
                menu.setIcono(resultSet.getString("chv_icono"));
                menu.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                menu.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                menu.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                menu.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                menu.setCodigoPadre(resultSet.getInt("int_menu_padre"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Menu obtenerMenuDadoCodigo(int codigo) throws Exception {
        Menu menu = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_menu_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("sr_id_menu"));
                menu.setNombre(resultSet.getString("chv_nombre"));
                menu.setDescripcion(resultSet.getString("chv_descripcion"));
                menu.setIcono(resultSet.getString("chv_icono"));
                menu.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                menu.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                menu.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                menu.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                menu.setCodigoPadre(resultSet.getInt("int_menu_padre"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return menu;
    }

    public static ArrayList<Menu> obtenerNodoSubmenus() throws Exception {
        ArrayList<Menu> lst = new ArrayList<Menu>();
        Menu menu;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_nodo_submenus()";
            prstm = accesoDatos.creaPreparedSmt(sql);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                menu = new Menu();
                menu.setIdMenu(resultSet.getInt("id_submenu"));
                menu.setNombre(resultSet.getString("submenu"));
                menu.setIcono(resultSet.getString("icono_submenu"));
                lst.add(menu);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    /// REGISTRAR MENU NIVEL CERO DADO MODULO
    public static String registrarMenuCeroDadoModulo(Menu menu, Modulo modulo, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_modulo_menu_cero(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, menu.getNombre());
            prstm.setString(2, menu.getDescripcion());
            prstm.setInt(3, modulo.getIdModulo());
            prstm.setInt(4, idUsuario);
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

    /// registrar menu dado padre
    public static String registrarMenuDadoPadre(Menu menu, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_menu_dado_padre(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, menu.getNombre());
            prstm.setString(2, menu.getDescripcion());
            prstm.setInt(3, menu.getCodigoPadre());
            prstm.setInt(4, idUsuario);
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

    public static String actualizarMenu(Menu menu, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_menu(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, menu.getNombre());
            prstm.setString(2, menu.getDescripcion());
            prstm.setInt(3, menu.getIdMenu());
            prstm.setInt(4, idUsuario);
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

    public static String actualizarMenuGenerico(Menu menu) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.t_actualizar_menu_dado_generico(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, menu.getNombre());
            prstm.setString(2, menu.getDescripcion());
            prstm.setInt(3, menu.getSessionUsuario().getIdPersona());
            prstm.setInt(4, menu.getIdMenu());
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

    public static String eliminarMenuGenerico(Menu menu) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.t_eliminar_menu(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, menu.getIdMenu());
            prstm.setInt(2, menu.getSessionUsuario().getIdPersona());
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
