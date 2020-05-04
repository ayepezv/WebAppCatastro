package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Bloque2;
import catastro.logica.entidades.Piso2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;

public class FuncionesPiso {

    public static ArrayList<Piso2> obtenerPisoDadoBloque(int bloque) throws Exception {
        ArrayList<Piso2> lst = new ArrayList<>();
        Piso2 piso;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_pisos_dado_bloque_v2(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, bloque);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                piso = new Piso2();
                piso.setBloque(new Bloque2());
                piso.setBloque(FuncionesBloque.obtenerBloqueDadoCodigo(resultSet.getInt("int_id_bloque")));
                piso.setIdPiso(resultSet.getInt("sr_id_piso"));
                piso.setAnioConstruccion(resultSet.getInt("int_anio_construc"));
                piso.setAreaConstruccion(resultSet.getDouble("db_area_construcc"));
                piso.setEstadoPiso(resultSet.getString("chv_estado_piso"));
                piso.setEstructCimientos(resultSet.getString("chv_estruct_cimientos"));
                piso.setEstructColumnas(resultSet.getString("chv_estruct_columnas"));
                piso.setEstructVigas(resultSet.getString("chv_estruct_vigas"));
                piso.setEstructEntrepisos(resultSet.getString("chv_estruct_entrepisos"));
                piso.setEstructParedes(resultSet.getString("chv_estruct_paredes"));
                piso.setEstructCubierta(resultSet.getString("chv_estruct_cubierta"));
                piso.setEstructEscaleras(resultSet.getString("chv_estruct_escaleras"));
                piso.setAcabPisos(resultSet.getString("chv_acab_pisos"));
                piso.setAcabPuertas(resultSet.getString("chv_acab_puertas"));
                piso.setAcabVentanas(resultSet.getString("chv_acab_ventanas"));
                piso.setAcabCubrevent(resultSet.getString("chv_acab_cubrevent"));
                piso.setAcabTumbados(resultSet.getString("chv_acab_tumbados"));
                piso.setAcabCubierta(resultSet.getString("chv_acab_cubierta"));
                piso.setAcabBanios(resultSet.getString("chv_acab_banios"));
                piso.setAcabCocina(resultSet.getString("chv_acab_cocina"));
                piso.setAcabClosets(resultSet.getString("chv_acab_closets"));
                piso.setAcabRevInterior(resultSet.getString("chv_acab_rev_interior"));
                piso.setAcabRevExt(resultSet.getString("chv_acab_rev_ext"));
                piso.setPiso(resultSet.getString("chv_piso"));
                piso.setDescripcion(resultSet.getString("chv_descripcion"));
                piso.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                piso.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                piso.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                piso.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(piso);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarPiso(Piso2 piso) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_piso_v2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, piso.getBloque().getIdBloque());
            prstm.setInt(2, piso.getAnioConstruccion());
            prstm.setDouble(3, piso.getAreaConstruccion());
            prstm.setString(4, piso.getEstadoPiso());
            prstm.setString(5, piso.getEstructCimientos());
            prstm.setString(6, piso.getEstructColumnas());
            prstm.setString(7, piso.getEstructVigas());
            prstm.setString(8, piso.getEstructEntrepisos());
            prstm.setString(9, piso.getEstructParedes());
            prstm.setString(10, piso.getEstructCubierta());
            prstm.setString(11, piso.getEstructEscaleras());
            prstm.setString(12, piso.getAcabPisos());
            prstm.setString(13, piso.getAcabPuertas());
            prstm.setString(14, piso.getAcabVentanas());
            prstm.setString(15, piso.getAcabCubrevent());
            prstm.setString(16, piso.getAcabTumbados());
            prstm.setString(17, piso.getAcabCubierta());
            prstm.setString(18, piso.getAcabBanios());
            prstm.setString(19, piso.getAcabCocina());
            prstm.setString(20, piso.getAcabClosets());
            prstm.setString(21, piso.getAcabRevInterior());
            prstm.setString(22, piso.getAcabRevExt());
            prstm.setString(23, piso.getPiso());
            prstm.setString(24, piso.getDescripcion());
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

