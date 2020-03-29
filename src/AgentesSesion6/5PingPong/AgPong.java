/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*; 
/**
 *ping:agentesencillo.AgPing(agpong); pong:agentesencillo.AgPong
 */
public class AgPong extends Agent{
  
    private String  nameAgPong;
   

 class ComportPong extends CyclicBehaviour {
     
     public ComportPong(Agent a)
             
     {
       super(a); 
     }
     
    public void action() {
ACLMessage acl;
 acl = blockingReceive();
 
 

 if (acl != null)
 {
 if (acl.getPerformative()==ACLMessage.REQUEST)
 {
 System.out.println(acl.getContent());
 doWait(3000);
 ACLMessage respuesta = new ACLMessage(ACLMessage.INFORM);
 respuesta.addReceiver(acl.getSender());
 respuesta.setContent("Pong");
 send(respuesta);

 }
 } 
}
}

 protected void setup()
 {
 ComportPong comp1 = new ComportPong(this);
 addBehaviour(comp1);
 } 

}
