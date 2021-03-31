/**Given an array of n elements containing elements from 0 to n-1, 
 * with any of these numbers appearing any number of times, 
 * find these repeating numbers in O(n) and using only constant memory space. */
package Arrays;
class ArrayDuplicates {
    public static void main(String[] args0) {
       
        int arr[] = { 1, 6, 3, 1, 3, 6, 6 };
        printRepeating(arr, arr.length);
    }

    private static void printRepeating(int[] arr, int n) {

        for (int i= 0; i < n; i++) {
            arr[arr[i] % n] += n;
        }
        for (int i= 0; i < n; i++) {
            if((arr[i]/n) >= 2) {
                System.out.print(i + " ");
            }
        }

    }
}