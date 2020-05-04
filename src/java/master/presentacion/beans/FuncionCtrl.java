package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Accion;
import master.logica.entidades.Funcion;
import master.logica.entidades.Menu;
import master.logica.entidades.Rol;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosAccion;
import master.logica.servicios.ServiciosFuncion;
import master.logica.servicios.ServiciosMenu;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class FuncionCtrl implements Serializable {

    private ArrayList<Funcion> funciones;
    private Funcion funcion;
    private Funcion funcionSel;
    private ArrayList<Menu> lstMenusModulos;
    private ArrayList<Menu> lstSubmenus;
    private ArrayList<Menu> lstItems;
    private ArrayList<Accion> lstAcciones;
    private ArrayList<Rol> lstRoles;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private int menuModuloSel;
    private int submenuSel;
    private int itemSel;
    private int accionSel;
    private int rolSel;

    public FuncionCtrl() {
        funcion = new Funcion();
        funcionSel = new Funcion();
        funciones = new ArrayList<>();
        lstMenusModulos = new ArrayList<>();
        lstSubmenus = new ArrayList<>();
        lstItems = new ArrayList<>();
        lstAcciones = new ArrayList<>();
        lstRoles = new ArrayList<>();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerFuncionesActivas();
        obtenerMenusModulos();
        obtenerRoles();
        obtenerUrls();
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

    public void obtenerFuncionesActivas() {
        try {
            funciones = ServiciosFuncion.obtenerFuncionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerFuncionesActivas() dice: " + e.getMessage());
            System.out.println("public void obtenerFuncionesActivas() dice: " + e.getMessage());
        }
    }

    public void obtenerSubmenus() {
        try {
            System.out.println("idMenuModulo :" + menuModuloSel);
            lstSubmenus = ServiciosMenu.obtenerMenusDadoPadreEstado(menuModuloSel, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSubmenus() dice: " + e.getMessage());
            System.out.println("public void obtenerSubmenus() dice: " + e.getMessage());
        }
    }

    public void obtenerItems() {
        try {
            lstItems = ServiciosMenu.obtenerMenusDadoPadreEstado(submenuSel, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSubmenus() dice: " + e.getMessage());
            System.out.println("public void obtenerSubmenus() dice: " + e.getMessage());
        }
    }

    public void obtenerMenusModulos() {
        try {
            lstMenusModulos = ServiciosMenu.obtenerMenusModulos("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerMenusModulos() dice: " + e.getMessage());
        }
    }

    public void obtenerRoles() {
        try {
            lstRoles = ServiciosRoles.obtenerRolesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRoles() dice: " + e.getMessage());
            System.out.println("public void obtenerRoles() dice: " + e.getMessage());
        }
    }

    public void obtenerUrls() {
        try {
            lstAcciones = ServiciosAccion.obtenerAccionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRoles() dice: " + e.getMessage());
            System.out.println("public void obtenerRoles() dice: " + e.getMessage());
        }
    }

    public void registarFuncion() {
        try {
            rolSel = ServiciosRoles.obtenerRolDadoCodigoMenuCero(menuModuloSel).getIdRol();
            System.out.println("rol seL: " + rolSel);
            String msgBd = ServiciosFuncion.registrarFuncion(accionSel, rolSel, itemSel, sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msgBd);
            obtenerFuncionesActivas();
            accionSel = 0;
            rolSel = 0;
            itemSel = 0;
            menuModuloSel = 0;
            submenuSel = 0;
            resetearFitrosTabla("frmFunciones:tblFunciones");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registarFuncion() dice: " + e.getMessage());
            System.out.println("public void registarFuncion() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Funcion getFuncionSel() {
        return funcionSel;
    }

    public ArrayList<Menu> getLstMenusModulos() {
        return lstMenusModulos;
    }

    public void setLstMenusModulos(ArrayList<Menu> lstMenusModulos) {
        this.lstMenusModulos = lstMenusModulos;
    }

    public ArrayList<Menu> getLstSubmenus() {
        return lstSubmenus;
    }

    public void setLstSubmenus(ArrayList<Menu> lstSubmenus) {
        this.lstSubmenus = lstSubmenus;
    }

    public ArrayList<Menu> getLstItems() {
        return lstItems;
    }

    public void setLstItems(ArrayList<Menu> lstItems) {
        this.lstItems = lstItems;
    }

    public ArrayList<Accion> getLstAcciones() {
        return lstAcciones;
    }

    public void setLstAcciones(ArrayList<Accion> lstAcciones) {
        this.lstAcciones = lstAcciones;
    }

    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public void setFuncionSel(Funcion funcionSel) {
        this.funcionSel = funcionSel;
    }

    public int getMenuModuloSel() {
        return menuModuloSel;
    }

    public void setMenuModuloSel(int menuModuloSel) {
        this.menuModuloSel = menuModuloSel;
    }

    public int getSubmenuSel() {
        return submenuSel;
    }

    public void setSubmenuSel(int submenuSel) {
        this.submenuSel = submenuSel;
    }

    public int getItemSel() {
        return itemSel;
    }

    public void setItemSel(int itemSel) {
        this.itemSel = itemSel;
    }

    public int getAccionSel() {
        return accionSel;
    }

    public void setAccionSel(int accionSel) {
        this.accionSel = accionSel;
    }

    public int getRolSel() {
        return rolSel;
    }

    public void setRolSel(int rolSel) {
        this.rolSel = rolSel;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

}
