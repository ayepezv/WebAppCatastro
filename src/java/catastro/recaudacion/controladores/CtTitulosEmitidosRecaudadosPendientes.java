package catastro.recaudacion.controladores;

import com.estadisticas.entidades.Estadisticas;
import com.estadisticas.servicios.ServiciosEstadisticas;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtTitulosEmitidosRecaudadosPendientes implements Serializable {

    private String json;
    private String json2;

    @PostConstruct
    public void init() {
        generarJson();
    }

    public void generarJson() {
        try {
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

            setJson("[");
            List<Estadisticas> lst = ServiciosEstadisticas.titulosEmitidosPendientesRecaudados();
            for (int i = 0; i < lst.size(); i++) {
                setJson(getJson() + "{'Año':'" + lst.get(i).getIndice() + "',");
                setJson(getJson() + "'Recaudados':'" + lst.get(i).getIndice3() + "',");
                setJson(getJson() + "'Pendientes':'" + lst.get(i).getIndice2() + "',");
                setJson(getJson() + "'Total':'" + lst.get(i).getIndice1() + "'");
                if (i != lst.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");

            /*
            inicio del json 2
             */
            setJson2("[");
            for (int i = 0; i < lst.size(); i++) {
                setJson2(getJson2() + "{'Año':'" + lst.get(i).getIndice() + "',");
                setJson2(getJson2() + "'Recaudados':'" + formateador.format(lst.get(i).getTotal1()) + "',");
                setJson2(getJson2() + "'Pendientes':'" + formateador.format(lst.get(i).getTotal2()) + "',");
                setJson2(getJson2() + "'Total':'" + formateador.format(lst.get(i).getTotal3()) + "'");
                if (i != lst.size() - 1) {
                    setJson2(getJson2() + "},");
                } else {
                    setJson2(getJson2() + "}");
                }
            }
            setJson2(getJson2() + "]");

            System.out.println("json1: " + getJson());
            System.out.println("json2: " + getJson2());

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
