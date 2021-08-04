/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import vista.InterfazVista;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;

/**
 *
 * @author cirkuit
 */
public abstract class Controlador implements ActionListener, KeyListener{
    InterfazVista vistaHome = null;
    InterfazVista vistaTurno = null;
    InterfazVista vistaRegTurno = null;
    InterfazVista vistaRegEntrega = null;
    InterfazVista vistaRegCliente = null;
    InterfazVista vistaFichaMecanica = null;
    InterfazVista vistaInformeDiario = null;
    InterfazVista vistaInformeMensual = null;
    Modelo MODELO = null;
    
    public abstract void setCliente(Cliente cliente);
}
