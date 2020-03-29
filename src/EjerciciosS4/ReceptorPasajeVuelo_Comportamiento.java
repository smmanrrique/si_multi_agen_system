/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS4;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author goyo
 */
public class ReceptorPasajeVuelo_Comportamiento extends SimpleBehaviour{
    public void action() {

        ACLMessage resp = myAgent.blockingReceive();
        if (resp != null) {
            if (resp.getPerformative() == ACLMessage.CONFIRM) {
                System.out.println("recibido");
                try {
                    Object obj = resp.getContentObject();
                    if (obj instanceof PasajeVuelo) {
                        PasajeVuelo pb = (PasajeVuelo) obj;

                        System.out.println("Origen: " + pb.getOrigen());
                        System.out.println("Destino: " + pb.getDestino());
                        System.out.println("Tarifa: " + pb.getTipoTarifa());
                        System.out.println("Aerolinea: " + pb.getAerolinea());
                    }
                } catch (Exception ce) {
                    System.out.println("error " + ce.toString());
                }
            }
        }
    }
    
    public boolean done() {
        return true;
    }
}
