package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Acabados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiciosAcabados {

    public static String registrarAcabadosFicha(Acabados acabados) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_acabados(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, acabados.getPisos());
            prstm.setString(2, acabados.getPuertasExteriores());
            prstm.setString(3, acabados.getPuertasInteriores());
            prstm.setString(4, acabados.getVentanas());
            prstm.setString(5, acabados.getVidrios());
            prstm.setString(6, acabados.getProtecVentanas());
            prstm.setString(7, acabados.getEnlucidos());
            prstm.setString(8, acabados.getTumbados());
            prstm.setString(9, acabados.getCubiertas());
            prstm.setString(10, acabados.getPiezasSanitarias());
            prstm.setString(11, acabados.getCocina());
            prstm.setString(12, acabados.getClosets());
            prstm.setString(13, acabados.getPintura());
            prstm.setString(14, acabados.getFachada());
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
