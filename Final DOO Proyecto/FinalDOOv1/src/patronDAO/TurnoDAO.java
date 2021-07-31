/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import java.util.Date;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;

/**
 *
 * @author cirkuit
 */
public interface TurnoDAO {
    boolean verificarExistenciaTurno(Date fecha, Empleado mecanico); 
    boolean asignarTurno(Date fecha,int mecanicoDNI,Cliente cliente, int estadoId,int telefonico);
    boolean cambiarEstado(int ticketId,int estadoId);
    int getCliente(int turnoId);
    int getMecanico(int turnoId);
    
}
