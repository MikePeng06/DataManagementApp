package core.comp3111;

public class DataTableArray implements java.io.Serializable{
	public int rowSize;
	public String[] textCol;
	public int numKey;
	public String[] keyRow;
	public Integer[][] data;
	
	public DataTableArray() {
		rowSize = 0;
		textCol = null;
		numKey = 0;
		keyRow = null;
		data = null;
	}
	
	public DataTableArray(int rowSize, String[] textCol, int numKey, String[] keyRow, Integer[][] data) {
		this.rowSize = rowSize;
		this.textCol = textCol;
		this.numKey = numKey;
		this.keyRow = keyRow;
		this.data = data;
	}
	
}
