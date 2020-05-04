package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
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
public class CtRecaudacionDiariaPorCaja implements Serializable {

    private String json;

    @PostConstruct
    public void init() {
        generarJson();
    }

    public void generarJson() {
        try {
            /*
            gestión de fechas en sql
             */
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            /*
            formateador de números decimales
             */
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

            /*
            generación del json
             */
            List<Transanccion> lst = FTransaccion.transaccionesDiariasPorCaja(sqlDate);

            setJson("[");
            for (int i = 0; i < lst.size(); i++) {
                setJson(getJson() + "{'Caja': '");
                setJson(getJson() + lst.get(i).getRecaudador().getNick()+"',");
                setJson(getJson() + "'Total':'");
                setJson(getJson() + formateador.format(lst.get(i).getValorTransaccion()) + "'");
                if (i != lst.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");

            System.out.println("json: " + getJson());
        } catch (Exception e) {
            System.out.println("public void generarJson() dice: " + e.getMessage());
            Util.addErrorMessage("public void generarJson() dice: " + e.getMessage());
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
