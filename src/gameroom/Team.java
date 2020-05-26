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
public class Team  {
    private Player[] Team; //an array of players that will make up the team
    private int TeamID; //the id of the team
    //public static Team[] players = new Team[NUM_TEAMS];
    
    public Team(Player[] plys, int teamis, int num_teams, int tot_num_players){
        //System.out.println("const");
        TeamID = teamis;
        //makes an array for the team of the selected amount
        Team = new Player[tot_num_players / num_teams];
        int numplayer = 0;
        for (int c =0; c < tot_num_players; c++ ){
            //System.out.println("hehr");
            if(plys[c].getTeamID() == teamis){
                //System.out.println("here2");
                Team[numplayer] = plys[c];
                numplayer++;
            }
        }
    }
    //prints the team
    public String toString(){
        //int temp = Team.length;
        //String temp2 = Team[0].toString();
        String rt = "Team # " + TeamID +": ";
        for (int c =0; c < Team.length; c++ ){
            //System.out.println("here3");
            rt += "\n " +Team[c].toString();
        }
        return rt;
    }
    
    public int getID(){
        return this.TeamID;
    }
    public int getTeamSize(){
        return this.Team.length;
    }
    
    //adds a win to every member of the team
    public void addWin(){
        for (int c =0; c < Team.length; c++ ){
            //System.out.println("here3");
            Team[c].addWin();
        }
    }
    
    public Player[] getPlayers(){
        return this.Team;
    }
    
    
    
}
