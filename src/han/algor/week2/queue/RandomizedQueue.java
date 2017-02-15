package han.algor.week2.queue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * Created by zh355245849 on 2017/2/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueue() {
        // construct an empty randomized queue
        this.items = (Item[]) new Object[2];
        this.size = 0;
    }

    public boolean isEmpty() {
        // is the queue empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        // remove and return a random item
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item sample() {
        // return (but do not remove) a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)];
    }

    private void resize(int n) {
        Item[] arr = (Item[]) new Object[n];
        for (int i = 0; i < size; i++) {
            arr[i] = items[i];
        }
        items = arr;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {

        int len;
        Item[] arr;

        public RandomQueueIterator() {
            this.len = size;
            arr = (Item[])new Object[len];
            for (int i = 0; i < size; i++) {
                arr[i] = items[i];
            }
        }

        @Override
        public boolean hasNext() {
            return len > 0;
        }

        @Override
        public Item next() {
            int random = StdRandom.uniform(len);
            if (arr[random] == null) {
                throw new NoSuchElementException();
            }
            Item item = arr[random];
            arr[random] = arr[len - 1];
            arr[len - 1] = null;
            len--;
            return item;
        }

        public void remove() {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        Iterator<Integer> iter = rq.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }
        StdOut.println("dequeue : " + rq.dequeue());
    }
}
