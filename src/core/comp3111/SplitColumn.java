package core.comp3111;

public class SplitColumn {


	//return the DataColumn[] if it can split
	public static DataColumn[] splitDataColumn(DataColumn selectCol, String target, String splitColMode) {

		Object[] selectData = selectCol.getData();
		int colNum =0;

		if(canSplit(selectCol, target, colNum) == true) {

			DataColumn cols[] = new DataColumn[colNum];
			String[][] mulCol = new String[colNum][selectCol.getSize()]; 

			//delimiter mode selected
			if(splitColMode == "delimiter") {

				//get Column name
				for(int i = 0; i< colNum; i++)
					mulCol[i][0] = ((String) selectData[0]);

				//get rest Column Data
				for(int i =1; i< selectData.length; i++) {
					String[] temp = ((String) selectData[i]).split(target);
					for(int j = 0; j < colNum; j++) {
						mulCol[j][i] = temp[j];
					}

				}

				//set new generated Col, also get the type
				for(int i = 0; i< colNum; i++)
					cols[i].set(selectCol.getTypeName(), mulCol[i]);
			}

			
			//fixwidth selected
			else if (splitColMode == "fixwidth") {
				
			}


			return cols;
		}

		//cant split
		DataColumn[] cols = new DataColumn[1];
		cols[0] = selectCol;
		return cols;
	}


	//return whether canSplit the selected Column
	public static boolean canSplit(DataColumn selectCol, String target, int colNum) {

		Integer[] lengths = new Integer[selectCol.getSize()];
		Object[] selectData = selectCol.getData();

		for(int i =1; i< selectData.length; i++) {
			String[] temp = ((String) selectData[i]).split(target); 
			lengths[i] = temp.length;

			
			if(i > 1)
				if(lengths[i] != lengths[i-1])
					return false;

			
			colNum = temp.length;
		}

		if(colNum == 1)
			return false;
			return true;
	}
}
