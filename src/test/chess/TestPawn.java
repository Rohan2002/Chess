package src.test.chess;

import chess.Board;

public class TestPawn {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

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

        // attack sequence. white pawn attacks black pawn
        b.initBoard();
        assert true == b.setPiece("e2", "e3");
        assert true == b.setPiece("d7", "d6");
        assert true == b.setPiece("d6", "d5");
        assert true == b.setPiece("d5", "d4");
        assert true == b.setPiece("e3", "d4"); // attack.

        // attack sequence. black pawn attacks white pawn
        b.initBoard();
        assert true == b.setPiece("e7", "e5");
        assert true == b.setPiece("e5", "e4");
        assert true == b.setPiece("f2", "f3");
        assert true == b.setPiece("e4", "f3"); // attack.

        // move white back (instead of forward) should be false
        b.initBoard();
        assert true == b.setPiece("e2", "e4");
        assert false == b.setPiece("e4", "e3");

        // move black back (instead of forward) should be false
        assert true == b.setPiece("e7", "e5");
        assert false == b.setPiece("e5", "e4");

        // Try to attack white -> black pawn forward only.
        assert false == b.setPiece("e4", "e5");

    }

}
