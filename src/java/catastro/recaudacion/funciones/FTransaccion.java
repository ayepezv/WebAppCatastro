package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.recaudacion.entidades.Transanccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FTransaccion {

    public static List<Transanccion> obtenerTransancciones() throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_transacciones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.setCliente(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_cliente")));
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_recaudador")));
                transaccion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                transaccion.setObservaciones(resultSet.getString("chv_observaciones"));
                transaccion.setFormaRecaudacion(FFormasRecaudacion.obtenerDadoCodigo(resultSet.getInt("int_id_forma_cobro")));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Transanccion obtenerTransaccionDadoCodigo(int codigo) throws Exception {
        Transanccion transaccion = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_transaccion_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.setCliente(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_cliente")));
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_recaudador")));
                transaccion.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                transaccion.setObservaciones(resultSet.getString("chv_observaciones"));
                transaccion.setFormaRecaudacion(FFormasRecaudacion.obtenerDadoCodigo(resultSet.getInt("int_id_forma_cobro")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return transaccion;
    }

    public static List<Transanccion> reporteTransaccionesDadoUsuarioFecha(int usuario, Date fecha) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_dado_usuario_fecha(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> transaccionesDadoUsuarioRangoFecha(int usuario, Date fechaInicio, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_transacciones_dado_usuario_rango_fecha(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> transPorDiaUsuarioRangoFecha(int usuario, Date fechaInicio, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_x_dia_dado_usuario_fechas(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                //seteo de valores auxiliares
                transaccion.setObservaciones(resultSet.getString("dia_text"));
                transaccion.setNumTransaccion(resultSet.getInt("dia_int"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> transPorDiaUsuarioRangoFechaDia(int usuario, Date fechaInicio, Date fechaFin, String dia) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_x_dia_dado_usuario_fechas_dia(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            prstm.setString(4, dia);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                //seteo de valores auxiliares
                transaccion.setObservaciones(resultSet.getString("dia_text"));
                transaccion.setNumTransaccion(resultSet.getInt("dia_int"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerDiasTransDadoUsuarioRangoFecha(int usuario, Date fechaInicio, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_dias_recaudaciones_dado_usuario_fechas(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                //seteo de valores auxiliares
                transaccion.setObservaciones(resultSet.getString("dia_text"));
                transaccion.setNumTransaccion(resultSet.getInt("dia_int"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTiposTransDadoUsuarioRangoFecha(int usuario, Date fechaInicio, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_tipos_recaudaciones_dado_usuario_fechas(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudacion"));
                transaccion.setRecaudacion(resultSet.getString("recaudacion"));
                //seteo de valores auxiliares
                transaccion.setObservaciones(resultSet.getString("dia_text"));
                transaccion.setNumTransaccion(resultSet.getInt("dia_int"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    //<editor-fold defaultstate="collapsed" desc="Total de recaudaciones diarias">
    public static List<Transanccion> obtenerTotalTransaccionesDiarias() throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_total_transacciones_diarias();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setValorTransaccion(resultSet.getDouble("valor_recaudado"));
                transaccion.setFechaEmision(resultSet.getTimestamp("fecha"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static List<Transanccion> transaccionesDiariasPorCaja(Date fecha) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_reporte_recaudacion_global_cajas_dado_fecha(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setRecaudador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("_id_recaudador")));
                transaccion.setValorTransaccion(resultSet.getDouble("_total"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTransaccionesDadoFecha(Date fecha) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_diarias(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                transaccion.getCliente().setNombres(resultSet.getString("chv_nombres"));
                transaccion.getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                transaccion.getCliente().setCedula(resultSet.getString("chv_cedula"));
                transaccion.getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                transaccion.getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTransaccionesCemDadoFecha(Date fecha) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_diarias_cem(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                transaccion.getCliente().setNombres(resultSet.getString("chv_nombres"));
                transaccion.getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                transaccion.getCliente().setCedula(resultSet.getString("chv_cedula"));
                transaccion.getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                transaccion.getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTransaccionesDadoRangoFechas(Date fechaIni, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_diarias_por_fecha(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fechaIni);
            prstm.setDate(2, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                transaccion.getCliente().setNombres(resultSet.getString("chv_nombres"));
                transaccion.getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                transaccion.getCliente().setCedula(resultSet.getString("chv_cedula"));
                transaccion.getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                transaccion.getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTransaccionesCemDadoRangoFechas(Date fechaIni, Date fechaFin) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_recaudaciones_diarias_cem_dado_fechas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fechaIni);
            prstm.setDate(2, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.getCliente().setIdPersona(resultSet.getInt("id_cliente"));
                transaccion.getCliente().setNombres(resultSet.getString("chv_nombres"));
                transaccion.getCliente().setApellidos(resultSet.getString("chv_apellidos"));
                transaccion.getCliente().setCedula(resultSet.getString("chv_cedula"));
                transaccion.getRecaudador().setNombres(resultSet.getString("chv_nombres_recaudador"));
                transaccion.getRecaudador().setApellidos(resultSet.getString("chv_apellidos_recaudador"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<Transanccion> obtenerTransaccionesGlobalDadoFecha(Date fecha) throws Exception {
        List<Transanccion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Transanccion transaccion;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_obtener_transacciones_diarias_global_dado_fecha(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fecha);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                transaccion = new Transanccion();
                transaccion.setIdTransaccion(resultSet.getInt("sr_id_transaccion"));
                transaccion.setNumTransaccion(resultSet.getInt("num_transaccion"));
                transaccion.setFechaEmision(resultSet.getTimestamp("fecha_emision_transac"));
                transaccion.setRecaudacion(resultSet.getString("chv_recaudacion"));
                transaccion.setValorTransaccion(resultSet.getDouble("db_valor_transaccion"));
                transaccion.setEstadoTransacion(resultSet.getString("estado_transacion"));
                transaccion.setObservaciones(resultSet.getString("chv_observaciones"));
                transaccion.setFormaRecaudacion(FFormasRecaudacion.obtenerDadoCodigo(resultSet.getInt("int_id_forma_cobro")));
                /// datos del recaudador
                transaccion.getRecaudador().setIdPersona(resultSet.getInt("int_id_usuario"));
                transaccion.getRecaudador().setNick(resultSet.getString("nick_recaudador"));
                transaccion.getRecaudador().setMail(resultSet.getString("mail_recaudador"));
                transaccion.getRecaudador().setNombres(resultSet.getString("nombres_recaudador"));
                transaccion.getRecaudador().setApellidos(resultSet.getString("apellidos_recaudador"));
                /// datos del cliente
                transaccion.getCliente().setIdPersona(resultSet.getInt("sr_id_cliente"));
                transaccion.getCliente().setCedula(resultSet.getString("cedula_cliente"));
                transaccion.getCliente().setNombres(resultSet.getString("nombres_cliente"));
                transaccion.getCliente().setApellidos(resultSet.getString("apellidos_cliente"));
                lst.add(transaccion);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String anularTransaccion(int idTransaccion) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_anular_cobro(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idTransaccion);
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
}
