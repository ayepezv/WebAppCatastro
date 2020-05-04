/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria.catastro.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.LogPredio;
import catastro.logica.servicios.FuncionesPredio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import master.logica.servicios.ServiciosUsuario;

/**
 *
 * @author Geovanny Cudco
 */
public class FLogsPredios {

    public static ArrayList<LogPredio> listarLogsPredio(Date fechaInicio, Date fechaFin) throws Exception {
        ArrayList<LogPredio> lst = new ArrayList<>();
        LogPredio logsPredio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_obtener_predios_entre_fechas(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fechaInicio);
            prstm.setDate(2, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                logsPredio = new LogPredio();
                logsPredio.setIdLog(resultSet.getInt("id_log_predio"));
                logsPredio.setAccion(resultSet.getString("chv_accion"));
                logsPredio.setFecha(resultSet.getTimestamp("ts_fecha"));
//                logsPredio.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                logsPredio.getPredio().setIdPredio(resultSet.getInt("int_id_predio"));
                logsPredio.getPredio().setZona(resultSet.getInt("int_sector"));
                logsPredio.getPredio().setManzana(resultSet.getInt("int_manzana"));
                logsPredio.getPredio().setSector(resultSet.getInt("int_sector"));
                logsPredio.getPredio().setNumPredio(resultSet.getInt("int_num_predio"));
                logsPredio.getPredio().setSessionUsuario(ServiciosUsuario.buscarUsuarioParaSession(resultSet.getInt("id_session_usuario")));
                lst.add(logsPredio);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
