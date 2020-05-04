package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.TransferenciaDominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FTransferencias {

    public static String registrar(TransferenciaDominio transferencia) throws Exception {
        String band;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_registrar_transferencia_dominio(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, transferencia.getPropietario().getIdPersona());
            prstm.setInt(2, transferencia.getComprador().getIdPersona());
            prstm.setInt(3, transferencia.getPredio().getIdPredio());
            prstm.setString(4, transferencia.getTipoTocumento());
            prstm.setString(5, transferencia.getNumeroDocumento());
            prstm.setDate(6, new java.sql.Date(transferencia.getFechaDocumento().getTime()));
            prstm.setString(7, transferencia.getObservaciones());
            prstm.setString(8, transferencia.getExcencionesEsp());
            prstm.setString(9, transferencia.getRazonExcencion());
            prstm.setString(10, transferencia.getFormaAdquisicion());
            prstm.setString(11, transferencia.getNotaria());
            prstm.setDate(12, new java.sql.Date(transferencia.getFechaEscritura().getTime()));
            prstm.setDate(13, new java.sql.Date(transferencia.getFechaRegistroTitulo().getTime()));
            prstm.setDouble(14, transferencia.getAreaEscritura());
            prstm.setDouble(15, transferencia.getAvaluoProp());
            
            prstm.setString(16, transferencia.getPrestamo());
            prstm.setString(17, transferencia.getEntidad());
            prstm.setString(18, transferencia.getObjeto());
            prstm.setDouble(19, transferencia.getMontoCredito());
            prstm.setInt(20, transferencia.getPlazo());
            prstm.setDate(21, new java.sql.Date(transferencia.getFechaConsec().getTime()));
            prstm.setInt(22, transferencia.getSessionUsuario().getIdPersona());
            
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                band = resultSet.getString(1);
                return band;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
