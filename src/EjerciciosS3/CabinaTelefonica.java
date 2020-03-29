/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 * Tests PhoneFSMBehaviour
 * see PhoneFSMBehaviour.java
 */
public class CabinaTelefonica extends Agent  {
    
    @Override
    public void setup() {
        addBehaviour(new CabinaTelefonica_Comportamiento(this));
    }
    
    @Override
    public void takeDown(){
       
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
