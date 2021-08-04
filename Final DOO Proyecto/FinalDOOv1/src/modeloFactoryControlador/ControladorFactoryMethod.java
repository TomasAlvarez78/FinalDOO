/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloFactoryControlador;

import controlador.Controlador;
import controlador.Controlador;
import vista.InterfazVista;
import modelo.Modelo;

/**
 *
 * @author cirkuit
 */
public abstract class ControladorFactoryMethod {
    public abstract Controlador crearControlador(int idCreacion,InterfazVista vista,Modelo modelo);
}
