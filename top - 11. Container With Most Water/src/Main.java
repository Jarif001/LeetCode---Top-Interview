public class Main {

    public static int maxArea(int[] height) {//from left and right
        int containedWater = Math.min(height[0], height[height.length-1]) * (height.length-1);//initial best
        int leftIdx = 0;
        int rightIdx = height.length - 1;
        while(leftIdx < rightIdx){
            int leftMoved = Math.min(height[leftIdx+1], height[rightIdx]) * (rightIdx - leftIdx - 1);//moving from left
            int rightMoved = Math.min(height[leftIdx], height[rightIdx - 1]) * (rightIdx - 1 - leftIdx);//moving from right
            int bothMoved = Math.min(height[leftIdx+1], height[rightIdx-1]) * (rightIdx - leftIdx - 2);//move from both left and right
            //make changes according to the best move
            if(leftMoved > containedWater){//by moving left, we get the current best
                leftIdx++;
                containedWater = leftMoved;
            }
            else if(rightMoved > containedWater){//by moving right, we get the current best
                rightIdx--;
                containedWater = rightMoved;
            }
            else if(bothMoved > containedWater){//by moving left and right both, we get the current best
                leftIdx++;
                rightIdx--;
                containedWater = bothMoved;
            }
            else{//best remains the same --> move from the smaller bar
                if(height[leftIdx] > height[rightIdx]){
                    rightIdx--;
                }
                else{
                    leftIdx++;
                }
            }
        }
        return containedWater;
    }

    public static int maxArea2(int[] height) {//move to bigger bar (finding bigger bar)
        int containedWater = 0;
        int lefIdx = 0;
        int rightIdx = height.length - 1;
        while(lefIdx < rightIdx){
            int currContainedWater = Math.min(height[lefIdx], height[rightIdx]) * (rightIdx - lefIdx);//contained by current bars
//            containedWater = Math.max(currContainedWater, containedWater);//take the max
            if(currContainedWater > containedWater){
                containedWater = currContainedWater;
            }
            if(height[lefIdx] > height[rightIdx]){//move from the smaller bar
                rightIdx--;
            }
            else{
                lefIdx++;
            }
        }
        return containedWater;
    }

    public static int maxArea3(int[] height) {//same as 2. lowering runtime (not using javas methods - min, max)
        int containedWater = 0;
        int lefIdx = 0;
        int rightIdx = height.length - 1;
        while(lefIdx < rightIdx){
            int currContainedWater;
            if(height[lefIdx] > height[rightIdx]){
                currContainedWater = height[rightIdx] * (rightIdx - lefIdx);
                rightIdx--;
            }
            else{
                currContainedWater = height[lefIdx] * (rightIdx - lefIdx);
                lefIdx++;
            }
            if(currContainedWater > containedWater){
                containedWater = currContainedWater;
            }
        }
        return containedWater;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea2(height));
    }
}