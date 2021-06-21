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
import vista.VistaEntrega;

/**
 *
 * @author cirkuit
 */
public class ControladorImplRegistrarEntregaBD extends Controlador{
    
    private final GestorGeneral objeto;
    private String ticketId;
    
    public ControladorImplRegistrarEntregaBD (InterfazVista vista, Modelo modelo) {
        vistaRegEntrega = vista;
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
                case BUSCARTURNO:
                    ticketId = vistaRegEntrega.getTicket();
                    Cliente cliente = objeto.buscarClientePorTicket(Integer.parseInt(ticketId));
                    vistaRegEntrega.updateDatos(cliente);
                    break;
                case REGISTRARENTREGA:
                    int respuesta = JOptionPane.showConfirmDialog(((VistaEntrega) this.vistaRegEntrega), "¿Seguro desea registrar la entrega?", "Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.OK_OPTION) {
                        ticketId = vistaRegEntrega.getTicket();
                        boolean estado = objeto.cambiarEstado(Integer.parseInt(ticketId), 6);
                        if(estado){
                            vistaRegEntrega.imprimeResultado("Se registro la entrega del auto correctamente");
                        }else{
                            vistaRegEntrega.imprimeResultado("No se pudo registrar la entrega del auto. Consulte al administrador");
                        }
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
            vistaRegEntrega.imprimeError(ex);
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
