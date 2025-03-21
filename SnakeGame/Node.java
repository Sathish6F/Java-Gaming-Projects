package SnakeGame;

public class Node {
	private int row;
    private int col;
    Node(int row,int col){
        this.row = row;
        this.col = col;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
}