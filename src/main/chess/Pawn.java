package chess;

import java.lang.Math;

public class Pawn extends Piece {
    public Pawn(Color c, FileRank fr) {
        super(c, Piece.PieceType.pawn, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * Pawn moves one square at a time.
         * Pawn can move two squares for first time move.
         * Pawn moves only forward.
         * Pawn cannot move backward.
         */
        Boolean isBlack = this.getColorPiece() == Color.black;
        FileRank curr = this.getFileRank();
        if (nextPiece == null) {
            // write move policies here.

            int allowed_moves = 1;
            // Pawn is at the start row.
            if ((isBlack && curr.getRank() == 7) || (!isBlack && curr.getRank() == 2)) {
                allowed_moves = 2;
            }

            boolean whitePawnUpwards = this.getColorPiece() == Color.white && nfr.getRank() - curr.getRank() > 0
                    && nfr.getRank() - curr.getRank() <= allowed_moves;
            boolean blackPawnDownwards = this.getColorPiece() == Color.black && curr.getRank() - nfr.getRank() > 0
                    && curr.getRank() - nfr.getRank() <= allowed_moves;
            
            boolean sameColumn = curr.getFile() == nfr.getFile();
            return sameColumn && (whitePawnUpwards || blackPawnDownwards);
        } else {
            // write attack policies here.
            FileRank nxt = nextPiece.getFileRank();
            boolean nxtPieceIsLeftOrRight = Math.abs(nxt.getFile() - curr.getFile()) == 1;
            boolean nxtPieceIsTop = nxt.getRank() - curr.getRank() == 1;
            boolean oppositeColors = this.getColorPiece() != nextPiece.getColorPiece();
            return oppositeColors && nxtPieceIsLeftOrRight && nxtPieceIsTop;
        }
    }

}
