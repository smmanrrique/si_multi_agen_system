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
public class HolaMundoBehaviour extends Agent{
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        addBehaviour(new Behaviour(){
            public void action() {
                //ACCIONES A REALIZAR
                System.out.println("Yo os saludo");
            }
            public boolean done(){
                // CONDICION DE FINAL DE COMPORTAMIENTO
                return true;
            }
        });
    }
    protected void takeDown(){
        System.out.println("El agente " + getLocalName() + " muere");
    }
}

