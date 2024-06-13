public class Main {

    public static void printNum(int[] num){
        for(int i = 0; i < num.length; i++){
            System.out.print(num[i] + ", ");
        }
        System.out.println();
    }
    public static int[] rotate(int[] nums, int k) {//copying to array
        int n = nums.length;
        k = k % n;
        int[] numCpy = new int[n];
        for(int i = 0; i < n; i++){
            numCpy[i] = nums[i];
        }
        for(int i = 0; i < n; i++){
            nums[(i+k)%n] = numCpy[i];
        }
        return nums;
    }

    public static void rotate2(int[] nums, int k){//start with 1st, then forward k indices until coming back to start again
        int n = nums.length;                //it goes on cyclic order until n shifts
        k = k % n;
        if(k == 0){
            return;
        }
        int currIdx = 0;
        int initialIdx = 0;
        int currElem = nums[currIdx];
        int count = 0;
        while(count < n){ //total n shifts
            //0->k->2k->...again returning to start
            while(k + currIdx < n){
                int newIdx = (currIdx+k) % n;
                int prev = nums[newIdx];
                nums[newIdx] = currElem;
                currIdx = newIdx;
                currElem = prev;
                count++;
            }
            //shifting the element that needs cyclic shift
            int newIdx = (currIdx+k) % n;
            int prev = nums[newIdx];
            nums[newIdx] = currElem;
            currIdx = newIdx;
            currElem = prev;
            count++;
            if(currIdx == initialIdx){ //if it returns to initial starting then move current idx to next idx coz initial idx has been shifted
                currIdx++;
                initialIdx = currIdx;
                currElem = nums[currIdx];
            }
        }
    }

    public static void rotate3(int[] nums, int k){//O(n^2) solution. time limit exceeds
        int n = nums.length;
        k = k % n;
        for(int i = 0; i < k; i++){//shifting all elements 1 idx right --> total k times
            int last = nums[n-1];
            for (int j = n-1; j > 0; j--){
                nums[j] = nums[j-1];
            }
            nums[0] = last;
        }
    }

    public static void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate4(int[] nums, int k) {//reversing the array
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5,6,7};
        printNum(num);
//        int[] num2 = rotate(num, 2);
        rotate4(num, 3);
        printNum(num);
    }
}