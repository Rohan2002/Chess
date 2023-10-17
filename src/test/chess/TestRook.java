package src.test.chess;

import chess.Board;

public class TestRook {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        boolean test1_b = b.setPiece("a2", "a4"); // moves pawn away from Rook
        assert test1_b == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("a4", "a5"); // moves pawn away from Rook
        assert test1_c == true;
        System.out.println(b);

        boolean test1_a = b.setPiece("a1", "a4"); // Rook goes forward
        assert test1_a == true;
        System.out.println(b);

        boolean test1_d = b.setPiece("e2", "e4"); // creates block
        assert test1_d == true;
        System.out.println(b);

        boolean test2 = b.setPiece("a4", "h4"); // Rook goes to the right + Illegal Move
        assert test2 == false;
        System.out.println(b);

        // boolean test3 = b.setPiece("h4", "a4"); // Rook goes to the left
        boolean test3 = b.setPiece("a4", "d4"); // Rook goes to the left
        assert test3 == true;
        System.out.println(b);

        boolean test4 = b.setPiece("d4", "d3"); // Rook goes backward
        assert test4 == true;
        System.out.println(b);

        boolean test5 = b.setPiece("d3", "e3"); // Rook goes up
        assert test5 == true;
        System.out.println(b);

        boolean test6 = b.setPiece("e3", "e5"); // Illegal Move
        assert test6 == false;
        System.out.println(b);
    }
}
