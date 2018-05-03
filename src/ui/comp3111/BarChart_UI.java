package ui.comp3111;

import core.comp3111.DataTableArray;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarChart_UI {
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	protected BarChart<String,Number> bc;
	public Button btLineChartBackMain;
	
	public BarChart_UI() {
		
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		bc = new BarChart<String,Number>(xAxis,yAxis);
		btLineChartBackMain = new Button();
	}
	
	public void populateDataToBarChartUI(DataTableArray dta) {
    	XYChart.Series [] series = new XYChart.Series[dta.rowSize];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    
    	for (int i = 0; i < dta.rowSize ; i++) {
    		series[i].setName(dta.textCol[i]);
    		for (int k = 1; k < dta.numKey; k++) {
    			series[i].getData().add(new XYChart.Data(dta.keyRow[k], dta.data[k-1][i]));
    		}
    	}
		
    	for (int i = 0; i < dta.rowSize ; i++) {
    		bc.getData().add(series[i]);
    	}
	}
	
	public Pane paneBarChartScreen(String xAxisLabel, String yAxisLabel, String chartTitle) {
		Button btLineChartBackMain = this.btLineChartBackMain;

		xAxis.setLabel(xAxisLabel);
		yAxis.setLabel(yAxisLabel);
		bc.setTitle(chartTitle);

		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(bc, btLineChartBackMain);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;	
	}
	
	public CategoryAxis getXAxis() {
		return xAxis;
	}
	public NumberAxis getYAxis() {
		return yAxis;
	}
	public BarChart<String, Number> getBC(){
		return bc;
	}
	public Button getBackButton() {
		return btLineChartBackMain;
	}
	public void setBackButton(Button b) {
		btLineChartBackMain = b;
	}

}
