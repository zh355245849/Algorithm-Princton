package han.algor.week2.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;
/**
 * Created by zh355245849 on 2017/2/14.
 */
public class Deque<Item> implements Iterable<Item> {

    private class ListNode {
        Item val;
        ListNode next;
        ListNode prev;
        public ListNode(Item val) {
            this.val = val;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public Deque() {
        // construct an empty deque
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new NullPointerException();
        }
        ListNode newnode = new ListNode(item);
        if (head == null) {
            head = newnode;
            tail = newnode;
        }
        else {
            newnode.next = head;
            head.prev = newnode;
            head = newnode;
        }
        size++;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        }
        ListNode newnode = new ListNode(item);
        if (tail == null) {
            head = newnode;
            tail = newnode;
        }
        else {
            tail.next = newnode;
            newnode.prev = tail;
            tail = newnode;
        }
        size++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty!");
        }
        Item item = head.val;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty!");
        }
        Item item = tail.val;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new Iterator<Item>() {
            ListNode p = head;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Item next() {
                if (p == null) {
                    throw new NoSuchElementException();
                }
                Item cur = p.val;
                p = p.next;
                return cur;
            }
        };
    }

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }
        StdOut.println("size : " + deque.size());

        deque.addLast(4);
        deque.addLast(5);
        iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }
        StdOut.println("size : " + deque.size());

        deque.removeFirst();
        deque.removeLast();
        iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }
        StdOut.println("size : " + deque.size());
    }
}