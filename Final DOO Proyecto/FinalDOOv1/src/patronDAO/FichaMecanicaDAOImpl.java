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
import java.sql.Types;
import java.util.ArrayList;

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
            String fechaSalida;
            int tiempoEmpleado;
            String gastos;
            int conformidad;
           
            
            while (rs.next()) {
                descripcion = rs.getString("descripcion");
                fechaSalida = rs.getString("fechaSalida");
                tiempoEmpleado = rs.getInt("tiempoEmpleado");
                gastos = rs.getString("gastos");
                conformidad = rs.getInt("conformidad");
                System.out.println(conformidad);
                if(gastos == null){
                    gastos = "";
                }
                fichaMecanica = new FichaMecanica(descripcion,fechaSalida, tiempoEmpleado, gastos, conformidad);
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
        return fichaMecanica;
    }

    @Override
    public boolean actFicha(FichaMecanica ficha, int fichaId) {
        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            String sql;
            if(ficha.getFechayHora() == null){
                sql = "UPDATE fichaMecanicaTable SET descripcion = ?, tiempoEmpleado = ?, gastos = ?,conformidad = ? WHERE id = ?";
                sentencia = con.prepareStatement(sql);
                sentencia.setString(1, ficha.getDescripcion());
                sentencia.setInt(2, ficha.getTiempoEmpleado());
                sentencia.setString(3, ficha.getGastos());
                sentencia.setInt(4, ficha.isConformidad());
                sentencia.setInt(5, fichaId);
            }else{
                sql = "UPDATE fichaMecanicaTable SET descripcion = ?,fechaSalida = ?,tiempoEmpleado = ?,gastos = ?,conformidad = ? WHERE id = ?";
                sentencia = con.prepareStatement(sql);
                sentencia.setString(1, ficha.getDescripcion());
                sentencia.setString(2, ficha.getFechayHora());
                sentencia.setInt(3, ficha.getTiempoEmpleado());
                sentencia.setString(4,ficha.getGastos());
                sentencia.setInt(5, ficha.isConformidad());
                sentencia.setInt(6, fichaId);
            }
            
            int resultado = sentencia.executeUpdate();

            return (resultado > 0);
        } catch (SQLException e) {
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
    public List <String> infDiario(int especialidadId, String fecha) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List <String>resultado = new ArrayList<>();
        
        try{
            con = conexion.getConnection();
            String sql = "select et.nombre || ' ' || et.apellido as nombreCompleto, fmt.descripcion,fmt.tiempoEmpleado\n" +
                        "from turnoTable tt\n" +
                        "join agendaTable at\n" +
                        "on tt.agendaId = at.id\n" +
                        "join empleadoTable et\n" +
                        "on at.mecanicoId = et.id\n" +
                        "join fichaMecanicaTable fmt\n" +
                        "on tt.id = fmt.idTurno\n" +
                        "and et.especialidadId = ?\n" +
                        "where date(tt.fecha) = ?\n" +
                        "and fmt.descripcion is not null";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, especialidadId);
            sentencia.setString(2, fecha);
            
            rs = sentencia.executeQuery();
            
            String nombre;
            String descripcion;
            int tiempoEmpleado;
                        
            while (rs.next()) {
                nombre = rs.getString("nombreCompleto");
                descripcion = rs.getString("descripcion");
                tiempoEmpleado = rs.getInt("tiempoEmpleado");
                resultado.add("Mecanico: " + nombre + "\nTrabajo: " + descripcion + "\nTiempo empleado: " + tiempoEmpleado + " minutos\n");
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
        return resultado;
    }

    @Override
    public List<String> infMensual(int seguroId, String fecha) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List <String> resultado = new ArrayList<>();
        
        try{
            con = conexion.getConnection();
            String sql = "select ct.nombre || ' ' || ct.apellido as nombreCliente,e.nombreEsp,fmt.gastos\n" +
                            "from turnoTable tt\n" +
                            "join clienteTable ct\n" +
                            "on tt.clienteId = ct.id\n" +
                            "and ct.companiaSeguroCuit = ?\n" +
                            "join agendaTable at\n" +
                            "on tt.agendaId = at.id\n" +
                            "join empleadoTable et\n" +
                            "on at.mecanicoId = et.id\n" +
                            "join especialidad e\n" +
                            "on et.especialidadId = e.id\n" +
                            "join fichaMecanicaTable fmt\n" +
                            "on fmt.idTurno = tt.id\n" +
                            "where strftime('%m',tt.fecha) = strftime('%m',?)\n" +
                            "and strftime('%Y',tt.fecha) = strftime('%Y',?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, seguroId+100);
            sentencia.setString(2, fecha);
            sentencia.setString(3, fecha);
            
            rs = sentencia.executeQuery();
            
            String nombre;
            String nombreEsp;
            String gastos;
                        
            while (rs.next()) {
                nombre = rs.getString("nombreCliente");
                nombreEsp = rs.getString("nombreEsp");
                gastos = rs.getString("gastos");
                resultado.add(nombre +"/"+nombreEsp + "/" + gastos);
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
        return resultado;

    }
    
}
    