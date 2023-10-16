package chess;

public class King extends Piece {
    public King(Color c, FileRank fr) {
        super(c, Piece.PieceType.king, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * King moves one square at a time.
         * King can go in all directions
         */
        FileRank curr = this.getFileRank();

        // write move policies here.
        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        if (Math.abs(totalFile) <= 1 && Math.abs(totalRank) <= 1) {
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
