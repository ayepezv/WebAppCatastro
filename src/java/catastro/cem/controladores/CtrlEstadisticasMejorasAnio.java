/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.cem.controladores;

import catastro.cem.entidades.Mejora;
import catastro.cem.funciones.FMejora;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlEstadisticasMejorasAnio implements Serializable {
    
    private String json;
    
    @PostConstruct
    public void init() {
        this.graficar();
    }
    
    private void graficar() {
        try {
            List<Mejora> anios = FMejora.obtenerAniosAvaluoMejoras();
            setJson("[");
            for (int i = 0; i < anios.size(); i++) {
                setJson(getJson() + "{'Año':'" + anios.get(i).getAnioAvaluo() + "',");
                setJson(getJson() + "'Mejoras':'" + FMejora.encontrarMejoraDadoAnioAvaluo(anios.get(i).getAnioAvaluo()).size() + "'");
                if (i != anios.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json Data: " + json);
        } catch (Exception e) {
            Util.addErrorMessage("private void graficar() dice: " + e.getMessage());
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
