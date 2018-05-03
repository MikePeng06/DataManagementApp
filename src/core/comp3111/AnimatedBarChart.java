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
		numFrame = t.getNumRow();
		frame = new BarChart_UI[numFrame];
		for (int i = 0; i<frame.length; i++) {
			frame[i] = new BarChart_UI();
		}
		dta = new DataTableArray();
	}
	
	private void populateDataToAnimatedBarChart() {
    	int numKey = 2;
    	int numNumericCol = 1;
    	int rowSize = dataset.getNumRow();
    	
    	String[] textCol = new String[rowSize];
    	String[] keyRow = new String[numKey];
    	Integer[] dataSingle = new Integer[rowSize];
    	
    	Set<String> keys = dataset.getDC().keySet();
    	for (String key : keys) {
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_STRING) {
    			keyRow[0] = key;
    			for (int i = 0; i < rowSize; i++) {
    				textCol[i] = (String) dataset.getCol(key).getData()[i];
    			}
    		}
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_NUMBER) {
    			keyRow[1] = key;
    			for (int i = 0; i < rowSize ; i++) {
    				dataSingle[i] = (Integer) dataset.getCol(key).getData()[i] ;
    			}
    		}
    	}
    	
    	dta = new DataTableArray(rowSize, textCol, numKey, keyRow, dataSingle);
    	
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
