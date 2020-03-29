/**
 *
 * @author goyo
 */

package EjerciciosS3;

import jade.core.Agent;

public class TickerWaker extends Agent{
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        // Comprueba cada segundo (1000 ms)
        addBehaviour(new Ticker_Comportamiento(this,1000));
        // Despierta a los 10 segudnos (10000 ms)
        addBehaviour(new Waker_Comportamiento(this,10000));
        
    }
    
    protected void takeDown(){
        
        System.out.println("El agente " + getLocalName() + " muere");
    }  
}
