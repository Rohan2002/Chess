package chess;

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

        if (Math.abs(totalFile) <= 1 && Math.abs(totalRank) <= 1) {
            if (nextPiece == null) {
                castling(b, nfr, b.getPiece(curr), curr);
                hasMoved = true;
                return true;
            } else if (this.getColorPiece() != nextPiece.getColorPiece()) {
                hasMoved = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean castling(Board b, FileRank nfr, Piece evalPiece, FileRank curr)
    {
        int changeInRank = nfr.getFile()-curr.getFile();
        if (changeInRank == 2)
        {
            if (!((King) evalPiece).hasMoved)
            {
                String checkfile = Character.toString(nfr.getFile() + 1);
                String checkrank = Integer.toString(nfr.getRank());
                FileRank checkFileRank = new FileRank(checkfile+checkrank);
                if (b.getPiece(checkFileRank).getPieceType() == Piece.PieceType.rook)
                {
                    if (!((Rook) b.getPiece(checkFileRank)).hasMoved)
                    {
                        System.out.println(jumpStraight(b, evalPiece, nfr));
                        if(jumpStraight(b, evalPiece, nfr))
                        {
                            //setrook at f + same rank and king at g + same rank
                            hasMoved = true;
                            return true;
                        }
                    }
                }
            }
        }
        else if (changeInRank == -2)
        {
            if (!((King) evalPiece).hasMoved)
            {
                String checkfile = Character.toString(nfr.getFile() - 2);
                String jumpCheck = Character.toString(nfr.getFile() - 1);
                String checkrank = Integer.toString(nfr.getRank());
                FileRank checkFileRank = new FileRank(checkfile+checkrank);
                FileRank jumpcheckFileRank = new FileRank(jumpCheck+checkrank);
                if (b.getPiece(checkFileRank).getPieceType() == Piece.PieceType.rook)
                {
                    if (!((Rook) b.getPiece(checkFileRank)).hasMoved)
                    {
                        if (jumpStraight(b, evalPiece, jumpcheckFileRank))
                        {
                            //setrook at d + same rank and king at c + same rank
                            hasMoved = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
