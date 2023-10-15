package src.test.chess;

import chess.Board;
import chess.Piece;

public class TestBoard {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;
    }
}
