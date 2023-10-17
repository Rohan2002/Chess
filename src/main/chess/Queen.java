package chess;

public class Queen extends Piece {
    public Queen(Color c, FileRank fr) {
        super(c, Piece.PieceType.queen, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * Queen can moves from 1-7 spaces at a time
         * Queen can go in all directions
         */
        FileRank curr = this.getFileRank();

        // write move policies here.
        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        if ((Math.abs(totalFile) <= 8 && Math.abs(totalRank) == 0) ||
                (Math.abs(totalFile) == 0 && Math.abs(totalRank) <= 8)) {
            if (jumpStraight(b, nextPiece, nfr)) {
                if (nextPiece == null) {
                    return true;
                } else if (this.getColorPiece() != nextPiece.getColorPiece()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if ((Math.abs(totalFile) == Math.abs(totalRank))) {
            if (jumpDiagonal(b, nextPiece, nfr)) {
                if (nextPiece == null) {
                    return true;
                } else if (this.getColorPiece() != nextPiece.getColorPiece()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

}
