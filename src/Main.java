import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String name1 = "A";
        String name2 = "B";

        Player player1 = new Player(name1, TicTacToe.SYMBOL_X);
        Player player2 = new Player(name2, TicTacToe.SYMBOL_0);

        boolean playGame = true;
        while(playGame){
            TicTacToe ticTacToe = new TicTacToe(player1, player2);
            Player winner = ticTacToe.playGame();
            System.out.println("play again ?");
            Scanner scanner = new Scanner(System.in);

            if ("yes".equals(scanner.nextLine())) {
                if (winner == player2) {
                    Player aux = player1;
                    player1 = player2;
                    player2 = aux;
                }
                playGame = true;
            } else {
                playGame = false;
            }
        }
    }

}
