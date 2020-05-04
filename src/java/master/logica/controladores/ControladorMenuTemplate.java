package master.logica.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Funcion;
import master.logica.entidades.Menu;
import master.logica.entidades.NodoMenu;
import master.logica.entidades.RolUsuario;
import master.logica.servicios.ServiciosFuncion;
import master.logica.servicios.ServiciosNodoMenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorMenuTemplate implements Serializable {

    private RolUsuario rolUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private MenuModel menu;
    private DefaultMenuModel menuModel;
    private ArrayList<Menu> submenus;
    private ArrayList<NodoMenu> lstMenus;
    private String paginaActual;

    public ControladorMenuTemplate() {
        menuModel = new DefaultMenuModel();
        menu = new DefaultMenuModel();
        rolUsuario = new RolUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        crearMenu();
    }

    public void crearMenu() {
        try {

            //obtengo los menus correspondientes al rol
            int idRol = (int) httpServletRequest.getSession().getAttribute("idRol");
            System.out.println("idRol: " + idRol);

            //// página de inicio
            DefaultMenuItem home = new DefaultMenuItem("Inicio");
            home.setIcon("home");
            String pag = "";

            switch (idRol) {
                case 1:
                    System.out.println("Módulo Super Administrador");
                    pag = "/faces/seguridad/master/home.xhtml";
                    break;
                case 2:
                    System.out.println("Módulo Auditoria");
                    pag = "/faces/seguridad/auditoria/home.xhtml";
                    break;
                case 4:
                    System.out.println("Módulo Catastro");
                    pag = "/faces/seguridad/catastro/home.xhtml";
                    break;
                case 5:
                    System.out.println("Módulo Contribuyentes");
                    pag = "/faces/seguridad/propietarios/home.xhtml";
                    break;

                case 6:
                    System.out.println("Módulo Agua Potable");
                    pag = "/faces/seguridad/agua_potable/home.xhtml";
                    break;

                case 7:
                    System.out.println("Módulo Recaudacion");
                    pag = "/faces/seguridad/recaudacion/home.xhtml";
                    break;

                case 8:
                    System.out.println("Módulo Municipio");
                    pag = "/faces/seguridad/municipio/home.xhtml";
                    break;

                case 13:
                    System.out.println("Módulo CEM");
                    pag = "/faces/seguridad/cem/home.xhtml";
                    break;

                case 14:
                    System.out.println("Módulo Registro de la Propiedad");
                    pag = "/faces/seguridad/registro_propiedad/home.xhtml";
                    break;

                case 15:
                    System.out.println("Módulo de Tesorería");
                    pag = "/faces/seguridad/tesorero/home.xhtml";
                    break;

                case 19:
                    System.out.println("Módulo Regulación Urbana");
                    pag = "/faces/seguridad/regulacion_urbana/home.xhtml";
                    break;

                case 21:
                    System.out.println("Módulo Rentas");
                    pag = "/faces/seguridad/rentas/home.xhtml";
                    break;

                default:
                    System.out.println("Rol no existente");
                    Util.addErrorMessage("Rol inexistente");
                    pag = "permisos.jsf";
                    break;
            }

            home.setUrl(pag);
            menu.addElement(home);
            //// fin pagina de inicio

            lstMenus = ServiciosNodoMenu.generarMenu(idRol);
            System.out.println("Total de submenus: " + lstMenus.size());
            //recorro la lista de menús
            ///-- empiezo a recorrer submenus   
            DefaultSubMenu subMenu;
            DefaultMenuItem item;
            for (int s = 0; s < lstMenus.size(); s++) {

                //borrar si da errores
                if ((lstMenus.get(s).getItems().isEmpty()) || ("Y".equals(lstMenus.get(s).getSubmenu().getIndex()))) {
                    item = new DefaultMenuItem(lstMenus.get(s).getSubmenu().getNombre());
                    item.setIcon(lstMenus.get(s).getSubmenu().getIcono());
                    item.setStyleClass("jsId" + lstMenus.get(s).getSubmenu().getIdMenu());
                    item.setCommand("#{controladorMenuTemplate.obtenerUrlDadoItem("
                            + lstMenus.get(s).getSubmenu().getIdMenu()
                            + ")}");
                    //subMenu.addElement(item);
                    menu.addElement(item);
                } else {
                    //agrego el submenu al modelo              
                    subMenu = new DefaultSubMenu(lstMenus.get(s).getSubmenu().getNombre());
                    subMenu.setStyleClass("jsId" + lstMenus.get(s).getSubmenu().getIdMenu());
                    subMenu.setId(String.valueOf(lstMenus.get(s).getSubmenu().getIdMenu()));
                    subMenu.setIcon(lstMenus.get(s).getSubmenu().getIcono());

                    // recorro los items                                   
                    for (int i = 0; i < lstMenus.get(s).getItems().size(); i++) {
                        item = new DefaultMenuItem(lstMenus.get(s).getItems().get(i).getNombre());
                        item.setIcon(lstMenus.get(s).getItems().get(i).getIcono());
                        item.setStyleClass("jsId" + lstMenus.get(s).getItems().get(i).getIdMenu());
                        item.setCommand("#{controladorMenuTemplate.obtenerUrlDadoItem(" + lstMenus.get(s).getItems().get(i).getIdMenu() + ")}");
                        //item.setUrl(paginaActual);                    
                        subMenu.addElement(item);
                    }
                    menu.addElement(subMenu);
                }

            }
            //paginaActual = "home.xhtml";
            //redirecciono a la parte interna segura del sistema
            //faceContext.getExternalContext().redirect("index.xhtml");
        } catch (Exception e) {
            Util.addErrorMessage("public void crearMenu() dice: " + e.getMessage());
        }
    }

    public void obtenerUrlDadoItem(int idMenuSel) {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Funcion funcion = ServiciosFuncion.obtenerFuncionDadoIdMenu(idMenuSel);
            //System.out.println("Página seleccionada: " + funcion.getCodigoAccion().getUrl());
            paginaActual = funcion.getCodigoAccion().getUrl();
            fc.getExternalContext().redirect(paginaActual);
//            context.update("paginas");
        } catch (Exception e) {
            //Util.addErrorMessage("public void obtenerUrlDadoItem() dice: " + e.getMessage());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Este item no tiene URL asociada", "Consulte con el administrador");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
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

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public DefaultMenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public ArrayList<Menu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<Menu> submenus) {
        this.submenus = submenus;
    }

    public ArrayList<NodoMenu> getLstMenus() {
        return lstMenus;
    }

    public String getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }

    public void setLstMenus(ArrayList<NodoMenu> lstMenus) {
        this.lstMenus = lstMenus;
    }

}
