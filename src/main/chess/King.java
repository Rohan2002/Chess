package chess;

public class King extends Piece {
    public King(Color c, FileRank fr){
        super(c, Piece.PieceType.king, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * King moves one square at a time.
         * King can go in all directions
         */
        if (nextPiece == null)
        {
            // write move policies here.
            FileRank curr = this.getFileRank();

            int totalFile = nfr.getFile() - curr.getFile();
            int totalRank = nfr.getRank() - curr.getRank();

            if (Math.abs(totalFile) <= 1 && Math.abs(totalRank) <= 1)
            {
                return true;
            }
        }
        else
        {
            // write attack policies here.
        }
        return false;
    }
    
}
