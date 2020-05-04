package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorRecaudacionPorDia implements Serializable {

    private List<Transanccion> transacciones;
    private Date fechaInicio;
    private Date fechaFin;
    private String json;
    private String estilo;
    private int idUsuarioSession;

    @PostConstruct
    public void init() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        setIdUsuarioSession((int) session.getAttribute("idUsuario"));
    }

    public void generarJson() {
        try {
            java.sql.Date sqlFechaInicio = Util.sqlDate(fechaInicio);
            java.sql.Date sqlFechaFin = Util.sqlDate(fechaFin);

            System.out.println("fecha inicio: " + sqlFechaInicio);
            System.out.println("fecha fin: " + sqlFechaFin);

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
            System.out.println(formateador.format(3.43242383));

            List<Transanccion> dias = FTransaccion.obtenerDiasTransDadoUsuarioRangoFecha(idUsuarioSession, sqlFechaInicio, sqlFechaFin);

            json = "";
            for (int i = 0; i < dias.size(); i++) {
                transacciones = FTransaccion.transPorDiaUsuarioRangoFechaDia(idUsuarioSession, sqlFechaInicio, sqlFechaFin, dias.get(i).getObservaciones());
                json = json + "{'dia':'"
                        + dias.get(i).getObservaciones() + "'";

                for (int j = 0; j < transacciones.size(); j++) {
                    json = json + ",'"
                            + transacciones.get(j).getRecaudacion() + "':'"
                            + formateador.format(transacciones.get(j).getValorTransaccion()) + "'";
                }
                json = json + "}";
                if (i != dias.size() - 1) {
                    json = json + ",";
                }
            }

            estilo = "";
            List<Transanccion> trans = FTransaccion.obtenerTiposTransDadoUsuarioRangoFecha(idUsuarioSession, sqlFechaInicio, sqlFechaFin);
            for (int i = 0; i < trans.size(); i++) {
                if (i == 0) {
                    estilo = estilo
                            + "{'balloonText':'Recaudaciones de los días [[category]] ("
                            + trans.get(i).getRecaudacion()
                            + "): <b>$[[value]]</b>',";
                    estilo = estilo + "'fillAlphas': 0.9,"
                            + "'lineAlpha': 0.2,"                            
                            + "'title':'"
                            + trans.get(i).getRecaudacion() + "',"
                            + "'type':'column',"
                            + "'valueField':'"
                            + trans.get(i).getRecaudacion() + "'}";
                } else {
                    estilo = estilo
                            + "{'balloonText':'Recaudaciones de los días [[category]] ("
                            + trans.get(i).getRecaudacion()
                            + "): <b>$[[value]]</b>',";
                    estilo = estilo + "'fillAlphas': 0.9,"
                            + "'lineAlpha': 0.2,"                           
                            + "'title':'"
                            + trans.get(i).getRecaudacion() + "',"
                            + "'type':'column',"
                            + "'clustered':false,"
                            + "'columnWidth':0.5,"
                            + "'valueField':'"
                            + trans.get(i).getRecaudacion() + "'}";
                }
                if (i != trans.size() - 1) {
                    estilo = estilo + ",";
                }
            }

            RequestContext.getCurrentInstance().update("frmGraficos");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    /**
     * @return the idUsuarioSession
     */
    public int getIdUsuarioSession() {
        return idUsuarioSession;
    }

    /**
     * @param idUsuarioSession the idUsuarioSession to set
     */
    public void setIdUsuarioSession(int idUsuarioSession) {
        this.idUsuarioSession = idUsuarioSession;
    }

    public List<Transanccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transanccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
