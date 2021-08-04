/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cirkuit
 */
public class Modelo {
    
    public List<String> generarTexto(List<String> entrada){
        List<String> salida = new ArrayList<>();
        int monto=0;

        
        for(String linea : entrada){
            String [] lineaTemp = linea.split("/");
            String [] lineaMontoTemp = lineaTemp[2].split(";");
            for (int i = 0; i < lineaMontoTemp.length; i++) {
                if( ((i+1) % 4) == 0){
                    monto += Integer.parseInt(lineaMontoTemp[i]);
                }
            }
            
            salida.add("Cliente: " + lineaTemp[0] + "\nEspecialidad solicitada: " + lineaTemp[1] + "\nMonto total: $" + monto);
            monto = 0;
        }
        
        return salida;
        
        /*
        
            Federico Alvarez/Carroceria/Parche;Pomolito;23;120
            Diego Quiroga/Carroceria/Parlantes;Sony;2;5000;Cubiertas;Goldin;5;100
        
        */
        
    }
}
