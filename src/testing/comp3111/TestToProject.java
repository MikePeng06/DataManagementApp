package testing.comp3111;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.comp3111.*;
 
public class TestToProject {

	@Test
	void testCoverageToProject() throws ClassNotFoundException {
		 ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
		 ArrayList<DataTable> chartList = new ArrayList<DataTable>();
		 ArrayList<String> dataTableName = new ArrayList<String>();
		 ArrayList<String> charName = new ArrayList<String>();
		 ArrayList<DataTableArray> DTALIST = new ArrayList<DataTableArray>();
		 ArrayList<File> flist = new ArrayList<File>(); 
		 
		 DataPack dp = new DataPack(dataTableList, chartList, dataTableName,  charName, DTALIST, flist);
		 DataTable dp2 = new DataTable(); 
		     
		 ToProject tp = new ToProject();       
		//tp.SaveProject(dp); 
		 tp.SaveProject(dp, "src/testsave.comp3311");  
		 tp.SaveProject(dp, "src/ToProject2/testsave.comp3311");  
		 tp.LoadProject("src/comp3311.comp3311"); 
		// tp.LoadProject("/Users/ZeyangBao/Desktop/comp3311workspace/UnitTest/ToProject/comp3311.comp3311");
		 tp.LoadProject("src/test1.csv"); 
	}
	 
	 
}
