package core.comp3111;

public class SplitColumn_delimiter {


	/**
	 * return the DataColumn[] if it can split
	 * 
	 * @param selectCol
	 *            - The column that is selected to be split
	 * 
	 * @param target
	 *            - The delimiter/width input in the splitting mode
	 *            
	 * @param splitColMode
	 *            - indicate the split mode
	 *            
	 * @return - the split result, which is an array of DataColumn 
	 */
	public static DataColumn[] splitDataColumn(DataColumn selectCol, String target) {

		Object[] selectData = selectCol.getData();
		int colNum =0;

		if(canSplit(selectCol, target, colNum) == true) {

			DataColumn cols[] = new DataColumn[colNum];
			String[][] mulCol = new String[colNum][selectCol.getSize()]; 

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
			
			
			return cols;
		}

		//cant split case
		DataColumn[] cols = new DataColumn[1];
		cols[0] = selectCol;
		return cols;
	}


	/**return whether canSplit the selected Column
	 * 
     * @param selectCol - The column that is selected to be split
     * 
	 * @param target - The delimiter/width input in the splitting mode
	 * 
	 * @param colNum - The output numOfColumn if it can split
	 *   
	 * @return - whether it can split into multiple columns
	 */
	public static boolean canSplit(DataColumn selectCol, String target, int colNum) {

		Integer[] lengths = new Integer[selectCol.getSize()];
		Object[] selectData = selectCol.getData();


		//check if every rows have same size
		for(int i =1; i< selectData.length; i++) {

			String[] temp = ((String) selectData[i]).split(target); 

			// store the ith rows lengths after splitting
			lengths[i] = temp.length; 

			if(i > 1)
				if(lengths[i] != lengths[i-1])
					return false;

			colNum = temp.length;
		}


		//colNum is 1 means no splitting happen, so return false
		if(colNum == 1)
			return false;

		return true;
	}
	
}
