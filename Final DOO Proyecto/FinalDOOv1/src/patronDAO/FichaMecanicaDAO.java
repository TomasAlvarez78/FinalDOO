/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

import clases.FichaMecanica;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cirkuit
 */
public interface FichaMecanicaDAO {
    int buscarTurno(int fichaId);
    
    FichaMecanica getFichaId(int fichaId);
    
    List <FichaMecanica> listarFichasMensual(Date fecha);
    
    boolean actFicha(FichaMecanica ficha, int fichaId);
    
    List <String> infDiario(int especialidadId, String fecha);
    List <String> infMensual(int seguroId, String fecha);
    
    boolean agregarFicha(int idTurno);
    
    boolean borrarFicha(int id);
    
    void cerrarConexion();
}
