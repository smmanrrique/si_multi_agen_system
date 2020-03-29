package EjerciciosS3;


import jade.core.Agent;
import jade.util.leap.Iterator;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

/**
 *
 * @author goyo
 */
public class BusquedaServicios extends Agent {

    protected void setup() {
        System.out.println(" Hola yo soy el agente :" + this.getLocalName() + " : y voy a buscar servicios registrados en el directorio...");
        doWait(6000);
        BuscarServicios();
    }

    protected void BuscarServicios() {
        DFAgentDescription dfd = new DFAgentDescription();

        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            System.out.println("Total encontrados " + result.length);
            for (int i = 0; i < result.length; i++) {
                String out = result[i].getName() + " proporciona el servicio ";
                Iterator iter = result[i].getAllServices();
                while (iter.hasNext()) {
                    ServiceDescription sd = (ServiceDescription) iter.next();
                    out += " " + sd.getName();
                }
                System.out.println(this.getLocalName() + ": " + out);
            }
        } catch (FIPAException fe) {
            System.err.println(getLocalName() + " busqueda con DF fallida "
                    + fe.getMessage());
            doDelete();
        }

    }

    protected void takeDown() {
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
