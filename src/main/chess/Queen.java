package chess;

public class Queen extends Piece {
    public Queen(Color c, FileRank fr){
        super(c, Piece.PieceType.queen, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) 
    {
        /*
         * Queen can moves from 1-7 spaces at a time
         * Queen can go in all directions
         */
        if (nextPiece == null)
        {
            // write move policies here.
            FileRank curr = this.getFileRank();

            int totalFile = nfr.getFile() - curr.getFile();
            int totalRank = nfr.getRank() - curr.getRank();

            if ((Math.abs(totalFile) <= 8 && Math.abs(totalRank) == 0) || 
                (Math.abs(totalFile) == 0 && Math.abs(totalRank) <= 8) ||
                (Math.abs(totalFile) == Math.abs(totalRank)))
            {
                //check if jumping over pieces
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
