package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.LocalizTopog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiciosLocalizacion {

    public static String insertarLocalizac(LocalizTopog loc) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_localizacion_top_ficha(?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, loc.getLocalizacion());
            prstm.setString(2, loc.getTopografia());
            prstm.setString(3, loc.getMaterialVial());
            prstm.setString(4, loc.getConstrucSuelo());
            prstm.setString(5, loc.getBordillos());
            prstm.setString(6, loc.getAceras());
            prstm.setString(7, loc.getAguaPotable());
            prstm.setString(8, loc.getAlcantPluvial());
            prstm.setString(9, loc.getAlcantSanitario());
            prstm.setString(10, loc.getEnergiaElectrica());
            prstm.setString(11, loc.getRedTelefonica());
            prstm.setString(12, loc.getAlumbPublico());
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
