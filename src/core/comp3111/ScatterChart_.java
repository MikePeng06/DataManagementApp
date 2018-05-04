package core.comp3111;

import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * BarChart_: a class for constructing a scatter chart, it is a sub-class of Chart
 * it contains all the data members (except those from JavaFX) and methods needed for this purpose.
 * @author kclamap
 *
 */
public class ScatterChart_ extends Chart implements java.io.Serializable{
	
	/**
	 * data member of scatter chart
	 */
	private DataTableArray dta;
	
	/**
	 * default constructor of ScatterChart_
	 * it initialize data members of ScatterChart_ to meaningless value
	 */
    public ScatterChart_() {
    	dta = null;
    	dataset = null;
    	type = 1;
    }
    
    /**
     * conversion consructor of ScatterChart_
     * it accepts a DataTable and stores it in the data member t
	 * it initialize the data member dta as well
     * @param t
     * 		contains the data for constructing a scatter chart
     */
    public ScatterChart_(DataTable t){
    	dta = new DataTableArray();
    	dataset = t;
    }
    
    /**
	 * it checks if the DataTable stored in the object is valid for use to generate a scatter chart
	 * @return
	 * 		true: if the DataTable is useful
	 * 		false: if the DataTable cannot be used
	 */
    public boolean valid() {
    	int numTextCol = 0;
    	int numNumericCol = 0;
    	
    	Set<String> keys = dataset.getDC().keySet();
    	for (String key : keys) {
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_STRING) {
    			numTextCol++;
    		}
    		if (dataset.getCol(key).getTypeName() == DataType.TYPE_NUMBER) {
    			numNumericCol++;
    		}
    	}
    	
    	if (numTextCol != 1 || numNumericCol != 2) {
    		return false;
    	}
    	return true;
    }

    /**
	 * it converts dataTable into a dataTableArray
	 */
	private void populateDataToScatterChart() {
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
    	
    	int numDistinctElement = 1;
    	boolean same = false;
    	for (int i = 1; i< rowSize; i++) {
    		same = false;
    		for (int n = 0; n < i; n++) {
    			if (textCol[i].equals( textCol[n])) {
    				same = true;
    				break;
    			}
    		}
    		if (same == false) {
    			numDistinctElement++;
    		}
    	}

    	
    	String[] textColDistinct = new String[numDistinctElement];
    	int numNonEmptytextColDistinct = 0;
    	for (int i = 0; i < rowSize; i++) {
    		same = false;
    		for (int k = 0; k < numNonEmptytextColDistinct; k++) {
    			if (textCol[i].equals( textColDistinct[k])) {
    				same = true;
    			}
    		}
    		if (!same) {
    			textColDistinct[numNonEmptytextColDistinct] = textCol[i];
    			numNonEmptytextColDistinct++;
    		}
    	}
    	
    	dta = new DataTableArray(rowSize, textCol, numKey, keyRow, data, numDistinctElement, textColDistinct);
    	
    	
    	
	}
	
	/**
	 * public method for external use to call a private method
	 */
	public void populateDataToChart(){
		populateDataToScatterChart();
	}
	
	/**
	 * a getter
	 * @return
	 * 		DataTableArray stored in the object
	 */
	public DataTableArray getDTA() {
		return dta;
	}
	

}
