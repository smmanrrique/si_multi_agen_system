/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS5;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 
 */
public class EscucharMensajes extends CyclicBehaviour {

     VentanaChat v;
     
     public EscucharMensajes(VentanaChat v1)
     {
         this.v = v1;
     }
     
    @Override
    public void action() {
        
        ACLMessage m = this.myAgent.receive();
        
        if (m != null)
        {
            v.escribirMensaje(m.getSender().getLocalName()
                             + " --> " + m.getContent());
            
        }
        
    
    }
    
}
