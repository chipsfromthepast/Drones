package drone;

import java.util.Arrays;

public class ConsoleCanvas {
	//initializes necessary variables for this class
    private int row;
    private int col ;
    char [][] box;

    public ConsoleCanvas(){
      new ConsoleCanvas(10,20);
    }

    public ConsoleCanvas(int row, int col) {
        this.row = row;
        this.col = col;
        box = new char[row][col];
        for (int i = 0; i < row; i++) { //Rows
            for (int j = 0; j < col; j++) { //Columns
                if (i == 0 || i == row-1 || j == 0 || j == col-1){
                    //System.out.print("#");
                    box[i][j] = '#';
                } else{
                   // System.out.print(" ");
                    box[i][j] = ' ';
                }
            }
            //System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas(100, 20);
        c.showIt(4,3,"d");
        System.out.println(c.toString());
    }

    void showIt(int x, int y, String d) {
        if (x != 0 || x != row-1 || y != 0 || y != col-1){
            box[x][y] = 'd';
        }
    }

    public String toString() {
        String map ="";
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                map += box[i][j];
            }
            map += "\n";
        }
        return map;
    }
}
