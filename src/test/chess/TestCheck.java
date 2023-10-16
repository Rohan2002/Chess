package src.test.chess;

import chess.Board;
import chess.Piece.Color;

public class TestCheck {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        b.setActiveColor(Color.white);
        b.setPiece("f2", "f3");
        System.out.println(b);

        b.setActiveColor(Color.black);
        b.setPiece("e7", "e5");
        System.out.println(b);

        b.setActiveColor(Color.white);
        b.setPiece("g2", "g4");
        System.out.println(b);

        b.setActiveColor(Color.black);
        b.setPiece("d8", "h4");
        System.out.println(b);
        
        System.out.println(b.isCheckMate());
    }

}
