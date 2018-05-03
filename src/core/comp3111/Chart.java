package core.comp3111;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class Chart implements java.io.Serializable{
	private String[] typeSet = {"BarChart", "ScatterChart", "AnimatedChart"};
	protected int type = -1;
	protected DataTable dataset;
	protected Button btLineChartBackMain = null;
	
	abstract Pane paneChart(String xAxisLabel, String yAxisLabel, String chartTitle);
	
	abstract void populateDataToChart();
	
	public DataTable getDataset() {return dataset;}
	public void setDataset(DataTable t) {dataset = t;}
	
	public String getType() {return typeSet[type];}
	public void setType(int index) {type = index;}
	
}
