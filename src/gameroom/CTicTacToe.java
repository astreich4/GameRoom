/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom ;

import static java.lang.Math.floor;
import java.util.Scanner;

/**
 *
 * @author adamstreich
 */
public class CTicTacToe extends BoardGame implements Playable {
    private Team[] teams;
    private Board board;
    private Stats stats;
    
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;
    private final int NUM_COLORS =1;
    private final int ROOM_IN_SQUARE = 1;
    private int X_IN_A_ROW;
    private final char[] idc = new char[]{'x','o','a','b','c','d','e'}; //CANNOT ADD 'n' in current implementaion

    public CTicTacToe(Team[] tms,Stats fl, int w, int h, int x){
        teams = tms;
        stats = fl;
        BOARD_HEIGHT =h;
        BOARD_WIDTH = w;
        X_IN_A_ROW = x;
        board = new Board(BOARD_HEIGHT,BOARD_WIDTH,NUM_COLORS,ROOM_IN_SQUARE);
        
    }
    
 
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //basic introduction to every game
        //Scanner words = new Scanner(System.in);
        
        //Intro messages + rules
        System.out.println("Welcome to TIC TAC TOE");
        System.out.println("This is played on a "+BOARD_HEIGHT+ "x"+BOARD_WIDTH+" size board." );
        System.out.println("The teams are as follows:");
        for(int c = 0; c < this.teams.length;c++){
            System.out.println("The "+ idc[teams[c].getID()]+ " team is ");
            System.out.println(teams[c].toString());
        }
        System.out.println("Get "+X_IN_A_ROW+" in a row to win.");
        
        //the game part
        boolean gameon = true;
        //int round = 0;
        int numturns = 0;
        //floor(numturns/2);
        
