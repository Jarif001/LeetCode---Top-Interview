import java.util.Arrays;

public class Main {

    public static String longestCommonPrefix(String[] strs) {
        int i = 0;
        while(i < strs[0].length()){//index of letter
            for(int j = 1; j < strs.length; j++){//index of string (word)
                if(i >= strs[j].length() || i >= strs[j-1].length() || strs[j].charAt(i) != strs[j-1].charAt(i)){
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
        return strs[0].substring(0, i);
    }

    public static String longestCommonPrefix2(String[] strs) {//if checking statement is reduced by introducing minLength
        int minLength = 200;                                 // so indices won't be out of bound
        for(int i = 0; i < strs.length; i++){
            minLength = Math.min(minLength, strs[i].length());
        }
        int i = 0;
        while(i < minLength){//index of letter
            for(int j = 1; j < strs.length; j++){//index of string (word)
                if(strs[j].charAt(i) != strs[j-1].charAt(i)){
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
        return strs[0].substring(0, i);
    }

    public static String longestCommonPrefix3(String[] strs) {//sorting the strings and compare the most possible different strings
        Arrays.sort(strs);//sorting the array lexicographically        // first and the last string in that sorted array of strings
        int i;
        int min = Math.min(strs[0].length(), strs[strs.length-1].length());//min length of this two, so indices are in bound
        for(i = 0; i < min; i++){
            if(strs[0].charAt(i) != strs[strs.length-1].charAt(i)){
                break;
            }
        }
        return strs[0].substring(0, i);
    }

    public static String longestCommonPrefix4(String[] strs) {//longest common prefix sequentially
        String currPrefix = strs[0];//assuming the first string is the prefix
        for (String currStr:  strs) {//check for every string
            //shorting down the prefix with the current string
            while ((currStr.length() < currPrefix.length()) || (!currPrefix.equals(currStr.substring(0, currPrefix.length())))) {
                //if prefix doesn't match then prefix length decremented by 1. then check again if it matches
                currPrefix = currPrefix.substring(0, currPrefix.length() - 1);
            }
        }
        return currPrefix;
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix3(strs));
    }
}