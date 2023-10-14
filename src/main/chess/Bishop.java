package chess;

public class Bishop extends Piece {
    public Bishop(Color c, FileRank fr){
        super(c, Piece.PieceType.bishop, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }
    
}
