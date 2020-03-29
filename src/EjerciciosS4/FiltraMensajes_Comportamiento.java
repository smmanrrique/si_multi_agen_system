/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS4;
import jade.core.behaviours.CyclicBehaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
/**
 *
 * @author goyo
 */
public class FiltraMensajes_Comportamiento extends CyclicBehaviour{
    public void action() {
        //ACCIONES A REALIZAR
        ACLMessage msg = myAgent.receive();
        // myAgent.receive()
        
        if (msg != null)
        {
            System.out.println(" Mensaje recibido del Agente " + msg.getSender().getLocalName());
            System.out.println(" Que dice >>> "+ msg.getContent());
            System.out.println(msg.getEncoding() + " " + msg.getLanguage());
            // etc...
        }
    }
  
}
