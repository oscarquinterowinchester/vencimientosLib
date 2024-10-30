/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos;

import basic.utils;

/**
 *
 * @author User
 */
public class  Permisos {

    public static boolean esVentanaYardaVisible() {
        String estatusPermiso = utils.dbConsult("SELECT MAX(IFNULL(ventanaYardas,'0')) FROM configuracionVencimientos limit 1");

        boolean esEstatusInactivo = esEstatusInactivo(estatusPermiso);

        if (esEstatusInactivo) {
            return false;
        }

        return estatusPermiso.equals("1");
    }

    public static boolean esVentanaExtintoresVisible() {

        String estatusPermiso = utils.dbConsult("SELECT MAX(IFNULL(ventanaExtintores,'0')) FROM configuracionVencimientos limit 1");

        boolean esEstatusInactivo = esEstatusInactivo(estatusPermiso);

        if (esEstatusInactivo) {
            return false;
        }

        return estatusPermiso.equals("1");
    }
    public static void  prt(String cadena){
        System.out.println(cadena);
    }
    public static boolean estaCorreosDisponible() {
            prt("1");
        String estatusPermiso = utils.dbConsult("SELECT MAX(IFNULL(envioCorreos,'0')) FROM configuracionVencimientos limit 1");
            prt("2");
        boolean esEstatusInactivo = esEstatusInactivo(estatusPermiso);
            prt("3");
        if (esEstatusInactivo) {
            prt("4");
            return false;
        }
            prt("5");
        return estatusPermiso.equals("1");
    }
    
    private static boolean esEstatusInactivo(String estatusPermiso) {
        try {

            boolean esPermisoInactivo = estatusPermiso.equals("0") || estatusPermiso.isEmpty();

            if (esPermisoInactivo) {

                return true;

            }
        } catch (NullPointerException e) {

            return true;

        }
        return false;
    }

}
