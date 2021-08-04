/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
