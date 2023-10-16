package src.test.chess;

import chess.Board;
import chess.FileRank;
import chess.King;
import chess.Pawn;
import chess.Piece;

public class TestEnpassant {
    public static void main(String[] args) 
    {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;

        boolean testEdgeCase = b.setPiece("e7", "e5"); // moves pawn away from king
        assert testEdgeCase == true;

        boolean test1_b = b.setPiece("e5", "e4"); // moves pawn away from king
        assert test1_b == true;
        System.out.println(b);

        boolean test1_d = b.setPiece("d2", "d4"); // moves pawn away from king
        assert test1_d == true;
        System.out.println(b);

        Pawn pawn = (Pawn) b.getPiece(new FileRank("" + 'e' + 4));

        System.out.println(pawn.willEnpassant(b, new FileRank("" + 'd' + 3), pawn, pawn.getFileRank()));

        /*boolean test2 = b.setPiece("e2", "e3");
        assert test2 == true;
        System.out.println(b);*/
    }

}
