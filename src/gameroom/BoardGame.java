/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom ;

/**
 *
 * @author adamstreich
 */
public class BoardGame{
    
    //a method to move pieces on the board
    //returns true if successful false if not
    public boolean move(Board bd, Piece pc, int lc1, int lc2){
        boolean rt = false;
        Cell[][] brd = bd.getBoard();
        //gets the location and puts it into an array such that [row,col]
        int[] loc1 = bd.locToRC(lc1);
        int[] loc2 = bd.locToRC(lc2);
        //this does the move
        Cell old = brd[loc1[0]][loc1[1]];
        Piece tbm = old.pullFromBag(pc);
        Cell new1 = brd[loc2[0]][loc2[1]];
        //attempts to add it to the new locations bag
        rt = new1.addToBag(tbm);
        return rt; 
    }
    
    //a method to add a piece to the board
    public boolean add(Board bd, Piece pc, int lc1){
        boolean rt = false;
        Cell[][] brd = bd.getBoard();
        //gets the location and puts it into an array such that [row,col]
        int[] loc1 = bd.locToRC(lc1);
        Cell new1 = brd[loc1[0]][loc1[1]];
        rt = new1.addToBag(pc);
        return rt;
    }
    
    //a method to remove a piece to the board
    public boolean remove(Board bd, Piece pc, int lc1){
        boolean rt = true;
        Cell[][] brd = bd.getBoard();
        //gets the location and puts it into an array such that [row,col]
        int[] loc1 = bd.locToRC(lc1);
        Cell old = brd[loc1[0]][loc1[1]];
        Piece tbm = old.pullFromBag(pc);
        if(old.equals(null)){
            rt = false;
        }
        return rt;
    }
    
    
}
