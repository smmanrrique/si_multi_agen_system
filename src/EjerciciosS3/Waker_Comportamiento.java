/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;
import jade.core.behaviours.WakerBehaviour;
import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class Waker_Comportamiento extends WakerBehaviour{
    Waker_Comportamiento (Agent a, long t){
        super(a, t);
    } 
    protected void handleElapsedTimeout() {
        System.out.println("Agent "+myAgent.getLocalName()+": Hora de despertarse. Adios...");
        myAgent.doDelete();
      } 
}
