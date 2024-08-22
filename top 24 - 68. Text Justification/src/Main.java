import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static List<String> fullJustify(String[] words, int maxWidth) {//with debugging lines
        ArrayList<String> strList = new ArrayList<>();

        //Counting the total lengths of all words ignoring the spaces in between
//        int totalLen = 0;
//        for(String str : words){//length of words without space
//            totalLen += str.length();
//        }
//        System.out.println("Total words " + words.length);
//        System.out.println("Withoud spaces len " + totalLen);
//        totalLen += (words.length - 1);//adding minimum number of spaces between them
//        System.out.println("Total length with spaces " + totalLen);
//        int rows = (int) Math.ceil(1.0 * totalLen / maxWidth);//number of rows those words will be fitted in
//        System.out.println("Rows number " + rows);

        //work on every row
        int startIdx = 0;
        while (startIdx < words.length) {
            int lastIdx = startIdx;
            int currIdx = startIdx;
//            System.out.println("Starting this row - " + i + " from idx " + currIdx);
            int wordsLenInThisRow = 0;//considering one space between words
            //counting number of words in this row
            while (currIdx < words.length) {
                wordsLenInThisRow += words[currIdx++].length();
                if (wordsLenInThisRow == maxWidth) {//matches exactly to maxWidth
                    lastIdx = currIdx - 1;//last word of this row
                    break;
                } else if (wordsLenInThisRow < maxWidth) {//space for a space
                    wordsLenInThisRow++;
                } else {//passes maxwidth. so minus the last length
                    wordsLenInThisRow -= words[currIdx - 1].length();
                    wordsLenInThisRow--;//minus 1 for extra space was added
                    lastIdx = currIdx - 2;//last word of this row (minus 2 for it has gone 2 steps forward)
                    break;
                }
                if(currIdx == words.length){
                    wordsLenInThisRow--;
                    lastIdx = currIdx - 1;
                }
            }
            System.out.println("Words len with one space in this row " + wordsLenInThisRow);

            System.out.println("Last word idx for this row " + lastIdx);
            System.out.println("So words from " + words[startIdx] + " <---> " + words[lastIdx]);

            //counting spaces
            int spaceSlots = lastIdx - startIdx;//how many slots for spaces to put between words
            int extraSpaces = maxWidth - wordsLenInThisRow;
            int spaceInASlot = 0;
            if (spaceSlots != 0) {
                spaceInASlot = extraSpaces / spaceSlots;
            }
            System.out.println("Spaceslots - " + spaceSlots + ", extraspaces - " + extraSpaces + ", Spaces in a slot - " + spaceInASlot);

            //construct the string for this row
            currIdx = startIdx;
            StringBuilder str = new StringBuilder();
            if (startIdx == lastIdx) {//only one word in this row
                str.append(words[currIdx]);
                if (extraSpaces > 0) {//doesn't fit exactly. so need spaces
                    str.append(" ".repeat(extraSpaces));
                }
            } else {//more than one word
                if(lastIdx != words.length - 1){//not the last row
                    //calculation for uneven spaces
                    int[] spaceCounts = new int[spaceSlots];
                    Arrays.fill(spaceCounts, spaceInASlot);
                    int remainingSpaces = extraSpaces - (spaceInASlot * spaceSlots);//remaining spaces will be at the left slots
                    for(int i = 0; i < remainingSpaces; i++){
                        spaceCounts[i]++;
                    }
                    int idxForSpaceSlot = 0;
                    while (currIdx <= lastIdx) {
                        str.append(words[currIdx]);
                        if (currIdx != lastIdx) {
                            str.append(" ");//normal one space
                            str.append(" ".repeat(spaceCounts[idxForSpaceSlot++]));//extra space for padding to maxwidth
                        }
                        currIdx++;
                    }
                }
                else{
                    System.out.println("Duski last part e. eikhane jhamela!!!");
                    //last row (add the remaining words normally as left justified)
                    for(int i = startIdx; i < words.length; i++){
                        str.append(words[i]);
                        if(i != words.length-1){
                            str.append(" ");//add a space after every word except the last word
                        }
                    }
//                    System.out.println("Last row starts from idx " + startIdx);
                    //adding spaces to pad the maxwidth
                    str.append(" ".repeat(maxWidth-str.length()));
                }
            }
            System.out.println("-----------------------------");
            System.out.println(str.toString());
            System.out.println("------------------------------");
            //add the string to the list
            strList.add(str.toString());
            startIdx = lastIdx + 1;
        }
        return strList;
    }

    public static List<String> fullJustify2(String[] words, int maxWidth) {//same as the previous one. But clean
        List<String> strList = new ArrayList<>();
        //work on every row
        int startIdx = 0;
        while (startIdx < words.length) {
            int lastIdx = startIdx;
            int currIdx = startIdx;
            int wordsLenInThisRow = 0;//considering one space between words
            //counting number of words in this row
            while (currIdx < words.length) {
                wordsLenInThisRow += words[currIdx++].length();
                if (wordsLenInThisRow == maxWidth) {//matches exactly to maxWidth
                    lastIdx = currIdx - 1;//last word of this row
                    break;
                } else if (wordsLenInThisRow < maxWidth) {//space for a space
                    wordsLenInThisRow++;
                } else {//passes maxwidth. so minus the last length
                    wordsLenInThisRow -= words[currIdx - 1].length();
                    wordsLenInThisRow--;//minus 1 for extra space was added
                    lastIdx = currIdx - 2;//last word of this row (minus 2 for it has gone 2 steps forward)
                    break;
                }
                if (currIdx == words.length) {
                    wordsLenInThisRow--;
                    lastIdx = currIdx - 1;
                }
            }
            //counting spaces
            int spaceSlots = lastIdx - startIdx;//how many slots for spaces to put between words
            int extraSpaces = maxWidth - wordsLenInThisRow;
            int spaceInASlot = 0;
            if (spaceSlots != 0) {
                spaceInASlot = extraSpaces / spaceSlots;
            }
            //construct the string for this row
            currIdx = startIdx;
            StringBuilder str = new StringBuilder();
            if (startIdx == lastIdx) {//only one word in this row
                str.append(words[currIdx]);
                if (extraSpaces > 0) {//doesn't fit exactly. so need spaces
                    str.append(" ".repeat(extraSpaces));
                }
            } else {//more than one word
                if (lastIdx != words.length - 1) {//not the last row
                    //calculation for spaces
                    int[] spaceCounts = new int[spaceSlots];
                    Arrays.fill(spaceCounts, spaceInASlot);
                    int remainingSpaces = extraSpaces - (spaceInASlot * spaceSlots);//remaining for uneven spaces will be at the left slots
                    for(int i = 0; i < remainingSpaces; i++){
                        spaceCounts[i]++;//adding uneven spaces to left slots
                    }
                    int idxForSpaceSlot = 0;
                    while (currIdx <= lastIdx) {
                        str.append(words[currIdx]);
                        if (currIdx != lastIdx) {
                            str.append(" ");//normal one space
                            str.append(" ".repeat(spaceCounts[idxForSpaceSlot++]));//extra space for padding to maxwidth
                        }
                        currIdx++;
                    }
                } else {
                    //last row (add the remaining words normally as left justified)
                    for (int i = startIdx; i < words.length; i++) {
                        str.append(words[i]);
                        if (i != words.length - 1) {
                            str.append(" ");//add a space after every word except the last word
                        }
                    }
                    //adding spaces to pad the maxwidth
                    str.append(" ".repeat(maxWidth - str.length()));
                }
            }
            //add the string to the list
            strList.add(str.toString());
            startIdx = lastIdx + 1;
        }
        return strList;
    }

    public static void main(String[] args) {
        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int  maxWidth = 20;
        ArrayList<String> strss = (ArrayList<String>) fullJustify(words, maxWidth);
        for (String s : strss){
            System.out.println(s);
        }
    }
}