package catastro.recaudacion.controladores;

import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.FormaRecaudacion;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FFormasRecaudacion;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ReporteTitulo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlRecaudacionTitulos implements Serializable {

    private String parametro;
    private List<Titulo> lstTitulos;
    private List<Titulo> lstTitulosPagados;
    private List<Titulo> lstTitulosAnulados;
    private Usuario propietario;
    private Titulo titulo;
    private PredioV2 predio;
    private ArrayList<PredioV2> lstPredios;
    private double totalPagar;
    private ArrayList<Usuario> lstPersonas;
    private String cedula;
    private Usuario cliente;
    private List<FormaRecaudacion> lstFormasPago;
    private int idFormaPago;
    private double dineroEfectivo;
    private double vuelto;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private Titulo titulo2;
    private int band;
    private int intZona;
    private int intSector;
    private int intManzana;
    private int intPredio;

    public CtrlRecaudacionTitulos() {
        titulo = new Titulo();
        titulo2 = new Titulo();
        predio = new PredioV2();
        cliente = new Usuario();
        propietario = new Usuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerFormasPago();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
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

    public void obtenerPrediosDadoPropietario() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getParametro()));
            Util.addSuccessMessage("Predios obtenidos: " + getLstPredios().size());
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosDadoPropietario() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPrediosDadoPropietario() dice: " + e.getMessage());
        }
    }

    public void consultarTitulos() {
        try {
            setLstTitulos(FTitulos.consultarValoresPagarDadoPredio(getPredio().getIdPredio()));
            for (Titulo t : getLstTitulos()) {
                setTotalPagar(getTotalPagar() + t.getTotalPagar());
            }
        } catch (Exception e) {
            System.out.println("public void consultarTitulos() dice: " + e.getMessage());
            Util.addErrorMessage("public void consultarTitulos() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulosPagados() {
        try {
            setLstTitulosPagados(FTitulos.consultarTitulosPagados(getPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosPagados() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosPagados() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulosPagadosZonaSectorManzanapredio() {
        try {
            setLstTitulosPagados(FTitulos.consultarTitulosPagadosDadoZonaSectorManzanaPredio(getIntZona(), getIntSector(), getIntManzana(), getIntPredio()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstTitulosPagados().size()), "Titulos obtenidos"));
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosPagadosZonaSAectorManzanapredio() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosPagadosZonaSAectorManzanapredio() dice: " + e.getMessage());
        }
    }

    public void obtenerTitulosAnulados() {
        try {

            System.out.println("<<<<<<<<<<< TITULOS ANULADOS >>>>>>>>>>>>>>>");
            System.out.println("Id predio: " + getPredio().getIdPredio());

            setLstTitulosAnulados(FTitulos.consultarTitulosAnulados(getPredio().getIdPredio()));

        } catch (Exception e) {
            System.out.println("public void consultarTitulosAnulados() dice: " + e.getMessage());
            Util.addErrorMessage("public void consultarTitulosAnulados() dice: " + e.getMessage());
        }
        /*try {
            setLstTitulosAnulados(FTitulos.consultarTitulosAnulados(getPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void obtenerTitulosAnulados() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTitulosAnulados() dice: " + e.getMessage());
        }*/
    }

    public void obtenerTitulos() {
        obtenerTitulosPagados();
        consultarTitulos();
        obtenerTitulosAnulados();
    }

    public void obtenerPersonas() {
        try {
            setCliente(ServiciosUsuario.buscarUsuarioDadoCedula(getCedula()));
        } catch (Exception e) {
            System.out.println("public void obtenerPersonas() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPersonas() dice: " + e.getMessage());
        }
    }

    public void obtenerFormasPago() {
        try {
            setLstFormasPago(FFormasRecaudacion.obtenerFormasPago());
        } catch (Exception e) {
            System.out.println("public void obtenerFormasPago() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerFormasPago() dice: " + e.getMessage());
        }
    }

    public void recaudarTitulo() {
        try {
            System.out.println("Id titulo: " + getTitulo().getIdTitulo());
            String msg = FTitulos.recaudarTitulo(getTitulo(), getSessionUsuario().getIdPersona(), getCliente().getIdPersona(), getIdFormaPago());
            verReportePdfPorId();
            Util.addSuccessMessage(msg);
        } catch (Exception e) {
            System.out.println("public void recaudarTitulo() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void refrescarRecaudacion() {
        try {
            consultarTitulos();
            obtenerTitulosPagados();
            resetearFitrosTabla("frmPrincipal:tabTitulos:tblTitulos");
            resetearFitrosTabla("frmPrincipal:tabTitulos:tblTitulosPagados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgCobrarTitulo').hide()");
        } catch (Exception e) {
            System.out.println("public void refrescar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void verReportePdfPorId() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ReporteTitulo reporte = new ReporteTitulo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/ReporteTitulo.jasper");
        reporte.getReportePdfPorId(ruta, getTitulo().getIdTitulo());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void verReporteDuplicado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + getTitulo().getIdTitulo());
            ReporteTitulo reporte = new ReporteTitulo();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/ReporteTituloDuplicado.jasper");
            reporte.getReportePdfPorId(ruta, getTitulo().getIdTitulo());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + getTitulo().getIdTitulo());
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void verReporteDuplicado2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + getTitulo2().getIdTitulo());
            ReporteTitulo reporte = new ReporteTitulo();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/reportes/ReporteTituloDuplicado.jasper");
            reporte.getReportePdfPorId(ruta, getTitulo2().getIdTitulo());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("=========>>>>>>>>>>> Id Titulo: " + getTitulo2().getIdTitulo());
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void verReporteDuplicadoAnulada() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ReporteTitulo reporte = new ReporteTitulo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/ReporteTituloDuplicadoAnulado.jasper");
        reporte.getReportePdfPorId(ruta, getTitulo().getIdTitulo());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void calcularVuelto() {
        try {
            setVuelto(getDineroEfectivo() - getTitulo().getTotalPagar());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }

    public void limpiar() {
        try {
            setLstTitulos(new ArrayList<>());
            setLstPersonas(new ArrayList<>());
            setIntZona(0);
            setIntSector(0);
            setIntManzana(0);
            setIntPredio(0);
            setParametro(null);
            setLstPredios(new ArrayList<>());
            setLstTitulosPagados(new ArrayList<>());
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    /**
     * @return the parametro
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
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

    /**
     * @return the totalPagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * @param totalPagar the totalPagar to set
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the cliente
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the lstFormasPago
     */
    public List<FormaRecaudacion> getLstFormasPago() {
        return lstFormasPago;
    }

    /**
     * @param lstFormasPago the lstFormasPago to set
     */
    public void setLstFormasPago(List<FormaRecaudacion> lstFormasPago) {
        this.lstFormasPago = lstFormasPago;
    }

    /**
     * @return the idFormaPago
     */
    public int getIdFormaPago() {
        return idFormaPago;
    }

    /**
     * @param idFormaPago the idFormaPago to set
     */
    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    /**
     * @return the dineroEfectivo
     */
    public double getDineroEfectivo() {
        return dineroEfectivo;
    }

    /**
     * @param dineroEfectivo the dineroEfectivo to set
     */
    public void setDineroEfectivo(double dineroEfectivo) {
        this.dineroEfectivo = dineroEfectivo;
    }

    /**
     * @return the vuelto
     */
    public double getVuelto() {
        return vuelto;
    }

    /**
     * @param vuelto the vuelto to set
     */
    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    /**
     * @return the propietario
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the lstPersonas
     */
    public ArrayList<Usuario> getLstPersonas() {
        return lstPersonas;
    }

    /**
     * @param lstPersonas the lstPersonas to set
     */
    public void setLstPersonas(ArrayList<Usuario> lstPersonas) {
        this.lstPersonas = lstPersonas;
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
     * @return the lstTitulosAnulados
     */
    public List<Titulo> getLstTitulosAnulados() {
        return lstTitulosAnulados;
    }

    /**
     * @param lstTitulosAnulados the lstTitulosAnulados to set
     */
    public void setLstTitulosAnulados(List<Titulo> lstTitulosAnulados) {
        this.lstTitulosAnulados = lstTitulosAnulados;
    }

    /**
     * @return the titulo2
     */
    public Titulo getTitulo2() {
        return titulo2;
    }

    /**
     * @param titulo2 the titulo2 to set
     */
    public void setTitulo2(Titulo titulo2) {
        this.titulo2 = titulo2;
    }

    /**
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
    }

    /**
     * @return the intZona
     */
    public int getIntZona() {
        return intZona;
    }

    /**
     * @param intZona the intZona to set
     */
    public void setIntZona(int intZona) {
        this.intZona = intZona;
    }

    /**
     * @return the intSector
     */
    public int getIntSector() {
        return intSector;
    }

    /**
     * @param intSector the intSector to set
     */
    public void setIntSector(int intSector) {
        this.intSector = intSector;
    }

    /**
     * @return the intManzana
     */
    public int getIntManzana() {
        return intManzana;
    }

    /**
     * @param intManzana the intManzana to set
     */
    public void setIntManzana(int intManzana) {
        this.intManzana = intManzana;
    }

    /**
     * @return the intPredio
     */
    public int getIntPredio() {
        return intPredio;
    }

    /**
     * @param intPredio the intPredio to set
     */
    public void setIntPredio(int intPredio) {
        this.intPredio = intPredio;
    }

}
