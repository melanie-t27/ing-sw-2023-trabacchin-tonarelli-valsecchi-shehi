package it.polimi.ingsw.net;

import it.polimi.ingsw.controller.ClientInterface;

/**
 * Interface needed to menage in case the connection is lost
 *
 * @author Tommaso Trabacchin
 * @author Melanie Tonarelli
 * @author Emanuele Valsecchi
 * @author Adem Shehi
 * @version 1.0
 * @since 05/04/2023
 */


public interface OnServerConnectionLostListener {
    /**
     * Defines the action to be performed when a {@linkplain it.polimi.ingsw.controller.ClientInterface client}
     * lost the connection to the server.
     *
     * @param user the user which has lost the connection
     */
    public void onConnectionLost(ClientInterface user);

}
