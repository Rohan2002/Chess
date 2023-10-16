package chess;

import java.util.ArrayList;

import chess.Piece.Color;

public class CheckMate {
    public static enum CheckMateType {
        checkmate, check, none
    };

    private CheckMateType type;
    private ArrayList<FileRank> checkMatePos;

    public CheckMate(CheckMateType type, ArrayList<FileRank> checkMatePos) {
        this.type = type;
        this.checkMatePos = checkMatePos;
    }

    public CheckMateType getType() {
        return this.type;
    }

    public void setType(CheckMateType type) {
        this.type = type;
    }

    public ArrayList<FileRank> getCheckMatePos() {
        return this.checkMatePos;
    }

    public void setCheckMatePos(ArrayList<FileRank> checkMatePos) {
        this.checkMatePos = checkMatePos;
    }

    public static ArrayList<FileRank> possibleKingPositions(Board b, Piece currentKingPiece) {
        FileRank currKingPosition = currentKingPiece.getFileRank();

        int[] possibleRanks = { currKingPosition.getRank() - 1, currKingPosition.getRank(),
                currKingPosition.getRank() + 1 };
        char[] possibleFiles = { (char) (currKingPosition.getFile() - 1), currKingPosition.getFile(),
                (char) (currKingPosition.getFile() + 1) };

        ArrayList<FileRank> PossibleKingFrs = new ArrayList<>();
        for (char possibleFile : possibleFiles) {
            for (int possibleRank : possibleRanks) {
                try {
                    FileRank fr = new FileRank("" + possibleFile + possibleRank);
                    Piece currentPiece = b.getPiece(fr);
                    if (currentPiece != null && !currentPiece.equals(currentKingPiece)
                            && currentPiece.getColorPiece() == currentKingPiece.getColorPiece()) {
                        continue;
                    }
                    PossibleKingFrs.add(fr);
                } catch (IllegalArgumentException exception) {
                    continue;
                }
            }
        }
        return PossibleKingFrs;
    }

    public static CheckMate isCheckMate(Board b) {
        /*
         * White King. This is under attack.
         * -> White King cannot escape the attack. Checkmate
         * -> White King can escape the attack. Check
         * 
         * Black piece can attack white king.
         * 
         * 1. White king position in the board.
         * -> All the possible positions that king placed.
         *
         * Set data structure: Board class state. AlivePieces
         * 
         * Loop throgh all possible white king positio.
         * 
         * 
         * Get all black AlivePieces and check if
         */
        // freedom movements. Checkmate not possible.
        ArrayList<FileRank> attackableKingPositions = new ArrayList<>();

        if (b.getActiveColor() == null) {
            return new CheckMate(CheckMateType.none, attackableKingPositions);
        }
        Color attackerColor = b.getActiveColor();
        Color victimColor = b.getActiveColor() == Color.black ? Color.white : Color.black;

        Piece victimKing = null;

        // get vicitim's king position.
        for (Piece p : b.getAlivePieces()) {
            if (p.getColorPiece() == victimColor && p.getPieceType() == Piece.PieceType.king) {
                victimKing = p;
            }
        }
        // if victim is is missing something is wrong with the game. Checkmate is not
        // possible.
        if (victimKing == null) {
            return new CheckMate(CheckMateType.none, attackableKingPositions);
        }

        ArrayList<FileRank> possibleKingPositions = possibleKingPositions(b, victimKing);

        boolean[] unSafeKingPositions = new boolean[possibleKingPositions.size()];
        // No unsafe position means that the king is safe.
        // Goal: Checkmate: all are unsafe positions.

        int i = 0;

        // all posibile location that vicitim king can go too.
        for (FileRank possibleKingPosition : possibleKingPositions) {
            int safePosition = -1; // we dont know yet.
            for (Piece alivePiece : b.getAlivePieces()) {
                // make sure alivePiece is opposite color.
                if (alivePiece.getColorPiece() == attackerColor) {
                    // different team as king that is under attack.
                    // try to attack king in possible king location
                    if (alivePiece.canMove(b, victimKing, possibleKingPosition)) {
                        attackableKingPositions.add(possibleKingPosition);
                        safePosition = 0;
                        break;
                    }
                }
            }
            if (safePosition == 0) {
                unSafeKingPositions[i] = true;
            }
            i++;
        }
        int unSafePositionCount = 0;
        for (boolean unSafeKingPosition : unSafeKingPositions) {
            if (unSafeKingPosition) {
                unSafePositionCount++;
            }
        }
        if (unSafePositionCount == 0) {
            return new CheckMate(CheckMateType.none, possibleKingPositions);
        } else if (unSafePositionCount < unSafeKingPositions.length) {
            return new CheckMate(CheckMateType.check, possibleKingPositions);
        } else {
            return new CheckMate(CheckMateType.checkmate, possibleKingPositions);
        }
    }
}
