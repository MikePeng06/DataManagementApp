package core.comp3111;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChart_backup {
	
	@Override public void start(Stage stage) {
	
	DataTable dataset = SampleDataGenerator.generateSampleLineData();
	int numKey = dataset.getNumCol();
	int numTextCol = 1;
	int numNumericCol = numKey - numTextCol;
	int rowSize = dataset.getNumRow();
	String temp = "";
	
	String[] textCol = new String[rowSize - 1];
	String[] Key = new String[numKey];
	float[][] data = new float[numNumericCol][rowSize];
	int j = 1;
	for (Object key:dataset.getDC().keySet()) {
		temp = (String) dataset.getDC().get(key);
		if (dataset.getCol(temp).getTypeName() == "text") {
			Key[0] = temp;
			for (int i = 0; i < rowSize - 1; i++) {
				textCol[i] = (String) dataset.getCol(temp).getData()[i];
			}
		}
		if (dataset.getCol(temp).getTypeName() == "numeric") {
			Key[j] = temp;
			for (int i = 0; i < rowSize - 1; i++) {
				data[j][i] = (float) dataset.getCol(temp).getData()[i];
			}
			j++;
		}
	}
	XYChart.Series series[] = new XYChart.Series[rowSize-1];
	for (int i = 1; i < rowSize; i++) {
		series[i].setName(textCol[i]);
		for (int k = 1; k < numKey; k++) {
			series[i].getData().add(new XYChart.Data(Key[k], data[k][i]));
		}
	}
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> bc = 
        new BarChart<String,Number>(xAxis,yAxis);
    bc.setTitle("Country Summary");
    xAxis.setLabel("Country");       
    yAxis.setLabel("Value");
	bc.getData().clear();
	for (int i = 0; i < rowSize - 1; i++) {
		bc.getData().add(series[i]);
	}
	
    Scene scene  = new Scene(bc,800,600);
   // bc.getData().addAll(series1, series2, series3);
    stage.setScene(scene);
    stage.show();
	}
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
