/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS4;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;
import jade.core.AID;
/**
 *
 * @author goyo
 */
public class EnviaMensaje_Comportamiento extends CyclicBehaviour{
    Scanner teclado;
    
    public EnviaMensaje_Comportamiento(){
        super();
        teclado = new Scanner (System.in);
    }
    
    //@Override
    public void action() {
        System.out.println("Nombre del Agente al que va dirigido el mensaje: ");
        String name = teclado.nextLine();
        System.out.println("Introduce el mensaje: ");
        String texto = teclado.nextLine();
        
        // Se construye un mensaje de tipo PETICIÓN
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        
        // Construimos un objeto de tipo Identificador de Agente (Agent IDentifier)
        AID p = new AID(name, AID.ISLOCALNAME);
        // Añadimos como receptor del mensaje el AID
        msg.addReceiver(p);
        // Añadimos el contenido del mensaje
        msg.setContent(texto);
        
        // Enviamos el mensaje
        myAgent.send(msg);
    }
}
