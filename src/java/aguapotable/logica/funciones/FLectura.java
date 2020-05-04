package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Lectura;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FLectura {

    public static List<Lectura> obtenerLecturas() throws Exception {
        List<Lectura> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Lectura lectura;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from agua_potable.f_seleccionar_lecturas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                lectura = new Lectura();
                lectura.setIdLectura(resultSet.getInt("sr_id_lectura"));
                lectura.setMedidor(FMedidor.obtenerMedidorDadoCodigo(resultSet.getInt("int_id_medidor")));
                lectura.setFechaLectura(resultSet.getDate("fecha_lectura"));
                lectura.setLecturaAnterior(resultSet.getDouble("db_lectura_anterior"));
                lectura.setLecturaActual(resultSet.getDouble("db_lectura_actual"));
                lectura.setConsumo(resultSet.getDouble("db_consumo"));
                lectura.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                lectura.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                lectura.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                lectura.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lectura.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                lectura.setInicioPeriodo(resultSet.getDate("inicio_periodo"));
                lectura.setFinPeriodo(resultSet.getDate("fin_periodo"));
                lectura.setInt_proceso_mes(resultSet.getInt("int_proceso_mes"));
                lectura.setInt_proceso_anio(resultSet.getInt("int_proceso_anio"));
                lectura.setTs_fecha_digitada(resultSet.getTimestamp("ts_fecha_digitada"));
                lectura.setTs_fecha_validacion(resultSet.getTimestamp("ts_fecha_validacion"));
                lectura.setInt_novedad(resultSet.getInt("int_novedad"));
                lectura.setInt_lectura_aplicada(resultSet.getInt("int_lectura_aplicada"));
                lectura.setInt_lectura_accion(resultSet.getInt("int_lectura_accion"));
                lectura.setInt_lectura_verificada(resultSet.getInt("int_lectura_verificada"));
                lectura.setInt_lectura_final(resultSet.getInt("int_lectura_final"));
                lectura.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_usuario_verificador")));
                lectura.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_usuario_digitador")));
                lectura.setInt_lectura_calculada(resultSet.getInt("int_lectura_calculada"));
                lectura.setChv_observacion(resultSet.getString("chv_observacion"));
                lectura.setBy_medidor_lectura(resultSet.getString("by_medidor_lectura"));
                lectura.setBy_medidor(resultSet.getString("by_medidor"));
                lectura.setBy_observacion_1(resultSet.getString("by_observacion_1"));
                lectura.setBy_observacion_2(resultSet.getString("by_observacion_2"));
                lectura.setBy_fachada(resultSet.getString("by_fachada"));
                lectura.setDb_coordenada_x(resultSet.getDouble("db_coordenada_x"));
                lectura.setDb_coordenada_y(resultSet.getDouble("db_coordenada_y"));
                lst.add(lectura);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarLecturaV1(Lectura lectura) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from agua_potable.f_insertar_lectura(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, lectura.getMedidor().getIdMedidor());
            prstm.setDate(2, (Date) lectura.getFechaLectura());
            prstm.setDouble(3, lectura.getLecturaActual());
            prstm.setDouble(4, lectura.getConsumo());
            prstm.setInt(5, lectura.getSessionUsuario().getIdPersona());
            prstm.setDate(6, (Date) lectura.getInicioPeriodo());
            prstm.setDate(7, (Date) lectura.getFinPeriodo());

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
