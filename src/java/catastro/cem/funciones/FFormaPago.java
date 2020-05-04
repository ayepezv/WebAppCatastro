package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.FormaPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FFormaPago {

    public static List<FormaPago> obtenerFormasPagos() throws Exception {
        List<FormaPago> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        FormaPago forma;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_forma_pago();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                forma = new FormaPago();
                forma.setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                forma.setFormaPago(resultSet.getString("chv_forma_pago"));
                forma.setPeriodo(resultSet.getInt("int_periodo"));
                forma.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                forma.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                forma.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                forma.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(forma);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static FormaPago obtenerFormaPagoDadoCodigo(int codigo) throws Exception {
        FormaPago forma = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_forma_pago_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                forma = new FormaPago();
                forma.setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                forma.setFormaPago(resultSet.getString("chv_forma_pago"));
                forma.setPeriodo(resultSet.getInt("int_periodo"));
                forma.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                forma.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                forma.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                forma.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return forma;
    }
}
