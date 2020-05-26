/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

/**
 *
 * @author adamstreich
 */
public class Cell {
    private int color; //color of the cell
    private int location; // way to reference other cells
    private Piece[] inside; // way to hold all of the cells contents
    
    public Cell(int c, int lc, Piece[] bg){
        color = c;
        location = lc;
        inside = bg;
        
    }
    
    public Piece[] getBag(){
        return this.inside;
    }
    
    
    //removes the object x from the bag and replaces it with a null object
    public Piece pullFromBag(Piece x){
        Piece rt = null;
        for(int c = 0; c< this.inside.length ;c++){
            if(x.equals(inside[c])){ 
                rt = this.inside[c];
                inside[c]= new Piece();
            }
        }
        return rt;
    }
    
    //adds an object x to the bag if there is room, true if successful false if not
    public void fillBag(){
        for(int c = 0; c< this.inside.length ;c++){   
                inside[c] = new Piece();  
        } 
    }
    
      //adds an object to the bag in the cell if there is room  
    public boolean addToBag(Piece x){
        boolean yn = false;
        Piece nullt = new Piece();
        for(int c = 0; c< this.inside.length ;c++){
            if(nullt.equals(inside[c])){ 
                inside[c] = x;
                yn = true;
            }
        }
        return yn;
    }
    
    //checks if the piece x is in the bag
    public boolean insideBag(Piece x){
        boolean yn = false;
        
        for(int c = 0; c< this.inside.length ;c++){
            if(x.equals(inside[c])){ 
                
                yn = true;
            }
        }
        return yn;
    }
    
    //gets all the contents of the bag as a sting
    public String getContents(){
        String rt ="";
         for(int c = 0; c< this.inside.length ;c++){
             //System.out.println(this.inside.length);
            rt+=inside[c].getChar();
        }
         return rt;
    }
}
