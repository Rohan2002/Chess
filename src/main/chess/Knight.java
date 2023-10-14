package chess;

public class Knight extends Piece {
    public Knight(Color c, FileRank fr){
        super(c, Piece.PieceType.knight, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }
    
    
}
