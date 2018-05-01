package core.comp3111;

import java.io.IOException;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import core.comp3111.*;

public class SelectColumn {

	private String charType;
	public SelectColumn(String str) {
		charType = str;
	}
	
	public static BarChart generate(DataTable dataset) {
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        
        
     	int numKey = dataset.getNumCol();
    	int numTextCol = 1;
    	int numNumericCol = numKey - numTextCol;
    	int rowSize = dataset.getNumRow();
    	
    	String[] textCol = new String[rowSize];
    	String[] keyRow = new String[numKey];
    	Float[][] data = new Float[numNumericCol][rowSize];
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
    				data[j-1][i] = (Float) dataset.getCol(key).getData()[i] ;
    			}
    			j++;
    		}
    	}
    	
    	XYChart.Series [] series = new XYChart.Series[rowSize];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    
//    	System.out.println(series.length);
    	for (int i = 0; i < rowSize ; i++) {
    		series[i].setName(textCol[i]);
    		for (int k = 1; k < numKey; k++) {
    			series[i].getData().add(new XYChart.Data(keyRow[k], data[k-1][i]));
    		}
    	}
     	for (int i = 0; i < rowSize ; i++) {
    		bc.getData().add(series[i]);
    	}
    	return bc;
	}
	
}
