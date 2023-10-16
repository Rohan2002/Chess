package src.test.chess;

import chess.Board;
import chess.FileRank;
import chess.King;
import chess.Pawn;
import chess.Piece;

public class TestCastling {
    public static void main(String[] args) 
    {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;

        boolean testEdgeCase = b.setPiece("b2", "b4"); // moves pawn away from king
        assert testEdgeCase == true;

        boolean test1_b = b.setPiece("c2", "c4"); // moves pawn away from king
        assert test1_b == true;
        System.out.println(b);

        boolean test1_d = b.setPiece("d2", "d4"); // moves pawn away from king
        assert test1_d == true;
        System.out.println(b);

        /*boolean test1_c = b.setPiece("d1", "d3"); 
        assert test1_c == true;
        System.out.println(b);*/
        
        boolean test1_a = b.setPiece("c1", "a3"); 
        assert test1_a == true;
        System.out.println(b);

        boolean test4 = b.setPiece("b1", "c3"); 
        assert test4 == true;
        System.out.println(b);

        King king = (King) b.getPiece(new FileRank("" + 'e' + 1));

        System.out.println(king.castling(b, new FileRank("" + 'c' + 1), king, king.getFileRank()));

        /*boolean test2 = b.setPiece("e2", "e3");
        assert test2 == true;
        System.out.println(b);*/
    }

}
