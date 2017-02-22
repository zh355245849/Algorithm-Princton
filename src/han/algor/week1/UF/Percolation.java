package han.algor.week1.UF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by zh355245849 on 2017/2/9.
 */
public class Percolation {
    private boolean[][] open;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufull;
    private int size;
    private int top;
    private int bottom;
    private int numOfOpenSites;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.size = n;
        this.top = 0;
        this.bottom = n * n + 1;
        this.open = new boolean[n][n];
        this.numOfOpenSites = 0;
        this.ufull = new WeightedQuickUnionUF(n * n + 1);
        this.uf = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        isValidIndex(row, col);
        if (!isOpen(row, col)) {
            open[row - 1][col - 1] = true;
            numOfOpenSites++;
        }
        int idx = to1D(row, col);
        if (row == 1) {
            uf.union(top, idx);
            ufull.union(top, idx);
        }

        if (row == size) {
            uf.union(idx, bottom);
        }

        if (row - 1 > 0 && row - 1 < size && isOpen(row - 1, col)) {
            uf.union(idx, to1D(row - 1, col));
            ufull.union(idx, to1D(row - 1, col));
        }

        if (row + 1 > 0 && row + 1 < size + 1 && isOpen(row + 1, col)) {
            uf.union(idx, to1D(row + 1, col));
            ufull.union(idx, to1D(row + 1, col));
        }

        if (col - 1 > 0 && col - 1 < size && isOpen(row, col - 1)) {
            uf.union(idx, to1D(row, col - 1));
            ufull.union(idx, to1D(row, col - 1));
        }

        if (col + 1 > 0 && col + 1 < size + 1 && isOpen(row, col + 1)) {
            uf.union(idx, to1D(row, col + 1));
            ufull.union(idx, to1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        isValidIndex(row, col);
        return open[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        isValidIndex(row, col);
        return uf.connected(to1D(row, col), top);
    }

    private void isValidIndex(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new ArrayIndexOutOfBoundsException("index is invalid");
        }
    }

    private int to1D(int i, int j) {
        return (i - 1) * size + j;
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numOfOpenSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(6);
        percolation.open(2, 3);
        percolation.open(1, 3);
        percolation.open(4, 3);
        percolation.open(5, 3);
        System.out.println(percolation.isFull(5, 3));
        percolation.open(6, 3);
        percolation.open(3, 3);
        System.out.println(percolation.percolates());

    }
}
