package core.comp3111;

import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.BarChart;

/**
 * BarChartAnimate: a class for constructing a animated bar chart, it is a sub-class of Chart and is very similar to barChart_
 * it contains all the data members (except those from JavaFX) and methods needed for this purpose.
 * @author kclamap
 *
 */
public class BarChartAnimate extends Chart implements java.io.Serializable{
	
	/**
	 * data member of BarChart_
	 */
	private DataTableArray dta;
	
	
	/**
	 * Default constructor of BarChartAnimate,
	 * it initialize all data members to meaningless value
	 */
	public BarChartAnimate() {
	//	ui = null;
		dta = null;
		dataset = null;
		type = 0;
	}
	
	/**
	 * Conversion constructor of BarChart_
	 * it accepts a DataTable and stores it in the data member t
	 * it initialize the data member dta as well
	 * @param t
	 * 		contains the data for constructing an animated bar chart
	 */
	public BarChartAnimate(DataTable t) {
		//ui = new BarChart_UI();
		dataset = t;
		dta = new DataTableArray();
	}
	
	/**
	 * it checks if the DataTable stored in the object is valid for use to generate an animated bar chart
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
    	
    	
    	if (numTextCol != 1 || numNumericCol < 1) {
    		return false;
    	}
    	return true;
	}
	


	/**
	 * it converts dataTable into a dataTableArray
	 */
	private void populateDataToBarChart() {
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
	
	/**
	 * public method for external use to call a private method
	 */
	public void populateDataToChart(){
		populateDataToBarChart();
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