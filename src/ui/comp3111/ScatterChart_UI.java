package ui.comp3111;

import core.comp3111.DataTableArray;
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
/**
 * BarChart_: a class for constructing a scatter chart
 * it contains all the data members from JavaFX and methods (which involves use of JavaFX elements) needed for this purpose.
 * @author kclamap
 *
 */
public class ScatterChart_UI {
	/**
	 * data members of Scatter_UI
	 */
	private NumberAxis xAxis;
    private NumberAxis yAxis;        
    private ScatterChart<Number,Number> sc;
    public Button btLineChartBackMain; 
    
    /**
	 * Default constuctor of ScatterChart_UI
	 * it initialize data members of ScatterChart_UI to meaningless values
	 */
	public ScatterChart_UI() {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		sc = new ScatterChart<Number,Number>(xAxis,yAxis);
		btLineChartBackMain = new Button();
	}
	
	
	/**
	 * it accepts a DataTableAaary and adds its content to a scatterChart for constructing it
	 * @param dta
	 */
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
	
	/**
	 * it takes in the x Axis' and y Axis' label as well as the title for the chart to initialize the pane containing the scatter chart
	 * @param xAxisLabel
	 * @param yAxisLabel
	 * @param chartTitle
	 * @return
	 */
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
    
	/**
	 * getter
	 * @return
 	 *		data member xAxis
	 */
	public NumberAxis getXAxis() {
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
	 * 		data member sc
	 */		
	public ScatterChart<Number, Number> getSC(){
		return sc;
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