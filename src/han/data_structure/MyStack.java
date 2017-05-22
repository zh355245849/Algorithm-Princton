package han.data_structure;

import java.util.Stack;

/**
 * Created by zh355245849 on 2017/4/16.
 */
public class MyStack {

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    ListNode head;
    public void push(int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public int pop() {
        if (head == null) {
            throw new NullPointerException("stack is null");
        }
        ListNode p = head;
        head = p.next;
        p.next = null;
        return p.val;
    }

    public int peek() {
        if (head == null) {
            throw new NullPointerException("stack is null");
        }
        return head.val;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());;
        System.out.println(stack.pop());
        System.out.println(stack.peek());;
        System.out.println(stack.pop());
        System.out.println(stack.peek());;
    }
}
