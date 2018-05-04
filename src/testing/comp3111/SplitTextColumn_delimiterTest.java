package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.SampleDataGenerator;
import core.comp3111.SplitTable;
import core.comp3111.SplitTextColumn_delimiter;

public class SplitTextColumn_delimiterTest {
	
	@Test 
	void testCanSplitCase() {
		SplitTextColumn_delimiter stc = new SplitTextColumn_delimiter();
		DataTable t = SampleDataGenerator.generateSampleLineData();
		DataColumn[] buffer = SplitTextColumn_delimiter.splitDataColumn(t.getCol("label"), "e");
		assert (buffer.length == 2); // can split the column with the right delimiter
}
	
	@Test 
	void testCoverageInvalidCases2() {

		DataTable t = SampleDataGenerator.generateSampleLineData();
		DataColumn[] buffer = SplitTextColumn_delimiter.splitDataColumn(t.getCol("label"), "q");
		assert (buffer.length == 1); //length == 1 means it didn't split with the incorrect delimiter
}
	
	
}
