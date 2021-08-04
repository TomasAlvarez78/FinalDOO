/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;
import modeloFactoryPersona.Cliente;
import modeloFactoryPersona.Mecanico;

public class Turno {

    Date diayHora;
    Mecanico mecanicoAsignado;
    Cliente cliente;
    String vehiculo;
    boolean personalTelefonica;
    int estado;
    
    public Date getDiayHora() {
        return diayHora;
    }

    public void setDiayHora(Date diayHora) {
        this.diayHora = diayHora;
    }

    public Mecanico getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    public void setMecanicoAsignado(Mecanico mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public boolean isPersonalTelefonica() {
        return personalTelefonica;
    }

    public void setPersonalTelefonica(boolean personalTelefonica) {
        this.personalTelefonica = personalTelefonica;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}