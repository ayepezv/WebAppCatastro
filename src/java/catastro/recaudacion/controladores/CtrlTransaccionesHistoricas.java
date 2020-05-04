package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
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
public class CtrlTransaccionesHistoricas implements Serializable {

    private List<Transanccion> lstTransacciones;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaMax;
    private String json;
    private int idUsuarioSession;

    public CtrlTransaccionesHistoricas() {
        fechaMax = new Date();
    }

    @PostConstruct
    public void init() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        setIdUsuarioSession((int) session.getAttribute("idUsuario"));
    }

    public void graficar() {
        try {

            System.out.println("id usuariuo session " + idUsuarioSession);

            System.out.println("Fecha inicio util: " + fechaInicio);
            System.out.println("Fecha fin util: " + fechaFin);

            java.sql.Date sqlFechaInicio = Util.sqlDate(fechaInicio);
            java.sql.Date sqlFechaFin = Util.sqlDate(fechaFin);

            System.out.println("fecha inicio: " + sqlFechaInicio);
            System.out.println("fecha fin: " + sqlFechaFin);

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
            System.out.println(formateador.format(3.43242383));

            setLstTransacciones(FTransaccion.transaccionesDadoUsuarioRangoFecha(getIdUsuarioSession(), sqlFechaInicio, sqlFechaFin));
            setJson("");
            for (int i = 0; i < getLstTransacciones().size(); i++) {
                setJson(getJson() + "{'rubro':'" + getLstTransacciones().get(i).getRecaudacion() + "','total':'" + formateador.format(lstTransacciones.get(i).getValorTransaccion()) + "'}");
                if (i != getLstTransacciones().size() - 1) {
                    setJson(getJson() + ",");
                }
            }
            System.out.println("Test de json: " + getJson());

            RequestContext.getCurrentInstance().update("frmGraficos");

        } catch (Exception e) {
            System.out.println("public void graficar() dice: " + e.getMessage());
            Util.addErrorMessage("public void graficar() dice: " + e.getMessage());
        }
    }

    /**
     * @return the lstTransacciones
     */
    public List<Transanccion> getLstTransacciones() {
        return lstTransacciones;
    }

    /**
     * @param lstTransacciones the lstTransacciones to set
     */
    public void setLstTransacciones(List<Transanccion> lstTransacciones) {
        this.lstTransacciones = lstTransacciones;
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

    public Date getFechaMax() {
        return fechaMax;
    }

    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

}
