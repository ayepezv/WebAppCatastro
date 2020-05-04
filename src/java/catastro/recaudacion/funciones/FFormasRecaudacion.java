package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.recaudacion.entidades.FormaRecaudacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FFormasRecaudacion {

    public static List<FormaRecaudacion> obtenerFormasPago() throws Exception {
        List<FormaRecaudacion> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        FormaRecaudacion formaPago;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_obtener_formas_cobro();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                formaPago = new FormaRecaudacion();
                formaPago.setCodigo(resultSet.getInt("sr_codigo"));
                formaPago.setFormaRecaudacion(resultSet.getString("chv_forma_cobro"));
                formaPago.setDescripcion(resultSet.getString("txt_descripcion"));
                formaPago.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaPago.setObservaciones(resultSet.getString("txt_observaciones"));
                lst.add(formaPago);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static FormaRecaudacion obtenerDadoCodigo(int codigo) throws Exception {
        FormaRecaudacion formaPago = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_formas_cobro_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                formaPago = new FormaRecaudacion();
                formaPago.setCodigo(resultSet.getInt("sr_codigo"));
                formaPago.setFormaRecaudacion(resultSet.getString("chv_forma_cobro"));
                formaPago.setDescripcion(resultSet.getString("txt_descripcion"));
                formaPago.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaPago.setObservaciones(resultSet.getString("txt_observaciones"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return formaPago;
    }
}
