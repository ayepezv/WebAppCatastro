/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.cem.controladores;

import catastro.cem.entidades.BeneficiariosObras;
import catastro.cem.entidades.FormaPago;
import catastro.cem.entidades.Mejora;
import catastro.cem.entidades.Obra;
import catastro.cem.entidades.ObraCalle;
import catastro.cem.entidades.TipoObra;
import catastro.cem.funciones.FBenefDirectos;
import catastro.cem.funciones.FDetalleCuotasCem;
import catastro.cem.funciones.FFormaPago;
import catastro.cem.funciones.FMejora;
import catastro.cem.funciones.FObra;
import catastro.cem.funciones.FObraCalle;
import catastro.cem.funciones.FTipoObra;
import catastro.logica.entidades.Calle;
import catastro.logica.servicios.ServiciosCalles;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtCem implements Serializable {

    private List<Mejora> lstMejoras;
    private Mejora objMejora;
    private Mejora mejoraSel;
    private String msgBd;
    private List<FormaPago> lstFormaPago;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private List<Obra> lstObras;
    private Obra obraSel;
    private Obra objObra;
    private List<TipoObra> lstTipoObras;
    private ArrayList<Calle> lstCalles;
    private List<BeneficiariosObras> lstBenefDirectos;
    private List<BeneficiariosObras> lstBenefIndirectos;
    private BeneficiariosObras beneficiario;
    private ArrayList<Calle> lstCallesSel;
    private Calle calleSel;
    private String txtCalle;
    private int idCalle;
    private List<ObraCalle> lstCallesObras;
    private ObraCalle OC;
    private ObraCalle OCSel;
    private int intVisible;
    private String visible;
    private String visibleBenef;

    public CtCem() {
        visible = "hidden";
        visibleBenef = "hidden";
        OC = new ObraCalle();
        OCSel = new ObraCalle();
        calleSel = new Calle();
        beneficiario = new BeneficiariosObras();
        mejoraSel = new Mejora();
        objMejora = new Mejora();
        objObra = new Obra();
        obraSel = new Obra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        lstCallesSel = new ArrayList<>();
        obtenerMejoras();
        obtenerTiposObras();
        obtenerFormaPago();
        seleccionarCalles();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    //<editor-fold defaultstate="collapsed" desc="Método para seleccionar las calles">    
    public void seleccionarCalles() {
        try {
            this.setLstCalles(ServiciosCalles.obtenerCalles());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccionar Calles dice: " + e.getMessage()));
            System.out.println("Seleccionar Calles dice: " + e.getMessage());
        }
    }
    //</editor-fold>

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

    public void obtenerMejoras() {
        try {
            setLstMejoras(FMejora.obtenerMejoras());
        } catch (Exception e) {
            System.out.println("public void obtenerMejoras() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerObras() {
        try {
            setLstObras(FObra.obtenerObraDadoMejora(getMejoraSel().getIdMejora()));
            if (getLstObras().size() > 0) {
                setVisible("visible");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(getLstObras().size()), " obras obtenidas."));
            } else {
                setVisible("visible");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "<div>"
                        + "<b>No se encontraron obras</b> "
                        + "</div>", "Consulte con el Administrador"));
            }
        } catch (Exception e) {
            System.out.println("public void obtenerObras() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerTiposObras() {
        try {
            setLstTipoObras(FTipoObra.obtenerTiposObras());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerFormaPago() {
        try {
            setLstFormaPago(FFormaPago.obtenerFormasPagos());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void registrarObra() {
        try {
            getObjObra().setMejora(getMejoraSel());
            getObjObra().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObra.registrarObra(getObjObra()));

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", getMsgBd());
            FacesContext.getCurrentInstance().addMessage(null, message);

            setObjObra(new Obra());
            obtenerObras();
            resetearFitrosTabla("frmObras:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgNuevaObra').hide();");
        } catch (Exception e) {
            System.out.println("public void registrarObra() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void editarObra() {
        try {
            getObraSel().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObra.editarObra(getObraSel()));

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", getMsgBd());
            FacesContext.getCurrentInstance().addMessage(null, message);

            setObraSel(new Obra());
            obtenerObras();
            resetearFitrosTabla("frmObras:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarObra').hide();");
        } catch (Exception e) {
            System.out.println("public void registrarObra() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void eliminarObra() {
        try {
            this.getObraSel().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObra.eliminarObra(getObraSel()));
            Util.addErrorMessage(getMsgBd());
            setObraSel(new Obra());
            resetearFitrosTabla("ffrmObras:tblObras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarObra').hide();");
            obtenerObras();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerBeneficiariosDirectos() {
        try {
//            setLstBenefDirectos(FBenefDirectos.previsualizarBeneficiariosDadoObra(getObraSel().getIdObra()));
            setLstBenefDirectos(FBenefDirectos.obtenerBeneficiariosDirectosV1(getObraSel().getIdObra()));
            Util.addSuccessMessage(getLstBenefDirectos().size() + " beneficiarios obtenidos");

            setVisibleBenef("visible");

            resetearFitrosTabla("frmBeneficiarios:tblBeneficiarios");
        } catch (Exception e) {
            System.out.println("public void obtenerBeneficiariosDirectos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerBeneficiariosIndirectos() {
        try {
            setLstBenefDirectos(FBenefDirectos.previsualizarBeneficiariosIndirectos(getObraSel().getIdObra()));
            Util.addSuccessMessage(getLstBenefDirectos().size() + " beneficiarios obtenidos");
        } catch (Exception e) {
            System.out.println("public void obtenerBeneficiariosDirectos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void registrarBeneficiarios() {
        int cont = 0;
        try {
            for (int i = 0; i < getLstBenefDirectos().size(); i++) {
                cont = i + 1;
                setBeneficiario(getLstBenefDirectos().get(i));
                getBeneficiario().setNumBeneficiario(cont);
                getBeneficiario().setSessionUsuario(getSessionUsuario());
                getBeneficiario().setFactor(getLstBenefDirectos().get(i).getObra().getValorDirecto()); // almaceno temporalmente el valor del metro o avaluo
                setMsgBd(FBenefDirectos.insertarBeneficiario(getBeneficiario()));
                System.out.println("Inserción " + cont + " dice: " + getMsgBd());
            }
            Util.addSuccessMessage("Beneficiarios registrados exitosamente.");
        } catch (Exception e) {
            System.out.println("public void registrarBeneficiarios() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void generarEmsisionCuotas() {
        try {
            System.out.println("Obra: " + getLstBenefDirectos().get(0).getObra().getIdObra());
            setMsgBd(FDetalleCuotasCem.registrarCuotasCemV2(getLstBenefDirectos().get(0).getObra().getIdObra(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsgBd());
        } catch (Exception e) {
            System.out.println("public void generarEmsisionCuotas() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void agregarCalles() {
        try {
            System.out.println("idCalle: " + getIdCalle());
            setCalleSel(ServiciosCalles.obtenerCalleDadoCodigo(getIdCalle()));
            getOC().setObra(getObraSel());
            getOC().setCalle(getCalleSel());
            getOC().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObraCalle.insertarObra(getOC()));
            obtenerCallesAfectadas();
            resetearFitrosTabla("frmAgregar:tblCallesAf");
            Util.addSuccessMessage("Se agregó la calle: " + getCalleSel().getNombre());
        } catch (Exception e) {
            System.out.println("public void agregarCalles() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void eliminarCalles() {
        try {
            System.out.println("Calle afectada: " + getOCSel().getCalle().getNombre());
            getOCSel().setSessionUsuario(getSessionUsuario());
            setMsgBd(FObraCalle.eliminarObra(getOCSel()));
            obtenerCallesAfectadas();
            resetearFitrosTabla("frmAgregar:tblCallesAf");
            Util.addSuccessMessage(getMsgBd());
        } catch (Exception e) {
            System.out.println("public void eliminarCalles() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCallesAfectadas() {
        try {
            System.out.println("Obra sel: " + getObraSel().getNombreObra() + " id: " + getObraSel().getIdObra());
            setLstCallesObras(FObraCalle.obtenerCallesDadoObra(getObraSel().getIdObra()));
        } catch (Exception e) {
            System.out.println("public void obtenerCallesAfectadas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarMejora() {
        try {
            getMejoraSel().setSesionUsuario(getSessionUsuario());
            setMsgBd(FMejora.actualizarMejora(getMejoraSel()));
            Util.addSuccessMessage(getMsgBd());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarMejora').hide();");
            this.obtenerMejoras();
            resetearFitrosTabla("frmPrincipal:tblMejoras");
            setMejoraSel(new Mejora());
        } catch (Exception e) {
            System.out.println("Error public void editarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void insertarMejora() {
        try {
            getObjMejora().setSesionUsuario(getSessionUsuario());
            this.setMsgBd(FMejora.registrarMejora(getObjMejora()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrarMejora').hide();");
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMsgBd(), getMsgBd());
            FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
            this.obtenerMejoras();
            resetearFitrosTabla("frmPrincipal:tblMejoras");
            this.setObjMejora(new Mejora());
        } catch (Exception e) {
            System.out.println("public void insertarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
        }
    }

    public void eliminarMejora() {
        try {
            setMsgBd(FMejora.eliminarMejora(getMejoraSel().getIdMejora(), getSessionUsuario().getIdPersona()));
            Util.addSuccessMessage(getMsgBd());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarMejora').hide();");
            this.obtenerMejoras();
            resetearFitrosTabla("frmPrincipal:tblMejoras");
        } catch (Exception e) {
            System.out.println("Error public void eliminarMejora() dice: " + e.getMessage());
            /*
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, message);
             */
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
     * @return the lstMejoras
     */
    public List<Mejora> getLstMejoras() {
        return lstMejoras;
    }

    /**
     * @param lstMejoras the lstMejoras to set
     */
    public void setLstMejoras(List<Mejora> lstMejoras) {
        this.lstMejoras = lstMejoras;
    }

    /**
     * @return the mejoraSel
     */
    public Mejora getMejoraSel() {
        return mejoraSel;
    }

    /**
     * @param mejoraSel the mejoraSel to set
     */
    public void setMejoraSel(Mejora mejoraSel) {
        this.mejoraSel = mejoraSel;

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
     * @return the objMejora
     */
    public Mejora getObjMejora() {
        return objMejora;
    }

    /**
     * @param objMejora the objMejora to set
     */
    public void setObjMejora(Mejora objMejora) {
        this.objMejora = objMejora;
    }

    /**
     * @return the objObra
     */
    public Obra getObjObra() {
        return objObra;
    }

    /**
     * @param objObra the objObra to set
     */
    public void setObjObra(Obra objObra) {
        this.objObra = objObra;
    }

    /**
     * @return the lstTipoObras
     */
    public List<TipoObra> getLstTipoObras() {
        return lstTipoObras;
    }

    /**
     * @param lstTipoObras the lstTipoObras to set
     */
    public void setLstTipoObras(List<TipoObra> lstTipoObras) {
        this.lstTipoObras = lstTipoObras;
    }

    /**
     * @return the msgBd
     */
    public String getMsgBd() {
        return msgBd;
    }

    /**
     * @param msgBd the msgBd to set
     */
    public void setMsgBd(String msgBd) {
        this.msgBd = msgBd;
    }

    /**
     * @return the lstFormaPago
     */
    public List<FormaPago> getLstFormaPago() {
        return lstFormaPago;
    }

    /**
     * @param lstFormaPago the lstFormaPago to set
     */
    public void setLstFormaPago(List<FormaPago> lstFormaPago) {
        this.lstFormaPago = lstFormaPago;
    }

    /**
     * @return the lstCalles
     */
    public ArrayList<Calle> getLstCalles() {
        return lstCalles;
    }

    /**
     * @param lstCalles the lstCalles to set
     */
    public void setLstCalles(ArrayList<Calle> lstCalles) {
        this.lstCalles = lstCalles;
    }

    /**
     * @return the lstBenefDirectos
     */
    public List<BeneficiariosObras> getLstBenefDirectos() {
        return lstBenefDirectos;
    }

    /**
     * @param lstBenefDirectos the lstBenefDirectos to set
     */
    public void setLstBenefDirectos(List<BeneficiariosObras> lstBenefDirectos) {
        this.lstBenefDirectos = lstBenefDirectos;
    }

    /**
     * @return the lstBenefIndirectos
     */
    public List<BeneficiariosObras> getLstBenefIndirectos() {
        return lstBenefIndirectos;
    }

    /**
     * @param lstBenefIndirectos the lstBenefIndirectos to set
     */
    public void setLstBenefIndirectos(List<BeneficiariosObras> lstBenefIndirectos) {
        this.lstBenefIndirectos = lstBenefIndirectos;
    }

    /**
     * @return the beneficiario
     */
    public BeneficiariosObras getBeneficiario() {
        return beneficiario;
    }

    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(BeneficiariosObras beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * @return the lstCallesSel
     */
    public ArrayList<Calle> getLstCallesSel() {
        return lstCallesSel;
    }

    /**
     * @param lstCallesSel the lstCallesSel to set
     */
    public void setLstCallesSel(ArrayList<Calle> lstCallesSel) {
        this.lstCallesSel = lstCallesSel;
    }

    /**
     * @return the calleSel
     */
    public Calle getCalleSel() {
        return calleSel;
    }

    /**
     * @param calleSel the calleSel to set
     */
    public void setCalleSel(Calle calleSel) {
        this.calleSel = calleSel;
    }

    /**
     * @return the txtCalle
     */
    public String getTxtCalle() {
        return txtCalle;
    }

    /**
     * @param txtCalle the txtCalle to set
     */
    public void setTxtCalle(String txtCalle) {
        this.txtCalle = txtCalle;
    }

    /**
     * @return the idCalle
     */
    public int getIdCalle() {
        return idCalle;
    }

    /**
     * @param idCalle the idCalle to set
     */
    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    /**
     * @return the lstCallesObras
     */
    public List<ObraCalle> getLstCallesObras() {
        return lstCallesObras;
    }

    /**
     * @param lstCallesObras the lstCallesObras to set
     */
    public void setLstCallesObras(List<ObraCalle> lstCallesObras) {
        this.lstCallesObras = lstCallesObras;
    }

    /**
     * @return the OC
     */
    public ObraCalle getOC() {
        return OC;
    }

    /**
     * @param OC the OC to set
     */
    public void setOC(ObraCalle OC) {
        this.OC = OC;
    }

    /**
     * @return the OCSel
     */
    public ObraCalle getOCSel() {
        return OCSel;
    }

    /**
     * @param OCSel the OCSel to set
     */
    public void setOCSel(ObraCalle OCSel) {
        this.OCSel = OCSel;
    }

    /**
     * @return the intVisible
     */
    public int getIntVisible() {
        return intVisible;
    }

    /**
     * @param intVisible the intVisible to set
     */
    public void setIntVisible(int intVisible) {
        this.intVisible = intVisible;
    }

    /**
     * @return the visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }

    /**
     * @return the visibleBenef
     */
    public String getVisibleBenef() {
        return visibleBenef;
    }

    /**
     * @param visibleBenef the visibleBenef to set
     */
    public void setVisibleBenef(String visibleBenef) {
        this.visibleBenef = visibleBenef;
    }

}
