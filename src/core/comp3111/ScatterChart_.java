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

public class ScatterChart_ extends Chart implements java.io.Serializable{
	
	private NumberAxis xAxis;
    private NumberAxis yAxis;        
    private ScatterChart<Number,Number> sc;
    public Button btLineChartBackMain; 
    
    public ScatterChart_() {
    	xAxis = null;
    	yAxis = null;
    	sc = null;
    	dataset = null;
    	type = 1;
    }
    
    public ScatterChart_(DataTable t){
    	xAxis = new NumberAxis();
    	yAxis = new NumberAxis();
    	sc = new ScatterChart<Number,Number>(xAxis,yAxis);
    	setDataset(t);
    }

    private Pane paneScatterChartScreen(String xAxisLabel, String yAxisLabel, String chartTitle) {
		btLineChartBackMain = this.btLineChartBackMain;

		xAxis.setLabel(xAxisLabel);
		yAxis.setLabel(yAxisLabel);
		sc.setTitle(chartTitle);

		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(sc, btLineChartBackMain);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
    }
    
	public Pane paneChart(String xAxisLabel, String yAxisLabel, String chartTitle) {
		return paneScatterChartScreen(xAxisLabel, yAxisLabel, chartTitle);
	}
	
	private void populateDataToScatterChart() {
		int numKey = dataset.getNumCol();
    	int numTextCol = 1;
    	int numNumericCol = numKey - numTextCol;
    	int rowSize = dataset.getNumRow();
    	
    	String[] textCol = new String[rowSize];
    	String[] keyRow = new String[numKey];
<<<<<<< HEAD
    	float[][] data = new float[numNumericCol][rowSize];
    	
    	sc.getData().clear();
=======
    	Integer[][] data = new Integer[numNumericCol][rowSize];
>>>>>>> refs/remotes/origin/master
    	int j = 1;
    	sc.getData().clear();
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
    	textCol[0] ="a";
    	textCol[1] ="b";
    	textCol[2] = "a";
    	
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
<<<<<<< HEAD
=======
    	System.out.println(numDistinctElement);
>>>>>>> refs/remotes/origin/master
    	
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
    	
    	XYChart.Series [] series = new XYChart.Series[numDistinctElement];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    	for (int i = 0; i < numDistinctElement ; i++) {
    		series[i].setName(textColDistinct[i]);
    		
    	}
    	for (int i = 0; i < rowSize; i++) {
    		for (int k = 0; k < numDistinctElement; k++) {
    			if (textCol[i].equals( textColDistinct[k]) ){
    				series[k].getData().add(new XYChart.Data(data[1][i], data[0][i]));
    				
    			}
    		}
    	}
    	
    	for (int i = 0; i < numDistinctElement ; i++) {
    		sc.getData().add(series[i]);
    		
    	}
    	
    	System.out.println(series.length);
    	
	}
	
	public void populateDataToChart(){
		populateDataToScatterChart();
	}
	
	public ScatterChart<Number, Number> getSC(){
		return sc;
	}
	
	private DataTable generateDummyDataTable() throws DataTableException {
		DataTable t = new DataTable();
        String[] category = new String[25];
        for (int i = 0; i < 25; i++) {
        	if (i <= 15) {
        		category[i] = "Equities";
        	}else {
        		category[i] = "Mutual funds";
        	}
        }
        DataColumn categoryCol = new DataColumn(DataType.TYPE_STRING, category);
        
        
        Float[] age = new Float[] {(float) 4.2, (float) 2.8, (float) 6.2, (float) 1, (float) 1.2, 
        						   (float) 4.4, (float) 8.5, (float) 6.9, (float) 9.9, (float) 0.9, 
        						   (float) 3.2, (float) 4.8, (float) 7.3, (float) 1.8, (float) 7.3, 
        						   (float) 2.7, (float) 5.2, (float) 2.4, (float) 3.2, (float) 1.8, 
        						   (float) 3.2, (float) 7.4, (float) 3.5, (float) 9.3, (float) 8.1};
		DataColumn ageCol = new DataColumn(DataType.TYPE_NUMBER, age);
        
		Float[] returnToDate = new Float[] {(float) 193.2, (float) 33.6, (float) 24.8, (float) 14, (float) 26.4,
										    (float) 114.4, (float) 323, (float) 289.8, (float) 287.1, (float) -9,
										    (float) 150.8, (float) 20.8, (float) -42.3, (float)  81.4, (float) 110.3,
										    (float) 41.2, (float) 229.2, (float) 37.6, (float) 49.8, (float) 134,
										    (float) 236.2, (float) 114.1, (float) 323, (float) 29.9, (float) 287.4};
		DataColumn returnToDateCol = new DataColumn(DataType.TYPE_NUMBER, returnToDate);
		
		
		
		t.addCol("Category", categoryCol);
		t.addCol("Age", ageCol);
		t.addCol("Return To Date", returnToDateCol);
		
		return t;
	}
	
	public void printDataTable() {
		Set<String> keys_before = dataset.getDC().keySet();
    	for (String key_before : keys_before) {
    		System.out.print(key_before + " ");
    		for (int i = 0; i < dataset.getNumRow(); i++) {
    			System.out.print(dataset.getCol(key_before).getData()[i] + " ");
    		}
    		System.out.println();
    	}
	}
	
}

