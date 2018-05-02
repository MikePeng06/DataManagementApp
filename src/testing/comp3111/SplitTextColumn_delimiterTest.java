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
	void testCoverageInvalidCases1() {
		SplitTextColumn_delimiter stc = new SplitTextColumn_delimiter();
		DataTable t = SampleDataGenerator.generateSampleLineData();
		DataColumn[] buffer = SplitTextColumn_delimiter.splitDataColumn(t.getCol("label"), "e");
		assert (buffer.length == 2);
}
	
	@Test 
	void testCoverageInvalidCases2() {

		DataTable t = SampleDataGenerator.generateSampleLineData();
		DataColumn[] buffer = SplitTextColumn_delimiter.splitDataColumn(t.getCol("label"), "q");
		assert (buffer.length == 1);
}
	
	
}
