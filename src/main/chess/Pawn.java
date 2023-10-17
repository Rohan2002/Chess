package chess;

import java.lang.Math;

public class Pawn extends Piece {
    private boolean enpassant;

    public Pawn(Color c, FileRank fr) {
        super(c, Piece.PieceType.pawn, fr);
        this.enpassant = false;
    }

    public boolean getEnpassant() {
        return this.enpassant;
    }

    public void setEnpassant(boolean enpassant) {
        this.enpassant = enpassant;
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
        FileRank curr = this.getFileRank();
        if (nextPiece == null) {
            // write move policies here.

            int allowed_moves = 1;
            // Pawn is at the start row.
            if ((isBlack && curr.getRank() == 7) || (!isBlack && curr.getRank() == 2)) {
                allowed_moves = 2;
            }

            boolean whitePawnUpwards = this.getColorPiece() == Color.white && nfr.getRank() - curr.getRank() > 0
                    && nfr.getRank() - curr.getRank() <= allowed_moves;
            boolean blackPawnDownwards = this.getColorPiece() == Color.black && curr.getRank() - nfr.getRank() > 0
                    && curr.getRank() - nfr.getRank() <= allowed_moves;

            boolean sameColumn = curr.getFile() == nfr.getFile();

            /*
             * else if (willEnpassant(b, nfr, nextPiece, curr, promo))
             * {
             * return true;
             * }
             */
            boolean validPawnMove = sameColumn && (whitePawnUpwards || blackPawnDownwards);
            if (Math.abs(nfr.getRank() - curr.getRank()) == 2 && validPawnMove) {
                this.setEnpassant(true);
            }

            boolean enPassantValid = enpassant(b, nfr, this);

            return enPassantValid || validPawnMove; // Valid Enpassant move will move pawn OR Valid Pawn move will move
                                                    // pawn.
        } else {
            // write attack policies here.
            FileRank nxt = nfr;
            boolean nxtPieceIsLeftOrRight = Math.abs(nxt.getFile() - curr.getFile()) == 1;
            boolean nxtPieceIsTop = Math.abs(nxt.getRank() - curr.getRank()) == 1;
            boolean oppositeColors = this.getColorPiece() != nextPiece.getColorPiece();
            return oppositeColors && nxtPieceIsLeftOrRight && nxtPieceIsTop;
        }
    }

    public boolean enpassant(Board b, FileRank nfr, Piece evalPiece) {
        FileRank curr = evalPiece.getFileRank();
        int currRank = curr.getRank();

        if (currRank == 5 && evalPiece.getColorPiece() == Piece.Color.white) {
            if (nfr.equals(new FileRank("" + ((char) (curr.getFile() + 1)) + (curr.getRank() + 1)))) {
                Piece potentialEnpassantVictim = b
                        .getPiece(new FileRank("" + ((char) (nfr.getFile())) + (nfr.getRank() - 1)));
                if (potentialEnpassantVictim.getPieceType() == Piece.PieceType.pawn) {
                    if (((Pawn) potentialEnpassantVictim).enpassant == true) {
                        b.removePiece(potentialEnpassantVictim);
                        return true;
                    }
                }
            } else if (nfr.equals(new FileRank("" + ((char) (curr.getFile() - 1)) + (curr.getRank() + 1)))) {
                Piece potentialEnpassantVictim = b
                        .getPiece(new FileRank("" + ((char) (nfr.getFile())) + (nfr.getRank() - 1)));
                if (potentialEnpassantVictim.getPieceType() == Piece.PieceType.pawn) {
                    if (((Pawn) potentialEnpassantVictim).enpassant == true) {
                        b.removePiece(potentialEnpassantVictim);
                        return true;
                    }
                }
            }
        } else if (currRank == 4 && evalPiece.getColorPiece() == Piece.Color.black) {
            if (nfr.equals(new FileRank("" + ((char) (curr.getFile() + 1)) + (curr.getRank() - 1)))) {
                Piece potentialEnpassantVictim = b
                        .getPiece(new FileRank("" + ((char) (nfr.getFile())) + (nfr.getRank() + 1)));
                if (potentialEnpassantVictim.getPieceType() == Piece.PieceType.pawn) {
                    if (((Pawn) potentialEnpassantVictim).enpassant == true) {
                        b.removePiece(potentialEnpassantVictim);
                        return true;
                    }
                }
            } else if (nfr.equals(new FileRank("" + ((char) (curr.getFile() - 1)) + (curr.getRank() - 1)))) {
                Piece potentialEnpassantVictim = b
                        .getPiece(new FileRank("" + ((char) (nfr.getFile())) + (nfr.getRank() + 1)));
                if (potentialEnpassantVictim.getPieceType() == Piece.PieceType.pawn) {
                    if (((Pawn) potentialEnpassantVictim).enpassant == true) {
                        b.removePiece(potentialEnpassantVictim);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
