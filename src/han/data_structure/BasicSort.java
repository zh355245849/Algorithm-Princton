package han.data_structure;

/**
 * Created by zh355245849 on 2017/5/10.
 */
public class BasicSort {
    public static void main(String[] args) {
        int[] nums = {9, 6, 2, 5, 1, 0, 4};
        BasicSort bs = new BasicSort();
        bs.insertsort(nums);
        for (int i : nums)
        System.out.println(i);
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
