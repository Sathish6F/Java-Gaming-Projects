package GameHandler;
import java.util.*;
import BoardHandler.Board;

public class Game {
    static Scanner sc = new Scanner(System.in);
    static Board board;
    public static int coinRange;

	public void initializePlayers(){

        System.out.println("Enter Player1 Name:");
        String p1name = sc.next();
        System.out.println("Choose Color:");
        String p1color = sc.next();
        Player p1 = new Player(p1name, p1color);
        System.out.println("Enter Player2 Name:");
        String p2name = sc.next();
        System.out.println("Choose Color:");
        String p2color = sc.next();
        Player p2 = new Player(p2name, p2color);
        play(p1,p2);

    }

    public void startGame(){

        System.out.println("Choose Board size:");
        int n = sc.nextInt();
        System.out.println("Enter the no.of Coins:");
        coinRange = sc.nextInt();
        board = new Board(n+1);
        System.out.println("1.Play with Person\n2.Play with Computer");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                initializePlayers();
                break;
            case 2:
                initializePlayer();
                break;
            default:
                System.out.println("Choose valid Option");
                startGame();
                break;
        }
        
    }

    public void play(Player p1,Player p2){

        System.out.println("Player 1:Color "+p1.playerColor+" Name "+p1.playerName);
        System.out.println("Player 2:Color "+p2.playerColor+" Name "+p2.playerName);
        Player currentPlayer = p1;
        
        while(true){
            System.out.println(currentPlayer.playerName);
            String val = sc.next();
            String pos = sc.next();
            
            try{
                if(val.charAt(0) != currentPlayer.playerColor.charAt(0)){
                    throw new IllegalArgumentException("Invalid Color");
                }
                if(val.charAt(1)-48 > coinRange || val.charAt(1)-48 < 0) {
                    throw new IllegalArgumentException("Invalid Coin Value"); 
                }
                if(Board.isUnder(val)){
                    throw new IllegalArgumentException("Can't move "+val);
                }
                if(Board.alreadyInBoard(val)){
                    Board.placeOnNewPosition(pos, val);
                    Board.checkWin(currentPlayer);
                }else{
                    Board.placeOnBoard(val, pos);
                    Board.checkWin(currentPlayer);
                }
                
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            currentPlayer = (currentPlayer == p1)? p2: p1;
            Board.displayBoard();
        }
    }

    public void initializePlayer(){

        System.out.println("Enter Player1 Name:");
        String p1name = sc.next();
        System.out.println("Choose Color:");
        String p1color = sc.next();
        Player p1 = new Player(p1name, p1color);
        String compColor = (p1.playerColor.equals("O")) ? "B" : "O";
        Player p2 = new Player("Computer",compColor);
        playWithComputer(p1, p2);
    }

    public void playWithComputer(Player p,Player comp){
        System.out.println("Playre 1:Color "+p.playerColor+" Name "+p.playerName);
        System.out.println("Playre 2:Color "+comp.playerColor+" Name "+comp.playerName);
        Player currentPlayer = p;

        while(true){
            String val,pos;
            if(currentPlayer.playerName.equals("Computer")){
                System.out.println(currentPlayer.playerName);
                String[] move = Board.generateMove(currentPlayer.playerColor);
                val = move[0];
                pos = move[1];
            }else{
                System.out.println(currentPlayer.playerName);
                val = sc.next();
                pos = sc.next();
            }
            try{
                if(val.charAt(0) != currentPlayer.playerColor.charAt(0)){
                    throw new IllegalArgumentException("Invalid Color");
                }
                if(val.charAt(1)-48 > coinRange || val.charAt(1)-48 < 0) {
                    throw new IllegalArgumentException("Invalid Coin Value"); 
                }
                if(Board.isUnder(val)){
                    throw new IllegalArgumentException("Can't move "+val);
                }
                if(Board.alreadyInBoard(val)){
                    Board.placeOnNewPosition(pos, val);
                    Board.checkWin(currentPlayer);
                }else{
                    Board.placeOnBoard(val, pos);
                    Board.checkWin(currentPlayer);
                }
                
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            currentPlayer = (currentPlayer == p)? comp: p;
            Board.displayBoard();
        }

    }

}