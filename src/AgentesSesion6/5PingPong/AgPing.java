/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import jade.core.Agent;
//import jade.core.behaviours.CyclicBehaviour;
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*; 
/**
 *
 */
public class AgPing extends Agent{
  
    private String  nameAgPong;
   
 class ComportPing extends CyclicBehaviour {
     
     

    public void action() {
        
        
 ACLMessage acl= new ACLMessage(ACLMessage.REQUEST);
 AID idpong = new
    AID(nameAgPong,AID.ISLOCALNAME);
     acl.addReceiver(idpong);
     acl.setContent("Ping");
     acl.setLanguage("plain/text");
     send(acl);

     acl = blockingReceive();

     if (acl != null)
     {
     if (acl.getPerformative()==ACLMessage.INFORM)
     {
     System.out.println(acl.getContent());
        }
       }
       } 
}

protected void setup(){
    
    this.doWait(7000);
 Object [] parametros = this.getArguments();
 if (parametros.length == 0) { 
 System.out.println("Error : debe invocar al Agente con Parametros");
 }
 else{
 this.nameAgPong = (String) parametros[0];
 ComportPing comp1 = new ComportPing();
 addBehaviour(comp1);
 }
 } 

}



