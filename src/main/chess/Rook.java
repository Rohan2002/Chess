package chess;

public class Rook extends Piece {
    public Rook(Color c, FileRank fr){
        super(c, Piece.PieceType.rook, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

}
