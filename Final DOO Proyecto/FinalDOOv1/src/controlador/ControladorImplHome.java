/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import modeloFactoryControlador.ControladorFactory;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import vista.InterfazVista;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import prototipovistas.VistaFichaMecanica;
import vista.VistaEntrega;
import vista.VistaInformeDiario;
import vista.VistaInformeMensual;
import vista.VistaTurno;

/**
 *
 * @author cirkuit
 */
public class ControladorImplHome extends Controlador{

    ControladorFactory factory;
       
    public ControladorImplHome(InterfazVista vista, Modelo modelo) {
        factory = new ControladorFactory();
        vistaHome = vista;
        MODELO = modelo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case REGISTRARTURNO:
                    vistaTurno = new VistaTurno();
                    Controlador controladorImplBuscarUsuario = factory.crearControlador(2,vistaTurno,this.MODELO);
                    vistaTurno.setControlador(controladorImplBuscarUsuario);
                    vistaTurno.iniciaVista();
                    break;
                case REGISTRARENTREGA:
                    vistaRegEntrega = new VistaEntrega();
                    Controlador controladorImplRegistrarEntrega = factory.crearControlador(3,vistaRegEntrega,this.MODELO);
                    vistaRegEntrega.setControlador(controladorImplRegistrarEntrega);
                    vistaRegEntrega.iniciaVista();
                    break;
                case ACTUALIZARFICHAMECANICA:
                    vistaFichaMecanica = new VistaFichaMecanica();
                    Controlador controladorImplActualizarFicha = factory.crearControlador(4,vistaFichaMecanica,this.MODELO);
                    vistaFichaMecanica.setControlador(controladorImplActualizarFicha);
                    vistaFichaMecanica.iniciaVista();
                    break;
                case GENERARDIARIO:
                    vistaInformeDiario = new VistaInformeDiario();
                    Controlador controladorImplInformeDiario = factory.crearControlador(5,vistaInformeDiario,this.MODELO);
                    vistaInformeDiario.setControlador(controladorImplInformeDiario);
                    vistaInformeDiario.iniciaVista();
                    break;
                case GENERARMENSUAL:
                    vistaInformeMensual = new VistaInformeMensual();
                    Controlador controladorImplInformeMensual = factory.crearControlador(8,vistaInformeMensual,this.MODELO);
                    vistaInformeMensual.setControlador(controladorImplInformeMensual);
                    vistaInformeMensual.iniciaVista();
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
