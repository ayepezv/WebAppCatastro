package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Instalaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiciosInstalaciones {

    public static String registrarInstalacionesFicha(Instalaciones instalaciones) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_instalaciones_ficha(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, instalaciones.getEnergElectr());
            prstm.setString(2, instalaciones.getSanitarias());
            prstm.setString(3, instalaciones.getEspeciales());
            prstm.setString(4, instalaciones.getSistemContraIncend());
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
