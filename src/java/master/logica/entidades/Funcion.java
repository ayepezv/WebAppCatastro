package master.logica.entidades;

import java.util.Date;

public class Funcion {

    private Accion codigoAccion;
    private Rol codigoRol;
    private Menu codigoMenu;
    private int orden;
    private String estadoLogico;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaBaja;

    public Funcion() {
        codigoAccion = new Accion();
        codigoRol = new Rol();
        codigoMenu = new Menu();
    }

    public Accion getCodigoAccion() {
        return codigoAccion;
    }

    public void setCodigoAccion(Accion codigoAccion) {
        this.codigoAccion = codigoAccion;
    }

    public Rol getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Rol codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Menu getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(Menu codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
