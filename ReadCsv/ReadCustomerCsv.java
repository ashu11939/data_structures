package ReadCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadCustomerCsv {
    public static void main(String[] args) {
        String line = "";  
        String splitBy = "\t";  
        try   
        {  
            // Create an empty hash map by declaring object
            // of string and integer type
            HashMap<Integer, String> customerMap = new HashMap<>();
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("C://Users//ashutosh.kumar/Downloads/customer_details.tsv")); 
            int p = 1;
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                
                if (p >=2) {
                String[] customer = line.split(splitBy);    // use comma as separator  
                String name = customer[1].trim();
                int id = Integer.parseInt(customer[0].trim());
                customerMap.put(id, name);
                }
                p++;
                
            }  
            System.out.println("Total Customers : " + customerMap.size());
                
            // for (Map.Entry<Integer, String> e : map.entrySet()){
            //     System.out.println("Key: " + e.getKey()
            //                 + " Value: " + e.getValue());
            // }  
        }   
            catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
    }  
}
