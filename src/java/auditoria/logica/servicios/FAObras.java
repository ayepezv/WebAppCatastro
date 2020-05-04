package auditoria.logica.servicios;

import accesoDatos.AccesoDatos;
import auditoria.logica.entidades.AObras;
import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.TipoObra;
import catastro.cem.funciones.FMejora;
import catastro.cem.funciones.FTipoObra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

public class FAObras {

    public static ArrayList<AObras> obtenerAuditoriaObras() throws Exception {
        ArrayList<AObras> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        AObras obra;
        ResultSet resultSet;
        String sql;

        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM auditoria.f_seleccionar_obras()";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                obra = new AObras();
                obra.setTipoObra(new TipoObra());
                obra.setMejora(new Mejora());
                obra.setUsuarioSistema(new Usuario());
                obra.setIdAudObras(resultSet.getInt("id_aud_obra"));
                obra.setIdObra(resultSet.getInt("int_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorcentajeMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorcentajeBenefDirecto(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirecto(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvalBenefDirecto(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorcentajeBenefIndirecto(resultSet.getDouble("db_porct_benef_indirect"));
                obra.setFrenteBenefIndirecto(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvalBenefIndirecto(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                obra.setHoraEvento(resultSet.getTime("tm_hora"));
                obra.setAccionEjecutada(resultSet.getString("chv_accion_ejecutada"));
                lst.add(obra);
            }

        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<AObras> comparacionObras(int codigoObra) throws Exception {
        ArrayList<AObras> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        AObras obra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM auditoria.f_seleccionar_obras_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigoObra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                obra = new AObras();
                obra.setIdAudObras(resultSet.getInt("id_aud_obra"));
                obra.setIdObra(resultSet.getInt("int_id_obra"));
                obra.setNombreObra(resultSet.getString("chv_nombre_obra"));
                obra.setTipoObra(FTipoObra.obtenerTipoObraDadoCodigo(resultSet.getInt("int_id_tipo_obra")));
                obra.setExcluyente(resultSet.getBoolean("bl_excluyente"));
                obra.setValorObra(resultSet.getDouble("db_valor_obra"));
                obra.setValorDirecto(resultSet.getDouble("db_valor_directo"));
                obra.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("int_id_mejora")));
                obra.setPorcentajeMunicipio(resultSet.getDouble("db_porct_municipio"));
                obra.setPorcentajeBenefDirecto(resultSet.getDouble("db_porct_benef_direct"));
                obra.setFrenteBenefDirecto(resultSet.getDouble("db_frente_benef_direct"));
                obra.setAvalBenefDirecto(resultSet.getDouble("db_avaluo_benef_direct"));
                obra.setPorcentajeBenefIndirecto(resultSet.getDouble("db_porct_benef_indirect"));
                obra.setFrenteBenefIndirecto(resultSet.getDouble("db_frent_benef_indirect"));
                obra.setAvalBenefIndirecto(resultSet.getDouble("db_avaluo_benef_indirect"));
                obra.setAreaLoteMedio(resultSet.getDouble("db_area_lote_medio"));
                obra.setAreaConstrucMedia(resultSet.getDouble("db_area_construc_media"));
                obra.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                obra.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                obra.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                obra.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                obra.setUsuarioSistema(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
                obra.setFechaCreacion(resultSet.getTimestamp("ts_fecha_creacion"));
                obra.setHoraEvento(resultSet.getTime("tm_hora"));
                obra.setAccionEjecutada(resultSet.getString("chv_accion_ejecutada"));
                lst.add(obra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
