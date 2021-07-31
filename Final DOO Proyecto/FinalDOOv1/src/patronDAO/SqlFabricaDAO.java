/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronDAO;

/**
 *
 * @author cirkuit
 */
public class SqlFabricaDAO extends FabricaDAO{
    @Override
    public EmpleadoDAO getEmpleadoDao() {
        return new EmpleadoDAOImpl();
    }
    
    @Override
    public ClienteDAO getClienteDao() {
        return new ClienteDAOImpl();
    }

    @Override
    public TurnoDAO getTurnoDao() {
        return new TurnoDAOImpl();
    }
    
    @Override
    public SegurosDAO getSeguroDao() {
        return new SegurosDAOImpl();
    }

    @Override
    public FichaMecanicaDAO getFichaMecanicaDao() {
        return new FichaMecanicaDAOImpl();
    }
}
