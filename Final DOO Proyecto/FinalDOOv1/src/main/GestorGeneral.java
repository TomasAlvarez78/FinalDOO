/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Date;
import java.util.List;
import clases.FichaMecanica;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;
import patronDAO.ClienteDAO;
import patronDAO.EmpleadoDAO;
import patronDAO.FabricaDAO;
import patronDAO.FichaMecanicaDAO;
import patronDAO.SegurosDAO;
import patronDAO.TurnoDAO;


public class GestorGeneral {
    
    private final FabricaDAO fabricaDao = FabricaDAO.getFactory("SqlFabricaDAO");

    private final EmpleadoDAO empleadoDao = fabricaDao.getEmpleadoDao();
    
    private final ClienteDAO clienteDao = fabricaDao.getClienteDao();
    
    private final TurnoDAO turnoDao = fabricaDao.getTurnoDao();
    
    private final SegurosDAO seguroDao = fabricaDao.getSeguroDao();
    
    private final FichaMecanicaDAO fichaMecanicaDao = fabricaDao.getFichaMecanicaDao();
    
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
    
    public List<Empleado> buscarEmpleadosMecanicos(int especialidad){
        List<Empleado> lista = empleadoDao.listarEmpleadosMecanicos(especialidad);
        if(lista != null){
            return lista;
        }
        System.err.println("No hay mecanicos");
        return null;
        
    }
    
    public List<String> listarSeguros(){
        List<String> lista = seguroDao.getSeguros();
        if(lista != null){
            return lista;
        }
        System.err.println("No hay seguros");
        return null;
        
    }
    
    public List<String> listarEspecialidades(){
        List<String> lista = empleadoDao.listarEspecialidades();
        if(lista != null){
            return lista;
        }
        System.err.println("No hay especialidades");
        return null;
        
    }
    
    public boolean agregarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguro){
        return clienteDao.agregarCliente(dni, nombre, apellido, sexo, fechaNacimiento, auto, companiaSeguro);
    }
    
    public boolean agregarCliente(Cliente cliente){
        return clienteDao.agregarCliente(cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.getSexo(), cliente.getFechaNacimiento(), cliente.getAuto(), cliente.getCompaniaSeguro());
    }
    
    public boolean verificarCliente(int dni){
        Cliente cliente = clienteDao.buscarCliente(dni);
        if (cliente != null) {
            return true;
        }
        return false;
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
    
    public boolean asignarTurno(Date fecha,int mecanicoId,Cliente cliente){
        boolean estado = turnoDao.asignarTurno(fecha, mecanicoId, cliente, 3, 0);
        return estado;
    }
    
    public boolean cambiarEstado(int ticketId,int estadoId){
        boolean estado = turnoDao.cambiarEstado(ticketId,estadoId);
        return estado;
    }
    
    public Empleado buscarEmpleadoId(int empleadoId){
        Empleado empleado = empleadoDao.buscarEmpleadoId(empleadoId);
        if(empleado != null){
            return empleado;
        }
        return null;
    }
    
    public Cliente buscarClienteId(int clienteId){
        Cliente cliente = clienteDao.buscarClienteId(clienteId);
        if(cliente != null){
            return cliente;
        }
        return null;
    }
    public int buscarClienteTurno(int turnoId){
        int estado = turnoDao.getCliente(turnoId);
        return estado;
    }
    
    public int buscarMecanicoTurno(int turnoId){
        int estado = turnoDao.getMecanico(turnoId);
        if(estado != 0){
            return estado;
        }
        return estado;
    }
    
    public boolean generarFicha(int idTurno){
        boolean estado = fichaMecanicaDao.agregarFicha(idTurno);
        if(estado){
            return true;
        }
        return false;
    }
    
    public int buscarTurno(int fichaId){
        int turnoId = fichaMecanicaDao.buscarTurno(fichaId);
        if (turnoId != 0) {
            return turnoId;
        }
        return 0;
    }
    
    public FichaMecanica buscarFichaId(int fichaId){
        return fichaMecanicaDao.getFichaId(fichaId);
    }
    
    public boolean actFicha(FichaMecanica ficha, int fichaId){
        return fichaMecanicaDao.actFicha(ficha, fichaId);
    }
    
    public List <String> infDiario(int especialidadId, String fecha){
        return fichaMecanicaDao.infDiario(especialidadId, fecha);
    }
    
    public List <String> infMensual(int seguroId, String fecha){
        return fichaMecanicaDao.infMensual(seguroId, fecha);
    }
    
}
