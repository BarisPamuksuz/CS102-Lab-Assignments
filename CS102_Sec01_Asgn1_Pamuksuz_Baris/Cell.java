/**
 * @author Barış Pamuksuz
 * ID: 22202238
 * 03.10.2023
 * Lab 1 assignment
 */

// This class is to create Cell object, and its attributes. 

public class Cell {
    
    boolean hasTrap = false;
    
    public void setTrap(){
        hasTrap = true;
    }
    
    public void removeTrap(){
        hasTrap = false;
    }
    
        private final char[][] Lines;
        private final int topLeftLine;
        private final int topLeftCol;
        private final int topRightLine;
        private final int topRightCol;
        private final int bottomLeftLine;
        private final int bottomLeftCol;
        private final int bottomRightLine;
        private final int bottomRightCol;
    
        public Cell(char[][] Lines, int topLeftLine, int topLeftCol, int topRightLine, int topRightCol, int bottomLeftLine, int bottomLeftCol, int bottomRightLine, int bottomRightCol) {
            this.Lines = Lines;
            this.topLeftLine = topLeftLine;
            this.topLeftCol = topLeftCol;
            this.topRightLine = topRightLine;
            this.topRightCol = topRightCol;
            this.bottomLeftLine = bottomLeftLine;
            this.bottomLeftCol = bottomLeftCol;
            this.bottomRightLine = bottomRightLine;
            this.bottomRightCol = bottomRightCol;
        }
    
        public char getTopLeft() {
            return Lines[topLeftLine][topLeftCol];
        }
    
        public void setTopLeft(char value) {
            Lines[topLeftLine][topLeftCol] = value;
        }
    
        public char getTopRight() {
            return Lines[topRightLine][topRightCol];
        }
    
        public void setTopRight(char value) {
            Lines[topRightLine][topRightCol] = value;
        }
    
        public char getBottomLeft() {
            return Lines[bottomLeftLine][bottomLeftCol];
        }
    
        public void setBottomLeft(char value) {
            Lines[bottomLeftLine][bottomLeftCol] = value;
        }
    
        public char getBottomRight() {
            return Lines[bottomRightLine][bottomRightCol];
        }
    
        public void setBottomRight(char value) {
            Lines[bottomRightLine][bottomRightCol] = value;
        }
        
        
        /**
         * This method gets the symbol from the FINISH cell, that poses only one symbol.
         * @return
         */
        
        public char getSymbol(){
            if(this.getTopLeft() != ' '){
                return this.getTopLeft();
            }
            else if(this.getTopRight() != ' '){
                return this.getTopRight();
            }
            else if (this.getBottomLeft() != ' '){
                return this.getBottomLeft();
            }
            else{
                return this.getBottomRight();
            }
            
        }
        
    }
    
    

    

    


