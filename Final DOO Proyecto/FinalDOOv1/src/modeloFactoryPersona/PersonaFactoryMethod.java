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
public abstract class PersonaFactoryMethod {
    public abstract Persona crearPersona(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,int especialidad,String turno,String auto,int companiaSeguro,int idCreacion);
}
