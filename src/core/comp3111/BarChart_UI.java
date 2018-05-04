package core.comp3111;

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

/**
 * BarChart_: a class for constructing a bar chart
 * it contains all the data members from JavaFX and methods (which involves use of JavaFX elements) needed for this purpose.
 * @author kclamap
 *
 */
public class BarChart_UI {
	/**
	 * data members of BarChart_UI
	 */
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	public BarChart<String,Number> bc;
	public Button btLineChartBackMain;
	
	/**
	 * Default constuctor of BarChart_UI
	 * it initialize data members of BarChart_UI to meaningless values
	 */
	public BarChart_UI() {
		
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		bc = new BarChart<String,Number>(xAxis,yAxis);
		btLineChartBackMain = new Button();
	}
	
	/**
	 * it accepts a DataTableAaary and adds its content to a barChart for constructing it
	 * @param dta
	 */
	public void populateDataToBarChartUI(DataTableArray dta) {
	bc.getData().clear();
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
    	
    	System.out.println(bc.equals(null));
    	
    	for (int i = 0; i < dta.rowSize ; i++) { 
    		bc.getData().add(series[i]);  
    	}
    	
    	
	}
	
	/**
	 * it takes in the x Axis' and y Axis' label as well as the title for the chart to initialize the pane containing the bar chart
	 * @param xAxisLabel
	 * @param yAxisLabel
	 * @param chartTitle
	 * @return
	 */
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
	
	/**
	 * getter
	 * @return
 	 *		data member xAxis
	 */
	public CategoryAxis getXAxis() {
		return xAxis;
	}
	/**
	 * getter
	 * @return
	 * 		data member yAxis
	 */
	public NumberAxis getYAxis() {
		return yAxis;
	}
	/**
	 * getter
	 * @return
	 * 		data member bc
	 */		
	public BarChart<String, Number> getBC(){
		return bc;
	}
	/**
	 * getter
	 * @return
	 * 		data member btLineChartBackMain
	 */
	public Button getBackButton() {
		return btLineChartBackMain;
	}
	/**
	 * setter
	 * @param b
	 * 		set data member btLineChartBackMain to b
	 */		
	public void setBackButton(Button b) {
		btLineChartBackMain = b;
	}

}
