/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS5;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

/**
 *
 
 */
public class TalkAgent extends GuiAgent {

    VentanaChat ventana;
    
    @Override
    protected void setup()
    {
       ventana = new VentanaChat(this, this.getLocalName());
       ventana.setBounds(10,10,400,400);
       ventana.setVisible(true);
       
       EscucharMensajes em = new EscucharMensajes(ventana);
       this.addBehaviour(em);
       
       
       // llamar a un m�todo registrarservicio, tener en cuenta la configuraci�n 
       //  para luego buscarlo
       // ver el c�digo fuente Inmobiliaria Linea 23 a la 36
       ServiceDescription servicio = new ServiceDescription();
        servicio.setType("CHAT");
        servicio.setName("CHAT");
 
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.setName(getAID());
        descripcion.addLanguages("castellano");
        descripcion.addServices(servicio);
 
        try {
            DFService.register(this, descripcion);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
       
       
    }
    
    @Override
    protected void onGuiEvent(GuiEvent ge) {
        
        if (ge.getType() == 0)// origen Evento
        {   
            ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);
            System.out.println("ge.toString()");   
            System.out.println(ge.toString());            
            System.out.println("ge");
            System.out.println(ge);
            System.out.println("ge.getType()");
            System.out.println(ge.getType());
            System.out.println("ge.getSource()"); 
            System.out.println(ge.getSource()); 
            System.out.println("ge.getParameter(0)");           
            System.out.println(ge.getParameter(0));
            System.out.println("ge.getParameter(1)");           
            System.out.println(ge.getParameter(1));
            
            AID x = new AID ((String)ge.getParameter(0), 
                                AID.ISLOCALNAME);
            msg.addReceiver(x);
            msg.setContent((String)ge.getParameter(1));
            
            send(msg);
        }
        if (ge.getType() == 1)// Boton enviar a Todos 
        {
         ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);     
         // el contenido del mensaje tiene como argumento el texto que viene interfaz
        // VErificar este ejemplo
         //  msg.setContent((String)ge.getParameter(1));
         
         
        // Buscar el c�digo de Buscar Servicio  -- Sin Techo desde la linea 39 hasta la  54
            
            // Generar el mensaje acL con todos los agentes
           // ciclo  ver linea 55 a la 60 en Sin TEcho
            // 
            
            send(msg);
        }
    }
    
}
