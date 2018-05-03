package core.comp3111;

import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AnimatedBarChart_UI {
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	public Button btLineChartBackMain;
	
	public AnimatedBarChart_UI() {
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		btLineChartBackMain = new Button();
		
	}
	
	public void populateDataToAnimatedBarChartUI(DataTableArray dta, AnimatedBarChart abc) {
		for (int i = 0; i < abc.getNumFrame(); i++) {
			abc.getFrame()[i].getBC().getData().clear();
		}
    	XYChart.Series [] series = new XYChart.Series[dta.rowSize];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    	
    	for (int i = 0; i < dta.rowSize ; i++) {
    		series[i].setName(dta.textCol[i]);
    	}
    	
    	int frameCounter = 0;
    	for (int i = 0; i < dta.rowSize; i++) {
			//System.out.println(series.length + " " + dta.keyRow.length + " " + dta.data.length + " " + dta.data[0].length);
    			series[i].getData().add(new XYChart.Data(dta.keyRow[1], dta.dataSingle[i]));
 //   			for (int m = frameCounter; m < abc.getNumFrame(); m++) {
//				for (int n = 0; n < dta.rowSize; n++) {
//					abc.getFrame()[frameCounter].getBC().getData().add(series[n]);
//				}
//    			}
    			
			frameCounter++;
    	}
    	for (int i = 0; i < series.length; i++) {
    		abc.getFrame()[0].getBC().getData().add(series[i]);
    	}
	}
	
	public Pane paneAnimatedBarChart(String xAxisLabel, String yAxisLabel, String chartTitle, AnimatedBarChart abc, int frameIndex) {
		btLineChartBackMain = this.btLineChartBackMain;

		xAxis.setLabel(xAxisLabel);
		yAxis.setLabel(yAxisLabel);
		abc.frame[frameIndex].bc.setTitle(chartTitle);

		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(abc.getFrame()[frameIndex].bc, btLineChartBackMain);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	}
}
