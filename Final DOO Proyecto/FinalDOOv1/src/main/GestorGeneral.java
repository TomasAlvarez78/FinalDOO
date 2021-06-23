/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Date;
import java.util.List;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;
import modeloFactoryPersona.Mecanico;
import patronDAO.ClienteDAO;
import patronDAO.EmpleadoDAO;
import patronDAO.FabricaDAO;
import patronDAO.TurnoDAO;


public class GestorGeneral {
    
    private final FabricaDAO fabricaDao = FabricaDAO.getFactory("SqlFabricaDAO");

    private final EmpleadoDAO empleadoDao = fabricaDao.getEmpleadoDao();
    
    private final ClienteDAO clienteDao = fabricaDao.getClienteDao();
    
    private final TurnoDAO turnoDao = fabricaDao.getTurnoDao();
    
    public boolean agregarEmpleado(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,int especialidad,String turno,int tipo){
        return empleadoDao.agregarEmpleado(dni, nombre, apellido, sexo, fechaNacimiento, especialidad, turno, tipo);
    }
    
    public Empleado buscarEmpleado(int dni){
        Empleado empleado = empleadoDao.buscarEmpleado(dni);
        if (empleado != null) {
            return empleado;
        }
        return null;
    }
    
    public List<Empleado> buscarEmpleadosMecanicos(){
        List<Empleado> lista = empleadoDao.listarEmpleadosMecanicos();
        if(lista != null){
            return lista;
        }
        System.err.println("No hay mecanicos");
        return null;
        
    }
    
    public boolean agregarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguro){
        return clienteDao.agregarCliente(dni, nombre, apellido, sexo, fechaNacimiento, auto, companiaSeguro);
    }
    
    public Cliente buscarCliente(int dni){
        Cliente cliente = clienteDao.buscarCliente(dni);
        if (cliente != null) {
            return cliente;
        }
        return null;
    }
    
    public Cliente buscarClientePorTicket(int ticketId){
        Cliente cliente = clienteDao.buscarClientePorTicket(ticketId);
        if (cliente != null) {
            return cliente;
        }
        return null;
    }
    
    public boolean verificarExistenciaTurno(Date fecha, Empleado mecanico){
        boolean estado = turnoDao.verificarExistenciaTurno(fecha, mecanico);
        if(estado){
            return true;
        }
        return false;
    }
    
    public boolean agendarTurno(Date fecha,int mecanicoId,Cliente cliente){
        boolean estado = turnoDao.asignarTurno(fecha, mecanicoId, cliente, 3, 0);
        if(estado){
            return true;
        }
        return false;
    }
    
    public boolean cambiarEstado(int ticketId,int estadoId){
        boolean estado = turnoDao.cambiarEstado(ticketId,estadoId);
        if(estado){
            return true;
        }
        return false;
    }
    
}
