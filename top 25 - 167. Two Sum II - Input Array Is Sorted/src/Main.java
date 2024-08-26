import java.util.HashMap;

public class Main {

    public static int[] twoSum(int[] numbers, int target) {//O(logN) --> less than nlogn. O(bigger constant x logn)
        for(int i = 0; i < numbers.length-1; i++){
            int tempTarget = target - numbers[i];//current targeted number
            int leftIdx = i+1;//target after current number
            int rightIdx = numbers.length - 1;
            //binary search
            while(leftIdx <= rightIdx){
                int min = (leftIdx+rightIdx) / 2;
                if(numbers[min] == tempTarget){
                    return new int[]{i+1, min+1};//1-indexing
                }
                else if(numbers[min] < tempTarget){//target is greater so its on right
                    leftIdx = min + 1;
                }
                else{//target is smaller so its on left
                    rightIdx = min - 1;
                }
            }
        }
        return new int[]{0, 0};
    }

    public static int[] twoSum2(int[] numbers, int target) {//left(min) and right(max) sum approach
        int leftIdx = 0;
        int rightIdx = numbers.length - 1;
        while(leftIdx < rightIdx){
            int sum = numbers[leftIdx] + numbers[rightIdx];//current min and max sum
            if(sum < target){//target is greater than current sum. so min has to be increased (move to right)
                leftIdx++;
            }
            else if(sum > target){//target is smaller than current sum. so max has to be decreased (move to left)
                rightIdx--;
            }
            else{//sum is equal to target
                break;
            }
        }
        return new int[]{leftIdx+1, rightIdx+1};//1-based indexing
    }

    public static int[] twoSum3(int[] numbers, int target) {//using hashmap
        int idx1 = 1, idx2 = 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++){
            int tempTarget = target - numbers[i];
            if(map.containsKey(tempTarget)){
                idx1 = map.get(tempTarget) + 1;
                idx2 = i + 1;
                break;
            }
            else{
                map.put(numbers[i], i);//number-index pair
            }
        }
        return new int[]{idx1, idx2};
    }

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15, 17, 19, 22};
        int target = 33;
        int[] result = twoSum2(numbers, target);
        System.out.println(result[0] + ", " + result[1]);
    }
}