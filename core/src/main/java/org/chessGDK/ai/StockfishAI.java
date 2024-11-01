package org.chessGDK.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder;

public class StockfishAI {
    private final Process stockfishProcess;
    private final BufferedReader inputReader;
    private final OutputStream outputStream;
    private final int depth;

    public StockfishAI(String stockfishPath, int depth) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(stockfishPath);
        this.depth = depth;
        stockfishProcess = processBuilder.start();
        inputReader = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
        outputStream = stockfishProcess.getOutputStream();
        outputStream.write("uci\n".getBytes());
        outputStream.flush();
        // Read and print Stockfish's response
        String line;
        while ((line = inputReader.readLine()) != null) {
            System.out.println("Stockfish: " + line);
            if (line.equals("uciok")) {
                System.out.println("Stockfish: " + line);
                break;  // Stop reading when Stockfish signals that it is ready
            }
        }

        System.out.println("Stockfish: Universal Chess Interface - initialized");

        // Close streams and process when done
    }

    public void close() throws IOException {
        try {
            if (inputReader != null) {
                inputReader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (stockfishProcess != null) {
                stockfishProcess.destroy();  // Terminate the Stockfish process
            }
            System.out.println("Stockfish closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String getBestMove(String fen) throws IOException {
        // Send the position in FEN format
        String toSend = "position startpos\n";
        if (!fen.isEmpty())
            toSend = "position fen " + fen + "\n";

        outputStream.write(toSend.getBytes());
        outputStream.flush();

        // Request the best move
        toSend = "go depth " + depth + "\n";
        outputStream.write(toSend.getBytes());
        outputStream.flush();

        // Read the response until we find the best move
        String bestMove = null;
        String line;
        while ((line = inputReader.readLine()) != null) {
            if (line.startsWith("bestmove")) {
                bestMove = line.split(" ")[1];  // Extract the move from the response
                break;
            }
        }

        return bestMove;  // Return the best move found
    }
}
