/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloFactoryPersona;

import java.util.Date;

/**
 *
 * @author cirkuit
 */
public class Mecanico extends Empleado{
    
    int especialidad;
    String turno;
    
    public Mecanico(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno) {
        super(dni,nombre,apellido,fechaNacimiento,sexo);
        this.especialidad = especialidad;
        this.turno = turno;
    }

    @Override
    public String getTipo() {
        return "Soy un Mecanico";
    }

    @Override
    public int getEspecialidad() {
        return especialidad;
    }
    
    
}
