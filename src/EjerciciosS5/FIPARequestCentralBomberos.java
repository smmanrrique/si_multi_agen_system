package EjerciciosS5;

import java.util.StringTokenizer;
 
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.FailureException;
 
 
public class FIPARequestCentralBomberos extends Agent {
 
    public double DISTANCIA_MAX;
 
    protected void setup()
    {
        DISTANCIA_MAX=(Math.random()*10);
        System.out.println("Central "+getLocalName()+": Pendiente de alarmas...");
        MessageTemplate protocolo = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        MessageTemplate performativa = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        MessageTemplate plantilla = MessageTemplate.and(protocolo, performativa);
 
        addBehaviour(new ManejadorResponder(this, plantilla));
    }
 
    class ManejadorResponder extends AchieveREResponder
    {
        public ManejadorResponder(Agent a,MessageTemplate mt) {
            super(a,mt);
        }
 
        protected ACLMessage handleRequest(ACLMessage request)throws NotUnderstoodException, RefuseException
        {
            System.out.println("Central "+getLocalName()+": Hemos recibido una llamada de " + request.getSender().getName() + " diciendo que ha visto fuego.");
            StringTokenizer st=new StringTokenizer(request.getContent());
            String contenido=st.nextToken();
            if(contenido.equalsIgnoreCase("fuego"))
            {
                st.nextToken();
                int distancia=Integer.parseInt(st.nextToken());
                if (distancia<DISTANCIA_MAX)
                {
                    System.out.println("Central "+getLocalName()+": Salimos corriendo");
                    ACLMessage agree = request.createReply();
                    agree.setPerformative(ACLMessage.AGREE);
                    return agree;
                }
                else
                {
                    System.out.println("Central "+getLocalName()+": Fuego demasiado lejos. Pasamos!!");
                    throw new RefuseException("Fuego demasiado lejos");
                }
            }
            else throw new NotUnderstoodException("Central de bomberos alemana, no entiendo el mensaje.");
        }
 
        protected ACLMessage prepareResultNotification(ACLMessage request,ACLMessage response) throws FailureException
        {
            if (Math.random() > 0.2) {
                System.out.println("Central "+getLocalName()+": Hemos vuelto de apagar el fuego.");
                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                return inform;
            }
            else
            {
                System.out.println("Central "+getLocalName()+": Nos hemos quedado sin agua");
                throw new FailureException("Nos hemos quedado sin agua");
            }
        }
    }
}