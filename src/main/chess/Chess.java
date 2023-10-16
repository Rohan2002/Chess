package chess;

import java.util.ArrayList;

import chess.Piece.Color;
import chess.ReturnPiece.PieceFile;

class ReturnPiece {
	static enum PieceType {
		WP, WR, WN, WB, WQ, WK,
		BP, BR, BN, BB, BK, BQ
	};

	static enum PieceFile {
		a, b, c, d, e, f, g, h
	};

	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank; // 1..8

	public String toString() {
		return "" + pieceFile + pieceRank + ":" + pieceType;
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece) other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {
		ILLEGAL_MOVE, DRAW,
		RESIGN_BLACK_WINS, RESIGN_WHITE_WINS,
		CHECK, CHECKMATE_BLACK_WINS, CHECKMATE_WHITE_WINS,
		STALEMATE
	};

	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {

	enum Player {
		white, black
	}

	public static int turns;
	public static Board chessBoard;

	public static ReturnPiece convertPieceToReturnPiece(Piece p) {
		ReturnPiece rp = new ReturnPiece();

		FileRank pFileRank = p.getFileRank();

		rp.pieceRank = pFileRank.getRank();
		rp.pieceFile = PieceFile.valueOf("" + pFileRank.getFile());

		String pColor = p.getColorPiece() == Color.white ? "W" : "B";
		String pType = "";

		if (p.getPieceType() == Piece.PieceType.pawn) {
			pType = "P";
		} else if (p.getPieceType() == Piece.PieceType.rook) {
			pType = "R";
		} else if (p.getPieceType() == Piece.PieceType.king) {
			pType = "K";
		} else if (p.getPieceType() == Piece.PieceType.queen) {
			pType = "Q";
		} else if (p.getPieceType() == Piece.PieceType.bishop) {
			pType = "B";
		} else if (p.getPieceType() == Piece.PieceType.knight) {
			pType = "N";
		} else {
			throw new IllegalArgumentException("Cannot identify type of piece in type Piece");
		}

		rp.pieceType = ReturnPiece.PieceType.valueOf(pColor + pType);

		return rp;
	}

	public static ArrayList<ReturnPiece> fillFinalPiecesBoard(ArrayList<ReturnPiece> rPieces, Piece[][] board) {
		for (Piece[] pRow : board) {
			for (Piece p : pRow) {
				if (p == null) {
					continue;
				}
				ReturnPiece rp = convertPieceToReturnPiece(p);
				rPieces.add(rp);
			}
		}
		return rPieces;
	}

	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for
	 *         details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {
		Color activeColor = turns % 2 == 0 ? Color.white : Color.black;
		chessBoard.setActiveColor(activeColor);

		if (move.equals("resign")) {
			ReturnPlay rp = new ReturnPlay();

			rp.message = chessBoard.getActiveColor() == Color.white ? ReturnPlay.Message.RESIGN_BLACK_WINS
					: ReturnPlay.Message.RESIGN_WHITE_WINS;

			rp.piecesOnBoard = new ArrayList<ReturnPiece>();
			fillFinalPiecesBoard(rp.piecesOnBoard, chessBoard.getBoard());

			return rp;
		}

		String[] inputStrings = move.split(" ");
		boolean drawWasAsked = inputStrings[inputStrings.length - 1].equals("draw?");
		if (inputStrings.length > 2){
			chessBoard.setPawnPromotionDefault(inputStrings[2]);
		}

		ReturnPlay rp = new ReturnPlay();
		boolean setPieceStatus = false;
		try {
			setPieceStatus = chessBoard.setPiece(inputStrings[0], inputStrings[1]);
		} catch (IllegalArgumentException e) {
			setPieceStatus = false;
		}
		chessBoard.setGameCheckmateObject(null);

		rp.piecesOnBoard = new ArrayList<ReturnPiece>();
		fillFinalPiecesBoard(rp.piecesOnBoard, chessBoard.getBoard());

		if (setPieceStatus) {
			CheckMate c = CheckMate.isCheckMate(chessBoard);
			if (c.getType() == CheckMate.CheckMateType.checkmate) {
				rp.message = drawWasAsked ? ReturnPlay.Message.DRAW
						: (activeColor == Color.white ? ReturnPlay.Message.CHECKMATE_WHITE_WINS
								: ReturnPlay.Message.CHECKMATE_BLACK_WINS);
			} else if (c.getType() == CheckMate.CheckMateType.check) {
				rp.message = drawWasAsked ? ReturnPlay.Message.DRAW : ReturnPlay.Message.CHECK;
				chessBoard.setGameCheckmateObject(c);
			} else {
				rp.message = drawWasAsked ? ReturnPlay.Message.DRAW : null;
			}
			turns++;
		} else {
			rp.message = ReturnPlay.Message.ILLEGAL_MOVE;
		}
		return rp;
	}

	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		turns = 0;
		chessBoard = new Board();
		chessBoard.initBoard();
		chessBoard.setActiveColor(Color.white); // start white for first move.
	}
}
