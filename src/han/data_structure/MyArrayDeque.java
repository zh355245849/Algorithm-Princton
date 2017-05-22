package han.data_structure;

import java.util.Deque;

/**
 * Created by zh355245849 on 2017/5/22.
 */

public class MyArrayDeque {
    private int[] arr;
    private int size;
    private int capacity;
    private int head, tail;
    public MyArrayDeque(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0;
        this.head = 3;
        this.tail = 3;
    }
    public void addFirst(int n) {
        if (size == capacity) {
            System.out.println("Error: The deque is full!");
            return ;
        }
        arr[tail] = n;
        tail = (tail + 1) % capacity;
        size++;
    }
    public void addLast(int n) {
        if (size == capacity) {
            System.out.println("Error: The deque is full!");
            return ;
        }
        arr[head] = n;
        head = (head - 1) % capacity;
        size++;
    }
    public int pollFirst() {
        if (isEmpty()) {
            System.out.println("Error: The deque is empty!");
            return 0;
        }
        tail = (tail - 1) % capacity;
        size--;
        return arr[tail];
    }
    public int pollLast() {
        if (isEmpty()) {
            System.out.println("Error: The deque is empty!");
            return 0;
        }
        head = (head + 1) % capacity;
        size--;
        return arr[head];
    }
    public int peekFirst() {
        if (isEmpty()) {
            System.out.println("Error: The deque is empty!");
            return 0;
        }
        return arr[(tail - 1) % capacity];
    }
    public int peekLast() {
        if (isEmpty()) {
            System.out.println("Error: The deque is empty!");
            return 0;
        }
        return arr[(head + 1) % capacity];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }
    public int getSize() {
        return size;
    }
    public void print() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        MyArrayDeque deque = new MyArrayDeque(10);
        deque.addFirst(34);
        deque.addFirst(2);
        deque.addFirst(123);
        deque.addLast(111);
        deque.addFirst(996);
        deque.print();
        System.out.println(deque.isFull());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        System.out.println(deque.getSize());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        System.out.println(deque.isEmpty());
    }
}
