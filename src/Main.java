public class Main {
    public static void main(String[] args) {

        char [][] board = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};
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