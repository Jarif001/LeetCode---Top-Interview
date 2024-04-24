public class Main {

    public static void printArr(int[] num, int n){
        for(int i = 0; i < n; i++){
            System.out.print(num[i] + "  ");
        }
        System.out.println();
    }
    public static int removeDuplicates(int[] nums) {
        if(nums.length > 2){
            int unique = 2;
            for(int i = 2; i < nums.length; i++){
                if(nums[i] != nums[unique-2]){//array is ok upto unique no. of elements. so comparing with 2nd last of ok array
                    nums[unique++] = nums[i];//adding one more element to ok array
                }
            }
            return unique;
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,3,3};
        int k = removeDuplicates(nums);
        printArr(nums, k);
    }
}