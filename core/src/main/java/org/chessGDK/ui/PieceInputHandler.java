package org.chessGDK.ui;

import com.badlogic.gdx.graphics.Camera;
import org.chessGDK.logic.GameManager;
import org.chessGDK.pieces.Piece;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.chessGDK.utils.CoordinateUtils;

public class PieceInputHandler extends InputAdapter {
    private Piece selectedPiece = null;
    private Vector2 startPos = new Vector2();
    private Vector3 liftPositon = new Vector3();
    private Vector3 dropPosition = new Vector3();
    private boolean firstClick = true; // To track if it's the first click
    private boolean isDragging = false;

    private final GameManager gm;
    private final Camera camera;
    private final Piece[][] board;
    private final int TILE_SIZE;

    private CoordinateUtils coords;

    public PieceInputHandler(GameManager gm, Camera camera, Piece[][] board, int tileSize) {
        this.gm = gm;
        this.camera = camera;
        this.board = board;
        this.TILE_SIZE = tileSize;
        coords = new CoordinateUtils(TILE_SIZE);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convert screen coordinates to world coordinates
        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        camera.unproject(worldCoordinates); // Convert to world coordinates
        if (firstClick) {
            liftPositon.set(worldCoordinates); // Store the first click position
            handleLift(screenX, screenY);
        } else {
            dropPosition.set(worldCoordinates); // Store the second click position
            handlePlace(screenX, screenY);
            isDragging = false;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (isDragging && selectedPiece != null) {
            // Update the piece's position to follow the cursor
            Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
            camera.unproject(worldCoordinates);
            selectedPiece.setPosition(worldCoordinates.x - 50, worldCoordinates.y - 50);
        }
        return true;
    }

    // Method to handle selecting a piece
    private void handleLift(int screenX, int screenY) {
        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        camera.unproject(worldCoordinates);

        int liftX = (int) (worldCoordinates.x / TILE_SIZE);
        int liftY = (int) (worldCoordinates.y / TILE_SIZE);

        // First click: Select a piece if there's one at this position
        selectedPiece = board[liftY][liftX];
        if (selectedPiece != null) {
            if (selectedPiece.isWhite() != gm.isWhiteTurn()) {
                System.out.println("Not your turn");
                return;
            }
            isDragging = true;
            firstClick = false; // Switch to second click
            liftX += 'a';
            liftY += '1';
            startPos = new Vector2(liftX, liftY);
            System.out.println("Selected piece at: " + (char) liftX + ", " + (char) liftY);
        }
    }

    // Method to handle placing the piece
    private void handlePlace(int screenX, int screenY) {
        Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
        camera.unproject(worldCoordinates);
        int placeX = (int) (worldCoordinates.x / TILE_SIZE);
        int placeY = (int) (worldCoordinates.y / TILE_SIZE);
        placeX += 'a';
        placeY += '1';
        String move = String.valueOf((char) startPos.x) +
            (char) startPos.y +
            (char) placeX +
            (char) placeY;
        if (gm.movePiece(move)) {
            System.out.println("Placed piece at: " + (char) placeX + ", " + (char) placeY);
            placeX -= 'a';
            placeY -= '1';
            selectedPiece.setPosition(placeX * TILE_SIZE, placeY * TILE_SIZE);
            isDragging = false;
        } else {
            startPos.x -= 'a';
            startPos.y -= '1';
            selectedPiece.setPosition(startPos.x * TILE_SIZE, startPos.y * TILE_SIZE);
        }
        firstClick = true; // Reset for the next turn
        selectedPiece = null;  // Reset selection
        startPos = null;
    }
}
