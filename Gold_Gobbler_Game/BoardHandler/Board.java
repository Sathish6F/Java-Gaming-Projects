package BoardHandler;
import java.util.*;

import GameHandler.Game;
import GameHandler.Player;

public class Board {
    //The String matrix for representing the  game board
	public static String[][] board;
    //boardPositions - used to store the postion and corresponding value is on the top
    //Key - position     Value - coin value
    public static Map<String,String> boardPositions = new HashMap<>();
    //underlyingValues - used to store the underlying values which are present in that position
    //Key - position     Value - List<String> there may be chance of more than one underlying values
    public static Map<String,List<String>> underlyingValues = new HashMap<>();

    //construcor for initialize the board with given board size n
    public Board(int n){
        board = new String[n][n];
        initializeBoard(n);
    }

    public Board(){}

    //Initialize the Board's row with numbers and column with Alphabets 
    private void initializeBoard(int n){
        
        for(String[] ar:board){
            Arrays.fill(ar,"  ");
        }
        char row = 49;
        char col = 65;
        for(int i = 1;i < n;i++){
            board[i][0] = ""+((char)row++)+" ";
        } 
        for(int i = 1;i < n; i++){
            board[0][i] = ""+((char)col++)+" ";
        }
        System.out.println("Board Initialized Successfully....");
        displayBoard();
    }

    //Prepare the board with given postions and coin values 
    public void prepareBoard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the  no.of Initial Positions:");
        int n = sc.nextInt();
        while(n > 0){
            System.out.println("Enter values as (<Color|size>,Position):");
            String value = sc.next();
            String position = sc.next();
            placeOnBoard(value,position);
            n--;
        }
        displayBoard();
    }
    
    //Place the coins on the board & before placing coins check if there is any 
    //coins present on that same position
    public static void placeOnBoard(String val,String pos){
        int row = pos.charAt(1) - 48;
        int col = pos.charAt(0) - 64;
        if(alreadyInBoard(val)){
            System.out.println("Already Placed on the Board");
            return;
        }
        if(!boardPositions.containsKey(pos)){
            boardPositions.put(pos,val);
            board[row][col] = val;
            return;
        }else{
            String present = boardPositions.get(pos);
            if((present.charAt(1)-48) > (val.charAt(1)-48)){
                return;
            }else{
                if(underlyingValues.get(pos) == null){
                    List<String> li = new ArrayList<>();
                    li.add(present);
                    underlyingValues.put(pos,li);
                }else{
                    underlyingValues.get(pos).add(present);
                    boardPositions.replace(pos, val);
                }
            }
            board[row][col] = val;
        }
    }

    //To check whether the given value is already present in the board or not
    public static boolean alreadyInBoard(String val){
       for(int i = 1;i<board.length;i++){
            for(int j = 1;j<board.length;j++){
                if(val.equals(board[i][j])){
                   return true;
                }
            }
        }
        return false;
    }

    //To move the coin which is already present in the baord and
    //we are trying to move to a new position after moving that coin
    //the underlying coin is revealed
    public static void placeOnNewPosition(String pos,String val){
        String oldPos="";
        l1:for(int i = 1;i<board.length;i++){
            for(int j = 1;j<board.length;j++){
                if(val.equals(board[i][j])){
                    oldPos = ""+((char)(j+64))+i;
                    break l1;
                }
            }
        }
       
        if(underlyingValues.containsKey(oldPos) && !oldPos.equals("")){
            String underValue = underlyingValues.get(oldPos).get(0);
            underlyingValues.get(oldPos).remove(0);
            int row1 = oldPos.charAt(1) - 48;
            int col1 = oldPos.charAt(0) - 64;
            board[row1][col1] = underValue;
            placeOnBoard(val, pos);
        }else{
            placeOnBoard(val, pos);
        }
    }
    //To check whether the current player is a winner or not
    //check horizondally, vertically and diagonally for the same color coins
    public static void checkWin(Player p){
        if(isDraw()){
            System.out.println("Match Draw");
            return;
        }
        int color = p.playerColor.charAt(0);
        int cnt = 0;
        for(int i = 1;i < board.length;i++){
            cnt = 0;
            for(int j = 1;j< board.length;j++){
                if(board[i][j].charAt(0) == color){
                    cnt++;
                }
                 if(cnt == board.length-1){
                    displayBoard();
                    System.out.println(p.playerName+" Wins");
                    System.exit(0);
                }
            }
        }
       
        cnt  = 0;
        for(int i = 1;i < board.length;i++){
            cnt = 0;
            for(int j = 1;j< board.length;j++){
                if(board[j][i].charAt(0) == color){
                    cnt++;
                }
                if(cnt == board.length-1){
                    displayBoard();
                    System.out.println(p.playerName+" Wins");
                    System.exit(0);
                }
            }
        }
        
        cnt = 0;
        for(int i = 1; i< board.length;i++){
            if(board[i][i].charAt(0) == color){
                cnt++;
            }
            if(cnt == board.length-1){
                displayBoard();
                System.out.println(p.playerName+" Wins");
                System.exit(0);
            }
        }
        
        cnt = 0;
        for(int i = 1, j = board.length-1;i < board.length && j >=0;i++,j--){
            if(board[i][j].charAt(0) == color){
                cnt++;
            }
            if(cnt == board.length-1){
                displayBoard();
                System.out.println(p.playerName+" Wins");
                System.exit(0);
            }
        }
        
    }
    //If no more place on the board and we can't move the coins
    //then declare the match is draw
    public static boolean isDraw(){
        int cnt = 0;
        for(int i = 1;i < board.length;i++){
            for(int j = 1 ;j < board.length;j++){
                if(!board[i][j].equals("  ")){
                    cnt++;
                }
            }
        }
        return (cnt == ((board.length-1) * (board.length-1)));
    }

    //To check whether we are trying to move a coin which is already
    //under some coins then we can't move
    public static boolean isUnder(String val){
        for(List<String> list : underlyingValues.values()){
            for(String s: list){
                if(s.equals(val)){
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] generateMove(String color){
        String val="";
        String pos;
        Random random = new Random();
        int value = random.nextInt(Game.coinRange);
        val = color+value;
        while(value == 0 || isUnder(val) || alreadyInBoard(val)){
            value = random.nextInt(Game.coinRange);
            val = color+value;
        }
        

        int row = random.nextInt(board.length);
        while(row == 0){
            row = random.nextInt(board.length);
        }
        int col = random.nextInt(board.length);
        while(col == 0){
            col = random.nextInt(board.length);
        }
        char c = (char)(col+64);
        pos = ""+c+row;
        
        System.out.println(val +"\n"+pos);
        return new String[]{val,pos};
        
    }

    //To display the board 
    public static void displayBoard(){
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board.length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}