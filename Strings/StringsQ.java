package Strings;

import java.util.HashSet;

public class StringsQ {

    public static void main(String[] args) {
        System.out.println("StringsQ.main()");
        String s = "pwwkew";
        System.out.println("length of LongestSubstring " + lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<Character>();       
        for(int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        
        return set.size();
        
    }

}
