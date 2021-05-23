package Game.state;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Class representing the state of the puzzle.
 */
@Data
@Slf4j
public class GameState {



    private  Player playerOne =new Player("");

    private  Player playerTwo =new Player("");


    private  Player activePlayer =new Player("");
    int status = 0;


    /**
     * The array representing the initial configuration of the tray.
     */
    public static final int[][] INITIAL = {
            {0, 0, 0,0,0,0},
            {0, 0, 0,0,0,0},
            {0, 0, 0,0,0,0},
            {0, 0, 0,0,0,0},
            {0, 0, 0,0,0,0},
            {0, 0, 0,0,0,0}
    };


    /**
     * The array storing the current configuration of the tray.
     */
    @Setter(AccessLevel.NONE)
    private Letter[][] tray;


    /**
     * Creates a {@code Stone} object representing the (original)
     * initial state of the game.
     * @param playerOne the first player by definition
     * @param PlayerTwo the second player by definition
     */
    public GameState(Player playerOne, Player PlayerTwo) {
        this(INITIAL , playerOne , PlayerTwo);
    }

    /**
     * constructor with one argument for testing purposes
     * Creates a {@code Stone} object representing the (original)
     * initial state of the game.
     *   @param a an array of size 6&#xd7;6 representing the initial configuration
     *       of the tray
     */
    public GameState(int[][] a ) {
        this(a , new Player("john") , new Player("Doe"));
    }

    public GameState() {
        this(INITIAL , new Player("john") , new Player("Doe"));
    }

    /**
     * Creates a {@code StonesGameState} object that is initialized it with
     * the specified array.
     *
     * @param a an array of size 6&#xd7;6 representing the initial configuration
     *          of the tray
     */
    public GameState(int[][] a , Player PlayerOne, Player PlayerTwo) {
        this.playerOne = PlayerOne;
        this.playerTwo=PlayerTwo;
        this.activePlayer=PlayerOne;
        initBoard(a);

    }



    private void initBoard(int[][] a) {
        this.tray = new Letter[6][6];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.tray[i][j] = new Letter(i,j);
                this.tray[i][j].setStatus(a[i][j]);
            }
        }
    }

    /**
     * Checks whether the game is finished.
     *
     * @return {@code true} if the game is finished, {@code false} otherwise
     */
    public boolean isGameFinished(){
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if(this.canPlayerplay(i,j)) return false;
            }
        }
        return true;
    }

    /**
     * Returns whether the cell at the specified position can be chosen or not
     *
     * @param row the row of the X Or O to be put
     * @param col the column of the X Or O to be put
     * @return {@code true} if the  cell a the specified position can be chosen
     * , {@code false} otherwise
     */
    public boolean canPlayerplay(int row, int col) {
        return rowsAndColWithinTheRange(row, col)&& this.tray[row][col].getStatus()==0 &&
               this.ValidCellTobeChosen(row, col);
    }

    public boolean ValidCellTobeChosen(int row, int col){
        ArrayList<Integer[]> Cells = new ArrayList<>();
        ArrayList<Integer[]> afterCells = new ArrayList<>();
        Cells.add(new Integer[]{row+1,col});
        Cells.add(new Integer[]{row-1,col});
        Cells.add(new Integer[]{row,col+1});
        Cells.add(new Integer[]{row,col-1});
        for(Integer[] cell : Cells){
            if (rowsAndColWithinTheRange(cell[0],cell[1]) ){
                if(this.tray[cell[0]][cell[1]].getStatus()==0)  afterCells.add(cell);
            }
        }
        if(afterCells.size()==4) return true;
        if((row ==0 || row==5 || col ==0 || col ==5) && afterCells.size()==3){
            return true;
        } else if(((row == 0 && col == 0) || (row == 0 && col == 5) || (row == 5 && col == 0) || (row == 5 && col == 5)) && afterCells.size() == 2){
            return true;
        }else {
            log.info("Stone at ({},{}) can not be chosen by the player.", row, col);
            return false;
        }
    }

    /**
     * choose a cell at the specified position and occupy it with the associated letter based on the player and its neighbouring cells .
     *
     * @param row the row of the Cell to be Chosen
     * @param col the column of the cell to be choen
     */
    public void Play(int row, int col) {
        ArrayList<Integer[]> Cells = new ArrayList<>();
        if(activePlayer.equals(playerOne)){
          activePlayer=playerTwo;
          status=1;
      } else{
          activePlayer=playerOne;
          status=2;
        }
        Cells.add(new Integer[]{row+1,col});
        Cells.add(new Integer[]{row-1,col});
        Cells.add(new Integer[]{row,col+1});
        Cells.add(new Integer[]{row,col-1});
        Cells.add(new Integer[]{row,col});
        for(Integer[] cell : Cells){
            if (rowsAndColWithinTheRange(cell[0],cell[1]) ){
                this.tray[cell[0]][cell[1]].setStatus(status);
            }
        }
        log.info(String.valueOf(this));
        log.info("Stone at ({},{}) has be chosen by the player.", row, col);
    }

    private boolean rowsAndColWithinTheRange(int row, int col){
        return 0 <= row && row <= 5 && 0 <= col && col <= 5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter[] row : tray) {
            for (Letter letter : row) {
                sb.append(letter).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }


}
