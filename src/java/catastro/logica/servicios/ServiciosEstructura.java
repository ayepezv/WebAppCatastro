package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Estructura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiciosEstructura {

    public static String registrarEstructuraFicha(Estructura est) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_estructura_ficha(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);           
            prstm.setString(1, est.getCimientos());
            prstm.setString(2, est.getCadenas());
            prstm.setString(3, est.getColumnas());
            prstm.setString(4, est.getVigas());
            prstm.setString(5, est.getEntrepiso());
            prstm.setString(6, est.getParedes());
            prstm.setString(7, est.getCubierta());
            prstm.setString(8, est.getEscaleras());
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
