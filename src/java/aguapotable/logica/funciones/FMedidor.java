package aguapotable.logica.funciones;

import accesoDatos.AccesoDatos;
import aguapotable.logica.entidades.Medidor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FMedidor {

    public static Medidor obtenerMedidorDadoCodigo(int codigo) throws Exception {
        Medidor medidor = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM agua_potable.f_seleccionar_medidor_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                medidor = new Medidor();
                medidor.setIdMedidor(resultSet.getInt("sr_id_medidor"));
                medidor.setNumMedidor(resultSet.getInt("int_num_medidor"));
                medidor.setMarca(resultSet.getString("chv_marca"));
                medidor.setNumSerie(resultSet.getString("chv_num_serie"));
                medidor.setLecturaInicial(resultSet.getInt("int_lectura_inicial"));
                medidor.setEstado(resultSet.getString("chv_estado"));
                medidor.setTipo(resultSet.getString("chv_tipo"));
                medidor.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                medidor.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                medidor.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                medidor.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                medidor.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                medidor.setInt_medidor_activo(resultSet.getInt("int_medidor_activo"));
                medidor.setInt_numero_esferas(resultSet.getInt("int_numero_esferas"));
                medidor.setTs_fecha_instalacion(resultSet.getTimestamp("ts_fecha_instalacion"));
                medidor.setChv_medidor_diametro(resultSet.getString("chv_medidor_diametro"));
                medidor.setChv_medidor_ubicacion(resultSet.getString("chv_medidor_ubicacion"));
                medidor.setChv_sello_medidor(resultSet.getString("chv_sello_medidor"));
                medidor.setInt_sello_numero(resultSet.getInt("int_sello_numero"));
                medidor.setInt_id_instalador(resultSet.getInt("int_id_instalador"));
                medidor.setInt_num_cuenta(resultSet.getInt("int_num_cuenta"));

            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return medidor;
    }

    public static List<Medidor> obtenerMedidores() throws Exception {
        List<Medidor> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Medidor medidor;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from agua_potable.f_seleccionar_medidores();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                medidor = new Medidor();
                medidor.setIdMedidor(resultSet.getInt("sr_id_medidor"));
                medidor.setNumMedidor(resultSet.getInt("int_num_medidor"));
                medidor.setMarca(resultSet.getString("chv_marca"));
                medidor.setNumSerie(resultSet.getString("chv_num_serie"));
                medidor.setLecturaInicial(resultSet.getInt("int_lectura_inicial"));
                medidor.setEstado(resultSet.getString("chv_estado"));
                medidor.setTipo(resultSet.getString("chv_tipo"));
                medidor.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                medidor.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                medidor.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                medidor.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                medidor.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                medidor.setInt_medidor_activo(resultSet.getInt("int_medidor_activo"));
                medidor.setInt_numero_esferas(resultSet.getInt("int_numero_esferas"));
                medidor.setTs_fecha_instalacion(resultSet.getTimestamp("ts_fecha_instalacion"));
                medidor.setChv_medidor_diametro(resultSet.getString("chv_medidor_diametro"));
                medidor.setChv_medidor_ubicacion(resultSet.getString("chv_medidor_ubicacion"));
                medidor.setChv_sello_medidor(resultSet.getString("chv_sello_medidor"));
                medidor.setInt_sello_numero(resultSet.getInt("int_sello_numero"));
                medidor.setInt_id_instalador(resultSet.getInt("int_id_instalador"));
                medidor.setInt_num_cuenta(resultSet.getInt("int_num_cuenta"));
                lst.add(medidor);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
