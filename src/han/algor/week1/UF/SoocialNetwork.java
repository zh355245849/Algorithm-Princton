package han.algor.week1.UF;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zh355245849 on 2017/2/13.
 */

class UnionFind {
    int[] ids;
    int[] sizes;
    int count;
    public UnionFind(int n) {
        ids = new int[n + 1];
        sizes = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            sizes[i] = 1;
            ids[i] = i;
        }
        count = n;
    }
    public int find(int n) {
        while(n != ids[n]) {
            ids[n] = ids[ids[n]];
            n = ids[n];
        }
        return n;
    }
    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
    public void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return ;
        }
        //small size append to big size
        //change small size root to large size root
        if (sizes[pa] < sizes[pb]) {
            sizes[pb] += sizes[pa];
            ids[pa] = pb;
        }
        else {
            sizes[pa] += sizes[pb];
            ids[pa] += ids[pb];
        }
        count--;
    }
}

public class SoocialNetwork {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 2);
        StdOut.println(uf.count);
        uf.union(2, 3);
        StdOut.println(uf.count);
        uf.union(3, 4);
        StdOut.println(uf.count);
        if(uf.count == 1) {
            StdOut.println("All friends have connected!!");
        }
    }
}
