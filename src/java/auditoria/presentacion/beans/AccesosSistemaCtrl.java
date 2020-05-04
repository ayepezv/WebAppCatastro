package auditoria.presentacion.beans;

import auditoria.logica.entidades.AccesoSistema;
import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class AccesosSistemaCtrl implements Serializable {

    private ArrayList<AccesoSistema> lstAccesos;
    private ArrayList<Usuario> lstUsuarios;

    @PostConstruct
    public void init() {
        obtenerAccesos();
        obtenerUsuarios();
    }

    public void obtenerUsuarios() {
        try {
            setLstUsuarios(ServiciosUsuario.obtenerUsuariosDadoEstado("A"));
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void obtenerAccesos() {
        try {
            setLstAccesos(ServiciosAccesosUsuario.obtenerAccesosSistema());
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAccesos() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstAccesos
     */
    public ArrayList<AccesoSistema> getLstAccesos() {
        return lstAccesos;
    }

    /**
     * @param lstAccesos the lstAccesos to set
     */
    public void setLstAccesos(ArrayList<AccesoSistema> lstAccesos) {
        this.lstAccesos = lstAccesos;
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

}
