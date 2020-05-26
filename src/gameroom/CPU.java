/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

import java.util.Random;

/**
 *
 * @author adamstreich
 */
public class CPU extends Player{
    private String[] CPUnames = new String[]{ "NastyHulk","WarningOutOfMind","B4UShout","MortalityReturns","AssasinMeetUp","WickedImpulse"};
    
    public CPU(int x, int y) {
        super("CPU",x,y);
    }
    
    private String randomName(){
        Random rand = new Random();
        String rn = CPUnames[rand.nextInt(CPUnames.length-1)];
        return rn;
    }
    
}
