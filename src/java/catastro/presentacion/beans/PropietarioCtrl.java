/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.servicios.ServiciosPropietario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Usuario;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

/**
 *
 * @author ayepe
 */
@ManagedBean
@ViewScoped
public class PropietarioCtrl implements Serializable {

    private ArrayList<Usuario> lstUsuarios;
    private Usuario usuario;
    private Usuario usuarioSel;
    private Date fechaNacimiento;
    private String parametro;

    public PropietarioCtrl() {
        usuario = new Usuario();
        usuarioSel = new Usuario();
        //lstUsuarios = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        obtenerUsuarios();
    }

    public void obtenerUsuarios() {
        try {
            lstUsuarios = ServiciosPropietario.obtenerPropietariosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void insertarUsuario() {
        try {
            usuario.setFechaNacimiento(recursos.ConvertirFechas.devolverFecha(fechaNacimiento));
            String msg = ServiciosPropietario.registrarPropietario(usuario,1);
            Util.addSuccessMessage(msg);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());
        }
    }

    public void actualizarUsuario() {
        try {
            usuarioSel.setFechaNacimiento(recursos.ConvertirFechas.devolverFecha(fechaNacimiento));
            String msgBd = ServiciosPropietario.actualizarPropietario(usuarioSel);
            Util.addSuccessMessage(msgBd);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuarioSel = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarUsuario() dice: " + e.getMessage());
        }
    }
    
     public void eliminarUsuario() {
        try {
            
            String msgBd = ServiciosPropietario.eliminarPropietario(usuarioSel);
            Util.addSuccessMessage(msgBd);
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuarioSel = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarUsuario() dice: " + e.getMessage());
        }
    }
     
     public void encontrarPropietario() {
        try {
            lstUsuarios = ServiciosPropietario.encontrarPropietarios("A",parametro);
            System.out.println("parámetro de busquedas: " + parametro);
            System.out.println("total de propietarios: " + lstUsuarios.size());
        } catch (Exception e) {
            System.out.println(" public void encontrarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(" public void encontrarPropietario() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>
}
