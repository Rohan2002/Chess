package src.test.chess;

import chess.Board;
import chess.Piece;

public class TestBoard {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);
        Piece p = b.getPiece("h8");
        System.out.println("Got Piece: " + p);

        b.setPiece("e2", "e4");
        System.out.println(b);
    }
}
