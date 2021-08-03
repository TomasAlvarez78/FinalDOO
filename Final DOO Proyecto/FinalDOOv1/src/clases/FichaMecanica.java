/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Empleado;

/**
 *
 * @author cirkuit
 */
public class FichaMecanica {
    Empleado mecanico;
    Cliente cliente;
    String descripcion;
    String FechayHora;
    String gastos;
    int tiempoEmpleado;
    int conformidad;
    
    public FichaMecanica() {
        
    }
    
    public FichaMecanica(String descripcion, String FechayHora, int tiempoEmpleado,String gastos, int conformidad) {
        this.descripcion = descripcion;
        if(FechayHora != null){
            this.FechayHora = FechayHora;
        }
        if(tiempoEmpleado > 0)
            this.tiempoEmpleado = tiempoEmpleado;
        if(gastos.length() >  0)
            this.gastos = gastos;
        this.conformidad = conformidad;
    }
    

    public Empleado getMecanico() {
        return mecanico;
    }

    public void setMecanico(Empleado mecanico) {
        this.mecanico = mecanico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechayHora() {
        return FechayHora;
    }

    public void setFechayHora(String FechayHora) {
        this.FechayHora = FechayHora;
    }

    public String getGastos() {
        return gastos;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(int tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public int isConformidad() {
        return conformidad;
    }

    public void setConformidad(int conformidad) {
        this.conformidad = conformidad;
    }
    
    
    
}
