import java.util.Arrays;

public class Main {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        if(citations[citations.length-1] == 0){//only zero(es) then no citation at all
            return 0;
        }
        int hIdx = 0;
        int remaining = citations.length;//keeps track of how many papers are left
        for(int i = 0; i < citations.length; i++){
            remaining = citations.length-i;
            if(citations[i] <= remaining){//at least that many papers and all the remaining papers have more citations
                hIdx = citations[i];        //      as the array is sorted
            }
            else{
                break;
            }
        }
        if(hIdx < remaining){//hIndex is less than the number remaining papers and they have more citations
            hIdx = remaining;
        }
        return hIdx;
    }

    public static int hIndex2(int[] citations){//no. of frequencies --> no. of papers that have that number of citations
        int[] frquencies = new int[1001]; //range from 0 to 1000 --> total 1001 (index 2 --> no. of papers that have 2 citations)
        for(int i = 0; i < citations.length; i++){
            frquencies[citations[i]]++;
        }
        int maxPsblHIdx;//highest 1000 citations can be possible
        int count = 0;
        for(maxPsblHIdx = 1000; maxPsblHIdx >= 0; maxPsblHIdx--){
            count += frquencies[maxPsblHIdx];//counting papers from highest citation
            if(count >= maxPsblHIdx){//it means that no. of papers of at least that no. of citations is the max
                break;
            }
        }
        return maxPsblHIdx;
    }

    public static void main(String[] args) {
        int[] nums = {3,0,6,1,5,5,5};
        System.out.println(hIndex2(nums));
    }
}