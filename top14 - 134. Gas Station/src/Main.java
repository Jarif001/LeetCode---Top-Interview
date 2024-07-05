public class Main {

    public static int canCompleteCircuit(int[] gas, int[] cost) {//optimizing by keeping track of consecutive gains
        int n = gas.length;                                     //next starting station will be
        for(int i = 0; i < n; i++){
            int gasCount = gas[i] - cost[i];//takes some gas, but it needs some gas to reach the next station
            int countStation = 0;
            int consecutiveGain = 0;
            boolean consecutivelyGaining = true;
            while(gasCount >= 0){
                countStation++;//has enough gas to reach the next station
                if(countStation == n){
                    return i;
                }
                int gasGain = gas[(i+countStation)%n] - cost[(i+countStation)%n];
                if(gasGain >= 0 && consecutivelyGaining){
                    consecutiveGain++;
                }
                else{
                    consecutivelyGaining = false;
                }
                gasCount = gasCount + gasGain;
            }
            i = i + consecutiveGain;
        }
        return -1;
    }

    public static int canCompleteCircuit2(int[] gas, int[] cost) {//it also counts the consecutive loss stations that cant be starting station
        int n = gas.length;
        for(int i = 0; i < n; i++){
            int gasCount = gas[i] - cost[i];//takes some gas, but it needs some gas to reach the next station
            int countStation = 0;
            int consecutiveGain = 0;
            boolean consecutivelyGaining = true;
            int consecutiveLoss = 0;
            boolean consecutivelyLosing = false;
            while(gasCount >= 0){
                countStation++;//has enough gas to reach the next station
                if(countStation == n){
                    return i;
                }
                int gasGain = gas[(i+countStation)%n] - cost[(i+countStation)%n];
                if(gasGain >= 0){
                    if(consecutivelyGaining){
                        consecutiveGain++;
                    }
                    if(consecutivelyLosing){//after consecutively losing, any gains will make consecutive losing stop
                        consecutivelyLosing = false;
                    }
                }
                else{
                    if(consecutivelyGaining){//when for the first time consecutive gaining stops, then consecutive losing starts
                        consecutivelyGaining = false;
                        consecutivelyLosing = true;//only this time it can be true (only once)
                    }
                    if(consecutivelyLosing){
                        consecutiveLoss++;
                    }
                }
                gasCount = gasCount + gasGain;
            }
            i = Math.max((i + consecutiveGain), (i + consecutiveLoss));
        }
        return -1;
    }

    public static int canCompleteCircuit3(int[] gas, int[] cost) {//same optimized concept but with less calculation
                                                                    //using the idea of (either no solution or unique solution)
        int totalGas = 0, totalCost = 0;
        int gasCount = 0;
        int startIdx = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
            gasCount += (gas[i] - cost[i]);
            if(gasCount < 0){
                startIdx = i + 1;//it means from start to i-th station, there is not enough gas(it comes gaining but still it had not enough)
                gasCount = 0;
            }
        }
        if(totalGas < totalCost){//total gas gain cant be negative. total gas must be greater than total cost
            return -1;
        }
        return startIdx;
    }

    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(canCompleteCircuit3(gas, cost));
    }
}