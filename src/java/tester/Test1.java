package tester;

import catastro.coeficientes.funciones.FCoefEstado;

public class Test1 {

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Total de estados " + FCoefEstado.coeficientesEstado().size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
