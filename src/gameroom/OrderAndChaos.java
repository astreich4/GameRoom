/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameroom;

import static java.lang.Math.floor;
import java.util.Scanner;

/**
 *
 * @author adamstreich
 */
public class OrderAndChaos extends BoardGame{
    private Team[] teams;
    private Board board;
    private Stats stats;
    
    private Team order;
    private Team chaos;
    
    private final int BOARD_HEIGHT = 6;//CANNOT CHANGE FOR OaC (still works fine just not in the rules)
    private final int BOARD_WIDTH = 6;//CANNOT CHANGE FOR OaC (still works fine just not in the rules I choose)
    private final int NUM_COLORS =1;
    private final int ROOM_IN_SQUARE = 1;
    private final int X_IN_A_ROW =5;//CANNOT CHANGE FOR OaC (still works fine just not the rules I choose)
    private final char[] idc = new char[]{'x','o'}; //CANNOT ADD 'n' in current implementaion
    
    
    public OrderAndChaos(Team[] tms, Stats fl){
        teams = tms;
        stats = fl;
        order = teams[0];
        chaos = teams[1];
        board = new Board(BOARD_HEIGHT,BOARD_WIDTH,NUM_COLORS,ROOM_IN_SQUARE);
        
        
        
    }
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //basic introduction to every game
        Scanner words = new Scanner(System.in);
        
        //intro plus rules
        System.out.println("Welcome to ORDER and CHAOS");
        System.out.println("This is played on a "+BOARD_HEIGHT+ "x"+BOARD_WIDTH+" size board." );
        System.out.println("The teams are as follows:");
        System.out.println("Order: " + order.toString());
        System.out.println("Chaos: " + chaos.toString());
        System.out.println("Order must get 5 in a row of x or o to win.");
        System.out.println("Chaos1 must prevent that...");

        
        //the game part
        boolean gameon = true;
        //int round = 0;
        int numturns = 0;
        //floor(numturns/2);
        
        //meat of the game
            do{
                for(int c = 0; c < this.teams.length ; c++){
                    if(gameon){
                    //System.out.println(this.teams.length);
                    System.out.println(printBoard(this.board));
                    Player[] inplay = teams[c].getPlayers();
                    if(numturns != BOARD_HEIGHT * BOARD_WIDTH){
                        turn(this.board, inplay[(int) floor(numturns/2) % teams[c].getTeamSize()], this.stats);
                        numturns++;
                        if(checkstatus(this.board, this.stats ) == 1){
                            gameon=false;
                            System.out.println("----------------");
                            System.out.println("\tOrder Wins!   ");
                            System.out.println("----------------");
                            order.addWin();
                            System.out.println(printBoard(this.board));

                        }
                    }else{
                            gameon=false;
                            System.out.println("----------------");
                            System.out.println("    Chaos Wins! ");
                            System.out.println("----------------");
                            chaos.addWin();

                    }
                    
                    
                    }
                   
                }
            }while(gameon);
               
    }
    
    public void turn(Board bd, Player ply, Stats stats) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean sanitize = true;
        Scanner nums  = new Scanner(System.in);
        Scanner words = new Scanner(System.in);
        int typ = 0;
        int ans =-1;
        do{
        System.out.println(ply.getName() + " choose between (0): "+ idc[0] + " and (1): "+idc[1] + " (enter the number)");
        typ = words.nextInt();
        if( typ > -1 && typ <2){
            System.out.println(ply.getName() + " enter a space to move: ");
            ans = nums.nextInt();
            if( ans < (BOARD_HEIGHT * BOARD_WIDTH) && ans > -1){
                Piece temp = new Piece(idc[typ]);
                if(super.add(bd, temp, ans)){
                    stats.recordMove(ply, 3, ans, ans,temp); //the three means the move is an add
                    sanitize = false;
                }else{
                    System.out.println("That space is taken...");
                }  
            }
        }else{
            System.out.println("Please enter 0 or 1....");
        }
        
        }while(sanitize);
    }


  public int checkstatus(Board bd, Stats s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int gameover = 0;
        Object[] lastmv = s.getLastMove();
        Player current = (Player) lastmv[0];
        
        Piece checker = (Piece) lastmv[4];
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
                       // System.out.println(tf +" "+ inarow);

                if(tf && inarow){
                   counter+=1;
                   if(counter == X_IN_A_ROW){
                       gameover = 1;
                      // System.out.println("here2");
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

    
    //for printing the board
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
