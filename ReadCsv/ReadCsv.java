package ReadCsv;

import java.io.File;
import java.util.Scanner;  
public class ReadCsv  
{  
	public static void main(String[] args) throws Exception  
	{  
		//parsing a CSV file into Scanner class constructor  
		Scanner sc = new Scanner(new File("C://Users//ashutosh.kumar/Downloads/fs_tab.tsv"));  
		sc.useDelimiter("\t");   //sets the delimiter pattern  
		int c = 0;
		while (sc.hasNext())  //returns a boolean value  
		{  
			System.out.print(sc.next());  //find and returns the next complete token from this scanner  
			c++;
		}   
		sc.close();  //closes the scanner  
		System.out.print("Total Rows" + c);
		}  
		
}  