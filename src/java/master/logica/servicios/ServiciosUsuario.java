package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;

public class ServiciosUsuario {

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
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
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
                usuario.setRuc(resultSet.getString("chv_ruc"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("obtenerUsuariosDadoEstado dice: " + e.getMessage());
            throw e;
        }
        return lst;
    }

    public static String registrarUsuario(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_persona(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getNombres());
            prstm.setString(3, usuario.getApellidos());
            prstm.setString(4, usuario.getCelular());
            prstm.setString(5, usuario.getNick());
            prstm.setString(6, usuario.getMail());
            prstm.setString(7, usuario.getPassword());
            prstm.setString(8, usuario.getSexo());
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

    public static Usuario buscarUsuarioDadoCedula(String cedula) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_buscar_persona_usuario_dado_cedula(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, cedula);
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
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static String cambiarContrasenia(int idUsuario, String passwdActual, String nuevoPasswd, String verifPasswd) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_cambiar_contrasenia_usuario(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            prstm.setString(2, passwdActual);
            prstm.setString(3, nuevoPasswd);
            prstm.setString(4, verifPasswd);
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

    public static String actualizarContraseniaDadoId(Usuario usuario, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_actualizar_contraseña_dado_id(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setString(2, usuario.getPassword());
            prstm.setInt(3, idUsuario);
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

    public static String activarUsuarios(Usuario usuario, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_activar_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setInt(2, idUsuario);
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

    public static String desactivarUsuarios(Usuario usuario, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_desactivar_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setInt(2, idUsuario);
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

    public static ArrayList<Usuario> obtenerUsuariosDadoGrupoTrabajo(int idGrupoTrabajo) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_obtener_usuarios_dado_grupo_trabajo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idGrupoTrabajo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("int_id_usuario"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> encontrarUsuarioDadoRol(String criterio, int rol) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_encontrar_personas_dado_criterio_rol(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, criterio);
            prstm.setInt(2, rol);
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
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> obtenerUsuariosDelSistema() throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from master.f_obtener_usuarios_del_sistema();";
            resultSet = accesoDatos.ejecutaQuery(sql);
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
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;

    }

    public static String actualizarUsuario(Usuario usuario) throws Exception {
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

    public static String registrarPersonaNaturalRol(Usuario usuario, int idRol, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_registrar_persona_natural_dado_rol_v2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getNombres());
            prstm.setString(3, usuario.getApellidos());
            prstm.setString(4, usuario.getCelular());
            prstm.setString(5, usuario.getSexo());
            prstm.setDate(6, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(7, usuario.getEstadoCivil());
            prstm.setString(8, usuario.getRuc());
            prstm.setString(9, usuario.getDireccionDom());
            prstm.setInt(10, idUsuario);
            prstm.setInt(11, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(12, usuario.getNick());
            prstm.setString(13, usuario.getMail());
            prstm.setString(14, usuario.getPassword());
            prstm.setInt(15, idRol);
            prstm.setString(16, usuario.getCedulaConyuge());
            prstm.setString(17, usuario.getNombresConyuge());
            prstm.setString(18, usuario.getApellidosConyuge());
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

    public static ArrayList<Usuario> obtenerPersonasDadoTipo(String estado, int idTipo) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_usuarios_dado_tipo_estado(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            prstm.setInt(2, idTipo);
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
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.getTipoPersona().setIdTipoPersona(resultSet.getInt("id_tipo_persona"));
                usuario.getTipoPersona().setTipoPersona(resultSet.getString("chv_tipo_persona"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("obtenerUsuariosDadoEstado dice: " + e.getMessage());
            throw e;
        }
        return lst;
    }

    public static String registrarPersonaJuridicaRol(Usuario usuario, int idRol, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_registrar_persona_juridica_dado_rol(?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
//            prstm.setString(1, usuario.getCedula());
            prstm.setString(1, usuario.getNombres());
//            prstm.setString(3, usuario.getApellidos());
            prstm.setString(2, usuario.getCelular());
//            prstm.setString(5, usuario.getSexo());
            prstm.setDate(3, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
//            prstm.setString(7, usuario.getEstadoCivil());
            prstm.setString(4, usuario.getRuc());
            prstm.setString(5, usuario.getDireccionDom());
            prstm.setInt(6, idUsuario);
            prstm.setInt(7, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(8, usuario.getNick());
            prstm.setString(9, usuario.getMail());
            prstm.setString(10, usuario.getPassword());
            prstm.setInt(11, idRol);
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

    public static String registrarContribJuridico(Usuario usuario, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_registrar_contrib_persona_juridica_v2(?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getNombres());
            prstm.setString(2, usuario.getCelular());
            prstm.setDate(3, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(4, usuario.getRuc());
            prstm.setString(5, usuario.getDireccionDom());
            prstm.setInt(6, idUsuario);
            prstm.setInt(7, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(8, usuario.getNick());
            prstm.setString(9, usuario.getMail());
            prstm.setString(10, usuario.getPassword());
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

    public static String actualizarPersonas(Usuario usuario, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_aditar_persona_generico(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, usuario.getIdPersona());
            prstm.setString(2, usuario.getNick());
            prstm.setString(3, usuario.getMail());
            prstm.setString(4, usuario.getPassword());
            prstm.setInt(5, idUsuario);
            prstm.setString(6, usuario.getCedula());
            prstm.setString(7, usuario.getNombres());
            prstm.setString(8, usuario.getApellidos());
            prstm.setString(9, usuario.getCelular());
            prstm.setString(10, usuario.getSexo());
            prstm.setDate(11, recursos.ConvertirFechas.devolverFecha(usuario.getFechaNacimiento()));
            prstm.setString(12, usuario.getEstadoCivil());
            prstm.setString(13, usuario.getRuc());
            prstm.setString(14, usuario.getDireccionDom());
            prstm.setInt(15, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(16, usuario.getCedulaConyuge());
            prstm.setString(17, usuario.getNombresConyuge());
            prstm.setString(18, usuario.getApellidosConyuge());
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

    public static ArrayList<Usuario> encontrarPersonasDadoCriterioTipo(String parametro, int idTipo) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_find_encontrar_personas_dado_tipo_criterio(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
            prstm.setInt(2, idTipo);
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
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.getTipoPersona().setIdTipoPersona(resultSet.getInt("id_tipo_persona"));
                usuario.getTipoPersona().setTipoPersona(resultSet.getString("chv_tipo_persona"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                usuario.setCedulaConyuge(resultSet.getString("chv_cedula_conyuge"));
                usuario.setNombresConyuge(resultSet.getString("chv_nombres_conyuge"));
                usuario.setApellidosConyuge(resultSet.getString("chv_apellidos_conyuge"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("obtenerUsuariosDadoEstado dice: " + e.getMessage());
            throw e;
        }
        return lst;
    }

    public static ArrayList<Usuario> encontrarPersonasDadoCriterio(String parametro) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_find_encontrar_personas_dado_criterio(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, parametro);
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
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
                usuario.getTipoPersona().setIdTipoPersona(resultSet.getInt("id_tipo_persona"));
                usuario.getTipoPersona().setTipoPersona(resultSet.getString("chv_tipo_persona"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("obtenerUsuariosDadoEstado dice: " + e.getMessage());
            throw e;
        }
        return lst;
    }

    public static Usuario buscarUsuarioParaSession(int idUsuario) throws Exception {
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
//                usuario.setCelular(resultSet.getString("chv_celular"));
//                usuario.setFoto(resultSet.getString("chv_foto"));
//                usuario.setCedula(resultSet.getString("chv_cedula"));
//                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setNick(resultSet.getString("chv_nick"));
//                usuario.setMail(resultSet.getString("chv_mail"));
//                usuario.setPassword(resultSet.getString("chv_password"));
//                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
//                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
//                usuario.setDireccionDom(resultSet.getString("chv_direccion_domiciliaria"));
//                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
//                usuario.setSexo(resultSet.getString("ch_sexo"));
//                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
}
