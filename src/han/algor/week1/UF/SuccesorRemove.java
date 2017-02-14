package han.algor.week1.UF;

/**
 * Created by zh355245849 on 2017/2/14.
 */

import edu.princeton.cs.algs4.StdOut;
import han.algor.week1.UF.CanonicalElement;

import java.lang.reflect.Array;
import java.util.*;

public class SuccesorRemove {

    boolean[] data;
    HashSet<Integer> set;
    int size;
    UnionFind1 uf;

    public SuccesorRemove(int n) {
        this.size = n;
        uf = new UnionFind1(n);
        data = new boolean[n];
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            data[i] = true;
            set.add(i);
        }
    }

    public void remove(int x) {
        if (data[x]) {
            data[x] = false;
        }
        if (x - 1 >= 0 && !data[x - 1]) {
            uf.union(x, x - 1);
        }
        if (x + 1 < data.length && !data[x + 1]) {
            uf.union(x, x + 1);
        }
    }

    public int findSuccesor(int x) {  //x not essensially in set
        if (data[x + 1]) {
            return x + 1;
        }
        else {
            int succ = uf.findLargest(x + 1) + 1;
            return succ;
        }
    }

    public static void main(String[] args) {
        SuccesorRemove sm = new SuccesorRemove(10);
        StdOut.println(sm.findSuccesor(3));
        sm.remove(4);
        sm.remove(5);
        StdOut.println(sm.findSuccesor(3));
    }
}
