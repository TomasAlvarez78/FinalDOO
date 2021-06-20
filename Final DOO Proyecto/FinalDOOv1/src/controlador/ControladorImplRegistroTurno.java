/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import vista.InterfazVista;
import vista.VistaRegistroTurnoBD;

/**
 *
 * @author cirkuit
 */
public class ControladorImplRegistroTurno extends Controlador {
    
    private Cliente cliente;
    
    public ControladorImplRegistroTurno(InterfazVista vista, Modelo modelo) {
        vistaTurno = vista;
        MODELO = modelo;
    }
 

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Se apreto un boton");
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case BUSCARUSUARIO:
                    int dni = vistaTurno.getDNI();
                    
                    GestorGeneral objeto = new GestorGeneral();
                    cliente = objeto.buscarCliente(dni);
                    vistaTurno.updateDatos(cliente);
                    
                    break;
                case SIGUIENTE:
                    System.out.println("Se apreto un boton2");
                    vistaRegTurno = new VistaRegistroTurnoBD();
                    Controlador controladorImplRegistrarBD = new ControladorImplRegistrarBD(vistaRegTurno,this.MODELO);
                    controladorImplRegistrarBD.setCliente(cliente);
                    vistaRegTurno.setControlador(controladorImplRegistrarBD);
                    vistaRegTurno.iniciaVista();
            }
        } catch (Exception ex) {
            vistaTurno.imprimeError(ex);
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

    @Override
    public void setCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
