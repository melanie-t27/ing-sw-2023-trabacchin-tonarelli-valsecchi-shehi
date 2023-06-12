package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.utils.Coordinate;
import it.polimi.ingsw.utils.InputCheck;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.TileSubject.*;

public class CommonGoalClassTest {
    TileType[][] matrix;
    CommonGoal commonGoal;

    public static void main(String[] args){
        TileSubject[][] bookShelf = new TileSubject[][]{
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        };
        TileSubject[][] board = new TileSubject[][]{
                {null, null, null, BOOK_COMIC, BOOK_DICTIONARY, null, null, null, null},
                {null, null, null, BOOK_NOTE, CAT_BLACK, CAT_GRAY, null, null, null},
                {null, null, CAT_ORANGE, FRAME_DEGREE, FRAME_LOVE, FRAME_MEMORIES, GAME_CHESS, null, null},
                {null, GAME_MONOPOLY, GAME_RISIKO, PLANT_BASIL, PLANT_GREEN, PLANT_MONSTERA, TROPHY_CHAMPION, null, null},
                {BOOK_COMIC, BOOK_DICTIONARY, BOOK_NOTE, CAT_BLACK, CAT_GRAY, CAT_ORANGE, FRAME_DEGREE, null, null},
                {GAME_CHESS, GAME_MONOPOLY, GAME_RISIKO, PLANT_BASIL, PLANT_GREEN, PLANT_MONSTERA, TROPHY_CHAMPION, null, null},
                {null, null, TROPHY_GYM, TROPHY_MUSIC, BOOK_COMIC, BOOK_DICTIONARY, BOOK_NOTE, null, null},
                {null, null, null, CAT_BLACK, CAT_GRAY, CAT_ORANGE, null, null, null},
                {null, null, null, null, FRAME_DEGREE, FRAME_LOVE, null, null, null}
        };
        System.out.println(InputCheck.findIndexActiveAfterOneChosenTile(board, new Coordinate(5, 0), bookShelf));
        System.out.println(InputCheck.findIndexActiveAfterTwoChosenTiles(board, new Coordinate(5, 0), new Coordinate(5, 1), bookShelf));
    }

    /**
     *
     * @apiNote Valid combination of the parameters values are the following :
     * <ul>
     *     <li>groupsNumber = 6, adjacentTilesPo2 = 1, square = false, separate = true, sameTypeOnly = false</li>
     *     <li>groupsNumber = 4, adjacentTilesPo2 = 2, square = false, separate = true, sameTypeOnly = false</li>
     *     <li>groupsNumber = 2, adjacentTilesPo2 = 2, square = true, separate = true, sameTypeOnly = true</li>
     *     <li>groupsNumber = 8, adjacentTilesPo2 = 0, square = true, separate = false, sameTypeOnly = true</li>
     * </ul>
     */
    private void createGroupCommonGoal(){
        int  groupsNumber = 4;
        int adjacentTilesPo2 = 2;
        boolean square = false;
        boolean separate = true;
        boolean sameTypeOnly = false;
        this.commonGoal = new TupleCommonGoal("Description", groupsNumber, adjacentTilesPo2, square,separate, sameTypeOnly);
    }

