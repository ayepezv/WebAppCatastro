package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.UsoSuelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiciosUsoSuelo {

    public static String insertarUsoSuelo(UsoSuelo us) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_uso_suelo(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, us.getProduccion());
            prstm.setString(2, us.getIntercambio());
            prstm.setString(3, us.getConsumo());
            prstm.setString(4, us.getGestion());
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
