package it.polimi.ingsw.net_alternative.clientmessage;

import it.polimi.ingsw.net_alternative.ClientMessage;
import it.polimi.ingsw.net_alternative.ClientDispatcherInterface;

/**
 * Represents a client message indicating that a new CommonGoal has been Assigned.
 * <br>
 * Extends {@link ClientMessage} Interface.
 */
public class AssignedCommonGoalNetMessage implements ClientMessage {

    private final String description;

    private final int n;

    /**
     * Constructor of the class
     * @param description Is the Description of the CommonGoal
     * @param n Is the number of the CommonGoal
     */
    public AssignedCommonGoalNetMessage(String description, int n) {
        this.description = description;
        this.n = n;
    }

    /**
     * Getter method to get the description
     * @return The description of the CommonGoal
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter Method for the number of the CommonGoal
     * @return the number of the Common Goal
     */
    public int getN() {
        return n;
    }

    /**
     * It dispatches the message from the Client.
     * @param clientDispatcherInterface is the Handler of the CommonGoal.
     */
    @Override
    public void dispatch(ClientDispatcherInterface clientDispatcherInterface) {
        clientDispatcherInterface.dispatch(this);
    }


}