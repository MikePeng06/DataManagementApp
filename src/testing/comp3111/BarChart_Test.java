package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.BarChart_;
import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.DataType;

public class BarChart_Test {
	@Test 
	void testCoverageEmptyBarChart_Constructor() {

		BarChart_ bc = new BarChart_(); 
		assert (bc.getDataset() == null);
		assert (bc.getType() == "BarChart");

	}
	
	@Test 
	void testCoverageNonEmptyBarChart_Constructor() {
		
		DataTable dt = new DataTable();
		BarChart_ bc = new BarChart_(dt); 
		assert (bc.getDataset() == dt);
		
	}
	
	@Test
	void testCoveragePopulateDataToChart() throws DataTableException {
		DataTable dt = new DataTable();
        String[] Year = new String[] {"2003", "2004", "2005"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
		Number[] austria = new Integer[] { 25601, 57401,  45000};
		DataColumn austriaCol = new DataColumn(DataType.TYPE_NUMBER, austria);
		dt.addCol("Year", YearCol);
		dt.addCol("Austria", austriaCol);
		
		BarChart_ bc = new BarChart_(dt);
		bc.populateDataToChart();
		
		Integer[][] data = {{25601, 57401, 45000}};
		String[] keyRow = {"Year", "Austria"};
		
		
		assert (bc.getDTA().rowSize == 3);
		
		assert (bc.getDTA().textCol[0] == "2003");
		assert (bc.getDTA().textCol[2] == "2005");
		
		assert (bc.getDTA().numKey == 6);
		
		//System.out.println(bc.getDTA().keyRow[0] + " Year");
//		assert (bc.getDTA().keyRow[0].equals("Year"));
//		assert (bc.getDTA().keyRow[1].equals("Austria"));
//		
//		
//		assert (bc.getDTA().data[0][0] == 25601);
//		assert (bc.getDTA().data[0][2] == 45000);
		
	}
	
}
