
package catastro.logica.entidades;

import java.util.Date;


public class PredioExcencionTerACumplir {

      
    
    private int id_titular;
    private String nombres;
    private String apellidos;
    private String ruc;
    private String excencion;
    private int edad;
    private String EdadCompleta;
    private Date nacimiento;
    private double db_porcentaje;
    private int id_tipo_persona;
    private int id_predio;
    private String clave_catastral;
    private String direccion;
    private double avaluo;
    private String celular;
    private double porcentaje_excencion;
    private String observacion;

    /**
     * @return the id_titular
     */
    public int getId_titular() {
        return id_titular;
    }

    /**
     * @param id_titular the id_titular to set
     */
    public void setId_titular(int id_titular) {
        this.id_titular = id_titular;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the nacimiento
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * @return the db_porcentaje
     */
    public double getDb_porcentaje() {
        return db_porcentaje;
    }

    /**
     * @param db_porcentaje the db_porcentaje to set
     */
    public void setDb_porcentaje(double db_porcentaje) {
        this.db_porcentaje = db_porcentaje;
    }

    /**
     * @return the id_tipo_persona
     */
    public int getId_tipo_persona() {
        return id_tipo_persona;
    }

    /**
     * @param id_tipo_persona the id_tipo_persona to set
     */
    public void setId_tipo_persona(int id_tipo_persona) {
        this.id_tipo_persona = id_tipo_persona;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the excencion
     */
    public String getExcencion() {
        return excencion;
    }

    /**
     * @param excencion the excencion to set
     */
    public void setExcencion(String excencion) {
        this.excencion = excencion;
    }

    /**
     * @return the EdadCompleta
     */
    public String getEdadCompleta() {
        return EdadCompleta;
    }

    /**
     * @param EdadCompleta the EdadCompleta to set
     */
    public void setEdadCompleta(String EdadCompleta) {
        this.EdadCompleta = EdadCompleta;
    }

    /**
     * @return the id_predio
     */
    public int getId_predio() {
        return id_predio;
    }

    /**
     * @param id_predio the id_predio to set
     */
    public void setId_predio(int id_predio) {
        this.id_predio = id_predio;
    }
    
    /**
     * @return the clave_catastral
     */
    public String getClave_catastral() {
        return clave_catastral;
    }

    /**
     * @param clave_catastral the clave_catastral to set
     */
    public void setClave_catastral(String clave_catastral) {
        this.clave_catastral = clave_catastral;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the avaluo
     */
    public double getAvaluo() {
        return avaluo;
    }

    /**
     * @param avaluo the avaluo to set
     */
    public void setAvaluo(double avaluo) {
        this.avaluo = avaluo;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }


    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the porcentaje_excencion
     */
    public double getPorcentaje_excencion() {
        return porcentaje_excencion;
    }

    /**
     * @param porcentaje_excencion the porcentaje_excencion to set
     */
    public void setPorcentaje_excencion(double porcentaje_excencion) {
        this.porcentaje_excencion = porcentaje_excencion;
    }
}
