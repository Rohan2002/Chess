package chess;

import java.lang.Math;

public class Pawn extends Piece {
    char promo;
    boolean enpassant;
    public Pawn(Color c, FileRank fr) {
        super(c, Piece.PieceType.pawn, fr);
    }

    //constructor if promotion is given with move
    public Pawn(Color c, FileRank fr, char promotion) {
        super(c, Piece.PieceType.pawn, fr);
        promo = promotion;
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

            //checks for promotion and conducts promotion
            if (willPromote(b, nfr, nextPiece, curr, promo))
            {
                promotion(b, nfr, nextPiece, curr, promo);
                return true;
            }
            /*else if (willEnpassant(b, nfr, nextPiece, curr, promo))
            {
                return true;
            }*/
            else if (nfr.getRank() - curr.getRank() == 2 && (sameColumn && (whitePawnUpwards || blackPawnDownwards)))
            {
                enpassant = true;
            }

            return sameColumn && (whitePawnUpwards || blackPawnDownwards);
        } 
        else {
            // write attack policies here.
            FileRank nxt = nfr;
            boolean nxtPieceIsLeftOrRight = Math.abs(nxt.getFile() - curr.getFile()) == 1;
            boolean nxtPieceIsTop = Math.abs(nxt.getRank() - curr.getRank()) == 1;
            boolean oppositeColors = this.getColorPiece() != nextPiece.getColorPiece();
            willPromote(b, nfr, nextPiece, curr, promo);
            return oppositeColors && nxtPieceIsLeftOrRight && nxtPieceIsTop;
        }
    }

    public Piece promotion(Board b, FileRank nfr, Piece evalPiece, FileRank curr, char changedPiece)
    {
        switch (changedPiece)
        {
            case 'N':
                return new Knight(evalPiece.getColorPiece(), nfr);

            case 'R':
                return new Rook(evalPiece.getColorPiece(), nfr);

            case 'B':
                return new Bishop(evalPiece.getColorPiece(), nfr);

            case 'Q':
                return new Queen(evalPiece.getColorPiece(), nfr);

            default: 
                return null;
        }
    }

    public boolean willPromote(Board b, FileRank nfr, Piece evalPiece, FileRank curr, char changedPiece)
    {
        if ((this.getColorPiece() == Piece.Color.white && curr.getRank() == '8') || 
                (this.getColorPiece() == Piece.Color.black && curr.getRank() == '1'))
        {
            promotion(b,nfr,this,curr,promo);
            return true;
        }
        return false;
    }

    /*public boolean enpassant(Board b, FileRank nfr, Piece evalPiece, FileRank curr)
    {
        if ()
        {

        }
        return true;
    }

    public boolean willEnpassant(Board b, FileRank nfr, Piece evalPiece, FileRank curr, char changedPiece)
    {
        char currFile = curr.getFile();
        int currRank = curr.getRank(); 

        char nextMoveFile = nfr.getFile();
        int nextMoveRank = nfr.getRank();

        Piece potentialEnpassantVictim = b.getPiece(new FileRank("" + ((char) (nfr.getFile()+1)) + (nfr.getRank())));

        if (currRank == 5 && evalPiece.getColorPiece() == Piece.Color.white)
        {
            if (nfr.equals(new FileRank("" + ((char) (curr.getFile()+1)) + (curr.getRank()+1))) || 
                nfr.equals(new FileRank("" + ((char) (curr.getFile()-1)) + (curr.getRank()+1))) )
            {
                if (potentialEnpassantVictim.getPieceType() == Piece.PieceType.pawn)
                {
                    if (((Pawn) potentialEnpassantVictim).enpassant == true)
                    {
                        enpassant(b, nfr, evalPiece, curr);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void resetEnpassant(Board b, Color color)
    {
        for (Piece p : b.getAlivePieces()) {
            if (p.getColorPiece() == color && p.getPieceType() == Piece.PieceType.pawn) {
                ((Pawn) p).enpassant = false;
            }
        }
    }*/
}
