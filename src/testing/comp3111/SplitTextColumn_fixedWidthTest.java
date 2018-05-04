package testing.comp3111;

import org.junit.jupiter.api.Test;

import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataTableException;
import core.comp3111.SampleDataGenerator;
import core.comp3111.SplitTable;
import core.comp3111.SplitTextColumn_fixedWidth;

public class SplitTextColumn_fixedWidthTest {

	@Test 
	void testCoverageSplitColumn() {

		SplitTextColumn_fixedWidth stc = new SplitTextColumn_fixedWidth();
		DataTable t = SampleDataGenerator.generateSampleLineData();
		int[] widths = {2,3,4 };
		DataColumn[] buffer = SplitTextColumn_fixedWidth.splitDataColumn(t.getCol("label"), widths);
		assert (buffer.length == 4); // the input widths is right

	}
	
	@Test 
	void testCoverageInvalidInput1() {

		DataTable t = SampleDataGenerator.generateSampleLineData();
		int[] widths = {-1, 2, 1};
		DataColumn[] buffer = SplitTextColumn_fixedWidth.splitDataColumn(t.getCol("label"), widths);
		assert (buffer.length == 1); //length =1 means failed split

	}

	@Test 
	void testCoverageInvalidInput2() {

		DataTable t = SampleDataGenerator.generateSampleLineData();
		int[] widths = {1,2,2,5 };
		DataColumn[] buffer = SplitTextColumn_fixedWidth.splitDataColumn(t.getCol("label"), widths);
		assert (buffer.length == 1); //length =1 means failed split

	}


	@Test 
	void testCoverageInvalidInput3() {

		DataTable t = SampleDataGenerator.generateSampleLineData();
		int[] widths = {3, 1 , 2 };
		DataColumn[] buffer = SplitTextColumn_fixedWidth.splitDataColumn(t.getCol("label"), widths);
		assert (buffer.length == 1); //length =1 means failed split

	}

}
