package chess;

public class Rook extends Piece {
    public Rook(Color c, FileRank fr){
        super(c, Piece.PieceType.rook, fr);
    }

    @Override
    public boolean canMove(int rowIndex, int colIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

    @Override
    public boolean moveTo(int rowIndex, int colIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveTo'");
    }
    
}
