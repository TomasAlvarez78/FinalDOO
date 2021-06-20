/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import vista.InterfazVista;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import vista.VistaTurno;

/**
 *
 * @author cirkuit
 */
public class ControladorImplHome extends Controlador{

    public ControladorImplHome(InterfazVista vista, Modelo modelo) {
        vistaHome = vista;
        MODELO = modelo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case REGISTRARTURNO:
                    vistaTurno = new VistaTurno();
                    //crear nuevo modelo
                    //crear nuevo controlador
                    Controlador controladorImplRegistroTurno = new ControladorImplRegistroTurno(vistaTurno,this.MODELO);
                    vistaTurno.setControlador(controladorImplRegistroTurno);
                    vistaTurno.iniciaVista();
                    
                    //Para evitar de abrir muchos vistaTurno
                    //vistaHome.setVisible();
                    
                    break;
                case REGISTRARENTREGA:
                    
                    break;
            }
        } catch (Exception ex) {
            vistaHome.imprimeError(ex);
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
