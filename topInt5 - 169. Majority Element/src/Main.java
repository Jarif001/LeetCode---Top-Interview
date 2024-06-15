import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void printArr(int[] nums){
        for (int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap();
        for (int num : nums) {//put element to hashmap and count
            hashMap.put(num, hashMap.getOrDefault(nums[0], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> set : hashMap.entrySet()){
            if(set.getValue() > nums.length/2){//traverse the map to get the max count
                return set.getKey();
            }
        }
        return 0;
    }

    public static int majorityElementWithMooresVoting(int[] nums) {//works only when there are more than n/2 elements
        int count = 1;//at first count is 1 coz its set to nums[0] --> one occurrence
        int candidate = nums[0];
        for(int i = 1; i < nums.length; i++){//if currElem is equal to candidate then count increment, else decrement
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count++;
                }
            }
        }
        count = 0;
        //The portion below is not needed in the leetcode problem. coz its guaranteed that more than n/2 occurrenced element will be there
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == candidate){
                count++;
                if(count >= nums.length/2){
                    return candidate;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,6,2,1,2,1,7,1,9,1};
        int k = majorityElementWithMooresVoting(nums);
        System.out.println(k);
    }
}