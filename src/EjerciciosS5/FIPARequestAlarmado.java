package EjerciciosS5;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;
 
 
public class FIPARequestAlarmado extends Agent {
 
    protected void setup()
    {
        
        this.doWait(12000);
        Object[] args = getArguments();
            if (args != null && args.length > 0) {
            System.out.println("Solicitando ayuda a varias centrales de bomberos...");
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            for (int i = 0; i < args.length; ++i)
            msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
            msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
            msg.setContent("fuego a 5 kms");
 
            addBehaviour(new ManejadorInitiator(this,msg));
 
        }
        else System.out.println("Especifique el nombre de al menos alg�n agente central de bomberos.");
    }
 
    class ManejadorInitiator extends AchieveREInitiator
    {
        public ManejadorInitiator(Agent a,ACLMessage msg) {
            super(a,msg);
        }
 
        protected void handleAgree(ACLMessage agree)
        {
            System.out.println("Central de bomberos " + agree.getSender().getName()
                    + " informa que ha salido a apagar el fuego.");
        }
 
        protected void handleRefuse(ACLMessage refuse)
        {
            System.out.println("Central de bomberos " + refuse.getSender().getName()
                    + " responde que el fuego est� muy lejos y no puede apagarlo.");
        }
 
        protected void handleNotUnderstood(ACLMessage notUnderstood)
        {
            System.out.println("Central de bomberos " + notUnderstood.getSender().getName()
                    + " es idiota y no entiende el mensaje.");
        }
 
        protected void handleInform(ACLMessage inform)
        {
            System.out.println("Central de bomberos " + inform.getSender().getName()
                    + " informa que ha apagado el fuego.");
        }
 
        protected void handleFailure(ACLMessage fallo)
        {
            if (fallo.getSender().equals(myAgent.getAMS())) {
                System.out.println("Alguna de las centrales de bomberos no existe");
            }
            else
            {
                System.out.println("Fallo en central de bomberos " + fallo.getSender().getName()
                        + ": " + fallo.getContent().substring(1, fallo.getContent().length()-1));
            }
        }
    }
 
}