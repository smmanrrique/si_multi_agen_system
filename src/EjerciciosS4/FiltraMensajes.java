/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS4;

import jade.core.Agent;
/**
 *
 * @author goyo
 */
public class FiltraMensajes extends Agent{
     @Override
    public void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        addBehaviour(new FiltraMensajes_Comportamiento());
    }
    
    @Override
    public void takeDown(){
       
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
