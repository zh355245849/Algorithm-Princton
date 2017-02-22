package han.algor.week4.PQ;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zh355245849 on 2017/2/22.
 */
public class Solver {

    private boolean isSolve = false;
    private int move = -1;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int move;
        private final int priority;
        private final SearchNode parent;
        private final boolean isTwin;
        public SearchNode(Board board, int move, SearchNode parent, boolean isTwin) {
            this.board = board;
            this.move = move;
            this.priority = board.manhattan() + move;
            this.parent = parent;
            this.isTwin = isTwin;
        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.board.equals(that.board)) return 0;
            if (this.priority < that.priority) return -1;
            else return 1;
        }

    }

    private MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority - o2.priority;
        }
    });
    private Stack<Board> solutionQueue = new Stack<Board>();

    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        SearchNode startNode = new SearchNode(initial, 0, null, false);
        SearchNode startNodeTwin = new SearchNode(initial.twin(), 0, null, true);
        minPQ.insert(startNode);
        minPQ.insert(startNodeTwin);
        solve();
    }

    private void solve() {
        while (true) {
            SearchNode n = minPQ.delMin();
            if (n.board.isGoal()) {
                if (n.isTwin) {
                    this.isSolve = false;
                    this.move = -1;
                }
                else {
                    this.isSolve = true;
                    this.move = n.move;
                    this.solutionQueue.push(n.board);
                    while(n.parent != null){
                        n = n.parent;
                        this.solutionQueue.push(n.board);
                    }
                }
                break;
            }
            else {
                for (Board bd : n.board.neighbors()) {
                    SearchNode newNode = new SearchNode(bd, n.move + 1, n, n.isTwin);
                    if (n.parent == null) {
                        minPQ.insert(newNode);
                    }
                    else if (!n.parent.board.equals(newNode.board)) {
                        minPQ.insert(newNode);
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        // is the initial board solvable?
        return this.isSolve;
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return this.move;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (this.isSolve) {
            return this.solutionQueue;
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        int[][] blocks = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                blocks[i][j] = i + j;
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}