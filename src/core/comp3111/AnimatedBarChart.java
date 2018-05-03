package core.comp3111;

import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AnimatedBarChart extends Chart {
	BarChart_[] frame;
	int numFrame;
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	public Button btLineChartBackMain;
	
	public AnimatedBarChart() {
		frame = null;
		numFrame = 0;
		xAxis = null;
		yAxis = null;
		type = 2;
	}
	
	public AnimatedBarChart(DataTable t) {
		dataset = t;
		numFrame = t.getNumRow() * (t.getDC().size() - 1);
		frame = new BarChart_[numFrame];
		for (int i = 0; i<frame.length; i++) {
			frame[i] = new BarChart_();
		}
	}
	
	private void populateDataToAnimatedBarChart() {
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
    	
    	for (int i = 0; i < rowSize ; i++) {
    		series[i].setName(textCol[i]);

    	}
    	
    	int frameCounter = 0;
    	for (int i = 0; i < rowSize; i++) {
    		for (int k = 1; k < numKey; k++) {
    			series[i].getData().add(new XYChart.Data(keyRow[k], data[k-1][i]));
    			for (int m = frameCounter; m < numFrame; m++) {
    				for (int n = 0; n < rowSize; n++) {
    					frame[m].bc.getData().add(series[n]);
    				}
    			}
    			frameCounter++;
    		}
    	}
	}	

	public void populateDataToChart() {
		populateDataToAnimatedBarChart();
	}
	
	private Pane paneAnimatedBarChart(String xAxisLabel, String yAxisLabel, String chartTitle) {
		btLineChartBackMain = this.btLineChartBackMain;

		xAxis.setLabel(xAxisLabel);
		yAxis.setLabel(yAxisLabel);
		for (int i = 0; i < numFrame; i++) {
			frame[i].bc.setTitle(chartTitle);
		}

		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().add(btLineChartBackMain);
		for (int i = 0; i < numFrame; i++) {
			container.getChildren().add(frame[i].bc);
		}
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	}
	
	public Pane paneChart(String xAxisLabel, String yAxisLabel, String chartTitle) {
		return paneAnimatedBarChart(xAxisLabel, yAxisLabel, chartTitle);
	}
	
	public BarChart_[] getFrame() {
		return frame;
	}
}
