import java.util.Scanner;

public class TicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int SIZE = 3;

    public char[][] board = new char[SIZE][SIZE];

    public Player player1;
    public Player player2;

    public Player currentPlayer;

    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    // puts spaces in all locations on board
    public void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // shows the current state of the game
    public void showBoard() {
        System.out.println("board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // no need to do this
                // if(board[i][j] != SYMBOL_X && board[i][j] != SYMBOL_0)
                // because board can have only ' ' or 'X' or '0'
                if (board[i][j] == ' ') {
                    int x = i * SIZE + j + 1;
                    System.out.print(x + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int readMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(currentPlayer.name + " introduceti mutarea:");
        int move = scanner.nextInt();
        return move;
    }

    public void makeMove(int position) {
        //subtracting 1 to make sure we respect algorithm
        position = position - 1;
        // determining line and column
        int line = position / SIZE;
        int col = position % SIZE;
//        currentPlayer has symbol
        board[line][col] = currentPlayer.symbol;
    }


    /*
        tests if all the symbols on line `line` are currentPlayer.symbol
     */
    public boolean moveIsWinOnLine(int line) {
        // currentPlayer.symbol
        boolean isWinOnLine = true;
        // maybe it would have been clearer if I used j instead of i
        int i = 0;
        while (i < SIZE && isWinOnLine) {
            if (board[line][i] != currentPlayer.symbol) {
                isWinOnLine = false;
            }
            i++;
        }
        return isWinOnLine;
    }

    /*
        test if all the symbols on column `col` are `currentPlayer.symbol`
     */
    public boolean moveIsWinOnCol(int col) {
        boolean isWinOnCol = true;
        int i = 0;
        while (i < SIZE && isWinOnCol) {
            if (board[i][col] != currentPlayer.symbol) {
                isWinOnCol = false;
            }
            i++;
        }
        return isWinOnCol;
    }

    /*
        on main diag i == j so we can access [i][i]
     */
    public boolean moveIsWinDiag1() {
        boolean isWinDiag1 = true;
        int i = 0;
        while (i < SIZE && isWinDiag1) {
            if (board[i][i] != currentPlayer.symbol) {
                isWinDiag1 = false;
            }
            i++;
        }
        return isWinDiag1;
    }

    /*
    on second diag i + j + 1 = SIZE so we can access [i][SIZE - 1 - i]
    */
    public boolean moveIsWinDiag2() {
        boolean isWinDiag2 = true;
        int i = 0;
        while (i < SIZE && isWinDiag2) {
            if (board[i][SIZE - 1 - i] != currentPlayer.symbol) {
                isWinDiag2 = false;
            }
            i++;
        }
        return isWinDiag2;
    }

    /*
        this method determines if the last move was one that won the game or not
     */
    public boolean moveIsWin(int position) {
        //subtracting 1 to make sure we respect algorithm
        position = position - 1;
        // determining line and column
        int line = position / SIZE;
        int col = position % SIZE;

        boolean moveIsWin = false;

        // test if all the symbols on line `line` are `currentPlayer.symbol`
        moveIsWin = moveIsWinOnLine(line);

        if (!moveIsWin) {
            // test if all the symbols on column `col` are `currentPlayer.symbol`
            moveIsWin = moveIsWinOnCol(col);
        }

        if (!moveIsWin && line == col) {
            moveIsWin = moveIsWinDiag1();
        }

        if (!moveIsWin && line == SIZE - 1 - col) {
            moveIsWin = moveIsWinDiag2();
        }

        return moveIsWin;
    }

    public boolean moveIsValid(int position) {
        //subtracting 1 to make sure we respect algorithm
        position = position - 1;
        // determining line and column
        int line = position / SIZE;
        int col = position % SIZE;

        //TODO: test if line and col are within bounds

        if (board[line][col] == ' ') {
            return true;
        } else {
            return false;
        }

    }

    /*
        returns winning player if there is a winning player or null if there is none
     */
    public Player playGame() {
        // initBoard
        initBoard();
        showBoard();

        // current player always start with player1
        this.currentPlayer = player1;

        boolean isWin = false;
        int nrMoves = 0;
        // while not win
        while (!isWin && nrMoves < (SIZE * SIZE)) {

            // read a move
            int move = readMove();
            boolean moveIsValid = moveIsValid(move);
            // if move not valid keep reading moves while moves are invalid
            // or as I would say: keep reading moves until we read a valid move
            while (!moveIsValid) {
                move = readMove();
                // validate move
                moveIsValid = moveIsValid(move);
            }

            // make move
            makeMove(move);
            // count move
            nrMoves++;
            // show board
            showBoard();
            // test win
            // if it is not win `isWin` remains false else if it win `isWin` becomes true
            isWin = moveIsWin(move);

            if (!isWin) {
                // change player ?
                // this if will be in the big while
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }
            }
        }
        // we can exit while either because isWin has become TRUE
        // either because nrMoves is max
        if (isWin) {
            // currentPlayer has the name of the winner
            System.out.println("CONGRATULATIONS " + currentPlayer.name + " !!!");
            // congratulate winner
            return currentPlayer;
        } else {
            // game is draw
            System.out.println(" GAME IS DRAW ");
            return null;
        }
    }

    public void playGames(){
        // TODO:
        // move the while from main here
        // this would mean that in main we init TicTacToe only once
        // we call playGames instead of playGame
        // and here we just call .playGame when the answer is "yes"
    }
}
