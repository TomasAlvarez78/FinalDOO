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
public class Oficinista extends Empleado{
    
    public Oficinista(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo) {
        super(dni,nombre,apellido,fechaNacimiento,sexo);
    }

    @Override
    public String getTipo() {
        return "Soy un oficinista";
    }

    @Override
    public int getEspecialidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
