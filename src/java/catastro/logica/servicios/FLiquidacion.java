/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Liquidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gcudcop
 */
public class FLiquidacion {

    public static List<Liquidacion> obtenerLiquidaciones() throws Exception {
        List<Liquidacion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Liquidacion liquidacion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_liquidaciones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setIdLiquidacion(resultSet.getInt("sr_id_liquidacion"));
                liquidacion.setAnio(resultSet.getInt("int_anio"));
                liquidacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                liquidacion.setCostoPiso1(resultSet.getDouble("db_costo_piso_1"));
                liquidacion.setCostoPiso2(resultSet.getDouble("db_costo_piso_2"));
                liquidacion.setCostoPiso3(resultSet.getDouble("db_costo_piso_3"));
                liquidacion.setCostoPiso4(resultSet.getDouble("db_costo_piso_4"));
                liquidacion.setTotalConstruccion(resultSet.getDouble("db_total_costruccion"));
                liquidacion.setTotalUsoSuelo(resultSet.getDouble("db_total_uso_suelo"));
                liquidacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                liquidacion.setAvaluo(resultSet.getDouble("db_avaluo"));
                liquidacion.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                liquidacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(liquidacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Liquidacion> obtenerLiquidacionDadoClaveCatastral(String claveCatastral) throws Exception {
        List<Liquidacion> lst = new ArrayList<>();
        Liquidacion liquidacion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_liquidaciones_dado_clave_catastral(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, claveCatastral);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setIdLiquidacion(resultSet.getInt("sr_id_liquidacion"));
                liquidacion.setAnio(resultSet.getInt("int_anio"));
                liquidacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                liquidacion.setCostoPiso1(resultSet.getDouble("db_costo_piso_1"));
                liquidacion.setCostoPiso2(resultSet.getDouble("db_costo_piso_2"));
                liquidacion.setCostoPiso3(resultSet.getDouble("db_costo_piso_3"));
                liquidacion.setCostoPiso4(resultSet.getDouble("db_costo_piso_4"));
                liquidacion.setTotalConstruccion(resultSet.getDouble("db_total_costruccion"));
                liquidacion.setTotalUsoSuelo(resultSet.getDouble("db_total_uso_suelo"));
                liquidacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                liquidacion.setAvaluo(resultSet.getDouble("db_avaluo"));
                liquidacion.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                liquidacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(liquidacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Liquidacion> obtenerLiquidacionDadoPropietario(String propietario) throws Exception {
        List<Liquidacion> lst = new ArrayList<>();
        Liquidacion liquidacion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_liquidaciones_dado_propietario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, propietario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setIdLiquidacion(resultSet.getInt("sr_id_liquidacion"));
                liquidacion.setAnio(resultSet.getInt("int_anio"));
                liquidacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                liquidacion.setCostoPiso1(resultSet.getDouble("db_costo_piso_1"));
                liquidacion.setCostoPiso2(resultSet.getDouble("db_costo_piso_2"));
                liquidacion.setCostoPiso3(resultSet.getDouble("db_costo_piso_3"));
                liquidacion.setCostoPiso4(resultSet.getDouble("db_costo_piso_4"));
                liquidacion.setTotalConstruccion(resultSet.getDouble("db_total_costruccion"));
                liquidacion.setTotalUsoSuelo(resultSet.getDouble("db_total_uso_suelo"));
                liquidacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                liquidacion.setAvaluo(resultSet.getDouble("db_avaluo"));
                liquidacion.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                liquidacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(liquidacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Liquidacion> obtenerLiquidacionesDadoZonaSectorManzana(int zona, int sector, int manzana, int numeroPredio) throws Exception {
        List<Liquidacion> lst = new ArrayList<>();
        Liquidacion liquidacion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_liquidaciones_dado_zona_sector_manzana(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, numeroPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setIdLiquidacion(resultSet.getInt("sr_id_liquidacion"));
                liquidacion.setAnio(resultSet.getInt("int_anio"));
                liquidacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                liquidacion.setCostoPiso1(resultSet.getDouble("db_costo_piso_1"));
                liquidacion.setCostoPiso2(resultSet.getDouble("db_costo_piso_2"));
                liquidacion.setCostoPiso3(resultSet.getDouble("db_costo_piso_3"));
                liquidacion.setCostoPiso4(resultSet.getDouble("db_costo_piso_4"));
                liquidacion.setTotalConstruccion(resultSet.getDouble("db_total_costruccion"));
                liquidacion.setTotalUsoSuelo(resultSet.getDouble("db_total_uso_suelo"));
                liquidacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                liquidacion.setAvaluo(resultSet.getDouble("db_avaluo"));
                liquidacion.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                liquidacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(liquidacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Liquidacion> obtenerLiquidacionesDadoClaveAnterior(int zona, int sector, int manzana, int numeroPredio, int propAnt, int derAcc) throws Exception {
        List<Liquidacion> lst = new ArrayList<>();
        Liquidacion liquidacion;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_encontrar_predios_por_clave_anterior(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, numeroPredio);
            prstm.setInt(5, propAnt);
            prstm.setInt(6, derAcc);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setIdLiquidacion(resultSet.getInt("sr_id_liquidacion"));
                liquidacion.setAnio(resultSet.getInt("int_anio"));
                liquidacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                liquidacion.setCostoPiso1(resultSet.getDouble("db_costo_piso_1"));
                liquidacion.setCostoPiso2(resultSet.getDouble("db_costo_piso_2"));
                liquidacion.setCostoPiso3(resultSet.getDouble("db_costo_piso_3"));
                liquidacion.setCostoPiso4(resultSet.getDouble("db_costo_piso_4"));
                liquidacion.setTotalConstruccion(resultSet.getDouble("db_total_costruccion"));
                liquidacion.setTotalUsoSuelo(resultSet.getDouble("db_total_uso_suelo"));
                liquidacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                liquidacion.setAvaluo(resultSet.getDouble("db_avaluo"));
                liquidacion.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                liquidacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(liquidacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
