/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Zona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosZona {

    public static ArrayList<Zona> obtenerZonas() throws Exception {
        ArrayList<Zona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Zona zona;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_zonas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                zona = new Zona();
                zona.setIdZona(resultSet.getInt("sr_id_zona"));
                zona.setNombre(resultSet.getString("chv_nombre"));
                zona.setCodigo(resultSet.getString("chv_codigo"));
                zona.setDescripcion(resultSet.getString("chv_descripcion"));
                zona.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                zona.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                zona.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                zona.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(zona);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Zona obtenerZonaDadoCodigo(int codigo) throws Exception {
        Zona zona = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_zona_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                zona = new Zona();
                zona.setIdZona(resultSet.getInt("sr_id_zona"));
                zona.setNombre(resultSet.getString("chv_nombre"));
                zona.setCodigo(resultSet.getString("chv_codigo"));
                zona.setDescripcion(resultSet.getString("chv_descripcion"));
                zona.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                zona.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                zona.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                zona.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return zona;
    }
}
