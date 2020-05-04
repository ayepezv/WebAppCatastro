package auditoria.logica.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.LogPredio;
import catastro.logica.servicios.FuncionesPredio;
import catastro.logica.servicios.ServiciosPredio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FLogPredio {

    public static List<LogPredio> listarLogsPredio(int idPredio, Date fechaInicio, Date fechaFin) throws Exception {
        List<LogPredio> lst = new ArrayList<>();
        LogPredio logsPredio;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from auditoria.f_obtener_logs_dado_predio_fechas(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            prstm.setDate(2, (java.sql.Date) fechaInicio);
            prstm.setDate(3, (java.sql.Date) fechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                logsPredio = new LogPredio();
                logsPredio.setIdLog(resultSet.getInt("id_log_predio"));
                logsPredio.setAccion(resultSet.getString("chv_accion"));
                logsPredio.setFecha(resultSet.getTimestamp("ts_fecha"));
                logsPredio.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                lst.add(logsPredio);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
