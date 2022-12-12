import java.lang.reflect.Array;
import java.util.Arrays;

public class FixedStack<T> implements Stack<T>{
    private final T[] stack;
    private int top = 0;

    //constructor
    public FixedStack(Class<T>  tClass, int capacity){
        @SuppressWarnings("unchecked")
        final T[] s = (T[]) Array.newInstance(tClass, capacity);
        this.stack = s;
    }

    @Override
    public void push(T newEntry) { //add element from top of the list
        stack[top] = newEntry;
        if(top < stack.length) {
            top++; //increment top value so that when user pushes to stack again, it will be at stack[1], stack[2], stack[3], etc.
        }

    }

    @Override
    public T pop() { //remove element from top of the list
        T data; //used to store generic type
        top--; //decrement top because we are going to delete an element
        data = stack[top]; //data is = to stack[top]
        stack[top] = null; //stack @ top is null.
        return data; //return data. (in case we want to print which element was deleted.)
    }

    @Override
    public T peek() {
        return stack[top-1];
    }

    @Override
    public boolean isEmpty() {
        return stack[0] == null;
    }

    @Override
    public void clear() {
        Arrays.fill(stack, null);
    }

    @Override
    public void show() {
        int count = 0;
        for(T n: stack){
            System.out.println ("Element " + count + ": " + n);
            count++;
        }
    }

    public boolean isFull() {
        return top == stack.length; //return full if top == stack.length
    }
}
