public class Main {

    public static int lengthOfLastWord(String s) {//counting from the last letter
        boolean hasStarted = false;//checks when there encounters a letter (trailing spaces are there)
        int len = 0;
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) != ' '){//if the char is a letter
                hasStarted = true;
                len++;
            }
            else{//if the char is a space then check if we had at a letter --> it means it is the end of the word
                if(hasStarted){
                    break;
                }
            }
        }
        return len;
    }

    public static int lengthOfLastWord2(String s) {//split the string approach (split the string and last word is the target)
        s = s.stripTrailing();//strip trailing spaces
        String[] words = s.split(" ");//split the string by spaces
        return words[words.length-1].length();//last word of the split strings is the target word
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord2("   fly me   to   the moon  "));
    }
}