package core.comp3111;

import java.io.IOException;
import java.util.Set;

public class SplitTable {


	/**
	 * split a datatable into two datatable and also control the percentage of split
	 * 
	 * @param inputdt - the target datatable 
	 * @param partition1- the percentage of the first datatable split
	 * @return an array of two split data table
	 * @throws DataTableException
	 */
	public static DataTable[] splitDataTable(DataTable inputdt, int partition1) throws DataTableException {

		int colNum = inputdt.getNumCol();
		int rowNum = inputdt.getNumRow();
		int firstDtRowNum = (int)(partition1 * rowNum / 100) ;
		int secDtRowNum = rowNum - firstDtRowNum;
		DataTable temp1 = new DataTable();
		DataTable temp2 = new DataTable();
		DataTable[] result = new DataTable[2];

		Set<String> keys = inputdt.getDC().keySet();

		for(String key: keys) { 

			DataColumn temp = inputdt.getCol(key);


			if(temp.getTypeName() == DataType.TYPE_STRING) {

				String[] copyString = (String[])temp.getData();
				String[] data1 = new String[firstDtRowNum];
				String[] data2 = new String[secDtRowNum];

				for(int j = 0; j < firstDtRowNum; j++)
					data1[j] = copyString[j];

				for(int z = 0; z < secDtRowNum; z++) 
					data2[z] = copyString[z+firstDtRowNum];

				DataColumn add1 = new DataColumn(temp.getTypeName(), data1);
				DataColumn add2 = new DataColumn(temp.getTypeName(), data2);

				temp1.addCol(key, add1);
				temp2.addCol(key, add2);
			}

			if(temp.getTypeName() == DataType.TYPE_NUMBER) {

				Number[] copyNumber = (Number[])temp.getData();
				Number[] data1 = new Number[firstDtRowNum];
				Number[] data2 = new Number[secDtRowNum];

				for(int j = 0; j < firstDtRowNum; j++)
					data1[j] = copyNumber[j];

				for(int z = 0; z < secDtRowNum; z++) 
					data2[z] = copyNumber[z+firstDtRowNum];

				DataColumn add1 = new DataColumn(temp.getTypeName(), data1);
				DataColumn add2 = new DataColumn(temp.getTypeName(), data2);

				temp1.addCol(key, add1);
				temp2.addCol(key, add2);
			}
		}

		result[0] = temp1;
		result[1] = temp2;

		return result;
	}
}
