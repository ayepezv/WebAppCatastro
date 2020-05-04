package catastro.recaudacion.funciones;

import accesoDatos.AccesoDatos;
import catastro.recaudacion.entidades.ParametrosAdmin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;

public class FParametrosAdmin
{
  public static List<ParametrosAdmin> obtenerParametros()
    throws Exception
  {
    List<ParametrosAdmin> lst = new ArrayList();
    try
    {
      AccesoDatos accesoDatos = new AccesoDatos();
      String sql = "SELECT * from catastro.f_obtener_parametros_administrativos();";
      ResultSet resultSet = accesoDatos.ejecutaQuery(sql);
      while (resultSet.next())
      {
        ParametrosAdmin parametro = new ParametrosAdmin();
        parametro.setIdParametro(resultSet.getInt("sr_id_parametro"));
        parametro.setCodigo(resultSet.getString("chv_codigo"));
        parametro.setDescripcion(resultSet.getString("chv_descripcion"));
        parametro.setValor(resultSet.getDouble("db_valor"));
        parametro.setValorTextual(resultSet.getString("chv_valor_text"));
        parametro.setEstadoLogico(resultSet.getString("ch_estado_logico"));
        parametro.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
        parametro.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
        parametro.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
        parametro.setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("id_session_usuario")));
        lst.add(parametro);
      }
    }
    catch (Exception e)
    {
      throw e;
    }
    String sql;
    ResultSet resultSet;
    AccesoDatos accesoDatos;
    return lst;
  }
  
  public static String insertarParametro(ParametrosAdmin parametro)
    throws Exception
  {
    try
    {
      AccesoDatos accesoDatos = new AccesoDatos();
      String sql = "select * from catastro.f_insertar_parametro_administrativo(?,?,?,?,?)";
      PreparedStatement prstm = accesoDatos.creaPreparedSmt(sql);
      prstm.setString(1, parametro.getCodigo());
      prstm.setString(2, parametro.getDescripcion());
      prstm.setDouble(3, parametro.getValor());
      prstm.setString(4, parametro.getValorTextual());
      prstm.setInt(5, parametro.getSessionUsuario().getIdPersona());
      ResultSet resultSet = accesoDatos.ejecutaPrepared(prstm);
      if (resultSet.next()) {
        return resultSet.getString(1);
      }
      return null;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public static String editarParametro(ParametrosAdmin parametro)
    throws Exception
  {
    try
    {
      AccesoDatos accesoDatos = new AccesoDatos();
      String sql = "select * from catastro.f_actualizar_parametro(?,?,?,?,?,?)";
      PreparedStatement prstm = accesoDatos.creaPreparedSmt(sql);
      prstm.setInt(1, parametro.getIdParametro());
      prstm.setString(2, parametro.getCodigo());
      prstm.setString(3, parametro.getDescripcion());
      prstm.setDouble(4, parametro.getValor());
      prstm.setString(5, parametro.getValorTextual());
      prstm.setInt(6, parametro.getSessionUsuario().getIdPersona());
      ResultSet resultSet = accesoDatos.ejecutaPrepared(prstm);
      if (resultSet.next()) {
        return resultSet.getString(1);
      }
      return null;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public static String eliminarParametro(ParametrosAdmin parametro)
    throws Exception
  {
    try
    {
      AccesoDatos accesoDatos = new AccesoDatos();
      String sql = "select * from catastro.f_eliminar_parametro(?,?)";
      PreparedStatement prstm = accesoDatos.creaPreparedSmt(sql);
      prstm.setInt(1, parametro.getIdParametro());
      prstm.setInt(2, parametro.getSessionUsuario().getIdPersona());
      ResultSet resultSet = accesoDatos.ejecutaPrepared(prstm);
      if (resultSet.next()) {
        return resultSet.getString(1);
      }
      return null;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
}
