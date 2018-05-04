
package core.comp3111;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class Chart implements java.io.Serializable{
	private String[] typeSet = {"BarChart", "ScatterChart", "AnimatedChart"};
	protected int type = -1;
	protected DataTable dataset;
	public Button btLineChartBackMain = null;
	
//	abstract Pane paneChart(String xAxisLabel, String yAxisLabel, String chartTitle);
	
	abstract void populateDataToChart();
	
	public DataTable getDataset() {return dataset;}
	
	public String getType() {return typeSet[type];}
	
}