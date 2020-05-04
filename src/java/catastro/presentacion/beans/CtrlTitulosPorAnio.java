package catastro.presentacion.beans;

import com.estadisticas.entidades.Estadisticas;
import com.estadisticas.servicios.ServiciosEstadisticas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CtrlTitulosPorAnio implements Serializable {

    private String json;

    @PostConstruct
    public void init() {
        try {
            List<Estadisticas> est = ServiciosEstadisticas.estadisticasTitulosPorAnio();
            setJson("[");
            for (int i = 0; i < est.size(); i++) {
                setJson(getJson() + "{'Año':'" + est.get(i).getIndice() + "',");
                setJson(getJson() + "'Total':'" + est.get(i).getTotalInt() + "'");
                if (i != est.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json Data: " + getJson());
        } catch (Exception e) {
            System.out.println("Error: public void init() dice: " + e.getMessage());
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
