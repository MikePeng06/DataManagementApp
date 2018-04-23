package core.comp3111;


public class ManageData {

	protected String[][] inputTable;

	public DataTable[] splitDataTable(String[][] inputdt, int partition1) {

		int firstDtRowNum = (int)(partition1 * inputdt.length / 10) ;
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
		for (int i = 0; i <= SecDtRowNum; i++) {		
			for (int j = 0; j < tDtColNum; j++) {
				if(i == 0)
					table1[i][j] = inputdt[0][j];  //copy the column name
				table1[i][j] = inputdt[i+ firstDtRowNum][j]; //copy the rest data
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

	public static DataTable ToDataTable(String[][] table) throws DataTableException {
		DataTable Dataset = new DataTable();
		int linenumber = table.length;
		int columnnumber = table[0].length;

		for(int c=0;c<= columnnumber-1;c++) {
			String type = "";
			char typecheck = table[1][c].charAt(0);
			if((typecheck<='9'&&typecheck>='0')) {
				type = "numeric";
			}
			else {
				type = "text";
			}
			Object[] string = new Object[linenumber];
			for(int row=1;row<=linenumber-1;row++) {
				string[row-1] = table[row][c];
			}
			DataColumn dc = new DataColumn(type, string);
			Dataset.addCol(table[0][c], dc);
		}
		return Dataset;
	}

}
