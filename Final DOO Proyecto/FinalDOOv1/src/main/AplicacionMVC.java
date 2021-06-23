/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.Controlador;
import controlador.ControladorImplHome;
import java.util.Calendar;
import modelo.Modelo;
import vista.VistaHome;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class AplicacionMVC {
    public static void main(String[] args) {
        //modelo:
        Modelo modelo = new Modelo();
        
        //vista:
        InterfazVista vista = new VistaHome();
        
        //controlador:
        Controlador control = new ControladorImplHome(vista, modelo);
        
        //configuramos la vista para que pueda enviar las acciones del usuario como eventos al controlador
        vista.setControlador(control);
        
        //y arrancamos la interfaz:
        vista.iniciaVista();

    }
}
