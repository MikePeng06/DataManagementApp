package core.comp3111;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {
	
	
	public static String[][] LoadFile(String str) throws IOException{
		String[] columnname; 
		String csvFile = str;
        String line = "";
        String cvsSplitBy = ","; 
        int linenumber = 0;
        int columnnumber = 0; 
        
    BufferedReader brr = new BufferedReader(new FileReader(csvFile));
            while ((line = brr.readLine()) != null) {
               linenumber ++;
               String[] country = line.split(cvsSplitBy);
               if(linenumber==1) {
               columnnumber = country.length;
               }
            }

       
        
        String[][] table = new String[linenumber][columnnumber]; 
               
        int i = 0;
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
               	i++;
                // use comma as separator
                String[] country = new String[columnnumber];
                String[] countrytemp = line.split(cvsSplitBy);
                for(int b = 0; b<=columnnumber-1;b++) {
                	country[b] = ""; 
                }
                int a = 0;
                while(a<=countrytemp.length-1) {
                	country[a] = countrytemp[a];
                	a++;
                }
                
                for(int j=0;j<= columnnumber-1;j++) {
                	table[i-1][j] = country[j];
                }

    //            System.out.println("Country [code= " + country[0] + " , name=" + country[1] + "]");

            }

       
         
        for(int w = 0 ; w<= columnnumber-1; w++) { 
        	int row = 1;
        	String check = "";
        	while(row<=linenumber-1) {
        		if(!table[row][w].isEmpty()) { 
        			check = table[row][w];
        			break; 
        		}
        		row++;
        	}
        	//(check.charAt(0)<='Z'&&check.charAt(0)>='A')||(check.charAt(0)<='z'&&check.charAt(0)>='a')
        	if(Character.isLetter(check.charAt(0))) { 
        		check = " ";
        	} 
        	else {
        		check = "0"; 
        	}
        	for(int j = 0;j<=linenumber-1;j++) {
        		if(table[j][w].isEmpty()) {
        			table[j][w]=check;
        		}
        	}
        	
        }
        
        return table;
        
        
	}
	
	public static DataTable ToDataTable(String str) throws DataTableException, IOException {
		DataTable Dataset = new DataTable();
		String[][] table = LoadFile(str);
		int linenumber = table.length;
		int columnnumber = table[0].length;
		
		for(int c=0;c<= columnnumber-1;c++) {
			String type = "";
			char typecheck = table[1][c].charAt(0);
			if(Character.isDigit(typecheck)) {  
				type = "java.lang.Number";
			} 
			else {
				type = "java.lang.String";
			}
			Object[] string = new Object[linenumber];
			for(int row=1;row<=linenumber-1;row++) {
				string[row-1] = table[row][c];
			}
			DataColumn dc = new DataColumn(type, string);
			Dataset.addCol(table[0][c], dc);
		}
		return Dataset;
	}
	
	
//	public static void main(String[] args) throws IOException{
//		
//		String[][] table = LoadFile("/Users/ZeyangBao/Desktop/test1.csv");
//		
//		int linenumber = table.length;
//		int columnnumber = table[0].length;
//		
//		for (int k =0;k<=linenumber-1;k++) {
//        	for (int j=0; j<= columnnumber-1;j++) {
//        		System.out.print(table[k][j]);
//        	}
//        	System.out.println("");
//        }
//        
//		try {
//			DataTable dt = ToDataTable("/Users/ZeyangBao/Desktop/test1.csv") ;
//			dt.getNumCol();
//			System.out.print("length: ");
//			System.out.println(dt.getNumCol());
//		} catch (DataTableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	
}
