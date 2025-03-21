package SnakeGame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;
public class Snake {
	Queue<Node> queue = new LinkedList<>();
    char[][] snakeBoard;
    Snake(int r,int c){
        snakeBoard = new char[r][c];
        for(char[] ar: snakeBoard){
            Arrays.fill(ar, '0');
        }
        snakeBoard[2][3] = '*';
        snakeBoard[4][3] = '*';
        queue.offer(new Node(0,0));
    }
    public void snakeMove(int row,int col){
        if(snakeBoard[row][col] == '.'){
            System.out.println("Game Over");
            System.exit(0);
        }
        try{
            if(row >=0 && row < snakeBoard.length && col >=0 && col < snakeBoard[0].length){
            queue.offer(new Node(row,col));
            if(snakeBoard[row][col] != '*'){
                Node tail = queue.poll();
                int r = tail.getRow();
                int c = tail.getCol();
                snakeBoard[r][c] = '0';
            }
            snakeBoard[row][col] = '.';
            while(!queue.isEmpty()){
                printBoard();
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the choice:");
                System.out.println("U - Up    D - Down");
                System.out.println("R - Right L - Left");
                char choice = sc.next().charAt(0);
                if(choice == 'U'){
                    snakeMove(--row, col);
                }
                if(choice == 'D'){
                    snakeMove(++row, col);
                }
                if(choice == 'R'){
                    snakeMove(row,--col);
                }
                if(choice == 'L'){
                    snakeMove(row, ++col);
                }
                
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Game over");
            System.exit(0);
        }
        
    }
    public void printBoard(){
        for(char[] b : snakeBoard){
            System.out.println(b);
        }
    }

}