package Arrays;

public class StandardQuestions {
    
    public static void main(String[] args) {
        int[] maxsub = {1, 2, 3, -8, 5, 3, 4};
        MaxSubarray(maxsub, maxsub.length);
        MaxDistance(maxsub, 7);
    }

    /**
     * Max subarray with start and end indexes
     * @param a
     * @param n
     */
    public static void MaxSubarray(int[] a, int n) {
        int start = 0, end = 0, s = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += a[i];

            if(sum > max) {
                max = sum;
                start = s;
                end = i;
            }
            if(sum < 0) {
                sum = 0;
                s = i+1;
            }
        }

        System.out.println("Max Sum subarray = " + max);
        System.out.println("Start Index = " + start);
        System.out.println("End Index = " + end);
    }
    
    public static void AddOneToNumber(int[] a, int n) {
        int carry = 1;
        for(int i = n-1; i >= 0; i--) {
            int sum = a[i] + carry;
            a[i] = sum % 10;
            carry = sum / 10;
            if(carry == 0) break;
        }
        if(carry != 0 ) {
            a[0] = 1;
            a[n-1] = 0;
        }
    }

    /**
     * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
     * @param a
     * @param n
     */
    public static void MaxDistance(int[] a, int n) {

        int LMIN[] = new int[n];
        int RMAX[] = new int[n];

        LMIN[0] = a[0];
        RMAX[n-1] = a[n-1];
        for(int i = 1; i < n; i++) {
            LMIN[i] = Math.min(a[i], LMIN[i-1]); 
        }

        for(int j = n-2; j >= 0; j--) {
            RMAX[j] = Math.max(a[j], RMAX[j+1]);
        }

        int i = 0, j = 0, maxD = 0;
        while(i < n && j < n) {

            if(LMIN[i] < RMAX[j]) {
                maxD = Math.max(maxD, j-i);
                j++;
            } else {
                i++;
            }

        }

        System.out.println("max D = " + maxD);

    }


}
