import java.util.Arrays;

public class Main {

    public static void printArr(int[] nums, int n){
        for(int i = 0; i < n;i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int k = removeElement(nums, 2);
        printArr(nums, k);
    }
}