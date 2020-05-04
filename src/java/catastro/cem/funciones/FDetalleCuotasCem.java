package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.DetalleCuotasCem;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.funciones.FTransaccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.servicios.ServiciosUsuario;

public class FDetalleCuotasCem {

    public static String registrarCuotasCem(int idObra) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_generar_cuotas_benef_obras(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idObra);
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

    public static String registrarCuotasCemV2(int idObra, int usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_generar_cuotas_cem_dado_obra(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idObra);
            prstm.setInt(2, usuario);
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

    public static List<DetalleCuotasCem> obtenerCuotasDadoObra(int obra) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_cuotas_dado_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                detalle.setCuota(resultSet.getDouble("db_cuota"));
                detalle.setPago(resultSet.getString("pago"));
                detalle.setFechaPago(resultSet.getDate("dt_fecha_pagar"));
                detalle.setValorRestante(resultSet.getDouble("db_valor_restante"));
                detalle.setPagado(resultSet.getBoolean("bl_pagado"));
                detalle.setFechaCobro(resultSet.getTimestamp("ts_fecha_cobro"));
                detalle.setTransaccion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("id_transaccion")));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                detalle.setObservaciones(resultSet.getString("txt_observacion"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    
    public static List<DetalleCuotasCem> obtenerCuotasDadoObraPredio(int obra,int idPredio) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_cuotas_dado_obra_predio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            prstm.setInt(2, idPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                detalle.setCuota(resultSet.getDouble("db_cuota"));
                detalle.setPago(resultSet.getString("pago"));
                detalle.setFechaPago(resultSet.getDate("dt_fecha_pagar"));
                detalle.setValorRestante(resultSet.getDouble("db_valor_restante"));
                detalle.setPagado(resultSet.getBoolean("bl_pagado"));
                detalle.setFechaCobro(resultSet.getTimestamp("ts_fecha_cobro"));
                detalle.setTransaccion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("id_transaccion")));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                detalle.setObservaciones(resultSet.getString("txt_observacion"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<DetalleCuotasCem> obtenerCuotasDadoObraEstado(int obra, boolean estado) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_cuotas_dado_obra_estado(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            prstm.setBoolean(2, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                detalle.setCuota(resultSet.getDouble("db_cuota"));
                detalle.setPago(resultSet.getString("pago"));
                detalle.setFechaPago(resultSet.getDate("dt_fecha_pagar"));
                detalle.setValorRestante(resultSet.getDouble("db_valor_restante"));
                detalle.setPagado(resultSet.getBoolean("bl_pagado"));
                detalle.setFechaCobro(resultSet.getTimestamp("ts_fecha_cobro"));
                detalle.setTransaccion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("id_transaccion")));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                detalle.setObservaciones(resultSet.getString("txt_observacion"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<DetalleCuotasCem> obtenerCuotasDadoPredioEstado(int predio, boolean estado) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_cuotas_dado_predio_estado(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio);
            prstm.setBoolean(2, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                detalle.setCuota(resultSet.getDouble("db_cuota"));
                detalle.setPago(resultSet.getString("pago"));
                detalle.setFechaPago(resultSet.getDate("dt_fecha_pagar"));
                detalle.setValorRestante(resultSet.getDouble("db_valor_restante"));
                detalle.setPagado(resultSet.getBoolean("bl_pagado"));
                detalle.setFechaCobro(resultSet.getTimestamp("ts_fecha_cobro"));
                detalle.setTransaccion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("id_transaccion")));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                detalle.setObservaciones(resultSet.getString("txt_observacion"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String cobrarCuotasCem(int idCliente, int idSessionUsuario, int idFormaPago, int idCuotaCem) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_recaudar_cem_transaccion(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idCliente);
            prstm.setInt(2, idSessionUsuario);
            prstm.setInt(3, idFormaPago);
            prstm.setInt(4, idCuotaCem);
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

    public static List<DetalleCuotasCem> consultarCuotasCemPendientesDadoPpredio(int predio) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_consultar_cuotas_cem_pagar_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, predio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("_sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("_ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("_int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("_id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                detalle.setCuota(resultSet.getDouble("_db_cuota"));
                detalle.setPago(resultSet.getString("_pago"));
                detalle.setFechaPago(resultSet.getDate("_dt_fecha_pagar"));
                detalle.setPagado(resultSet.getBoolean("_bl_pagado"));
                detalle.setInteres(resultSet.getDouble("_db_intereses_mora"));
                detalle.setTotalPagar(resultSet.getDouble("_db_total_pagar"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<DetalleCuotasCem> obtenerCuotasPagadasDadoPredio(int idPredio) throws Exception {
        List<DetalleCuotasCem> lst = new ArrayList<>();
        DetalleCuotasCem detalle;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_cuotas_cem_pagadas_dado_predio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idPredio);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                detalle = new DetalleCuotasCem();
                detalle.setCodigo(resultSet.getInt("sr_codigo"));
                detalle.setFechaEmision(resultSet.getTimestamp("ts_fecha_emision"));
                detalle.setNumCouta(resultSet.getInt("int_num_couta"));
                detalle.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                detalle.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                detalle.setCuota(resultSet.getDouble("db_cuota"));
                detalle.setPago(resultSet.getString("pago"));
                detalle.setFechaPago(resultSet.getDate("dt_fecha_pagar"));
                detalle.setValorRestante(resultSet.getDouble("db_valor_restante"));
                detalle.setPagado(resultSet.getBoolean("bl_pagado"));
                detalle.setFechaCobro(resultSet.getTimestamp("ts_fecha_cobro"));
                detalle.setTransaccion(FTransaccion.obtenerTransaccionDadoCodigo(resultSet.getInt("id_transaccion")));
                detalle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                detalle.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("session_usuario")));
                detalle.setObservaciones(resultSet.getString("txt_observacion"));
                detalle.setInteres(resultSet.getDouble("db_intereses_mora"));
                detalle.setTotalPagar(resultSet.getDouble("db_total_pagar"));
                lst.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
