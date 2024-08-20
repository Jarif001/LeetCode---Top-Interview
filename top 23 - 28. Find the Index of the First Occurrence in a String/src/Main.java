public class Main {

    public static int strStr(String haystack, String needle) {//using javas method
        return haystack.indexOf(needle);
    }

    public static int strStr2(String haystack, String needle) {//naive approach - iterate from every index - O(mn)
        for(int i = 0; i <= haystack.length()-needle.length(); i++){//start from every index to match
            int idxHaystack = i, idxNeedle = 0;
            while(haystack.charAt(idxHaystack) == needle.charAt(idxNeedle)){//compare at the current indices
                idxHaystack++;
                idxNeedle++;
                if(idxNeedle == needle.length()){//pattern matched
                    return i;
                }
            }
        }
        return -1;//if it is reached then pattern not matched
    }

    public static int strStr3(String haystack, String needle){//KMP algorithm - O(m+n)
        //construct lps array
        // Length of the previous longest prefix suffix
        int len = 0;
        int[] lps = new int[needle.length()];
        lps[0] = 0;// lps[0] is always 0
        int i = 1;
        while (i < needle.length()) {//iterate over all the letters
            if (needle.charAt(i) == needle.charAt(len)){//value is (len+1) and increment both
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len != 0){
                    len = lps[len - 1];//go to previous cell value and compare again with the same letter
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }

        //kmp algo
        int idx = -1;
        int M = needle.length();
        int N = haystack.length();
        i = 0; // index for haystack
        int j = 0; // index for needle
        while((N - i) >= (M - j)){//no. of remaining letters in the haystack is greater than that of needle
            if (needle.charAt(j) == haystack.charAt(i)) {//character matched --> increment both indices
                j++;
                i++;
            }
            if (j == M) {//matched the whole pattern
                idx = i - j;//0 based indexing
                break;
            }
            else if(i < N && needle.charAt(j) != haystack.charAt(i)){
                if (j != 0){
                    j = lps[j - 1];//go back to previous cell value and compare the same letter
                }
                else{
                    i = i + 1;
                }
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        String haystack = "leetcode", needle = "leeto";
        System.out.println(strStr2(haystack, needle));
    }
}