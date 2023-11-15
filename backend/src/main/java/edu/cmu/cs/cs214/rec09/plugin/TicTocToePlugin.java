package edu.cmu.cs.cs214.rec09.plugin;
import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

public class TicTocToePlugin implements GamePlugin<TicTacToe.Player> {
    private static final String GAME_NAME = "Tic-Tac-Toe";
    private static final int BOARD_SIZE = TicTacToe.SIZE;

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return BOARD_SIZE;
    }

    @Override
    public int getGridHeight() {
        return BOARD_SIZE;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        framework.setFooterText("Let's play Tic-Tac-Toe!");
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                framework.setSquare(x, y, "");
            }
        }
    }

    @Override
    public void onNewMove() {
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return game.isOver();
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.getSquare(x, y).toString());
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        TicTacToe.Player winner = game.winner();
        if (winner != null) {
            return "Player " + winner.toString() + " wins!";
        } else {
            return "It's a tie!";
        }
    }

    @Override
    public void onGameClosed() {
    }

    @Override
    public TicTacToe.Player currentPlayer() {
        return game.currentPlayer();
    }
}
