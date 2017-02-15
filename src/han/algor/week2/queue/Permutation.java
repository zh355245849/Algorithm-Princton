package han.algor.week2.queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zh355245849 on 2017/2/14.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rq.enqueue(str);
            count++;
        }
        while (count - k > 0) {
            rq.dequeue();
            count--;
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
