package catastro.recaudacion.controladores;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ReporteTitulo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class CtrlRecaudacionClientes implements Serializable {

    private List<Titulo> lstTitulos;
    private List<Titulo> lstTitulosPagados;
    private ArrayList<PredioV2> lstPredios;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private PredioV2 predio;
    private Titulo titulo;

    public CtrlRecaudacionClientes() {
        titulo = new Titulo();
        predio = new PredioV2();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerPredios();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void obtenerPredios() {
        try {
            setLstPredios(FuncionesPredio.obtenerPrediosDadoPropietario(getSessionUsuario().getIdPersona()));
        } catch (Exception e) {
            System.out.println("public void obtenerPredios() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void valoresPendientes() {
        try {
            setLstTitulos(FTitulos.consultarValoresPagarDadoPredio(getPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void valoresPendientes() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void consultarTitulos() {
        try {
            setLstTitulosPagados(FTitulos.consultarTitulosPagados(getPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void valoresPendientes() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerTitulos() {
        consultarTitulos();
        valoresPendientes();
    }

      public void verReportePdfPorId() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {        
        ReporteTitulo reporte = new ReporteTitulo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/ReporteTituloDuplicado.jasper");
        reporte.getReportePdfPorId(ruta, getTitulo().getIdTitulo());
        FacesContext.getCurrentInstance().responseComplete();
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

    /**
     * @return the lstTitulos
     */
    public List<Titulo> getLstTitulos() {
        return lstTitulos;
    }

    /**
     * @param lstTitulos the lstTitulos to set
     */
    public void setLstTitulos(List<Titulo> lstTitulos) {
        this.lstTitulos = lstTitulos;
    }

    /**
     * @return the lstTitulosPagados
     */
    public List<Titulo> getLstTitulosPagados() {
        return lstTitulosPagados;
    }

    /**
     * @param lstTitulosPagados the lstTitulosPagados to set
     */
    public void setLstTitulosPagados(List<Titulo> lstTitulosPagados) {
        this.lstTitulosPagados = lstTitulosPagados;
    }

    /**
     * @return the lstPredios
     */
    public ArrayList<PredioV2> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(ArrayList<PredioV2> lstPredios) {
        this.lstPredios = lstPredios;
    }

    /**
     * @return the predio
     */
    public PredioV2 getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(PredioV2 predio) {
        this.predio = predio;
    }

    /**
     * @return the titulo
     */
    public Titulo getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

}
