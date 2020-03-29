/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;
import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class AgenteSetter extends Agent{
    public int cambio;
    
    public AgenteSetter(){
        super();
        cambio = 0;
    }
    
    public int get_cambio(){
        return cambio;
    }
    
    public void set_cambio(int ncambio){
        this.cambio = ncambio;
    }
    
    @Override
    public void setup(){
        System.out.println("Hola, soy el agente " + getLocalName() + " y mi variable vale " + cambio);
        
        addBehaviour(new AgenteSetter_Comportamiento(this,this));
    }
    
    @Override
    public void takeDown(){
       
        System.out.println("El agente " + getLocalName() + " muere" + " y su variable vale " + cambio);
    }
}
