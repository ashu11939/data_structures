package ReadCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * select customerid, COUNT(*) as a from phoenix_fs_content_rule where exclude_extension_list NOT LIKE '[]' group by customerid order by a ASC;
 */

public class ReadCsvID {
    public static void main(String[] args) {
    String line = "";  
    String splitBy = "\t";  
    try   
    {  
        //Customer data
        // Create an empty hash map by declaring object
        // of string and integer type
        HashMap<Integer, String> customerDMap = new HashMap<>();
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader cbr = new BufferedReader(new FileReader("C://Users//ashutosh.kumar/Downloads/customer_details.tsv")); 
        int p = 1;
        while ((line = cbr.readLine()) != null)   //returns a Boolean value  
        {  
            
            if (p >=2) {
            String[] customer = line.split(splitBy);    // use comma as separator  
            String name = customer[1].trim();
            int id = Integer.parseInt(customer[0].trim());
            customerDMap.put(id, name);
            }
            p++;
            
        }  
        System.out.println("Total Customers : " + customerDMap.size());



        // Create an empty hash map by declaring object
        // of string and integer type
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Set<Integer>> customerMap = new HashMap<>();
 
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("C://Users//ashutosh.kumar/Downloads/fs_latest.tsv")); 
        int c = 0; 
        String defaultContentRuleList = "";
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            String[] contentRule = line.split(splitBy);    // use comma as separator  
            String excludeList = contentRule[12].trim();
            excludeList = excludeList.replace("[", "");
            excludeList = excludeList.replace("]", "");
            String[] excludeListArr = excludeList.split(",");
            // System.out.println("excludeList: " + excludeList);
            // System.out.println("contentRuleName : " + contentRule[3]);
            for(int i = 0; i < excludeListArr.length; i++ ){
                if (!contentRule[3].equals("Default_content_rule")) {
                    String key = excludeListArr[i].trim();
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key)+1);
                        customerMap.get(key).add(Integer.parseInt(contentRule[1]));
                    } else {
                        map.put(key, 1);
                        //ArrayList<Integer> customerList = new ArrayList<>();
                        Set<Integer> customerList = new HashSet<Integer> (); 
                        customerMap.put(key, customerList);
                    }
                } 
            }
            c++;
            if (c == 4809) {
                System.out.println("content_rule_name: " + contentRule[3]);
                defaultContentRuleList = contentRule[12].trim();
                defaultContentRuleList = defaultContentRuleList.replace("[", "");
                defaultContentRuleList = defaultContentRuleList.replace("]", "");
            }
            // if (c == 5) {
            //     break;
            // }
        }  
        System.out.println("Total Rows : " + c);

        HashMap<String, Integer> defaultMap = new HashMap<>();
        String[] dexcludeListArr = defaultContentRuleList.split(",");
        for(int i = 0; i < dexcludeListArr.length; i++ ){
            // System.out.println("excludeList : " + excludeList);
            defaultMap.put(dexcludeListArr[i].trim(), 1);
        }
        // for (Map.Entry<String, Integer> e : defaultMap.entrySet()){
        //     System.out.println("Key: " + e.getKey()
        //                 + " Value: " + e.getValue());
        // }
        
        System.out.println("Exclude extension occurences more than 100");
        for (Map.Entry<String, Integer> e : map.entrySet()){
            if (e.getValue() > 100) {
                if(!defaultMap.containsKey(e.getKey())) {

                    ArrayList<String> customerNames = new ArrayList<>();
                    Set<Integer> customerIDs = new HashSet<>();
                    customerIDs = customerMap.get(e.getKey());
                    for(int customerID : customerIDs){
                        customerNames.add(customerDMap.get(customerID));
                    }

                    System.out.println("ext: " + e.getKey()
                        + " total occurences: " + e.getValue() + " for total unique customer IDs : "+ customerMap.get(e.getKey()).size() + " customer names : " + customerNames);
                    System.out.println();
                    System.out.println();    
                }
            }
        }
         
    }   
        catch (IOException e)   
    {  
        e.printStackTrace();  
    }  
}  
}

