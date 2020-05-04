/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.cem.controladores;

import catastro.cem.entidades.Obra;
import catastro.cem.funciones.FObra;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CtrlEstadisticasTipoObras implements Serializable {

    private String json;

    @PostConstruct
    public void graficar() {
        try {
            List<Obra> obras = FObra.obtenerTiposObras();
            setJson("[");
            for (int i = 0; i < obras.size(); i++) {
                setJson(getJson() + "{'Tipo de obra':'" + obras.get(i).getTipoObra().getTipoObra() + "',");
                setJson(getJson() + "'Total':'" + FObra.obtenerObraDadoTipo(obras.get(i).getTipoObra().getIdTipoObra()).size() + "'");
                if (i != obras.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json data: " + getJson());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
