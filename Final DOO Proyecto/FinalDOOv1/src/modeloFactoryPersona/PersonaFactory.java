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
public class PersonaFactory extends PersonaFactoryMethod{
    
    @Override
    public Persona crearPersona(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno,String auto,int companiaSeguro,int idCreacion) {
        
        switch(idCreacion){
            case 1:
                return new Cliente(dni,nombre,apellido,fechaNacimiento,sexo,auto,companiaSeguro);
            case 2:
                return new Oficinista(dni,nombre,apellido,fechaNacimiento,sexo);
            case 3:
                return new Mecanico(dni,nombre,apellido,fechaNacimiento,sexo,especialidad,turno);
            default:
                return null;
        }
    }
}
