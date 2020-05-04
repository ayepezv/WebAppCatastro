package auditoria.presentacion.beans;

import auditoria.logica.entidades.AMejoras;
import auditoria.logica.servicios.FAMejoras;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtrlAuditoriaMejoras implements Serializable {
    
    private ArrayList<AMejoras> lstMejoras;
    private AMejoras mejoraSel;
    private String contenidoComparacion;
    private String comparacion;
    
    public CtrlAuditoriaMejoras() {
        mejoraSel = new AMejoras();
        obtenerMejoras();
    }
    
    public void obtenerMejoras() {
        try {
            setLstMejoras(FAMejoras.obtenerAuditoriaMejoras());
        } catch (Exception e) {
            Util.addErrorMessage("Obtener mejoras dice: " + e.getMessage());
        }
    }
    
    public void compararMejoras() {
        try {
            lstMejoras = FAMejoras.comparacionMejoras(mejoraSel.getIdAudMejoras());
            
            AMejoras mejoraAnt, mejoraActual;
            mejoraAnt = lstMejoras.get(0);
            mejoraActual = lstMejoras.get(1);
            
            contenidoComparacion = "";
            
            if (mejoraActual.getNombre().equals(mejoraAnt.getNombre())) {
            } else {
                contenidoComparacion = contenidoComparacion + "<b>Nombre de la Mejora</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + mejoraActual.getNombre() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + mejoraAnt.getNombre() + "<br><hr/>";
            }
            if (mejoraActual.getFechaInicio().equals(mejoraAnt.getFechaInicio())) {
                contenidoComparacion = contenidoComparacion + "<b>Fecha de Inicio</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + mejoraActual.getFechaInicio() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + mejoraAnt.getFechaInicio() + "<br><hr/>";
            }
            
            if (mejoraActual.getAnioAvaluo() != mejoraAnt.getAnioAvaluo()) {
                contenidoComparacion = contenidoComparacion + "<b>Año de Avalúo</b><br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor actual: </b>" + mejoraActual.getAnioAvaluo() + "<br>";
                contenidoComparacion = contenidoComparacion + "<b>Valor anterior: </b>" + mejoraAnt.getAnioAvaluo() + "<br><hr/>";
            }
            
        } catch (Exception e) {
            System.out.println("public void compararMejoras() diece: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void comparacionMejoras() {
        try {
            String nombre = "auditoria.logica.entidades.AMejoras";
            Class clase = Class.forName(nombre);
            Field[] fields = clase.getDeclaredFields();
            System.out.println("Total de atributos " + fields.length);
            System.out.println("Impresión de atributos: ");
            AMejoras mejoraActual = FAMejoras.comparacionMejoras(mejoraSel.getIdAudMejoras()).get(0);
            AMejoras mejoraAnt = FAMejoras.comparacionMejoras(mejoraSel.getIdAudMejoras()).get(1);
            
            comparacion = "<div class='list-group'>";
            
            for (Field f : fields) {
                String field = f.getName();
                System.out.println("Atributo " + field);
                //Obtener nombre de Metodo Getter
                Method getter = mejoraActual.getClass().getMethod("get"
                        + String.valueOf(field.charAt(0)).toUpperCase()
                        + field.substring(1));

                Object value = getter.invoke(mejoraActual, new Object[0]);
                Object valueAnt = getter.invoke(mejoraAnt, new Object[0]);
                
                if (value.equals(valueAnt) || field.equals("idAudMejoras") ||field.equals("idMejora")) {
                    
                } else {
                    
                    comparacion = comparacion +"<div class='card'>";                                        
                    comparacion = comparacion + "<b>" + field + "</b><br/>";                    
                    comparacion = comparacion + "<a class='list-group-item list-group-item-action' href='#' >";
                    comparacion = comparacion + "<i class='fa fa-pencil-square fa-2x fa-fw' aria-hidden='false'></i>";
                    comparacion = comparacion + "&nbsp; Actual: " + value;
                    comparacion = comparacion + "</a><br/>";
                    
                    comparacion = comparacion + "<a class='list-group-item list-group-item-action' href='#'>";
                    comparacion = comparacion + "<i class='fa fa-save fa-2x fa-fw' aria-hidden='true'></i>";
                    comparacion = comparacion + "&nbsp; Anterior: " + valueAnt;
                    comparacion = comparacion + "</a><br/>";                                         
                    comparacion = comparacion + "</div>";
                }                
                
            }
            comparacion = comparacion + "</div>";
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            Util.addErrorMessage("La Inserción no tiene datos previos.");
            comparacion =""+ "<center><b>La Inserción no tiene datos previos</b></center>";
        }
    }            

    /**
     * @return the lstMejoras
     */
    public ArrayList<AMejoras> getLstMejoras() {
        return lstMejoras;
    }

    /**
     * @param lstMejoras the lstMejoras to set
     */
    public void setLstMejoras(ArrayList<AMejoras> lstMejoras) {
        this.lstMejoras = lstMejoras;
    }

    /**
     * @return the mejoraSel
     */
    public AMejoras getMejoraSel() {
        return mejoraSel;
    }

    /**
     * @param mejoraSel the mejoraSel to set
     */
    public void setMejoraSel(AMejoras mejoraSel) {
        this.mejoraSel = mejoraSel;
    }

    /**
     * @return the contenidoComparacion
     */
    public String getContenidoComparacion() {
        return contenidoComparacion;
    }

    /**
     * @param contenidoComparacion the contenidoComparacion to set
     */
    public void setContenidoComparacion(String contenidoComparacion) {
        this.contenidoComparacion = contenidoComparacion;
    }
    
    public String getComparacion() {
        return comparacion;
    }
    
    public void setComparacion(String comparacion) {
        this.comparacion = comparacion;
    }
    
}
