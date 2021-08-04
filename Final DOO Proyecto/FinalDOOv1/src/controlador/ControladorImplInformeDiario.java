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
import modeloFactoryPersona.Cliente;
import vista.InterfazVista;
import modelo.Modelo;
import modeloFactoryPersona.Empleado;

/**
 *
 * @author cirkuit
 */
public class ControladorImplInformeDiario extends Controlador{

    private GestorGeneral objeto;
    private List<String> especialidades;
    
    public ControladorImplInformeDiario(InterfazVista vista, Modelo modelo) {
        vistaInformeDiario= vista;
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
                    especialidades = objeto.listarEspecialidades();
                    vistaInformeDiario.cargarLista(especialidades);                    
                    break;
                case GENERARDIARIO:
                    if(vistaInformeDiario.getEstado() != 0){
                        int especialidad = vistaInformeDiario.getEspecialidadId();
                        especialidad++;
                        String fecha = vistaInformeDiario.getDate();
                        List <String> infDiario = objeto.infDiario(especialidad, fecha);
                        if(infDiario.isEmpty()){
                            vistaInformeDiario.imprimeResultado("No se encuentrar registros en esa fecha");
                            break;
                        }
                        vistaInformeDiario.cargarSeguros(infDiario);
                    }else{
                        vistaInformeDiario.imprimeResultado("Ingreso un dato incorrectamente");
                    }
                    break;
            }
        } catch (Exception ex) {
            vistaInformeDiario.imprimeError(ex);
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
