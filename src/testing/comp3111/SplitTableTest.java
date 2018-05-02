package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.SampleDataGenerator;
import core.comp3111.SplitTable;


public class SplitTableTest {

	@Test 
	void testCoverageSplitTable() {

		SplitTable st = new SplitTable();
		DataTable t = SampleDataGenerator.generateSampleLineData();
		DataTable[] buffer =null;
		try {
			buffer = SplitTable.splitDataTable(t, 50);
		} catch (DataTableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assert (buffer.length == 2);

	}

	
}
