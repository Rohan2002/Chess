package chess;

import java.lang.Math;

public class Knight extends Piece {
    public Knight(Color c, FileRank fr) {
        super(c, Piece.PieceType.knight, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * Knight can move in a L shaped manner.
         * This L shape is a vertical L or horizontal L.
         * Vertical L is 2 rows up or down and 1 column left or right.
         * Horizontal L is 2 column left or right and 1 column up or down.
         */
        FileRank curr = this.getFileRank();

        boolean upLShape = (Math.abs(nfr.getFile() - curr.getFile()) == 1
                && Math.abs(nfr.getRank() - curr.getRank()) == 2);
        boolean downLShape = (Math.abs(nfr.getFile() - curr.getFile()) == 2
                && Math.abs(nfr.getRank() - curr.getRank()) == 1);

        if (upLShape || downLShape) {
            if (nextPiece == null) {
                return true;
            } else if (this.getColorPiece() != nextPiece.getColorPiece()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
