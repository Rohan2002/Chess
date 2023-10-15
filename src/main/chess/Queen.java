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
        FileRank curr = this.getFileRank();

        if (nextPiece == null)
        {
            // write move policies here.
            int totalFile = nfr.getFile() - curr.getFile();
            int totalRank = nfr.getRank() - curr.getRank();

            if ((Math.abs(totalFile) <= 8 && Math.abs(totalRank) == 0) || 
                (Math.abs(totalFile) == 0 && Math.abs(totalRank) <= 8))
            {
                if (jumpStraight(b, nextPiece, nfr))
                {
                    return true;
                }
                else
                {
                    return false;
                }   
            }
            else if ((Math.abs(totalFile) == Math.abs(totalRank)))
            {
                if (jumpDiagonal(b, nextPiece, nfr))
                {
                    return true;
                }
                else
                {
                    return false;
                }  
            }
        }
        else
        {
            // write attack policies here.
            FileRank nxt = nextPiece.getFileRank();
            boolean nxtPieceIsLeftOrRight = Math.abs(nxt.getFile() - curr.getFile()) == 1;
            boolean nxtPieceIsTop = Math.abs(nxt.getRank() - curr.getRank()) == 1;
            boolean oppositeColors = this.getColorPiece() != nextPiece.getColorPiece();
            return oppositeColors && nxtPieceIsLeftOrRight && nxtPieceIsTop;
        }
        return false;
    }
    
}
