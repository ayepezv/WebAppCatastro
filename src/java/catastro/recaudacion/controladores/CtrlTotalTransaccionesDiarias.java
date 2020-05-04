package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CtrlTotalTransaccionesDiarias implements Serializable {

    private String json;

    @PostConstruct
    public void init() {
        this.graficar();
    }

    private void graficar() {
        try {
            Calendar cal = Calendar.getInstance();
            Date fecha = new Date();

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
            List<Transanccion> trans = FTransaccion.obtenerTotalTransaccionesDiarias();
            setJson("[");
            for (int i = 0; i < trans.size(); i++) {
                //
                fecha = trans.get(i).getFechaEmision();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, 1);
                ///

                json = json + "{'Fecha':'";
                json = json + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + "',";
                json = json + "'Total':'" + formateador.format(trans.get(i).getValorTransaccion()) + "'";
                if (i != trans.size() - 1) {
                    setJson(getJson() + "},");
                } else {
                    setJson(getJson() + "}");
                }
            }
            setJson(getJson() + "]");
            System.out.println("Json data: " + getJson());
        } catch (Exception e) {
            System.out.println("private void graficar() dice: " + e.getMessage());
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
