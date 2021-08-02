/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import clases.FichaMecanica;
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
        REGISTRARTURNO,REGISTRARTURNOBD,REGISTRARENTREGA,BUSCARUSUARIO,SIGUIENTE,CARGAR,BUSCARTURNO,CANCELAR,REGISTRARCLIENTE,REGISTRARCLIENTEBD,ACTUALIZARMECANICOS,ACTUALIZARFICHAMECANICA,GENERARDIARIO,GENERARMENSUAL,BUSCARFICHA,GUARDAR,LIMPIAR;
    };

    void setControlador(Controlador c);
    void iniciaVista();
    void cerrarVista();
    
    int getMecanicoId();
    int getEspecialidadId();
    int getDNI();
    String getDate();
    String getTicket();
    
    Cliente getCliente();
    void imprimeResultado(String resultado);
    void imprimeError(Exception e);
    void updateDatos(Cliente c);
    void updateDatos(FichaMecanica f);
    void cargarMecanicos(List <Empleado> lista,int especialidad);
    void cargarSeguros(List <String> lista);
    void cargarEspecialidades(List <String> lista);
    void setEnable(boolean estado);
    
    List<Empleado> getEmpleadosLocales();
    


}
