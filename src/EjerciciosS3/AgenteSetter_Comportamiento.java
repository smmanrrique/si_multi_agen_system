/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author goyo
 */
public class AgenteSetter_Comportamiento extends OneShotBehaviour{
    
    AgenteSetter agente;
    
    AgenteSetter_Comportamiento (Agent a1, AgenteSetter a2){
        super(a1);
        this.agente = a2;
    } 
    
    @Override
    public void action() {
        agente.cambio=1;
        agente.set_cambio(2);
        System.out.println("Agente " + myAgent.getLocalName() + " Termino...");
        myAgent.doDelete();
    }
    
}

