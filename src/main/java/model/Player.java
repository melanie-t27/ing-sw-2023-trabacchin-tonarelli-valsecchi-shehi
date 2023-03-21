package model;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 97354642643274L;
    private final String nickName;
    private final PersonalGoal personalGoal;
    private int score;

    public Player( String  nickName, PersonalGoal personalGoal ){
        this.nickName = nickName;
        this.personalGoal = personalGoal;
        this.score = 0;
    }

    public String getNickName(){
        return nickName;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
