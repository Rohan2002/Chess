package src.test.chess;

import java.util.ArrayList;

import chess.Board;
import chess.FileRank;
import chess.Piece;

public class TestBoard {
    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);

        Piece p = b.getPiece("h8");
        boolean test0 = p.toString().equalsIgnoreCase("b_rook_h8");
        assert test0 == true;

        Piece kingVictim = b.getPiece("e3");
        ArrayList<FileRank> kingPoss = b.possibleKingPositions(b, kingVictim);

        ArrayList<FileRank> expectedKingPoss = new ArrayList<>();
        expectedKingPoss.add(new FileRank("d2"));
        expectedKingPoss.add(new FileRank("d3"));
        expectedKingPoss.add(new FileRank("d4"));

        expectedKingPoss.add(new FileRank("e2"));
        expectedKingPoss.add(new FileRank("e3"));
        expectedKingPoss.add(new FileRank("e4"));

        expectedKingPoss.add(new FileRank("f2"));
        expectedKingPoss.add(new FileRank("f3"));
        expectedKingPoss.add(new FileRank("f4"));

        assert kingPoss.size() == expectedKingPoss.size();
        boolean listEqual = true;
        for (int i = 0; i < kingPoss.size(); i++) {
            if (!kingPoss.get(i).equals(expectedKingPoss.get(i))) {
                listEqual = false;
                break;
            }
        }
        assert listEqual == true;


        ArrayList<Integer> t1 = new ArrayList<Integer>();
        t1.add(1);
        t1.add(2);
        t1.add(3);
        int counter = 0;
        for(Integer i: t1){
            if(i == 2){
                continue;
            }
            counter++;
        }
        System.out.println("Counter: " + counter);
    }
}
