
package catastro.logica.entidades;

import java.util.Date;


public class PredioExcencionTerCumplir {
    
    
    private int id_titular;
    private int num_predios;
    private double suma_valor;
    private double suma_excencion;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String ruc;
    private String excencion;
    private int edad;
    private Date nacimiento;
    private double db_porcentaje;
    private int id_tipo_persona;

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
     * @return the num_predios
     */
    public int getNum_predios() {
        return num_predios;
    }

    /**
     * @param num_predios the num_predios to set
     */
    public void setNum_predios(int num_predios) {
        this.num_predios = num_predios;
    }

    /**
     * @return the suma_valor
     */
    public double getSuma_valor() {
        return suma_valor;
    }

    /**
     * @param suma_valor the suma_valor to set
     */
    public void setSuma_valor(double suma_valor) {
        this.suma_valor = suma_valor;
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
     * @return the suma_excencion
     */
    public double getSuma_excencion() {
        return suma_excencion;
    }

    /**
     * @param suma_excencion the suma_excencion to set
     */
    public void setSuma_excencion(double suma_excencion) {
        this.suma_excencion = suma_excencion;
    }

}
