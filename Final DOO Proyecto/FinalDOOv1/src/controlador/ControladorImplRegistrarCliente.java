/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import vista.InterfazVista;
import vista.VistaRegistroCliente;

/**
 *
 * @author cirkuit
 */
public class ControladorImplRegistrarCliente extends Controlador{
    
    GestorGeneral objeto;

     public ControladorImplRegistrarCliente (InterfazVista vista, Modelo modelo) {
        vistaRegCliente = vista;
        MODELO = modelo;
        objeto = new GestorGeneral();
    }
    
    @Override
    public void setCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case REGISTRARCLIENTEBD:
                    Cliente cliente = vistaRegCliente.getCliente();
                    boolean existe = objeto.verificarCliente(cliente.getDni());
                    if(!existe){
                        boolean creado = objeto.agregarCliente(cliente);
                        if(creado){
                            vistaRegCliente.imprimeResultado("Se ha aniadido el cliente");
                        }else{
                            vistaRegCliente.imprimeResultado("No se pudo aniadir el cliente");
                        }
                    }else{
                        vistaRegCliente.imprimeResultado("Este cliente ya existe en el sistema");
                    }
                    break;
                case CARGAR:
                    vistaRegCliente.cargarSeguros(objeto.listarSeguros());
                case CANCELAR:
                    vistaRegCliente.cerrarVista();
                    break;
            }
        } catch (NumberFormatException ex) {
            vistaRegCliente.imprimeError(ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
