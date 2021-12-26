package Arrays;

public class NumberToWords {

    static String[] lessThanTwenty = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", 
                                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static String[] tens = {"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static String[] thousands = {"", " thousand ", " million ", " billion " };
 
    public static void main(String[] args) {

        System.out.println("Number to Words");

        String words = "";
        int N = 645624567;
        int i = 0;
        while(N > 0) {
            words = helper(N % 1000) + thousands[i] + words;
            N = N / 1000;
            i++;
          
        }
        System.out.println(words);
    }

    public static String helper(int N) {

        if (N == 0) return "";
        else if(N < 20) return lessThanTwenty[N];
        else if(N < 100) return tens[N / 10] + " " + helper(N % 10);
        else {
            return lessThanTwenty[N / 100] + " hundred " + helper(N % 100);
        }

    }

    
}
