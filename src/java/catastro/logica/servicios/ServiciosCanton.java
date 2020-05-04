/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Canton;
import catastro.logica.entidades.Provincia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosCanton {

    public static ArrayList<Canton> obtenerCantones() throws Exception {
        ArrayList<Canton> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Canton canton;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_cantones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                canton = new Canton();
                canton.setProvincia(new Provincia());
                canton.setProvincia(ServiciosProvincia.obtenerProvinciaDadoCodigo(resultSet.getInt("int_id_provincia")));
                canton.setIdCanton(resultSet.getInt("sr_id_canton"));
                canton.setNombre(resultSet.getString("chv_nombre"));
                canton.setCodigo(resultSet.getString("chv_codigo"));
                canton.setDescripcion(resultSet.getString("chv_descripcion"));
                canton.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                canton.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                canton.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                canton.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(canton);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Canton> obtenerCantonesDadoProvincia(int provincia) throws Exception {
        ArrayList<Canton> lst = new ArrayList<Canton>();
        Canton canton;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_seleccionar_cantones_dado_provincia(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, provincia);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                canton = new Canton();
                canton.setProvincia(new Provincia());
                canton.setProvincia(ServiciosProvincia.obtenerProvinciaDadoCodigo(resultSet.getInt("int_id_provincia")));
                canton.setIdCanton(resultSet.getInt("sr_id_canton"));
                canton.setNombre(resultSet.getString("chv_nombre"));
                canton.setCodigo(resultSet.getString("chv_codigo"));
                canton.setDescripcion(resultSet.getString("chv_descripcion"));
                canton.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                canton.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                canton.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                canton.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(canton);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Canton obtenerCantonDadoCodigo(int codigo) throws Exception {
        Canton canton = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_cantones_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                canton = new Canton();
                canton.setProvincia(new Provincia());
                canton.setProvincia(ServiciosProvincia.obtenerProvinciaDadoCodigo(resultSet.getInt("int_id_provincia")));
                canton.setIdCanton(resultSet.getInt("sr_id_canton"));
                canton.setNombre(resultSet.getString("chv_nombre"));
                canton.setCodigo(resultSet.getString("chv_codigo"));
                canton.setDescripcion(resultSet.getString("chv_descripcion"));
                canton.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                canton.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                canton.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                canton.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return canton;
    }
}
