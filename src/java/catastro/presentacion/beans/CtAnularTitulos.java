package catastro.presentacion.beans;

import catastro.recaudacion.entidades.Titulo;
import catastro.recaudacion.entidades.TitulosHistoricos;
import catastro.recaudacion.funciones.FTitulos;
import catastro.recaudacion.funciones.FTitulosHistoricos;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtAnularTitulos implements Serializable {

    private List<Titulo> lstTitulos;
    private int numZona;
    private int numSector;
    private int numManzana;
    private int numPredio;
    private Titulo titulo;
    private TitulosHistoricos objTituloHist;
    private TitulosHistoricos tituloHistSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    //manejo de documentos
    private String nombreDocumento;
    private UploadedFile archivoDocumento;
    //cargar configuracion del  path
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public CtAnularTitulos() {
        titulo = new Titulo();
        objTituloHist = new TitulosHistoricos();
        tituloHistSel = new TitulosHistoricos();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void inicializar() {
        try {
            titulo = new Titulo();
            objTituloHist = new TitulosHistoricos();
            tituloHistSel = new TitulosHistoricos();
            lstTitulos = new ArrayList<>();
            numZona = 0;
            numSector = 0;
            numManzana = 0;
            numPredio = 0;
        } catch (Exception e) {
            System.out.println("public void inicializar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void buscarTitulos() {
        try {
            setLstTitulos(FTitulos.obtenerTitulosDadoZonaSectorManzanaPredio(getNumZona(), getNumSector(), getNumManzana(), getNumPredio()));
            Util.addSuccessMessage(lstTitulos.size() + " obtenidos");
        } catch (Exception e) {
            System.out.println("public void buscarTitulos() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void seleccionar() {
        try {
            Util.addSuccessMessage("Número de título seleccionado: " + titulo.getTituloNumero());
        } catch (Exception e) {
            System.out.println("public void seleccionar() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void anularTitulo() {
        try {
            objTituloHist.setSessionUsuario(sessionUsuario);
            objTituloHist.setTitulo(titulo);
            String msg = FTitulosHistoricos.anularTitulo(objTituloHist);
            Util.addSuccessMessage(msg);
            inicializar();
        } catch (Exception e) {
            System.out.println("public void anularTitulo() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método para la gestión de fichas de adjuntos">   
    public void cargarRespaldo(FileUploadEvent e) {
        System.out.println("Entra al método cargar documento");
        UploadedFile file = e.getFile();
        this.setArchivoDocumento(file);
        System.out.println(file.getContentType());
        //objDocumento.setTipo(file.getContentType());
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
            // Logger.getLogger(ImagenControlador.class.getName()).log(Level.SEVERE, null, ex);
            Util.addErrorMessage(ex.getMessage());
        }
    }

    public boolean guardarArchivo(String nombre, byte[] contenido) {
        String rutaFicha = getConfiguracion().getString("rutaTitulosHist");
        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaFicha + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //objPredio.setFichaAdjunta(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            String path = Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length());
            String ruta = path.replace("\\", "/");
            getObjTituloHist().setPathRespaldo(ruta);
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
    //</editor-fold>

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
     * @return the numZona
     */
    public int getNumZona() {
        return numZona;
    }

    /**
     * @param numZona the numZona to set
     */
    public void setNumZona(int numZona) {
        this.numZona = numZona;
    }

    /**
     * @return the numSector
     */
    public int getNumSector() {
        return numSector;
    }

    /**
     * @param numSector the numSector to set
     */
    public void setNumSector(int numSector) {
        this.numSector = numSector;
    }

    /**
     * @return the numManzana
     */
    public int getNumManzana() {
        return numManzana;
    }

    /**
     * @param numManzana the numManzana to set
     */
    public void setNumManzana(int numManzana) {
        this.numManzana = numManzana;
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
     * @return the objTituloHist
     */
    public TitulosHistoricos getObjTituloHist() {
        return objTituloHist;
    }

    /**
     * @param objTituloHist the objTituloHist to set
     */
    public void setObjTituloHist(TitulosHistoricos objTituloHist) {
        this.objTituloHist = objTituloHist;
    }

    /**
     * @return the tituloHistSel
     */
    public TitulosHistoricos getTituloHistSel() {
        return tituloHistSel;
    }

    /**
     * @param tituloHistSel the tituloHistSel to set
     */
    public void setTituloHistSel(TitulosHistoricos tituloHistSel) {
        this.tituloHistSel = tituloHistSel;
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

}
