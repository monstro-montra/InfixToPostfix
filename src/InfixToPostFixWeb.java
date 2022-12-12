import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public interface Stack<T> {
    void push (T newEntry); //add new entry to the top of the stack. // throw exception if empty
    T pop(); //removes and returns the stack's top entry. //throw exception if empty
    T peek(); //retrieve stack's top entry without changing the stack. throw exception if empty.
    boolean isEmpty(); //return true if the stack is empty
    void clear(); //remove all entries from the stack.

    void show(); //show all elements in the stack.
}

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

public class InfixToPostFixWeb {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.next();

    }

    public static int priority (char character){
        return switch (character) {
            case '+', '-' -> 1;
            case '*', '/' -> 2; //second-highest priority
            case '^' -> 3;
            default -> //highest priority
                    -1;
        };
    }

    public static String convertToPostFix(String infix){
        FixedStack<Character> operatorStack = new FixedStack<>(Character.class, 20); // init stack of capacity 10
        StringBuilder postfix = new StringBuilder(); //empty string
        for(int i = 0; i < infix.length(); ++i){ //while i < infix length, increment i
            char currentChar = infix.charAt(i); //the character at the current iteration of the for loop
            if(Character.isLetterOrDigit(currentChar)){
                postfix.append(currentChar); //add currentChar to postfix
            }
            else if(currentChar == '('){ //if current char is (
                operatorStack.push(currentChar); //push currentChar from operatorStack
            }
            else if(currentChar == ')'){ //if current char is ')'
                while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {// while operator stack is not empty and peek is not (
                    postfix.append(operatorStack.peek()); //add operatorStack.peek to postfix
                    operatorStack.pop();
                }
                operatorStack.pop();
            }
            else {// when an operator is encountered
                //while operator stack is not empty and priority at currentChar <= priority at peek
                while (!operatorStack.isEmpty() && priority(currentChar) <= priority(operatorStack.peek())){
                    postfix.append(operatorStack.peek());
                    operatorStack.pop(); //pop off last element
                }
                operatorStack.push(currentChar);
            }
        }
        //pop out all the operators from stack
        while(!operatorStack.isEmpty()) { //while operator stack is not empty
            if (operatorStack.peek() == '('){
                return "Invalid Expression.";
            }
            postfix.append(operatorStack.peek()); //add peek to postfix
            operatorStack.pop();
        }

        return postfix.toString();
    }


}

