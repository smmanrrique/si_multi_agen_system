/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS2;

import jade.core.Agent;


/**
 *
 * @author goyo
 */
public class AgenteContador extends Agent{
    
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        Object[] args = getArguments();
       
        int valor = Integer.parseInt(args[0].toString());
        
        addBehaviour(new AgenteContador_Comportamiento(valor));
    }
    
    protected void takeDown(){
        System.out.println("El agente " + getLocalName() + " muere");
    }  
}
