/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.entidades.TransferenciaDominio;
import catastro.logica.servicios.FTransferenciaDominio;
import catastro.logica.servicios.FTransferencias;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.messaging.ReqRespListener;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtTransferenciaDominio {

    private int zona;
    private int sector;
    private int manzana;
    private int predio;
    private int band;
    private String txtCedula;
    private String txtComprador;
    private String datosPropietario;
    private String datosComprador;
    private String prestamo;
    private String visible;
    private String visible1;
    private boolean habilitado;
    private String txtCriterio;
    private PredioV2 objPredio;
    private Usuario sessionUsuario;
    private Usuario propietario;
    private Usuario comprador;
    private ArrayList<PredioV2> lstPredios;
    private TransferenciaDominio objTransferencia;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private List<TransferenciaDominio> lstTransferencias;
    private List<Titulo> lstTitulos;

    public CtTransferenciaDominio() {
        visible = "hidden";
        visible1 = "hidden";
        habilitado = true;
        objTransferencia = new TransferenciaDominio();
        sessionUsuario = new Usuario();
        propietario = new Usuario();
        comprador = new Usuario();
        objPredio = new PredioV2();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerTransferencias();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerTransferencias() {
        try {
            setLstTransferencias(FTransferenciaDominio.listarTransferenciasDominio());
        } catch (Exception e) {
            System.out.println("public void obtenerTransferencias() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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

    public void buscarPredios() {
        try {
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getZona(), getSector(), getManzana(), getPredio()));
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) encontrado(s)");
            System.out.println(getLstPredios().size() + " Predio(s) encontrado(s)");
        } catch (Exception e) {
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void buscarPorPropietario() {
        try {
            setLstPredios(FuncionesPredio.encontrarPredio(getTxtCriterio()));
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) encontrado(s)");
            System.out.println(getLstPredios().size() + " Predio(s) encontrado(s)");
        } catch (Exception e) {
            System.out.println("public void buscarPorPropietario() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPorPropietario() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            setZona(0);
            setSector(0);
            setManzana(0);
            setPredio(0);
            setLstPredios(new ArrayList<>());
            setVisible("hidden");
            setVisible1("hidden");
            setDatosPropietario(null);
            setHabilitado(true);
            setTxtComprador(null);
            setDatosComprador(null);
            setTxtCriterio(null);
            RequestContext.getCurrentInstance().update("frmPrincipal");
            RequestContext.getCurrentInstance().update("frmTransferencia");
        } catch (Exception e) {
            System.out.println("public void limpiar() dice: " + e.getMessage());
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void chequear() {
        try {
            {//tareas previas
                setVisible("hidden");
                RequestContext.getCurrentInstance().update("frmTransferencia");
                setDatosPropietario(null);
                setDatosComprador(null);
                setTxtComprador(null);
                setComprador(new Usuario());
                setHabilitado(true);
                RequestContext.getCurrentInstance().update("frmTransferencia:txtDatosComprador");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtNumeroDocumento");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtTipoDocumento");
                RequestContext.getCurrentInstance().update("frmTransferencia:fechaDocumento");
                RequestContext.getCurrentInstance().update("frmTransferencia:cmbFormaAdq");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtCuantia");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtArea");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtNotaria");
                RequestContext.getCurrentInstance().update("frmTransferencia:fechaEscritura");
                RequestContext.getCurrentInstance().update("frmTransferencia:fechaRegistroTitulo");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtObservaciones");
                RequestContext.getCurrentInstance().update("frmTransferencia:btnRegistrar");
            }

            if (FTitulos.titulosPendientes(getObjPredio().getIdPredio()) > 0) {
                setLstTitulos(FTitulos.consultarValoresPagarDadoPredio(getObjPredio().getIdPredio()));
                Util.addErrorMessage("El predio tiene " + FTitulos.titulosPendientes(getObjPredio().getIdPredio()) + " pendientes de pago.");
                RequestContext.getCurrentInstance().execute("PF('dlgObligacionesPendientes').show()");
                RequestContext.getCurrentInstance().update("frmPendientes");
            } else {
                setVisible("visible");
                RequestContext.getCurrentInstance().update("frmTransferencia");
                RequestContext.getCurrentInstance().update("frmTransferencia:txtDatosProp");
                buscarPropietario();
            }
        } catch (Exception e) {
            System.out.println("public void chequear() dice: " + e.getMessage());
            Util.addErrorMessage("public void chequear() dice: " + e.getMessage());
        }
    }

    public void buscarPropietario() {
        try {
            setPropietario(ServiciosUsuario.buscarUsuarioDadoCedula(getObjPredio().getPropietario().getRuc()));
            Util.addSuccessMessage(getPropietario().getApellidos() + " " + getPropietario().getNombres());

            setDatosPropietario("<div class='contenedor-agendas'>");
            setDatosPropietario(getDatosPropietario() + "<div class='opcion-agenda opcion-seleccionada agenda'>");
            setDatosPropietario(getDatosPropietario() + "<a href='#'>");

            setDatosPropietario(getDatosPropietario() + "<span class='agenda-icon icon-seleccionado'>5</span>");

            setDatosPropietario(getDatosPropietario() + "<div class='contenido-agenda'>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Nombres:</b>" + getPropietario().getNombres());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Apellidos:</b>" + getPropietario().getApellidos());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Cédula/RUC: </b>" + getPropietario().getRuc());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Dirección: </b>" + getPropietario().getDireccionDom());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Teléfono: </b>" + getPropietario().getCelular());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "<div>");
            setDatosPropietario(getDatosPropietario() + "<b>Fecha de nacimiento: </b>" + getPropietario().getFechaNacimiento());
            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "</div>");

            setDatosPropietario(getDatosPropietario() + "</a>");
            setDatosPropietario(getDatosPropietario() + "</div>");
            setDatosPropietario(getDatosPropietario() + "</div>");
        } catch (Exception e) {
            System.out.println("public void buscarPropietario() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPropietario() dice: " + e.getMessage());
        }
    }

    public void buscarComprador() {
        try {
            System.out.println("total de personas encontradas: " + ServiciosUsuario.encontrarPersonasDadoCriterio(getTxtComprador()).size());

            if (ServiciosUsuario.encontrarPersonasDadoCriterio(getTxtComprador()).size() > 1) {
                setDatosComprador(null);
                setVisible1("hidden");
                setHabilitado(true);
                setTxtComprador(null);
                setDatosComprador(null);
                RequestContext.getCurrentInstance().update("frmTransferencia:txtDatosComprador");
                Util.addErrorMessage("Realice la búsqueda mediante la Cédula/Ruc de la Persona.");

            } else if (ServiciosUsuario.encontrarPersonasDadoCriterio(getTxtComprador()).size() == 1) {
                System.out.println("Id vendedor: " + getPropietario().getIdPersona() + " id comprador: " + ServiciosUsuario.buscarUsuarioDadoCedula(getTxtComprador()).getIdPersona());
                if (getPropietario().getIdPersona() == ServiciosUsuario.buscarUsuarioDadoCedula(getTxtComprador()).getIdPersona()) {
                    Util.addErrorMessage("El Compdrador y Vendedor no pueden ser la misma persona.");
                    datosComprador = null;
                    RequestContext.getCurrentInstance().update("frmTransferencia:txtDatosComprador");
                    setHabilitado(true);
                } else {
                    setVisible1("visible");
                    setHabilitado(false);
                    setComprador(ServiciosUsuario.buscarUsuarioDadoCedula(getTxtComprador()));
                    setDatosComprador("<div class='contenedor-agendas'>");

                    setDatosComprador(getDatosComprador() + "<div class='opcion-agenda opcion-seleccionada agenda'>");
                    setDatosComprador(getDatosComprador() + "<a href='#'>");

                    setDatosComprador(getDatosComprador() + "<span class='agenda-icon icon-seleccionado'>p</span>");

                    setDatosComprador(getDatosComprador() + "<div class='contenido-agenda'>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Nombres:</b>" + getComprador().getNombres());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Apellidos:</b>" + getComprador().getApellidos());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Cédula/RUC: </b>" + getComprador().getRuc());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Dirección: </b>" + getComprador().getDireccionDom());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Teléfono: </b>" + getComprador().getCelular());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "<div>");
                    setDatosComprador(getDatosComprador() + "<b>Fecha de nacimiento: </b>" + getComprador().getFechaNacimiento());
                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "</div>");

                    setDatosComprador(getDatosComprador() + "</a>");
                    setDatosComprador(getDatosComprador() + "</div>");
                    setDatosComprador(getDatosComprador() + "</div>");

                    RequestContext.getCurrentInstance().update("frmTransferencia:txtNumeroDocumento");
                    RequestContext.getCurrentInstance().update("frmTransferencia:txtTipoDocumento");
                    RequestContext.getCurrentInstance().update("frmTransferencia:fechaDocumento");
                }

            } else {
                setDatosComprador(null);
                RequestContext.getCurrentInstance().update("frmTransferencia:txtDatosComprador");
                Util.addErrorMessage("El comprador no se encuentra registrado en el Sistema. Regístrelo en el sistema y vuelva a intentarlo.");
            }
        } catch (Exception e) {
            System.out.println("public void buscarComprador() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarComprador() dice: " + e.getMessage());
        }
    }

    public void registarTransferencia() {
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            getObjTransferencia().setPrestamo("No");
            getObjTransferencia().setEntidad("Ninguna");
            getObjTransferencia().setObjeto("Ninguno");
            getObjTransferencia().setFechaConsec(sqlDate);
            getObjTransferencia().setPredio(getObjPredio());
            getObjTransferencia().setRazonExcencion("NN");
            getObjTransferencia().setPropietario(getPropietario());
            getObjTransferencia().setComprador(getComprador());
            getObjTransferencia().setSessionUsuario(getSessionUsuario());

            String msg = FTransferencias.registrar(getObjTransferencia());
            Util.addSuccessMessage(msg);

            setObjTransferencia(new TransferenciaDominio());
            setDatosPropietario("");
            setDatosComprador("");
            setPrestamo("");
            limpiar();
        } catch (Exception e) {
            System.out.println("public void registarTransferencia() dice: " + e.getMessage());
            Util.addErrorMessage("public void registarTransferencia() dice: " + e.getMessage());
        }
    }

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
     * @return the predio
     */
    public int getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(int predio) {
        this.predio = predio;
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
     * @return the objTransferencia
     */
    public TransferenciaDominio getObjTransferencia() {
        return objTransferencia;
    }

    /**
     * @param objTransferencia the objTransferencia to set
     */
    public void setObjTransferencia(TransferenciaDominio objTransferencia) {
        this.objTransferencia = objTransferencia;
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
     * @return the comprador
     */
    public Usuario getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the txtCedula
     */
    public String getTxtCedula() {
        return txtCedula;
    }

    /**
     * @param txtCedula the txtCedula to set
     */
    public void setTxtCedula(String txtCedula) {
        this.txtCedula = txtCedula;
    }

    /**
     * @return the txtComprador
     */
    public String getTxtComprador() {
        return txtComprador;
    }

    /**
     * @param txtComprador the txtComprador to set
     */
    public void setTxtComprador(String txtComprador) {
        this.txtComprador = txtComprador;
    }

    /**
     * @return the datosPropietario
     */
    public String getDatosPropietario() {
        return datosPropietario;
    }

    /**
     * @param datosPropietario the datosPropietario to set
     */
    public void setDatosPropietario(String datosPropietario) {
        this.datosPropietario = datosPropietario;
    }

    /**
     * @return the datosComprador
     */
    public String getDatosComprador() {
        return datosComprador;
    }

    /**
     * @param datosComprador the datosComprador to set
     */
    public void setDatosComprador(String datosComprador) {
        this.datosComprador = datosComprador;
    }

    /**
     * @return the prestamo
     */
    public String getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo the prestamo to set
     */
    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
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

    /**
     * @return the lstTransferencias
     */
    public List<TransferenciaDominio> getLstTransferencias() {
        return lstTransferencias;
    }

    /**
     * @param lstTransferencias the lstTransferencias to set
     */
    public void setLstTransferencias(List<TransferenciaDominio> lstTransferencias) {
        this.lstTransferencias = lstTransferencias;
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
     * @return the visible1
     */
    public String getVisible1() {
        return visible1;
    }

    /**
     * @param visible1 the visible1 to set
     */
    public void setVisible1(String visible1) {
        this.visible1 = visible1;

    }

    /**
     * @return the habilitado
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
