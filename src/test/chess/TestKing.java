package src.test.chess;

import chess.Board;
import chess.Piece;

public class TestKing {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;

        boolean testEdgeCase = b.setPiece("e1", "e2");
        assert testEdgeCase == false;

        boolean test1_b = b.setPiece("e2", "e4"); // moves pawn away from king
        assert test1_b == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("e4", "e5"); // moves pawn away from king
        assert test1_c == true;
        System.out.println(b);

        boolean test1_a = b.setPiece("e1", "e2"); // king goes forward
        assert test1_a == true;
        System.out.println(b);

        boolean test2 = b.setPiece("e2", "e3");
        assert test2 == true;
        System.out.println(b);

        boolean test3 = b.setPiece("e3", "e4");
        assert test3 == true;
        System.out.println(b);

        boolean test4 = b.setPiece("e4", "e3"); // king goes backward
        assert test4 == true;
        System.out.println(b);

        boolean test5 = b.setPiece("e3", "d3"); // king goes left
        assert test5 == true;
        System.out.println(b);

        boolean test6 = b.setPiece("d3", "e3"); // king goes right
        assert test6 == true;
        System.out.println(b);

        boolean test7 = b.setPiece("e3", "d4"); // king goes up and left
        assert test7 == true;
        System.out.println(b);

        boolean test8 = b.setPiece("d4", "e3"); // king goes down and right
        assert test8 == true;
        System.out.println(b);

        boolean test9 = b.setPiece("e3", "f4"); // king goes up and right
        assert test9 == true;
        System.out.println(b);

        boolean test10 = b.setPiece("f4", "e3"); // king goes down and left
        assert test10 == true;
        System.out.println(b);

        boolean test11 = b.setPiece("e3", "e1"); // king goes down 2
        assert test11 == false;
        System.out.println(b);
    }

}
