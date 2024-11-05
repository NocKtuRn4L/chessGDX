package org.chessGDK.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chessGDK.logic.GameManager;

import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ScreenManager extends Game {
    private GameManager gm;
    // Create references to different screens
    private ChessBoardScreen chessBoardScreen;


    @Override
    public void create() {
        // print the current working directory
        // Initialize screens
        try {
            chessBoardScreen = new ChessBoardScreen();
            gm = new GameManager(chessBoardScreen);
            chessBoardScreen.loadTextures(gm);
            chessBoardScreen.addButtons(gm);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set the initial screen to the main menu
        this.setScreen(chessBoardScreen);

        //proofOfConcept();
    }

    public void proofOfConcept() {


        String fen;
        String bestMove;
        try {
            bestMove = gm.getBestMove("");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean moved;
/*
        moved = gm.movePiece(bestMove);
        System.out.println("Best Move: " + bestMove);
        System.out.println("\nResponded to stockfish move: " + moved);
        // Try moving a piece according to Stockfish's best move
        gm.printBoard();
 */

        boolean hasMoves = true;
        while (hasMoves) {
                hasMoves = gm.aiTakeTurn();
        }


    }

    @Override
    public void render() {
        // This will call the render method of the current screen
        super.render();
    }

    @Override
    public void dispose() {
        // Dispose of resources when the game ends
        chessBoardScreen.dispose();
    }

    // Add other methods to manage game state, screens, etc.
}

