public class Main {
    public static void main(String[] args) {
        String example1 = "a*b/(c-d)";
        String example2 = "(a-b*c)/(d*e*f+g)";
        String example3 = "a/b*(c+(d-e))";
        String example4 = "(a^b*c-d)^e+f^g^h";

        System.out.println("Before: " + example1);
        System.out.println("After: " + convertToPostFix(example1));

        System.out.println("Before: " + example2);
        System.out.println("After: " + convertToPostFix(example2));

        System.out.println("Before: " + example3);
        System.out.println("After: " + convertToPostFix(example3));

        System.out.println("Before: " + example4);
        System.out.println("After: " + convertToPostFix(example4));

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