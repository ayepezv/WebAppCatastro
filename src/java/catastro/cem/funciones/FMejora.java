package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.Mejora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FMejora {

    //<editor-fold defaultstate="collapsed" desc="Obtener mejoras">
    public static List<Mejora> obtenerMejoras() throws Exception {
        List<Mejora> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Mejora mejora;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_datos_mejoras();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                mejora = new Mejora();
                mejora.setIdMejora(resultSet.getInt("sr_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setPlazo(resultSet.getInt("int_tiempo_pago"));
                mejora.getFormaPago().setIdFormaPago(resultSet.getInt("int_id_forma_pago"));
                mejora.getFormaPago().setFormaPago(resultSet.getString("chv_forma_pago"));
                mejora.getFormaPago().setPeriodo(resultSet.getInt("int_periodo"));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                mejora.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                mejora.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                mejora.getSesionUsuario().setIdPersona(resultSet.getInt("int_id_usuario_sesion"));
                mejora.getSesionUsuario().setNick(resultSet.getString("chv_nick"));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    public static String registrarMejora(Mejora mejora) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_mejora(?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, mejora.getNombre());
            prstm.setDate(2, new java.sql.Date(mejora.getFechaInicio().getTime()));
            prstm.setInt(3, mejora.getAnioAvaluo());
            prstm.setDouble(4, mejora.getCosto());
            prstm.setInt(5, mejora.getPlazo());
            prstm.setInt(6, mejora.getFormaPago().getIdFormaPago());
            prstm.setDate(7, new java.sql.Date(mejora.getFechaInicialPago().getTime()));
            prstm.setString(8, mejora.getObservaciones());
            prstm.setInt(9, mejora.getSesionUsuario().getIdPersona());
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

    public static Mejora obtenerMejoraDadoCodigo(int codigo) throws Exception {
        Mejora mejora = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_mejora_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                mejora = new Mejora();
                mejora.setIdMejora(resultSet.getInt("sr_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setPlazo(resultSet.getInt("int_tiempo_pago"));
                mejora.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                mejora.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                mejora.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return mejora;
    }

    public static String actualizarMejora(Mejora mejora) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_mejor(?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, mejora.getIdMejora());
            prstm.setString(2, mejora.getNombre());
            prstm.setDate(3, new java.sql.Date(mejora.getFechaInicio().getTime()));
            prstm.setInt(4, mejora.getAnioAvaluo());
            prstm.setDouble(5, mejora.getCosto());
            prstm.setInt(6, mejora.getPlazo());
            prstm.setInt(7, mejora.getFormaPago().getIdFormaPago());
            prstm.setDate(8, new java.sql.Date(mejora.getFechaInicialPago().getTime()));
            prstm.setString(9, mejora.getObservaciones());
            prstm.setInt(10, mejora.getSesionUsuario().getIdPersona());
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

    public static String eliminarMejora(int idMejora, int sessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_mejora(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idMejora);
            prstm.setInt(2, sessionUsuario);
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

    public static List<Mejora> encontrarMejoraDadoParametro(String parametro) throws Exception {
        List<Mejora> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Mejora mejora;
        ResultSet resultSet;
        String sql;
        PreparedStatement prstm;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_find_mejora(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                mejora = new Mejora();
                mejora.setIdMejora(resultSet.getInt("sr_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setPlazo(resultSet.getInt("int_tiempo_pago"));
                mejora.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                mejora.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                mejora.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                mejora.setSesionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario_sesion")));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    //<editor-fold defaultstate="collapsed" desc="Obtener anios de avaluo de las mejoras">
    public static List<Mejora> obtenerAniosAvaluoMejoras() throws Exception {
        List<Mejora> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Mejora mejora;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_anios_avaluo_mejoras();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                mejora = new Mejora();
                mejora.setIdMejora(resultSet.getInt("sr_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setPlazo(resultSet.getInt("int_tiempo_pago"));
                mejora.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                mejora.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                mejora.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                mejora.setSesionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario_sesion")));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtener mejoras dado el anio de avaluo">
    public static List<Mejora> encontrarMejoraDadoAnioAvaluo(int anio) throws Exception {
        List<Mejora> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Mejora mejora;
        ResultSet resultSet;
        String sql;
        PreparedStatement prstm;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_mejoras_dado_anio_avaluo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, anio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                mejora = new Mejora();
                mejora.setIdMejora(resultSet.getInt("sr_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setPlazo(resultSet.getInt("int_tiempo_pago"));
                mejora.setFormaPago(FFormaPago.obtenerFormaPagoDadoCodigo(resultSet.getInt("int_id_forma_pago")));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                mejora.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                mejora.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                mejora.setSesionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario_sesion")));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>    
}
