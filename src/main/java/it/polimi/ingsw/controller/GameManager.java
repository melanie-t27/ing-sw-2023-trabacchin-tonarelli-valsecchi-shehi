package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.utils.Coordinate;

import java.util.List;

/**
 *
 * The abstract class representing the game manager responsible for managing the game flow and interactions.
 * Subclasses must implement specific game-related methods.
 *
 * This class provides methods for dragging tiles to the bookshelf, registering players,
 * setting the next current player, and quitting the game. It also includes methods for
 * registering listeners for various game events and verifying if all players except
 * a specified player are disconnected.
 *
 * The GameManager requires a Controller instance to manage the game.
 *
 * @author Tommaso Trabacchin
 * @author Melanie Tonarelli
 * @author Emanuele Valsecchi
 * @author Adem Shehi
 * @version 6.0
 * @since 03/05/2023
 */
public abstract class GameManager {
    private final Controller controller;

    /**
     * Constructs a new GameManager with the specified controller.
     *
     * @param controller the Controller instance managing the game.
     */
    public GameManager(Controller controller){
        this.controller = controller;
    }

    /**
     * Returns the Controller instance associated with this GameManager.
     * <br>
     * @return the controller associated to this GameManager.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * <br>
     * Allows dragging tiles to the bookshelf.
     *
     * @param view the ClientInterface view of the player.
     * @param chosenTiles the list of chosen tile coordinates.
     * @param chosenColumn the chosen column index.
     */
    public abstract void dragTilesToBookShelf(ClientInterface view, List<Coordinate> chosenTiles, int chosenColumn);

    /**
     * Abstract method to be implemented by subclasses.
     * Registers a player with the specified nickname and view.
     *
     * @param view the ClientInterface view of the player.
     * @param nickname the nickname of the player.
     */
    public abstract void registerPlayer(ClientInterface view, String nickname);

    /**
     * Protected abstract method to be implemented by subclasses.
     * Sets the next current player in the game.
     */
    protected abstract void setNextCurrentPlayer();

    /**
     * Method that handles to process to quit the game
     * Updates the player's state, triggers necessary events, and <br>most important, checks if the game should end.
     * @param view the ClientInterface view of the player.
     */
    public synchronized void quitGame(ClientInterface view) {
        getController().getState().getPlayerFromView(view).setPlayerState(PlayerState.QUITTED);
        //getController().getLobbyController().onQuitGame(view);
        setNextCurrentPlayer();
        if(getController().getState().getPlayers().stream().filter(p->p.getPlayerState()!=PlayerState.QUITTED).count() <= 1) {
            getController().getState().setGameState(GameState.END);
            getController().getLobbyController().onEndGame(getController());
        }
    }

    /**
     * Registers listeners for the specified player with the given nickname and view.
     * <br>
     * Replaces old listeners with the new ones.
     * @param view the ClientInterface view of the player.
     * @param nickname of the player.
     */

    public void registerListeners(ClientInterface view, String nickname){
        ClientInterface oldView = getController().getState().getPlayerFromNick(nickname).getVirtualView();
        getController().getState().removeAchievedCommonGoalListener(oldView);
        getController().getState().setAchievedCommonGoalListener(view);
        getController().getState().removeOnAchievedPersonalGoalListener(oldView);
        getController().getState().setOnAchievedPersonalGoalListener(view);
        getController().getState().removeOnAdjacentTilesUpdatedListener(oldView);
        getController().getState().setOnAdjacentTilesUpdatedListener(view);
        getController().getState().removeOnAssignedCommonGoalListener(oldView);
        getController().getState().setOnAssignedCommonGoalListener(view);
        getController().getState().getPlayerFromNick(nickname).removeOnAssignedPersonalGoalListener(oldView);
        getController().getState().getPlayerFromNick(nickname).setOnAssignedPersonalGoalListener(view);
        getController().getState().getBoard().removeOnBoardRefilledListener(oldView);
        getController().getState().getBoard().setOnBoardRefilledListener(view);
        getController().getState().getBoard().removeOnBoardUpdatedListener(oldView);
        getController().getState().getBoard().setOnBoardUpdatedListener(view);
        getController().getState().removeOnChangedCommonGoalAvailableScoreListener(oldView);
        getController().getState().setOnChangedCommonGoalAvailableScoreListener(view);
        getController().getState().removeOnCurrentPlayerChangedListener(oldView);
        getController().getState().setOnCurrentPlayerChangedListener(view);
        getController().getState().removeLastPlayerUpdatedListeners(oldView);
        getController().getState().setLastPlayerUpdatedListener(view);
        getController().getState().removeMessageSentListener(oldView);
        getController().getState().setMessageSentListener(view);
        getController().getState().removeStateChangedListener(oldView);
        getController().getState().setStateChangedListener(view);
        getController().getState().removeOnExceptionsListener(oldView);
        getController().getState().setOnExceptionsListener(view);
        getController().getState().removeOnWinnerChangedListener(oldView);
        getController().getState().setOnWinnerChangedListener(view);

        for(Player p : getController().getState().getPlayers()){
            p.getBookShelf().removeOnBookShelfUpdated(oldView);
            p.getBookShelf().setOnBookShelfUpdated(view);
        }
        for(Player p : getController().getState().getPlayers()){
            //System.out.println("...Register Listeners on "+p.getNickName() + " of "+ getController().getState().getPlayerFromView(oldView).getNickName());
            p.removeOnPlayerStateChangedListener(oldView);
            p.setOnPlayerStateChangedListener(view);
        }
        for(Player p : getController().getState().getPlayers()){
            p.getPointPlayer().removeOnPointsUpdatedListener(oldView);
            p.getPointPlayer().setOnPointsUpdatedListener(view);
        }
        //getController().getState().getPlayers().forEach(p -> p.getBookShelf().removeOnBookShelfUpdated(oldView));
        //getController().getState().getPlayers().forEach(p-> p.getBookShelf().setOnBookShelfUpdated(view));
       // getController().getState().getPlayers().forEach(p->p.removeOnPlayerStateChangedListener(oldView));
        //getController().getState().getPlayers().forEach(p->p.setOnPlayerStateChangedListener(view));
        //getController().getState().getPlayers().forEach(p->p.getPointPlayer().removeOnPointsUpdatedListener(oldView));
        //getController().getState().getPlayers().forEach(p->p.getPointPlayer().setOnPointsUpdatedListener(view));
    }

    /**
     * Boolean Method used to verify if all players except
     * <br>
     *  the player passed as parameter are disconnected.
     * <br>
     * @param player to be excepted from the check of disconnection
     * @return True if all players except the one passed as parameter
     * <br>
     * are disconnected and false viceversa
     */
    protected boolean verifyAllDisconnectedPlayer(Player player){

        //Player player = getController().getState().getCurrentPlayer();
        for(Player p: getController().getState().getPlayers()){
            if(p != player && p.getPlayerState() != PlayerState.DISCONNECTED){
                return false;
            }
        }
        //GameState gameState = getController().getState().getGameState();
        //getController().getState().setGameState(GameState.SUSPENDED);
        //getController().setGameManager(new SuspendedGameManager(getController(), gameState));
        return true;
    }
}
