package core.comp3111;

public class SplitColumn_fixedWidth {


	public static DataColumn[] splitDataColumn(DataColumn selectCol, int[] widths) {

		Object[] selectData = selectCol.getData();


		//	if(canSplit(selectCol, widths, colNum) == true)
		//	{
		int colNum = widths.length;
		DataColumn cols[] = new DataColumn[colNum];
		String[][] mulCol = new String[colNum][selectCol.getSize()]; //mulCol[numOfColumn][numOfRow]
		//	}
		
		
		for(int i = 0 ; i< selectData.length; i++) {
			String[] temp = splitFixedWidth((String)selectData[i], widths);
			for(int j = 0; j < colNum; j++)
				mulCol[j][i] = temp[j];
		}

		
		for(int i = 0 ; i< colNum; i++)
			cols[i].set(selectCol.getTypeName(), mulCol[i]);
		return cols;
	}

	/*
	public static boolean canSplit(DataColumn selectCol, int widths[]) {

		if(widths[0] <= 0)
			return false;

		for(int i = 1; i<widths.length; i++)
			if (widths[i] < widths[i-1])
				return false;

	    Integer[] lengths = new Integer[selectCol.getSize()];
		Object[] selectData = selectCol.getData();

		for(int i = 0; i < selectData.length ; i++) {
			String temp = (String) selectData[i];
			lengths[i] = temp.length();	
		}

		int minimumWidths = lengths[0];
		for(int i = 0; i< selectData.length; i++) {
			if(lengths[i] < minimumWidths)
				minimumWidths = lengths[i];

		}

		for(int i = 0; i< selectData.length ; i++)
			if( widths[i] >= minimumWidths)
				return false;


		return true;
	}  */


	public static String[] splitFixedWidth(String original, int[] widths)
	{
		String[] results = new String[widths.length];

		results[0] = original.substring(0,widths[0]);
		for (int i = 1; i < widths.length - 1; ++i)
		{
			results[i] = original.substring(widths[i-1], (widths[i]));
		}
		results[widths.length-1] = original.substring(widths[widths.length -1]-1);

		return results;
	}



	public static void main(String[] args) {

		int[] widths = {3,5,8,9};
		String original = "qwertyuioa";
		String[] outputs = splitFixedWidth(original, widths);
		for(int i = 0; i < outputs.length; i++) {
			System.out.print(outputs[i]+"   ");
		}

	}

}
