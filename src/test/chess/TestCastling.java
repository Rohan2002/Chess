package src.test.chess;

import chess.Board;
import chess.FileRank;
import chess.King;
import chess.Pawn;
import chess.Piece;

public class TestCastling {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;

        boolean testEdgeCase = b.setPiece("b7", "b5"); // moves pawn away from king
        assert testEdgeCase == true;

        boolean test1_b = b.setPiece("c7", "c5"); // moves pawn away from king
        assert test1_b == true;
        System.out.println(b);

        boolean test1_d = b.setPiece("d7", "d5"); // moves pawn away from king
        assert test1_d == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("d8", "d6");
        assert test1_c == true;
        System.out.println(b);

        boolean test1_a = b.setPiece("c8", "a6");
        assert test1_a == true;
        System.out.println(b);

        boolean test4 = b.setPiece("b8", "c6");
        assert test4 == true;
        System.out.println(b);

        King king = (King) b.getPiece(new FileRank("" + 'e' + 8));

        System.out.println(king.castling(b, new FileRank("" + 'c' + 8), king, king.getFileRank()));

        /*
         * boolean test2 = b.setPiece("e2", "e3");
         * assert test2 == true;
         * System.out.println(b);
         */
    }

}
