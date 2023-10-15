package chess;

import chess.Piece.Color;

public class Board {

    private int _board_dim;
    private Piece[][] board;

    public Board() {
        this._board_dim = 8;
        this.board = new Piece[_board_dim][_board_dim];
    }

    public Piece[] createPawnRank(Color c) {
        int rank = c == Color.black ? 7 : 2;
        Piece[] pawnRankPieces = new Piece[this._board_dim];
        for (int file = 0; file < pawnRankPieces.length; file++) {
            pawnRankPieces[file] = new Pawn(c, new FileRank("" + FileRank.files[file] + rank));
        }
        return pawnRankPieces;
    }

    public Piece[] createNonPawnRank(Color c) {
        int rank = c == Color.black ? 8 : 1;
        Piece[] nonPawnRankPieces = new Piece[this._board_dim];

        nonPawnRankPieces[0] = new Rook(c, new FileRank("" + FileRank.files[0] + rank));
        nonPawnRankPieces[1] = new Knight(c, new FileRank("" + FileRank.files[1] + rank));
        nonPawnRankPieces[2] = new Bishop(c, new FileRank("" + FileRank.files[2] + rank));

        nonPawnRankPieces[3] = new Queen(c, new FileRank("" + FileRank.files[3] + rank));
        nonPawnRankPieces[4] = new King(c, new FileRank("" + FileRank.files[4] + rank));

        nonPawnRankPieces[5] = new Bishop(c, new FileRank("" + FileRank.files[5] + rank));
        nonPawnRankPieces[6] = new Knight(c, new FileRank("" + FileRank.files[6] + rank));
        nonPawnRankPieces[7] = new Rook(c, new FileRank("" + FileRank.files[7] + rank));

        return nonPawnRankPieces;
    }

    /*
     * Fill the chess board with pieces.
     * lowercase indicates black color.
     * uppercase indicates white color.
     * 
     * Chess Board
     * 
     * a b c d e f g h
     * 8 r n b q k b n r 8
     * 7 p p p p p p p p 7
     * 6 6
     * 5 5
     * 4 4
     * 3 3
     * 2 P P P P P P P P 2
     * 1 R N B Q K B N R 1
     * a b c d e f g h
     * 
     */
    public void initBoard() {
        this.board[0] = createNonPawnRank(Color.black);
        this.board[1] = createPawnRank(Color.black);

        this.board[2] = new Piece[this._board_dim];
        this.board[3] = new Piece[this._board_dim];
        this.board[4] = new Piece[this._board_dim];
        this.board[5] = new Piece[this._board_dim];

        this.board[6] = createPawnRank(Color.white);
        this.board[7] = createNonPawnRank(Color.white);
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public int getCol(FileRank fr) {
        return fr.getFile() - 'a';
    }

    public int getRow(FileRank fr) {
        return 8 - fr.getRank();
    }

    public boolean setPiece(String currFrString, String nextFrString) {
        FileRank cfr = new FileRank(currFrString);
        FileRank nfr = new FileRank(nextFrString);

        Piece currentPiece = getPiece(cfr);
        Piece nextPiece = getPiece(nfr);

        // illegal move.
        // cannot move a null piece.
        if (currentPiece == null) {
            return false;
        }

        // canMove will define the move and attack policies for a piece.
        if (currentPiece.canMove(this, nextPiece, nfr)) {
            this.board[getRow(nfr)][getCol(nfr)] = currentPiece; // new = current
            currentPiece.setFileRank(nfr);
            this.board[getRow(cfr)][getCol(cfr)] = null; // old = null
            return true;
        }
        return false;
    }

    public Piece getPiece(String fileRank) {
        FileRank fr = new FileRank(fileRank);
        return this.board[getRow(fr)][getCol(fr)];
    }

    public Piece getPiece(FileRank fr) {

        return this.board[getRow(fr)][getCol(fr)];
    }

    public int get_board_dim() {
        return this._board_dim;
    }

    public void set_board_dim(int _board_dim) {
        this._board_dim = _board_dim;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece[] pieces : this.board) {
            for (Piece piece : pieces) {
                String pString = piece == null ? "*_****_*" : piece.toString();
                sb.append(pString).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
