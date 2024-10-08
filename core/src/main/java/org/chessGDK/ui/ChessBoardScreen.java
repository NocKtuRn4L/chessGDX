package org.chessGDK.ui;


import org.chessGDK.pieces.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/** First screen of the application. Displayed after the application is created. */
public class ChessBoardScreen implements Screen {
    private final SpriteBatch batch;
    private Texture whitePawnTexture, blackPawnTexture, boardTexture;
    private static final int TILE_SIZE = 80;
    private final Piece[][] board;

    public ChessBoardScreen(ChessScreenManager gameScreen) {
        this.batch = gameScreen.batch;
        this.board = gameScreen.gm.getBoard();
        loadPieceTextures();
    }

    private void loadPieceTextures() {
        boardTexture = new Texture("chessboard.png");  // Path to chessboard image
        whitePawnTexture = new Texture("white_pawn.png");
        blackPawnTexture = new Texture("black_pawn.png");
        // Load all other piece textures (rook, knight, etc.)
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a solid color (black, in this case)
        Gdx.gl.glClearColor(1, 1, 1, 1); // Clear to white
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin drawing
        batch.begin();
        // Draw the chessboard (you can later add pieces and other elements)
        batch.draw(boardTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        whitePawnTexture.dispose();
        blackPawnTexture.dispose();
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }
}
