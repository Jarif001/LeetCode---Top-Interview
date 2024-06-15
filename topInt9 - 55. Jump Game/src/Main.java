public class Main {

    public static boolean isStuck(int[] nums, int zeroIdx){//check from zero to previously checked zero
        for(int i = zeroIdx-1; i >= 0; i--){
            if(nums[i] > zeroIdx-i){
                return false;
            }
        }
        return true;
    }
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return true;
        }
        else{
            if(nums[0] == 0){
                return false;
            }
            else{
                for(int i = 1; i < n-1; i++){//n-1 because we dont have to calc for the last zero [2,1,0]
                    if(nums[i] == 0){
                        //Check if it is stuck in this zero
                        if(isStuck(nums, i)){
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    public static boolean canJump2(int[] nums){//keep track of last reachable index that can jump to the end
        int n = nums.length;
        boolean canJump = true;
        int lastReachableIdx = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] >= lastReachableIdx - i) {
                lastReachableIdx = i;
                canJump = true;
            } else {
                canJump = false;
            }
        }
        return canJump;
    }//1 ms runtime. beats 99.97%

    public static boolean canJump3(int[] nums){//keeps track of the highest jump made from index i
        int highestReachableIdx = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > highestReachableIdx){
                return false;
            }
            highestReachableIdx = Math.max((i + nums[i]), highestReachableIdx);
        }
        return true;
    }

    public static boolean canJump4(int[] nums){//counting total indices that i can jump
        int canJumpFurtherUpto = 0;//number of jumps that can be made from current idx
        for(int i = 0; i < nums.length; i++){
            if(canJumpFurtherUpto < 0){//cant jump anymore, means this current idx is not reachable from any previous idx
                return false;
            }
            else if(nums[i] > canJumpFurtherUpto){//im here means i could jump to i. if this can lead to farther idx then update the jump var
                canJumpFurtherUpto = nums[i];
            }
            canJumpFurtherUpto -= 1;//im jumping forward one by one (i to i+1 index)
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2,0,0,1,1,2,2,0,2,2};
        System.out.println(canJump4(nums));
    }
}