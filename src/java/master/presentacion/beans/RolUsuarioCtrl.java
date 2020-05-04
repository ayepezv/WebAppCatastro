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
import recursos.Util;

@ManagedBean
@ViewScoped
public class RolUsuarioCtrl implements Serializable {

    private ArrayList<RolUsuario> lstRolesUsuarios;
    private RolUsuario rolUsuario;
    private RolUsuario rolUsuarioSel;
    private ArrayList<Rol> lstRoles;
    private ArrayList<Usuario> lstUsuarios;
    private int idRol;
    private int idUsuario;
    private int idUsuarioSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private String parametro;
    private ArrayList<Usuario> lstPersonas;
    private int idTipoPersona;
    private List<TipoPersona> lstTiposPersonas;
    private Usuario usuarioSel;
    private int band;

    public RolUsuarioCtrl() {
        rolUsuario = new RolUsuario();
        rolUsuarioSel = new RolUsuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerRoles();
        obtenerTiposPersonas();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
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

    public void obtenerRolesUs() {
        try {
            setLstRolesUsuarios(ServiciosRolUsuario.listarRolesDadoUsuario(getIdUsuarioSel()));
        } catch (Exception e) {
            System.out.println("public void obtenerRolesUs() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerRolesUs() dice: " + e.getMessage());
        }
    }

    public void encontrarRolesUs() {
        try {
            setLstRolesUsuarios(ServiciosRolUsuario.encontrarRolesDadoUsuario(getUsuarioSel().getCedula()));
            setBand(1);
        } catch (Exception e) {
            System.out.println("public void encontrarRolesUs() dice: " + e.getMessage());
            Util.addErrorMessage("public void encontrarRolesUs() dice: " + e.getMessage());
        }
    }

    public void limpirar() {
        try {
            setLstRolesUsuarios(new ArrayList<>());
            setParametro(null);
            setLstPersonas(new ArrayList<>());
            setBand(0);
            setIdTipoPersona(0);
            setUsuarioSel(new Usuario());
        } catch (Exception e) {
            System.out.println("public void limpirar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpirar() dice: " + e.getMessage());
        }
    }

    public void obtenerRoles() {
        try {
            setLstRoles(ServiciosRoles.obtenerRolesDadoEstado("A"));
        } catch (Exception e) {
            System.out.println("public void obtenerRoles() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerRoles() dice: " + e.getMessage());
        }
    }

    public void obtenerUsuarios() {
        try {
            setLstUsuarios(ServiciosUsuario.obtenerUsuariosDelSistema());
        } catch (Exception e) {
            System.out.println("public void obtenerUsuarios() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerRolesUs() dice: " + e.getMessage());
        }
    }

    public void obtenerPersonas() {
        try {
            setLstPersonas(ServiciosUsuario.encontrarPersonasDadoCriterio(getParametro()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstPersonas().size()), " coincidencias."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e.getMessage()));
            System.out.println("public void obtenerPersonas() dice: " + e.getMessage());
        }
    }

    public void encontrarPersonas() {
        try {
            setLstPersonas(ServiciosUsuario.encontrarPersonasDadoCriterioTipo(getParametro(), getIdTipoPersona()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstPersonas().size()), " coincidencias."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e.getMessage()));
            System.out.println("public void obtenerPersonas() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            getRolUsuario().setSessionUsuario(getSessionUsuario());
            getRolUsuario().getIdRol().setIdRol(getIdRol());
            getRolUsuario().getIdUsuario().setIdPersona(getUsuarioSel().getIdPersona());
            String respuesta = ServiciosRolUsuario.registrarRolUsuario(getRolUsuario());
            Util.addSuccessMessage(respuesta);
            encontrarRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuario(new RolUsuario());
            setIdRol(0);
            setIdUsuario(0);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            getRolUsuarioSel().setSessionUsuario(getSessionUsuario());
            String respuesta = ServiciosRolUsuario.eliminarRolUsuario(getRolUsuarioSel());
            Util.addSuccessMessage(respuesta);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuarioSel(new RolUsuario());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void cambiarPrivSeleccionar() {
        try {
            System.out.println("Privilegios: " + getRolUsuarioSel().getSeleccionar());

            getRolUsuarioSel().setSessionUsuario(getSessionUsuario());
            String msg = ServiciosRolUsuario.cambiarPrivSelect(getRolUsuarioSel());
            Util.addSuccessMessage(msg);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuarioSel(new RolUsuario());
        } catch (Exception e) {
            System.out.println("public void cambiarPrivSeleccionar() dice: " + e.getMessage());
            Util.addErrorMessage("public void cambiarPrivSeleccionar() dice: " + e.getMessage());
        }
    }

    public void cambiarPrivInsert() {
        try {
            System.out.println("Privilegios: " + getRolUsuarioSel().getInsertar());
            getRolUsuarioSel().setSessionUsuario(getSessionUsuario());
            String msg = ServiciosRolUsuario.cambiarPrivInsert(getRolUsuarioSel());
            Util.addSuccessMessage(msg);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuarioSel(new RolUsuario());
        } catch (Exception e) {
            System.out.println("public void cambiarPrivInsert() dice: " + e.getMessage());
            Util.addErrorMessage("public void cambiarPrivInsert() dice: " + e.getMessage());
        }
    }

    public void cambiarPrivUpdate() {
        try {
            System.out.println("Privilegios antes: " + getRolUsuarioSel().getActualizar());
            getRolUsuarioSel().setSessionUsuario(getSessionUsuario());
            String msg = ServiciosRolUsuario.cambiarPrivUpdate(getRolUsuarioSel());
            Util.addSuccessMessage(msg);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuarioSel(new RolUsuario());
        } catch (Exception e) {
            System.out.println("public void cambiarPrivUpdate() dice: " + e.getMessage());
            Util.addErrorMessage("public void cambiarPrivUpdate() dice: " + e.getMessage());
        }
    }

    public void cambiarPrivDelete() {
        try {
            System.out.println("Privilegios: " + getRolUsuarioSel().getEliminar());

            getRolUsuarioSel().setSessionUsuario(getSessionUsuario());
            String msg = ServiciosRolUsuario.cambiarPrivDelete(getRolUsuarioSel());
            Util.addSuccessMessage(msg);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            setRolUsuarioSel(new RolUsuario());
        } catch (Exception e) {
            System.out.println("public void cambiarPrivDelete() dice: " + e.getMessage());
            Util.addErrorMessage("public void cambiarPrivDelete() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">
    public void setRolUsuarioSel(RolUsuario rolUsuarioSel) {
        this.rolUsuarioSel = rolUsuarioSel;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<RolUsuario> getLstRolesUsuarios() {
        return lstRolesUsuarios;
    }

    public void setLstRolesUsuarios(ArrayList<RolUsuario> lstRolesUsuarios) {
        this.lstRolesUsuarios = lstRolesUsuarios;
    }

    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public RolUsuario getRolUsuarioSel() {
        return rolUsuarioSel;
    }
    //</editor-fold>

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
     * @return the idUsuarioSel
     */
    public int getIdUsuarioSel() {
        return idUsuarioSel;
    }

    /**
     * @param idUsuarioSel the idUsuarioSel to set
     */
    public void setIdUsuarioSel(int idUsuarioSel) {
        this.idUsuarioSel = idUsuarioSel;
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

    /**
     * @return the lstPersonas
     */
    public ArrayList<Usuario> getLstPersonas() {
        return lstPersonas;
    }

    /**
     * @param lstPersonas the lstPersonas to set
     */
    public void setLstPersonas(ArrayList<Usuario> lstPersonas) {
        this.lstPersonas = lstPersonas;
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

}
