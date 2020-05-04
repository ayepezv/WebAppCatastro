package master.logica.servicios;

import java.util.ArrayList;
import master.logica.entidades.Menu;
import master.logica.entidades.NodoMenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

public class ServiciosNodoMenu {

    public static ArrayList<NodoMenu> generarMenu(int idRol) throws Exception {
        ArrayList<NodoMenu> menu;
        try {
            menu = new ArrayList<>();
            ArrayList<Menu> submenu = ServiciosMenu.obtenerSubmenusDadoRol(idRol);
            for (int i = 0; i < submenu.size(); i++) {
                NodoMenu nodo = new NodoMenu();
                nodo.setSubmenu(submenu.get(i));
                nodo.setItems(ServiciosMenu.obtenerItemsDadoSubmenu(submenu.get(i).getIdMenu()));
                menu.add(nodo);
            }
        } catch (Exception e) {
            throw e;
        }
        return menu;
    }

    public static ArrayList<NodoMenu> generarNodoSubmenu() throws Exception {
        ArrayList<NodoMenu> menu;
        try {
            menu = new ArrayList<>();
            ArrayList<Menu> submenu = ServiciosMenu.obtenerNodoSubmenus();
            for (int i = 0; i < submenu.size(); i++) {
                NodoMenu nodo = new NodoMenu();
                nodo.setSubmenu(submenu.get(i));
                nodo.setItems(ServiciosMenu.obtenerItemsDadoSubmenu(submenu.get(i).getIdMenu()));
                menu.add(nodo);
            }
        } catch (Exception e) {
            throw e;
        }
        return menu;
    }

    public static MenuModel cargarMenuBar(int idRol) throws Exception {
        DefaultMenuModel menuModel = new DefaultMenuModel();
        DefaultSubMenu subMenu = null;
        DefaultMenuItem item;
        DefaultSeparator separtor;
        try {
            ArrayList<NodoMenu> lstMenus = ServiciosNodoMenu.generarMenu(idRol);

            for (int s = 0; s < lstMenus.size(); s++) {
                subMenu = new DefaultSubMenu(lstMenus.get(s).getSubmenu().getNombre());
                subMenu.setStyleClass("jsId" + lstMenus.get(s).getSubmenu().getIdMenu());
                subMenu.setId(String.valueOf(lstMenus.get(s).getSubmenu().getIdMenu()));
                subMenu.setIcon("ui-icon-home");
                ArrayList<Menu> items = lstMenus.get(s).getItems();
                for (int i = 0; i < items.size(); i++) {
                    item = new DefaultMenuItem(items.get(i).getNombre());
                    item.setCommand("#{indexSeguridad.obtenerUrlDadoItem(" + items.get(i).getIdMenu() + ")}");
                    subMenu.addElement(item);
                }

                menuModel.addElement(subMenu);
            }
        } catch (Exception e) {
            throw e;
        }
        return menuModel;
    }
}
