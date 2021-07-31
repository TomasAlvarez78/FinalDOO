/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloFactoryPersona;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author cirkuit
 */
public abstract class Persona {
    
    protected String nombre;
    protected String apellido;
    protected Date fechaNacimiento;
    protected int dni;
    protected String sexo;
    
    public Persona(int dni,String nombre, String apellido, Date fechaNacimiento,String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
      public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public int getEdad(){
        Calendar c = Calendar.getInstance();
        c.setTime(this.fechaNacimiento);
        
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH) + 1;
        int fecha = c.get(Calendar.DATE);
        
        LocalDate fecha1 = LocalDate.of(anio, mes, fecha);
        LocalDate ahora = LocalDate.now();
        Period diff1 = Period.between(fecha1, ahora);
        System.out.println("Edad: " + diff1.getYears() + " anios");
        
        return diff1.getYears();
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    public abstract String getTipo();
    
    public abstract int getEspecialidad();

}
