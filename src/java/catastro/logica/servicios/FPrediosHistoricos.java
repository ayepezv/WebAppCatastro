package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.PredioHistorico;
import digitales.logica.funciones.FDocumento;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FPrediosHistoricos {

    public static String registrarEliminacion(PredioHistorico predioHist) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_predio_v2(?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predioHist.getPredio().getIdPredio());
            prstm.setInt(2, predioHist.getSessionUsuario().getIdPersona());
            prstm.setString(3, predioHist.getObservaciones());
            prstm.setString(4, predioHist.getRazonEliminacion());
            prstm.setString(5, predioHist.getDocumento().getTitulo());
            prstm.setString(6, predioHist.getDocumento().getPath());
            prstm.setString(7, predioHist.getDocumento().getTipo());
            prstm.setString(8, predioHist.getDocumento().getKeywords());
            prstm.setDate(9, new java.sql.Date(predioHist.getDocumento().getFechaDocumento().getTime()));
            prstm.setInt(10, predioHist.getDocumento().getTipoDocumento().getIdTipo());
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

    public static List<PredioHistorico> obtenerPredios() throws Exception {
        List<PredioHistorico> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PredioHistorico predioHist;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_buscar_predios_historicos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                predioHist = new PredioHistorico();
                predioHist.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                predioHist.setDocumento(FDocumento.obtenerDocumentoDadoCodigo(resultSet.getInt("int_id_documento")));
                predioHist.setObservaciones(resultSet.getString("chv_observaciones"));
                predioHist.setRazonEliminacion(resultSet.getString("chv_motivo_eliminacion"));
                predioHist.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                predioHist.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                predioHist.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                lst.add(predioHist);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
