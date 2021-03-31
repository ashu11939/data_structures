/**
 * You are given a list of n-1 integers and these integers are in the range of 1 to n. 
 * There are no duplicates in the list. One of the integers is missing in the list.
 * Write an efficient code to find the missing integer.
 */
package Arrays;
public class MissingNumber {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 6, 5, 8};
        int num = missingNum(arr, arr.length+1);
        System.out.println(num);
        System.out.println(missingNum2(arr, arr.length));
    }

    private static Integer missingNum(int[] arr, int n)  {
        int sum = 0;
        for (int i = 0; i < n-1; i++) {
            sum += arr[i];
        }
        System.out.println("sum: "+sum);
        int totalsum = (n * (n+1)) / 2;
        System.out.println("totalsum: "+ totalsum);
        return totalsum - sum;
    }

    private static Integer missingNum2(int[] arr, int n)  {
        /**
         * Approach: The approach remains the same but there can be overflow 
         * if n is large. In order to avoid integer overflow,
         * pick one number from known numbers and s
         * subtract one number from given numbers. 
         * This way there won’t have Integer Overflow ever.
         */
        int sum = 1;
        for (int i = 2; i <= (n+1); i++) {
            sum += i;
            sum -= arr[i-2];
        }
        
        return sum;
    }
}
