import java.util.Arrays;

public class Main {

    public static int candy(int[] ratings) {
        int totalCandies = 0;
        int n = ratings.length;
        if(n == 1){
            totalCandies = 1;
        }
        else{
            int[] candies = new int[n];
            int countToRaise = 0;
            if(ratings[0] > ratings[1]){
                candies[0] = 2;
                totalCandies += 2;
            }
            else{
                candies[0] = 1;
                totalCandies += 1;
            }
            for(int i = 1; i < n; i++){
                if(ratings[i] > ratings[i-1]){
                    totalCandies += (candies[i-1]+1);
                    candies[i] = candies[i-1] + 1;
                    countToRaise = 0;
                }
                else{
                    if(ratings[i] < ratings[i-1]){
                        countToRaise++;
                        if(candies[i-1] == 1){
                            candies[i] = 1;
                            for(int j = 0; j < countToRaise; j++){
                                if(candies[i-1-j] == candies[i-j]){
                                    candies[i-1-j]++;
                                    totalCandies++;
                                }
                                else{
                                    break;
                                }
                            }
                        }
                    }
                    else{
                        countToRaise = 0;
                    }
                    totalCandies++;
                    candies[i] = 1;
                }
            }
        }
        return totalCandies;
    }

    public static int candy2(int[] ratings) {
        int totalCandies = 0;
        int n = ratings.length;
        if(n == 1){
            totalCandies = 1;
        }
        else{
            int prev, highestCandy, highestIdx;//keep track of previous candy, highest till rising, if equal then becomes 1
            highestIdx = 0;                         //coz higher rating will have more candies, equal doesnt matter. so minimize with 1
            //calculating the first index comparing with the 2nd one. this left corner always will be either 2 or 1
            if(ratings[0] > ratings[1]){
                totalCandies += 2;
                highestCandy = 2;
                prev = 2;
            }
            else{
                highestCandy = 1;
                totalCandies += 1;
                prev = 1;
            }
            for(int i = 1; i < n; i++){//index 1 to n-1
                if(ratings[i] > ratings[i-1]){//rating higher than previous will get one extra candy
                    totalCandies += prev + 1;
                    highestCandy = prev + 1;
                    highestIdx = i;//update when it rating rises
                    prev = prev + 1;
                }
                else{//rating less than or equal to previous rating
                    if(ratings[i] < ratings[i-1]){//less than
                        if(prev == 1){//prev is 1. at least 1 candy must be given. so this index will get 1 and previous will be updated
                            if(highestCandy > (prev + 1)){//this increase wont effect the previously assigned highest. so increase all between
                                totalCandies += (i-highestIdx-1);
                                highestCandy--;//difference gets lower by 1
                            }
                            else{//highest candy will be equal to now assigned candies. so update highest rating index too(increment by 1)
                                totalCandies += (i-highestIdx);
                            }
                        }
                    }
                    else{//rating equal
                        highestIdx = i;//consistently rising fails. now current highest will be i-th index
                        highestCandy = 1;//to keep minimum candies
                    }
                    totalCandies++;//give current i-th child a candy
                    prev = 1;
                }
            }
        }
        return totalCandies;
    }

    public static int candy3(int[] ratings) {//2 iterations. from left and then from right
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);//at least one candy
        for(int i = 1; i < ratings.length; i++){//considering only the left neighbour
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }
        }
        int totalCandies = candies[ratings.length-1];
        for(int i = ratings.length-2; i >= 0; i--){//considering right neighbour
            if(ratings[i] > ratings[i+1]){
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
            totalCandies += candies[i];
        }
        return totalCandies;
    }

    public static void main(String[] args) {
        int[] ratings = {1,0,2};
        System.out.println(candy3(ratings));
    }
}