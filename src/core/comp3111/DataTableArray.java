package core.comp3111;

public class DataTableArray implements java.io.Serializable{
	public int rowSize;
	public String[] textCol;
	public int numKey;
	public String[] keyRow;
	public Integer[][] data;
	
	public int numDistinctElement;
	public String[] textColDistinct;
	
	public Integer[] dataSingle;
	
	public DataTableArray() {
		rowSize = 0;
		textCol = null;
		numKey = 0;
		keyRow = null; 
		data = null;
		
		numDistinctElement = 0;
		textColDistinct = null;
		
		dataSingle = null;
	}
	
	public DataTableArray(int rowSize, String[] textCol, int numKey, String[] keyRow, Integer[][] data) {
		this.rowSize = rowSize;
		
		
		this.textCol = new String[textCol.length];
		for (int i = 0; i < textCol.length; i++) {
			this.textCol[i] = textCol[i];
		}
		
		
		this.numKey = numKey;
		
		
		this.keyRow = new String[keyRow.length];
		for (int i = 0; i < keyRow.length; i++) {
			this.keyRow[i] = keyRow[i];
		}
		
		
		this.data = new Integer[numKey - 1][rowSize];
		for (int i = 0; i < (numKey - 1); i++) {
			for (int j = 0; j < rowSize; j++) {
				this.data[i][j] = data[i][j];
			}
		}
		
		numDistinctElement = 0;
		textColDistinct = null;
	}
	
	public DataTableArray(int rowSize, String[] textCol, int numKey, String[] keyRow, Integer[][] data, int numDistinctElement, String[] textColDistinct) {
		this.rowSize = rowSize;
		
		
		this.textCol = new String[textCol.length];
		for (int i = 0; i < textCol.length; i++) {
			this.textCol[i] = textCol[i];
		}
		
		
		this.numKey = numKey;
		
		
		this.keyRow = new String[keyRow.length];
		for (int i = 0; i < keyRow.length; i++) {
			this.keyRow[i] = keyRow[i];
		}
		
		
		this.data = new Integer[numKey - 1][rowSize];
		for (int i = 0; i < (numKey - 1); i++) {
			for (int j = 0; j < rowSize; j++) {
				this.data[i][j] = data[i][j];
			}
		}
		
		
		this.numDistinctElement = numDistinctElement;
		
		
		this.textColDistinct = new String[textColDistinct.length];
		for (int i = 0; i < textColDistinct.length; i++) {
			this.textColDistinct[i] = textColDistinct[i];
		}
		
		dataSingle = null;
	}
	
	public DataTableArray(int rowSize, String[] textCol, int numKey, String[] keyRow, Integer[] dataSingle) {
		this.rowSize = rowSize;
		
		
		this.textCol = new String[textCol.length];
		for (int i = 0; i < textCol.length; i++) {
			this.textCol[i] = textCol[i];
		}
		
		
		this.numKey = numKey;
		
		
		this.keyRow = new String[keyRow.length];
		for (int i = 0; i < keyRow.length; i++) {
			this.keyRow[i] = keyRow[i];
		}
		
		
		this.dataSingle = new Integer[rowSize];
			for (int j = 0; j < rowSize; j++) {
				this.dataSingle[j] = dataSingle[j];
		}
		
		numDistinctElement = 0;
		textColDistinct = null;
		
		this.data = null;
	}
}