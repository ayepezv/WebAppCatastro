package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Transanccion;
import catastro.recaudacion.funciones.FTransaccion;
import catastro.reportes.entidades.ServiciosReportes;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorRecaudacionesDiariasUsuario implements Serializable {

    private List<Transanccion> lstTransacciones;
    private String json;
    private String json2;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public ControladorRecaudacionesDiariasUsuario() {
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        transaccionesDiarias();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void transaccionesDiarias() {
        try {

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            setLstTransacciones(FTransaccion.reporteTransaccionesDadoUsuarioFecha(sessionUsuario.getIdPersona(), sqlDate));
            setJson("[");
            for (int i = 0; i < getLstTransacciones().size(); i++) {
                setJson(getJson() + "['" + getLstTransacciones().get(i).getRecaudacion() + "'," + getLstTransacciones().get(i).getValorTransaccion() + "]");
                if (i != getLstTransacciones().size() - 1) {
                    setJson(getJson() + ",");
                }
            }
            setJson(getJson() + "]");

            setJson2("");
            for (int i = 0; i < lstTransacciones.size(); i++) {
                setJson2(getJson2() + "{'rubro':'" + lstTransacciones.get(i).getRecaudacion() + "','total':'" + lstTransacciones.get(i).getValorTransaccion() + "'}");
                if (i != lstTransacciones.size() - 1) {
                    setJson2(getJson2() + ",");
                }
            }

        } catch (Exception e) {
            Util.addErrorMessage("public void transaccionesDiarias() dice: " + e.getMessage());
            System.out.println("public void transaccionesDiarias() dice: " + e.getMessage());
        }
    }

    public void generarReporteCatastro() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("//reportes//DetalleDiarioPorUsuario.jasper");
            reporte.getRDetRecaudDiariaCatastroPorUuario(ruta, getSessionUsuario().getIdPersona());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("public void verReporteDuplicado() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
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

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

}
