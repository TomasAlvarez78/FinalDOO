/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import controlador.Controlador;
import java.util.List;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;

/**
 *
 * @author cirkuit
 */
public interface InterfazVista {
    public static enum Operacion {
        REGISTRARTURNO,REGISTRARTURNOBD,REGISTRARENTREGA,BUSCARUSUARIO,SIGUIENTE,CARGAR,BUSCARTURNO;
    };

    void setControlador(Controlador c);
    void iniciaVista();
    String getDate();
    String getTicket();
    int getDNI();
    void imprimeResultado(String resultado);
    void imprimeError(Exception e);
    void updateDatos(Cliente c);
    void cargarMecanicos(List <Empleado> lista);
    int getMecanicoId();
    


}
