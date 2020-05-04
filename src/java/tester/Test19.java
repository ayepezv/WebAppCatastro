/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.entidades.TransferenciaDominio;
import catastro.logica.servicios.FTransferenciaDominio;
import catastro.logica.servicios.FTransferencias;

/**
 *
 * @author Geovanny
 */
public class Test19 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            TransferenciaDominio transferencia = new TransferenciaDominio();
            transferencia.getPropietario().setIdPersona(2);
            transferencia.getComprador().setIdPersona(2120);
            transferencia.getPredio().setIdPredio(3636);
            transferencia.setTipoTocumento("nn");
            transferencia.setNumeroDocumento("nn");
            transferencia.setFechaDocumento(sqlDate);
            transferencia.setObservaciones("nn");
            transferencia.setExcencionesEsp("nnn");
            transferencia.setRazonExcencion("nnn");
            transferencia.setFormaAdquisicion("nnn");
            transferencia.setNotaria("nnn");
            transferencia.setFechaEscritura(sqlDate);
            transferencia.setFechaRegistroTitulo(sqlDate);
            transferencia.setAreaEscritura(20);
            transferencia.setAvaluoProp(20);
            transferencia.setPrestamo("nnn");
            transferencia.setEntidad("nnn");
            transferencia.setObjeto("nnn");
            transferencia.setMontoCredito(20);
            transferencia.setPlazo(20);
            transferencia.setFechaConsec(sqlDate);
            transferencia.getSessionUsuario().setIdPersona(1);

            System.out.println("Area " + transferencia.getAreaEscritura());
            System.out.println("Avaluo " + transferencia.getAvaluoProp());
            System.out.println("Monto " + transferencia.getMontoCredito());

            String msg = FTransferencias.registrar(transferencia);
            System.out.println(msg);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
