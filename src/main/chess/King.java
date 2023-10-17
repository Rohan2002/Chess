package chess;

import java.io.File;

public class King extends Piece {

    boolean hasMoved = false;

    public King(Color c, FileRank fr) {
        super(c, Piece.PieceType.king, fr);
    }

    @Override
    public boolean canMove(Board b, Piece nextPiece, FileRank nfr) {
        /*
         * King moves one square at a time.
         * King can go in all directions
         */
        FileRank curr = this.getFileRank();

        // write move policies here.
        int totalFile = nfr.getFile() - curr.getFile();
        int totalRank = nfr.getRank() - curr.getRank();

        if (castling(b, nfr, this)) {
            return true;
        } else {
            if (Math.abs(totalFile) <= 1 && Math.abs(totalRank) <= 1) {
                if (nextPiece == null) {
                    hasMoved = true;
                    return true;
                } else if (this.getColorPiece() != nextPiece.getColorPiece()) {
                    hasMoved = true;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean castling(Board b, FileRank nfr, Piece evalPiece) {
        FileRank curr = evalPiece.getFileRank();
        int changeInRank = nfr.getFile() - curr.getFile();
        try {
            if (changeInRank == 2) {
                if (!((King) evalPiece).hasMoved) {
                    String checkfile = Character.toString(nfr.getFile() + 1);
                    String checkrank = Integer.toString(nfr.getRank());
                    FileRank checkFileRank = new FileRank(checkfile + checkrank);
                    if (b.getPiece(checkFileRank).getPieceType() == Piece.PieceType.rook) {
                        if (!((Rook) b.getPiece(checkFileRank)).hasMoved) {
                            if (jumpStraight(b, evalPiece, nfr)) {
                                String rookFile = Character.toString(nfr.getFile() - 1);
                                String rookRank = Integer.toString(nfr.getRank());

                                FileRank newRookPos = new FileRank(rookFile + rookRank);

                                Piece newRook = new Rook(b.getPiece(checkFileRank).getColorPiece(), newRookPos);
                                b.removePiece(b.getPiece(checkFileRank));
                                b.putPiece(newRook);
                                // setrook at f + same rank and king at g + same rank
                                hasMoved = true;
                                return true;
                            }
                        }
                    }
                }
            } else if (changeInRank == -2) {
                if (!((King) evalPiece).hasMoved) {
                    String checkfile = Character.toString(nfr.getFile() - 2);
                    String jumpCheck = Character.toString(nfr.getFile() - 1);
                    String checkrank = Integer.toString(nfr.getRank());
                    FileRank checkFileRank = new FileRank(checkfile + checkrank);
                    FileRank jumpcheckFileRank = new FileRank(jumpCheck + checkrank);
                    if (b.getPiece(checkFileRank).getPieceType() == Piece.PieceType.rook) {
                        if (!((Rook) b.getPiece(checkFileRank)).hasMoved) {
                            if (jumpStraight(b, evalPiece, jumpcheckFileRank)) {
                                // setrook at d + same rank and king at c + same rank
                                String rookFile = Character.toString(nfr.getFile() + 1);
                                String rookRank = Integer.toString(nfr.getRank());

                                FileRank newRookPos = new FileRank(rookFile + rookRank);

                                Piece newRook = new Rook(b.getPiece(checkFileRank).getColorPiece(), newRookPos);
                                b.removePiece(b.getPiece(checkFileRank));
                                b.putPiece(newRook);
                                // setrook at f + same rank and king at g + same rank
                                hasMoved = true;
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }
}
