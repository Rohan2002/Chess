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
        FileRank curr = this.getFileRank();
        if (nextPiece == null)
        {
            // write move policies here.
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
            FileRank nxt = nextPiece.getFileRank();
            boolean nxtPieceIsLeftOrRight = Math.abs(nxt.getFile() - curr.getFile()) == 1;
            boolean nxtPieceIsTop = Math.abs(nxt.getRank() - curr.getRank()) == 1;
            boolean oppositeColors = this.getColorPiece() != nextPiece.getColorPiece();
            return oppositeColors && nxtPieceIsLeftOrRight && nxtPieceIsTop;
        }
        return false;
    }
    
}
