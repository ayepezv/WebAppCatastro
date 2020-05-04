package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Cuenta;
import catastro.logica.servicios.FuncionesPredio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FCuenta {

    public static List<Cuenta> obtenerCuentas() throws Exception {
        List<Cuenta> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Cuenta cuenta;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from agua_potable.f_seleccionar_cuentas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("sr_id_cuenta"));
                cuenta.setNumCuenta(resultSet.getInt("num_cuenta"));
                cuenta.setAbonado(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_abonado")));
                cuenta.setMedidor(FMedidor.obtenerMedidorDadoCodigo(resultSet.getInt("id_medidor_asociado")));
                cuenta.setDireccion(resultSet.getString("direccion"));
                cuenta.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                cuenta.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                cuenta.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                cuenta.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                cuenta.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                cuenta.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                lst.add(cuenta);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarCuenta(Cuenta cuenta) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from agua_potable.f_insertar_cuenta(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, cuenta.getAbonado().getIdPersona());
            prstm.setInt(2, cuenta.getMedidor().getIdMedidor());
            prstm.setString(3, cuenta.getDireccion());
            prstm.setInt(4, cuenta.getSessionUsuario().getIdPersona());
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

    public static String registrarCuentaCompleta(Cuenta cuenta) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from agua_potable.f_registrar_cuenta_medidor_abonado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, cuenta.getAbonado().getCedula());
            prstm.setString(2, cuenta.getAbonado().getNombres());
            prstm.setString(3, cuenta.getAbonado().getApellidos());
            prstm.setString(4, cuenta.getAbonado().getCelular());
            prstm.setString(5, cuenta.getAbonado().getNick());
            prstm.setString(6, cuenta.getAbonado().getMail());
            prstm.setString(7, cuenta.getAbonado().getPassword());
            prstm.setString(8, cuenta.getAbonado().getSexo());
            prstm.setString(9, cuenta.getDireccion());
            prstm.setString(10, cuenta.getMedidor().getMarca());
            prstm.setString(11, cuenta.getMedidor().getNumSerie());
            prstm.setInt(12, cuenta.getMedidor().getLecturaInicial());
            prstm.setString(13, cuenta.getMedidor().getEstado());
            prstm.setString(14, cuenta.getMedidor().getTipo());
            prstm.setInt(15, cuenta.getSessionUsuario().getIdPersona());
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

    public static String registrarCuentaDadoPredio(Cuenta cuenta) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from agua_potable.f_registrar_cuenta_dado_predio(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, cuenta.getMedidor().getMarca());
            prstm.setString(2, cuenta.getMedidor().getNumSerie());
            prstm.setInt(3, cuenta.getMedidor().getLecturaInicial());
            prstm.setString(4, cuenta.getMedidor().getEstado());
            prstm.setString(5, cuenta.getMedidor().getTipo());
            prstm.setInt(6, cuenta.getPredio().getIdPredio());
            prstm.setInt(7, cuenta.getSessionUsuario().getIdPersona());
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

    public static List<Cuenta> encontrarCuenta(String parametro) throws Exception {
        List<Cuenta> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Cuenta cuenta;
        ResultSet resultSet;
        String sql;
        PreparedStatement prstm;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from agua_potable.f_buscar_cuenta(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("sr_id_cuenta"));
                cuenta.setNumCuenta(resultSet.getInt("num_cuenta"));
                cuenta.setAbonado(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_abonado")));
                cuenta.setMedidor(FMedidor.obtenerMedidorDadoCodigo(resultSet.getInt("id_medidor_asociado")));
                cuenta.setDireccion(resultSet.getString("direccion"));
                cuenta.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                cuenta.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                cuenta.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                cuenta.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                cuenta.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                cuenta.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                lst.add(cuenta);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
