package model;

import java.io.Serializable;

public enum CommonGoal implements Serializable {
    //nomi da assegnare in modo più significativo
    COMMON_GOAL1,
    COMMON_GOAL2,
    COMMON_GOAL3,
    COMMON_GOAL4,
    COMMON_GOAL5,
    COMMON_GOAL6,
    COMMON_GOAL7,
    COMMON_GOAL8,
    COMMON_GOAL9,
    COMMON_GOAL10,
    COMMON_GOAL11,
    COMMON_GOAL12;

    private static final long serialVersionUID = 285236373L;
    //attributo per memorizzare il punteggio assegnato dal goal
    private int availableScore;
    //descrizione della common goal
    private String description;

    public int getAvailableScore() {
        return availableScore;
    }

    public void setAvailableScore( int availableScore ) {
        this.availableScore = availableScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
