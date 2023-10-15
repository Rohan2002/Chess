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

        boolean test1_b = b.setPiece("e2", "e4"); // init start
        assert test1_b == true;
        
        b.initBoard();
        boolean test1_a = b.setPiece("e2", "e3");
        assert test1_a == true;

        boolean test2 = b.setPiece("e3", "e4");
        assert test2 == true;

        boolean test3 = b.setPiece("e4", "e6");
        assert test3 == false;

        boolean test4 = b.setPiece("e6", "e5");
        assert test4 == false;

        boolean test5 = b.setPiece("e6", "e7"); // piece exists at e7.
        assert test5 == false;

    }

}
