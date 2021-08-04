/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.Controlador;
import modeloFactoryControlador.ControladorFactory;
import modelo.Modelo;
import vista.VistaHome;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class AplicacionMVC {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        InterfazVista vista = new VistaHome();
        ControladorFactory factory = new ControladorFactory();
        Controlador control = factory.crearControlador(1,vista,modelo);
        vista.setControlador(control);
        vista.iniciaVista();
    }
}
