/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.FichaMecanica;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Mecanico;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorImplActualizarFicha extends Controlador {
    
    public ControladorImplActualizarFicha(InterfazVista vista, Modelo modelo) {
        vistaActFicha = vista;
        MODELO = modelo;
    }

    @Override
    public void setCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case BUSCARFICHA:
                    String ficha = vistaActFicha.getTicket();
                    
                    GestorGeneral objeto = new GestorGeneral();
                    int turnoId = objeto.buscarTurno(Integer.parseInt(ficha));
                    int clienteId = objeto.buscarClienteTurno(turnoId);
                    int mecanicoId = objeto.buscarMecanicoTurno(turnoId);
                    Mecanico mecanico = objeto.buscarEmpleadoId(mecanicoId);
                    Cliente cliente = objeto.buscarClienteId(clienteId);
                    vistaTurno.updateDatos(cliente);
                    
                    break;
                case GUARDAR:
                    vistaRegTurno = new VistaRegistroTurno();
                    Controlador controladorImplRegistrarBD = new ControladorImplRegistrarTurnoBD(vistaRegTurno,this.MODELO);
                    controladorImplRegistrarBD.setCliente(cliente);
                    vistaRegTurno.setControlador(controladorImplRegistrarBD);
                    vistaRegTurno.iniciaVista();
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
    
}
