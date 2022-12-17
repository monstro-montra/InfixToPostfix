import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Insert an expression with no integers or decimals. E.g., 5+7*4");
        String expression = in.next();
        System.out.println("Infix: " + expression);
        System.out.println("Postfix: " + convertToPostFix(expression));


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