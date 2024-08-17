public class Main {

    public static String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length()){//numrows 1 means the string as it is. same for rows equal to or greater than length
            return s;
        }
        StringBuilder theString = new StringBuilder();
        int initialOffset = (numRows-1) * 2;//follows this pattern (for 4 rows --> offsets 6,4,2,6)
        int count = 0;//count the letters
        int currIdx;
        int startIdx = 0;//starting of the row
        while(count < s.length()){//until all the letters are added
            currIdx = startIdx;
            int offset = initialOffset;
            while(currIdx < s.length()){//doing the work for the current row
                theString.append(s.charAt(currIdx));
                count++;
                currIdx += offset;
                if(((numRows-1) * 2) - offset != 0){//for the first and last row, offset remains the same
                    offset = ((numRows-1) * 2) - offset;//otherwise offset becomes initial offset - current offset
                }
            }
            startIdx++;//next row
            initialOffset -= 2;//initial offset decreases by 2 for the next row
            if(initialOffset == 0){//resets to initial offset (row 1 offset)
                initialOffset = (numRows-1) * 2;
            }

        }
        return theString.toString();
    }

    public static String convert2(String s, int numRows) {//same as previous. here no count var. row wise iteration
        if(numRows == 1 || numRows >= s.length()){//numrows 1 means the string as it is. same for rows equal to or greater than length
            return s;
        }
        StringBuilder theString = new StringBuilder();
        int initialOffset = (numRows-1) * 2;//follows this pattern (for 4 rows --> offsets 6,4,2,6)
        int currIdx;
        for(int i = 0; i < numRows; i++){//until all the letters are added
            currIdx = i;
            int offset = initialOffset;
            while(currIdx < s.length()){//doing the work for the current row
                theString.append(s.charAt(currIdx));
                currIdx += offset;
                if(((numRows-1) * 2) - offset != 0){//for the first and last row, offset remains the same
                    offset = ((numRows-1) * 2) - offset;//otherwise offset becomes initial offset - current offset
                }
            }
            initialOffset -= 2;//initial offset decreases by 2 for the next row
            if(initialOffset == 0){//resets to initial offset (row 1 offset)
                initialOffset = (numRows-1) * 2;
            }

        }
        return theString.toString();
    }

    public static String convert3(String s, int numRows) {//Arrange the letters to rows accordingly
        if(numRows == 1 || numRows >= s.length()){//numrows 1 means the string as it is. same for rows equal to or greater than length
            return s;
        }
        StringBuilder[] strs = new StringBuilder[numRows];//array of strings (rows)
        for(int i = 0; i < numRows; i++){
            strs[i] = new StringBuilder();//initializing the stringBuilders
        }
        boolean increasing = true;//whether the index of row is upward or downward
        int idxOfStrs = 0;//keeps track of the current row index (0,1,2,3,...,numRows-1,numRows-2,numRows-3,....3,2,1,0,1,2,3...)
        for(int i = 0; i < s.length(); i++){
            if(increasing){
                strs[idxOfStrs++].append(s.charAt(i));
                if(idxOfStrs == numRows){
                    idxOfStrs = numRows - 2;
                    increasing = false;
                }
            }
            else {
                strs[idxOfStrs--].append(s.charAt(i));
                if(idxOfStrs == -1){
                    idxOfStrs = 1;
                    increasing = true;
                }
            }
        }
        StringBuilder theString = new StringBuilder();
        for (int i = 0; i < strs.length; i++){//iterating the rows to build the string
            theString.append(strs[i]);
        }
        return theString.toString();
    }

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        System.out.println(convert3(str, 4));
    }

}