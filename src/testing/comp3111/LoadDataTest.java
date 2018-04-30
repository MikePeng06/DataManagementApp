package testing.comp3111;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import core.comp3111.LoadData;
import core.comp3111.DataColumn;
import core.comp3111.DataType;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;


public class LoadDataTest {
	 
	@Test
	void testCoverageFullTable() throws DataTableException, IOException {
		LoadData ld = new LoadData();
		DataTable dt = new DataTable();
		    dt = ld.ToDataTable("/Users/ZeyangBao/Desktop/test1.csv") ;
			dt.getNumCol(); 
			System.out.print("length: ");
			System.out.println(dt.getNumCol());
		
	} 
	
	@Test
	void testCoverageEmpty()  {  
		LoadData ld = new LoadData();
		DataTable dt = new DataTable(); 
        try {
			ld.LoadFile("/Users/ZeyangBao/Desktop/test2.csv") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	
	
}
