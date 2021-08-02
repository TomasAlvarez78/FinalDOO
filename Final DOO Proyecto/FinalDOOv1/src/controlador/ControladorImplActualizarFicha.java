/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.FichaMecanica;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorImplActualizarFicha extends Controlador {
    
    FichaMecanica fichaMecanica;
    
    public ControladorImplActualizarFicha(InterfazVista vista, Modelo modelo) {
        vistaFichaMecanica = vista;
        MODELO = modelo;
        fichaMecanica = new FichaMecanica();
    }

    @Override
    public void setCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            System.out.println("Alguien toco un boton");
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case BUSCARFICHA:
                    String ficha = vistaFichaMecanica.getTicket();
                    int fichaId;
                    int turnoId;
                    GestorGeneral objeto = new GestorGeneral();
                    try{
                        fichaId = Integer.parseInt(ficha);
                    }catch(NumberFormatException ex){
                        vistaFichaMecanica.imprimeResultado("Error, ingreso un valor no permitido");
                        break;
                    }
                    if(fichaId > 0){
                        turnoId = objeto.buscarTurno(fichaId);
                    }else{
                        vistaFichaMecanica.imprimeResultado("Error, ingreso un valor no permitido");
                        break;
                    }
                    int clienteId = objeto.buscarClienteTurno(turnoId);
                    int mecanicoId = objeto.buscarMecanicoTurno(turnoId);
                    Empleado empleado = objeto.buscarEmpleadoId(mecanicoId);
                    Cliente cliente = objeto.buscarClienteId(clienteId);
                    fichaMecanica = objeto.buscarFichaId(fichaId);
                    if (cliente != null && empleado != null){
                        fichaMecanica.setCliente(cliente);
                        fichaMecanica.setMecanico(empleado);
                        vistaFichaMecanica.updateDatos(fichaMecanica);
                    }else{
                        vistaFichaMecanica.imprimeResultado("Error, no se pudo encontrar la ficha designada");
                        break;
                    }
                    break;
                case GUARDAR:
                    //int estado = vistaFichaMecanica.
                    
                    break;                            
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            vistaFichaMecanica.imprimeError(ex);
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
