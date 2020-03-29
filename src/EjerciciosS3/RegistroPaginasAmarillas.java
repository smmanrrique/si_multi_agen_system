/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;

import jade.core.Agent;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

/**
 *
 * @author goyo
 */
public class RegistroPaginasAmarillas extends Agent {

    public String servicio;

    protected void setup() {
        Object[] arg = getArguments();

        if (arg.length > 0) {

            servicio = (String) arg[0];

            System.out.println("Hola! Soy el agente : " + this.getLocalName() + " : Yo doy el servicio " + servicio);
            registrarServicio();
        }
    }

    private void registrarServicio() {
        DFAgentDescription dfd = new DFAgentDescription();
        // Se le asigna al servicio el ID del Agente que lo ofrece
        dfd.setName(this.getAID());
        
        // Se especifica el tipo y nombre del servicio
        ServiceDescription sd = new ServiceDescription();
        sd.setType(servicio);
        sd.setName(servicio);

        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } 
        catch (FIPAException ex) {
            System.err.println("el Agente :" + getLocalName() + "No ha podido registar el servicio : " + ex.getMessage());
            doDelete();
        }
     }

    protected void takeDown() {
        System.out.println("El agente " + getLocalName() + " muere");
    }

}
