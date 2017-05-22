package han.data_structure;

import java.util.Scanner;
/**
 * Created by zh355245849 on 2017/4/17.
 */
public class MyQueue {

    int[] array;
    int head;
    int tail;
    int size;
    int capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        head = tail = 0;
        size = 0;
    }

    public void offer(int val) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full!");
        }
        array[tail] = val;
        tail = (tail + 1) % capacity;
        size++;
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty!");
        }
        int val = array[head];
        array[head] = 0;
        head = (head + 1) % capacity;
        size--;
        return val;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty!");
        }
        return array[head];
    }

    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }
}
