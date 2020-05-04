
package catastro.webservice.funciones;

import accesoDatos.AccesoDatos;
import catastro.webservice.entidades.ReportePrediosClaveCatastral;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FTitulosPagados {
    
    
    //<editor-fold defaultstate="collapsed" desc="Consultar titulos pagados dado predio"> 
    
    public static List<ReportePrediosClaveCatastral> consultarTitulosPagadosWS(Date fechaIni, Date FechaFin) throws Exception {
        ArrayList<ReportePrediosClaveCatastral> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        ReportePrediosClaveCatastral titulo;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_consultar_titulos_pagados_entre_fechasWS(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setDate(1, (java.sql.Date) fechaIni);
            prstm.setDate(2, (java.sql.Date) FechaFin);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                titulo = new ReportePrediosClaveCatastral();
                titulo.setId(resultSet.getInt("Id"));
                titulo.setParroquia(resultSet.getInt("Parroquia"));
                titulo.setZona(resultSet.getInt("Zona"));
                titulo.setSector(resultSet.getInt("Sector"));
                titulo.setManzana(resultSet.getInt("Manzana"));
                titulo.setPredio(resultSet.getInt("Predio"));
                titulo.setPropiedadHorizontal(resultSet.getInt("Propiedad_horizontal"));
                titulo.setPropiedadHorPiso(resultSet.getInt("Propiedad_hor_piso"));
                titulo.setClaveCatastral(resultSet.getString("Clave_catastral"));
                titulo.setPropietario(resultSet.getString("Propietario"));
                titulo.setCedula(resultSet.getString("Cedula"));
                titulo.setDireccion(resultSet.getString("Direccion"));
                titulo.setNumeroTitulo(resultSet.getString("Numero_Titulo"));
                titulo.setFechaTitulo(resultSet.getTimestamp("Fecha_Titulo"));
                titulo.setAnio(resultSet.getInt("Anio"));
                titulo.setValorPredial(resultSet.getDouble("Valor_Predial"));
                titulo.setValorBomberos(resultSet.getDouble("Valor_Bomberos"));
                titulo.setValorDescuento(resultSet.getDouble("Valor_Descuento"));
                titulo.setValorRecargo(resultSet.getDouble("Valor_Recargo"));
                titulo.setValorInteres(resultSet.getDouble("Valor_Interes"));
                titulo.setValorEmision(resultSet.getDouble("Valor_Emision"));
                titulo.setValorTitulo(resultSet.getDouble("Valor_Titulo"));
                titulo.setFechaPago(resultSet.getTimestamp("Fecha_Pago"));
                lst.add(titulo);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
        
    }
    
    //</editor-fold>
    
}
