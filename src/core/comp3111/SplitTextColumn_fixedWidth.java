package core.comp3111;

public class SplitTextColumn_fixedWidth {

/**
 * Split a text column into multiple column in fixed width mode
 * @param selectCol -the origin target column 
 * @param widths - an array of integer that denotes each fixed points
 * @return an array of datacolumn that contain the outcome of the splitting column(s)
 */
	public static DataColumn[] splitDataColumn(DataColumn selectCol, int[] widths) {

		Object[] selectData = selectCol.getData();


		if(canSplit(selectCol, widths) == true)
		{
			int colNum = widths.length + 1;
			DataColumn cols[] = new DataColumn[colNum];
			String[][] mulCol = new String[colNum][selectData.length]; //mulCol[numOfColumn][numOfRow]
		


			for(int i = 0 ; i< selectData.length; i++) {
				String[] temp = splitFixedWidth((String)selectData[i], widths);
				for(int j = 0; j < colNum; j++)
					mulCol[j][i] = temp[j];
			}


			for(int i = 0 ; i< colNum; i++)
				cols[i] = new DataColumn(selectCol.getTypeName(), mulCol[i]);
			return cols;
		}
		
		//handle the case if dont need to split
		DataColumn cols[] = new DataColumn[1];
		cols[0] = selectCol;
		return cols;
	}

/**
 * check if we can split the column
 * @param -the origin column 
 * @param widths - an array of integer that denotes each fixed points
 * @return boolean value show if we can split the column
 */
	public static boolean canSplit(DataColumn selectCol, int widths[]) {

		if((widths[0] <= 0) )
			return false;

		for(int i = 1; i<widths.length; i++)
			if (widths[i] <= widths[i-1])
				return false;

	
		Object[] selectData = selectCol.getData();
		int[] lengths = new int[selectData.length];

		for(int i = 0; i < selectData.length ; i++) {
			String temp = (String) selectData[i];
			lengths[i] = temp.length();	
		}

		int minimumWidths = lengths[0];
		for(int i = 0; i< selectData.length; i++) {
			if(lengths[i] < minimumWidths)
				minimumWidths = lengths[i];
		}

		for(int i = 0; i< widths.length ; i++)
			if( widths[i] >= minimumWidths)
				return false;


		return true;
	}  

/**
 * split a text follow the fixed width points into multi-strings
 * @param original- the text of one row in the column
 * @param widths - an array of integer that denotes each fixed points
 * @return the split results of the text
 */
	public static String[] splitFixedWidth(String original, int[] widths)
	{
		String[] results = new String[widths.length+1];

		results[0] = original.substring(0,widths[0]);
		for (int i = 1; i < widths.length ; i++)
		{
			results[i] = original.substring(widths[i-1], (widths[i]));
		}
		
		results[widths.length] = original.substring(widths[widths.length-1]-1);

		return results;
	}



//	public static void main(String[] args) {
//
//		
//		// Sample: A array of String
//		String[] labels = new String[] { "P1eoquehr", "P2ewrg3", "P30912g", "P4qewrs", "P563kwbd" };
//		DataColumn labelsCol = new DataColumn(DataType.TYPE_STRING, labels);
//		int[] widths = {3,4,5};
//		DataColumn[] outputs = splitDataColumn(labelsCol, widths);
//		//String[] test = splitFixedWidth(labels[0], widths);
//		for(DataColumn x : outputs)
//			System.out.println(x);
//		System.out.println(labelsCol.toString());
//
//	}

}
