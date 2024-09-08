package com.example;

import java.util.Scanner;

class Game {
    private final Board board = new Board();
    private final Player player1;
    private final Player player2;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        System.out.println("Bore ho rahe ho? To chalo game khelte hai..\uD83C\uDFB2");
        System.out.println("So, tell me what's your name?");
        String playerName = scanner.nextLine();
        player1 = new HumanPlayer(playerName);
        player2 = new CpuPlayer();
    }

    public void start() {
        boolean playAgain = true;

        while (playAgain) {
            board.reset();
            player1.resetPositions();
            player2.resetPositions();
            board.printBoard();

            while (true) {
                if(takeTurn(player1)) {
                    break;
                }
                if(takeTurn(player2)) {
                    break;
                }
            }

            playAgain = askToPlayAgain();

        }

        System.out.println("Okay, Bye bye!\uD83D\uDC4B\uD83C\uDFFB");
    }

    private boolean takeTurn(Player player)
    {
        int pos = player.makeMove(board);
        player.addPosition(pos);
        char symbol = player instanceof HumanPlayer ? 'X' : 'O';
        board.placePiece(pos,symbol);
        if(player.equals(player1))
        {
            System.out.println("==================="+player1.getName()+"'s Turn=================");
            board.printBoard();
        }
        else {
            System.out.println("======================CPU's Turn====================");
            board.printBoard();
        }
        return checkWinner(player.getName());
    }


    private boolean checkWinner(String playerName) {
        String result = board.checkWinner(player1.getPositions(), player2.getPositions(), playerName);
        if (!result.isEmpty()) {
            System.out.println(result);
            return true;
        }
        return false;
    }

    private boolean askToPlayAgain()
    {
        System.out.println("Wanna play Again? (Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }
}
