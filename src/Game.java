import java.util.Scanner;

class Game {
    private final Board board;
    private final Player player;
    private final CPU cpu;
    private final Scanner scanner;

    public Game() {
        board = new Board();
        player = new Player();
        cpu = new CPU();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bore ho rahe ho? To chalo game khelte hai..\uD83C\uDFB2");
        System.out.println("So, tell me what's your name?");
        player.setName(scanner.nextLine());

        boolean playAgain = true;

        while (playAgain) {
            board.reset();
            player.resetPositions();
            cpu.resetPositions();
            board.printBoard();

            while (true) {
                playerTurn();
                if (checkWinner(player.getName())) break;

                cpuTurn();
                if (checkWinner(player.getName())) break;
            }

            scanner.nextLine();

            System.out.println("Wanna play again? (Y/N)");
            playAgain = scanner.nextLine().equalsIgnoreCase("Y");
        }

        System.out.println("Okay, Bye bye!\uD83D\uDC4B\uD83C\uDFFB");
    }

    private void playerTurn() {
        System.out.println(player.getName() + ", where do you want to place (1-9)?");
        int pos = scanner.nextInt();
        while (board.isPositionTaken(pos)) {
            System.out.println("Already hai waha pe \uD83E\uDD26\u200D♀\uFE0F, chalo fir se apna dalo apna number");
            pos = scanner.nextInt();
        }
        player.addPosition(pos);
        board.placePiece(pos, 'X');

    }

    private void cpuTurn() {
        int pos = cpu.generateMove();
        while (board.isPositionTaken(pos)) {
            pos = cpu.generateMove();
        }
        cpu.addPosition(pos);
        board.placePiece(pos, 'O');
        board.printBoard();
    }

    private boolean checkWinner(String playerName) {
        String result = board.checkWinner(player.getPositions(), cpu.getPositions(), playerName);
        if (!result.isEmpty()) {
            System.out.println(result);
            return true;
        }
        return false;
    }
}
