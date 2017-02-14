package han.algor.week1.UF;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zh355245849 on 2017/2/13.
 */
class UnionFind1 {
    int[] ids;
    int[] size;
    int[] large;
    public UnionFind1(int n) {
        ids = new int[n + 1];
        size = new int[n + 1];
        large = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ids[i] = i;
            size[i] = 1;
            large[i] = i;
        }
    }
    public int find(int n) {
        while (n != ids[n]) {
            ids[n] = ids[ids[n]];
            n = ids[n];
        }
        return n;
    }
    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
    public int findLargest(int n) {
        int pn = find(n);
        return large[pn];
    }
    public void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return ;
        }
        int biga = large[pa];
        int bigb = large[pb];
        if (size[pa] > size[pb]) {
            size[pa] += size[pb];
            ids[pb] = pa;
            large[pa] = Math.max(biga, bigb);
        }
        else {
            size[pb] += size[pa];
            ids[pa] = pb;
            large[pb] = Math.max(biga, bigb);
        }
    }
}

public class CanonicalElement {
    public static void main(String[] args) {
        UnionFind1 uf = new UnionFind1(100);
        uf.union(3, 4);
        StdOut.println(uf.findLargest(3));
        uf.union(4, 56);
        StdOut.println(uf.findLargest(4));
    }
}
