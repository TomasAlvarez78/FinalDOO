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
import modeloFactoryPersona.Cliente;
import clases.Turno;
import modeloFactoryPersona.Empleado;

public class TurnoDAOImpl implements TurnoDAO{
    
    private ConexionSql conexion = null;
    
    public TurnoDAOImpl() {
        conexion = ConexionSql.getInstancia();
    }

    @Override
    public boolean verificarExistenciaTurno(Date fecha, Empleado mecanico) {
        // Mediante la fecha, hora y mecanico, tenemos que revisar en la BD que no haya
        // turnos en ese momento
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Turno turno = null;
        
        try{
            con = conexion.getConnection();
            
            String sql = "SELECT * FROM turnoTable tt join agendaTable at on tt.agendaId = at.id join empleadoTable et on at.mecanicoId = et.id where et.dni = ? and tt.fecha = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1,mecanico.getDni());
            sentencia.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fecha));
            rs = sentencia.executeQuery();
            if(rs.next() == false){
                return false;
            }else{
                return true;
            }
        }catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return false;
    }

    @Override
    public boolean asignarTurno(Date fecha,int mecanicoDNI, Cliente cliente, int estadoId, int telefonico) {
        Connection con = null;
        PreparedStatement sentencia = null;
        PreparedStatement sentencia2 = null;
        PreparedStatement sentencia3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        
        try{
            con = conexion.getConnection();

            //Hago un query para conseguir el id de la agenda de un mecanico especifico en anio especifico
            String anio = new SimpleDateFormat("yyyy").format(fecha);

            String sql1 = "SELECT at.* FROM agendaTable at join empleadoTable et on at.mecanicoId = et.id where et.dni = ? and at.anio = ?";
            
            sentencia = con.prepareStatement(sql1);
            sentencia.setInt(1, mecanicoDNI);
            sentencia.setString(2,anio);
            
            rs = sentencia.executeQuery();
            int agendaId = rs.getInt("id");
            
            //Hago un query para conseguir le id del cliente segun el DNI
            String sql2 = "SELECT * FROM clienteTable where dni = ?";
            
            sentencia2 = con.prepareStatement(sql2);
            sentencia2.setInt(1, cliente.getDni());
            rs2 = sentencia2.executeQuery();
            int clienteId = rs2.getInt("id");
            

            //Agrego un nuevo turno
            String sql3 = "INSERT INTO turnoTable (fecha,agendaId,clienteId,vehiculo,estadoId,telefonico) VALUES (?,?,?,?,?,?)";
            sentencia3 = con.prepareStatement(sql3);
            sentencia3.setString(1,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fecha));
            sentencia3.setInt(2,agendaId);
            sentencia3.setInt(3,clienteId);
            sentencia3.setString(4,cliente.getAuto());
            sentencia3.setInt(5,estadoId);
            sentencia3.setInt(6,telefonico);
            
            int resultado = sentencia3.executeUpdate();
            
            return (resultado > 0);
            
        }catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return false;
        
    }

    @Override
    public boolean cambiarEstado(int ticketId, int estadoId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        
        try{
            con = conexion.getConnection();
            
            String sql = "UPDATE turnoTable SET estadoId = ? WHERE id = ?";
            
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, estadoId);
            sentencia.setInt(2,ticketId);

            int resultado = sentencia.executeUpdate();
            
            return (resultado > 0);
            
        }catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return false;
    }

    @Override
    public int getCliente(int turnoId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        int clienteId=0;
        
        try{
            con = conexion.getConnection();
            String sql = "select clienteId from turnoTable where id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, turnoId);
            
            rs = sentencia.executeQuery();
            
            while (rs.next()) {
                clienteId = rs.getInt("clienteId");
            }
            
        }catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return clienteId;
    }

    @Override
    public int getMecanico(int turnoId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        int mecanicoId=0;
        
        try{
            con = conexion.getConnection();
            String sql = "select mecanicoId from turnoTable tt join agendaTable at on at.id = tt.agendaId where tt.id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, turnoId);
            
            rs = sentencia.executeQuery();
            
            while (rs.next()) {
                mecanicoId = rs.getInt("mecanicoId");
            }
            
        }catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return mecanicoId;
    }
    
}
