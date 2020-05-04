package catastro.cem.funciones;

import accesoDatos.AccesoDatos;
import catastro.cem.entidades.BeneficiariosObras;
import catastro.logica.servicios.FuncionesPredio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FBenefDirectos {

    public static List<BeneficiariosObras> obtenerBenefDirectosDadoObra(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_prev_beneficiarios_obras(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
                benef.setNumBeneficiario(resultSet.getInt("_num_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("_id_obra")));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                benef.setFrente(resultSet.getDouble("_db_frente"));
                benef.setAreaTerreno(resultSet.getDouble("_db_area_terreno"));
                benef.setAreaConstruccion(resultSet.getDouble("_db_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("_db_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("_db_avaluo_terreno"));
                benef.setAnioAvaluo(resultSet.getInt("_int_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("_db_valor_mejora"));

                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarBeneficiarioDirecto(BeneficiariosObras beneficiario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_registrar_benef_directo(?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, beneficiario.getNumBeneficiario());
            prstm.setInt(2, beneficiario.getObra().getIdObra());
            prstm.setInt(3, beneficiario.getPredio().getIdPredio());
            prstm.setDouble(4, beneficiario.getFrente());
            prstm.setDouble(5, beneficiario.getAreaTerreno());
            prstm.setDouble(6, beneficiario.getAreaConstruccion());
            prstm.setDouble(7, beneficiario.getAvaluoTerreno());
            prstm.setDouble(8, beneficiario.getAvaluo());
            prstm.setInt(9, beneficiario.getAnioAvaluo());
            prstm.setDouble(10, beneficiario.getValorMejora());
            prstm.setInt(11, beneficiario.getObra().getMejora().getIdMejora());
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

    public static List<BeneficiariosObras> previsualizarBeneficiariosDirectos(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_beneficiarios_directos_dado_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
                benef.setNumBeneficiario(resultSet.getInt("_num_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("_id_obra")));
                //benef.getObra().setIdObra(resultSet.getInt("_id_obra"));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                benef.setFrente(resultSet.getDouble("_db_frente"));
                benef.setAreaTerreno(resultSet.getDouble("_db_area_terreno"));
                benef.setAreaConstruccion(resultSet.getDouble("_db_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("_db_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("_db_avaluo_terreno"));
                benef.setAnioAvaluo(resultSet.getInt("_int_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("_db_valor_mejora"));
                benef.setTipo(resultSet.getString("_ch_tipo"));
                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<BeneficiariosObras> previsualizarBeneficiariosIndirectos(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_beneficiarios_indirectos_dado_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
                benef.setNumBeneficiario(resultSet.getInt("_num_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("_id_obra")));
                //benef.getObra().setIdObra(resultSet.getInt("_id_obra"));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
                benef.setFrente(resultSet.getDouble("_db_frente"));
                benef.setAreaTerreno(resultSet.getDouble("_db_area_terreno"));
                benef.setAreaConstruccion(resultSet.getDouble("_db_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("_db_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("_db_avaluo_terreno"));
                benef.setAnioAvaluo(resultSet.getInt("_int_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("_db_valor_mejora"));
                benef.setTipo(resultSet.getString("_ch_tipo"));
                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static List<BeneficiariosObras> previsualizarBeneficiariosDadoObra(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_todos_beneficiarios_dado_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
                benef.setNumBeneficiario(resultSet.getInt("pnum_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("pid_obra")));
                benef.getObra().setValorDirecto(resultSet.getDouble("pdb_valor_metro"));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("pid_predio")));
                benef.setFrente(resultSet.getDouble("pdb_frente"));
                benef.setAreaTerreno(resultSet.getDouble("pdb_area_terreno"));
                benef.setAreaConstruccion(resultSet.getDouble("pdb_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("pdb_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("pdb_avaluo_terreno"));
                benef.setAnioAvaluo(resultSet.getInt("pint_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("pdb_valor_mejora"));
                benef.setTipo(resultSet.getString("pch_tipo"));
                benef.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("pid_mejora")));
                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String insertarBeneficiario(BeneficiariosObras beneficiario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from catastro.f_insertar_beneficiarios_obra_v2(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, beneficiario.getNumBeneficiario());
            prstm.setInt(2, beneficiario.getObra().getIdObra());
            prstm.setInt(3, beneficiario.getPredio().getIdPredio());
            prstm.setDouble(4, beneficiario.getFrente());
            prstm.setDouble(5, beneficiario.getAreaTerreno());
            prstm.setDouble(6, beneficiario.getAreaConstruccion());
            prstm.setDouble(7, beneficiario.getAvaluoTerreno());
            prstm.setDouble(8, beneficiario.getAvaluo());
            prstm.setInt(9, beneficiario.getAnioAvaluo());
            prstm.setDouble(10, beneficiario.getValorMejora());
            prstm.setInt(11, beneficiario.getObra().getMejora().getIdMejora());
            prstm.setString(12, beneficiario.getTipo());
            prstm.setInt(13, beneficiario.getSessionUsuario().getIdPersona());
            prstm.setDouble(14, beneficiario.getFactor());
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

    public static List<BeneficiariosObras> obtenerBeneficiariosDadoObra(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_obtener_todos_beneficiarios_registrados_dado_obra(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
                benef.setIdBeneficiario(resultSet.getInt("sr_id_beneficiario"));
                benef.setNumBeneficiario(resultSet.getInt("num_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("id_obra")));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("id_predio")));
                benef.setFrente(resultSet.getDouble("db_frente"));
                benef.setAreaTerreno(resultSet.getDouble("db_area_terreno"));
                benef.setAreaConstruccion(resultSet.getDouble("db_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("db_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("db_avaluo_terreno"));
                benef.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("db_valor_mejora"));
                benef.setTipo(resultSet.getString("ch_tipo"));
                benef.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("id_mejora")));
                benef.setFactor(resultSet.getDouble("db_factor"));
                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    
    public static List<BeneficiariosObras> obtenerBeneficiariosDirectosV1(int obra) throws Exception {
        List<BeneficiariosObras> lst = new ArrayList<>();
        BeneficiariosObras benef;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_previsualizar_beneficiarios_directos_v1(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, obra);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                benef = new BeneficiariosObras();
//                benef.setIdBeneficiario(resultSet.getInt("sr_id_beneficiario"));
                benef.setNumBeneficiario(resultSet.getInt("_num_beneficiario"));
                benef.setObra(FObra.obtenerObraDadoCodigo(resultSet.getInt("_id_obra")));
                benef.setPredio(FuncionesPredio.obtenerPredioDadoCodigo(resultSet.getInt("_id_predio")));
//                benef.setFrente(resultSet.getDouble("db_frente"));
//                benef.setAreaTerreno(resultSet.getDouble("db_area_terreno"));
//                benef.setAreaConstruccion(resultSet.getDouble("db_area_construccion"));
                benef.setAvaluo(resultSet.getDouble("_db_valor_avaluo"));
                benef.setAvaluoTerreno(resultSet.getDouble("_db_avaluo_terreno"));
//                benef.setAnioAvaluo(resultSet.getInt("int_anio_avaluo"));
                benef.setValorMejora(resultSet.getDouble("_db_valor_mejora"));
                benef.setTipo(resultSet.getString("_ch_tipo"));
//                benef.setMejora(FMejora.obtenerMejoraDadoCodigo(resultSet.getInt("id_mejora")));
//                benef.setFactor(resultSet.getDouble("db_factor"));
                lst.add(benef);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
