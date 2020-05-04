package catastro.presentacion.beans;

import catastro.logica.entidades.PredioV2;
import catastro.logica.entidades.RepositorioDocumentos;
import catastro.logica.servicios.FRepositorioDocumentos;
import catastro.logica.servicios.FuncionesPredio;
import digitales.logica.entidades.Documento;
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
public class CtRepDocumentos implements Serializable {

    private int zona;
    private int sector;
    private int manzana;
    private int numPredio;
    private PredioV2 objPredio;
    private RepositorioDocumentos repositorioSel;
    private RepositorioDocumentos objRepositorio;
    private List<RepositorioDocumentos> lstDocumentos;
    private Documento objDocumento;
    private Documento documentoSel;
    //manejo de imagenes
    private String nombreDocumento;
    private UploadedFile archivoDocumento;
    //cargar configuracion del  path
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtRepDocumentos() {
        objRepositorio = new RepositorioDocumentos();
        repositorioSel = new RepositorioDocumentos();
        documentoSel = new Documento();
        objDocumento = new Documento();
        objPredio = new PredioV2();
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

    public void buscarPredios() {
        try {
            //String strClave = String.valueOf(zona) + String.valueOf(sector) + String.valueOf(manzana) + String.valueOf(numPredio);
            String strClave = "";
            String strZona = "";
            String strSector = "";
            String strManzana = "";
            String strPredio = "";

            if ((getZona() / 10) < 1) {
                strZona = "0" + String.valueOf(getZona());
            } else {
                strZona = String.valueOf(getZona());
            }

            if ((getSector() / 10) < 1) {
                strSector = "0" + String.valueOf(getSector());
            } else {
                strSector = String.valueOf(getSector());
            }

            if ((getManzana() / 10) < 1) {
                strManzana = "00" + String.valueOf(getManzana());
            } else {
                strManzana = "0" + String.valueOf(getManzana());
            }

            if ((getNumPredio() / 10) < 1) {
                strPredio = "00" + String.valueOf(getNumPredio());
            } else {
                strPredio = "0" + String.valueOf(getNumPredio());
            }

            strClave = strZona + strSector + strManzana + strPredio;

            setObjPredio(FuncionesPredio.encontrarPredio(strClave).get(0));

            System.out.println("Id predio: " + objPredio.getIdPredio());

            setLstDocumentos(FRepositorioDocumentos.obtenerDocumentosDadoPredio(getObjPredio().getIdPredio()));

            Util.addSuccessMessage("Propietario: " + objPredio.getPropietario().getApellidos());

            Util.addSuccessMessage("Documentos encontrados: " + getLstDocumentos().size());
        } catch (Exception e) {
            System.out.println("public void buscarPredios() dice: " + e.getMessage());
            Util.addErrorMessage("public void buscarPredios() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            objDocumento.setSessionUsuario(sessionUsuario);
            objRepositorio.setDocumento(objDocumento);
            objRepositorio.setSessionUsuario(sessionUsuario);
            objRepositorio.setPredio(objPredio);
            String msg = FRepositorioDocumentos.registrarDocumentoDadoPredio(objRepositorio);
            Util.addSuccessMessage(msg);
            buscarPredios();
            resetearFitrosTabla("frmPrincipal:tblDocumentos");
            objRepositorio = new RepositorioDocumentos();
            objDocumento = new Documento();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertarDocumento').hide()");
        } catch (Exception e) {
            System.out.println("public void registrar() dice: " + e.getMessage());
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }
    
    
    public void eliminarDocumento(){
        try {
            String msg = FRepositorioDocumentos.eliminarDocumento(repositorioSel);
            Util.addSuccessMessage(msg);
            buscarPredios();
            resetearFitrosTabla("frmPrincipal:tblDocumentos");
            repositorioSel = new RepositorioDocumentos();            
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarDocumento').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarDocumento() dice: " + e.getMessage());
            Util.addErrorMessage("public void eliminarDocumento() dice: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            this.setLstDocumentos(new ArrayList<>());
            setZona(0);
            setSector(0);
            setManzana(0);
            setNumPredio(0);
        } catch (Exception e) {
            Util.addErrorMessage("public void limpiar() dice: " + e.getMessage());
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
        String rutaImagenes = getConfiguracion().getString("rutaDocumentos");
        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            getObjDocumento().setPath(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
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

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

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
     * @return the lstDocumentos
     */
    public List<RepositorioDocumentos> getLstDocumentos() {
        return lstDocumentos;
    }

    /**
     * @param lstDocumentos the lstDocumentos to set
     */
    public void setLstDocumentos(List<RepositorioDocumentos> lstDocumentos) {
        this.lstDocumentos = lstDocumentos;
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
     * @return the documentoSel
     */
    public Documento getDocumentoSel() {
        return documentoSel;
    }

    /**
     * @param documentoSel the documentoSel to set
     */
    public void setDocumentoSel(Documento documentoSel) {
        this.documentoSel = documentoSel;
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
     * @return the repositorioSel
     */
    public RepositorioDocumentos getRepositorioSel() {
        return repositorioSel;
    }

    /**
     * @param repositorioSel the repositorioSel to set
     */
    public void setRepositorioSel(RepositorioDocumentos repositorioSel) {
        this.repositorioSel = repositorioSel;
    }

}
