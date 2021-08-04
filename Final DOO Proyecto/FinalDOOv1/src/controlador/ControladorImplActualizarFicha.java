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
import modeloFactoryPersona.Empleado;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorImplActualizarFicha extends Controlador {
    
    FichaMecanica fichaMecanica;
    GestorGeneral objeto;
    int fichaId;
    int turnoId;
    
    public ControladorImplActualizarFicha(InterfazVista vista, Modelo modelo) {
        vistaFichaMecanica = vista;
        MODELO = modelo;
        fichaMecanica = new FichaMecanica();
        objeto = new GestorGeneral();
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
                    int estado = vistaFichaMecanica.getEstado();
                    switch(estado){
                        case 1:
                            String temp = vistaFichaMecanica.getString();
                            System.out.println(temp);
                            String [] datosFicha = temp.split(";");
                            fichaMecanica.setDescripcion(datosFicha[0]);
                            fichaMecanica.setTiempoEmpleado(Integer.parseInt(datosFicha[1]));
                            if(!"null".equals(datosFicha[2])){
                                fichaMecanica.setFechayHora(datosFicha[2]);
                                fichaMecanica.setConformidad(Integer.parseInt(datosFicha[3]));
                            }else{
                                fichaMecanica.setConformidad(2);
                            }
                            int longi = datosFicha.length;
                            String temp2 = "";
                            for (int i = 4; i < longi; i++) {
                                if(i != longi-1){
                                    temp2 += datosFicha[i] + ";";
                                }else{
                                    temp2 += datosFicha[i];
                                }
                            }
                            fichaMecanica.setGastos(temp2);
                            boolean estado2 = objeto.actFicha(fichaMecanica,fichaId);
                            if(estado2 == true){
                                if(fichaMecanica.getFechayHora() != null){
                                    objeto.cambiarEstado(turnoId, 7);
                                }
                                vistaFichaMecanica.imprimeResultado("Guardado correctamente");
                            }
                            break;
                        case 2:
                            vistaFichaMecanica.imprimeResultado("La descripcion no es suficientemente detallada.");
                            break;
                        case 3:
                            vistaFichaMecanica.imprimeResultado("No cargo el tiempo empleado");
                            break;
                        case 4:
                            vistaFichaMecanica.imprimeResultado("No cargo la fecha de salida");
                            break;
                        case 5:
                            vistaFichaMecanica.imprimeResultado("No selecciono la conformidad");
                            break;
                        case 6:
                            vistaFichaMecanica.imprimeResultado("La tabla se lleno incorrectamente");
                            break;
                    }
                    System.out.println("Estado: " + estado);
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
