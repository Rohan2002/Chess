package src.test.chess;

import chess.Board;
import chess.Piece;

public class TestBishop {
    public static void main(String[] args) 
    {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        boolean test1_b = b.setPiece("d2", "d4"); // moves pawn away from Bishop
        assert test1_b == true;
        System.out.println(b);

        boolean test1_c = b.setPiece("d4", "d5"); // moves pawn away from Bishop
        assert test1_c == true;
        System.out.println(b);
        
        boolean test1_a = b.setPiece("c1", "f4"); // Bishop goes up and right
        assert test1_a == true;
        System.out.println(b);

        boolean test2 = b.setPiece("f4", "d2"); //  Bishop goes down and left
        assert test2 == true;
        System.out.println(b);

        boolean test3 = b.setPiece("d2", "a5"); //  Bishop goes up and left
        assert test3 == true;
        System.out.println(b);
        
        boolean test4 = b.setPiece("a5", "d2"); //  Bishop goes down and right
        assert test4 == true;
        System.out.println(b);

        boolean test5 = b.setPiece("d2", "d3"); // Illegal Moves
        assert test5 == false;
        System.out.println(b);

        boolean test6 = b.setPiece("d2", "h4"); // Illegal Moves
        assert test6 == false;
        System.out.println(b);
    }
}
