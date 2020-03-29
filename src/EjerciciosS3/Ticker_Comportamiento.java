/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;

import jade.core.behaviours.TickerBehaviour;
import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class Ticker_Comportamiento extends TickerBehaviour{
    Ticker_Comportamiento(Agent a, long t){
        super(a,t);
    }
    
    protected void onTick() {
        System.out.println("Agent "+myAgent.getLocalName()+": tick ="+getTickCount());
      } 
}
