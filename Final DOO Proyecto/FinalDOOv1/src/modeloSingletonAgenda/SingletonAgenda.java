/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modeloSingletonAgenda;
import modeloFactoryPersona.Mecanico;
import java.util.Calendar;
/**
 *
 * @author cirkuit
 */
public class SingletonAgenda {
    int anio;
    Mecanico mecanico;
    
    private static SingletonAgenda instancia;
    
    private SingletonAgenda(Mecanico mec){
        mecanico = mec;
    }
    
    public static SingletonAgenda getInstancia(Mecanico mec){
        if(instancia == null){
            instancia = new SingletonAgenda(mec);
        
        }
                
        return instancia;
    }

    
}
