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
public class Cliente extends Persona{

    String auto;
    int companiaSeguro;

    public Cliente(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,String auto,int companiaSeguro) {
        super(dni,nombre,apellido,fechaNacimiento,sexo);
        this.auto = auto;
        this.companiaSeguro = companiaSeguro;
    }

    @Override
    public String getTipo() {
        return "Soy un Cliente";
    }
    
    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public int getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(int companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    @Override
    public int getEspecialidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    

}
