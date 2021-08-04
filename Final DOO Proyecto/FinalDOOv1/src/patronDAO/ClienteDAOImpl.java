/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Persona;
import modeloFactoryPersona.PersonaFactory;
/**
 *
 * @author cirkuit
 */
public class ClienteDAOImpl implements ClienteDAO{
    
    private ConexionSql conexion = null;
    PersonaFactory factory;
    
    public ClienteDAOImpl() {
        conexion = ConexionSql.getInstancia();
        factory = new PersonaFactory();
    }

    @Override
    public Cliente buscarCliente(int dni) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select dni, nombre, apellido,sexo, fechaNacimiento, auto,companiaSeguroCuit "
                    + " from clienteTable where dni = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dni);
            
            rs = sentencia.executeQuery();
            
            int dniCli;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            String auto;
            int companiaSeguro;
            
            while (rs.next()) {
                dniCli = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento"));
                sexo = rs.getString("sexo");
                auto = rs.getString("auto");
                companiaSeguro = rs.getInt("companiaSeguroCuit");
                //cliente = new Cliente(dniCli,nombre, apellido, fechaNacimiento, sexo,auto,companiaSeguro);
                cliente = (Cliente)factory.crearPersona(dniCli,nombre,apellido,fechaNacimiento,sexo,0,"",auto,companiaSeguro,1);
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
        return cliente;
    }

    @Override
    public Cliente buscarClienteId(int clienteId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select dni, nombre, apellido,sexo, fechaNacimiento, auto,companiaSeguroCuit "
                    + " from clienteTable where id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, clienteId);
            
            rs = sentencia.executeQuery();
            
            int dniCli;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            String auto;
            int companiaSeguro;
            
            while (rs.next()) {
                dniCli = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento"));
                sexo = rs.getString("sexo");
                auto = rs.getString("auto");
                companiaSeguro = rs.getInt("companiaSeguroCuit");
                //cliente = new Cliente(dniCli,nombre, apellido, fechaNacimiento, sexo,auto,companiaSeguro);
                cliente = (Cliente)factory.crearPersona(dniCli,nombre,apellido,fechaNacimiento,sexo,0,"",auto,companiaSeguro,1);
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
        return cliente;
    }
    
    @Override
    public Cliente buscarCliente(String nombre, String apellido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguroCuit) {
        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            String sql = "insert into clienteTable (dni,nombre,apellido,sexo,fechaNacimiento,auto,companiaSeguroCuit) "
                    + "values(?,?,?,?,?,?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setString(4, sexo);
            sentencia.setString(5, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(fechaNacimiento));
            sentencia.setString(6, auto);
            sentencia.setInt(7, companiaSeguroCuit);

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
    public boolean modificarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguroCuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrarCliente(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente buscarClientePorTicket(int ticketId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select dni, nombre, apellido,sexo, fechaNacimiento, auto,companiaSeguroCuit from turnoTable tt join clienteTable ct on tt.clienteId = ct.id where tt.id = ?";
           
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, ticketId);
            
            rs = sentencia.executeQuery();
            
            int dniCli;
            String nombre;
            String apellido;
            Date fechaNacimiento;
            String sexo;
            String auto;
            int companiaSeguro;
            
            while (rs.next()) {
                dniCli = rs.getInt("dni");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaNacimiento"));
                sexo = rs.getString("sexo");
                auto = rs.getString("auto");
                companiaSeguro = rs.getInt("companiaSeguroCuit");
                //cliente = new Cliente(dniCli,nombre, apellido, fechaNacimiento, sexo,auto,companiaSeguro);
                cliente = (Cliente)factory.crearPersona(dniCli,nombre,apellido,fechaNacimiento,sexo,0,"",auto,companiaSeguro,1);
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
        return cliente;
    }
    
}
