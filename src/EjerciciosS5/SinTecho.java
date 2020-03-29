package EjerciciosS5;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;
 
import java.util.Date;
import java.util.Vector;
 
public class SinTecho extends Agent {
 
    // N�mero de ofertas que se considerar�n.
    private int numeroDeOfertas;
 
    // Precio m�ximo que se pagar� por un piso.
    private int precionMaximo;
 
    @Override
    protected void setup() {
        
        this.doWait(15000);
        // El precio m�ximo se recibir� como argumento de entrada.
        Object[] args = this.getArguments();
 
        if (args != null && args.length == 1) {
            this.precionMaximo = Integer.parseInt(((String) args[0]));
 
            // B�squeda del servicio de inmobiliarias en las p�ginas amarillas.
            ServiceDescription servicio = new ServiceDescription();
            servicio.setType("Inmobiliaria");
            servicio.setName("Venta pisos");
 
            DFAgentDescription descripcion = new DFAgentDescription();
            descripcion.addLanguages("castellano");
            descripcion.addServices(servicio);
 
            try {
                DFAgentDescription[] resultados = DFService.search(this, descripcion);
                if (resultados.length <= 0) {
                    System.out.println("No existen inmobiliarias.");
                } else {
                    System.out.println("En busca de piso, tenemos " + resultados.length + " ofertas...");
 
                    // Creamos el mensaje CFP(Call For Proposal) cumplimentando sus par�metros
                    ACLMessage mensajeCFP = new ACLMessage(ACLMessage.CFP);
                    for (DFAgentDescription agente:resultados) {
                        mensajeCFP.addReceiver(agente.getName());
                    }
                    mensajeCFP.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                    mensajeCFP.setContent("Busco piso, �que precios me ofrecen?");
 
                    // Indicamos el tiempo que esperaremos por las ofertas.
                    mensajeCFP.setReplyByDate(new Date(System.currentTimeMillis() + 15000));
 
                    // Se a�ade el comportamiento que manejar� las ofertas.
                    this.addBehaviour(new ManejoOpciones(this, mensajeCFP));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
 
        } else {
            System.out.println("N�mero de par�metros introducidos incorrecto (precio m�ximo a pagar)");
        }
 
    } // Fin del setup
 
    private class ManejoOpciones extends ContractNetInitiator {
        public ManejoOpciones(Agent agente, ACLMessage plantilla) {
            super(agente, plantilla);
        }
 
        // Manejador de proposiciones.
        @Override
        protected void handlePropose(ACLMessage propuesta, Vector aceptadas) {
            System.out.printf("%s: Recibida oferta de inmobiliaria %s. Ofrece un piso por %s euros.\n",
                this.myAgent.getLocalName(), propuesta.getSender().getLocalName(), propuesta.getContent());
        }
 
        // Manejador de rechazos de proposiciones.
        @Override
        protected void handleRefuse(ACLMessage rechazo) {
            System.out.printf("%s: La inmobiliaria %s no tiene nada que ofrecer.\n",
                this.myAgent.getLocalName(), rechazo.getSender().getLocalName());
        }
 
        // Manejador de respuestas de fallo.
        @Override
        protected void handleFailure(ACLMessage fallo) {
            if (fallo.getSender().equals(myAgent.getAMS())) {
                // Esta notificacion viene del entorno de ejecuci�n JADE (no existe el receptor)
                System.out.println("AMS: Esta inmobiliaria no existe/no es accesible");
            } else {
                System.out.printf("%s: La inmobiliaria %s ha sufrido un fallo.\n",
                    this.myAgent.getLocalName(), fallo.getSender().getLocalName());
            }
            // Fall�, por lo tanto, no recibiremos respuesta desde ese agente
            SinTecho.this.numeroDeOfertas--;
        }
 
        // M�todo colectivo llamado tras finalizar el tiempo de espera o recibir todas las propuestas.
        @Override
        protected void handleAllResponses(Vector respuestas, Vector aceptados) {
            // Se comprueba si alguna inmobiliaria se pas� el plazo de env�o de ofertas.
            if (respuestas.size() < numeroDeOfertas) {
                System.out.printf("%s: %d inmobiliarias llegan tarde.\n",
                    this.myAgent.getLocalName(), SinTecho.this.numeroDeOfertas - respuestas.size());
            }
 
            // Ahora toca escoger la mejor oferta
            int mejorOferta = Integer.MAX_VALUE;
            AID mejorInmobiliaria = null;
            ACLMessage aceptado = null;
            for (Object resp:respuestas) {
                ACLMessage mensaje = (ACLMessage) resp;
                if (mensaje.getPerformative() == ACLMessage.PROPOSE) {
                    ACLMessage respuesta = mensaje.createReply();
                    respuesta.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    aceptados.add(respuesta);
 
                    // Si la oferta es la mejor (inferior a todas las otras)
                    // se almacena su precio y el AID de la inmobiliaria que la hizo.
                    int oferta = Integer.parseInt(mensaje.getContent());
                    if (oferta <= precionMaximo && oferta <= mejorOferta) {
                        mejorOferta = oferta;
                        mejorInmobiliaria = mensaje.getSender();
                        aceptado = respuesta;
                    }
                }
            }
 
            // Si hay una oferta aceptada se modifica su performativa.
            if (aceptado != null) {
                System.out.printf("%s: Decidido, el piso de %s es el que compro!\n",
                    this.myAgent.getLocalName(), mejorInmobiliaria.getLocalName());
                aceptado.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            }
        }
 
        // Manejador de los mensajes inform.
        @Override
        protected void handleInform(ACLMessage inform) {
            System.out.printf("%s: La inmobiliaria %s nos ha enviado el contrato!!\n",
                this.myAgent.getLocalName(), inform.getSender().getName());
        }
    }
}
 
