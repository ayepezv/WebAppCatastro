
package catastro.logica.entidades;

import java.util.Date;


public class PredioExcencionTer {
    
    
    private int id_titular;
    private int num_predios;
    private double suma_valor;
    private double suma_construc;
    private double suma_suelo;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String chv_ruc;
    private int sr_id_excencion;
    private String chv_excencion;
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
     * @return the suma_construc
     */
    public double getSuma_construc() {
        return suma_construc;
    }

    /**
     * @param suma_construc the suma_construc to set
     */
    public void setSuma_construc(double suma_construc) {
        this.suma_construc = suma_construc;
    }

    /**
     * @return the suma_suelo
     */
    public double getSuma_suelo() {
        return suma_suelo;
    }

    /**
     * @param suma_suelo the suma_suelo to set
     */
    public void setSuma_suelo(double suma_suelo) {
        this.suma_suelo = suma_suelo;
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
     * @return the chv_ruc
     */
    public String getChv_ruc() {
        return chv_ruc;
    }

    /**
     * @param chv_ruc the chv_ruc to set
     */
    public void setChv_ruc(String chv_ruc) {
        this.chv_ruc = chv_ruc;
    }

    /**
     * @return the sr_id_excencion
     */
    public int getSr_id_excencion() {
        return sr_id_excencion;
    }

    /**
     * @param sr_id_excencion the sr_id_excencion to set
     */
    public void setSr_id_excencion(int sr_id_excencion) {
        this.sr_id_excencion = sr_id_excencion;
    }

    /**
     * @return the chv_excencion
     */
    public String getChv_excencion() {
        return chv_excencion;
    }

    /**
     * @param chv_excencion the chv_excencion to set
     */
    public void setChv_excencion(String chv_excencion) {
        this.chv_excencion = chv_excencion;
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

}
