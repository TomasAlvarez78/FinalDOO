/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
    private List<String> especialidades;
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
                    especialidades = objeto.listarEspecialidades();
                    vistaRegTurno.cargarLista(especialidades);                    
                    break;
                case ACTUALIZARMECANICOS:
                    int especialidad = vistaRegTurno.getEspecialidadId();
                    especialidad++;
                    mecanicos = objeto.buscarEmpleadosMecanicos();
                    vistaRegTurno.cargarMecanicos(mecanicos,especialidad);
                    vistaRegTurno.setEnable(true);
                    break;
                case REGISTRARTURNOBD:
                    boolean existe = true;
                    int mecanicoId = -1;
                    Empleado mecanico=null;
                    if(vistaRegTurno.getEspecialidadId() > -1){
                        try{
                            mecanicoId = vistaRegTurno.getMecanicoId();
                            mecanicos = vistaRegTurno.getEmpleadosLocales();
                            mecanico = objeto.buscarEmpleado(mecanicos.get(mecanicoId).getDni());
                        }catch(Exception ex){
                            vistaRegTurno.imprimeResultado("Ingreso los datos incorrectamente");
                            break;
                        }
                    }
                    if(mecanicoId > -1 && vistaRegTurno.getEspecialidadId() > -1){
                        mecanicoId++;
                    }else{
                        vistaRegTurno.imprimeResultado("Ingreso los datos incorrectamente");
                        break;
                    }
                    try{
                        existe = objeto.verificarExistenciaTurno(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(vistaRegTurno.getDate()),mecanico);
                    }catch(ParseException ex){
                        vistaRegTurno.imprimeResultado("Ingreso los datos incorrectamente");
                        break;
                    }
                    
                    if(!existe){
                        objeto.asignarTurno(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(vistaRegTurno.getDate()),mecanico.getDni(),cliente);
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
