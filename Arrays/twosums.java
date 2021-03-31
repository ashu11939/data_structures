package Arrays;

import java.util.HashMap;
import java.util.Map; 
class Solution {

    public static void main(String args[]) {
        System.out.println("Hello World");
        int[] arr = twoSum(new int[] { 3, 2, 4 }, 6);
        System.out.println(arr[0] +" , "+ arr[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        
        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(hash.containsKey(complement)) {
                return new int[]{i, hash.get(complement)};
                        }
            hash.put(nums[i], i);
        }
        return new int[]{2};
        
    }
}