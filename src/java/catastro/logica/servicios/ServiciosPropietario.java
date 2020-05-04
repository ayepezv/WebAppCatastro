/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosRolUsuario;
import master.presentacion.beans.RolUsuarioCtrl;

/**
 *
 * @author ayepe
 */
public class ServiciosPropietario {

    public static Usuario buscarUsuarioDadoId(int idUsuario) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_seleccionar_usuario_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static ArrayList<Usuario> obtenerUsuariosDadoEstado(String estado) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_usuarios(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
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
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));

                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> obtenerPropietariosDadoEstado(String estado) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_propietarios(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
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
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));

                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> encontrarPropietarios(String estado, String parametro) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_propietarios_parametro(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            prstm.setString(2, parametro);
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
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));

                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarPropietario(Usuario usuario, int idSessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_propietario(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getNombres());
            prstm.setString(3, usuario.getApellidos());
            prstm.setString(4, usuario.getCelular());
            prstm.setString(5, usuario.getNick());
            prstm.setString(6, usuario.getMail());
            prstm.setString(7, usuario.getPassword());
            prstm.setString(8, usuario.getSexo());
            prstm.setDate(9, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(10, usuario.getEstadoCivil());
            prstm.setString(11, usuario.getRuc());
            prstm.setString(12, usuario.getDireccionDom());
            prstm.setInt(13, idSessionUsuario);
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

    public static String actualizarPropietario(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_propietario(?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setString(2, usuario.getCedula());
            prstm.setString(3, usuario.getNombres());
            prstm.setString(4, usuario.getApellidos());
            prstm.setString(5, usuario.getCelular());
            prstm.setString(6, usuario.getSexo());
            prstm.setDate(7, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(8, usuario.getEstadoCivil());
            prstm.setString(9, usuario.getRuc());
            prstm.setString(10, usuario.getDireccionDom());
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

    public static String eliminarPropietario(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_eliminar_propietario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
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

    public static ArrayList<Usuario> encontrarPropietariosDadoTipo(String estado, int tipo) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_propietarios_dado_tipo(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            prstm.setInt(2, tipo);
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
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.getTipoPersona().setIdTipoPersona(resultSet.getInt("id_tipo_persona"));
                usuario.getTipoPersona().setTipoPersona(resultSet.getString("chv_tipo_persona"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> encontrarPropietariosDadoTipoParametro(String estado, String parametro, int tipo) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_buscar_propietarios_parametro_tipo(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            prstm.setString(2, parametro);
            prstm.setInt(3, tipo);
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
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.getTipoPersona().setIdTipoPersona(resultSet.getInt("id_tipo_persona"));
                usuario.getTipoPersona().setTipoPersona(resultSet.getString("chv_tipo_persona"));
                usuario.setCedulaConyuge(resultSet.getString("chv_cedula_conyuge"));
                usuario.setNombresConyuge(resultSet.getString("chv_nombres_conyuge"));
                usuario.setApellidosConyuge(resultSet.getString("chv_apellidos_conyuge"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
