package han.data_structure;

import java.util.PriorityQueue;

/**
 * Created by zh355245849 on 2017/4/24.
 */
public class Heapsort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 6, 4, 5};
        Heapsort hs = new Heapsort(arr.length);
        hs.heapsort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    int[] array;
    int capacity;

    public Heapsort(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    public void heapsort(int[] arr) {
        heapify(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, capacity - 1);
            capacity -= 1;
            sinkdown(arr, 0);
        }
    }

    public void heapify(int[] arr) {
        if (arr == null || arr.length == 0) {
            return ;
        }
        for (int i = capacity / 2 - 1; i >= 0; i--) {
            sinkdown(arr, i);
        }
    }

    public void sinkdown(int[] arr, int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int max = i;
        if (left < capacity && arr[left] > arr[max]) {
            max = left;
        }
        if (right < capacity && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, i, max);
            sinkdown(arr, max);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int leftChild(int n) {
        return 2 * n + 1;
    }

    public int rightChild(int n) {
        return 2 * n + 2;
    }

    public int root(int n) {
        return (n - 1) / 2;
    }
}
