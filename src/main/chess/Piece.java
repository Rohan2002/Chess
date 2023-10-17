/*
 * 
 * Warning: This abstract class cannot be instantiated.
 * It can only be extended as a superclass.
 */
package chess;

public abstract class Piece {
    public enum Color {
        white, black
    };

    public enum PieceType {
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

    public boolean equals(Piece p) {
        return (p.getColorPiece() == this.getColorPiece() &&
                p.getFileRank() == this.getFileRank() &&
                p.getPieceType() == this.getPieceType());
    }

    public void resetEnpassant(Board b) {
        Color color = this.getColorPiece();
        for (Piece p : b.getAlivePieces()) {
            if (p.getColorPiece() == color && p.getPieceType() == Piece.PieceType.pawn && p instanceof Pawn) {
                ((Pawn) p).setEnpassant(false);
            }
        }
    }

    @Override
    public String toString() {
        String c = getColorPiece() == Color.black ? "b" : "w";
        return c + "_" + getPieceType() + "_" + getFileRank();
    }

    public boolean jumpDiagonal(Board b, Piece nextPiece, FileRank nfr) {
        FileRank curr = this.getFileRank();

        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        for (int i = 1; i < (Math.abs(totalFile)); i++) {
            if (totalFile > 0 && totalRank > 0) {
                if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() - i)) + (nfr.getRank() - i))) != null) {
                    return false;
                }
            } else if (totalFile < 0 && totalRank < 0) {
                if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() + i)) + (nfr.getRank() + i))) != null) {
                    return false;
                }
            } else if (totalFile < 0 && totalRank > 0) {
                if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() + i)) + (nfr.getRank() - i))) != null) {
                    return false;
                }
            } else if (totalFile > 0 && totalRank < 0) {
                if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() - i)) + (nfr.getRank() + i))) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean jumpStraight(Board b, Piece nextPiece, FileRank nfr) {
        FileRank curr = this.getFileRank();

        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        char changingDimension = (Math.abs(totalFile) > 0) ? 'f' : 'r';

        for (int i = 1; i < (Math.abs(totalFile) + Math.abs(totalRank)); i++) {
            if (changingDimension == 'f') {
                if (totalFile > 0) {
                    if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() - i)) + nfr.getRank())) != null) {
                        return false;
                    }
                } else {
                    if (b.getPiece(new FileRank("" + ((char) (nfr.getFile() + i)) + nfr.getRank())) != null) {
                        return false;
                    }
                }
            } else {
                if (totalRank > 0) {
                    if (b.getPiece(new FileRank("" + nfr.getFile() + (nfr.getRank() - i))) != null) {
                        return false;
                    }
                } else {
                    if (b.getPiece(new FileRank("" + nfr.getFile() + (nfr.getRank() + i))) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
