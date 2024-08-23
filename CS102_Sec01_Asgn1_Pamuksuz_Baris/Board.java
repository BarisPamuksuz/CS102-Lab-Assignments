/**
 * @author Barış Pamuksuz
 * ID: 22202238
 * 03.10.2023
 * Lab 1 assignment
 */

import java.util.Random;

public class Board {
    
    Random rand = new Random();
    char[][] Rows;
    Cell[] cells;
    int cellNumber;
    /**
     * By taking rows containing dynamic arrays(changes as symbols move), it creates a board. 
     * @param rows
     */
    public void displayCurrentBoard(char [][] rows){
        int height = rows.length/2;
        
        int r = 0;
        for(int i = 0; i < 3*height+1; i++){
            if(i % 3 == 0){
                for(int k = 0; k < rows[0].length; k++){
                    System.out.print('#');
                }
                System.out.println();
            }
            else{
                
                arrayToString1D(rows[r]);
                r++;
            }
        }    
    }
    /**
     * It deploys traps to random cells excluding corner cells and finish cell.
     * @param cells
     * @param height
     * @param width
     */
    public void setTrapToRandomCells(Cell[] cells, int height, int width){   
        for(int i = 0; i < cells.length; i++){
            if(i == 0 || i == width-1 || i == width+height-2 || i == cells.length-1 || i == 2*width+height-3){
                cells[i].hasTrap = false;
            }
            else{
                int random = rand.nextInt(3);
                if(random == 1){
                    cells[i].setTrap();
                }
            }
        }
    }
    /**
     * Finds the number of cells 
     * @param height
     * @param width
     * @return
     */
    public int getCellNumber(int height, int width){
        int cEllNumber = 2*(height + width - 2);
        this.cellNumber = cEllNumber;
        return this.cellNumber;
    }

    public char[][] getRowsArrayOfBoard(){
        return Rows;
    }
    /**
     * Create cells respectively(first line- end column- down line- start column) according to height and width, and add them to 
     * Cell[] array.
     * @param height
     * @param width
     * @return
     */
    public Cell[] implementCellsOnBoard(int height, int width){
        
        int n = 1;
        Cell[] cellArray = new Cell[this.getCellNumber(height, width)];

        for(int h = 0; h < width; h++){
            Cell c = new Cell(Rows,0,(3*n-2),0,(3*n-1),1,(3*n-2),1,(3*n-1));
            cellArray[n-1] = c;
            n++;
        }

        for(int y = 2; y < 2*height-2; y+=2){
            Cell e = new Cell(Rows,y,(3*width-2), y,(3*width-1),(y+1),(3*width-2),y+1,(3*width-1));
            cellArray[n-1] = e;
            n++;
        }

        for(int i = 0; i < width; i++){
            Cell l = new Cell(Rows,2*height-2,(3*width-2-3*i),(2*height-2),(3*width-1-3*i),(2*height-1),(3*width-2-3*i), (2*height-1),(3*width-1-3*i));
            cellArray[n-1] = l;
            n++;
        }

        for(int y = 2*height-4; y > 1; y-=2){
            Cell l = new Cell(Rows,y,1,y,2,(y+1),1, (y+1),2);
            cellArray[n-1] = l;
            n++;
        }

        cells = cellArray;
        return cells;
    }
    /**
     * Creates arrays based on height and width, also specific indexes.
     * @param height
     * @param width
     * @return
     */
    public char[][] createRows( int height, int width){

        char[][] rows = new char[2*height][3*width+1];
        for(int i = 0; i < 2*height; i++){
            for(int m = 0; m < 3*width+1; m++){
                if(i == 0 || i == 1 || i == 2*height-1 || i == 2*height-2){
                    if( m % 3 == 0){
                        rows[i][m] = '#';
                    }
                    else{
                        rows[i][m] = ' ';
                    }
                }
                else{
                    rows[i][m] = ' ';
                    if( m == 0 || m == 3 || m == 3*width || m == 3*width-3){
                        rows[i][m] = '#';
                    }
                }
            }
        }
        Rows = rows;
        return Rows;
    }
    /**
     * Prints 2D array
     * @param arr
     */
    public static void arrayToString2D(char[][] arr){
        for (int r = 0; r < arr.length; r++) {
            for (int m = 0; m < arr[r].length; m++) {
                System.out.print(arr[r][m]);
            }
            System.out.println();
        }
        
    }

    /**
     * Prints 1D array
     * @param arr
     */
    public static void arrayToString1D(char[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    /**
     * Sets start position of symbols. Since every symbol gets unique position to start.
     * @param players
     */
    public void setStartPosition(Player[] players){
       
        if(players.length == 2){
            cells[0].setTopLeft(players[0].getSymbol());
            cells[0].setTopRight(players[1].getSymbol());
        }
        if(players.length == 3){
            cells[0].setTopLeft(players[0].getSymbol());
            cells[0].setTopRight(players[1].getSymbol());
            cells[0].setBottomLeft(players[2].getSymbol());
        }
        if(players.length == 4){
            cells[0].setTopLeft(players[0].getSymbol());
            cells[0].setTopRight(players[1].getSymbol());
            cells[0].setBottomLeft(players[2].getSymbol());
            cells[0].setBottomRight(players[3].getSymbol());
        }   
    } 
}
