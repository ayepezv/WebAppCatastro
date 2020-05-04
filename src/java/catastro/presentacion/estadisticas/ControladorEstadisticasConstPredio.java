/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.estadisticas;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorEstadisticasConstPredio implements Serializable {

    private String json;

    @PostConstruct
    public void init() {
        generarJson();
    }

    private void generarJson() {
        try {
            setJson("[");
            String oc;
            ArrayList<PredioV2> lstOcupaciones = FuncionesPredio.seleccionarOcupacionesPredios();
            for (int i = 0; i < lstOcupaciones.size(); i++) {
                oc = lstOcupaciones.get(i).getOcupacionPredio();
                setJson(getJson() + "{'Ocupación': ");
                setJson(getJson() + "'" + lstOcupaciones.get(i).getOcupacionPredio() + "'");
                setJson(getJson() + ",'Total':'" + FuncionesPredio.seleccionarPrediosDadoOcupacion(oc).size() + "'");
                if (i != lstOcupaciones.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json: " + getJson());
        } catch (Exception e) {
            Util.addErrorMessage("public void generarJson() dice: " + e.getMessage());
            System.out.println("public void generarJson() dice: " + e.getMessage());
        }
    }

    /**
     * @return the json
     */
    public String getJson() {
        return json;
    }

    /**
     * @param json the json to set
     */
    public void setJson(String json) {
        this.json = json;
    }

}
