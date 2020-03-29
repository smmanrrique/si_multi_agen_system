/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS2;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author goyo
 */
public class AgenteContador_Comportamiento extends Behaviour{

    public int contador;
    
    AgenteContador_Comportamiento(int c){
        contador = c;
    }
    
    public void action() {
        //ACCIONES A REALIZAR
        System.out.println(contador);
        contador--;
    }
    public boolean done(){
        // CONDICION DE FINAL DE COMPORTAMIENTO
        return contador == 0;
    }
}
