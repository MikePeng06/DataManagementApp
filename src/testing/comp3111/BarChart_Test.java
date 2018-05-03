package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.BarChart_;
import core.comp3111.DataColumn;
import core.comp3111.DataTable;

public class BarChart_Test {
	@Test 
	void testCoverageEmptyBarChart_Constructor() {

		BarChart_ bc = new BarChart_(); 
		assert (bc.getUI() == null);
		assert (bc.getDataset() == null);
		assert (bc.getType() == "BarChart");

	}
	
	@Test 
	void testCoverageNonEmptyBarChart_Constructor() {
		DataTable dt = new DataTable();
		
		
		BarChart_ bc = new BarChart_(dt); 
//		assert (bc.getUI() == null);
//		assert (bc.getDataset() == null);
//		assert (bc.getType() == "BarChart");

	}
}
