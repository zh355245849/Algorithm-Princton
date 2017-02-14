package han.algor.week1.UF;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zh355245849 on 2017/2/13.
 */
public class SearchInBitonicArray {
    public static void main(String[] args) {
        int[] A = new int[] {1, 3, 6, 9, 12, 17, 20, 32, 19, 16, 13, 10, 8, 4};
        SearchInBitonicArray s = new SearchInBitonicArray();
        StdOut.println(s.searchInBitonicArray(A, 10));
    }

    public boolean searchInBitonicArray(int[] A, int target) {
        int turning = searchTurningPoint(A);
        return binarySearch(A, target, 0, turning) || binarySearch(A,target, turning + 1, A.length - 1);
    }

    public boolean binarySearch(int[] A, int target, int start, int end) {
        if(A == null || A.length == 0) {
            return false;
        }
        int left = start, right = end;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (target == A[mid]) {
                return true;
            }
            else if (target > A[mid]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return false;
    }

    //A[i - 1] < A[i] > A[i + 1]
    public int searchTurningPoint(int[] A) {
        if(A == null || A.length == 0) {
            throw new IllegalArgumentException("Array not exist!");
        }
        int left = 0, right = A.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid < 1 || mid > A.length - 2) {
                throw new IndexOutOfBoundsException();
            }
            if (A[mid] >= A[mid - 1] && A[mid] >= A[mid + 1]) {
                return mid;
            }
            else if (A[mid] >= A[mid - 1]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
