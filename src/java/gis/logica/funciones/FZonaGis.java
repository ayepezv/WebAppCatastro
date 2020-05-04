package gis.logica.funciones;

import accesoDatos.AccesoDatos;
import gis.logica.entidades.GisZona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FZonaGis {

    public static List<GisZona> obtenerZonas() throws Exception {
        List<GisZona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        GisZona zona;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from topology.f_obtener_zonas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                zona = new GisZona();
                zona.setId(resultSet.getInt("id"));
                zona.setObjectId(resultSet.getInt("objectid"));
                zona.setArea(resultSet.getDouble("area"));
                zona.setZona(resultSet.getInt("zona"));
                lst.add(zona);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<GisZona> obtenerCoordenadas() throws Exception {
        List<GisZona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        GisZona zona;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from topology.f_obtener_coordenadas_zona_v2();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                zona = new GisZona();
                zona.setId(resultSet.getInt("pid"));
                zona.setCoordX(resultSet.getDouble("coord_x_geog"));
                zona.setCoordY(resultSet.getDouble("coord_y_geog"));
                zona.setObjectId(resultSet.getInt("pobjectid"));
                zona.setArea(resultSet.getDouble("parea"));
                zona.setZona(resultSet.getInt("pzona"));
                lst.add(zona);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<GisZona> obtenerCoordenadasDadoCodigoZona(int codigo) throws Exception {
        List<GisZona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        GisZona zona;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from topology.f_obtener_coordenadas_zona_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                zona = new GisZona();
                zona.setId(resultSet.getInt("pid"));
                zona.setCoordX(resultSet.getDouble("coord_x_geog"));
                zona.setCoordY(resultSet.getDouble("coord_y_geog"));
                zona.setObjectId(resultSet.getInt("pobjectid"));
                zona.setArea(resultSet.getDouble("parea"));
                zona.setZona(resultSet.getInt("pzona"));
                lst.add(zona);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
