/**
 * @author Barış Pamuksuz
 * ID: 22202238
 * 03.10.2023
 * Lab 1 assignment
 */
import java.util.Scanner;


public class Game {
    /**
     * Checks whether the last cell contains any symbol, if so it returns true.
     * @param lastCell
     * @return
     */
    public static boolean didGameFinish(Cell lastCell){
        boolean isFinished = false;
        if( lastCell.getTopRight() != ' ' || lastCell.getBottomRight() != ' ' || lastCell.getTopLeft() != ' '|| lastCell.getBottomLeft() != ' ' ){
            isFinished = true;
            
        }
        return isFinished;
    }
    
    
    
    public static void main(String[] args) {
        
        int width;
        int height;
        int numberOfPlayers;
        
        boolean isAgain = true;
        Scanner input = new Scanner(System.in);
        
        while(isAgain == true){
        System.out.println("Welcome to the board game.");
        System.out.print("Please enter board width: ");
        width = input.nextInt();
        System.out.print("Please enter board height:");
        height = input.nextInt();
        System.out.print("How many players? (2-4) ");
        numberOfPlayers = input.nextInt();
        System.out.println("Please enter a character (symbol) for each player.");

        Player[] players = new Player[numberOfPlayers];
        
        
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.print("For player " + (i+1) + ": ");
            String in = input.next();
            char symbol = in.charAt(0);
            players[i] = new Player(symbol);
        }
        System.out.println("Players are rolling dice.");
        for(int i = 0; i < numberOfPlayers; i++){
            int value = players[i].getFirstValue();
            System.out.printf( "%c: %d  ", players[i].getSymbol(), value);
        }
        System.out.println();
        for(int k = 0; k < players.length; k++){
            for(int m = 0; m < players.length - k - 1; m++){
                if(players[m].getFirstValue() < players[m+1].getFirstValue()){
                    Player temp = players[m];
                    players[m] = players[m+1];
                    players[m+1] = temp;
                }
            }
        }
        for(int u = 0; u <players.length-1; u++){
            if(players[u].getFirstValue() == players[u+1].getFirstValue()){
                players[u].tieBreaker(players[u+1], players);
            }
        }
        System.out.print("Playing order is: ");
        for(int k = 0; k < players.length; k++){
                System.out.print(players[k].getSymbol() + " ");
        }
        System.out.println();

        Board board = new Board();
        
        char[][] Rows = board.createRows(height, width);
        Cell[] cells = board.implementCellsOnBoard(height, width);
        board.setTrapToRandomCells(cells, height, width);
        board.setStartPosition(players);
        

        boolean endFlag = didGameFinish(cells[cells.length-1]);
        while(endFlag == false){
            for(int i = 0; i < players.length; i++){
                if(isAgain == true && !didGameFinish(cells[cells.length-1])){
                    board.displayCurrentBoard(Rows);
        
        
                    int value = players[i].setValue();
                    int choice = players[i].getChoiceForPlayer(value);
        
                    players[i].movePlayer(choice, players, cells, height, width);
                    
                }
            }
            if(didGameFinish(cells[cells.length-1])){
                endFlag = true;
                System.out.printf("Winner is %c, congratulations !\n", cells[cells.length-1].getSymbol());
                System.out.println();
                System.out.println("Player   Move   Trap");
                for(int k = 0; k < players.length; k++){
                    System.out.printf("%c        %d\t%d\n", players[k].getSymbol(), players[k].playerTotalMoves, players[k].playerTotalTraps); 
                }
                
                System.out.print("Play Again? (Y/N): ");
                String aNswer = input.next();
                char answer = aNswer.charAt(0);
                if(answer == 'Y' || answer == 'y'){
                    isAgain = true;
                }
                else{
                    isAgain = false;
                }
            }


        }
        }
    
    System.out.println("Bye...");   
    }
    


}

