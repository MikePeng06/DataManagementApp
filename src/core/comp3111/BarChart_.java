package core.comp3111;

import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChart_ extends Application {
	private DataTable generateDummyDataTable() throws DataTableException {
		DataTable t = new DataTable();

        String[] Year = new String[] {"2003", "2004", "2005"};
        DataColumn YearCol = new DataColumn(DataType.TYPE_STRING, Year);
        
        
		Number[] austria = new Float[] { (float) 25601, (float) 57401, (float) 45000};
		DataColumn austriaCol = new DataColumn(DataType.TYPE_NUMBER, austria);
		
		Number[] brazil = new Float[] { (float) 20148, (float) 41941, (float) 44835};
		DataColumn brazilCol = new DataColumn(DataType.TYPE_NUMBER, brazil);
		
		Number[] france = new Float[] { (float) 10000, (float) 45263, (float) 18722};
		DataColumn franceCol = new DataColumn(DataType.TYPE_NUMBER, france);
		
		Number[] italy = new Float[] { (float) 35407, (float) 117320, (float) 17557};
		DataColumn italyCol = new DataColumn(DataType.TYPE_NUMBER, italy);
		
		Number[] usa = new Float[] { (float) 12000, (float) 14845, (float) 92633};
		DataColumn usaCol = new DataColumn(DataType.TYPE_NUMBER, usa);
				

		t.addCol("Year", YearCol);
		t.addCol("Austria", austriaCol);
		t.addCol("Brazil", brazilCol);
		t.addCol("France", franceCol);
		t.addCol("Italy", italyCol);
		t.addCol("USA", usaCol);
		
		return t;
	}
	
	public static BarChart generateBarChart(DataTable t) {
		//stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
        
        DataTable dataset = t;
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
    		for (int k = 1; k < numKey; k++) {
    			series[i].getData().add(new XYChart.Data(keyRow[k], data[k-1][i]));
    		}
    	}
		
    	for (int i = 0; i < rowSize ; i++) {
    		bc.getData().add(series[i]);
    	}
    	
    	return bc;
    	
	}

	@Override public void start(Stage stage) throws DataTableException {
        
        /////////////////////////////////////////////////////////////////
        DataTable t = generateDummyDataTable();
    	
        Scene scene  = new Scene(generateBarChart(t),800,600);

        stage.setScene(scene);
        stage.show();
    	}
	
	public static void main(String[] args) {
		launch(args);
	}

}