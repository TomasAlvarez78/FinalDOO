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
import vista.VistaRegistroTurno;

/**
 *
 * @author cirkuit
 */
public class ControladorImplBuscarUsuario extends Controlador {
    
    private Cliente cliente;
    
    public ControladorImplBuscarUsuario(InterfazVista vista, Modelo modelo) {
        vistaTurno = vista;
        MODELO = modelo;
    }
 

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case BUSCARUSUARIO:
                    int dni = vistaTurno.getDNI();
                    
                    GestorGeneral objeto = new GestorGeneral();
                    cliente = objeto.buscarCliente(dni);
                    vistaTurno.updateDatos(cliente);
                    
                    break;
                case SIGUIENTE:
                    vistaRegTurno = new VistaRegistroTurno();
                    Controlador controladorImplRegistrarBD = new ControladorImplRegistrarTurnoBD(vistaRegTurno,this.MODELO);
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
