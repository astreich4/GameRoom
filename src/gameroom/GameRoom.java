/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
create an array of player objects, the pass that array to each game
character identifier as a number mod number of teams
 */
package gameroom;

import java.util.Scanner;

/**
 *
 * @author adamstreich
 */
public class GameRoom {
    
    //important numbers
    public static int NUM_GAMES = 3;
    public static int NUM_TEAMS;
    public static int NUM_PLAYERS;
    public static final int MIN_NUM_PLAYERS = 2;
    public static final int MAX_NUM_PLAYERS = 10;
    public static final int MIN_NUM_TEAMS = 2;
    public static final int MAX_NUM_TEAMS = 7;
    
    //important arrays
    private static Player[] players;
    private static Team[] teams;
    private static Stats statsfile = new Stats();
    
    /*
    public static final int  MAX_TEAMS = 2;
    public static final int MAX_PLAYERS = 4;
    */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Scanner gamechoice = new Scanner(System.in);
        Scanner words  = new Scanner(System.in);
        //boolean gameon = true;
        boolean sanitize = true;
        System.out.println("Welcome to the Game Room");
        String answ = " ";
        int anstemp = 2;
        
        //Asks the number of players
         
            while(sanitize){
            System.out.println("Enter the number of players:");
            anstemp = gamechoice.nextInt();
            if(anstemp >= MIN_NUM_PLAYERS && anstemp <= MAX_NUM_PLAYERS){
                sanitize = false;
                NUM_PLAYERS = anstemp;
            }else{
                System.out.println("Must be between 2 and 10");
            }
        }
         
         
         
            
            //asks the number of teams
            sanitize=true;
            
            while(sanitize){
            System.out.println("Enter the number of teams:");
            anstemp = gamechoice.nextInt();
            if(anstemp >= MIN_NUM_TEAMS && anstemp <= MAX_NUM_TEAMS){
                sanitize = false;
                NUM_TEAMS = anstemp;
            }else{
                System.out.println("Must be between 2 and 7");
            }
        }
        
            
         players = new Player[NUM_PLAYERS];
         teams = new Team[NUM_TEAMS];
        
        
        
        
        
        
        //builds the array of players for your durration of gameplay
        //your teams are based off of mod opperation so if there are 4 players and 2 teams, alternating players will be on opposite teams
        for (int c =0; c < NUM_PLAYERS ; c++ ){
            System.out.println("Enter your gamer tag for player "+(1+c)+":");
            answ = words.nextLine();
            players[c] = new Human(answ,c+1,c%NUM_TEAMS);
        }
        
        //builds the array of teams
        for (int c =0; c < NUM_TEAMS ; c++ ){
            teams[c] = new Team(players,c,NUM_TEAMS, NUM_PLAYERS);
        }
        
        
        System.out.println("Below is a list of available games");
        //List of games available, Add new titles here
        System.out.println("------------------");
        System.out.println("(0)\t Tic-Tac-Toe");
        System.out.println("(1)\t Order and Chaos");
        System.out.println("(2)\t Custom Tic Tac Toe");
        System.out.println("(-1)\t Quit");
        int ans = -1;
        sanitize = true;
        boolean gameon=true;
        while(gameon){
            //used to sanitize the input for the question
            while(sanitize){
            System.out.println("Enter the number of the game you would like to play:");
            ans = gamechoice.nextInt();
            if(ans >= -1 && ans < NUM_GAMES){
                sanitize = false;
                //System.out.println("here");
            }else{
                System.out.println("It's a simple answer...");
            }
        }
            sanitize = true;
            
            if(ans == -1){
               gameon = false;
               for(int c = 0; c < NUM_TEAMS;c++){ 
                 System.out.println(teams[c].toString());
                }
                System.out.println("Come Again Soon!");
            }else if(ans == 0){
                ticTacToe();
            }else if(ans == 1){
                orderAndChaos();
            }else if(ans == 2){
                CticTacToe();
            }
    }
    
}
         
    
    
    
    //methods to play the games
    public static void ticTacToe(){
        RTicTacToe game = new RTicTacToe(teams,statsfile);  
        game.run();
    }
    
    public static void orderAndChaos(){
        if(NUM_TEAMS>2){
            System.out.println("This game only suports 2 teams.... Try another game.");
        }else{
           OrderAChaos game = new OrderAChaos(teams,statsfile);
        game.run(); 
        }
        
    }
    
    public static void CticTacToe(){
        Scanner size = new Scanner(System.in);
        int w =3;
        int h = 3;
        int in = 3;
        boolean san = true;
        do{
            System.out.println("Enter a board width:");
            w = size.nextInt();
            System.out.println("Enter a board height");
            h = size.nextInt();
            System.out.println("Enter x in a row to win:");
            in = size.nextInt();
            if(w>2 && w<101 && h>2 && h<101 && in>1 && in<w){
                san = false;
            }
        }while(san);
        CTicTacToe game = new CTicTacToe(teams,statsfile,w,h,in);  
        game.run();
    }

}
