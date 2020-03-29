package EjerciciosS2;

import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class HolaMundo extends Agent{
    protected void setup(){
        System.out.println("Hola holita mundo");
        System.out.println("Mi nombrecito es " + getLocalName());
    }
    
    protected void takeDown(){
        System.out.println("Adios mundito cruel");
    }
}
