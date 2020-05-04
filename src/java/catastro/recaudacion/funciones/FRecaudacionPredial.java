package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.RecaudacionPredial;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FRecaudacionPredial {

    public static List<RecaudacionPredial> obtenerRecaudaciones() throws Exception {
        List<RecaudacionPredial> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        RecaudacionPredial recaudacion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_recaudacion_predial();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                recaudacion = new RecaudacionPredial();
                recaudacion.setId(resultSet.getInt("sr_id_recaudacion"));
                recaudacion.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                recaudacion.setAvaluoCatastral(resultSet.getDouble("avaluo_catastral"));
                recaudacion.setExenciones(resultSet.getDouble("db_exenciones_rebajas"));
                recaudacion.setImpuestoPredial(resultSet.getDouble("db_impuesto_predial"));
                recaudacion.setCuerpoBomberos(resultSet.getDouble("db_cuerpo_bomberos"));
                recaudacion.setSolarNoEdificado(resultSet.getDouble("db_solar_no_edificado"));
                recaudacion.setProcesamiento(resultSet.getDouble("db_procesamiento"));
                recaudacion.setDescuentos(resultSet.getDouble("db_descuentos"));
                recaudacion.setIntereses(resultSet.getDouble("db_intereses"));
                recaudacion.setTotal(resultSet.getDouble("db_total"));
                recaudacion.setAnioEmision(resultSet.getInt("int_anio_emision"));
                recaudacion.setEmitida(resultSet.getString("chv_emitida"));
                recaudacion.setPagada(resultSet.getString("chv_pagada"));
                recaudacion.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                /*
                 recaudacion.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                 recaudacion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                 recaudacion.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));*/
                lst.add(recaudacion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
