package src.test.chess;

import chess.Board;

public class TestKnight {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        boolean test1 = b.setPiece("b1", "c3"); // init start white knight
        assert test1 == true;
        System.out.println(b);


        boolean test2 = b.setPiece("c3", "d1"); // white knight valid but existing piece.
        assert test2 == false;
        System.out.println(b);

        boolean test3 = b.setPiece("c3", "d5"); // white knight valid move.
        assert test3 == true;
        System.out.println(b);

        boolean test4 = b.setPiece("d5", "e7"); // white knight valid move.
        assert test4 == true;
        System.out.println(b);

    }
}
