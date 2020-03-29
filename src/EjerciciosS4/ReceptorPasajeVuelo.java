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
public class ReceptorPasajeVuelo extends Agent{
     protected void setup(){
        addBehaviour( new ReceptorPasajeVuelo_Comportamiento());
    } 
    
    protected void takeDown(){
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
