/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioExcencion;
import catastro.logica.servicios.FPredioExcencion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtEstadisticasExcenciones implements Serializable {

    private Date fechaInicio;
    private Date fechaFin;
    private String json;
    private String json2;

    public void graficar() {
        try {

            List<PredioExcencion> lst = FPredioExcencion.reporteTotalDescuentosExcenciones(Util.sqlDate(getFechaInicio()), Util.sqlDate(getFechaFin()));
            setJson("[");

            for (int i = 0; i < lst.size(); i++) {
                setJson(getJson() + "{'Excencion':'" + lst.get(i).getExcencion().getExcencion() + "',");
                setJson(getJson() + "'Total':'" + Util.redondearDecimales(lst.get(i).getDsctoExcencion(), 2) + "'");
                if (i != lst.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json Data: " + getJson());

            setJson2("[");

            for (int i = 0; i < lst.size(); i++) {
                setJson2(getJson2() + "{'Excencion':'" + lst.get(i).getExcencion().getExcencion() + "',");
                setJson2(getJson2() + "'Total':'" + (int) lst.get(i).getPorcentaje() + "'");
                if (i != lst.size() - 1) {
                    setJson2(getJson2() + "},");
                } else {
                    setJson2(getJson2() + "}");
                }
            }
            setJson2(getJson2() + "]");
            System.out.println("Json Data: " + getJson2());

        } catch (Exception e) {
            System.out.println("public void graficar() dice: " + e.getMessage());
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    /**
     * @return the json2
     */
    public String getJson2() {
        return json2;
    }

    /**
     * @param json2 the json2 to set
     */
    public void setJson2(String json2) {
        this.json2 = json2;
    }

}
