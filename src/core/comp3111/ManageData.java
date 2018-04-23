package core.comp3111;
import java.util.Random;

public class ManageData {
	
	protected String[][] inputTable;

	public DataTable[] splitDataTable(String[][] inputdt, int partition1) {

		int firstDtRowNum = (int)(partition1 * inputdt.length / 10) ;
		int tDtColNum = inputdt[0].length;
		int SecDtRowNum = inputdt.length - firstDtRowNum;
		String[][] table1 = new String[firstDtRowNum][tDtColNum];
		String[][] table2 = new String[SecDtRowNum][tDtColNum];
		DataTable temp1;
		DataTable temp2;
		DataTable[] DtArray = null;
		for (int i = 0; i < firstDtRowNum; i++) {
			for (int j = 0; j < tDtColNum; j++) {
				table1[i][j] = inputdt[i][j];
			}
		}
		for (int i = 0; i < SecDtRowNum; i++) {
			for (int j = 0; j < tDtColNum; j++) {
				table1[i][j] = inputdt[i+ firstDtRowNum][j];
			}
		}
		
		//need to implement function : String table[][] convert to DataTable

		return DtArray;
	}
	
	
}


