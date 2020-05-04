package com.estadisticas.servicios;

import accesoDatos.AccesoDatos;
import com.estadisticas.entidades.Estadisticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiciosEstadisticas {

    public static List<Estadisticas> estadisticasTitulosPorAnio() throws Exception {
        List<Estadisticas> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Estadisticas estadisticas;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_estadisticas_titulos_por_anio();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                estadisticas = new Estadisticas();
                estadisticas.setTotalInt(resultSet.getInt("total")); //muestra el total de titulos
                estadisticas.setIndice(resultSet.getInt("anio")); //muestra el año 
                lst.add(estadisticas);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Estadisticas> titulosEmitidosPendientesRecaudados() throws Exception {
        List<Estadisticas> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Estadisticas estadisticas;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_titulos_emitidos_pendientes_recaudados();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                estadisticas = new Estadisticas();
                estadisticas.setIndice(resultSet.getInt("int_anio_titulo"));// anio
                estadisticas.setIndice1(resultSet.getInt("emitidos"));//total de titulos emitidos
                estadisticas.setIndice2(resultSet.getInt("pendientes"));//total de titulos pendientes
                estadisticas.setIndice3(resultSet.getInt("recaudados"));//total de titulos recaudados
                estadisticas.setTotal2(resultSet.getDouble("valor_pendiente"));// valor pendiente
                estadisticas.setTotal1(resultSet.getDouble("valor_recaudado"));// valor recaudado
                estadisticas.setTotal3(resultSet.getDouble("recaudar"));// total por recuadar
                lst.add(estadisticas);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
