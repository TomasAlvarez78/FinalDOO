/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import java.util.Date;
import java.util.List;
import modeloFactoryPersona.Cliente;

/**
 *
 * @author cirkuit
 */
public interface ClienteDAO {
    Cliente buscarCliente(int dni);
    Cliente buscarClienteId(int clienteId);
    Cliente buscarClientePorTicket(int ticketId);
    Cliente buscarCliente(String nombre,String apellido);
    
    List<Cliente> listarClientes();
    
    boolean agregarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguroCuit);
    
    boolean modificarCliente(int dni,String nombre,String apellido, String sexo, Date fechaNacimiento,String auto,int companiaSeguroCuit);
    
    boolean borrarCliente(int dni);
    
    void cerrarConexion();
    
}
