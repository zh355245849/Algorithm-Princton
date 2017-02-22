package han.algor.week4.PQ;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by zh355245849 on 2017/2/21.
 */
public class Board {

    private int[][] board;
    private int N;

    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        if (blocks == null) {
            throw new NullPointerException();
        }
        this.N = blocks.length;
        this.board = new int[N][N];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                this.board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        // board dimension n
        return N;
    }

    public int hamming() {
        // number of blocks out of place
        int ham = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N && i + j < 2 * N - 2; j++) {
                if (board[i][j] != i * N + j + 1) {
                    ham++;
                }
            }
        }
        return ham;
    }

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int manh = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    manh += Math.abs((board[i][j] - 1) / N - i) + Math.abs((board[i][j] - 1) % N - j);
                }
            }
        }
        return manh;
    }

    public boolean isGoal() {
        // is this board the goal board?
        return hamming() == 0;
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] nboard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nboard[i][j] = board[i][j];
            }
        }
        if (board[0][0] != 0 && board[0][1] != 0) {
            int temp = nboard[0][1];
            nboard[0][1] = nboard[0][0];
            nboard[0][0] = temp;
        }
        else {
            int temp = nboard[1][0];
            nboard[1][0] = nboard[1][1];
            nboard[1][1] = temp;
        }
        return new Board(nboard);
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (this == y) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board bb = (Board) y;

        if (this.N != ((Board) y).board.length) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != bb.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        int blankx = 0, blanky = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    blankx = i;
                    blanky = j;
                }
            }
        }

        MinPQ<Board> minHeap = new MinPQ<>(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                if (o1.manhattan() < o2.manhattan()) return -1;
                else if (o1.manhattan() == o2.manhattan()) return 0;
                else return 1;
            }
        });

        if (isValid(blankx - 1, blanky)) {
            int[][] temp = getCopy(board);
            temp[blankx][blanky] = temp[blankx - 1][blanky];
            temp[blankx - 1][blanky] = 0;
            minHeap.insert(new Board(temp));
        }
        if (isValid(blankx + 1, blanky)) {
            int[][] temp = getCopy(board);
            temp[blankx][blanky] = temp[blankx + 1][blanky];
            temp[blankx + 1][blanky] = 0;
            minHeap.insert(new Board(temp));
        }
        if (isValid(blankx, blanky - 1)) {
            int[][] temp = getCopy(board);
            temp[blankx][blanky] = temp[blankx][blanky - 1];
            temp[blankx][blanky - 1] = 0;
            minHeap.insert(new Board(temp));
        }
        if (isValid(blankx, blanky + 1)) {
            int[][] temp = getCopy(board);
            temp[blankx][blanky] = temp[blankx][blanky + 1];
            temp[blankx][blanky + 1] = 0;
            minHeap.insert(new Board(temp));
        }

        return minHeap;
    }

    private boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }
        return true;
    }

    private int[][] getCopy(int[][] b) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = b[i][j];
            }
        }
        return copy;
    }

    public String toString() {
        // string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            sb.append(" ");
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // unit tests (not graded)
        int[][] blocks = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blocks[i][j] = i + j;
            }
        }
        Board b = new Board(blocks);
        StdOut.println(b.toString());
        StdOut.println(b.hamming());
    }
}