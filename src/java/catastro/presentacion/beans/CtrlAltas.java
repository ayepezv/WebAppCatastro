package catastro.presentacion.beans;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlAltas implements Serializable {

    private Date fechaInicio;
    private Date fechaFin;
    private List<Titulo> lstTitulos;

    public void limpiar() {
        try {
            lstTitulos = new ArrayList<>();
            fechaFin = null;
            fechaInicio = null;
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void obtenerAltas() {
        try {
            java.sql.Date sqlInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date sqlFin = new java.sql.Date(fechaFin.getTime());
            lstTitulos = FTitulos.consultarTitulosIndividualesEntreFechas(sqlInicio, sqlFin);
            System.out.println("Fecha inicio " + sqlInicio + " fecha fin " + sqlFin);
            Util.addSuccessMessage(getLstTitulos().size() + " Título(s) emitido(s)");
        } catch (Exception e) {
            System.out.println("public void obtenerAltas() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerAltas() dice: " + e.getMessage());
        }
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the lstTitulos
     */
    public List<Titulo> getLstTitulos() {
        return lstTitulos;
    }

    /**
     * @param lstTitulos the lstTitulos to set
     */
    public void setLstTitulos(List<Titulo> lstTitulos) {
        this.lstTitulos = lstTitulos;
    }

}
