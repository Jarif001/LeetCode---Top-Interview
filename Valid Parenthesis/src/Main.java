import java.util.Stack;

public class Main {
    public static boolean validParenthesis(String str){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char theChar = str.charAt(i);
            if(theChar == '}' || theChar == ']' || theChar == ')'){
                if(!stack.empty()){//if the stack is not empty, match this closing with the opening
                    if((theChar == ')' && stack.peek() == '(') || (theChar == '}' && stack.peek() == '{') || (theChar == ']' && stack.peek() == '[')){
                        stack.pop();
                    }
                    else{//if not matched with opening then not valid
                        return false;
                    }
                }
                else{//found started with the closing
                    return false;
                }
            }
            else{//found the opening bracket. push to the stack
                stack.push(str.charAt(i));
            }
        }
        return stack.isEmpty();//iterated through all characters. if stack is empty then all opening matched with closing
    }

    public static void main(String[] args) {
        String str = "({[]})";
        System.out.println(validParenthesis(str));
    }
}