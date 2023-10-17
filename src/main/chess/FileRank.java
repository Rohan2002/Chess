package chess;

import java.util.Objects;

public class FileRank {
    private char file;
    private int rank;
    public static char[] files = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

    /*
     * FileRank.
     * 
     * File is a column from a thru h.
     * Rank is a row from 1 to 8.
     * 
     * So, for instance, the square g5 is in column g, row 5.
     */
    public FileRank(String fileRankString) {
        int file = fileRankString.charAt(0) - 'a'; // column index
        int rank = fileRankString.charAt(1) - '0'; // row index

        boundCheckFileRank(file, rank);

        this.file = fileRankString.charAt(0);
        this.rank = rank;
    }

    public void boundCheckFileRank(int file, int rank) {
        if (rank < 1 || rank > 8) {
            throw new IllegalArgumentException("Invalid rank index (valid: 1 to 8): " + rank);
        }
        if (file < 0 || file > 7) {
            throw new IllegalArgumentException("Invalid file index (valid: a to e): " + file);
        }
    }

    public char getFile() {
        return this.file;
    }

    public void setFile(char file) {
        this.file = file;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean equals(FileRank fr) {
        return fr.getFile() == this.getFile() && fr.getRank() == this.getRank();
    }

    @Override
    public String toString() {
        return "" + this.file + this.rank;
    }
}
