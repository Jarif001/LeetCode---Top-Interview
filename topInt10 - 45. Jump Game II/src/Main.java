public class Main {

    public static int jump(int[] nums){//calculate reversely the min steps to be taken from current index to reach the end
        int n = nums.length;            //O(n^2)
        if(n == 1){
            return 0;
        }
        int[] stepsToEnd = new int[n];
        stepsToEnd[n-2] = 1;
        for(int i = n-3; i >= 0; i--){
            int minStepsToEnd = n - 1 - i;
            if(nums[i] >= minStepsToEnd){
                stepsToEnd[i] = 1;
            }
            else{
                for(int j = i+1; j <= i+nums[i]; j++){
                    minStepsToEnd = Integer.min(minStepsToEnd, stepsToEnd[j] + 1);
                }
                stepsToEnd[i] = minStepsToEnd;
            }
        }
        return stepsToEnd[0];
    }

    public static int jump2(int[] nums){
        int currEnd = 0;
        int farthestReach = 0;
        int steps = 0;
        for(int i = 0; i < nums.length-1; i++){
            farthestReach = Math.max(farthestReach, i+nums[i]);
            if(i == currEnd){//if it reaches to the currEnd then another step is taken
                steps++;
                currEnd = farthestReach;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(jump2(nums));
    }
}