package core.comp3111;
import java.io.*;
import java.util.StringTokenizer;

import core.comp3111.*;

public class FileToDataTable {
	//Varibale
	File dataSet;
public FileToDataTable(File dataSet) {
	this.dataSet = dataSet;
}

private static void  transfer() {
	//DataTable tb = new DataTable();
	 try { 
         BufferedReader br = new BufferedReader(new FileReader("/Users/ZeyangBao/Desktop/test1.csv"));

         String line = ""; 
         while ((line = br.readLine()) != null) { 
             StringTokenizer st = new StringTokenizer(line, ",");

             while (st.hasMoreTokens()) { 
                 
                 System.out.print(st.nextToken() + "/t"); 
             } 
             System.out.println(); 
         } 
         br.close();

     } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
     } catch (IOException e) { 
         e.printStackTrace(); 
     } 	 
}

public static void main(String[] args) {
	transfer();
}

}
