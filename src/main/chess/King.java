package chess;

public class King extends Piece {
    public King(Color c, FileRank fr){
        super(c, Piece.PieceType.king, fr);
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
