package auditoria.logica.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.AMejoras;
import catastro.cem.entidades.FormaPago;
import catastro.cem.funciones.FFormaPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

public class FAMejoras {

    public static ArrayList<AMejoras> obtenerAuditoriaMejoras() throws Exception {
        ArrayList<AMejoras> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        AMejoras mejora;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from auditoria.f_seleccionar_mejoras()";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                mejora = new AMejoras();
                mejora.setIdAudMejoras(resultSet.getInt("sr_id_aud_mejora"));
                mejora.setIdMejora(resultSet.getInt("int_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setTiempoPago(resultSet.getInt("int_tiempo_pago"));
                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setAccionRealizada(resultSet.getString("chv_accion"));            
                mejora.setFechaEvento(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setSessionUsuario(resultSet.getString("usuario_session"));
                mejora.setFormaPago(resultSet.getString("forma_pago"));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<AMejoras> comparacionMejoras(int codigoMejora) throws Exception {
        ArrayList<AMejoras> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        AMejoras mejora;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_seleccionar_mejoras_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoMejora);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                mejora = new AMejoras();

                mejora.setIdAudMejoras(resultSet.getInt("sr_id_aud_mejora"));
                mejora.setIdMejora(resultSet.getInt("int_id_mejora"));
                mejora.setNombre(resultSet.getString("chv_nombre"));
                mejora.setFechaInicio(resultSet.getDate("dt_fecha_inicio"));
                mejora.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                mejora.setCosto(resultSet.getDouble("db_costo"));
                mejora.setTiempoPago(resultSet.getInt("int_tiempo_pago"));

                mejora.setFechaInicialPago(resultSet.getDate("dt_fecha_inicial_pago"));
                mejora.setObservaciones(resultSet.getString("txt_observaciones"));
                mejora.setAccionRealizada(resultSet.getString("chv_accion"));
                
                mejora.setFechaEvento(resultSet.getTimestamp("ts_fecha_registro"));
                mejora.setSessionUsuario(resultSet.getString("usuario_session"));
                mejora.setFormaPago(resultSet.getString("forma_pago"));
                lst.add(mejora);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
