package it.polimi.ingsw.controller.exceptions;

public class AlreadyInGameException extends RuntimeException{
    private static final String DEF_MESSAGE = "You are already in a game and you need to leave first to create or join a new game.";

    public AlreadyInGameException(){
        super(DEF_MESSAGE);
    }

    public AlreadyInGameException(String message){
        super(message);
    }

    public AlreadyInGameException(String message, Throwable cause){
        super(message, cause);
    }

    public AlreadyInGameException(Throwable cause){
        super(DEF_MESSAGE, cause);
    }
}
