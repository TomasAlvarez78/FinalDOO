/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modeloFactoryControlador.ControladorFactory;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import vista.InterfazVista;
import vista.VistaRegistroCliente;
import vista.VistaRegistroTurno;

/**
 *
 * @author cirkuit
 */
public class ControladorImplBuscarUsuario extends Controlador {
    
    private Cliente cliente;
    ControladorFactory factory;
    GestorGeneral objeto;
    
    public ControladorImplBuscarUsuario(InterfazVista vista, Modelo modelo) {
        factory = new ControladorFactory();
        vistaTurno = vista;
        MODELO = modelo;
        objeto = new GestorGeneral();
    }
 

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case BUSCARUSUARIO:
                    int dni = vistaTurno.getDNI();
                    cliente = objeto.buscarCliente(dni);
                    vistaTurno.updateDatos(cliente);
                    break;
                case SIGUIENTE:
                    vistaRegTurno = new VistaRegistroTurno();
                    Controlador controladorImplRegistrarBD = factory.crearControlador(6,vistaRegTurno,this.MODELO);
                    controladorImplRegistrarBD.setCliente(cliente);
                    vistaRegTurno.setControlador(controladorImplRegistrarBD);
                    vistaRegTurno.iniciaVista();
                    break;
                case REGISTRARCLIENTE:
                    vistaRegCliente = new VistaRegistroCliente();
                    Controlador controladorImplRegistrarCliente = factory.crearControlador(7,vistaRegCliente,this.MODELO);
                    vistaRegCliente.setControlador(controladorImplRegistrarCliente);
                    vistaRegCliente.iniciaVista();
                    break;
                            
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
