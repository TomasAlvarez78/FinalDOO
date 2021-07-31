/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Mecanico;

/**
 *
 * @author cirkuit
 */
public class FichaMecanica {
    Mecanico mecanico;
    Cliente cliente;
    String descripcion;
    Date FechayHora;
    int tiempoEmpleado;
    boolean conformidad;
    
    public FichaMecanica(Mecanico mecanico, Cliente cliente, String descripcion, Date FechayHora, int tiempoEmpleado, boolean conformidad) {
        this.mecanico = mecanico;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.FechayHora = FechayHora;
    }
    
    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
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

    public Date getFechayHora() {
        return FechayHora;
    }

    public void setFechayHora(Date FechayHora) {
        this.FechayHora = FechayHora;
    }

    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(int tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public boolean isConformidad() {
        return conformidad;
    }

    public void setConformidad(boolean conformidad) {
        this.conformidad = conformidad;
    }
    
    
}
