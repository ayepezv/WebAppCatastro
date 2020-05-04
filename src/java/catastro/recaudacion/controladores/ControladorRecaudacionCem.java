package catastro.recaudacion.controladores;

import catastro.cem.entidades.DetalleCuotasCem;
import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.Obra;
import catastro.cem.funciones.FDetalleCuotasCem;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.FormaRecaudacion;
import catastro.recaudacion.funciones.FFormasRecaudacion;
import catastro.reportes.entidades.ReporteCem;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ControladorRecaudacionCem implements Serializable {

    private List<PredioV2> lstPredios;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private String parametro;
    private PredioV2 predioSel;
    private List<DetalleCuotasCem> lstCuotasPendientes;
    private List<DetalleCuotasCem> lstCuotasPagadas;
    private DetalleCuotasCem cuotaSel;
    private List<FormaRecaudacion> lstFormasPago;
    private int idFormaPago;
    private String cedula;
    private Usuario cliente;
    private double dineroEfectivo;
    private double vuelto;

    public ControladorRecaudacionCem() {
        cliente = new Usuario();
        cuotaSel = new DetalleCuotasCem();
        predioSel = new PredioV2();
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
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosDadoPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCuotas() {
        try {
            obtenerCuotasPendientes();
            obtenerCuotasPagadas();
//            resetearFitrosTabla("frmPrincipal:tabCuotas:tblValoresPendientes");
//            resetearFitrosTabla("frmPrincipal:tabTitulos:tblTitulosPagados");
        } catch (Exception e) {
            System.out.println("public void obtenerCuotas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCuotasPendientes() {
        try {
            setLstCuotasPendientes(FDetalleCuotasCem.consultarCuotasCemPendientesDadoPpredio(getPredioSel().getIdPredio()));
            System.out.println("Cuotas pendientes: " + lstCuotasPendientes.size());
        } catch (Exception e) {
            System.out.println("public void obtenerPrediosDadoPropietario() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCuotasPagadas() {
        try {
            setLstCuotasPagadas(FDetalleCuotasCem.obtenerCuotasPagadasDadoPredio(getPredioSel().getIdPredio()));
            System.out.println("Cuotas pagadas: " + lstCuotasPagadas.size());
        } catch (Exception e) {
            System.out.println("public void obtenerCuotasPagadas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
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

    public void obtenerPersonas() {
        try {
            setCliente(ServiciosUsuario.buscarUsuarioDadoCedula(getCedula()));
        } catch (Exception e) {
            System.out.println("public void obtenerPersonas() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPersonas() dice: " + e.getMessage());
        }
    }

    public void calcularVuelto() {
        try {
            setVuelto(getDineroEfectivo() - cuotaSel.getTotalPagar());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }

    public void recaudarCem() {
        try {
            String msg = FDetalleCuotasCem.cobrarCuotasCem(cliente.getIdPersona(), sessionUsuario.getIdPersona(), idFormaPago, cuotaSel.getCodigo());
            Util.addSuccessMessage(msg);
            verReportePdfPorId();
        } catch (Exception e) {
            System.out.println("public void recaudarTitulo() dice: " + e.getMessage());
            Util.addErrorMessage("public void recaudarTitulo() dice: " + e.getMessage());
        }
    }

    public void refrescarRecaudacion() {
        try {
            obtenerCuotasPagadas();
            obtenerCuotasPendientes();
            resetearFitrosTabla("frmPrincipal:tabCuotas:tblValoresPendientes");
            resetearFitrosTabla("frmPrincipal:tabCuotas:tblValoresPagados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRecaudarCem').hide()");
        } catch (Exception e) {
            System.out.println("public void refrescarRecaudacion() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void verReportePdfPorId() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ReporteCem reporte = new ReporteCem();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/PagoCem.jasper");
        reporte.getReportePdfPorId(ruta, cuotaSel.getCodigo());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void verReporteDuplicado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ReporteCem reporte = new ReporteCem();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/PagoCemDuplicado.jasper");
        reporte.getReportePdfPorId(ruta, cuotaSel.getCodigo());
        FacesContext.getCurrentInstance().responseComplete();
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

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
     * @return the lstPredios
     */
    public List<PredioV2> getLstPredios() {
        return lstPredios;
    }

    /**
     * @param lstPredios the lstPredios to set
     */
    public void setLstPredios(List<PredioV2> lstPredios) {
        this.lstPredios = lstPredios;
    }

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
     * @return the predioSel
     */
    public PredioV2 getPredioSel() {
        return predioSel;
    }

    /**
     * @param predioSel the predioSel to set
     */
    public void setPredioSel(PredioV2 predioSel) {
        this.predioSel = predioSel;
    }

    /**
     * @return the lstCuotasPendientes
     */
    public List<DetalleCuotasCem> getLstCuotasPendientes() {
        return lstCuotasPendientes;
    }

    /**
     * @param lstCuotasPendientes the lstCuotasPendientes to set
     */
    public void setLstCuotasPendientes(List<DetalleCuotasCem> lstCuotasPendientes) {
        this.lstCuotasPendientes = lstCuotasPendientes;
    }

    /**
     * @return the lstCuotasPagadas
     */
    public List<DetalleCuotasCem> getLstCuotasPagadas() {
        return lstCuotasPagadas;
    }

    /**
     * @param lstCuotasPagadas the lstCuotasPagadas to set
     */
    public void setLstCuotasPagadas(List<DetalleCuotasCem> lstCuotasPagadas) {
        this.lstCuotasPagadas = lstCuotasPagadas;
    }

    /**
     * @return the cuotaSel
     */
    public DetalleCuotasCem getCuotaSel() {
        return cuotaSel;
    }

    /**
     * @param cuotaSel the cuotaSel to set
     */
    public void setCuotaSel(DetalleCuotasCem cuotaSel) {
        this.cuotaSel = cuotaSel;
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
}
