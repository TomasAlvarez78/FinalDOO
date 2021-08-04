/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeloFactoryPersona.Empleado;
import modeloFactoryPersona.Oficinista;
import modeloFactoryPersona.Mecanico;
import modeloFactoryPersona.PersonaFactory;

/**
 *
 * @author cirkuit
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {
    
    private ConexionSql conexion = null;
    PersonaFactory factory;
    
    public EmpleadoDAOImpl() {
        conexion = ConexionSql.getInstancia();
        factory = new PersonaFactory();
    }

    @Override
    public Empleado buscarEmpleado(int dni) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Empleado empleado = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select dni, nombre, apellido,sexo, fechaNacimiento, tipoEmpleado, especialidadId,turno "
                    + "from empleadoTable where dni = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dni);
            
            rs = sentencia.executeQuery();
            
            int dniEmp;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            int tipoEmpleado;
            int especialidad;
            String turno;
            
            while (rs.next()) {
                dniEmp = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento"));
                sexo = rs.getString("sexo");
                tipoEmpleado = rs.getInt("tipoEmpleado");
                especialidad = rs.getInt("especialidadId");
                turno = rs.getString("turno");
                if(tipoEmpleado == 0){
                    //Es oficinista
                    //empleado = new Oficinista(dniEmp,nombre, apellido, fechaNacimiento, sexo);
                    empleado = (Empleado)factory.crearPersona(dniEmp,nombre,apellido,fechaNacimiento,sexo,0,"","",0,2);
                    //int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno,String auto,int companiaSeguro,int idCreacion
                }else{
                    //Es mecanico
                    //empleado = new Mecanico(dniEmp,nombre, apellido, fechaNacimiento, sexo,especialidad,turno);
                    empleado = (Empleado)factory.crearPersona(dniEmp,nombre,apellido,fechaNacimiento,sexo,especialidad,turno,"",0,3);
                }
                
            }
            
        }catch (SQLException | ParseException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return empleado;
    }
    
    
    @Override
    public Empleado buscarEmpleadoId(int id) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Empleado empleado = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select dni, nombre, apellido,sexo, fechaNacimiento, tipoEmpleado, especialidadId,turno from empleadoTable where id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            
            rs = sentencia.executeQuery();
            
            int dniEmp;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            int tipoEmpleado;
            int especialidad;
            String turno;
            
            while (rs.next()) {
                dniEmp = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento"));
                sexo = rs.getString("sexo");
                tipoEmpleado = rs.getInt("tipoEmpleado");
                especialidad = rs.getInt("especialidadId");
                turno = rs.getString("turno");
                if(tipoEmpleado == 0){
                    //Es oficinista
                    empleado = new Oficinista(dniEmp,nombre, apellido, fechaNacimiento, sexo);
                }else{
                    //Es mecanico
                    empleado = new Mecanico(dniEmp,nombre, apellido, fechaNacimiento, sexo,especialidad,turno);
                }
                
            }
            
        }catch (SQLException | ParseException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return empleado;
    }

    @Override
    public Empleado buscarEmpleado(String nombre, String apellido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> listarEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Empleado> listarEmpleadosMecanicos() {
        Connection con = null;
        PreparedStatement sentencia = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        try{
            con = conexion.getConnection();
            String sql = "SELECT * from empleadoTable where tipoEmpleado = 1";
            sentencia = con.prepareStatement(sql);
            
            ResultSet rs = sentencia.executeQuery();
            
            int dni;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            int especialidad;
            String turno;
            Empleado empleado;
            while (rs.next()){
                dni = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = rs.getString("fechaNacimiento") != null ? new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento")) : null;
                sexo = rs.getString("sexo");
                especialidad = rs.getInt("especialidadId");
                turno = rs.getString("turno");
                
                empleado = new Mecanico(dni,nombre,apellido,fechaNacimiento,sexo,especialidad,turno);
                listaEmpleados.add(empleado);
            }
            
            return listaEmpleados;
            //int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }
    
    
    @Override
    public List<Empleado> listarEmpleadosMecanicos(int especialidadId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        try{
            con = conexion.getConnection();
            String sql = "SELECT * from empleadoTable where tipoEmpleado = 1 and especialidadId = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, especialidadId);
            
            ResultSet rs = sentencia.executeQuery();
            
            int dni;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            int especialidad;
            String turno;
            Empleado empleado;
            while (rs.next()){
                dni = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = rs.getString("fechaNacimiento") != null ? new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento")) : null;
                sexo = rs.getString("sexo");
                especialidad = rs.getInt("especialidadId");
                turno = rs.getString("turno");
                
                empleado = new Mecanico(dni,nombre,apellido,fechaNacimiento,sexo,especialidad,turno);
                listaEmpleados.add(empleado);
            }
            
            return listaEmpleados;
            //int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }

    @Override
    public boolean agregarEmpleado(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,int especialidadId,String turno,int tipo) {
        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            String sql = "insert into empleadoTable (dni,nombre,apellido,sexo,fechaNacimiento,tipoEmpleado,especialidadId,turno) "
                    + "values(?,?,?,?,?,?,?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setString(4, sexo);
            sentencia.setString(5, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(fechaNacimiento));
            sentencia.setInt(6, tipo);
            if(tipo == 0){
                sentencia.setNull(7,Types.INTEGER);
                sentencia.setNull(8,Types.INTEGER);
            }else{
                sentencia.setInt(7, especialidadId);
                sentencia.setString(8, turno);
            }

            int resultado = sentencia.executeUpdate();

            return (resultado > 0);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        
    }
    

    @Override
    public boolean modificarEmpleado(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,int especialidadId,String turno,int tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrarEmpleado(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> listarEspecialidades() {
        Connection con = null;
        PreparedStatement sentencia = null;
        List<String> listaEspecialidad = new ArrayList<>();
        try{
            con = conexion.getConnection();
            String sql = "SELECT * from especialidad";
            sentencia = con.prepareStatement(sql);
            
            ResultSet rs = sentencia.executeQuery();
            
            String especialidad;
            while (rs.next()){
                
                especialidad = rs.getString("nombreEsp");
                listaEspecialidad.add(especialidad);
            }
            
            return listaEspecialidad;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }


    
    
}
