/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Modelo;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorFactory extends ControladorFactoryMethod{

    @Override
    public Controlador crearControlador(int idCreacion,InterfazVista vista,Modelo modelo) {
        switch(idCreacion){
            case 1:
                return new ControladorImplHome(vista,modelo);
            case 2:
                return new ControladorImplBuscarUsuario(vista,modelo);
            case 3:
                return new ControladorImplRegistrarEntregaBD(vista,modelo);
            case 4:
                return new ControladorImplActualizarFicha(vista,modelo);
            case 5:
                return new ControladorImplInformeDiario(vista,modelo);
            case 6:
                return new ControladorImplRegistrarTurnoBD(vista,modelo);
            case 7:
                return new ControladorImplRegistrarCliente(vista,modelo);
        }
        return null;
    }
    
}
