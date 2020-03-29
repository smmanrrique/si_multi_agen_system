/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS2;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author goyo
 */
public class HolaMundo2 extends Agent{
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        addBehaviour(new HolaMundo2_Comportamiento());
    }
    protected void takeDown(){
        System.out.println("El agente " + getLocalName() + " muere");
    }    
}
