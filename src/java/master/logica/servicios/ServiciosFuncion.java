package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Accion;
import master.logica.entidades.Funcion;
import master.logica.entidades.Menu;
import master.logica.entidades.Rol;

public class ServiciosFuncion {
    
    public static ArrayList<Funcion> obtenerFuncionesDadoRolPadreGruposMenus(int codRol, int codPadre) throws Exception {
        ArrayList<Funcion> lst = new ArrayList<Funcion>();
        Funcion funcion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_funciones_dado_rol_menu_padre(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codRol);
            prstm.setInt(2, codPadre);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                funcion = new Funcion();
                funcion.setCodigoAccion(new Accion());
                funcion.setCodigoMenu(new Menu());
                funcion.setCodigoRol(new Rol());
                
                funcion.getCodigoAccion().setIdAccion(resultSet.getInt("codigo_accion"));
                funcion.getCodigoAccion().setTitulo(resultSet.getString("titulo_accion"));
                funcion.getCodigoAccion().setUrl(resultSet.getString("url"));
                
                funcion.getCodigoMenu().setIdMenu(resultSet.getInt(sql));
                funcion.getCodigoMenu().setNombre(resultSet.getString(sql));
                
                funcion.getCodigoRol().setIdRol(resultSet.getInt("int_id_rol"));
                funcion.getCodigoRol().setNombre(resultSet.getString("rol"));
                
                lst.add(funcion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static Funcion obtenerFuncionDadoIdMenu(int idMenu) throws Exception {
        Funcion funcion = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_seleccionar_funcion_dado_menu_item(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idMenu);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                funcion = new Funcion();
                funcion.setCodigoAccion(new Accion());
                funcion.setCodigoMenu(new Menu());
                funcion.setCodigoRol(new Rol());
                //seteo valores de la accion URL
                funcion.getCodigoAccion().setIdAccion(resultSet.getInt("id_accion_url"));
                funcion.getCodigoAccion().setTitulo(resultSet.getString("titulo_url"));
                funcion.getCodigoAccion().setUrl(resultSet.getString("url"));
                //seteo valores del Menu item
                funcion.getCodigoMenu().setIdMenu(resultSet.getInt("id_menu_item"));
                funcion.getCodigoMenu().setNombre(resultSet.getString("menu_item"));
                //seteo valores rol
                funcion.getCodigoRol().setIdRol(resultSet.getInt("id_rol"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return funcion;
    }
    
    
    public static ArrayList<Funcion> obtenerFuncionesDadoEstado(String estado) throws Exception {
        ArrayList<Funcion> lst = new ArrayList<Funcion>();
        Funcion funcion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_selecionar_funciones(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);            
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                funcion = new Funcion();
                funcion.setCodigoAccion(new Accion());
                funcion.setCodigoMenu(new Menu());
                funcion.setCodigoRol(new Rol());
                //URL
                funcion.getCodigoAccion().setIdAccion(resultSet.getInt("int_codigo_accion"));
                funcion.getCodigoAccion().setTitulo(resultSet.getString("titulo_url"));
                funcion.getCodigoAccion().setUrl(resultSet.getString("url_item"));
                //item
                funcion.getCodigoMenu().setIdMenu(resultSet.getInt("id_item"));
                funcion.getCodigoMenu().setNombre(resultSet.getString("item_menu"));
                //rol
                funcion.getCodigoRol().setIdRol(resultSet.getInt("int_codigo_rol"));
                funcion.getCodigoRol().setNombre(resultSet.getString("rol"));
                //datos adicionales
                funcion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                funcion.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                funcion.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                funcion.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                funcion.setOrden(resultSet.getInt("orden"));
                lst.add(funcion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    
     public static String registrarFuncion(int accion,int rol, int menu, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_funcion(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, accion);
            prstm.setInt(2, rol);
            prstm.setInt(3, menu);
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
}
