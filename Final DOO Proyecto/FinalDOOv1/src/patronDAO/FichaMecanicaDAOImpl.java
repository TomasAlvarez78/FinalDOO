/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import clases.FichaMecanica;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.sql.Types;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cirkuit
 */
public class FichaMecanicaDAOImpl implements FichaMecanicaDAO{

    private ConexionSql conexion = null;
    
    public FichaMecanicaDAOImpl() {
        conexion = ConexionSql.getInstancia();
    }
    
    @Override
    public int buscarTurno(int fichaId) {
        
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        int turnoId = 0;
        
        try{
            con = conexion.getConnection();
            String sql = "select * from fichaMecanicaTable where id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, fichaId);
            
            rs = sentencia.executeQuery();
            
            while (rs.next()) {
                turnoId = rs.getInt("idTurno");
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
        return turnoId;
        
    }

    @Override
    public List<FichaMecanica> listarFichasMensual(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarFicha(int idTurno) {
        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            String sql = "insert into fichaMecanicaTable (descripcion,fechaSalida,tiempoEmpleado,conformidad,idTurno) "
                    + "values(?,?,?,?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setNull(1, Types.VARCHAR);
            sentencia.setNull(2, Types.DATE);
            sentencia.setNull(3, Types.INTEGER);
            sentencia.setNull(4, Types.INTEGER);
            sentencia.setInt(5, idTurno);

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
    public boolean borrarFicha(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FichaMecanica getFichaId(int fichaId) {
        
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        FichaMecanica fichaMecanica = null;
        
        try{
            con = conexion.getConnection();
            String sql = "select descripcion, fechaSalida, tiempoEmpleado,gastos, conformidad from fichaMecanicaTable where id = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, fichaId);
            
            rs = sentencia.executeQuery();
            
            String descripcion;
            Date fechaSalida=null;
            int tiempoEmpleado;
            String gastos;
            int conformidad;
           
            
            while (rs.next()) {
                descripcion = rs.getString("descripcion");
                if(rs.getString("fechaSalida") != null){
                    fechaSalida = new SimpleDateFormat("MM dd,yyyy").parse(rs.getString("fechaSalida"));
                }
                tiempoEmpleado = rs.getInt("tiempoEmpleado");
                gastos = rs.getString("gastos");
                conformidad = rs.getInt("conformidad");
                if(gastos == null){
                    gastos = "";
                }
                fichaMecanica = new FichaMecanica(descripcion,fechaSalida, tiempoEmpleado, gastos, conformidad);
            }
            
        }catch (SQLException e) {
            System.err.println(e);
        } catch (ParseException ex) {
            Logger.getLogger(FichaMecanicaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return fichaMecanica;
    }
    
}
    