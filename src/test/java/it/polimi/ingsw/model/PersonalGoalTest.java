package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.controller.JSONExclusionStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

class PersonalGoalTest {

    private final static int PATTERN_NUMBER = 10; //number of total patterns
    private final static Random RANDOM = new Random();
    private final String inputFileName = "pattern" + RANDOM.nextInt(1,13);

    @Test
    void getGoalPattern(){
        PersonalGoal personalGoal;
        PersonalGoal personalGoal1;

        try {
            personalGoal = new PersonalGoal("pattern1");
            personalGoal1 = new PersonalGoal("pattern2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            personalGoal = new PersonalGoal();
            personalGoal1 = null;
        }

        assertNotEquals(personalGoal1, personalGoal);

        personalGoal1 = new PersonalGoal(personalGoal);

        assertEquals(personalGoal1.getGoalPattern(), personalGoal.getGoalPattern());
    }

    @Test
    void getScoreMap() {
        PersonalGoal personalGoal;
        PersonalGoal personalGoal1;

        try {
            personalGoal = new PersonalGoal("pattern1");
            personalGoal1 = new PersonalGoal("pattern2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            personalGoal = new PersonalGoal();
            personalGoal1 = null;
        }

        assertNotEquals(personalGoal1, personalGoal);

        personalGoal1 = new PersonalGoal(personalGoal);

        assertEquals(personalGoal1.getScoreMap(), personalGoal.getScoreMap());
    }

    @Test
    void testToString() {
        PersonalGoal personalGoal;

        try {
            personalGoal = new PersonalGoal("pattern1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            personalGoal = new PersonalGoal();
        }

        assertNotNull(personalGoal);

        String excepted = """
                Pattern:{
                \tEntryPatternGoal{row=0, column=0, tileType=PLANT},
                \tEntryPatternGoal{row=0, column=2, tileType=FRAME},
                \tEntryPatternGoal{row=1, column=4, tileType=CAT},
                \tEntryPatternGoal{row=2, column=3, tileType=BOOK},
                \tEntryPatternGoal{row=3, column=1, tileType=GAME},
                \tEntryPatternGoal{row=5, column=2, tileType=TROPHY}
                }
                scoreMap:{6=12, 5=9, 4=6, 3=4, 2=2, 1=1}""";
        assertEquals(personalGoal.toString(), excepted);
    }
}