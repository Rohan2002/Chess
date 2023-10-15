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
        FileRank curr = this.getFileRank();
        if (nextPiece == null)
        {
            // write move policies here.
            int totalFile = nfr.getFile() - curr.getFile();
            int totalRank = nfr.getRank() - curr.getRank();

            if (Math.abs(totalFile) == Math.abs(totalRank))
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
