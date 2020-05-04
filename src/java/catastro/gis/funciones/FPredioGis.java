package catastro.gis.funciones;

import accesoDatos.AccesoDatos;
import catastro.gis.entidades.PredioGis;
import catastro.logica.servicios.ServiciosParroquia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FPredioGis {

    public static List<PredioGis> obtenerPredioGisDadoClaveCatastral(String clave) throws Exception {
        List<PredioGis> lst = new ArrayList<>();
        PredioGis predio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM topology.f_seleccionar_predios_gis(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, clave);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                predio = new PredioGis();
                predio.setGid(resultSet.getInt("gid"));
                //predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigo(resultSet.getInt("parroquia")));
                predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigoString(resultSet.getString("parroquia")));
                predio.setZona(resultSet.getInt("zona"));
                predio.setSector(resultSet.getInt("sector"));
                predio.setManzana(resultSet.getInt("manzana"));
                predio.setNumPredio(resultSet.getInt("predio"));
                predio.setTipoLote(resultSet.getString("tipo_lote"));
                predio.setPropiedad(resultSet.getDouble("propiedad_"));
                predio.setDerechosAccion(resultSet.getDouble("derechos_a"));
                predio.setClaveCatastral(resultSet.getString("clave_cata"));
                predio.setPropietario(resultSet.getString("propietari"));
                predio.setArea(resultSet.getDouble("area"));
                predio.setPerimetro(resultSet.getDouble("perimetro"));
                predio.setCentroide(resultSet.getDouble("centroide_"));
                predio.setCentroid00(resultSet.getDouble("centroid00"));
                predio.setCentroid01(resultSet.getDouble("centroid01"));
                predio.setCentroid02(resultSet.getDouble("centroid02"));
                predio.setFotografia(resultSet.getString("fotografia"));
                predio.setHtml(resultSet.getString("html"));
                predio.setVinculado(resultSet.getString("vinculado"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_creacion"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predio.setBloque(resultSet.getInt("bloque"));
                predio.setPiso(resultSet.getInt("piso"));
                predio.setUnidad(resultSet.getInt("unidad"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioGis> obtenerPredios() throws Exception {
        List<PredioGis> lst = new ArrayList<>();
        PredioGis predio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from topology.f_obtener_predios_espaciales();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                predio = new PredioGis();
                predio.setGid(resultSet.getInt("gid"));
                // predio.setParroquia(ServiciosParroquia.obtenerParroquiaDadoCodigoString(resultSet.getString("parroquia")));
                predio.setZona(resultSet.getInt("zona"));
                predio.setSector(resultSet.getInt("sector"));
                predio.setManzana(resultSet.getInt("manzana"));
                predio.setNumPredio(resultSet.getInt("predio"));
                predio.setTipoLote(resultSet.getString("tipo_lote"));
                predio.setPropiedad(resultSet.getDouble("propiedad_"));
                predio.setDerechosAccion(resultSet.getDouble("derechos_a"));
                predio.setClaveCatastral(resultSet.getString("clave_cata"));
                predio.setPropietario(resultSet.getString("propietari"));
                predio.setArea(resultSet.getDouble("area"));
                predio.setPerimetro(resultSet.getDouble("perimetro"));
                predio.setCentroide(resultSet.getDouble("centroide_"));
                predio.setCentroid00(resultSet.getDouble("centroid00"));
                predio.setCentroid01(resultSet.getDouble("centroid01"));
                predio.setCentroid02(resultSet.getDouble("centroid02"));
                predio.setFotografia(resultSet.getString("fotografia"));
                predio.setHtml(resultSet.getString("html"));
                predio.setVinculado(resultSet.getString("vinculado"));
                predio.setFechaRegistro(resultSet.getTimestamp("ts_fecha_creacion"));
                predio.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<PredioGis> obtenerCoordenadasDadoCodigo(int codigo) throws Exception {
        List<PredioGis> lst = new ArrayList<>();
        PredioGis predio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM topology.f_obtener_coordenadas_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                predio = new PredioGis();
                predio.setGid(resultSet.getInt("pgid"));
                predio.setX(resultSet.getDouble("px"));
                predio.setY(resultSet.getDouble("py"));
                lst.add(predio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
