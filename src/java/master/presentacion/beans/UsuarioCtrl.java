package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Rol;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.TipoPersona;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosRolUsuario;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosTipoPersona;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class UsuarioCtrl implements Serializable {

    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<Usuario> lstUsuariosInactivos;
    private Usuario usuario;
    private Usuario usuarioSel;
    private RolUsuario rolUsuario;
    private int idRol;
    private ArrayList<Rol> lstRoles;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private int band;
    private Usuario objUsuario;
    private List<TipoPersona> lstTiposPersonas;
    private int idTipoPersona;
    private String msg;
    private String parametro;
    private boolean habilitado;

    public UsuarioCtrl() {
        habilitado = true;
        objUsuario = new Usuario();
        usuario = new Usuario();
        usuarioSel = new Usuario();
        rolUsuario = new RolUsuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        parametro = null;
        band = 0;
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerUsuarios();
        obtenerUsuariosInactivos();
        obtenerRoles();
        obtenerTiposPersonas();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerUsuarios() {
        try {
            setLstUsuarios(ServiciosUsuario.obtenerPersonasDadoTipo("A", getIdTipoPersona()));
            setParametro(null);
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void encontrarUsuarios() {
        try {
            setLstUsuarios(ServiciosUsuario.encontrarPersonasDadoCriterioTipo(getParametro(), getIdTipoPersona()));
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void obtenerTiposPersonas() {
        try {
            setLstTiposPersonas(ServiciosTipoPersona.obtenerTiposPersona());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerUsuariosInactivos() {
        try {
            setLstUsuariosInactivos(ServiciosUsuario.obtenerUsuariosDadoEstado("D"));
            System.out.println("obtenerUsuariosDadoEstado INGRESADO");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuariosInactivos() dice: " + e.getMessage());
        }
    }

    public void insertarUsuario() {
        try {
            setMsg(ServiciosUsuario.registrarUsuario(getUsuario()));
            Util.addSuccessMessage(getMsg());
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuario(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());
        }
    }

    public void registrarPersonaNatural() {
        try {
            System.out.println("Nick a registrar: " + objUsuario.getNick());

            setMsg(ServiciosUsuario.registrarPersonaNaturalRol(getObjUsuario(), getIdRol(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsg());
//            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuario(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoUsuario').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());

        }
    }

    public void registrarPersonaJuridica() {
        try {
            String msg = ServiciosUsuario.registrarPersonaJuridicaRol(getObjUsuario(), getIdRol(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuario(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoUsuario').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());

        }
    }

    public void insertarUsuarioDadoRol() {
        try {
            getRolUsuario().setIdRol(ServiciosRoles.obtenerRolDadoCodigo(getIdRol()));
            String msg = ServiciosRolUsuario.registrarUsuarioDadoRol(getRolUsuario(), getSessionUsuario().getIdPersona());
            setRolUsuario(new RolUsuario());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Exitosa", msg);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarUsuario').hide()");
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
        } catch (Exception e) {
            System.out.println("public void insertarUsuarioDadoRol()  dice: " + e.getMessage());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    public void obtenerRoles() {
        try {
            this.setLstRoles(ServiciosRoles.obtenerRolesDadoEstado("A"));
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizarContraseña() {
        try {
            String msgBd = ServiciosUsuario.actualizarContraseniaDadoId(getUsuarioSel(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msgBd);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuarioSel(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarContraseña() dice: " + e.getMessage());
        }
    }

    public void activarUsuario() {
        try {
            String msgBd = ServiciosUsuario.activarUsuarios(getUsuarioSel(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msgBd);
            obtenerUsuariosInactivos();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuarioSel(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void activarUsuario() dice: " + e.getMessage());
        }
    }

    public void desactivarUsuario() {
        try {
            String msgBd = ServiciosUsuario.desactivarUsuarios(getUsuarioSel(), getSessionUsuario().getIdPersona());
            Util.addSuccessMessage(msgBd);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuarioSel(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void desactivarUsuario() dice: " + e.getMessage());
        }
    }

    public void actualizarUsuario() {
        try {
            setMsg(ServiciosUsuario.actualizarPersonas(getUsuarioSel(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsg());
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuarioSel(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActPerNat').hide()");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActPerJur').hide()");
        } catch (Exception e) {
            System.out.println("public void actualizarUsuario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void verificarEstadoCivil() {
        try {
            if ("Casado(a)".equals(objUsuario.getEstadoCivil())) {
                habilitado = false;
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtCedulaConyuge");
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtNombresConyuge");
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtApellidosConyuge");
            } else if ("Casado(a)".equals(usuarioSel.getEstadoCivil())) {
                habilitado = false;
                RequestContext.getCurrentInstance().update("frmActPerNat:txtCedulaConyuge2");
                RequestContext.getCurrentInstance().update("frmActPerNat:txtNombresConyuge2");
                RequestContext.getCurrentInstance().update("frmActPerNat:txtApellidosConyuge2");
            } else {
                habilitado = true;
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtCedulaConyuge");
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtNombresConyuge");
                RequestContext.getCurrentInstance().update("frmNuevoRegistro:txtApellidosConyuge");
                RequestContext.getCurrentInstance().update("frmActPerNat:txtCedulaConyuge2");
                RequestContext.getCurrentInstance().update("frmActPerNat:txtNombresConyuge2");
                RequestContext.getCurrentInstance().update("frmActPerNat:txtApellidosConyuge2");
            }
        } catch (Exception e) {
            System.out.println("public void verificarEstadoCivil()  dice: " + e.getMessage());
            Util.addErrorMessage("public void verificarEstadoCivil() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the lstUsuarios
     */
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    /**
     * @param lstUsuarios the lstUsuarios to set
     */
    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    /**
     * @return the lstUsuariosInactivos
     */
    public ArrayList<Usuario> getLstUsuariosInactivos() {
        return lstUsuariosInactivos;
    }

    /**
     * @param lstUsuariosInactivos the lstUsuariosInactivos to set
     */
    public void setLstUsuariosInactivos(ArrayList<Usuario> lstUsuariosInactivos) {
        this.lstUsuariosInactivos = lstUsuariosInactivos;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioSel
     */
    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    /**
     * @param usuarioSel the usuarioSel to set
     */
    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    /**
     * @return the rolUsuario
     */
    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    /**
     * @return the idRol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * @return the lstRoles
     */
    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
    }

    /**
     * @param lstRoles the lstRoles to set
     */
    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    /**
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
    }

    /**
     * @return the objUsuario
     */
    public Usuario getObjUsuario() {
        return objUsuario;
    }

    /**
     * @param objUsuario the objUsuario to set
     */
    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    /**
     * @return the lstTiposPersonas
     */
    public List<TipoPersona> getLstTiposPersonas() {
        return lstTiposPersonas;
    }

    /**
     * @param lstTiposPersonas the lstTiposPersonas to set
     */
    public void setLstTiposPersonas(List<TipoPersona> lstTiposPersonas) {
        this.lstTiposPersonas = lstTiposPersonas;
    }

    /**
     * @return the idTipoPersona
     */
    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    /**
     * @param idTipoPersona the idTipoPersona to set
     */
    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the parametro
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
