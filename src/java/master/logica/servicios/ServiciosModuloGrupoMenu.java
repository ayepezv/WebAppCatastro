package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Menu;
import master.logica.entidades.Modulo;
import master.logica.entidades.ModuloGrupoMenu;

public class ServiciosModuloGrupoMenu {
    
    public static ModuloGrupoMenu obtenerModuloGrupoMenuDadoCodigo(int codigo) throws Exception {
        ModuloGrupoMenu objModuloGrupoMenu = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_select_numero_grupo_menu_dado_modulo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                objModuloGrupoMenu = new ModuloGrupoMenu();
                objModuloGrupoMenu.setCodigoModulo(new Modulo());
                objModuloGrupoMenu.setCodigoMenu(new Menu());
                objModuloGrupoMenu.getCodigoMenu().setIdMenu(resultSet.getInt("int_id_menu"));
                objModuloGrupoMenu.getCodigoModulo().setIdModulo(resultSet.getInt("int_id_modulo"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return objModuloGrupoMenu;
    }
    
    public static ModuloGrupoMenu obtenerModuloGrupoMenuDadoRol(int rol) throws Exception {
        ModuloGrupoMenu objModuloGrupoMenu = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_grupo_menu_cero_dado_rol(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, rol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                objModuloGrupoMenu = new ModuloGrupoMenu();
                objModuloGrupoMenu.setCodigoModulo(new Modulo());
                objModuloGrupoMenu.setCodigoMenu(new Menu());
                
                objModuloGrupoMenu.getCodigoMenu().setIdMenu(resultSet.getInt("id_menu_cero"));
                objModuloGrupoMenu.getCodigoModulo().setIdModulo(resultSet.getInt("int_id_modulo"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return objModuloGrupoMenu;
    }
    
    public static ArrayList<ModuloGrupoMenu> obtenerModulosMenusDadoEstado(String estado) throws Exception {
        ArrayList<ModuloGrupoMenu> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        ModuloGrupoMenu modulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_modulo_grupo_menus_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                modulo = new ModuloGrupoMenu();                
                modulo.setCodigoMenu(new Menu());
                modulo.setCodigoModulo(new Modulo());                
                modulo.setCodigoMenu(ServiciosMenu.obtenerMenuDadoCodigo(resultSet.getInt("int_id_menu")));
                modulo.setCodigoModulo(ServiciosModulo.obtenerModuloDadoCodigo(resultSet.getInt("int_id_modulo")));
                modulo.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                modulo.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                modulo.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                modulo.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                lst.add(modulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    
    
    // REGISTRAR MENÚ CERO DADO MÓDULO
    
}
