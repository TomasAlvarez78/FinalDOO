/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorImplInformeMensual extends Controlador{
    private GestorGeneral objeto;
    private List<String> seguros;
    
    public ControladorImplInformeMensual(InterfazVista vista, Modelo modelo) {
        vistaInformeMensual= vista;
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
                case CARGAR:
                    System.out.println("1");
                    seguros = objeto.listarSeguros();
                    System.out.println("2");
                    vistaInformeMensual.cargarLista(seguros);                    
                    break;
                case GENERARMENSUAL:
                    if(vistaInformeMensual.getEstado() != 0){
                        int seguro = vistaInformeMensual.getEspecialidadId();
                        seguro++;
                        String fecha = vistaInformeMensual.getDate();
                        List <String> infMensual = objeto.infMensual(seguro, fecha);
                        if(infMensual != null){
                            infMensual = MODELO.generarTexto(infMensual);
                            for(String linea : infMensual){
                                System.out.println(linea);
                            }
                            if(infMensual.isEmpty()){
                                vistaInformeMensual.imprimeResultado("No se encontro trabajos completos en ese mes");
                                break;
                            }
                            vistaInformeMensual.cargarSeguros(infMensual);
                        }else{
                            vistaInformeMensual.imprimeResultado("No se encontro trabajos completos en ese mes");
                        }
                    }else{
                        vistaInformeMensual.imprimeResultado("Ingreso un dato incorrectamente");
                    }
                    break;
            }
        } catch (Exception ex) {
            vistaInformeMensual.imprimeError(ex);
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
