/**
 * @author Barış Pamuksuz
 * ID: 22202238
 * 03.10.2023
 * Lab 1 assignment
 */

import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    Random rand = new Random();
    private int playerDiceValue = rand.nextInt(6) + 1;
    private char playerSymbol;
    int playerTotalMoves = 0;
    int playerTotalTraps = 0;
    int j = 0;
    int k = 0;
    int t = 0;
    int m = 0;
    public int getFirstValue(){
        
        return playerDiceValue;
    }
    /**
     * Gets random dice value.
     * @return
     */
    public int setValue(){
        int newValue = rand.nextInt(6) + 1;
        return newValue;
    }
    /**
     * Gets choice between 0 and dice value (both included). If a number out of rules chosen, value will
     * be assigned to max possible value. Message will also display.
     * @param diceValue
     * @return
     */
    public int getChoiceForPlayer(int diceValue){
        System.out.printf("Player %c rolls %d, how many cells you move? (0-%d) ",this.playerSymbol, diceValue, diceValue);
        int choice = input.nextInt();
        if(choice > diceValue || choice < 0){
            System.out.println("Invalid choice - Max value will be assigned.");
            choice = diceValue;
        }
        playerTotalMoves += choice;
        return choice;
    
    }
    /**
     * gets player symbol.
     * @return
     */
    public char getSymbol(){
        return playerSymbol;
    }

    public Player(char symbol){
        playerSymbol = symbol;
    }
    /**
     * Breaks tie between 2 players posing equal dice values.
     * This break will operate randomly.
     * @param p
     * @param arr
     * @return
     */
    public Player[] tieBreaker(Player p, Player[] arr){
        System.out.printf("Breaking tie for %c %c\n", this.getSymbol(), p.getSymbol());
        int randomChoice = rand.nextInt(2);
        if(randomChoice == 1){
            int indexOfThis = findIndexOfPlayer(this, arr);
            int indexOfOther = findIndexOfPlayer(p, arr);
            arr[indexOfOther] = this;
            arr[indexOfThis] = p;
            return arr;
        }
        else{
            return arr;
        }

    }
    /**
     * Finds index of player in players array
     * @param player
     * @param arr
     * @return
     */
    public static int findIndexOfPlayer( Player player, Player[] arr){
        int index = -1;
        for(int m = 0; m < arr.length; m++){
                if(arr[m].equals(player))
                    index = m;
        }
        return index;
    }
    
    /**
     * Moves player on board according to choice. If player got trapped, player moves back to corner cell and trap 
     * in that cell will become inactive. If player choose a number that exceeds final cell, overlaped message will 
     * be displayed and the player will win. 
     * 
     * @param choice
     * @param players
     * @param cells
     * @param height
     * @param width
     */
    public void movePlayer(int choice, Player[] players, Cell[] cells, int height, int width) {
        if (choice != 0) {
            if (players.length == 2) {
                if (players[0] == this) {
                    if(m + choice < cells.length){
                        if (cells[m + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(m + choice, height, width);
                            cells[i].setTopLeft(playerSymbol);
                            cells[m + choice].removeTrap();
                            if (m != i) {
                                cells[m].setTopLeft(' ');
                                m = i;
                            }
                        }   
                            else {
                                cells[m + choice].setTopLeft(playerSymbol);
                                cells[m].setTopLeft(' ');
                                m += choice;
                            }
                    }
                    else{
                    System.out.println("You overlapped the board! ");
                    cells[cells.length-1].setTopLeft(playerSymbol);
                    }
                }
                
                if (players[1] == this) {
                    if( k + choice < cells.length){
                        if (cells[k + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(k + choice, height, width);
                            cells[i].setTopRight(playerSymbol);
                            cells[k + choice].removeTrap();
                            if (k != i) {
                                cells[k].setTopRight(' ');
                                k = i;
                            }
                        } else {
                            cells[k + choice].setTopRight(playerSymbol);
                            cells[k].setTopRight(' ');
                            k += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setTopRight(playerSymbol);
                    }
                }
            }
            if (players.length == 3) {
                    if (players[0] == this) {
                        if(m + choice < cells.length){
                            if (cells[m + choice].hasTrap) {
                                playerTotalTraps++;
                                System.out.println("You moved into a trap!");
                                System.out.println("You moved back to the closest corner!");
                                int i = findIndexOfClosestCell(m + choice, height, width);
                                cells[i].setTopLeft(playerSymbol);
                                cells[m + choice].removeTrap();
                                if (m != i) {
                                    cells[m].setTopLeft(' ');
                                    m = i;
                                }
                            }else{
                                cells[m + choice].setTopLeft(playerSymbol);
                                cells[m].setTopLeft(' ');
                                m += choice;
                                }
                        }
                        else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setTopLeft(playerSymbol);
                    }
                }
                    
                if (players[1] == this) {
                    if( k + choice < cells.length){
                        if (cells[k + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(k + choice, height, width);
                            cells[i].setTopRight(playerSymbol);
                            cells[k + choice].removeTrap();
                            if (k != i) {
                                cells[k].setTopRight(' ');
                                k = i;
                            }
                        } 
                        else {
                            cells[k + choice].setTopRight(playerSymbol);
                            cells[k].setTopRight(' ');
                            k += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setTopRight(playerSymbol);
                    }
                }
                if (players[2] == this) {
                    if(j + choice < cells.length){
                        if (cells[j + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(j + choice, height, width);
                            cells[i].setBottomLeft(playerSymbol);
                            cells[j + choice].removeTrap();
                            if (j != i) {
                                cells[j].setBottomLeft(' ');
                                j = i;
                            }
                        } 
                        else {
                            cells[j + choice].setBottomLeft(playerSymbol);
                            cells[j].setBottomLeft(' ');
                            j += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setBottomLeft(playerSymbol);
                    }
                }
            }
            if (players.length == 4) {
                if (players[0] == this) {
                    if(m + choice < cells.length){
                        if (cells[m + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(m + choice, height, width);
                            cells[i].setTopLeft(playerSymbol);
                            cells[m + choice].removeTrap();
                            if (m != i) {
                                cells[m].setTopLeft(' ');
                                m = i;
                            }
                        } 
                        else {
                            cells[m + choice].setTopLeft(playerSymbol);
                            cells[m].setTopLeft(' ');
                            m += choice;
                        }
                    }
                    else{
                    System.out.println("You overlapped the board! ");
                    cells[cells.length-1].setTopLeft(playerSymbol);
                    }    
                }
                
                if (players[1] == this) {
                    if( k + choice < cells.length){
                        if (cells[k + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(k + choice, height, width);
                            cells[i].setTopRight(playerSymbol);
                            cells[k + choice].removeTrap();
                            if (k != i) {
                                cells[k].setTopRight(' ');
                                k = i;
                            }
                        } 
                        else {
                            cells[k + choice].setTopRight(playerSymbol);
                            cells[k].setTopRight(' ');
                            k += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setTopRight(playerSymbol);
                    }
                }
                if (players[2] == this) {
                    if(j + choice < cells.length){
                        if (cells[j + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(j + choice, height, width);
                            cells[i].setBottomLeft(playerSymbol);
                            cells[j + choice].removeTrap();
                            if (j != i) {
                                cells[j].setBottomLeft(' ');
                                j = i;
                            }
                        } 
                        else {
                            cells[j + choice].setBottomLeft(playerSymbol);
                            cells[j].setBottomLeft(' ');
                            j += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setBottomLeft(playerSymbol);
                    }
                }
                if (players[3] == this) {
                    if(t+choice < cells.length){   
                        if (cells[t + choice].hasTrap) {
                            playerTotalTraps++;
                            System.out.println("You moved into a trap!");
                            System.out.println("You moved back to the closest corner!");
                            int i = findIndexOfClosestCell(t + choice, height, width);
                            cells[i].setBottomRight(playerSymbol);
                            cells[t + choice].removeTrap();
                            if (t != i) {
                                cells[t].setBottomRight(' ');
                                t = i;
                            }
                        } 
                        else {
                            cells[t + choice].setBottomRight(playerSymbol);
                            cells[t].setBottomRight(' ');
                            t += choice;
                        }
                    }
                    else{
                        System.out.println("You overlapped the board! ");
                        cells[cells.length-1].setBottomRight(playerSymbol);
                    }
                }
            }
        }
    }
    
    /**
     * Finds the index of the closest back cell when player got trapped. These cells are determined as
     * corner cells.
     * @param i
     * @param height
     * @param width
     * @return
     */
    public int findIndexOfClosestCell(int i, int height, int width){

        if( i < width -1){
            return 0;
        }
        else if( i < width + height -2 && i > width -1){
            return width-1;
        }
        else if( i < height + 2*width -3 && i > width + height -2){
            return width + height-2;
        }
        else{
            return height + 2*width - 3 ;
        }
    }
    

    

    

}
