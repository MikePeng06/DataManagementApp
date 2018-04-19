package core.comp3111;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {
	public static void main(String[] args){
		DataTable Dataset = new DataTable();
		String csvFile = "/Users/ZeyangBao/Desktop/test1.csv";
        String line = "";
        String cvsSplitBy = ",";
        int linenumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
               	linenumber++;
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                if(linenumber == 1) {
                	
                }
                

                System.out.println("Country [code= " + country[0] + " , name=" + country[1] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
