package ReadCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadCsvNAS 
{  
public static void main(String[] args)   
{  
    String line = "";  
    String splitBy = "\t";  
    try   
    {  
        // Create an empty hash map by declaring object
        // of string and integer type
        HashMap<String, Integer> map = new HashMap<>();
 
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("C://Users//ashutosh.kumar/Downloads/nas_content.tsv")); 
        int c = 0; 
        String defaultContentRuleList = "";
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            String[] contentRule = line.split(splitBy);    // use comma as separator  
            String excludeList = contentRule[10].trim();
            excludeList = excludeList.replace("[", "");
            excludeList = excludeList.replace("]", "");
            String[] excludeListArr = excludeList.split(",");
            // System.out.println("excludeList: " + excludeList);
            // System.out.println("contentRuleName : " + contentRule[3]);
            for(int i = 0; i < excludeListArr.length; i++ ){
                if (!contentRule[11].equals("Default_content_rule")) {
                    String key = excludeListArr[i].trim();
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key)+1);
                    } else {
                        map.put(key, 1);
                    }
                } 
            }
            c++;
            if (c == 441) {
                System.out.println("content_rule_name: " + contentRule[3]);
                defaultContentRuleList = contentRule[10].trim();
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
        
        System.out.println("Exclude extention occurances more than 100");
        for (Map.Entry<String, Integer> e : map.entrySet()){
            if (e.getValue() > 100) {
                if(!defaultMap.containsKey(e.getKey())) {
                    System.out.println("Key: " + e.getKey()
                        + " Value: " + e.getValue());
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