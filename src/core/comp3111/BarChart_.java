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

public class BarChart_ extends Chart implements java.io.Serializable{
	
	//private BarChart_UI ui;
	private DataTableArray dta;
	
	public BarChart_() {
	//	ui = null;
		dta = null;
		dataset = null;
		type = 0;
	}
	
	public BarChart_(DataTable t) {
		//ui = new BarChart_UI();
		dataset = t;
		dta = new DataTableArray();
	}
	

//	public Pane paneChart(String xAxisLabel, String yAxisLabel, String chartTitle) {
//		return ui.paneBarChartScreen(xAxisLabel, yAxisLabel, chartTitle);
//	}
	
	public void populateDataToBarChart() {
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
	
	public void populateDataToChart(){
		populateDataToBarChart();
	}
	
	
	public DataTableArray getDTA() {
		return dta;
	}
	

	public DataTable generateDummyDataTable() throws DataTableException {


		DataTable t = new DataTable();

        String[] Year = new String[] {"2003", "2004", "2005"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
        
        
		Number[] austria = new Float[] { (float) 25601, (float) 57401, (float) 45000};
		DataColumn austriaCol = new DataColumn(DataType.TYPE_NUMBER, austria);
		
		Number[] brazil = new Float[] { (float) 20148, (float) 41941, (float) 44835};
		DataColumn brazilCol = new DataColumn(DataType.TYPE_NUMBER, brazil);
		
		Number[] france = new Float[] { (float) 10000, (float) 45263, (float) 18722};
		DataColumn franceCol = new DataColumn(DataType.TYPE_NUMBER, france);
		
		Number[] italy = new Float[] { (float) 35407, (float) 117320, (float) 17557};
		DataColumn italyCol = new DataColumn(DataType.TYPE_NUMBER, italy);
		
		Number[] usa = new Float[] { (float) 12000, (float) 14845, (float) 92633};
		DataColumn usaCol = new DataColumn(DataType.TYPE_NUMBER, usa);
				

		t.addCol("Year", YearCol);
		t.addCol("Austria", austriaCol);
		t.addCol("Brazil", brazilCol);
		t.addCol("France", franceCol);
		t.addCol("Italy", italyCol);
		t.addCol("USA", usaCol);
		
		return t;
	}
}