    private void createGroupDesign(){
        // 6 groups of 2 tiles each
        // 4 groups of 4 tiles each
        matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, TileType.GAME, TileType.FRAME},
                {TileType.GAME, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.FRAME},
                {TileType.GAME, TileType.TROPHY, TileType.TROPHY, TileType.TROPHY, TileType.FRAME},
                {TileType.CAT, TileType.CAT, TileType.PLANT, TileType.TROPHY, TileType.FRAME},
                {TileType.BOOK, TileType.FRAME, TileType.FRAME, TileType.CAT, TileType.CAT},
                {TileType.BOOK, TileType.BOOK, TileType.BOOK, TileType.PLANT, TileType.PLANT}
        };

        // 4 groups of 4 tiles each; 2 squares group of dim=2; 8 tiles of one type
        /* matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, null, TileType.PLANT},
                {TileType.PLANT, TileType.CAT, TileType.CAT, TileType.PLANT, TileType.PLANT},
                {TileType.PLANT, TileType.FRAME, TileType.BOOK, TileType.PLANT, TileType.FRAME},
                {TileType.BOOK, TileType.BOOK, TileType.FRAME, TileType.CAT, TileType.GAME},
                {TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.PLANT, TileType.PLANT},
                {TileType.PLANT, TileType.PLANT, TileType.BOOK, TileType.PLANT, TileType.PLANT}
        }; */

        // 8 tiles of one type not verified, 2 squares group of dim=2 not verified,
        // 6 groups of 2 tiles each not verified
        /* matrix = new TileType[][]{
                {null, null , null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.FRAME, TileType.PLANT},
                {TileType.PLANT, TileType.PLANT, TileType.BOOK, TileType.PLANT, TileType.PLANT}
        }; */
    }

    public TileType[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(TileType[][] matrix) {
        this.matrix = matrix;
    }

    public CommonGoal getCommonGoal() {
        return commonGoal;
    }

    public void setCommonGoal(CommonGoal commonGoal) {
        this.commonGoal = commonGoal;
    }

    private void createStairDesign(){
        // verified
      /* matrix = new TileType[][]{
                {null,  null, null, null, null},
                {TileType.PLANT, null, null, null, null},
                {TileType.CAT, TileType.CAT, null, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, null, null},
                {TileType.CAT, TileType.CAT, TileType.PLANT, TileType.CAT, null},
                {TileType.CAT, TileType.FRAME, TileType.CAT, TileType.CAT, TileType.CAT}
        };*/


        // verified
       /*matrix = new TileType[][]{
                {null,  null, null, null, TileType.CAT},
                {null, null, null, TileType.CAT, TileType.CAT},
                {null, null, TileType.CAT, TileType.CAT, TileType.CAT},
                {null, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT}
        };*/

        //verified, 3
       /* matrix = new TileType[][]{
                {TileType.CAT,  null, null, null, null},
                {TileType.CAT, TileType.CAT, null, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT}
        };*/

        //4
        /*matrix = new TileType[][]{
                {null,  null, null, null, null},
                {null, TileType.CAT, null, null, null},
                {null, TileType.CAT, null, TileType.CAT, TileType.CAT},
                {null, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {null, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT}
        };*/

        // 5
       matrix = new TileType[][]{
                {null,  null, null, null, null},
                {null, TileType.CAT, null, null, null},
                {null, TileType.CAT, null, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, /*TileType.CAT*/ null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, /*TileType.CAT*/ null, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, /*TileType.CAT*/ null, TileType.CAT}
        };

       //6
       /*matrix = new TileType[][]{
                {null,  null, null, null, null},
                {TileType.CAT, TileType.CAT, null, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, null, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, null},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT}
        };*/

    }

    private void createLineDesign(){
        //number = 1
        matrix = new TileType[][]{
                {TileType.CAT,  TileType.CAT, TileType.CAT, TileType.CAT, TileType.PLANT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.TROPHY, TileType.CAT, TileType.BOOK},
                {TileType.TROPHY, TileType.PLANT, TileType.FRAME, TileType.CAT, TileType.GAME}, //NO
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.BOOK, TileType.CAT},
                {TileType.GAME, TileType.FRAME, TileType.PLANT, TileType.BOOK, TileType.CAT} //NO
        };

        // number = 2
      /*matrix = new TileType[][]{
                {null,TileType.CAT , null, TileType.CAT,TileType.CAT},
                {null, TileType.CAT, null, TileType.CAT ,  TileType.CAT},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.GAME},
                {TileType.PLANT, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.GAME},
                {TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT, TileType.CAT},
                {TileType.GAME, TileType.GAME, TileType.PLANT, TileType.BOOK, TileType.GAME}
        };*/

        //number = 3
       /*matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, null, null},
                {TileType.TROPHY, TileType.CAT, TileType.CAT, null, null},
                {TileType.BOOK, TileType.FRAME, TileType.TROPHY, null, null},
                {TileType.CAT, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.GAME},
                {TileType.FRAME, TileType.CAT, TileType.FRAME, TileType.CAT, TileType.CAT},
                {TileType.GAME, TileType.GAME, TileType.BOOK, TileType.BOOK, TileType.CAT}
        }; */

    }

    private void createShapeDesign(){
        // edges design verified
        matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, null, TileType.PLANT},
                {TileType.TROPHY, TileType.CAT, TileType.CAT, null, TileType.GAME},
                {TileType.BOOK, TileType.FRAME, TileType.TROPHY, null, TileType.FRAME},
                {TileType.CAT, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.GAME},
                {TileType.FRAME, TileType.CAT, TileType.FRAME, TileType.CAT, TileType.CAT},
                {TileType.PLANT, TileType.GAME, TileType.BOOK, TileType.BOOK, TileType.PLANT}
        };

        // edges design not verified and diagonal design verified
        /*matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, null, TileType.GAME},
                {TileType.PLANT, TileType.CAT, TileType.CAT, null, TileType.GAME},
                {TileType.BOOK, TileType.PLANT, TileType.TROPHY, null, TileType.FRAME},
                {TileType.CAT, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.GAME},
                {TileType.FRAME, TileType.CAT, TileType.FRAME, TileType.PLANT, TileType.CAT},
                {TileType.PLANT, TileType.GAME, TileType.BOOK, TileType.BOOK, TileType.PLANT}
        }; */

        // edges design not verified and x design not verified
        /*matrix = new TileType[][]{
                {null,  TileType.PLANT, TileType.GAME, null, null},
                {TileType.TROPHY, TileType.CAT, TileType.CAT, null, TileType.GAME},
                {TileType.BOOK, TileType.FRAME, TileType.TROPHY, null, TileType.FRAME},
                {TileType.CAT, TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.GAME},
                {TileType.FRAME, TileType.CAT, TileType.FRAME, TileType.CAT, TileType.CAT},
                {TileType.PLANT, TileType.GAME, TileType.BOOK, TileType.BOOK, TileType.PLANT}
        }; */

        // x design verified and diagonal design not verified
        /*matrix = new TileType[][]{
                {null,  TileType.PLANT, TileType.GAME, null, null},
                {TileType.TROPHY, TileType.CAT, TileType.CAT, null, TileType.GAME},
                {TileType.BOOK, TileType.TROPHY, TileType.TROPHY, null, TileType.FRAME},
                {TileType.CAT, TileType.PLANT, TileType.TROPHY, TileType.CAT, TileType.GAME},
                {TileType.FRAME, TileType.CAT, TileType.FRAME, TileType.TROPHY, TileType.CAT},
                {TileType.CAT, TileType.GAME, TileType.CAT, TileType.BOOK, TileType.PLANT}
        }; */

    }



    private void createSquareDesign(){
        // 4 groups of 4 tiles each; 2 squares group of dim=2; 8 tiles of one type
        matrix = new TileType[][]{
                {TileType.PLANT,  TileType.PLANT, TileType.GAME, null, TileType.PLANT},
                {TileType.PLANT, TileType.CAT, TileType.CAT, TileType.PLANT, TileType.PLANT},
                {TileType.PLANT, TileType.FRAME, TileType.BOOK, TileType.PLANT, TileType.FRAME},
                {TileType.BOOK, TileType.BOOK, TileType.FRAME, TileType.CAT, TileType.GAME},
                {TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.PLANT, TileType.PLANT},
                {TileType.PLANT, TileType.PLANT, TileType.BOOK, TileType.PLANT, TileType.PLANT}
        };

        // 8 tiles of one type not verified, 2 squares group of dim=2 not verified,
        // 6 groups of 2 tiles each not verified
        /* matrix = new TileType[][]{
                {null, null , null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {TileType.PLANT, TileType.PLANT, TileType.CAT, TileType.FRAME, TileType.PLANT},
                {TileType.PLANT, TileType.PLANT, TileType.BOOK, TileType.PLANT, TileType.PLANT}
        }; */
    }

    private void createStairCommonGoal(){
        int numberOfColumns = 5;
        this.commonGoal = new StairCommonGoal(numberOfColumns);
    }


    private void createShapeCommonGoal(){
        List<Integer[]> incrementRuleShape = new ArrayList<Integer[]>();
        this.commonGoal = new ShapeCommonGoal(incrementRuleShape);
    }

    private void createSquareCommonGoal(){
        int groupsNumber = 3;
        int squareDim = 2;
        //this.commonGoal = new SquareCommonGoal("Description", groupsNumber, squareDim , true);
    }

    private void createLineCommonGoal(){
        int incRow = 1; // 1 to test rows or 0 to test columns
        int incCol = 1; // 1 to test columns or 0 to test rows
        int linesNumber = 4; //number of column or rows to test
        int numberOfTiles = 5;
        int[] differentTiles = {1};  //number of different tile types {1,2,3} or {5}/{6}
        this.commonGoal = new LineCommonGoal(incRow, incCol, linesNumber, numberOfTiles, differentTiles);
    }


    private void printResult(List<EntryPatternGoal> list){

        for(int i = 0; i < list.size(); i++){
            System.out.println("result.add(new EntryPatternGoal("+ list.get(i).getRow()+","+list.get(i).getColumn()+", TileType."+ list.get(i).getTileType().toString()+"));");
        }
        //System.out.println(list.toString());


        TileType[][] matrix = new TileType[6][5];
        int[][] index = new int[6][5];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 5; j++){
                matrix[i][j] = null;
                index[i][j] = 0;
            }
        }

        for (EntryPatternGoal element : list) {
            matrix[element.getRow()][element.getColumn()] = element.getTileType();
            index[element.getRow()][element.getColumn()] = list.indexOf(element)+1;
        }

        System.out.print("\t");
        for (int i = 0; i < 5; i++){
            System.out.print(i+ "\t\t");
        }
        System.out.println();
        for (int i = 0; i < 6; i++){
            System.out.print(i+ "\t");
            for (int j = 0; j < 5; j++){
                if (matrix[i][j]==null) System.out.print("----\t");
                else {
                    System.out.print( index[i][j] +"."+ matrix[i][j]  + "\t");

                }
            }
            System.out.println();
        }
    }
}
