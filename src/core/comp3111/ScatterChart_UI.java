package core.comp3111;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ScatterChart_UI {
	private NumberAxis xAxis;
    private NumberAxis yAxis;        
    private ScatterChart<Number,Number> sc;
    public Button btLineChartBackMain; 
    
	public ScatterChart_UI() {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		sc = new ScatterChart<Number,Number>(xAxis,yAxis);
		btLineChartBackMain = new Button();
	}
	
	public void populateDataToScatterChartUI(DataTableArray dta) {
		sc.getData().clear();
    	XYChart.Series [] series = new XYChart.Series[dta.numDistinctElement];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    	for (int i = 0; i < dta.numDistinctElement ; i++) {
    		series[i].setName(dta.textColDistinct[i]);
    		
    	}
    	for (int i = 0; i < dta.rowSize; i++) {
    		for (int k = 0; k < dta.numDistinctElement; k++) {
    			if (dta.textCol[i].equals( dta.textColDistinct[k]) ){
    				series[k].getData().add(new XYChart.Data(dta.data[1][i], dta.data[0][i]));
    				
    			}
    		}
    	}
    	
    	for (int i = 0; i < dta.numDistinctElement ; i++) {
    		sc.getData().add(series[i]);
    		
    	}
	}
	
    public Pane paneScatterChartScreen(String xAxisLabel, String yAxisLabel, String chartTitle) {
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
}
