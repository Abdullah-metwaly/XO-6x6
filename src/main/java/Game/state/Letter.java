package Game.state;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing the cells with X or O in the board game and whether its on the board or not.
 */
public class Letter {

    /**
     *  fields of the stone class.
     *  position field represents the position  of the x or O in the board
     *  Status represents whether the cell has O or X

     */

    @Setter
    @Getter
    private int col;
    @Setter
    @Getter
    private int row;
    @Setter
    @Getter
    private int Status;



    public Letter(int row, int col) {
        this.row = row;
        this.col=col;
        this.Status = 0;
    }




    public String toString() {

      if(this.Status==0){
          return "EMPTY";
      }else if (this.Status ==2){
          return "O";
      }
      else {
          return "X";
          }
    }
}
