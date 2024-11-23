import java.util.*;
class RockPaperSissor{
          public static void main(String[] args){
          //Create scanner object to get input and random object to generate random index
                    Scanner sc=new Scanner(System.in);
                    Random random=new Random();
                    //To track users score
                     int user_score=0;
                     int com_score=0;
                    //To Stores the user and computer genreated choices
                     String user_choice="";
                     String com_choice="";
                    int cnt=0;
                    while(cnt!=5){
                    //Get the users input
                    System.out.println("Enter your choise:");
                    user_choice=sc.next();
                    int index=random.nextInt(3);
                    //Create the String array to store the choices
                    String[] commands={"rock","paper","sissor"};
                    com_choice=commands[index];
                    //Checks the conditions when the player is win , if player wins the increase the user score else increase computer score
                    if((user_choice.equals("paper")&&com_choice.equals("rock")) ||(user_choice.equals("sissor")&&com_choice.equals("paper")) ||(user_choice.equals("rock")&&com_choice.equals("sissor"))){
                              user_score++;
                              System.out.println(com_choice);
                    }
                    else if((user_choice.equals("paper")&&com_choice.equals("paper")) ||(user_choice.equals("sissor")&&com_choice.equals("sissor")) ||(user_choice.equals("rock")&&com_choice.equals("rock"))){
                              cnt++;
                              System.out.println(com_choice);
                    }
                    else{
                              com_score++;
                              System.out.println(com_choice);
                    }
                    
                    cnt++;
                    }
                    //After 5 chances displays the winner deatils
                    if(user_score>com_score){
                              System.out.println("_____________________________________");
                              System.out.println("Player Won !!!");
                              System.out.println("User score:"+user_score);
                              System.out.println("Computer score:"+com_score);
                    }
                    else if(user_score==com_score){
                              System.out.println("_____________________________________");
                              System.out.println("Draw !!!");
                    }
                    else{
                              System.out.println("_____________________________________");
                              System.out.println("Computer Won !!!");
                              System.out.println("User score:"+user_score);
                              System.out.println("Computer score:"+com_score);
                    }
          }
}
