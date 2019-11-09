public class Main {

    public static void main(String[] args) {

        String name1 = "A";
        String name2 = "B";

        Player player1 = new Player(name1, TicTacToe.SYMBOL_X);
        Player player2 = new Player(name2, TicTacToe.SYMBOL_0);

        TicTacToe ticTacToe = new TicTacToe(player1, player2);
        ticTacToe.playGame();

    }

}
