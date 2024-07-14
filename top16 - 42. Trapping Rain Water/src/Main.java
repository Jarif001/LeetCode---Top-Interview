public class Main {

    public static int trap(int[] height) {//two traversals (if higher bar found then calculate water trapped in between)
        int highIdx = 0, highBarLvl = 0;//keeping track of current highest height and its index
        int minusWater = 0;//that is filled with bar instead of water in between two big bars. has to minus
        int trappedWater = 0;//amount of water trapped

        for (int i = 0; i < height.length; i++) {
            if (height[i] >= highBarLvl) {//greater than or equal to current highest, so calculate trapped water in between
                //trapped water if there was no bar in between (minus) the number of small bars in between
                trappedWater += (((i - highIdx - 1) * highBarLvl) - minusWater);
                minusWater = 0;//reset no. of bars in between
                highIdx = i;//it's the index of current highest
                highBarLvl = height[i];//current highest level
            }
            else {
                minusWater += height[i];//number of small bars in between
            }
        }
        //only greater than and equal of current highest case is considered above. what if there is no bar larger than the current highest?
        //that means all other bars after highest index is smaller. so we can think it reversely as the above solution
        minusWater = 0;
        highBarLvl = 0;
        int currentEndIdx = highIdx;
        highIdx = height.length-1;
        for(int i = height.length-1; i >= currentEndIdx; i--){//reversely traversing from last to current highest index
            if (height[i] >= highBarLvl) {
                trappedWater += (((highIdx - i - 1) * highBarLvl) - minusWater);
                minusWater = 0;
                highIdx = i;
                highBarLvl = height[i];
            }
            else {
                minusWater += height[i];
            }
        }
        return trappedWater;
    }

    public static int trap2(int[] height) {//approaching from lower side (each time calculating the amount of trapped water on each bar)
        int leftIdx = 0, rightIdx = height.length-1;                    //column wise water trapped counting
        int leftMax = height[0];//keeping track of left max
        int rightMax = height[height.length-1];//keeping track of right max
        int trappedWater = 0;
        while(leftIdx < rightIdx){
            if(leftMax <= rightMax){//left side is lower. so water will be trapped there because there is a higher bar in right side
                trappedWater += (leftMax-height[leftIdx]);//Water will be trapped from lower bar to higher bar level
                leftIdx++;//going to next bar
                leftMax = Math.max(leftMax, height[leftIdx]);//updating the leftMax if found
            }
            else{//right side is lower. water will be trapped there because there is a higher bar in left side
                trappedWater += (rightMax-height[rightIdx]);
                rightIdx--;
                rightMax = Math.max(rightMax, height[rightIdx]);
            }
        }
        return trappedWater;
    }

    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};
        System.out.println(trap(height));
    }
}