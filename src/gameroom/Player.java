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
public class Player {
    private String name; // the name, no name object yet implemented
    private int charid; // character id such as 1 for X or 2 for O
    private int teamid; // team number
    private int wins;
    
    public Player(String nm,int x, int y){
        name = nm.trim();
        charid = x;
        teamid = y;
        wins = 0;
    
    }
    
    public int getTeamID(){
        return teamid;
    }
    
    public String getName(){
        return name;
    }
    
    public void addWin(){
        this.wins++;
    }
    @Override
    public String toString(){
        return name + " has " + wins + " wins. ";
    }
    
    
}
