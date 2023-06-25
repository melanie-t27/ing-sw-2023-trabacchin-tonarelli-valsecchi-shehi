package it.polimi.ingsw.view.tui_alternative.input.draginput;

import it.polimi.ingsw.utils.Coordinate;
import it.polimi.ingsw.view.tui_alternative.TUI;
import it.polimi.ingsw.view.tui_alternative.input.Input;
import it.polimi.ingsw.view.tui_alternative.input.InputSelector;

import java.io.PrintStream;
import java.util.List;

public class AskColumn extends Input {

    private final List<Coordinate> coordinates;
    public AskColumn(TUI tui, PrintStream out, List<Coordinate> coordinates) {
        super(tui, out);
        this.coordinates = coordinates;
        getTUI().setInputInProgress(true);
    }

    @Override
    public void printPrompt() {
        getOut().println("Now enter the number, between 1 and 5, of the column of your bookshelf where to insert the chosen tiles:");
    }

    @Override
    public void readLine(String line) {
        if(!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4") || line.equals("5")))  {
            getOut().println("The column number is invalid, please try again");
        }
        else {
            getTUI().setCurrentInput(new InputSelector(getTUI(), getOut()));
            getTUI().getLogicController().dragTilesToBookShelf(coordinates, Integer.parseInt(line));
            getTUI().refresh();
        }
    }
}