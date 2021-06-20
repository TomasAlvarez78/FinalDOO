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
public abstract class Empleado extends Persona{
    public Empleado(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo) {
        super(dni,nombre,apellido,fechaNacimiento,sexo);
    }

    @Override
    public abstract String getTipo();
}
