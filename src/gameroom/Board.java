/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

/**
 *
 * @author adamstreich
 * 
 * 
 * Sets up a board of cells with numbered locations like such
 * 
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * 
 * No toString because each game will print the board differently
 * 
 * 
 */
public class Board {
    private int width;
    private int height;
    private int space; // room inside the cell to store pieces
    private int alternateColors; //alternate colors with 2, single color is with 1
    private Cell[][] board;
    
  
    public Board(int w, int h, int nc, int sp){
        width = w;
        height =h;
        alternateColors = nc;
        space = sp;
        //creates the board
        board = new Cell[width][height];
        //fills the board with cells
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){ 
                board[row][col] = new Cell(((row+col)%alternateColors), (row+col), new Piece[space]);
            }
        }
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){ 
                board[row][col].fillBag();
                //System.out.println("bag filled");
            }
        }
        
    }
    //constructor to do a square board
    public Board(int wh, int nc, int sp){
        this(wh,wh,nc,sp);
    }
    
    //getter method for board array
    public Cell[][] getBoard(){
        return this.board;
    }
    
    //way to retrieve row and col data with the perscribed location input 
    public int[] locToRC(int lc){
        int[] loc = new int[2];
        Cell[][] brd = this.getBoard();
        int counter = 0;
        for(int row = 0; row < brd.length; row++){
            for(int col = 0; col < brd[row].length; col++){ 
                if(lc == (counter)){
                    loc[0]=row;
                    loc[1]=col;
                }
                counter++;
            }
        }
        return loc;  
    }
/*
    int[] locToRC(int lc1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    
    
}
