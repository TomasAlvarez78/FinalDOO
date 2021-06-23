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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cirkuit
 */
public class SegurosDAOImpl implements SegurosDAO{

    private ConexionSql conexion = null;
    
    public SegurosDAOImpl() {
        conexion = ConexionSql.getInstancia();
    }
    
    @Override
    public List<String> getSeguros() {
        List<String> listaSeguros = new ArrayList<>();
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        String seguro;
        
        try{
            con = conexion.getConnection();
            
            String sql = "SELECT nombre FROM companiaSeguroTable";
            sentencia = con.prepareStatement(sql);
            rs = sentencia.executeQuery();
            while (rs.next()){
                seguro= rs.getString("nombre");
                listaSeguros.add(seguro);
            }
            return listaSeguros;
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
        return null;
    }
    
}
