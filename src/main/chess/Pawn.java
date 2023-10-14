package chess;

public class Pawn extends Piece {
    public Pawn(Color c, FileRank fr){
        super(c, Piece.PieceType.pawn, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * Pawn moves one square at a time.
         * Pawn can move two squares for first time move.
         * Pawn moves only forward.
         * Pawn cannot move backward.
         */
        Boolean isBlack = this.getColorPiece() == Color.black;
        if (nextPiece == null){
            // write move policies here.
            FileRank curr = this.getFileRank();

            int allowed_moves = 1;
            // Pawn is at the start row.
            if ((isBlack && curr.getRank() == 7) || (!isBlack && curr.getRank() == 2)){
                allowed_moves = 2;
            }

            if (curr.getFile() == nfr.getFile() && nfr.getRank() - curr.getRank() <= allowed_moves){
                return true;
            }
        }
        else{
            // write attack policies here.
        }
        return false;
    }
    
}
