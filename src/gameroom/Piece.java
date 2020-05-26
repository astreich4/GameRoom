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
public class Piece {
    //the empty piece is denoted by the 'n' char
    private final char NULL_PIECE = 'n';
    //additionally the equals method only evaluates the xo, not written abstractly yet
    private char xo;
    
    
    public Piece(char o){
        xo = o;
    }
    
    //null piece 
    public Piece(){
        xo = NULL_PIECE ;
    }
    
    public boolean equals(Piece x){
        boolean rt = false;
        if(this.xo == x.xo){
            rt = true;
        }
        return rt;
    }
    
    public char getChar(){
        char rt = ' ';
        if(this.xo == NULL_PIECE){
            rt = ' ';
        }else{
            rt= this.xo;
        }
        return rt;
    }
    
    
}
