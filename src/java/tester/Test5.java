/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import auditoria.logica.entidades.AMejoras;
import auditoria.logica.servicios.FAMejoras;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            int bnd = 0;
            
            String nombre = "auditoria.logica.entidades.AMejoras";
            Class clase = Class.forName(nombre);

            Field[] fields = clase.getDeclaredFields();
            System.out.println("Total de atributos " + fields.length);

            System.out.println("Impresión de atributos: ");

            AMejoras mejoraActual = FAMejoras.comparacionMejoras(10).get(0);
            AMejoras mejoraAnt = FAMejoras.comparacionMejoras(10).get(1);

            for (Field f : fields) {
                
                //Obtener nombre de Atributo
                String field = f.getName();
                System.out.println("Atributo " + field);
                try {
                    //Obtener nombre de Metodo Getter
                    Method getter = mejoraActual.getClass().getMethod("get"
                            + String.valueOf(field.charAt(0)).toUpperCase()
                            + field.substring(1));

                    //Llamo al Metodo especificado de este Objeto
                    //con un array con los respectivos Parametros
                    //En este caso al ser un Getter no recibe parametros
                    Object value = getter.invoke(mejoraActual, new Object[0]);
                    Object valueAnt = getter.invoke(mejoraAnt, new Object[0]);

                    System.out.println("Valor Actual: "+value);
                    System.out.println("Valor anterior: "+valueAnt);
                    
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
