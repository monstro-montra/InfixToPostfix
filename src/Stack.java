public interface Stack<T> {
    void push (T newEntry); //add new entry to the top of the stack. // throw exception if empty
    T pop(); //removes and returns the stack's top entry. //throw exception if empty
    T peek(); //retrieve stack's top entry without changing the stack. throw exception if empty.
    boolean isEmpty(); //return true if the stack is empty
    void clear(); //remove all entries from the stack.

    void show(); //show all elements in the stack.
}
