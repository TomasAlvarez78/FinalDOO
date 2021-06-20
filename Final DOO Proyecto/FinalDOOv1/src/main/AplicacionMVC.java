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
import vista.PrototipoHome1;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class AplicacionMVC {
    public static void main(String[] args) {
        
//        ComponenteNegocio objetito = new ComponenteNegocio();
//        
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 10);
//        cal.set(Calendar.MONTH, 1);
//        cal.set(Calendar.YEAR, 2021);
        
//        boolean error = objetito.agregarEmpleado(42568342, "Federico", "Maywheather", "M", cal.getTime(), 3, "Tarde", 1);
//        if (error == true){
//            System.out.println("lo pude insertar ");
//        }else{
//            System.out.println("no pude");
//        }
//        objetito.buscarEmpleado(42568342);
        
//         boolean error = objetito.agregarCliente(42564512, "Diego", "Quiroga", "M", cal.getTime(), "Mercedes Benz", 1);
//        if (error == true){
//            System.out.println("lo pude insertar");
//        }else{
//            System.out.println("no pude che");
//        }
//        objetito.buscarCliente(42564512);
        //modelo:
        Modelo modelo = new Modelo();
        
        //vista:
        InterfazVista vista = new PrototipoHome1();
        
        //controlador:
        Controlador control = new ControladorImplHome(vista, modelo);
        
        //configuramos la vista para que pueda enviar las acciones del usuario como eventos al controlador
        vista.setControlador(control);
        
        //y arrancamos la interfaz:
        vista.iniciaVista();

    }
}
