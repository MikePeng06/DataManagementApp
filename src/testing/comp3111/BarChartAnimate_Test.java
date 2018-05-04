package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.BarChartAnimate;
import core.comp3111.BarChart_;
import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.DataType;

public class BarChartAnimate_Test {
	@Test 
	void testCoverageEmptyBarChart_Constructor() {

		BarChartAnimate bc = new BarChartAnimate(); 
		assert (bc.getDataset() == null);
		assert (bc.getType() == "BarChart");

	}
	
	@Test 
	void testCoverageNonEmptyBarChart_Constructor() {
		
		DataTable dt = new DataTable();
		BarChartAnimate bc = new BarChartAnimate(dt); 
		assert (bc.getDataset() == dt);
		
	}
	
	@Test
	void testCoverageValid_positive() throws DataTableException {
		DataTable dt = new DataTable();
        String[] Year = new String[] {"2003", "2004", "2005"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
		Number[] austria = new Integer[] { 25601, 57401,  45000};
		DataColumn austriaCol = new DataColumn(DataType.TYPE_NUMBER, austria);
		dt.addCol("Year", YearCol);
		dt.addCol("Austria", austriaCol);
		
		BarChartAnimate bc = new BarChartAnimate(dt);
		assert (bc.valid() == true);
	}
	
	@Test
	void testCoverageValid_negative() throws DataTableException {
		DataTable dt = new DataTable();
		BarChartAnimate bc = new BarChartAnimate(dt);
		assert (bc.valid() == false);
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
		
		BarChartAnimate bc = new BarChartAnimate(dt);
		bc.populateDataToChart();
		
		Integer[][] data = {{25601, 57401, 45000}};
		String[] keyRow = {"Year", "Austria"};
		
		
		
		assert (bc.getDTA().rowSize == 3);
		
		assert (bc.getDTA().textCol[0].equals("2003"));
		assert (bc.getDTA().textCol[2].equals("2005"));
//		
		assert (bc.getDTA().numKey == 2);
		
		//System.out.println(bc.getDTA().keyRow[0] + " Year");
		assert (bc.getDTA().keyRow[0].equals("Year"));
		assert (bc.getDTA().keyRow[1].equals("Austria"));
//		
//		
		assert (bc.getDTA().data[0][0].equals(25601));
		assert (bc.getDTA().data[0][2].equals(45000));
		
	}
}
