package chess;

import java.io.File;

import chess.Piece.Color;

public class Board {

    private int _board_dim;
    private Piece[][] board;

    public Board() {
        this._board_dim = 8;
        this.board = new Piece[_board_dim][_board_dim];
    }

    public Piece[] createPawnRank(Color c) {
        String rank = c == Color.black ? "b" : "e";
        Piece[] pawnRankPieces = new Piece[this._board_dim];
        for (int file = 0; file < pawnRankPieces.length; file++) {
            int incrementFile = file + 1;
            pawnRankPieces[file] = new Pawn(c, new FileRank(rank + incrementFile));
        }
        return pawnRankPieces;
    }

    public Piece[] createNonPawnRank(Color c) {
        String rank = c == Color.black ? "a" : "h";
        Piece[] nonPawnRankPieces = new Piece[this._board_dim];

        nonPawnRankPieces[0] = new Rook(c, new FileRank(rank + 1));
        nonPawnRankPieces[1] = new Knight(c, new FileRank(rank + 2));
        nonPawnRankPieces[2] = new Bishop(c, new FileRank(rank + 3));
        
        nonPawnRankPieces[3] = new Queen(c, new FileRank(rank + 4));
        nonPawnRankPieces[4] = new King(c, new FileRank(rank + 5));
        
        nonPawnRankPieces[5] = new Bishop(c, new FileRank(rank + 6));
        nonPawnRankPieces[6] = new Knight(c, new FileRank(rank + 7));
        nonPawnRankPieces[7] = new Rook(c, new FileRank(rank + 8));

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
    
    public int getCol(FileRank fr){
        return fr.getFile() - 'a';
    }

    public int getRow(FileRank fr){
        return 7 - fr.getRank();
    }
    
    public boolean setPiece(Piece p, String frString){
        FileRank fr = new FileRank(frString);

        if (!p.canMove(this, fr)){
            return false;
        }
        // Check if there is a existing piece at fr.
        if (this.board[getRow(fr)][getCol(fr)] != null){
            this.board[getRow(fr)][getCol(fr)] = null; // kill the existing piece;
        }
        this.board[getRow(fr)][getCol(fr)] = p;
        FileRank prevFileRank = p.getFileRank();
        System.out.println("prevFileRank: " + prevFileRank);
        System.out.println("p: " + p);
        
        this.board[getRow(prevFileRank)][getCol(prevFileRank)] = null;
        p.setFileRank(fr);
        return true;
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

    public static void main(String[] args) {
        Board b = new Board();
        b.initBoard();
        System.out.println(b);
        Piece p = b.getPiece("e1");
        System.out.println("Got Piece: " + p);

        // b.setPiece(p, new FileRank("e4"));
        // System.out.println(b);
    }

}
