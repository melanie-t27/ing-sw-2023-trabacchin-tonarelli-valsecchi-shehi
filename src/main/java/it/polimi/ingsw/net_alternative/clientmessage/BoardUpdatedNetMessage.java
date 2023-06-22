package it.polimi.ingsw.net_alternative.clientmessage;

import it.polimi.ingsw.model.TileSubject;
import it.polimi.ingsw.net_alternative.ClientMessage;
import it.polimi.ingsw.net_alternative.ClientDispatcherInterface;

/**
 * Class used to allow the client notify the change of board.
 * <br>
 * It extends {@link ClientMessage} Interface.
 */
public class BoardUpdatedNetMessage implements ClientMessage {



    private final TileSubject[][] tileSubjects;

    /**
     * Constructor of the class
     * @param tileSubjects Represents the bord updated
     */
    public BoardUpdatedNetMessage(TileSubject[][] tileSubjects) {
        this.tileSubjects = tileSubjects;
    }


    /**
     * getter method to get the updated board
     * @return the Board updated
     */
    public TileSubject[][] getTileSubjects() {
        return tileSubjects;
    }

    /**
     * It dispatches the message from the client
     * @param clientDispatcherInterface is the handler of the message.
     */
    @Override
    public void dispatch(ClientDispatcherInterface clientDispatcherInterface) {
        clientDispatcherInterface.dispatch(this);
    }
}