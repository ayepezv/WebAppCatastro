package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.TransferenciaDominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FTransferenciaDominio {

    public static List<TransferenciaDominio> obtenerTransferenciasDominio() throws Exception {
        List<TransferenciaDominio> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        TransferenciaDominio transferencia;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_transferencias_dominio();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                transferencia = new TransferenciaDominio();
                transferencia.setIdTransferencia(resultSet.getInt("sr_id_transferencia"));
                transferencia.setPropietario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_propietario")));
                transferencia.setComprador(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_comprador")));
                transferencia.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("int_id_predio")));
                transferencia.setTipoTocumento(resultSet.getString("chv_tipo_documento"));
                transferencia.setNumeroDocumento(resultSet.getString("chv_numero_documento"));
                transferencia.setFechaDocumento(resultSet.getDate("dt_fecha_documento"));
                transferencia.setObservaciones(resultSet.getString("txt_observaciones"));
                transferencia.setExcencionesEsp(resultSet.getString("chv_excenciones_esp"));
                transferencia.setRazonExcencion(resultSet.getString("chv_razon_excencion"));
                transferencia.setFormaAdquisicion(resultSet.getString("chv_forma_adquis"));
                transferencia.setNotaria(resultSet.getString("chv_notaria"));
                transferencia.setFechaEscritura(resultSet.getDate("dt_fecha_escritura"));
                transferencia.setFechaRegistroTitulo(resultSet.getDate("dt_fecha_registro"));
                transferencia.setAreaEscritura(resultSet.getDouble("db_area_escritura"));
                transferencia.setAvaluoProp(resultSet.getDouble("db_avaluo_prop"));
                transferencia.setPrestamo(resultSet.getString("chv_prestamo"));
                transferencia.setEntidad(resultSet.getString("chv_entidad"));
                transferencia.setObjeto(resultSet.getString("chv_objeto"));
                transferencia.setMontoCredito(resultSet.getDouble("db_monto"));
                transferencia.setPlazo(resultSet.getInt("int_plazo"));
                transferencia.setFechaConsec(resultSet.getDate("dt_fecha_consec"));
                transferencia.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                transferencia.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                transferencia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                transferencia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(transferencia);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarTransferencia(TransferenciaDominio transferencia) throws Exception {
        String respuesta;
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
            prstm.setDate(13, new java.sql.Date(transferencia.getFechaRegistro().getTime()));
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
                respuesta = resultSet.getString(1);
                System.out.println("respuesta: " + respuesta);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("exception " + e.getMessage());
            throw e;
        }
    }

    public static String registrar(TransferenciaDominio transferencia, double area, double avaluo, double montoCredito) throws Exception {
        String respuesta;
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
            prstm.setDate(13, new java.sql.Date(transferencia.getFechaRegistro().getTime()));
            prstm.setDouble(14, area);
            prstm.setDouble(15, transferencia.getAvaluoProp());
            prstm.setString(16, transferencia.getPrestamo());
            prstm.setString(17, transferencia.getEntidad());
            prstm.setString(18, transferencia.getObjeto());
            prstm.setDouble(19, montoCredito);
            prstm.setInt(20, transferencia.getPlazo());
            prstm.setDate(21, new java.sql.Date(transferencia.getFechaConsec().getTime()));
            prstm.setInt(22, transferencia.getSessionUsuario().getIdPersona());

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

    public static List<TransferenciaDominio> listarTransferenciasDominio() throws Exception {
        List<TransferenciaDominio> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        TransferenciaDominio transferencia;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_listar_transferencias_dominio();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                transferencia = new TransferenciaDominio();
                transferencia.setIdTransferencia(resultSet.getInt("_sr_id_transferencia"));
                //datos propietario
                transferencia.getPropietario().setIdPersona(resultSet.getInt("_int_id_propietario"));
                transferencia.getPropietario().setCedula(resultSet.getString("_chv_cedula_ruc_prop"));
                transferencia.getPropietario().setNombres(resultSet.getString("_chv_nombres_prop"));
                //datos comprador  
                transferencia.getComprador().setIdPersona(resultSet.getInt("_int_id_comprador"));
                transferencia.getComprador().setCedula(resultSet.getString("_chv_cedula_ruc_compr"));
                transferencia.getComprador().setNombres(resultSet.getString("_chv_nombres_compr"));
                //datos predio
                transferencia.getPredio().setIdPredio(resultSet.getInt("_int_id_predio"));
                transferencia.getPredio().setClaveCatastral(resultSet.getString("_chv_clave_catastral"));

                transferencia.setTipoTocumento(resultSet.getString("_chv_tipo_documento"));
                transferencia.setNumeroDocumento(resultSet.getString("_chv_numero_documento"));
                transferencia.setFechaDocumento(resultSet.getDate("_dt_fecha_documento"));
                transferencia.setObservaciones(resultSet.getString("_txt_observaciones"));
                transferencia.setExcencionesEsp(resultSet.getString("_chv_excenciones_esp"));
                transferencia.setRazonExcencion(resultSet.getString("_chv_razon_excencion"));
                transferencia.setFormaAdquisicion(resultSet.getString("_chv_forma_adquis"));
                transferencia.setNotaria(resultSet.getString("_chv_notaria"));
                transferencia.setFechaEscritura(resultSet.getDate("_dt_fecha_escritura"));
                transferencia.setFechaRegistroTitulo(resultSet.getDate("_dt_fecha_registro"));
                transferencia.setAreaEscritura(resultSet.getDouble("_db_area_escritura"));
                transferencia.setAvaluoProp(resultSet.getDouble("_db_avaluo_prop"));
                transferencia.setPrestamo(resultSet.getString("_chv_prestamo"));
                transferencia.setEntidad(resultSet.getString("_chv_entidad"));
                transferencia.setObjeto(resultSet.getString("_chv_objeto"));
                transferencia.setMontoCredito(resultSet.getDouble("_db_monto"));
                transferencia.setPlazo(resultSet.getInt("_int_plazo"));
                transferencia.setFechaConsec(resultSet.getDate("_dt_fecha_consec"));
                transferencia.setEstadoLogico(resultSet.getString("_ch_estado_logico"));
                transferencia.setFechaRegistro(resultSet.getTimestamp("_ts_fecha_registro"));
//                transferencia.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
//                transferencia.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(transferencia);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static TransferenciaDominio obtenerTransferenciaDadoCodigo(int codigo) throws Exception {
        TransferenciaDominio transferencia = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_listar_transferencias_dominio_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
               transferencia = new TransferenciaDominio();
                transferencia.setIdTransferencia(resultSet.getInt("_sr_id_transferencia"));
                //datos propietario
                transferencia.getPropietario().setIdPersona(resultSet.getInt("_int_id_propietario"));
                transferencia.getPropietario().setCedula(resultSet.getString("_chv_cedula_ruc_prop"));
                transferencia.getPropietario().setNombres(resultSet.getString("_chv_nombres_prop"));
                //datos comprador  
                transferencia.getComprador().setIdPersona(resultSet.getInt("_int_id_comprador"));
                transferencia.getComprador().setCedula(resultSet.getString("_chv_cedula_ruc_compr"));
                transferencia.getComprador().setNombres(resultSet.getString("_chv_nombres_compr"));
                //datos predio
                transferencia.getPredio().setIdPredio(resultSet.getInt("_int_id_predio"));
                transferencia.getPredio().setClaveCatastral(resultSet.getString("_chv_clave_catastral"));

                transferencia.setTipoTocumento(resultSet.getString("_chv_tipo_documento"));
                transferencia.setNumeroDocumento(resultSet.getString("_chv_numero_documento"));
                transferencia.setFechaDocumento(resultSet.getDate("_dt_fecha_documento"));
                transferencia.setObservaciones(resultSet.getString("_txt_observaciones"));
                transferencia.setExcencionesEsp(resultSet.getString("_chv_excenciones_esp"));
                transferencia.setRazonExcencion(resultSet.getString("_chv_razon_excencion"));
                transferencia.setFormaAdquisicion(resultSet.getString("_chv_forma_adquis"));
                transferencia.setNotaria(resultSet.getString("_chv_notaria"));
                transferencia.setFechaEscritura(resultSet.getDate("_dt_fecha_escritura"));
                transferencia.setFechaRegistroTitulo(resultSet.getDate("_dt_fecha_registro"));
                transferencia.setAreaEscritura(resultSet.getDouble("_db_area_escritura"));
                transferencia.setAvaluoProp(resultSet.getDouble("_db_avaluo_prop"));
                transferencia.setPrestamo(resultSet.getString("_chv_prestamo"));
                transferencia.setEntidad(resultSet.getString("_chv_entidad"));
                transferencia.setObjeto(resultSet.getString("_chv_objeto"));
                transferencia.setMontoCredito(resultSet.getDouble("_db_monto"));
                transferencia.setPlazo(resultSet.getInt("_int_plazo"));
                transferencia.setFechaConsec(resultSet.getDate("_dt_fecha_consec"));
                transferencia.setEstadoLogico(resultSet.getString("_ch_estado_logico"));
                transferencia.setFechaRegistro(resultSet.getTimestamp("_ts_fecha_registro")); 
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return transferencia;
    }

}