    public static String actualizarPiso(Piso2 piso) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_piso_v2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, piso.getIdPiso());
            prstm.setInt(2, piso.getBloque().getIdBloque());
            prstm.setInt(3, piso.getAnioConstruccion());
            prstm.setDouble(4, piso.getAreaConstruccion());
            prstm.setString(5, piso.getEstadoPiso());
            prstm.setString(6, piso.getEstructCimientos());
            prstm.setString(7, piso.getEstructColumnas());
            prstm.setString(8, piso.getEstructVigas());
            prstm.setString(9, piso.getEstructEntrepisos());
            prstm.setString(10, piso.getEstructParedes());
            prstm.setString(11, piso.getEstructCubierta());
            prstm.setString(12, piso.getEstructEscaleras());
            prstm.setString(13, piso.getAcabPisos());
            prstm.setString(14, piso.getAcabPuertas());
            prstm.setString(15, piso.getAcabVentanas());
            prstm.setString(16, piso.getAcabCubrevent());
            prstm.setString(17, piso.getAcabTumbados());
            prstm.setString(18, piso.getAcabCubierta());
            prstm.setString(19, piso.getAcabBanios());
            prstm.setString(20, piso.getAcabCocina());
            prstm.setString(21, piso.getAcabClosets());
            prstm.setString(22, piso.getAcabRevInterior());
            prstm.setString(23, piso.getAcabRevExt());
            prstm.setString(24, piso.getPiso());
            prstm.setString(25, piso.getDescripcion());
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

    public static String eliminarPiso(int piso) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_desactivar_piso_v2(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, piso);
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

    public static String actualizarPisoAlfanumerico(Piso2 piso) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_actualizar_piso_v2_alfanumerico(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, piso.getIdPiso());
            prstm.setInt(2, piso.getAnioConstruccion());
            prstm.setString(3, piso.getEstadoPiso());
            prstm.setString(4, piso.getEstructCimientos());
            prstm.setString(5, piso.getEstructColumnas());
            prstm.setString(6, piso.getEstructVigas());
            prstm.setString(7, piso.getEstructEntrepisos());
            prstm.setString(8, piso.getEstructParedes());
            prstm.setString(9, piso.getEstructCubierta());
            prstm.setString(10, piso.getEstructEscaleras());
            prstm.setString(11, piso.getAcabPisos());
            prstm.setString(12, piso.getAcabPuertas());
            prstm.setString(13, piso.getAcabVentanas());
            prstm.setString(14, piso.getAcabCubrevent());
            prstm.setString(15, piso.getAcabTumbados());
            prstm.setString(16, piso.getAcabCubierta());
            prstm.setString(17, piso.getAcabBanios());
            prstm.setString(18, piso.getAcabCocina());
            prstm.setString(19, piso.getAcabClosets());
            prstm.setString(20, piso.getAcabRevInterior());
            prstm.setString(21, piso.getAcabRevExt());
            prstm.setString(22, piso.getPiso());
            prstm.setString(23, piso.getDescripcion());
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

    public static String eliminarPisosGlobal(Piso2 piso, Usuario sessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_eliminar_piso(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, piso.getIdPiso());
            prstm.setInt(2, sessionUsuario.getIdPersona());
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

    public static Piso2 obtenerPisoDadoId(int codigo) throws Exception {
        Piso2 piso = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_piso_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                piso = new Piso2();
                piso.setBloque(new Bloque2());
                // piso.setBloque(FuncionesBloque.obtenerBloqueDadoCodigo(resultSet.getInt("int_id_bloque")));
                piso.setIdPiso(resultSet.getInt("sr_id_piso"));
                piso.setAnioConstruccion(resultSet.getInt("int_anio_construc"));
                piso.setAreaConstruccion(resultSet.getDouble("db_area_construcc"));
                piso.setEstadoPiso(resultSet.getString("chv_estado_piso"));
                piso.setEstructCimientos(resultSet.getString("chv_estruct_cimientos"));
                piso.setEstructColumnas(resultSet.getString("chv_estruct_columnas"));
                piso.setEstructVigas(resultSet.getString("chv_estruct_vigas"));
                piso.setEstructEntrepisos(resultSet.getString("chv_estruct_entrepisos"));
                piso.setEstructParedes(resultSet.getString("chv_estruct_paredes"));
                piso.setEstructCubierta(resultSet.getString("chv_estruct_cubierta"));
                piso.setEstructEscaleras(resultSet.getString("chv_estruct_escaleras"));
                piso.setAcabPisos(resultSet.getString("chv_acab_pisos"));
                piso.setAcabPuertas(resultSet.getString("chv_acab_puertas"));
                piso.setAcabVentanas(resultSet.getString("chv_acab_ventanas"));
                piso.setAcabCubrevent(resultSet.getString("chv_acab_cubrevent"));
                piso.setAcabTumbados(resultSet.getString("chv_acab_tumbados"));
                piso.setAcabCubierta(resultSet.getString("chv_acab_cubierta"));
                piso.setAcabBanios(resultSet.getString("chv_acab_banios"));
                piso.setAcabCocina(resultSet.getString("chv_acab_cocina"));
                piso.setAcabClosets(resultSet.getString("chv_acab_closets"));
                piso.setAcabRevInterior(resultSet.getString("chv_acab_rev_interior"));
                piso.setAcabRevExt(resultSet.getString("chv_acab_rev_ext"));
                piso.setPiso(resultSet.getString("chv_piso"));
                piso.setDescripcion(resultSet.getString("chv_descripcion"));
                piso.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                piso.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                piso.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                piso.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return piso;
    }
}
