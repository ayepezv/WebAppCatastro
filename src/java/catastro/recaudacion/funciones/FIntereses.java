package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.recaudacion.entidades.Intereses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

public class FIntereses {

    public static List<Intereses> obtenerIntereses() throws Exception {
        List<Intereses> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Intereses interes;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_tasas_intereses();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                interes = new Intereses();
                interes.setIdInteres(resultSet.getInt("_sr_id_interes"));
                interes.setAnio(resultSet.getInt("_int_anio"));
                interes.setMes(resultSet.getString("_chv_mes"));
                interes.setTrimestre(resultSet.getString("_chv_trimestre"));
                interes.setNumTrimestre(resultSet.getInt("_int_trimestre"));
                interes.setInteresMensual(resultSet.getDouble("_db_interes_mensual"));
                interes.setInteresAcumAnual(resultSet.getDouble("_db_interes_acum_anual"));
                interes.setInteresTotalAcum(resultSet.getDouble("_db_interes_total_acum"));
//                interes.setInteresPorMora(resultSet.getDouble("_ANUAL_MORA"));
                interes.setEstadoLogico(resultSet.getString("_ch_estado_logico"));
                interes.setFechaRegistro(resultSet.getTimestamp("_ts_fecha_registro"));
                interes.setFechaActualizacion(resultSet.getTimestamp("_ts_fecha_actualizacion"));
                interes.setFechaBaja(resultSet.getTimestamp("_ts_fecha_baja"));
                interes.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_session_usuario")));
                lst.add(interes);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarInteres(Intereses interes) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_tasa_interes(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, interes.getNumTrimestre());
            prstm.setInt(2, interes.getAnio());
            prstm.setDouble(3, interes.getInteresMensual());
            prstm.setInt(4, interes.getSessionUsuario().getIdPersona());
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

    public static String editarInteres(Intereses interes) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_interes(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, interes.getIdInteres());
            prstm.setInt(2, interes.getSessionUsuario().getIdPersona());
            prstm.setDouble(3, interes.getInteresMostrar());
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

    public static List<Intereses> obtenerInteresDadoAnio(int anio) throws Exception {
        ArrayList<Intereses> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Intereses interes;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_buscar_interes_dado_anio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                interes = new Intereses();
                interes.setIdInteres(resultSet.getInt("sr_id_interes"));
                interes.setAnio(resultSet.getInt("int_anio"));
                interes.setMes(resultSet.getString("chv_mes"));
                interes.setTrimestre(resultSet.getString("chv_trimestre"));
                interes.setNumTrimestre(resultSet.getInt("int_trimestre"));
                interes.setInteresMensual(resultSet.getDouble("db_interes_mensual"));
                interes.setInteresAcumAnual(resultSet.getDouble("db_interes_acum_anual"));
                interes.setInteresTotalAcum(resultSet.getDouble("db_interes_total_acum"));
                interes.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                interes.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                interes.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                interes.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                interes.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                interes.setInteresMostrar(Util.redondearDecimales((resultSet.getDouble("db_interes_mensual") * 12 * 100 / 1.1), 2));
                lst.add(interes);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Intereses> obtenerAniosIntereses() throws Exception {
        List<Intereses> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Intereses interes;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_anios_interes();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                interes = new Intereses();
                interes.setAnio(resultSet.getInt("int_anio"));
                lst.add(interes);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Intereses> obtenerTrimestresDadoAnio(int anio) throws Exception {
        ArrayList<Intereses> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Intereses interes;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_trimestres_dado_anio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                interes = new Intereses();
                interes.setTrimestre(resultSet.getString("chv_trimestre"));
                interes.setNumTrimestre(resultSet.getInt("int_trimestre"));
                lst.add(interes);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
