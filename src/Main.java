import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char [][] board = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number where you want to place your dice (1-9)");
        int pos =  s.nextInt();
        System.out.println(pos);

        switch (pos)
        {
            case 1:
                board[0][0] = 'X';
                break;
            case 2:
                board[0][2] = 'X';
                break;
            case 3:
                board[0][4] = 'X';
                break;
            case 4:
                board[2][0] = 'X';
                break;
            case 5:
                board[2][2] = 'X';
                break;
            case 6:
                board[2][4] = 'X';
                break;
            case 7:
                board[4][0] = 'X';
                break;
            case 8:
                board[4][2] = 'X';
                break;
            case 9:
                board[4][4] = 'X';
                break;
        }
        printGameboard(board);

    }
    public static void printGameboard(char[][] board)
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
}