package core.comp3111;

import java.util.Set;


public class AnimatedBarChart extends Chart {
	BarChart_UI[] frame;
	int numFrame;
	DataTableArray dta;
	
	public AnimatedBarChart() {
		frame = null;
		numFrame = 0;
		dta = null;
		type = 2;
	}
	
	public AnimatedBarChart(DataTable t) {
		dataset = t;
		numFrame = t.getNumRow() * (t.getDC().size() - 1);
		frame = new BarChart_UI[numFrame];
		for (int i = 0; i<frame.length; i++) {
			frame[i] = new BarChart_UI();
		}
		dta = new DataTableArray();
	}
	
	private void populateDataToAnimatedBarChart() {
    	int numKey = dataset.getNumCol();
    	int numTextCol = 1;
    	int numNumericCol = numKey - numTextCol;
    	int rowSize = dataset.getNumRow();
    	
    	String[] textCol = new String[rowSize];
    	String[] keyRow = new String[numKey];
    	Integer[][] data = new Integer[numNumericCol][rowSize];
    	
    	int j = 1;
    	Set<String> keys = dataset.getDC().keySet();
    	for (String key : keys) {
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_STRING) {
    			keyRow[0] = key;
    			for (int i = 0; i < rowSize; i++) {
    				textCol[i] = (String) dataset.getCol(key).getData()[i];
    			}
    		}
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_NUMBER) {
    			keyRow[j] = key;
    			for (int i = 0; i < rowSize ; i++) {
    				data[j-1][i] = (Integer) dataset.getCol(key).getData()[i] ;
    			}
    			j++;
    		}
    	}
    	
    	dta = new DataTableArray(rowSize, textCol, numKey, keyRow, data);
    	
	}	
	
	public void populateDataToChart() {
		populateDataToAnimatedBarChart();
	}
	
	public DataTableArray getDTA() {
		return dta;
	}
	
	public int getNumFrame() {
		return numFrame;
	}
	
	public BarChart_UI[] getFrame() {
		return frame;
	}
}
