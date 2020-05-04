package catastro.presentacion.beans;

import catastro.logica.servicios.ServiciosPropietario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
public class CtContribuyente implements Serializable {

    private Usuario usuario;
    private Usuario objUsuario;
    private Usuario usuarioSel;
    private RolUsuario rolUsuario;
    private ArrayList<Usuario> lstUsuarios;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String strCriterio;
    private List<TipoPersona> lstTiposPersonas;
    private int idTipoPersona;
    private int band;
    private String msg;
    private boolean habilitado;

    public CtContribuyente() {
        habilitado = true;
        objUsuario = new Usuario();
        usuario = new Usuario();
        usuarioSel = new Usuario();
        rolUsuario = new RolUsuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerTiposPersonas();
    }

    public void obtenerContribuyentes() {
        try {
            System.out.println("parametro: " + getStrCriterio());
            setLstUsuarios(ServiciosPropietario.encontrarPropietarios("A", getStrCriterio()));
        } catch (Exception e) {
            System.out.println("public void obtenerUsuarios()  dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void obtenerContribuyentesDadoTipo() {
        try {
            setLstUsuarios(ServiciosPropietario.encontrarPropietariosDadoTipo("A", getIdTipoPersona()));
        } catch (Exception e) {
            System.out.println("public void obtenerContribuyentesDadoTipo()  dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerContribuyentesDadoTipo() dice: " + e.getMessage());
        }
    }

    public void obtenerContribuyentesDadoTipoParametro() {
        try {
            setLstUsuarios(ServiciosPropietario.encontrarPropietariosDadoTipoParametro("A", getStrCriterio(), getIdTipoPersona()));
        } catch (Exception e) {
            System.out.println("public void obtenerContribuyentesDadoTipo()  dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerContribuyentesDadoTipo() dice: " + e.getMessage());
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

    public void registrarPersonaNatural() {
        try {
            setMsg(ServiciosUsuario.registrarPersonaNaturalRol(getObjUsuario(), 5, getSessionUsuario().getIdPersona()));
            System.out.println("public void registrarPersonaNatural()  dice: " + msg);
            Util.addSuccessMessage(getMsg());
//            obtenerContribuyentes();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuario(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoUsuario').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrarPersonaNatural() dice: " + e.getMessage());
            System.out.println("public void registrarPersonaNatural() dice: " + e.getMessage());
        }
    }

    public void registrarPersonaJuridica() {
        try {
            //String msg = ServiciosUsuario.registrarPersonaJuridicaRol(getObjUsuario(), 5, getSessionUsuario().getIdPersona());
            setMsg(ServiciosUsuario.registrarContribJuridico(getObjUsuario(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsg());
            obtenerContribuyentes();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuario(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevoUsuario').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());

        }
    }

    public void insertarUsuarioDadoRol() {
        try {
            String p = getRolUsuario().getIdUsuario().getCedula();
            getRolUsuario().setIdRol(ServiciosRoles.obtenerRolDadoCodigo(5));
            setMsg(ServiciosRolUsuario.registrarUsuarioDadoRol(getRolUsuario(), getSessionUsuario().getIdPersona()));
            setRolUsuario(new RolUsuario());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Exitosa", getMsg());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarUsuario').hide()");

            setLstUsuarios(ServiciosPropietario.encontrarPropietarios("A", p));

            resetearFitrosTabla("frmUsuarios:tblUsuarios");
        } catch (Exception e) {
            System.out.println("public void insertarUsuarioDadoRol()  dice: " + e.getMessage());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    public void editarUsuario() {
        try {
            String p = getUsuarioSel().getCedula();
            setMsg(ServiciosUsuario.actualizarUsuario(getUsuarioSel()));
            setUsuarioSel(new Usuario());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Exitosa", getMsg());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarUsuario').hide()");
            setLstUsuarios(ServiciosPropietario.encontrarPropietarios("A", p));
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
        } catch (Exception e) {
            System.out.println("public void editarUsuario()  dice: " + e.getMessage());
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    public void actualizarUsuario() {
        try {
            setMsg(ServiciosUsuario.actualizarPersonas(getUsuarioSel(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsg());
            System.out.println("public void actualizarUsuario() dice: " + msg);
            limpiar();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setUsuarioSel(new Usuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActPerNat').hide()");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActPerJur').hide()");
        } catch (Exception e) {
            System.out.println("public void actualizarUsuario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void limpiar() {
        try {
            setLstUsuarios(new ArrayList<>());
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            setStrCriterio("");
            setIdTipoPersona(0);
        } catch (Exception e) {
            System.out.println("public void limpiar()  dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
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
     * @return the strCriterio
     */
    public String getStrCriterio() {
        return strCriterio;
    }

    /**
     * @param strCriterio the strCriterio to set
     */
    public void setStrCriterio(String strCriterio) {
        this.strCriterio = strCriterio;
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
     * @return the habilitado
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
