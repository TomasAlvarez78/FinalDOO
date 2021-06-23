/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import main.GestorGeneral;
import modelo.Modelo;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;
import vista.InterfazVista;

/**
 *
 * @author cirkuit
 */
public class ControladorImplRegistrarTurnoBD extends Controlador{
    
    private final GestorGeneral objeto;
    private List<Empleado> mecanicos;
    private Cliente cliente;
    
    public ControladorImplRegistrarTurnoBD(InterfazVista vista, Modelo modelo) {
        vistaRegTurno = vista;
        MODELO = modelo;
        objeto = new GestorGeneral();
    }
       
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case CARGAR:
                    mecanicos = objeto.buscarEmpleadosMecanicos();
                    vistaRegTurno.cargarMecanicos(mecanicos);
                    break;
                case REGISTRARTURNOBD:
                    int mecanicoId = vistaRegTurno.getMecanicoId();
                    Empleado mecanico = mecanicos.get(mecanicoId);
                    mecanicoId++;
                                               
                    boolean existe = objeto.verificarExistenciaTurno(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(vistaRegTurno.getDate()),mecanico);
                    
                    if(!existe){
                        objeto.agendarTurno(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(vistaRegTurno.getDate()),mecanicoId,cliente);
                        vistaRegTurno.imprimeResultado("Se registro el turno correctamente");
                    }else{
                        vistaRegTurno.imprimeResultado("El turno ya existe");
                    }
                    break;

            }
        } catch (Exception ex) {
            vistaRegTurno.imprimeError(ex);
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
