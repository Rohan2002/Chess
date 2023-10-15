package src.test.chess;

import chess.Board;
import chess.Piece;

public class TestQueen {
    public static void main(String[] args) 
    {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        boolean test1_b = b.setPiece("d2", "d4"); // moves pawn away from Queen
        assert test1_b == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("d4", "d5"); // moves pawn away from Queen
        assert test1_c == true;
        System.out.println(b);
        
        boolean test1_a = b.setPiece("d1", "d4"); // Queen goes forward
        assert test1_a == true;
        System.out.println(b);

        boolean test2 = b.setPiece("d4", "h4"); //Queen goes to the right
        assert test2 == true;
        System.out.println(b);

        boolean test3 = b.setPiece("h4", "a4"); // Queen goes to the left
        assert test3 == true;
        System.out.println(b);
        
        boolean test4 = b.setPiece("a4", "a3"); // Queen goes backward
        assert test4 == true;
        System.out.println(b);

        boolean test5 = b.setPiece("a3", "d6"); // Queen goes up and right
        assert test5 == true;
        System.out.println(b);

        boolean test6 = b.setPiece("d6", "b4"); // Queen goes down and left
        assert test6 == true;
        System.out.println(b);

        boolean test7 = b.setPiece("b4", "c4"); //Queen goes down and right
        assert test7 == true;
        System.out.println(b);

        boolean test8 = b.setPiece("c4", "e6"); // Queen goes up and left
        assert test8 == true;
        System.out.println(b);

        boolean test9 = b.setPiece("b4", "h6"); // Illegal Move
        assert test9 == false;
        System.out.println(b);

        boolean test10 = b.setPiece("b4", "g5"); // Illegal Move
        assert test10 == false;
        System.out.println(b);
    }

}
