package Lecture;
// Oct9.2020
// Practice for Lecture4
public class Stack {
    int[] value = new int[10];

    // len of the stack
    int length = 0;

    public static void main(String[] args) {
        Stack stack = new Stack();
        StackIterator iter = stack.new StackIterator(stack);

        stack.push(1);
        stack.push(3);
        stack.push(5);
        for(;iter.hasNext();) {
            System.out.println(iter.next());
        }
    }

    class StackIterator {
        int curIndex;

        // stack connected to this iterator
        Stack stack;

        StackIterator(Stack stack) {
            this.stack = stack;
            curIndex = stack.size() - 1;
        }

        public void reset() {
            curIndex = stack.size();
        }

        public boolean hasNext() {
            return curIndex != 0;
        }

        public int next() {
            int val = stack.pop();
            reset();
            return val;
        }
    }

    public void push(int x) {
        value[length++] = x;
    }

    public int pop() {
        return value[--length];
    }

    public int size() {
        return length;
    }

    public void clear() {
        length = 0;
    }
}

