import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char [][] board = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};
        printBoard(board);

        while (true)
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the number where you want to place your dice (1-9)");
            int playerPos =  s.nextInt();

            placePiece(board, playerPos, "player");
            Random rand = new Random();
            int cpuPos = rand.nextInt(9)+ 1;
            placePiece(board, cpuPos, "cpu");
            printBoard(board);
        }



    }
    public static void printBoard(char[][] board)
    {
        for(char[] row :board)
        {
            for (char c: row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] board,int pos, String user)
    {
        char symbol = ' ';
        if(user.equals("player"))
        {
            symbol = 'X';
        }
        else if(user.equals("cpu"))
        {
            symbol = 'O';
        }
        switch (pos)
        {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}