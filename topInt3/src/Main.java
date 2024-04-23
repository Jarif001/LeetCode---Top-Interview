public class Main {

    public static int removeDuplicates(int[] nums) {
        int unique = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i-1]){//comparing with prev
                nums[unique] = nums[i];
                unique++;
            }
        }
        return unique;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,4,6,6,8};
        int k = removeDuplicates(nums);
        for(int i = 0; i < k; i++){
            System.out.print(nums[i] + "  ");
        }
    }
}