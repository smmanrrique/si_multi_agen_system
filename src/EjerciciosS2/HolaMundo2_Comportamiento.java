/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS2;
import jade.core.behaviours.*;

/**
 *
 * @author goyo
 */
public class HolaMundo2_Comportamiento extends CyclicBehaviour{
    public void action() {
        //ACCIONES A REALIZAR
        System.out.println("Hola de nuevo");
    }
    /*public boolean done(){
        // CONDICION DE FINAL DE COMPORTAMIENTO
        System.out.println("Y ahora me suicido");
        myAgent.doDelete();
        return true;
    }*/
}
