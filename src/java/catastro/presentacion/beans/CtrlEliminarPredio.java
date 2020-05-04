/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.presentacion.beans;

import catastro.logica.entidades.PredioHistorico;
import catastro.logica.entidades.PredioV2;
import catastro.logica.servicios.FPrediosHistoricos;
import catastro.logica.servicios.FuncionesBloque;
import catastro.logica.servicios.FuncionesPredio;
import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.funciones.FTitulos;
import digitales.logica.entidades.Documento;
import digitales.logica.entidades.TipoDocumento;
import digitales.logica.funciones.FTipoDocumento;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlEliminarPredio implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int predio;
    private int band;
    private ArrayList<PredioV2> lstPredios;
    private PredioV2 predioSel;
    private PredioHistorico objPredioHistorico;
    private List<TipoDocumento> lstTiposDocumento;
    private int tipoDocumento;
    private Documento objDocumento;
    private String nombreDocumento;
    private UploadedFile archivoDocumento;
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private List<Titulo> lstTitulos;
    private Titulo objTitulo;

    public CtrlEliminarPredio() {
        objDocumento = new Documento();
        predioSel = new PredioV2();
        objPredioHistorico = new PredioHistorico();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerTiposDocumentos();
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
            setLstPredios(FuncionesPredio.encontrarPrediosDadoZonaSectorManzana(getZona(), getSector(), getManzana(), getPredio()));
            Util.addSuccessMessage(getLstPredios().size() + " Predio(s) encontrado(s)");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerPredios() dice: " + e.getMessage());
        }
    }

    public void obtenerTiposDocumentos() {
        try {
            setLstTiposDocumento(FTipoDocumento.obtenerTiposDocumentos());
        } catch (Exception e) {
            System.out.println("public void obtenerTiposDocumentos() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerTiposDocumentos() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            setLstPredios(new ArrayList<>());
            setBand(0);
            setZona(0);
            setSector(0);
            setManzana(0);
            setPredio(0);
            setObjDocumento(new Documento());
            setPredioSel(new PredioV2());
            setObjPredioHistorico(new PredioHistorico());
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
        }
    }

    public void verificarPendientes() {
        try {
            if (FTitulos.titulosPendientes(getPredioSel().getIdPredio()) > 0) {
                Util.addErrorMessage("El predio tiene " + FTitulos.titulosPendientes(getPredioSel().getIdPredio()) + " pendientes de pago.");
                consultarPendientes();
                DefaultRequestContext.getCurrentInstance().execute("PF('dlgObligacionesPendientes').show()");
            } else if (FuncionesBloque.obtenerBloquesDadoPredio(predioSel.getIdPredio()).size() > 0) {
                Util.addErrorMessage("El Predio tiene construcciones.");
                DefaultRequestContext.getCurrentInstance().execute("PF('dlgConfirmar').show()");
            } else {
                DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarPredio').show()");
            }
        } catch (Exception e) {
            System.out.println("public void verificarPendientes() dice: " + e.getMessage());
            Util.addErrorMessage("public void verificarPendientes() dice: " + e.getMessage());
        }
    }

    public void procederEliminacion() {
        try {
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgConfirmar').hide()");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarPredio').show()");
        } catch (Exception e) {
            System.out.println("public void proceder() dice: " + e.getMessage());
            Util.addErrorMessage("public void proceder() dice: " + e.getMessage());
        }
    }

    public void consultarPendientes() {
        try {
            lstTitulos = FTitulos.consultarValoresPagarDadoPredio(predioSel.getIdPredio());
        } catch (Exception e) {
            System.out.println("public void consultarPendientes() dice: " + e.getMessage());
            Util.addErrorMessage("public void consultarPendientes() dice: " + e.getMessage());
        }
    }

    public void eliminarPredio() {
        try {
            getObjDocumento().getTipoDocumento().setIdTipo(getTipoDocumento());
            getObjPredioHistorico().setDocumento(getObjDocumento());
            getObjPredioHistorico().setPredio(getPredioSel());
            getObjPredioHistorico().setSessionUsuario(getSessionUsuario());
            String msg = FPrediosHistoricos.registrarEliminacion(getObjPredioHistorico());
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarPredio').hide()");
            Util.addSuccessMessage(msg);
            this.limpiar();
            resetearFitrosTabla("frmPrincipal:tblPredios");
        } catch (Exception e) {
            System.out.println("public void eliminarPredio() dice: " + e.getMessage());
            Util.addErrorMessage("public void eliminarPredio() dice: " + e.getMessage());
        }
    }

    public void menu() {
        try {
            switch (getBand()) {
                case 1:
                    this.obtenerPredios();
                    break;
                case 2:
                    limpiar();
                    break;

                default:
                    Util.addErrorMessage("Error inesperado del sistema");
                    break;
            }

        } catch (Exception e) {
            Util.addErrorMessage("public void menu() dice: " + e.getMessage());
        }
    }

    public void cargarArchivoDocumento(FileUploadEvent e) {
        System.out.println("Entra al método cargar documento");
        UploadedFile file = e.getFile();
        this.setArchivoDocumento(file);
        System.out.println(file.getContentType());
        getObjDocumento().setTipo(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getFileName());
        setNombreDocumento(file.getFileName());
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido)) {
                Util.addSuccessMessage("Documento guardado con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar el Documento");
            }
        } catch (IOException ex) {
            Logger.getLogger(CtRepDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean guardarArchivo(String nombre, byte[] contenido) {
        String rutaImagenes = getConfiguracion().getString("rutaPrediosHist");
        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //getObjDocumento().setPath(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            System.out.println("Ruta a guardar " + Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));

            String rutaTemp = Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length());
            getObjDocumento().setPath(rutaTemp.replace('\\', '/'));

            getObjDocumento().setTitulo(nombre);
            System.out.println("cargar objeto fos ");
            FileOutputStream fos = new FileOutputStream(f);
            System.out.println("escribir fos ");
            fos.write(contenido);
            return true;
        } catch (Exception e) {
            Util.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
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
     * @return the objPredioHistorico
     */
    public PredioHistorico getObjPredioHistorico() {
        return objPredioHistorico;
    }

    /**
     * @param objPredioHistorico the objPredioHistorico to set
     */
    public void setObjPredioHistorico(PredioHistorico objPredioHistorico) {
        this.objPredioHistorico = objPredioHistorico;
    }

    /**
     * @return the lstTiposDocumento
     */
    public List<TipoDocumento> getLstTiposDocumento() {
        return lstTiposDocumento;
    }

    /**
     * @param lstTiposDocumento the lstTiposDocumento to set
     */
    public void setLstTiposDocumento(List<TipoDocumento> lstTiposDocumento) {
        this.lstTiposDocumento = lstTiposDocumento;
    }

    /**
     * @return the tipoDocumento
     */
    public int getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the objDocumento
     */
    public Documento getObjDocumento() {
        return objDocumento;
    }

    /**
     * @param objDocumento the objDocumento to set
     */
    public void setObjDocumento(Documento objDocumento) {
        this.objDocumento = objDocumento;
    }

    /**
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * @return the archivoDocumento
     */
    public UploadedFile getArchivoDocumento() {
        return archivoDocumento;
    }

    /**
     * @param archivoDocumento the archivoDocumento to set
     */
    public void setArchivoDocumento(UploadedFile archivoDocumento) {
        this.archivoDocumento = archivoDocumento;
    }

    /**
     * @return the Configuracion
     */
    public java.util.ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    /**
     * @param Configuracion the Configuracion to set
     */
    public void setConfiguracion(java.util.ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
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
     * @return the objTitulo
     */
    public Titulo getObjTitulo() {
        return objTitulo;
    }

    /**
     * @param objTitulo the objTitulo to set
     */
    public void setObjTitulo(Titulo objTitulo) {
        this.objTitulo = objTitulo;
    }

}
