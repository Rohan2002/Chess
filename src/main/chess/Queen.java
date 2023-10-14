package chess;

public class Queen extends Piece {
    public Queen(Color c, FileRank fr){
        super(c, Piece.PieceType.queen, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }
    
}
