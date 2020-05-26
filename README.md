# GameRoom
TicTacToe 2 ReadMe
1. How to Compile
2. How to Run
3. How to Play
4. Games

1. How to Compile
Download the three required .java files. 
(Board.java; BoardGame.java; Cell.java; CPU.java; CTicTacToe.java; GameRoom.java; Human.java; OrderAndChaos.java; Piece.java; Playable.java; Player.java; Stats.java; Team.java; TicTacToe.java) 
Place the three files into a folder named gameroom. In the terminal enter the gameroom directory and for Macs run the command: 
javac *.java
This will compile all the .java files in the directory.

2. How to Run
Make sure the files correctly produced the appropriate .class files. 
Exit the current directory to the directory above gameroom and on a Mac run the command:
java gameroom.GameRoom

3. How to Play
(Changes to the number of players and the number of teams must be done in the GameRoom class, there is not an implemented way of changing them each time you play)

First it will ask you to enter in the names of you player, next it will present you with a list of games or to quit. Select the games or quitting by entering a number -1 through 2.

4.Games
Tic Tac Toe
Can be played with any number of people on up to 7 teams. (This restriction is not enforced and will break the code, do not attempt more than 7 teams.)
Your basic 3x3 tic tac toe game. Players and teams will alternate choosing a number from 0 to 8 to place either an x or an o on the board.
Once you finish you will be returned to the main menu

Order and Chaos
Can be played with any number of players, but just two teams. (This restriction is not enforced and will break the code, do not attempt more than 2 teams)
Played on a 6x6 board, Order must get 5 in a row of either x or o and Chaos must fill the board. I chose these rules because they are the 'original' rules so far as I understood.

Custom Tic Tac Toe
Can be played with any number of people on up to 7 teams.(This restriction is not enforced and will break the code, do not attempt more than 7 teams.)
There is a restriction on the board size as it must be greater the 2x2 and less than 101x101 and the # in a row to win must be less than the width.
Other than that play and combination of # in a row and board size.


Written by Adam Streich
