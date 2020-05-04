package catastro.presentacion.beans;

import catastro.coeficientes.entidades.CoefAcabadosCubierta;
import catastro.coeficientes.entidades.CoefBanios;
import catastro.coeficientes.entidades.CoefClosets;
import catastro.coeficientes.entidades.CoefCocina;
import catastro.coeficientes.entidades.CoefColumnas;
import catastro.coeficientes.entidades.CoefCubierta;
import catastro.coeficientes.entidades.CoefCubreventanas;
import catastro.coeficientes.entidades.CoefEntrepisos;
import catastro.coeficientes.entidades.CoefEscaleras;
import catastro.coeficientes.entidades.CoefParedes;
import catastro.coeficientes.entidades.CoefPisos;
import catastro.coeficientes.entidades.CoefPuertas;
import catastro.coeficientes.entidades.CoefRevExterior;
import catastro.coeficientes.entidades.CoefRevInterior;
import catastro.coeficientes.entidades.CoefTumbados;
import catastro.coeficientes.entidades.CoefVentanas;
import catastro.coeficientes.entidades.CoefVigas;
import catastro.coeficientes.entidades.CoeficienteCimientos;
import catastro.coeficientes.entidades.CoeficienteEstado;
import catastro.coeficientes.funciones.FCoefAcabBanios;
import catastro.coeficientes.funciones.FCoefAcabClosets;
import catastro.coeficientes.funciones.FCoefAcabCocina;
import catastro.coeficientes.funciones.FCoefAcabCubierta;
import catastro.coeficientes.funciones.FCoefAcabCubreventanas;
import catastro.coeficientes.funciones.FCoefAcabPisos;
import catastro.coeficientes.funciones.FCoefAcabPuertas;
import catastro.coeficientes.funciones.FCoefAcabTumbados;
import catastro.coeficientes.funciones.FCoefAcabVentanas;
import catastro.coeficientes.funciones.FCoefCimientos;
import catastro.coeficientes.funciones.FCoefColumnas;
import catastro.coeficientes.funciones.FCoefEntrepisos;
import catastro.coeficientes.funciones.FCoefEscaleras;
import catastro.coeficientes.funciones.FCoefEstado;
import catastro.coeficientes.funciones.FCoefEstructCubierta;
import catastro.coeficientes.funciones.FCoefParedes;
import catastro.coeficientes.funciones.FCoefRevExt;
import catastro.coeficientes.funciones.FCoefRevInt;
import catastro.coeficientes.funciones.FCoefVigas;
import catastro.logica.entidades.Bloque2;
import catastro.logica.entidades.DetalleValorConstruccion;
import catastro.logica.entidades.Piso2;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FDetalleConstruccion;
import catastro.logica.servicios.FuncionesBloque;
import catastro.logica.servicios.FuncionesPiso;
import catastro.logica.servicios.FuncionesPredio;
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
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class RegistrarConstruccionesCtrl implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private String txtCriterio;
    private Bloque2 bloqueSel;
    private Piso2 pisoSel;
    private ArrayList<Bloque2> lstBloques;
    private ArrayList<Piso2> lstPisos;
    private ArrayList<PredioV2> lstPredios;
    private List<CoeficienteCimientos> lstCimientos;
    private List<CoefColumnas> lstColumnas;
    private List<CoefVigas> lstVigas;
    private List<CoefEntrepisos> lstEntrepisos;
    private List<CoefParedes> lstParedes;
    private List<CoefCubierta> lstCubierta;
    private List<CoefEscaleras> lstEscaleras;
    private List<CoefPisos> lstAcabPisos;
    private List<CoefPuertas> lstPuertas;
    private List<CoefVentanas> lstVentanas;
    private List<CoefCubreventanas> lstCubreVentanas;
    private List<CoefTumbados> lstTumbados;
    private List<CoefAcabadosCubierta> lstAcabCubierta;
    private List<CoefBanios> lstBanios;
    private List<CoefCocina> lstCocina;
    private List<CoefClosets> lstClosets;
    private List<CoefRevInterior> lstRevInterior;
    private List<CoefRevExterior> lstRevExterior;
    private List<CoeficienteEstado> lstEstadosConstruccion;
    private int band;
    private String msgBd;
    private DetalleValorConstruccion detalleConstruccion;
    private String visibilidad;
    private String visibleBloques;
    private String visiblePisos;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public RegistrarConstruccionesCtrl() {
        visibilidad = "hidden";
        visibleBloques = "hidden";
        visiblePisos = "hidden";
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        objPredio = new PredioV2();
        bloqueSel = new Bloque2();
        pisoSel = new Piso2();
        detalleConstruccion = new DetalleValorConstruccion();
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

    public void obtenerPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getZona(), getSector(), getManzana(), numPredio));
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) encontrado(s)");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPredios() dice: " + e.getMessage());
        }
    }

    public void obtenerCoeficientes() {
        try {
            this.obtenerEstados();
            this.obtenerCimientos();
            this.obtenerColumnas();
            this.obtenerVigas();
            this.obtenerEntrepisos();
            this.obtenerParedes();
            this.obtenerCubierta();
            this.obtenerEscaleras();
            this.obtenerAcabPisos();
            this.obtenerAcabPuertas();
            this.obtenerAcabVentanas();
            this.obtenerAcabCubreventanas();
            this.obtenerAcabTumbados();
            this.obtenerAcabCubierta();
            this.obtenerAcabBanios();
            this.obtenerAcabCocina();
            this.obtenerAcabClosets();
            this.obtenerRevExterior();
            this.obtenerRevInterior();
        } catch (Exception e) {
            System.out.println("public void obtenerCoeficientes() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerCoeficientes() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para buscar predios dado clave catastral ">    
    public void buscarPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getZona(), getSector(), getManzana(), getNumPredio()));
            if (getLstPredios().size() > 0) {
                setVisibilidad("visible");
            } else {
                setVisibilidad("hidden");
            }
            RequestContext.getCurrentInstance().update("frmPrincipal:tblPredios");
            Util.addSuccessMessage("Predios encontrados: " + getLstPredios().size());
        } catch (Exception e) {
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    public void encontrarPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getTxtCriterio()));
        } catch (Exception e) {
            Util.addErrorMessage("public void encontrarPredios() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstPredios(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
            setTxtCriterio(null);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void obtenerBloques() {
        try {
            //this.setLstBloques(FuncionesBloque.obtenerBloquesDadoClaveCatastral(objPredio.getClaveCatastral())));
            setLstBloques(FuncionesBloque.obtenerBloquesDadoClaveCatastral(getObjPredio().getClaveCatastral()));

            if (getLstBloques().size() > 0) {
                setVisibleBloques("visible");

            } else {
                setVisibleBloques("hidden");
            }
            RequestContext.getCurrentInstance().update("frmPrincipal:tblBloques");
            Util.addSuccessMessage("Total de bloques: " + getLstBloques().size());
        } catch (Exception e) {
            System.out.println("public void obtenerBloques() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerBloques() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para obtener los pisos dado bloque ">    
    public void obtenerPisosDadoBloque() {
        try {
            this.setLstPisos(FuncionesPiso.obtenerPisoDadoBloque(getBloqueSel().getIdBloque()));
            if (getLstPisos().size() > 0) {
                setVisiblePisos("visible");

            } else {
                setVisiblePisos("hidden");
                Util.addErrorMessage("No se encontraron pisos.");
            }
            RequestContext.getCurrentInstance().update("frmPrincipal:tblPisos");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPisosDadoBloque() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para actualizar el bloque">    
    public void actualizarBloque() {
        try {
            String msg = FuncionesBloque.actualizarBloqueAlfanumerico(getBloqueSel());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarBloque').hide()");
            resetearFitrosTabla("frmPrincipal:tblBloques");
            setBloqueSel(new Bloque2());
            obtenerBloques();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errror", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Obtención de Coeficientes">  
    public void obtenerEstados() {
        try {
            this.setLstEstadosConstruccion(FCoefEstado.coeficientesEstado());
        } catch (Exception e) {
            System.out.println("public void obtenerEstados() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCimientos() {
        try {
            this.setLstCimientos(FCoefCimientos.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerCimientos() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerColumnas() {
        try {
            this.setLstColumnas(FCoefColumnas.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerColumnas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerVigas() {
        try {
            this.setLstVigas(FCoefVigas.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerVigas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerEntrepisos() {
        try {
            this.setLstEntrepisos(FCoefEntrepisos.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerEntrepisos() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerParedes() {
        try {
            this.setLstParedes(FCoefParedes.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerParedes() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerCubierta() {
        try {
            this.setLstCubierta(FCoefEstructCubierta.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerCubierta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerEscaleras() {
        try {
            this.setLstEscaleras(FCoefEscaleras.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerEscaleras() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabPisos() {
        try {
            this.setLstAcabPisos(FCoefAcabPisos.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabPisos() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabPuertas() {
        try {
            this.setLstPuertas(FCoefAcabPuertas.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabPuertas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabVentanas() {
        try {
            this.setLstVentanas(FCoefAcabVentanas.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabVentanas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabCubreventanas() {
        try {
            this.setLstCubreVentanas(FCoefAcabCubreventanas.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabCubreventanas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabTumbados() {
        try {
            this.setLstTumbados(FCoefAcabTumbados.obtenerCoeficientes());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabTumbados() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabCubierta() {
        try {
            this.setLstAcabCubierta(FCoefAcabCubierta.obtenerCoefAcabadosCubierta());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabCubierta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabBanios() {
        try {
            this.setLstBanios(FCoefAcabBanios.obtenerCoefAcabadosBanios());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabBanios() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabCocina() {
        try {
            this.setLstCocina(FCoefAcabCocina.obtenerCoefAcabadosCocina());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabCocina() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerAcabClosets() {
        try {
            this.setLstClosets(FCoefAcabClosets.obtenerCoefAcabadosClosets());
        } catch (Exception e) {
            System.out.println("public void obtenerAcabClosets() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerRevInterior() {
        try {
            this.setLstRevInterior(FCoefRevInt.obtenerCoefAcabadosRevInterior());
        } catch (Exception e) {
            System.out.println("public void obtenerRevInterior() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerRevExterior() {
        try {
            this.setLstRevExterior(FCoefRevExt.obtenerCoefAcabadosRevExterior());
        } catch (Exception e) {
            System.out.println("public void obtenerRevExterior() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método para insertar un piso">    
    public void insertarPiso() {
        try {
            // objPiso.setBloque(bloqueSel);
            String msg = FuncionesPiso.actualizarPisoAlfanumerico(getPisoSel());
            Util.addSuccessMessage(msg);
            setPisoSel(new Piso2());
            obtenerPisosDadoBloque();
            DefaultRequestContext.getCurrentInstance().execute("PF('wdlgRegistrarPiso').hide()");
            resetearFitrosTabla("frmPrincipal:tblPisos");
        } catch (Exception e) {
            System.out.println("public void insertarPiso() dice: " + e.getMessage());
            Util.addErrorMessage("public void insertarPiso() dice: " + e.getMessage());
        }
    }
    //</editor-fold>

    public void eliminarPiso() {
        try {
            Util.addSuccessMessage("Piso a eliminar: " + getPisoSel().getIdPiso());
            setMsgBd(FuncionesPiso.eliminarPisosGlobal(getPisoSel(), getSessionUsuario()));
            setPisoSel(new Piso2());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación Exitosa", getMsgBd()));
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarPiso').hide();");
            obtenerPisosDadoBloque();

            if (lstPisos.size() > 0) {
                resetearFitrosTabla("frmPrincipal:tblPisos");
            } else {
                obtenerBloques();
                resetearFitrosTabla("frmPrincipal:tblBloques");
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "public void eliminarPiso() dice: ", e.getMessage()));
            System.out.println("public void eliminarPiso() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    public void obtenerDetalleConstruccion() {
        try {
            System.out.println("coidgo piso: " + getPisoSel().getIdPiso());
            setDetalleConstruccion(FDetalleConstruccion.obtenerDetalleDadoCodigo(getPisoSel().getIdPiso()));
        } catch (Exception e) {
            System.out.println("public void obtenerDetalleConstruccion() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerDetalleConstruccion() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters"> 
    /**
     * @return the zona
     */
    public int getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(int zona) {
        this.zona = zona;
    }

    /**
     * @return the sector
     */
    public int getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(int sector) {
        this.sector = sector;
    }

    /**
     * @return the manzana
     */
    public int getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    /**
     * @return the numPredio
     */
    public int getNumPredio() {
        return numPredio;
    }

    /**
     * @param numPredio the numPredio to set
     */
    public void setNumPredio(int numPredio) {
        this.numPredio = numPredio;
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
     * @return the objPredio
     */
    public PredioV2 getObjPredio() {
        return objPredio;
    }

    /**
     * @param objPredio the objPredio to set
     */
    public void setObjPredio(PredioV2 objPredio) {
        this.objPredio = objPredio;
    }

    /**
     * @return the txtCriterio
     */
    public String getTxtCriterio() {
        return txtCriterio;
    }

    /**
     * @param txtCriterio the txtCriterio to set
     */
    public void setTxtCriterio(String txtCriterio) {
        this.txtCriterio = txtCriterio;
    }

    /**
     * @return the lstBloques
     */
    public ArrayList<Bloque2> getLstBloques() {
        return lstBloques;
    }

    /**
     * @param lstBloques the lstBloques to set
     */
    public void setLstBloques(ArrayList<Bloque2> lstBloques) {
        this.lstBloques = lstBloques;
    }

    /**
     * @return the bloqueSel
     */
    public Bloque2 getBloqueSel() {
        return bloqueSel;
    }

    /**
     * @param bloqueSel the bloqueSel to set
     */
    public void setBloqueSel(Bloque2 bloqueSel) {
        this.bloqueSel = bloqueSel;
    }

    /**
     * @return the lstPisos
     */
    public ArrayList<Piso2> getLstPisos() {
        return lstPisos;
    }

    /**
     * @param lstPisos the lstPisos to set
     */
    public void setLstPisos(ArrayList<Piso2> lstPisos) {
        this.lstPisos = lstPisos;
    }

    /**
     * @return the pisoSel
     */
    public Piso2 getPisoSel() {
        return pisoSel;
    }

    /**
     * @param pisoSel the pisoSel to set
     */
    public void setPisoSel(Piso2 pisoSel) {
        this.pisoSel = pisoSel;
    }

    /**
     * @return the lstCimientos
     */
    public List<CoeficienteCimientos> getLstCimientos() {
        return lstCimientos;
    }

    /**
     * @param lstCimientos the lstCimientos to set
     */
    public void setLstCimientos(List<CoeficienteCimientos> lstCimientos) {
        this.lstCimientos = lstCimientos;
    }

    /**
     * @return the lstColumnas
     */
    public List<CoefColumnas> getLstColumnas() {
        return lstColumnas;
    }

    /**
     * @param lstColumnas the lstColumnas to set
     */
    public void setLstColumnas(List<CoefColumnas> lstColumnas) {
        this.lstColumnas = lstColumnas;
    }

    /**
     * @return the lstVigas
     */
    public List<CoefVigas> getLstVigas() {
        return lstVigas;
    }

    /**
     * @param lstVigas the lstVigas to set
     */
    public void setLstVigas(List<CoefVigas> lstVigas) {
        this.lstVigas = lstVigas;
    }

    /**
     * @return the lstEntrepisos
     */
    public List<CoefEntrepisos> getLstEntrepisos() {
        return lstEntrepisos;
    }

    /**
     * @param lstEntrepisos the lstEntrepisos to set
     */
    public void setLstEntrepisos(List<CoefEntrepisos> lstEntrepisos) {
        this.lstEntrepisos = lstEntrepisos;
    }

    /**
     * @return the lstParedes
     */
    public List<CoefParedes> getLstParedes() {
        return lstParedes;
    }

    /**
     * @param lstParedes the lstParedes to set
     */
    public void setLstParedes(List<CoefParedes> lstParedes) {
        this.lstParedes = lstParedes;
    }

    /**
     * @return the lstCubierta
     */
    public List<CoefCubierta> getLstCubierta() {
        return lstCubierta;
    }

    /**
     * @param lstCubierta the lstCubierta to set
     */
    public void setLstCubierta(List<CoefCubierta> lstCubierta) {
        this.lstCubierta = lstCubierta;
    }

    /**
     * @return the lstEscaleras
     */
    public List<CoefEscaleras> getLstEscaleras() {
        return lstEscaleras;
    }

    /**
     * @param lstEscaleras the lstEscaleras to set
     */
    public void setLstEscaleras(List<CoefEscaleras> lstEscaleras) {
        this.lstEscaleras = lstEscaleras;
    }

    /**
     * @return the lstAcabPisos
     */
    public List<CoefPisos> getLstAcabPisos() {
        return lstAcabPisos;
    }

    /**
     * @param lstAcabPisos the lstAcabPisos to set
     */
    public void setLstAcabPisos(List<CoefPisos> lstAcabPisos) {
        this.lstAcabPisos = lstAcabPisos;
    }

    /**
     * @return the lstPuertas
     */
    public List<CoefPuertas> getLstPuertas() {
        return lstPuertas;
    }

    /**
     * @param lstPuertas the lstPuertas to set
     */
    public void setLstPuertas(List<CoefPuertas> lstPuertas) {
        this.lstPuertas = lstPuertas;
    }

    /**
     * @return the lstVentanas
     */
    public List<CoefVentanas> getLstVentanas() {
        return lstVentanas;
    }

    /**
     * @param lstVentanas the lstVentanas to set
     */
    public void setLstVentanas(List<CoefVentanas> lstVentanas) {
        this.lstVentanas = lstVentanas;
    }

    /**
     * @return the lstCubreVentanas
     */
    public List<CoefCubreventanas> getLstCubreVentanas() {
        return lstCubreVentanas;
    }

    /**
     * @param lstCubreVentanas the lstCubreVentanas to set
     */
    public void setLstCubreVentanas(List<CoefCubreventanas> lstCubreVentanas) {
        this.lstCubreVentanas = lstCubreVentanas;
    }

    /**
     * @return the lstTumbados
     */
    public List<CoefTumbados> getLstTumbados() {
        return lstTumbados;
    }

    /**
     * @param lstTumbados the lstTumbados to set
     */
    public void setLstTumbados(List<CoefTumbados> lstTumbados) {
        this.lstTumbados = lstTumbados;
    }

    /**
     * @return the lstAcabCubierta
     */
    public List<CoefAcabadosCubierta> getLstAcabCubierta() {
        return lstAcabCubierta;
    }

    /**
     * @param lstAcabCubierta the lstAcabCubierta to set
     */
    public void setLstAcabCubierta(List<CoefAcabadosCubierta> lstAcabCubierta) {
        this.lstAcabCubierta = lstAcabCubierta;
    }

    /**
     * @return the lstBanios
     */
    public List<CoefBanios> getLstBanios() {
        return lstBanios;
    }

    /**
     * @param lstBanios the lstBanios to set
     */
    public void setLstBanios(List<CoefBanios> lstBanios) {
        this.lstBanios = lstBanios;
    }

    /**
     * @return the lstCocina
     */
    public List<CoefCocina> getLstCocina() {
        return lstCocina;
    }

    /**
     * @param lstCocina the lstCocina to set
     */
    public void setLstCocina(List<CoefCocina> lstCocina) {
        this.lstCocina = lstCocina;
    }

    /**
     * @return the lstClosets
     */
    public List<CoefClosets> getLstClosets() {
        return lstClosets;
    }

    /**
     * @param lstClosets the lstClosets to set
     */
    public void setLstClosets(List<CoefClosets> lstClosets) {
        this.lstClosets = lstClosets;
    }

    /**
     * @return the lstRevInterior
     */
    public List<CoefRevInterior> getLstRevInterior() {
        return lstRevInterior;
    }

    /**
     * @param lstRevInterior the lstRevInterior to set
     */
    public void setLstRevInterior(List<CoefRevInterior> lstRevInterior) {
        this.lstRevInterior = lstRevInterior;
    }

    /**
     * @return the lstRevExterior
     */
    public List<CoefRevExterior> getLstRevExterior() {
        return lstRevExterior;
    }

    /**
     * @param lstRevExterior the lstRevExterior to set
     */
    public void setLstRevExterior(List<CoefRevExterior> lstRevExterior) {
        this.lstRevExterior = lstRevExterior;
    }

    /**
     * @return the lstEstadosConstruccion
     */
    public List<CoeficienteEstado> getLstEstadosConstruccion() {
        return lstEstadosConstruccion;
    }

    /**
     * @param lstEstadosConstruccion the lstEstadosConstruccion to set
     */
    public void setLstEstadosConstruccion(List<CoeficienteEstado> lstEstadosConstruccion) {
        this.lstEstadosConstruccion = lstEstadosConstruccion;
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
    //</editor-fold>

    /**
     * @return the detalleConstruccion
     */
    public DetalleValorConstruccion getDetalleConstruccion() {
        return detalleConstruccion;
    }

    /**
     * @param detalleConstruccion the detalleConstruccion to set
     */
    public void setDetalleConstruccion(DetalleValorConstruccion detalleConstruccion) {
        this.detalleConstruccion = detalleConstruccion;
    }

    /**
     * @return the visibilidad
     */
    public String getVisibilidad() {
        return visibilidad;
    }

    /**
     * @param visibilidad the visibilidad to set
     */
    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    /**
     * @return the visibleBloques
     */
    public String getVisibleBloques() {
        return visibleBloques;
    }

    /**
     * @param visibleBloques the visibleBloques to set
     */
    public void setVisibleBloques(String visibleBloques) {
        this.visibleBloques = visibleBloques;
    }

    /**
     * @return the visiblePisos
     */
    public String getVisiblePisos() {
        return visiblePisos;
    }

    /**
     * @param visiblePisos the visiblePisos to set
     */
    public void setVisiblePisos(String visiblePisos) {
        this.visiblePisos = visiblePisos;
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

}
