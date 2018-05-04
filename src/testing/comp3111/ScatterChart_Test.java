package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.ScatterChart_;
import core.comp3111.BarChart_;
import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.DataType;

public class ScatterChart_Test {
	@Test 
	void testCoverageEmptyScatterChart_Constructor() {

		ScatterChart_ sc = new ScatterChart_(); 
		assert (sc.getDataset() == null);
		assert (sc.getType() == "ScatterChart");

	}
	
	@Test 
	void testCoverageNonEmptyScatterChart_Constructor() {
		
		DataTable dt = new DataTable();
		ScatterChart_ sc = new ScatterChart_(dt); 
		assert (sc.getDataset() == dt);
		
	}
	
	@Test
	void testCoverageValid_positive() throws DataTableException {
		DataTable dt = new DataTable();
        String[] Year = new String[] {"2003", "2004", "2005"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
		Number[] austriax = new Integer[] { 25601, 57401,  45000};
		DataColumn austriaxCol = new DataColumn(DataType.TYPE_NUMBER, austriax);
		Number[] austriay = new Integer[] { 25601, 57401,  45000};
		DataColumn austriayCol = new DataColumn(DataType.TYPE_NUMBER, austriay);
		dt.addCol("Year", YearCol);
		dt.addCol("Austriax", austriaxCol);
		dt.addCol("Austriay", austriayCol);
		
		
		ScatterChart_ sc = new ScatterChart_(dt);
		assert (sc.valid() == true);
	}
	
	@Test
	void testCoverageValid_negative() throws DataTableException {
		DataTable dt = new DataTable();
		ScatterChart_ sc = new ScatterChart_(dt);
		assert (sc.valid() == false);
	}
	
	@Test
	void testCoveragePopulateDataToChart() throws DataTableException {
		DataTable dt = new DataTable();
        String[] Year = new String[] {"2003", "2004", "2003"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
		Number[] austriax = new Integer[] { 25601, 57401,  45000};
		DataColumn austriaxCol = new DataColumn(DataType.TYPE_NUMBER, austriax);
		Number[] austriay = new Integer[] { 5601, 7401,  5000};
		DataColumn austriayCol = new DataColumn(DataType.TYPE_NUMBER, austriay);
		dt.addCol("Year", YearCol);
		dt.addCol("Austriax", austriaxCol);
		dt.addCol("Austriay", austriayCol);
		
		ScatterChart_ sc = new ScatterChart_(dt);
		sc.populateDataToChart();
		
//		

		assert (sc.getDTA().rowSize == 3);
		
		assert (sc.getDTA().textCol[0].equals("2003"));
		assert (sc.getDTA().textCol[2].equals("2003"));
		
		assert (sc.getDTA().numKey == 3);
		
		assert (sc.getDTA().keyRow[0].equals("Year"));
		assert (sc.getDTA().keyRow[1].equals("Austriax"));
		
		assert (sc.getDTA().data[0][0] == 25601);
		assert (sc.getDTA().data[1][2] == 5000);
		
	}
	
}