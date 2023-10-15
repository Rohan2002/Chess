package chess;

public class Bishop extends Piece {
    public Bishop(Color c, FileRank fr){
        super(c, Piece.PieceType.bishop, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * Bishop can moves from 1-7 spaces at a time
         * Bishop can go in only diagonal directions
         */
        if (nextPiece == null)
        {
            // write move policies here.
            FileRank curr = this.getFileRank();

            int totalFile = nfr.getFile() - curr.getFile();
            int totalRank = nfr.getRank() - curr.getRank();

            if (Math.abs(totalFile) == Math.abs(totalRank))
            {
                // check if jumping over pieces
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
