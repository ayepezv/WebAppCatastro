package catastro.recaudacion.controladores;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import catastro.reportes.entidades.ServiciosReportes;
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
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtRecaudacionMultiple implements Serializable {

    private String parametro;
    private ArrayList<Usuario> lstContribuyentes;
    private List<Titulo> lstTitulos;
    private List<Titulo> lstTitulosSel;
    private Usuario contribuyente;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private double deudaTotal;
    private double subTotal;
    private double intereses;
    private double total;
    private double descuentos;
    private String cedula;
    private Usuario cliente;
    private double dineroEfectivo;
    private double vuelto;
    private int band;
    private int intZona;
    private int intSector;
    private int intManzana;
    private int intPredio;
    private String deudasAnteriores;

    public CtRecaudacionMultiple() {
        contribuyente = new Usuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerContribuyentes() {
        try {
            java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.VariablesGlobales");
            int rolContribuyente = Integer.valueOf(Configuracion.getString("rolPropietario"));
            setLstContribuyentes(ServiciosUsuario.encontrarUsuarioDadoRol(getParametro(), rolContribuyente));

            setLstTitulos(new ArrayList<>());
            resetearFitrosTabla("frmPrincipal:tblTitulos");
        } catch (Exception e) {
            System.out.println("public void obtenerContribuyentes() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void consultarTitulos() {
        try {
            setDeudaTotal(0);
            setLstTitulos(FTitulos.consultarValoresPagarDadoPropietario(getContribuyente().getIdPersona()));
            
            if(lstTitulos.isEmpty()){
                Util.addSuccessMessage("El Predio no mentiene obligaciones pendientes.");
            }
            
            for (int i = 0; i < getLstTitulos().size(); i++) {
                setDeudaTotal(getDeudaTotal() + getLstTitulos().get(i).getTotalPagar());
            }
        } catch (Exception e) {
            System.out.println("public void consultarTitulos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void consultarTitulosClaveCatastral() {
        try {
            setDeudaTotal(0);
            setLstTitulos(FTitulos.consultarValoresPagarDadoZonaSectorManzanaPredio(getIntZona(), getIntSector(), getIntManzana(), getIntPredio()));

            if (getLstTitulos().size() == 0) {
                Util.addSuccessMessage("No tiene deudas pendientes");
            } else {
                for (int i = 0; i < getLstTitulos().size(); i++) {
                    setDeudaTotal(getDeudaTotal() + getLstTitulos().get(i).getTotalPagar());
                    setContribuyente(getLstTitulos().get(i).getPredio().getPropietario());
                }

                System.out.println("Contribuyente: " + "id - " + getContribuyente().getIdPersona() + " - " + getContribuyente().getApellidos() + " - " + getContribuyente().getNombres());
            }

        } catch (Exception e) {
            System.out.println("public void consultarTitulos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void buscarCliente() {
        try {
            setCliente(ServiciosUsuario.buscarUsuarioDadoCedula(getCedula()));
        } catch (Exception e) {
            System.out.println("public void obtenerPersonas() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerPersonas() dice: " + e.getMessage());
        }
    }

    public void verificarValores() {
        setSubTotal(0);
        setIntereses(0);
        setTotal(0);
        setDescuentos(0);
        try {

            int menor = getLstTitulosSel().get(getLstTitulosSel().size() - 1).getAnioTitulo();
            System.out.println("Año menor antes de empezar el ciclo for: " + menor);
            for (int j = 0; j < getLstTitulosSel().size(); j++) {
                System.out.println("Año seleccionado:  " + getLstTitulosSel().get(j).getAnioTitulo());
                if (menor > getLstTitulosSel().get(j).getAnioTitulo()) {
                    menor = getLstTitulosSel().get(j).getAnioTitulo();
                    System.out.println("Nuevo año menor: " + menor);
                }
            }
            System.out.println("Menor despues del ciclo for: " + menor);
            if (FTitulos.obtenerDeudasAnterioresDadoTituloAnio(getLstTitulosSel().get(0).getPredio().getIdPredio(), menor).size() > 0) {
                setDeudasAnteriores("");
                for (int c = 0; c < FTitulos.obtenerDeudasAnterioresDadoTituloAnio(getLstTitulosSel().get(0).getPredio().getIdPredio(), menor).size(); c++) {
                    setDeudasAnteriores(getDeudasAnteriores() + " - " + FTitulos.obtenerDeudasAnterioresDadoTituloAnio(getLstTitulosSel().get(0).getPredio().getIdPredio(), menor).get(c).getAnioTitulo());
                }
                setDeudasAnteriores(getDeudasAnteriores() + " - ");
                DefaultRequestContext.getCurrentInstance().execute("PF('dlgDeudas').show()");
                RequestContext.getCurrentInstance().update("frmDeudas");
            } else {
                for (int i = 0; i < getLstTitulosSel().size(); i++) {
                    System.out.println("Anio titulo: " + getLstTitulosSel().get(i).getAnioTitulo());
                    setSubTotal(getSubTotal() + getLstTitulosSel().get(i).getTotalTitulo());
                    setIntereses(getIntereses() + getLstTitulosSel().get(i).getInteresesMora());
                    setTotal(getTotal() + getLstTitulosSel().get(i).getTotalPagar());
                    setDescuentos(getDescuentos() + getLstTitulosSel().get(i).getDescuento());
                }
                DefaultRequestContext.getCurrentInstance().execute("PF('dlgVerificar').show()");
                RequestContext.getCurrentInstance().update("frmCobrarTitulo");
            }

        } catch (Exception e) {
            System.out.println("public void verificarValores() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void verificarDeudasExistentes() {
        try {
            int menor = getLstTitulosSel().get(getLstTitulosSel().size() - 1).getAnioTitulo();
            System.out.println("Año menor antes de empezar el ciclo for: " + menor);
            for (int i = 0; i < getLstTitulosSel().size(); i++) {
                System.out.println("Año seleccionado:  " + getLstTitulosSel().get(i).getAnioTitulo());
                if (menor > getLstTitulosSel().get(i).getAnioTitulo()) {
                    menor = getLstTitulosSel().get(i).getAnioTitulo();
                    System.out.println("Nuevo año menor: " + menor);
                }
            }
            System.out.println("Menor despues del ciclo for: " + menor);
            if (FTitulos.obtenerDeudasAnterioresDadoTituloAnio(getLstTitulosSel().get(0).getPredio().getIdPredio(), menor).size() > 0) {
                Util.addErrorMessage("No se pueden recaudar los predios seleccionados. El predio tiene obligaciones de años anteriores");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void calcularVuelto() {
        try {
            setVuelto(getDineroEfectivo() - getTotal());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }

    public void recaudar() {
        try {
            String msg;
            int formaPago = 1; // forma de pago efectivo           
            for (int i = 0; i < getLstTitulosSel().size(); i++) {
                msg = FTitulos.recaudarTitulo(getLstTitulosSel().get(i),
                        getSessionUsuario().getIdPersona(),
                        getLstTitulosSel().get(i).getPredio().getPropietario().getIdPersona(),
                        formaPago);
                System.out.println(msg);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(i + 1) + "° Titulo Recaudado", " Titulo Recaudado");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            Util.addSuccessMessage("Recaudación finalizada.");

            if (getIntZona() == 0) {
                consultarTitulos();
            } else {
                consultarTitulosClaveCatastral();
            }

            resetearFitrosTabla("frmPrincipal:tblTitulos");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgVerificar').hide()");
            /// limpio los valores calculados
            setSubTotal(0);
            setIntereses(0);
            setTotal(0);
            setDescuentos(0);
            setContribuyente(new Usuario());
            setCedula("");
            setDineroEfectivo(0);
            setVuelto(0);
        } catch (Exception e) {
            System.out.println("public void recaudar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void generarReporte() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            ServiciosReportes reporte = new ServiciosReportes();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            if (getIntZona() == 0) {
                String ruta = servletContext.getRealPath("//reportes//ReporteTituloMultiple.jasper");
                reporte.getReporteTituloMultiple(ruta, getContribuyente().getIdPersona());
            } else {
                String ruta = servletContext.getRealPath("//reportes//ReporteTituloMultipleZona.jasper");
                reporte.getReporteTituloMultipleZonaSectorManzanaPredio(ruta, getIntZona(), getIntSector(), getIntManzana(), getIntPredio());
            }
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("public void generarReporte() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiar() {
        try {
            setLstTitulos(new ArrayList<>());
            setLstContribuyentes(new ArrayList<>());
            setIntZona(0);
            setIntSector(0);
            setIntManzana(0);
            setIntPredio(0);
            setParametro(null);
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
     * @return the lstContribuyentes
     */
    public ArrayList<Usuario> getLstContribuyentes() {
        return lstContribuyentes;
    }

    /**
     * @param lstContribuyentes the lstContribuyentes to set
     */
    public void setLstContribuyentes(ArrayList<Usuario> lstContribuyentes) {
        this.lstContribuyentes = lstContribuyentes;
    }

    /**
     * @return the contribuyente
     */
    public Usuario getContribuyente() {
        return contribuyente;
    }

    /**
     * @param contribuyente the contribuyente to set
     */
    public void setContribuyente(Usuario contribuyente) {
        this.contribuyente = contribuyente;
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
     * @return the deudaTotal
     */
    public double getDeudaTotal() {
        return deudaTotal;
    }

    /**
     * @param deudaTotal the deudaTotal to set
     */
    public void setDeudaTotal(double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    /**
     * @return the lstTitulosSel
     */
    public List<Titulo> getLstTitulosSel() {
        return lstTitulosSel;
    }

    /**
     * @param lstTitulosSel the lstTitulosSel to set
     */
    public void setLstTitulosSel(List<Titulo> lstTitulosSel) {
        this.lstTitulosSel = lstTitulosSel;
    }

    /**
     * @return the subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the intereses
     */
    public double getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the descuentos
     */
    public double getDescuentos() {
        return descuentos;
    }

    /**
     * @param descuentos the descuentos to set
     */
    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
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

    public String getDeudasAnteriores() {
        return deudasAnteriores;
    }

    public void setDeudasAnteriores(String deudasAnteriores) {
        this.deudasAnteriores = deudasAnteriores;
    }

    
    
    
}
