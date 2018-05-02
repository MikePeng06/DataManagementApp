package core.comp3111;

import java.io.IOException;
import java.util.Set;

public class SplitTable {


	/*public static String[][] DTtoString(DataTable Datatable){


		String[][] temp = new String[Datatable.getNumRow()][Datatable.getNumCol()];
		Set<String> keys = Datatable.getDC().keySet();
		for (String key : keys) {
			for (int i = 0; i < Datatable.getNumCol(); i++) {

				DataColumn tempp = Datatable.getCol(key);

				String[] data = (String[])tempp.getData();

				for(int j = 0; j< data.length; j++) {
					temp[j][i] = data[j];
				}

			}
		}
		return temp;
	}
	 */

	/*	public static DataTable[] splitDataTable(String[][] inputdt, int partition1) {

		int firstDtRowNum = (int)(partition1 * inputdt.length / 100) ;
		int tDtColNum = inputdt[0].length;
		int SecDtRowNum = inputdt.length - firstDtRowNum;
		String[][] table1 = new String[firstDtRowNum][tDtColNum];
		String[][] table2 = new String[SecDtRowNum][tDtColNum];
		DataTable temp1 = null;
		DataTable temp2 = null;

		//copy related data in the first DataTable
		DataTable[] DtArray = new DataTable[2];
		for (int i = 0; i < firstDtRowNum; i++) {
			for (int j = 0; j < tDtColNum; j++) {
				table1[i][j] = inputdt[i][j]; 
			}
		}

		//copy related data in the Second DataTable
		for (int i = 0; i < SecDtRowNum; i++) {		
			for (int j = 0; j < tDtColNum; j++) {
				table2[i][j] = inputdt[i+ firstDtRowNum][j]; //copy the data

			}
		}

		//String table[][] convert to DataTable
		try {
			temp1 = ToDataTable(table1);
		} catch (DataTableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			temp2 = ToDataTable(table2);
		} catch (DataTableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DtArray[0] = temp1;
		DtArray[1] = temp2;
		return DtArray;
	}
	 */

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

	//from LoadData function, but change the argument
	/*	public static DataTable ToDataTable(String[][] table) throws DataTableException {
		DataTable Dataset = new DataTable();
		int linenumber = table.length;
		int columnnumber = table[0].length;

		for(int c=0;c < columnnumber; c++) {
			String type = "";
			char typecheck = table[0][c].charAt(0);
			if((typecheck<='9'&&typecheck>='0')) {
				type = DataType.TYPE_NUMBER;
			}
			else {
				type = DataType.TYPE_STRING;
			}
			Object[] string = new Object[linenumber];
			for(int row=0 ; row <linenumber ;row++) {
				string[row] = table[row][c];
			}
			DataColumn dc = new DataColumn(type, string);
			Dataset.addCol(dc., dc);
		}
		return Dataset;
	}
	 */

	public static void main(String[] args) throws DataTableException {


		DataTable t = new DataTable();

		//		// Sample: An array of integer
		//		String[] xvalues = new String[] { "1", "2", "3", "4", "5" };
		//		DataColumn xvaluesCol = new DataColumn(DataType.TYPE_NUMBER, xvalues);
		//
		//		// Sample: Can also mixed Number types
		//		String[] yvalues = new String[] { "30.0", "25", " 16", "8.0", " 22" };
		//		DataColumn yvaluesCol = new DataColumn(DataType.TYPE_NUMBER, yvalues);
		//
		//		// Sample: A array of String
		//		String[] labels = new String[] { "P1", "P2", "P3", "P4", "P5" };
		//		DataColumn labelsCol = new DataColumn(DataType.TYPE_STRING, labels);


		try {
			t = LoadData.ToDataTable("C:/Users/zpeng/OneDrive/Desktop/123.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		t.addCol("X", xvaluesCol);
		//		t.addCol("Y", yvaluesCol);
		//		t.addCol("label", labelsCol);


		DataTable[] output =  splitDataTable(t, 30);

		for(DataTable table: output) {
			Set<String> keys = table.getDC().keySet();
			for (String key : keys) {

				DataColumn tempp = table.getCol(key);
				System.out.println(tempp.toString());

			}

		}

	}
}
