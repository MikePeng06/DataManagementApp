package ui.comp3111;


import core.comp3111.DataTableArray;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

/**
 * BarChart_: a class for constructing an animated bar chart
 * it contains all the data members from JavaFX and methods (which involves use of JavaFX elements) needed for this purpose.
 * @author kclamap
 *
 */
public class BarChartAnimate_UI {
	/**
	 * data members of BarChartAnimate_UI
	 */
	private static CategoryAxis xAxis;
	private static NumberAxis yAxis;
	protected BarChart<String,Number> bc;
	public Button btLineChartBackMain;
	private int maxItem;
	
	/**
	 * Default constuctor of BarChartAnimate_UI
	 * it initialize data members of BarChartAnimate_UI to meaningless values
	 */
	public BarChartAnimate_UI() {
		
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		bc = new BarChart<String,Number>(xAxis,yAxis);
		btLineChartBackMain = new Button();
		maxItem = 0;
	
	}
	
	/**
	 * it accepts a DataTableAaary and adds its content to a barChart to construct it
	 * it also generates keyFrames needed for the time line to animate the bar chart 
	 * @param dta
	 */
	public void populateDataToBarCharAnimatetUI(DataTableArray dta) {
	bc.getData().clear();
    	XYChart.Series [] series = new XYChart.Series[dta.rowSize];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    
    	for (int i = 0; i < dta.rowSize ; i++) {
    		series[i].setName(dta.textCol[i]);
    		for (int k = 1; k < dta.numKey; k++) {
    			series[i].getData().add(new XYChart.Data(dta.keyRow[k], 0));
    			if (dta.data[k-1][i] > maxItem) {
    				maxItem = dta.data[k-1][i];
    			}
    			
    		}
    	}
		

    	
        Timeline tl = new Timeline();
        KeyFrame grow = new KeyFrame(Duration.seconds(.200),
                new EventHandler<ActionEvent>() {
	        	public void handle(ActionEvent event) {
	        		
	        		int counter = 0;
	        		while (counter < maxItem) {
		        		int i = 0;
		        		for (XYChart.Series<String, Number> series : bc.getData()) {
		        			int k = 1;
		                    for (XYChart.Data<String, Number> data : series.getData()) {
		                    	if ((Double) data.getYValue() < dta.data[k-1][i]) {
		                    		data.setYValue((Double) data.getYValue() + 1);
		                    	}
		                    	k++;
		                    }
		                    i++;
		                }
		        		counter++;
	        		}
	        		
	        	}
        });
        
        
        tl.getKeyFrames().add(grow);

//        tl.getKeyFrames().add(pause);
//        tl.getKeyFrames().add(reset);
//        tl.getKeyFrames().add(reset);
//        tl.getKeyFrames().add(grow);
        //tl.getKeyFrames().add(pause);
//        tl.getKeyFrames().add(reset);
//        tl.getKeyFrames().add(grow);
//        tl.getKeyFrames().add(grow);
//        tl.getKeyFrames().add(grow);
//        tl.getKeyFrames().add(grow);
//        tl.getKeyFrames().add(grow);
        
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        

        
        
        
    	for (int i = 0; i < dta.rowSize ; i++) {
    		bc.getData().add(series[i]);
    	}
	}
	
	/**
	 * it takes in the x Axis' and y Axis' label as well as the title for the chart to initialize the pane containing the animated bar chart
	 * @param xAxisLabel
	 * @param yAxisLabel
	 * @param chartTitle
	 * @return
	 */
	public Pane paneBarChartAnimateScreen(String xAxisLabel, String yAxisLabel, String chartTitle) {
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

