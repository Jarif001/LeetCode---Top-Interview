public class Main {

    public static void printArr(int[] num){
        for (int i = 0; i < num.length; i++){
            System.out.print(num[i] + ", ");
        }
        System.out.println();
    }
        public static void shift(int[] num, int sIdx, int m, int n){
            for(int i = m-1; i >= sIdx; i--) {
                num[i + n] = num[i];
            }
        }
        public static void merge(int[] nums1, int m, int[] nums2, int n) {
            int total2 = 0;
            for(int i = 0; i < m+n; i++){
                int end = n-1;
                int start = total2;
                while(end >= start){
                    int mid = (start + end) / 2;
                    if(nums2[mid] <= nums1[i]){
                        shift(nums1, i, m+total2, (mid-start+1));
                        for(int j = start; j <= mid; j++) {
                            nums1[i] = nums2[j];
                            if (j != mid) {
                                i++;
                            }
                            total2++;
                            if(total2 == n){
                                return;
                            }
                        }
                        break;
                    }
                    else {
                        end = mid-1;
                    }
                }
            }
            if(total2 != n){
                int leftout = n-total2;
                for(int i = 0; i < leftout; i++){
                    nums1[n+m-1-i] = nums2[n-1-i];
                }
            }
        }
    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {6};
        int m = 0;
        int n = 1;
        merge(nums1, m, nums2, n);

        for (int i = 0; i < m+n; i++){
            System.out.print(nums1[i] + ", ");
        }
    }
}