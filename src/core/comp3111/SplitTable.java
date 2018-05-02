package core.comp3111;

import java.io.IOException;
import java.util.Set;

public class SplitTable {


	//partition1:percentage
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


//	public static void main(String[] args) throws DataTableException {
//
//
//		DataTable t = new DataTable();
//
//
//		try {
//			t = LoadData.ToDataTable("C:/Users/zpeng/OneDrive/Desktop/123.csv");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//		t.addCol("X", xvaluesCol);
//		//		t.addCol("Y", yvaluesCol);
//		//		t.addCol("label", labelsCol);
//
//
//		DataTable[] output =  splitDataTable(t, 30);
//
//		for(DataTable table: output) {
//			Set<String> keys = table.getDC().keySet();
//			for (String key : keys) {
//
//				DataColumn tempp = table.getCol(key);
//				System.out.println(tempp.toString());
//
//			}
//
//		}
//
//	}
}
