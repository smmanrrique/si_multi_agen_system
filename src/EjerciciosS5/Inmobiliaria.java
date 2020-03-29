package EjerciciosS5;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;
 
public class Inmobiliaria extends Agent {
    
    protected void setup() {
        System.out.printf("%s: Esperando peticiones.\n", this.getLocalName());
 
        
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("Inmobiliaria");
        servicio.setName("Venta pisos");
 
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.setName(getAID());
        descripcion.addLanguages("castellano");
        descripcion.addServices(servicio);
 
        try {
            DFService.register(this, descripcion);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
 
        // Se crea una plantilla que filtre los mensajes a recibir.
        MessageTemplate template = ContractNetResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
 
        // A�adimos los comportamientos ante mensajes recibidos
        this.addBehaviour(new CrearOferta(this, template));
    }
 
    // Hacemos una simulaci�n para que pueda dar que existe o no piso (sobre un 75% probab).
    private boolean existePiso() {
        return (Math.random() * 100 > 25);
    }
 
    // Calculamos un precio para el piso aleatoriamente (estar� entre 40000 y 150000).
    private int obtenerPrecio() {
        return (int) (Math.random() * 110000) + 40000;
    }
 
    // Simula fallos en el c�lculo de precios.
    private boolean devolverPrecio() {
        return (int) (Math.random() * 10) > 1;
    }
 
    private class CrearOferta extends ContractNetResponder {
        public CrearOferta(Agent agente, MessageTemplate plantilla) {
            super(agente, plantilla);
        }
 
       
        protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
            System.out.printf("%s: Petici�n de oferta de %s recibida.\n", this.myAgent.getLocalName(), cfp.getSender().getLocalName());
 
            // Comprobamos si existen ofertas disponibles
            if (Inmobiliaria.this.existePiso()) {
                // Proporcionamos la informaci�n necesaria
                int precio = Inmobiliaria.this.obtenerPrecio();
                System.out.printf("%s: Preparando oferta(%d euros).\n", this.myAgent.getLocalName(), precio);
 
                // Se crea el mensaje
                ACLMessage oferta = cfp.createReply();
                oferta.setPerformative(ACLMessage.PROPOSE);
                oferta.setContent(String.valueOf(precio));
                return oferta;
            } else {
                // Si no hay ofertas disponibles rechazamos el propose
                System.out.printf("%s: Ninguna oferta disponible.\n", this.myAgent.getLocalName());
                throw new RefuseException("Fallo en la evaluaci�n.");
            }
        }
 
        
        protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
            // Hemos recibido una aceptaci�n de nuestra oferta, enviamos el
            // "contrato"
            System.out.printf("%s: Hay una posible oferta.\n", this.myAgent.getLocalName());
 
            if (devolverPrecio()) {
                System.out.printf("%s: Enviando contrato de compra.\n", this.myAgent.getLocalName());
 
                ACLMessage inform = accept.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                return inform;
            } else {
                System.out.printf("%s: Vaya!, la inmobiliaria ha fallado al enviar el contrato.\n", this.myAgent.getLocalName());
                throw new FailureException("Error al enviar contrato.");
            }
        }
 
    
        protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose,    ACLMessage reject) {
            // Nuestra oferta de piso ha sido rechazada
            System.out.printf("%s: Oferta rechazada por su excesivo precio.\n", this.myAgent.getLocalName());
        }
    }
}
 