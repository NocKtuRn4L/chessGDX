package org.chessGDK.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chessGDK.logic.GameManager;

import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ChessScreenManager extends Game {
    // SpriteBatch is needed to draw 2D elements on the screen
    public SpriteBatch batch;
    public GameManager gm;
    // Create references to different screens
    private ChessBoardScreen chessBoardScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        proofOfConcept();

        // Initialize screens
        chessBoardScreen = new ChessBoardScreen(this);

        // Set the initial screen to the main menu
        this.setScreen(chessBoardScreen);
    }

    public void proofOfConcept() {
        String stockfishPath = "\"D:\\Programming\\Projects\\3296_Software_Design\\stockfish\\stockfish-windows-x86-64-avx2.exe\"";

        String fen = "rnbqkbnr/1ppppppp/p7/8/P7/8/1PPPPPPP/RNBQKBNR w KQkq - 0 1";
        String bestMove;
        try {
            gm = new GameManager(stockfishPath);
            bestMove = gm.getBestMoveAI(fen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Move a white pawn from (1, 0) to (3, 0) (2 squares forward, initial move)
        boolean moved = gm.movePiece(1, 0, 3, 0);
        System.out.println("\nMove white pawn from (2, 0) to (4, 0): " + moved);
        gm.printBoard();

        // Try moving a black pawn from (6, 0) to (5, 0) (1 square forward)
        moved = gm.movePiece(6, 0, 5, 0);
        System.out.println("\nMove black pawn from (7, 0) to (6, 0): " + moved);
        gm.printBoard();

        System.out.println("Best Move: " + bestMove);
    }

    @Override
    public void render() {
        // This will call the render method of the current screen
        super.render();
    }

    @Override
    public void dispose() {
        // Dispose of resources when the game ends
        batch.dispose();
        chessBoardScreen.dispose();
    }

    // Add other methods to manage game state, screens, etc.
}

