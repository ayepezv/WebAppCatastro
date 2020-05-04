/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.ServiciosUrbanos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Geovanny
 */
public class FuncServiciosUrbanos {

    public static String insertarServiciosUrbanos(ServiciosUrbanos su) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_servicios_urbanos_ficha(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, su.getMedAguaPot());
            prstm.setString(2, su.getMedElectric());
            prstm.setString(3, su.getRecolBasura());
            prstm.setString(4, su.getAseoPublico());
            prstm.setString(5, su.getTranspUrbano());
            prstm.setString(6, su.getTelefono());
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
