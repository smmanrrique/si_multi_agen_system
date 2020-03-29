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
public class EnviaMensaje extends Agent{
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        addBehaviour(new EnviaMensaje_Comportamiento());
       
    }
    protected void takeDown(){
        System.out.print("El agente " + getLocalName() + " muere");
    }
}
