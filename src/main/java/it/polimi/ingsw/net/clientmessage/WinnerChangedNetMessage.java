package it.polimi.ingsw.net.clientmessage;

import it.polimi.ingsw.net.ClientMessage;
import it.polimi.ingsw.net.ClientDispatcherInterface;

/**
 * Represents a client message indicating a change in the winner of the game.
 * <p>
 * The {@code WinnerChangedNetMessage} class is a client message that notifies the client about a change in the winner
 * of the game. It contains the nickname of the new winner.
 * </p>
 * <br>
 * @author Tommaso Trabacchin
 * @author Melanie Tonarelli
 * @author Emanuele Valsecchi
 * @author Adem Shehi

 */
public class WinnerChangedNetMessage implements ClientMessage {

    /**
     * Nickname of the player
     */
    String nickname;

    /**
     * Constructs a {@code WinnerChangedNetMessage} with the specified winner's nickname.
     *
     * @param nickname the nickname of the new winner
     */
    public WinnerChangedNetMessage(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the nickname of the winner.
     *
     * @return the nickname of the winner
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Dispatches the message to the client dispatcher for handling.
     *
     * @param clientDispatcherInterface the client dispatcher interface
     */
    @Override
    public void dispatch(ClientDispatcherInterface clientDispatcherInterface) {
        clientDispatcherInterface.dispatch(this);
    }
}
