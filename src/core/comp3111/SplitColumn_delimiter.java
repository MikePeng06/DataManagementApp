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
		int colNum = getNumOfCol(selectCol, target);

		if(canSplit(selectCol, target) == true) {

			DataColumn cols[] = new DataColumn[colNum];
			String[][] mulCol = new String[colNum][selectCol.getSize()]; 

				//get Column Data
				for(int i = 0; i< selectData.length; i++) {
					String[] temp = ((String) selectData[i]).split(target);
					for(int j = 0; j < colNum; j++) {
						mulCol[j][i] = temp[j];
					}

				}

				//set new generated Col, also get the type
				for(int i = 0; i< colNum; i++)
					cols[i] = new DataColumn(selectCol.getTypeName(), mulCol[i]);
			
			
			return cols;
		}

		//handle the case if dont need to split
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
	public static boolean canSplit(DataColumn selectCol, String target) {

		
		int numOfCol = getNumOfCol(selectCol, target);

		//colNum is 1 means no splitting happen, so return false
		if(numOfCol == 1)
			return false;

		return true;
	}
	
	
	
	//retrun numOfColumn 1 if cantsplit
	public static int getNumOfCol(DataColumn selectCol, String target) {
		
		Integer[] lengths = new Integer[selectCol.getSize()];
		Object[] selectData = selectCol.getData();
		int num = 1;
		
		//check if every rows have same size
		for(int i = 0; i< selectData.length; i++) {

			String[] temp = ((String) selectData[i]).split(target); 

			// store the i-th rows lengths after splitting
			lengths[i] = temp.length; 

			if(i > 0)
				if(lengths[i] != lengths[i-1])
					return 1;

			num =  temp.length;
		}
		
		return num;
	}
	
	
	public static void main(String[] args) {

		
		// Sample: A array of String
		String[] labels = new String[] { "P1eroqrueh", "P2ewrgr3", "P3r09r12g", "P4rqewrs", "P56r3kwrbd" };
		DataColumn labelsCol = new DataColumn(DataType.TYPE_STRING, labels);
		DataColumn[] outputs = splitDataColumn(labelsCol, "r");
		
		for(DataColumn x : outputs)
			System.out.println(x);
		
		//System.out.println(labelsCol.toString());

	}
	
}
