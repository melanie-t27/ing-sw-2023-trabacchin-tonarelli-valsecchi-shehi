package it.polimi.ingsw.view.gui.customcomponents.guitoolkit;

import it.polimi.ingsw.model.TileSubject;
import it.polimi.ingsw.utils.Coordinate;
import it.polimi.ingsw.view.gui.customcomponents.tileview.TileSubjectView;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MyShelfieMatrixComponent {

    private MyShelfieMatrixComponent() {
    }

    public static TileSubject[][] toTileSubjectMatrix(Map<Coordinate, StackPane> tileSubjectViewMatrix) {

        if(tileSubjectViewMatrix == null)
            return null;

        TileSubject[][] tileMatrix = new TileSubject[getMaxBoardRow(tileSubjectViewMatrix)][getMaxBoardColumn(tileSubjectViewMatrix)];

        getMatrixState(tileSubjectViewMatrix).forEach((coordinate, tile) -> {
            if(tile != null)
                tileMatrix[coordinate.getX()][coordinate.getY()] = tile.getTileSubject();
        });

        return tileMatrix;
    }

    /**
     * Retrieves the maximum row coordinate inside the tile subject map
     *
     * @return max row coordinate
     */
    static int getMaxBoardRow(@NotNull Map<Coordinate, StackPane> tileSubjectViewMatrix) {
        int maxRow = 0;

        for (Coordinate coordinate : tileSubjectViewMatrix.keySet()) {
            if (coordinate.hasGraterRow(maxRow)) {
                maxRow = coordinate.getX();
            }
        }

        return (maxRow + 1);
    }

    /**
     * Retrieves the maximum column coordinate inside the tile subject map
     *
     * @return max column coordinate
     */
    static int getMaxBoardColumn(@NotNull Map<Coordinate, StackPane> tileSubjectViewMatrix) {
        int maxColumn = 0;

        for (Coordinate coordinate : tileSubjectViewMatrix.keySet()) {
            if (coordinate.hasGraterColumn(maxColumn)) {
                maxColumn = coordinate.getY();
            }
        }

        return (maxColumn + 1);
    }

    @NotNull
    public static Map<Coordinate, TileSubjectView> getMatrixState(@NotNull Map<Coordinate, StackPane> tileSubjectViewMatrix) {
        Map<Coordinate, TileSubjectView> boardState = new HashMap<>();

        tileSubjectViewMatrix.forEach((coordinate, parent) -> {
            try {
                TileSubjectView tileInBox = (TileSubjectView) parent.getChildren().stream().findFirst().orElse(null);
                boardState.put(coordinate, tileInBox);
            } catch (ClassCastException e) {
                //The box on the board or on the bookshelf does not contain a Tile
                MyShelfieAlertCreator.displayErrorAlert(e);
            }
        });

        return boardState;
    }
}