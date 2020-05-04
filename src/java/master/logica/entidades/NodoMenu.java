package master.logica.entidades;

import java.util.ArrayList;

public class NodoMenu {

    private Menu submenu;
    private ArrayList<Menu> items;

    public NodoMenu() {
        submenu = new Menu();
        items = new ArrayList<>();
    }

    public Menu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Menu submenu) {
        this.submenu = submenu;
    }

    public ArrayList<Menu> getItems() {
        return items;
    }

    public void setItems(ArrayList<Menu> items) {
        this.items = items;
    }

}
