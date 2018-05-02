package testing.comp3111;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.comp3111.*;
 
public class TestToProject {

	@Test
	void testCoverageToProject() {
		 ArrayList<String> dataTableName = new ArrayList<String>();
		 ArrayList<String> charName = new ArrayList<String>();
		 ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
		 ArrayList<DataTable> chartList = new ArrayList<DataTable>();
		 ArrayList<ModifiedDT> MD = new ArrayList<ModifiedDT>();
		 
		 DataPack dp = new DataPack(dataTableList, chartList, dataTableName, charName, MD);
		 
		 ToProject tp = new ToProject();  
		//tp.SaveProject(dp);
		 tp.LoadProject("/Users/ZeyangBao/Desktop/employee.comp3311");
		 tp.LoadProject("/Users/ZeyangBao/Desktop/employee.comp3312");
	}
	
	 
}
