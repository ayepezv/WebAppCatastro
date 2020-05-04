package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Menu;
import master.logica.entidades.Modulo;
import master.logica.entidades.ModuloGrupoMenu;
import master.logica.entidades.NodoMenu;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosMenu;
import master.logica.servicios.ServiciosModulo;
import master.logica.servicios.ServiciosModuloGrupoMenu;
import master.logica.servicios.ServiciosNodoMenu;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class MenuCtrl implements Serializable {

    private ArrayList<Menu> lstMenusModulos;
    private ArrayList<Menu> lstSubmenus;
    private ArrayList<Menu> lstItems;
    private ArrayList<ModuloGrupoMenu> lstModulosGruposMenus;
    private ArrayList<Modulo> lstModulos;
    private Menu menu;
    private Menu menuSel;
    private Modulo modulo;

    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    private int idModuloSel;
    private int idMenuPadre;
    private int idSubmenu;

    private ModuloGrupoMenu moduloCeroSel;

    public MenuCtrl() {
        moduloCeroSel = new ModuloGrupoMenu();
        menu = new Menu();
        menuSel = new Menu();
        modulo = new Modulo();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerMenusModulos();
        obtenerModulos();

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

    public void obtenerMenusModulos() {
        try {
            lstModulosGruposMenus = ServiciosModuloGrupoMenu.obtenerModulosMenusDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerMenusModulos() dice: " + e.getMessage());
        }
    }

    public void obtenerModulos() {
        try {
            lstModulos = ServiciosModulo.obtenerModulosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerModulos() dice: " + e.getMessage());
        }
    }

    public void obtenerSubmenusDadoPadre() {
        try {
            lstSubmenus = ServiciosMenu.obtenerMenusDadoPadreEstado(idMenuPadre, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSubmenusDadoPadre() dice: " + e.getMessage());
        }
    }

    public void obtenerItemsDadoSubmenu() {
        try {
            lstItems = ServiciosMenu.obtenerMenusDadoPadreEstado(idSubmenu, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerItemsDadoSubmenu() dice: " + e.getMessage());
        }
    }

    public void insertarMenuCeroDadoModulo() {
        try {
            modulo.setIdModulo(idModuloSel);
            String msg = ServiciosMenu.registrarMenuCeroDadoModulo(menu, modulo, sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerMenusModulos();
            resetearFitrosTabla("frmMenuNivelCero:tblMenus");
            menu = new Menu();
            modulo = new Modulo();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarMenuCeroDadoModulo() dice: " + e.getMessage());
        }
    }

    public void insertarMenuDadoPadre() {
        try {
            menu.setCodigoPadre(idMenuPadre);
            String msg = ServiciosMenu.registrarMenuDadoPadre(menu, sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerSubmenusDadoPadre();
            resetearFitrosTabla("frmSubmenus:tblMenus");
            menu = new Menu();

            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarMenuDadoPadre() dice: " + e.getMessage());
        }
    }

    public void actualizarSubmenu() {
        try {
            menuSel.setSessionUsuario(sessionUsuario);
            String msg = ServiciosMenu.actualizarMenuGenerico(menuSel);
            Util.addSuccessMessage(msg);
            obtenerSubmenusDadoPadre();
            resetearFitrosTabla("frmSubmenus:tblMenus");
            menuSel = new Menu();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            System.out.println("public void actualizarSubmenu() dice: " + e.getMessage());
            Util.addErrorMessage("public void actualizarSubmenu() dice: " + e.getMessage());
        }
    }

    public void eliminarSubmenu() {
        try {
            menuSel.setSessionUsuario(sessionUsuario);
            String msg = ServiciosMenu.eliminarMenuGenerico(menuSel);
            Util.addSuccessMessage(msg);
            obtenerSubmenusDadoPadre();
            resetearFitrosTabla("frmSubmenus:tblMenus");
            menuSel = new Menu();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarSubmenu() dice: " + e.getMessage());
            Util.addErrorMessage("public void eliminarSubmenu() dice: " + e.getMessage());
        }
    }

    public void eliminarItem() {
        try {
            menuSel.setSessionUsuario(sessionUsuario);
            String msg = ServiciosMenu.eliminarMenuGenerico(menuSel);
            Util.addSuccessMessage(msg);
            obtenerItemsDadoSubmenu();
            resetearFitrosTabla("frmItems:tblMenus");
            menuSel = new Menu();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarSubmenu() dice: " + e.getMessage());
            Util.addErrorMessage("public void eliminarSubmenu() dice: " + e.getMessage());
        }
    }

    public void insertarItemsDadoSubmenu() {
        try {
            menu.setCodigoPadre(idSubmenu);
            String msg = ServiciosMenu.registrarMenuDadoPadre(menu, sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerItemsDadoSubmenu();
            resetearFitrosTabla("frmItems:tblMenus");
            menu = new Menu();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarItemsDadoSubmenu() dice: " + e.getMessage());
        }
    }

    public void actualizarItem() {
        try {

            String msg = ServiciosMenu.actualizarMenu(menuSel, sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerItemsDadoSubmenu();
            resetearFitrosTabla("frmItems:tblMenus");
            menu = new Menu();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarItem() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">   
    public ArrayList<Menu> getLstMenusModulos() {
        return lstMenusModulos;
    }

    public int getIdSubmenu() {
        return idSubmenu;
    }

    public void setIdSubmenu(int idSubmenu) {
        this.idSubmenu = idSubmenu;
    }

    public int getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(int idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public int getIdModuloSel() {
        return idModuloSel;
    }

    public void setIdModuloSel(int idModuloSel) {
        this.idModuloSel = idModuloSel;
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

    public ArrayList<ModuloGrupoMenu> getLstModulosGruposMenus() {
        return lstModulosGruposMenus;
    }

    public void setLstModulosGruposMenus(ArrayList<ModuloGrupoMenu> lstModulosGruposMenus) {
        this.lstModulosGruposMenus = lstModulosGruposMenus;
    }

    public ArrayList<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(ArrayList<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenuSel() {
        return menuSel;
    }

    public void setMenuSel(Menu menuSel) {
        this.menuSel = menuSel;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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

    public ModuloGrupoMenu getModuloCeroSel() {
        return moduloCeroSel;
    }

    public void setModuloCeroSel(ModuloGrupoMenu moduloCeroSel) {
        this.moduloCeroSel = moduloCeroSel;
    }

//</editor-fold>
}
