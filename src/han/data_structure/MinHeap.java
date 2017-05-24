package han.data_structure;

import java.util.Arrays;

/**
 * Created by zh355245849 on 2017/5/23.
 */
public class MinHeap {

    private int[] arr;
    private int size;

    public MinHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be greater than 0");
        }
        this.arr = new int[capacity];
        this.size = 0;
    }

    public MinHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array length should greater than 0");
        }
        this.arr = arr;
        this.size = arr.length;
        heapify(arr);
    }

    private void heapify(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            sink(arr, i);
        }
    }

    private void sink(int[] arr, int index) {
        while (index <= (size - 2) / 2) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int min = index;
            if (left < size && arr[left] < arr[min]) {
                min = left;
            }
            if (right < size && arr[right] < arr[min]) {
                min = right;
            }
            if (arr[min] != arr[index]) {
                swap(arr, min, index);
            } else {
                break;
            }
            index = min;
        }
    }

    private void sift(int[] arr, int index) {
        while (index > 0) {
            int parent = parent(index);
            if (arr[parent] > arr[index]) {
                swap(arr, parent, index);
            } else {
                break;
            }
            index = parent;
        }
    }

    public void offer(int n) {
        if (size == arr.length) {
            throw new IllegalStateException("heap is full!");
        }
        arr[size++] = n;
        sift(arr, size - 1);
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty!");
        }
        int pollElem = arr[0];
        arr[0] = arr[size - 1];
        size--;
        sink(arr, 0);
        return pollElem;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty!");
        }
        return arr[0];
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void print() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(8);
        minHeap.offer(3);
        minHeap.offer(6);
        minHeap.offer(1);
        minHeap.print();
        System.out.println(minHeap.peek());
        System.out.println(minHeap.poll());
        minHeap.print();
        System.out.println(minHeap.poll());
        minHeap.print();
        System.out.println(minHeap.poll());
        minHeap.print();
    }
}
