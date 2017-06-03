package han.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh355245849 on 2017/5/10.
 */
public class BasicSort {
    public static void main(String[] args) {
        int[] nums = {9, 6, 2, 5, 1, 0, 4};
        BasicSort bs = new BasicSort();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        System.out.println(bs.minCuts("bb"));
    }

    public int minCuts(String input) {
        // Write your solution here.
        if (input.length() == 0) {
            return 0;
        }
        int n = input.length();
        int[] dp = new int[input.length() + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i - 1;
            for (int j = 0; j <= i; j++) {
                if (isPal(input.substring(j, i))) {
                    dp[i] = 0;
                    break;
                }
                if (isPal(input.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }
    private boolean isPal(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public int percentile95(List<Integer> lengths) {
        // Write your solution here.
        int[] bucket = new int[4096 + 1];
        for (Integer i : lengths) {
            bucket[i] += 1;
        }
        int sum = 0, len = 0;
        while (sum < 0.95 * lengths.size()) {
            sum += bucket[++len];
        }
        return len;
    }

    public void bubblesort(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    swap(A, i, j);
                }
            }
        }
    }

    public void insertsort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && key < A[j]) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

}
