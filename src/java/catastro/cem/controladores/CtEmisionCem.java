package catastro.cem.controladores;

import catastro.cem.entidades.BeneficiariosObras;
import catastro.cem.entidades.DetalleCuotasCem;
import catastro.cem.entidades.Obra;
import catastro.cem.funciones.FBenefDirectos;
import catastro.cem.funciones.FDetalleCuotasCem;
import catastro.cem.funciones.FObra;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtEmisionCem implements Serializable {

    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private List<Obra> lstObras;
    private Obra obraSel;
    private List<BeneficiariosObras> lstBeneficiarios;
    private String txtBusqueda;
    private BeneficiariosObras beneficiarioSel;
    private List<DetalleCuotasCem> lstCuotas;

    public CtEmisionCem() {
        beneficiarioSel = new BeneficiariosObras();
        obraSel = new Obra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void limpiar() {
        try {
            lstObras = new ArrayList<>();
            lstBeneficiarios = new ArrayList<>();
            lstCuotas = new ArrayList<>();
            txtBusqueda = "";
            beneficiarioSel = new BeneficiariosObras();
            obraSel = new Obra();
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(ServiciosUsuario.buscarUsuarioDadoId(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos());
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void encontrarObras() {
        try {
            setLstObras(FObra.encontrarObras(getTxtBusqueda()));
            Util.addSuccessMessage(getLstObras().size() + " obras encontradas");
        } catch (Exception e) {
            System.out.println("public void encontrarObras() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
            lstObras = new ArrayList<>();
        }
    }

    public void encontrarBeneficiarios() {
        try {
            setLstBeneficiarios(FBenefDirectos.obtenerBeneficiariosDadoObra(getObraSel().getIdObra()));
            Util.addSuccessMessage(getLstBeneficiarios().size() + " Beneficiarios encontrados.");
        } catch (Exception e) {
            System.out.println("public void encontrarBeneficiarios() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
            lstBeneficiarios = new ArrayList<>();
        }
    }

    public void obtenerCuotas() {
        try {
            setLstCuotas(FDetalleCuotasCem.obtenerCuotasDadoObraPredio(getBeneficiarioSel().getObra().getIdObra(), getBeneficiarioSel().getPredio().getIdPredio()));
        } catch (Exception e) {
            System.out.println("public void obtenerCuotas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
            lstCuotas = new ArrayList<>();
        }
    }

    /**
     * @return the txtBusqueda
     */
    public String getTxtBusqueda() {
        return txtBusqueda;
    }

    /**
     * @param txtBusqueda the txtBusqueda to set
     */
    public void setTxtBusqueda(String txtBusqueda) {
        this.txtBusqueda = txtBusqueda;
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
     * @return the lstObras
     */
    public List<Obra> getLstObras() {
        return lstObras;
    }

    /**
     * @param lstObras the lstObras to set
     */
    public void setLstObras(List<Obra> lstObras) {
        this.lstObras = lstObras;
    }

    /**
     * @return the obraSel
     */
    public Obra getObraSel() {
        return obraSel;
    }

    /**
     * @param obraSel the obraSel to set
     */
    public void setObraSel(Obra obraSel) {
        this.obraSel = obraSel;
    }

    /**
     * @return the lstBeneficiarios
     */
    public List<BeneficiariosObras> getLstBeneficiarios() {
        return lstBeneficiarios;
    }

    /**
     * @param lstBeneficiarios the lstBeneficiarios to set
     */
    public void setLstBeneficiarios(List<BeneficiariosObras> lstBeneficiarios) {
        this.lstBeneficiarios = lstBeneficiarios;
    }

    /**
     * @return the beneficiarioSel
     */
    public BeneficiariosObras getBeneficiarioSel() {
        return beneficiarioSel;
    }

    /**
     * @param beneficiarioSel the beneficiarioSel to set
     */
    public void setBeneficiarioSel(BeneficiariosObras beneficiarioSel) {
        this.beneficiarioSel = beneficiarioSel;
    }

    /**
     * @return the lstCuotas
     */
    public List<DetalleCuotasCem> getLstCuotas() {
        return lstCuotas;
    }

    /**
     * @param lstCuotas the lstCuotas to set
     */
    public void setLstCuotas(List<DetalleCuotasCem> lstCuotas) {
        this.lstCuotas = lstCuotas;
    }

}
