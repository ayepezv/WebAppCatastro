package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.ExcencionNoEdificado;
import catastro.logica.entidades.PredioV2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author gcudcop
 */


public class FExcencionNoEdificado {

    public static List<ExcencionNoEdificado> obtenerExEspNoEdificado() throws Exception {
        List<ExcencionNoEdificado> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        ExcencionNoEdificado noEdificado;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_no_edificados_predios();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                noEdificado = new ExcencionNoEdificado();
                noEdificado.setPredioNoEdificado(resultSet.getInt("sr_id_predio_no_edificado"));
                noEdificado.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                noEdificado.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                noEdificado.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                noEdificado.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                noEdificado.getPredio().getPropietario().setCedula(resultSet.getString("chv_ruc"));
                noEdificado.setRazon(resultSet.getString("chv_razon"));
                noEdificado.setFechaInicio(resultSet.getTimestamp("dt_fecha_inicio"));
                noEdificado.setFechaFin(resultSet.getTimestamp("dt_fecha_fin"));
                noEdificado.setReclamo(resultSet.getString("chv_reclamo"));
                noEdificado.setObservaciones(resultSet.getString("chv_observaciones"));
                noEdificado.setAvaluo_Predio(resultSet.getDouble("db_avaluo_predio"));
                noEdificado.setDescuentoNoEdificado(resultSet.getDouble("db_descuento_no_edificado"));
                noEdificado.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                noEdificado.setTemporalidad(resultSet.getString("temporalidad"));
                lst.add(noEdificado);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarExEspNoEdificado(ExcencionNoEdificado noEdificado) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_t_excenciones_no_edificado(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, noEdificado.getPredio().getIdPredio());
            prstm.setString(2, noEdificado.getRazon());
            prstm.setString(3, noEdificado.getReclamo());
            prstm.setInt(4, noEdificado.getSessionUsuario().getIdPersona());
            prstm.setString(5, noEdificado.getObservaciones());
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

    public static String actualizarExEspNoEdificado(ExcencionNoEdificado noEdificado) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_t_excenciones_no_edificado(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, noEdificado.getPredio().getIdPredio());
            prstm.setString(2, noEdificado.getRazon());
            prstm.setString(3, noEdificado.getReclamo());
            prstm.setInt(4, noEdificado.getSessionUsuario().getIdPersona());
            prstm.setString(5, noEdificado.getObservaciones());
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

    public static String eliminarExEspNoEdificado(ExcencionNoEdificado noEdificado) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_t_excenciones_no_edificado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, noEdificado.getPredioNoEdificado());
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

    public static List<ExcencionNoEdificado> obtenerNoEdificadoDadoCriterio(String criterio) throws Exception {
        List<ExcencionNoEdificado> lst = new ArrayList<>();
        ExcencionNoEdificado noEdificado;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_no_edificado_predios_dado_criterio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, criterio);

            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                noEdificado = new ExcencionNoEdificado();
                noEdificado.setPredioNoEdificado(resultSet.getInt("sr_id_predio_no_edificado"));
                noEdificado.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                noEdificado.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                noEdificado.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                noEdificado.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                noEdificado.getPredio().getPropietario().setCedula(resultSet.getString("chv_ruc"));
                noEdificado.setRazon(resultSet.getString("chv_razon"));
                noEdificado.setFechaInicio(resultSet.getTimestamp("dt_fecha_inicio"));
                noEdificado.setFechaFin(resultSet.getTimestamp("dt_fecha_fin"));
                noEdificado.setReclamo(resultSet.getString("chv_reclamo"));
                noEdificado.setObservaciones(resultSet.getString("chv_observaciones"));
                noEdificado.setAvaluo_Predio(resultSet.getDouble("db_avaluo_predio"));
                noEdificado.setDescuentoNoEdificado(resultSet.getDouble("db_descuento_no_edificado"));
                noEdificado.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                noEdificado.setTemporalidad(resultSet.getString("temporalidad"));
                lst.add(noEdificado);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static List<ExcencionNoEdificado> obtenerNoEdificadosDadoZonaSectorManzanaPredio(int zona, int sector, int manzana, int predio) throws Exception {
        List<ExcencionNoEdificado> lst = new ArrayList<>();
        ExcencionNoEdificado noEdificado;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_no_edificados_dado_zona_sector_manzana_predio(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, zona);
            prstm.setInt(2, sector);
            prstm.setInt(3, manzana);
            prstm.setInt(4, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                noEdificado = new ExcencionNoEdificado();
                noEdificado.setPredioNoEdificado(resultSet.getInt("sr_id_predio_no_edificado"));
                noEdificado.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                noEdificado.getPredio().setClaveCatastral(resultSet.getString("chv_clave_catastral"));
                noEdificado.getPredio().getPropietario().setApellidos(resultSet.getString("chv_apellidos"));
                noEdificado.getPredio().getPropietario().setNombres(resultSet.getString("chv_nombres"));
                noEdificado.getPredio().getPropietario().setCedula(resultSet.getString("chv_ruc"));
                noEdificado.setRazon(resultSet.getString("chv_razon"));
                noEdificado.setFechaInicio(resultSet.getTimestamp("dt_fecha_inicio"));
                noEdificado.setFechaFin(resultSet.getTimestamp("dt_fecha_fin"));
                noEdificado.setReclamo(resultSet.getString("chv_reclamo"));
                noEdificado.setObservaciones(resultSet.getString("chv_observaciones"));
                noEdificado.setAvaluo_Predio(resultSet.getDouble("db_avaluo_predio"));
                noEdificado.setDescuentoNoEdificado(resultSet.getDouble("db_descuento_no_edificado"));
                noEdificado.setAvaluoImponible(resultSet.getDouble("db_avaluo_imponible"));
                noEdificado.setTemporalidad(resultSet.getString("temporalidad"));
                lst.add(noEdificado);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
