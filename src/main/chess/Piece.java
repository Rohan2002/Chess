/*
 * 
 * Warning: This abstract class cannot be instantiated.
 * It can only be extended as a superclass.
 */
package chess;

public abstract class Piece {
    enum Color {
        white, black
    };

    enum PieceType {
        pawn, rook, knight, bishop, queen, king
    };

    private Color colorPiece;
    private PieceType pieceType;
    private FileRank fileRank;

    public abstract boolean canMove(Board b, Piece nextPiece, FileRank nfr);
    
    public Piece(Color c, PieceType p, FileRank fr) {
        this.colorPiece = c;
        this.pieceType = p;
        this.fileRank = fr;
    }

    public FileRank getFileRank() {
        return this.fileRank;
    }

    public void setFileRank(FileRank fileRank) {
        this.fileRank = fileRank;
    }

    public Color getColorPiece() {
        return this.colorPiece;
    }

    public void setColorPiece(Color colorPiece) {
        this.colorPiece = colorPiece;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        String c = getColorPiece() == Color.black ? "b" : "w";
        return c + "_" + getPieceType() + "_" + getFileRank();
    }

}
