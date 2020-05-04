package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Canton;
import catastro.logica.entidades.Parroquia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiciosParroquia {

    public static ArrayList<Parroquia> obtenerParroquiasArchidona() throws Exception {
        ArrayList<Parroquia> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Parroquia parroquia;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_parroquias_archidona();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                parroquia = new Parroquia();
                parroquia.setCanton(new Canton());
                parroquia.setCanton(ServiciosCanton.obtenerCantonDadoCodigo(resultSet.getInt("int_id_canton")));
                parroquia.setIdParroquia(resultSet.getInt("sr_id_parroquia"));
                parroquia.setNombre(resultSet.getString("chv_nombre"));
                parroquia.setCodigo(resultSet.getString("chv_codigo"));
                parroquia.setDescripcion(resultSet.getString("chv_descripcion"));
                parroquia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                parroquia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                parroquia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                parroquia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(parroquia);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Parroquia obtenerParroquiaDadoCodigo(int codigo) throws Exception {
        Parroquia parroquia = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_parroquia_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                parroquia = new Parroquia();
                parroquia.setCanton(new Canton());
                parroquia.setCanton(ServiciosCanton.obtenerCantonDadoCodigo(resultSet.getInt("int_id_canton")));
                parroquia.setIdParroquia(resultSet.getInt("sr_id_parroquia"));
                parroquia.setNombre(resultSet.getString("chv_nombre"));
                parroquia.setCodigo(resultSet.getString("chv_codigo"));
                parroquia.setDescripcion(resultSet.getString("chv_descripcion"));
                parroquia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                parroquia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                parroquia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                parroquia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return parroquia;
    }
    
    
    
    public static Parroquia obtenerParroquiaDadoCodigoString(String codigo) throws Exception {
        Parroquia parroquia = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_parroquia_dado_codigo_chv(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                parroquia = new Parroquia();
                parroquia.setCanton(new Canton());
                parroquia.setCanton(ServiciosCanton.obtenerCantonDadoCodigo(resultSet.getInt("int_id_canton")));
                parroquia.setIdParroquia(resultSet.getInt("sr_id_parroquia"));
                parroquia.setNombre(resultSet.getString("chv_nombre"));
                parroquia.setCodigo(resultSet.getString("chv_codigo"));
                parroquia.setDescripcion(resultSet.getString("chv_descripcion"));
                parroquia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                parroquia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                parroquia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                parroquia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return parroquia;
    }
}