        //The meat of the game
            do{
                for(int c = 0; c < this.teams.length ; c++){
                    if(gameon){
                    //System.out.println(this.teams.length);
                    System.out.println(printBoard(this.board));
                    Player[] inplay = teams[c].getPlayers();
                    if(numturns != BOARD_HEIGHT * BOARD_WIDTH){
                        turn(this.board, inplay[ (int) floor(numturns/2) % teams[c].getTeamSize()], this.stats);
                        numturns++;
                        if(checkstatus(this.board, this.stats ) == 1){
                            gameon=false;
                            System.out.println("----------------");
                            System.out.println("\t"+inplay[ (int) floor(numturns/2) % teams[c].getTeamSize()].toString()+" Wins!   ");
                            System.out.println("----------------");
                            teams[c].addWin();
                            System.out.println(printBoard(this.board));

                        }
                    }else{
                            gameon=false;
                            System.out.println("----------------");
                            System.out.println("    Cat Game!   ");
                            System.out.println("    No Winner.. ");
                            System.out.println("----------------");
                    }

                    }
                   
                }
            }while(gameon);   
    }

   //method for a turn to be taken by a player
    public void turn(Board bd, Player ply, Stats stats) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean sanitize = true;
        Scanner nums  = new Scanner(System.in);
        int ans =-1;
        do{   
        System.out.println(ply.getName() + " enter a space to move");
        ans = nums.nextInt();
        if( ans < (BOARD_HEIGHT * BOARD_WIDTH) && ans > -1){
            Piece temp = new Piece(idc[ply.getTeamID()]);
            if(super.add(bd, temp, ans)){
                stats.recordMove(ply, 3, ans, ans,temp); //the three means the move is an add
                sanitize = false;
            }  
        }
        }while(sanitize);
    }

   //method to check if the last move was a winning one
    public int checkstatus(Board bd, Stats s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int gameover = 0;
        Object[] lastmv = s.getLastMove();
        Player current = (Player) lastmv[0];
        Piece checker = new Piece(idc[current.getTeamID()]);
        //System.out.println(checker.getChar());
        
        int loc = (int) lastmv[2];
        int[] loc1 = bd.locToRC(loc);
       
        //creates the 4 check locations Right diagonal, Left Diagonal, Across, Down
        int[] rd = new int[]{loc1[0] - (X_IN_A_ROW - 1),loc1[1] - (X_IN_A_ROW - 1)};
        int[] ld = new int[]{loc1[0] - (X_IN_A_ROW - 1),loc1[1] + (X_IN_A_ROW - 1)};
        int[] d = new int[]{loc1[0] - (X_IN_A_ROW - 1),loc1[1]};
        int[] a = new int[]{loc1[0],loc1[1] - (X_IN_A_ROW - 1)};
        //System.out.println(ld[0]+"e"+ld[1]);
        //brings the 4 locations into indicies that will not set off an index out of bounds
        while(rd[0]<0 || rd[1]<0){
            rd[0]+=1;
            rd[1]+=1;
        }
        while(ld[0]<0 || ld[1]> BOARD_WIDTH-1){
            ld[0]+=1;
            ld[1]-=1;
        }
        while(d[0]<0){
            d[0]+=1;
        }
        while(a[1]<0){
            a[1]+=1;
        }
        
        //begins to check for x in a row begining at the 4 newly calculated indicies based on the most recent move
        //Right diagonal win
        Cell[][] brd = bd.getBoard();
        int counter =0;
        boolean inarow = false;
       
        int row = rd[0];
        int col = rd[1];
        int rowboard = brd.length;
        int colboard = brd[0].length;
        do{
            boolean tf = brd[row][col].insideBag(checker);
                        //System.out.println(tf +" "+ inarow);

                if(tf && inarow){
                   counter+=1;
                   if(counter == X_IN_A_ROW){
                       gameover = 1;
                       //System.out.println("1");
                   }
               }else if(tf){
                   counter =1;
                   inarow=true;
               }
                
                else{
                    inarow = false;
                    counter = 0;
                }
        row++;
        col++;    
        }while(row < rowboard && col < colboard);
        
        
        //begins to check for x in a row begining at the 4 newly calculated indicies based on the most recent move
        //Left diagonal win
         counter =0;
         inarow = false;
        row = ld[0];
        col = ld[1];
        do{
           // System.out.println(row+"  tut "+col);
            boolean tf = brd[row][col].insideBag(checker);
                        //System.out.println(tf +" "+ inarow);

                if(tf && inarow){
                   counter+=1;
                   if(counter == X_IN_A_ROW){
                       gameover = 1;
                       //System.out.println("here2");
                   }
               }else if(tf){
                   counter =1;
                   inarow=true;
               }else{
                    inarow = false;
                    counter = 0;
                }
        row++;
        col--;    
        //System.out.println(row+"  tut "+col);
        }while(row < rowboard && col < colboard && col > -1);
        
        
        //begins to check for x in a row begining at the 4 newly calculated indicies based on the most recent move
        //down win
        counter =0;
         inarow = false;
        row = d[0];
        col = d[1];
        do{
            boolean tf = brd[row][col].insideBag(checker);
                 //System.out.println(tf +" "+ inarow);
                if(tf && inarow){
                   counter+=1;
                   if(counter == X_IN_A_ROW){
                       gameover = 1;
                       //System.out.println("here3");
                   }
               }else if(tf){
                   counter =1;
                   inarow=true;
               }else{
                    inarow = false;
                    counter = 0;
                }
        row++;    
        }while(row < rowboard && col < colboard);
        
        
        //begins to check for x in a row begining at the 4 newly calculated indicies based on the most recent move
        //accross win
        counter =0;
         inarow = false;
        row = a[0];
        col = a[1];
        do{
            boolean tf = brd[row][col].insideBag(checker);
            //System.out.println(tf +" "+ inarow);
                if(tf && inarow){
                   counter+=1;
                   if(counter == X_IN_A_ROW){
                       gameover = 1;
                       //System.out.println("here4");
                   }
               }else if(tf){
                   counter =1;
                   inarow=true;
               }else{
                    inarow = false;
                    counter = 0;
                }
        col++;    
        }while(row < rowboard && col < colboard);
        
        
        return gameover;
    }

    //unsupported methods
    public void move() {
        throw new UnsupportedOperationException("You cannot move an X or an O"); //To change body of generated methods, choose Tools | Templates.
    }
  
    public void remove() {
        throw new UnsupportedOperationException("You cannot remove an X or an O"); //To change body of generated methods, choose Tools | Templates.
    }

    //a method to print the board
    public String printBoard(Board bd){
        Cell[][] brd = bd.getBoard();
        String x = "";
        String rt = "";
        for(int row = 0; row < brd.length; row++){
            for(int col = 0; col < brd[row].length; col++){ 
                x = brd[row][col].getContents();
                rt+= "| "+x+"\t|";
            }
            rt+="\n";
            for(int row1 = 0; row1 < brd.length; row1++){
                rt+="---------";
            }
            rt+="\n";
        }
        return rt;
    }
    
}
