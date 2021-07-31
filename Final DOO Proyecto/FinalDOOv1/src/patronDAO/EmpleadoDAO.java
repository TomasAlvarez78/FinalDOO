/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;
import java.util.Date;
import modeloFactoryPersona.Empleado;
import java.util.List;

/**
 *
 * @author cirkuit
 */
public interface EmpleadoDAO {
    Empleado buscarEmpleado(int dni);
    Empleado buscarEmpleadoId(int id);
    Empleado buscarEmpleado(String nombre,String apellido);
    
    List<Empleado> listarEmpleados();
    List<Empleado> listarEmpleadosMecanicos();
    List<Empleado> listarEmpleadosMecanicos(int especialidadId);
    
    boolean agregarEmpleado(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,int especialidadId,String turno,int tipo);
    
    boolean modificarEmpleado(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,int especialidadId,String turno,int tipo);
    
    boolean borrarEmpleado(int dni);
    
    List<String> listarEspecialidades();
    
    void cerrarConexion();
    
    /*
    empleadoTable
    CREATE TABLE empleadoTable (
    id           INTEGER      PRIMARY KEY,
    dni          INTEGER (15) NOT NULL,
    nombre       VARCHAR (40) NOT NULL,
    apellido     VARCHAR (40) NOT NULL,
    edad         INTEGER (3)  NOT NULL,
    tipoEmpleado INTEGER (2)  NOT NULL,
    especialidad VARCHAR (40),
    turno        VARCHAR (20) 
);
    
    
    */
}
