package master.presentacion.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Funcion;
import master.logica.entidades.Menu;
import master.logica.entidades.NodoMenu;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosFuncion;
import master.logica.servicios.ServiciosMenu;
import master.logica.servicios.ServiciosNodoMenu;
import recursos.Util;

@ManagedBean
@SessionScoped
public class MasterCtrl implements Serializable {

    private RolUsuario rolUsuario;
    private ArrayList<Menu> submenus;
    private ArrayList<NodoMenu> lstMenus;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Menu itemSel;
    private String paginaActual;
    private int idMenuSel;

    public MasterCtrl() {
        itemSel = new Menu();
        rolUsuario = new RolUsuario();
        submenus = new ArrayList<>();
        lstMenus = new ArrayList<>();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {

    }

    public void cargarMenus() throws IOException {
        try {
            System.out.println("Id rol: " + rolUsuario.getIdRol().getIdRol());
            // submenus = ServiciosMenu.obtenerSubmenusDadoRol(rolUsuario.getIdRol().getIdRol());
            lstMenus = ServiciosNodoMenu.generarMenu(rolUsuario.getIdRol().getIdRol());
            paginaActual = "home.jsf";
            faceContext.getExternalContext().redirect("index.jsf");
            System.out.println("total de submenus: " + submenus.size());
            Util.addSuccessMessage("total de submenus: " + submenus.size());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerUrlDadoItem() {
        try {
            System.out.println("Menu seleccionado: " + idMenuSel);
            Funcion funcion = ServiciosFuncion.obtenerFuncionDadoIdMenu(idMenuSel);
            System.out.println("Página seleccionada: " + funcion.getCodigoAccion().getUrl());
            paginaActual = funcion.getCodigoAccion().getUrl();
            System.out.println("Hola:");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUrlDadoItem() dice: " + e.getMessage());
            System.out.println("public void obtenerUrlDadoItem() dice: " + e.getMessage());
        }
    }

    public String getFechaActual() {
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");
        Calendar cal = Calendar.getInstance();
        return formato.format(cal.getTime());
    }

    public String getIpAddres() {
        String thisIp = null;
        try {
            thisIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("IP:" + thisIp);
        } catch (Exception e) {
            Util.addErrorMessage("Obtener ip dice: " + e.getMessage());
        }
        return thisIp;
    }

    public void verificarSesion() {
        try {
            Usuario us = new Usuario();
            us = (Usuario) httpServletRequest.getSession().getAttribute("usLogueado");
            if (us == null) {
                faceContext.getExternalContext().redirect("./../acces.jsf");
            }
        } catch (Exception e) {
            Util.addErrorMessage("verificar session dice: " + e.getMessage());
        }
    }

    public String getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Menu getItemSel() {
        return itemSel;
    }

    public void setItemSel(Menu itemSel) {
        this.itemSel = itemSel;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public ArrayList<Menu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<Menu> submenus) {
        this.submenus = submenus;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public ArrayList<NodoMenu> getLstMenus() {
        return lstMenus;
    }

    public void setLstMenus(ArrayList<NodoMenu> lstMenus) {
        this.lstMenus = lstMenus;
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

    public int getIdMenuSel() {
        return idMenuSel;
    }

    public void setIdMenuSel(int idMenuSel) {
        this.idMenuSel = idMenuSel;
    }

}
