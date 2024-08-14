import java.util.ArrayList;

public class Main {

    public static String reverseWords(String s) {
        String theSentence = "";
        String currentWord = "";
        boolean hasStarted = false;
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) != ' '){
                hasStarted = true;
                currentWord = s.charAt(i) + currentWord;
            }
            else if(hasStarted){
                theSentence = theSentence + currentWord + " ";
                currentWord = "";
                hasStarted = false;
            }
        }
        theSentence = theSentence + currentWord;
        return theSentence.stripTrailing();
    }

    public static String reverseWords2(String s) {
        String theSentence = "";
        String[] theWords = s.trim().split("\s+");
        for (int i = theWords.length-1; i > 0; i--){
            theSentence += theWords[i] + " ";
        }
        return theSentence + theWords[0];
    }

    public static String reverseWords3(String s) {
        StringBuilder theSentence = new StringBuilder();
        int currIdx = s.length()-1;
        while(currIdx >= 0){//iterate over all the characters
            while(currIdx >= 0 && s.charAt(currIdx) == ' '){//skip all the spaces
                currIdx--;
            }
            if(currIdx == -1){
                break;
            }
            //found a letter (starting of a word)
            String theWord = "";
            while(currIdx >= 0 && s.charAt(currIdx) != ' '){//all the letters
                theWord = s.charAt(currIdx) + theWord;
                currIdx--;
            }
            theSentence.append(theWord).append(" ");
        }
        return theSentence.toString().substring(0, theSentence.length()-1);
    }

    public static String reverseWords4(String s) {
        StringBuilder theSentence = new StringBuilder();
        ArrayList<StringBuilder> words = new ArrayList<>();
        int currIdx = s.length()-1;
        while(currIdx >= 0){//iterate over all the characters
            while(currIdx >= 0 && s.charAt(currIdx) == ' '){//skip all the spaces
                currIdx--;
            }
            if(currIdx == -1){
                break;
            }
            //found a letter (starting of a word)
            StringBuilder theWord = new StringBuilder();
            while(currIdx >= 0 && s.charAt(currIdx) != ' '){//all the letters
                theWord.insert(0, s.charAt(currIdx));//construct the word
                currIdx--;
            }
            words.add(theWord);//add the word to the list
        }
        for(int i = 0; i < words.size()-1; i++){
            theSentence.append(words.get(i)).append(" ");//add all the words to make the sentence except the last word for space checking
        }
        theSentence.append(words.get(words.size()-1));//add the last one to avoid checking for the last space
        return theSentence.toString();
    }

    public static String reverseWords5(String s) {//approach same as 4 but space complexity O(1). Modify in-place (the follow up)
        int length = s.length();
        int validLength = 0;
        int currIdx = 0;
        while(currIdx < length){//traverse to the end
            while(currIdx < length && s.charAt(currIdx) == ' '){//skip the spaces
                currIdx++;
            }//outside this loop means found a letter
            if(currIdx >= length){//out of bound exception when
                return s.substring(1, validLength);//if it is reached then there is a space in front. cut it!
            }
            int start = currIdx;
            while(currIdx < length && s.charAt(currIdx) != ' '){//all the letters
                currIdx++;
                validLength++;
            }//outside this loop means found a space (end of a word)
            //add the word to the front of s, and remove the word from previous position
            if(currIdx < length){
                s = " " + s.substring(start, currIdx) + s.substring(0, start) + s.substring(currIdx, length);
                validLength++;
            }
            else{
                s = s.substring(start, currIdx) + s.substring(0, start);//at the end, no space in front and 0-start is the whole string
            }
            //for the extra space -> length of the string increases and currIdx should be placed to where it was
            currIdx++;
            length++;
        }
        return s.substring(0, validLength);
    }

    public static void main(String[] args) {
        String str = "   hello   world   ";
        System.out.println(reverseWords5(str));
    }
}