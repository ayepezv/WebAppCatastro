package auditoria.logica.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.AccesoSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

public class ServiciosAccesosUsuario {

    public static ArrayList<AccesoSistema> obtenerAccesosSistema() throws Exception {
        ArrayList<AccesoSistema> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        AccesoSistema accesoSistema;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from auditoria.f_seleccionar_accesos()";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                accesoSistema = new AccesoSistema();
                accesoSistema.setUsuario(new Usuario());

                accesoSistema.setUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                accesoSistema.setIdAccessoUsuario(resultSet.getInt("sr_id_acceso_usuario"));
                accesoSistema.setFechaEvento(resultSet.getTimestamp("ts_fecha_evento"));
                accesoSistema.setHoraIngreso(resultSet.getTime("tm_hora_ingreso"));
                accesoSistema.setHoraSalida(resultSet.getTime("tm_hora_salida"));
                accesoSistema.setDuracion(resultSet.getTime("tm_duracion"));
                accesoSistema.setIpPrivada(resultSet.getString("chv_ip_privada"));
                accesoSistema.setIpPublica(resultSet.getString("chv_ip_publica"));
                accesoSistema.setEstadoSession(resultSet.getString("ch_estado_session"));
                accesoSistema.setObservaciones(resultSet.getString("chv_observacion_salida"));
                accesoSistema.setNombreEquipo(resultSet.getString("chv_nombre_equipo"));

                lst.add(accesoSistema);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarAccesoSistema(int idUsuario, String ipPrivada, String ipPublica, String nombreEquipo) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_registrar_ingreso_ssistema(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            prstm.setString(2, ipPrivada);
            prstm.setString(3, ipPublica);
            prstm.setString(4, nombreEquipo);
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

    public static String registrarSalidaSistema(int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_registrar_salida_sistema(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
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

    public static ArrayList<AccesoSistema> obtenerAccesosDadoUsuario(int idUsuario) throws Exception {
        ArrayList<AccesoSistema> lst = new ArrayList<>();
        AccesoSistema accesoSistema;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM auditoria.f_seleccionar_accesos_dado_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                accesoSistema = new AccesoSistema();
                accesoSistema.setUsuario(new Usuario());
                accesoSistema.setUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                accesoSistema.setIdAccessoUsuario(resultSet.getInt("sr_id_acceso_usuario"));
                accesoSistema.setFechaEvento(resultSet.getTimestamp("ts_fecha_evento"));
                accesoSistema.setHoraIngreso(resultSet.getTime("tm_hora_ingreso"));
                accesoSistema.setHoraSalida(resultSet.getTime("tm_hora_salida"));
                accesoSistema.setDuracion(resultSet.getTime("tm_duracion"));
                accesoSistema.setIpPrivada(resultSet.getString("chv_ip_privada"));
                accesoSistema.setIpPublica(resultSet.getString("chv_ip_publica"));
                accesoSistema.setEstadoSession(resultSet.getString("ch_estado_session"));
                accesoSistema.setObservaciones(resultSet.getString("chv_observacion_salida"));
                accesoSistema.setNombreEquipo(resultSet.getString("chv_nombre_equipo"));
                lst.add(accesoSistema);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<AccesoSistema> obtenerTotalAccesosSistema() throws Exception {
        ArrayList<AccesoSistema> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        AccesoSistema accesoSistema;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from auditoria.f_seleccionar_accesos_uuarios()";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                accesoSistema = new AccesoSistema();
                accesoSistema.setUsuario(new Usuario());
                accesoSistema.setUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                accesoSistema.setIdAccessoUsuario(resultSet.getInt("sr_id_acceso_usuario"));
                accesoSistema.setFechaEvento(resultSet.getTimestamp("ts_fecha_evento"));
                accesoSistema.setHoraIngreso(resultSet.getTime("tm_hora_ingreso"));
                accesoSistema.setHoraSalida(resultSet.getTime("tm_hora_salida"));
                accesoSistema.setDuracion(resultSet.getTime("tm_duracion"));
                accesoSistema.setIpPrivada(resultSet.getString("chv_ip_privada"));
                accesoSistema.setIpPublica(resultSet.getString("chv_ip_publica"));
                accesoSistema.setEstadoSession(resultSet.getString("ch_estado_session"));
                accesoSistema.setObservaciones(resultSet.getString("chv_observacion_salida"));
                accesoSistema.setNombreEquipo(resultSet.getString("chv_nombre_equipo"));
                lst.add(accesoSistema);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
