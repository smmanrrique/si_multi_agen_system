/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosS3;

import jade.core.Agent;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Simulates a coin operated telephone with dimes, nickels and quarters.
 * Meant to illustrate the use of the FSMBehaviour class.
 * Note the discussion below of receive() and blockingReceive().
 *
 * To run this, use rma or dummy to send a message to the PhoneFSM agent
 * (see PhoneFSM.java)  with the content nickel, dime or quarter (or anything).
 * Any communicative act will do.
 *
 * DG Aug 2002
 */

public class CabinaTelefonica_Comportamiento extends FSMBehaviour{

       // FSM states
    private static final String ZERO_STATE = "zero";
    private static final String FIVE_STATE = "five";
    private static final String TEN_STATE = "ten";
    private static final String FIFTEEN_STATE = "fifteen";
    private static final String TWENTY_STATE = "twenty";
    private static final String TWENTYFIVE_STATE = "twentyfive";

    private static final String ERROR_STATE = "error";

    // behaviour return values (from each onEnd() method
    private final int ZERO = 0;
    private final int FIVE = 5;
    private final int TEN = 10;
    private  final int FIFTEEN = 15;
    private  final int TWENTY = 20;
    private  final int TWENTYFIVE = 25;

    public CabinaTelefonica_Comportamiento(Agent a) {
        super(a);

        // register state behaviours

        registerFirstState(new ZeroBehaviour(myAgent), ZERO_STATE);
        registerState(new FiveBehaviour(myAgent), FIVE_STATE);
        registerState(new TenBehaviour(myAgent), TEN_STATE);
        registerState(new FifteenBehaviour(myAgent), FIFTEEN_STATE);
        registerState(new TwentyBehaviour(myAgent),TWENTY_STATE);
        registerLastState(new TwentyFiveBehaviour(myAgent), TWENTYFIVE_STATE);
        registerLastState(new ErrorBehaviour(myAgent), ERROR_STATE);

        // transitions of the FSM
        registerTransition(ZERO_STATE, FIVE_STATE, FIVE);
        registerTransition(ZERO_STATE, TEN_STATE, TEN);
        registerTransition(ZERO_STATE, TWENTYFIVE_STATE, TWENTYFIVE);
        registerTransition(FIVE_STATE, TEN_STATE, FIVE);
        registerTransition(FIVE_STATE, FIFTEEN_STATE, TEN);
        registerTransition(TEN_STATE, FIFTEEN_STATE, FIVE);
        registerTransition(TEN_STATE, TWENTY_STATE, TEN);
        registerTransition(FIFTEEN_STATE, TWENTY_STATE, FIVE);
        registerTransition(FIFTEEN_STATE, TWENTYFIVE_STATE, TEN);
        registerTransition(TWENTY_STATE, TWENTYFIVE_STATE, FIVE);

         registerDefaultTransition(ZERO_STATE, ERROR_STATE);
         registerDefaultTransition(FIVE_STATE, ERROR_STATE);
         registerDefaultTransition(TEN_STATE, ERROR_STATE);
         registerDefaultTransition(FIFTEEN_STATE, ERROR_STATE);
         registerDefaultTransition(TWENTY_STATE, ERROR_STATE);

         scheduleFirst();
    } // end PhoneFSMBehaviour constructor

    public int processCoins(ACLMessage msg) {
        int eventType = ZERO;
        String coin = msg.getContent();
        if (coin == null) return eventType;
        if(coin.equals("nickel")) {
            System.out.println("received a nickel");
            eventType = FIVE;
        } else if(coin.equals("dime") 
               // && !TWENTY_STATE.equals(getName(getCurrent())) 
                ) {
            System.out.println("received a dime");
            eventType = TEN;
        } else if(coin.equals("quarter") 
             //   && ZERO_STATE.equals(getName(getCurrent())) 
                ) {
            System.out.println("received a quarter");
            eventType = TWENTYFIVE;
         } else {
            System.out.println("illegal coin");
        }

        return eventType;
    } // end process coins

    // child behaviours inner classes

    // associated with the initial state
    class ZeroBehaviour extends OneShotBehaviour {

          int transition = ZERO;

        public ZeroBehaviour(Agent a) {
            super(a);
        }

        public void action() {
                System.out.println("Comportamiento Zero");
		ACLMessage msg  = myAgent.blockingReceive();
                transition = processCoins(msg);
				
          } // end action()

          public int onEnd() {
              //System.out.println("onEnd called");
              return  transition;
          }

    } // end ZeroBehaviour inner class

     class FiveBehaviour extends OneShotBehaviour {
        private int transition = ZERO;
        public FiveBehaviour(Agent a) {
            super(a);
        }

        public void action() {

            System.out.println("Comportamiento FIVE");
                  ACLMessage msg  = myAgent.blockingReceive();
                  transition = processCoins(msg);

          } // end action()

          public int onEnd() {
              return transition;
          }

    } // end FiveBehaviour inner class

        class TenBehaviour extends OneShotBehaviour {
            private int transition = ZERO;
            public TenBehaviour(Agent a) {
                super(a);
            }

            public void action() {
		  System.out.println("Comportamiento TEN");
                  ACLMessage msg  = myAgent.blockingReceive();
                transition = processCoins(msg);
          }
            public int onEnd() {
                return transition;
            }
    } // end TenBehaviour inner class

        class FifteenBehaviour extends OneShotBehaviour {
            private int transition = ZERO;
            public FifteenBehaviour(Agent a) {
                super(a);
            }

            public void action() {
		System.out.println("Comportamiento FifTeen");
                ACLMessage msg  = myAgent.blockingReceive();
                transition = processCoins(msg);

               }
               public int  onEnd() {
                   return transition;
            }
    } // end FifteenBehaviour inner class

    class TwentyBehaviour extends OneShotBehaviour {
        private int transition= ZERO;
        public TwentyBehaviour(Agent a) {
            super(a);
        }

         public void action() {
                  System.out.println("Comportamiento Twenty");
                  ACLMessage msg  = myAgent.blockingReceive();
                  transition = processCoins(msg);

            }
            public int onEnd() {
                return transition;
            }
    } // end TwentyBehaviour inner class

    // associated with the final state
    class TwentyFiveBehaviour extends OneShotBehaviour {

        public TwentyFiveBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            System.out.println("Comportamiento TwentyFive");
            System.out.println("buzz, ..., buzz, ...., buzz ....,");
        }
    } // end TwentyFiveBehaviour inner class

    class ErrorBehaviour extends OneShotBehaviour {
        public ErrorBehaviour(Agent a) {
            super(a);
        }
        public void action() {
            System.out.println("Sorry, wrong coins. Please try again.");
        }
    } 
}