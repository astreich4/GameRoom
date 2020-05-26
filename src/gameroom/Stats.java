/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

import java.util.Stack;

/**
 *
 * @author adamstreich
 * 
 * currently just used to see the last moves but could be expaneded out for AI
 */
public class Stats {
    private Stack<Object[]> stats;
    
    public Stats(){
        stats = new Stack<>();
    }
    //gets the info to add the move to the stack, int type is 1 is move , 2 remove, 3 add
    public void recordMove(Player x, int type, int loc1, int loc2, Piece y){
        Object[] move = new Object[]{x,type,loc1,loc2,y};
        stats.push(move);
        
    }
    
    public Object[] getLastMove(){
        return stats.peek();
    }
}
