package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny
 */
public class ServiciosLogin {

    public static Usuario loginUsuario(String correo, String clave) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_login_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, correo);
            prstm.setString(2, clave);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));            
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    
    public static Usuario ObtenerUsuario(int codigo) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_login_usuario_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
          
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));     
   
    
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    public static String actualizarFoto(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_foto(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setString(2, usuario.getFoto());
            
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
    
    public static String actualizarPerfil(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_perfil(?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setString(2, usuario.getCedula());
            prstm.setString(3, usuario.getNombres());
            prstm.setString(4, usuario.getApellidos());
            prstm.setString(5, usuario.getCelular());
            prstm.setString(6, usuario.getNick());
            prstm.setString(7, usuario.getMail());
            prstm.setString(8, usuario.getSexo());
            prstm.setDate(9, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(10, usuario.getEstadoCivil());
            prstm.setString(11, usuario.getRuc());
            prstm.setString(12, usuario.getDireccionDom());
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
}
