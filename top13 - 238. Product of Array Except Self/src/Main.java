public class Main {

    public static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    public static boolean diffSign(int a, int b){
        if((a > 0 && b > 0) || (a < 0 && b < 0)){
            return false;
        }
        return true;
    }

    public static int[] productExceptSelf(int[] nums) {//using minus as division --> gets a time limit exceeds
        int n = nums.length;
        int[] prodArr = new int[n];
        int product = 1;
        int zeroCount = 0;
        int zeroIdx = -1;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                product *= nums[i];
            }
            else {
                zeroCount++;
                zeroIdx = i;
                if(zeroCount == 2){
                    break;
                }
            }
        }
        System.out.println("Product: " + product);
        if(zeroCount == 0){
            for(int i = 0; i < n; i++){
                int currNum = nums[i];
                int count = 0;
                while(Math.abs(currNum) != Math.abs(product)){//product and currNum can be of different signs
                    currNum += nums[i];
                    count++;
                }
                prodArr[i] = count + 1;

                //if the product is not that high, we can use it. Otherwise it will overflow
//                if(product * nums[i] < 0){//product and num is of diff sign. so result is negative
//                    prodArr[i] = -prodArr[i];
//                }

                if(diffSign(product, nums[i])){
                    prodArr[i] = -prodArr[i];
                }
            }
        }
        else if(zeroCount == 1){//One zero ==> all products are zeroes but the zero index
            prodArr[zeroIdx] = product;
        }
        return prodArr;//by default all zeroes ==> more than 1 zero case
    }

    public static int[] productExceptSelf2(int[] nums) {//maintaining left and right product of the current element
        int n = nums.length;
        int[] products = new int[n];
        int[] productLeft = new int[n];
        productLeft[0] = 1;//for left most element (there is no element in left for it)
        int[] prductRight = new int[n];
        prductRight[n-1] = 1;//for right most element (there is no element in right for it)
        for(int i = 1; i < n; i++){
            productLeft[i] = productLeft[i-1] * nums[i-1];//product upto current term (excluding)
        }
        for(int i = n-2; i >= 0; i--){
            prductRight[i] = prductRight[i+1] * nums[i+1];//product upto current term from right(exclusive)
        }
        for(int i = 0; i < n; i++){
            products[i] = productLeft[i] * prductRight[i];
        }
        return products;
    }

    public static int[] productExceptSelf3(int[] nums){//space optimized
        int n = nums.length;//for convenience. for more optimization, can omit using n. instead use nums.length
        int[] products = new int[n];
        //at first using products as left product
        products[0] = 1;
        for(int i = 1; i < n; i++){
            products[i] = products[i-1] * nums[i-1];
        }
        //now calculating right products and the main products here together
        int rightProd = 1;//keep tracks of the right product till that element
        for(int i = n-1; i >= 0; i--){
            products[i] = products[i] * rightProd;
            rightProd = rightProd * nums[i];
        }
        //to make it more space efficient, omitting rightProd (bad practise!!! main data is changed!!!)
//        for(int i = n-2; i >= 0; i--){
//            products[i] = products[i] * nums[i+1];
//            nums[i] = nums[i+1] * nums[i];
//        }
        return products;
    }

    public static void main(String[] args) {
        int[] nums = {-1,1,0,-3,3};
        int[] prodArr;
        prodArr = productExceptSelf3(nums);
        printArr(prodArr);
    }
}