package core.comp3111;

import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterChart_ extends Application {
	 
    @Override public void start(Stage stage) throws DataTableException {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Age (years)");                
        yAxis.setLabel("Returns to date");
        sc.setTitle("Investment Overview");
       
        ////////////////////////////////////////////////////
        DataTable t = new DataTable();
        String[] category = new String[25];
        for (int i = 0; i < 25; i++) {
        	if (i <= 15) {
        		category[i] = "Equities";
        	}else {
        		category[i] = "Mutual funds";
        	}
        }
        DataColumn categoryCol = new DataColumn(DataType.TYPE_STRING, category);
        
        
        Float[] age = new Float[] {(float) 4.2, (float) 2.8, (float) 6.2, (float) 1, (float) 1.2, 
        						   (float) 4.4, (float) 8.5, (float) 6.9, (float) 9.9, (float) 0.9, 
        						   (float) 3.2, (float) 4.8, (float) 7.3, (float) 1.8, (float) 7.3, 
        						   (float) 2.7, (float) 5.2, (float) 2.4, (float) 3.2, (float) 1.8, 
        						   (float) 3.2, (float) 7.4, (float) 3.5, (float) 9.3, (float) 8.1};
		DataColumn ageCol = new DataColumn(DataType.TYPE_NUMBER, age);
        
		Float[] returnToDate = new Float[] {(float) 193.2, (float) 33.6, (float) 24.8, (float) 14, (float) 26.4,
										    (float) 114.4, (float) 323, (float) 289.8, (float) 287.1, (float) -9,
										    (float) 150.8, (float) 20.8, (float) -42.3, (float)  81.4, (float) 110.3,
										    (float) 41.2, (float) 229.2, (float) 37.6, (float) 49.8, (float) 134,
										    (float) 236.2, (float) 114.1, (float) 323, (float) 29.9, (float) 287.4};
		DataColumn returnToDateCol = new DataColumn(DataType.TYPE_NUMBER, returnToDate);
		
		
		
		t.addCol("Category", categoryCol);
		t.addCol("Age", ageCol);
		t.addCol("Return To Date", returnToDateCol);
		
//		Set<String> keys_before = t.getDC().keySet();
//    	for (String key_before : keys_before) {
//    		System.out.print(key_before + " ");
//    		for (int i = 0; i < t.getNumRow(); i++) {
//    			System.out.print(t.getCol(key_before).getData()[i] + " ");
//    		}
//    		System.out.println();
//    	}
        
        
        ////////////////////////////////////////////////////
        
        DataTable dataset = t;
    	int numKey = dataset.getNumCol();
    	int numTextCol = 1;
    	int numNumericCol = numKey - numTextCol;
    	int rowSize = dataset.getNumRow();
    	
    	String[] textCol = new String[rowSize];
    	String[] keyRow = new String[numKey];
    	float[][] data = new float[numNumericCol][rowSize];
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
    				data[j-1][i] = (float) dataset.getCol(key).getData()[i] ;
    			}
    			j++;
    		}
    	}
    	
    	
//    	for (int i = 0; i < numKey; i++) {
//    		System.out.print(keyRow[i] + " ");
//    	}
//    	System.out.println();
//    	for (int m = 0; m < rowSize; m++) {
//    		for (int k = 0; k < numNumericCol; k++) {
//    			if (k == 0) {
//    				System.out.print(textCol[m] + " ");
//    			}
//    			System.out.print(data[k][m] + " ");
//    		}
//    		System.out.println();
//    	}
    	
    	
    	int numDistinctElement = 1;
    	boolean same = false;
    	for (int i = 1; i< rowSize; i++) {
    		same = false;
    		for (int n = 0; n < i; n++) {
    			if (textCol[i] == textCol[n]) {
    				same = true;
    				break;
    			}
    		}
    		if (same == false) {
    			numDistinctElement++;
    		}
    	}
    	String[] textColDistinct = new String[numDistinctElement];
    	int numNonEmptytextColDistinct = 0;
    	for (int i = 0; i < rowSize; i++) {
    		same = false;
    		for (int k = 0; k < numNonEmptytextColDistinct; k++) {
    			if (textCol[i] == textColDistinct[k]) {
    				same = true;
    			}
    		}
    		if (!same) {
    			textColDistinct[numNonEmptytextColDistinct] = textCol[i];
    			numNonEmptytextColDistinct++;
    		}
    	}
    	
    	
    	
    	XYChart.Series [] series = new XYChart.Series[numDistinctElement];
    	for (int i = 0; i < series.length ;i++) {
    		series[i] = new  XYChart.Series();
    	}
    	for (int i = 0; i < numDistinctElement ; i++) {
    		series[i].setName(textColDistinct[i]);

    		
    	}
    	for (int i = 0; i < rowSize; i++) {
    		for (int k = 0; k < numDistinctElement; k++) {
    			if (textCol[i] == textColDistinct[k]) {
    				series[k].getData().add(new XYChart.Data(data[1][i], data[0][i]));
//    				System.out.println(data[0][i] + "  " + data[1][i]);
    			}
    		}
    	}
    	

 
    	for (int i = 0; i < numDistinctElement ; i++) {
    		sc.getData().add(series[i]);
    	}
        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}