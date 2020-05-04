package catastro.gis.entidades;

import catastro.logica.entidades.Parroquia;
import java.sql.Timestamp;

public class PredioGis {

    private int gid;
    private Parroquia parroquia;
    private double zona;
    private double sector;
    private double manzana;
    private int numPredio;
    private String tipoLote;
    private double propiedad;
    private double derechosAccion;
    private String claveCatastral;
    private String propietario;
    private double area;
    private double perimetro;
    private double centroide;
    private double centroid00;
    private double centroid01;
    private double centroid02;
    private String fotografia;
    private String html;
    private String vinculado;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private double x;
    private double y;
    private int bloque;
    private int piso;
    private int unidad;

    public PredioGis() {
        parroquia = new Parroquia();
    }

    /**
     * @return the gid
     */
    public int getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * @return the parroquia
     */
    public Parroquia getParroquia() {
        return parroquia;
    }

    /**
     * @param parroquia the parroquia to set
     */
    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }

    /**
     * @return the zona
     */
    public double getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(double zona) {
        this.zona = zona;
    }

    /**
     * @return the sector
     */
    public double getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(double sector) {
        this.sector = sector;
    }

    /**
     * @return the manzana
     */
    public double getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(double manzana) {
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
     * @return the tipoLote
     */
    public String getTipoLote() {
        return tipoLote;
    }

    /**
     * @param tipoLote the tipoLote to set
     */
    public void setTipoLote(String tipoLote) {
        this.tipoLote = tipoLote;
    }

    /**
     * @return the propiedad
     */
    public double getPropiedad() {
        return propiedad;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(double propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * @return the derechosAccion
     */
    public double getDerechosAccion() {
        return derechosAccion;
    }

    /**
     * @param derechosAccion the derechosAccion to set
     */
    public void setDerechosAccion(double derechosAccion) {
        this.derechosAccion = derechosAccion;
    }

    /**
     * @return the claveCatastral
     */
    public String getClaveCatastral() {
        return claveCatastral;
    }

    /**
     * @param claveCatastral the claveCatastral to set
     */
    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    /**
     * @return the propietario
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the perimetro
     */
    public double getPerimetro() {
        return perimetro;
    }

    /**
     * @param perimetro the perimetro to set
     */
    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    /**
     * @return the centroide
     */
    public double getCentroide() {
        return centroide;
    }

    /**
     * @param centroide the centroide to set
     */
    public void setCentroide(double centroide) {
        this.centroide = centroide;
    }

    /**
     * @return the centroid00
     */
    public double getCentroid00() {
        return centroid00;
    }

    /**
     * @param centroid00 the centroid00 to set
     */
    public void setCentroid00(double centroid00) {
        this.centroid00 = centroid00;
    }

    /**
     * @return the centroid01
     */
    public double getCentroid01() {
        return centroid01;
    }

    /**
     * @param centroid01 the centroid01 to set
     */
    public void setCentroid01(double centroid01) {
        this.centroid01 = centroid01;
    }

    /**
     * @return the centroid02
     */
    public double getCentroid02() {
        return centroid02;
    }

    /**
     * @param centroid02 the centroid02 to set
     */
    public void setCentroid02(double centroid02) {
        this.centroid02 = centroid02;
    }

    /**
     * @return the fotografia
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return the html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * @return the vinculado
     */
    public String getVinculado() {
        return vinculado;
    }

    /**
     * @param vinculado the vinculado to set
     */
    public void setVinculado(String vinculado) {
        this.vinculado = vinculado;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaActualizacion
     */
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the bloque
     */
    public int getBloque() {
        return bloque;
    }

    /**
     * @param bloque the bloque to set
     */
    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the unidad
     */
    public int getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

}
