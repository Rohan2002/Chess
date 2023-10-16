package chess;

public class Rook extends Piece {
    boolean hasMoved = false;
    public Rook(Color c, FileRank fr){
        super(c, Piece.PieceType.rook, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr)
    {
        /*
         * Rook can moves from 1-7 spaces at a time
         * Rook can go in all directions
         */
        FileRank curr = this.getFileRank();
        
        // write move policies here.
        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        if ((Math.abs(totalFile) <= 8 && Math.abs(totalRank) == 0) || 
            (Math.abs(totalFile) == 0 && Math.abs(totalRank) <= 8))
        {
            if (jumpStraight(b, nextPiece, nfr))
            {
                if (nextPiece == null)
                {
                    hasMoved = true;
                    return true;
                }
                else if (this.getColorPiece() != nextPiece.getColorPiece())
                {
                    hasMoved = true;
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }   
        }
        return false;
    }
}
