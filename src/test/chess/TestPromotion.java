package src.test.chess;

import chess.Board;
import chess.FileRank;
import chess.Pawn;
import chess.Piece;

public class TestPromotion {
    public static void main(String[] args) 
    {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        boolean test1_b = b.setPiece("g2", "g4"); // moves pawn away from Rook
        assert test1_b == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("g7", "g5"); // moves pawn away from Rook
        assert test1_c == true;
        System.out.println(b);
        
        boolean test1_a = b.setPiece("f2", "f4"); // Rook goes forward
        assert test1_a == true;
        System.out.println(b);

        boolean test1_d = b.setPiece("g5", "f4"); // creates block
        assert test1_d == true;
        System.out.println(b);

        boolean test2 = b.setPiece("g4", "g5"); //Rook goes to the right + Illegal Move
        assert test2 == true;
        System.out.println(b);

        //boolean test3 = b.setPiece("h4", "a4"); // Rook goes to the left
        boolean test3 = b.setPiece("g8", "f6"); // Rook goes to the left
        assert test3 == true;
        System.out.println(b);
        
        boolean test4 = b.setPiece("g5", "g6"); // Rook goes backward
        assert test4 == true;
        System.out.println(b);

        boolean test5 = b.setPiece("a7", "a6"); // Rook goes up
        assert test5 == true;
        System.out.println(b);

        boolean test6 = b.setPiece("g6", "g7"); // Illegal Move
        assert test6 == true;
        System.out.println(b);

        boolean test7 = b.setPiece("b7", "b6"); // Rook goes up
        assert test7 == true;
        System.out.println(b);

        /*boolean test8 = b.setPiece("g7", "g8"); // Rook goes up
        assert test8 == true;
        System.out.println(b);*/

        /*Pawn pawn = ((Pawn) b.getPiece(new FileRank("" + 'g' + 7)));

        System.out.println(pawn.willPromote(b, new FileRank("" + 'g' + 8), b.getPiece(new FileRank("" + 'g' + 7)), b.getPiece(new FileRank("" + 'g' + 7)).getFileRank(),'B'));

        System.out.println(b.getPiece(new FileRank("" + 'g' + 8)));

        b.addToBoard(b, new FileRank("c4"), new Pawn(Piece.Color.white, new FileRank("c4")));
        System.out.println(b);*/
    }
}
