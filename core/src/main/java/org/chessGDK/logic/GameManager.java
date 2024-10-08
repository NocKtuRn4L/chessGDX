package org.chessGDK.logic;

import org.chessGDK.pieces.*;
import org.chessGDK.ai.StockfishAI;

import java.io.IOException;


public class GameManager {
    private boolean isWhiteTurn = true;
    private final Piece[][] board;
    private StockfishAI stockfishAI;

    public GameManager(String stockfishPath) throws IOException {
        board = new Piece[8][8];
        setupPieces();
        stockfishAI = new StockfishAI(stockfishPath);
        printBoard();
    }

    private void setupPieces() {
        // Place white pawns on the second row (index 1)
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(true); // White pawns
        }
        // Place white major pieces on the first row (index 0)
        board[0][0] = new Rook(true);    // White rook
        board[0][7] = new Rook(true);    // White rook
        board[0][1] = new Knight(true);  // White knight
        board[0][6] = new Knight(true);  // White knight
        board[0][2] = new Bishop(true);  // White bishop
        board[0][5] = new Bishop(true);  // White bishop
        board[0][3] = new Queen(true);   // White queen
        board[0][4] = new King(true);    // White king

        // Place black pawns on the seventh row (index 6)
        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn(false); // Black pawns
        }
        // Place black major pieces on the eighth row (index 7)
        board[7][0] = new Rook(false);   // Black rook
        board[7][7] = new Rook(false);   // Black rook
        board[7][1] = new Knight(false); // Black knight
        board[7][6] = new Knight(false); // Black knight
        board[7][2] = new Bishop(false); // Black bishop
        board[7][5] = new Bishop(false); // Black bishop
        board[7][3] = new Queen(false);  // Black queen
        board[7][4] = new King(false);   // Black king
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        // Ensure the right piece color is moving according to the turn
        if (piece != null && piece.isValidMove(startX, startY, endX, endY, board)) {
            board[endX][endY] = piece;
            board[startX][startY] = null;
            isWhiteTurn = !isWhiteTurn;
            return true;
        }
        return false;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            System.out.print((row+1) + " ");
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    if (board[row][col] instanceof Pawn) {
                        System.out.print(board[row][col].isWhite() ? "P " : "p " );
                    }
                    else if (board[row][col] instanceof Rook) {
                        System.out.print(board[row][col].isWhite() ? "R " : "r " );
                    }
                    else if (board[row][col] instanceof Knight) {
                        System.out.print(board[row][col].isWhite() ? "N " : "n " );
                    }
                    else if (board[row][col] instanceof Bishop) {
                        System.out.print(board[row][col].isWhite() ? "B " : "b " );
                    }
                    else if (board[row][col] instanceof Queen) {
                        System.out.print(board[row][col].isWhite() ? "Q " : "q " );
                    }
                    else if (board[row][col] instanceof King) {
                        System.out.print(board[row][col].isWhite() ? "K " : "k " );
                    }
                }else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public String getBestMoveAI(String fen) throws IOException {
        return stockfishAI.getBestMove(fen);
    }

    public void exitGame() {
        if (stockfishAI != null) {
            try {
                stockfishAI.close();  // Close the Stockfish AI
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Perform any other cleanup needed for the game
        System.out.println("Game exited.");
    }
}
