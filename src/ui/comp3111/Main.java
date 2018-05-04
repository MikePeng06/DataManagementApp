package ui.comp3111;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import core.comp3111.DataColumn;
import core.comp3111.DataPack;
import core.comp3111.DataPackA;
import core.comp3111.DataTable;
import core.comp3111.DataTableArray;
import core.comp3111.DataTableException;
import core.comp3111.DataType;
import core.comp3111.SampleDataGenerator;
import core.comp3111.LoadData;
import core.comp3111.DataPack;
import core.comp3111.ToProject;
import core.comp3111.SelectColumn;
import core.comp3111.SplitTable;
import core.comp3111.SplitTextColumn_delimiter;
import core.comp3111.SplitTextColumn_fixedWidth;
import core.comp3111.Chart;
import core.comp3111.BarChart_;
import core.comp3111.BarChart_UI;
import core.comp3111.ScatterChart_;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Main class of this GUI application
 * 
 * @author cspeter
 *
 */
public class Main extends Application {

	// Attribute: DataTable 
	// In this sample application, a single data table is provided
	// You need to extend it to handle multiple data tables
	// Hint: Use java.util.List interface and its implementation classes (e.g.
	// java.util.ArrayList)
	private DataTable sampleDataTable = null;

	// Attributes: Scene and Stage
	private static final int SCENE_NUM = 4;
	private static final int SCENE_MAIN_SCREEN = 0;
	private static final int SCENE_LINE_CHART = 1;
	private static final int SCENE_BAR_CHART = 2;
	private static final int SCENE_SCATTER_CHART = 3;
	private int SCENE_INDEX = 0;
	private static final String[] SCENE_TITLES = { "COMP3111 Chart - [Team Name]", "Sample Line Chart Screen", "Sample Bar Screen", "Sample Scatter Screen" };
	private Stage stage = null;
	private Scene[] scenes = null;
	private ArrayList<String> dataTableName = new ArrayList<String>();
	private ArrayList<String> charName = new ArrayList<String>();
	private ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
	private ArrayList<DataTable> chartList = new ArrayList<DataTable>();
	private ArrayList<Chart> ChartObject = new ArrayList<Chart>();
	private String DataTemp;
	private ArrayList<String> ColumnName = new ArrayList<String>(); 
	public BarChart_ chartbc;
	public BarChart_UI chartuibc;
	public ArrayList<DataTableArray> DTALIST = new ArrayList<DataTableArray>();
	public DataTableArray tempdta;
	public ArrayList<String> path = new ArrayList<String>();
	public ArrayList<File> flist = new ArrayList<File>();
	// To keep this application more structural, 
	// The following UI components are used to keep references after invoking
	// createScene()

	// Screen 1: paneMainScreen
	private Button btSampleLineChartData, btSampleLineChartDataV2, btSampleLineChart, btSelectFile, btGenerateChart, 
	btSaveChart, LoadProject, SaveProject, btSplitTable, btSplitColumn_fixedWidth, btSplitColumn_delimiter;
	private Label lbSampleDataTable, lbMainScreenTitle;
	private ChoiceBox<String> cb;
	private ListView<String> DataSetList = new ListView<>();  
	private ListView<String> ChartList = new ListView<>(); 
	private ListView<CheckBox> ColumnList = new ListView<>();

	// Screen 2: paneSampleLineChartScreen
	private LineChart<Number, Number> lineChart = null;
	private NumberAxis xAxis = null;
	private NumberAxis yAxis = null;
	private Button btLineChartBackMain = null;

	/**
	 * create all scenes in this application
	 */
	private void initScenes() {
		//		dataTableList.add(SampleDataGenerator.generateSampleLineData());
		//		dataTableList.add(SampleDataGenerator.generateSampleLineDataV2());
		//		dataTableName.add("Sample 1");
		//		dataTableName.add("Sample 2");

		scenes = new Scene[SCENE_NUM];
		scenes[SCENE_MAIN_SCREEN] = new Scene(paneMainScreen(), 500, 550);
		scenes[SCENE_LINE_CHART] = new Scene(paneLineChartScreen(), 800, 600);
		BarChart_ bc = new BarChart_();
//		scenes[SCENE_BAR_CHART] = new Scene(this.chartbc.paneChart("x", yAxisLabel, chartTitle), 800, 500);
//		scenes[SCENE_SCATTER_CHART] = new Scene(paneLineChartScreen(), 800, 600);
		for (Scene s : scenes) {
			if (s != null)
				// Assumption: all scenes share the same stylesheet
				s.getStylesheets().add("Main.css");
		}
	}

	/**
	 * This method will be invoked after createScenes(). In this stage, all UI
	 * components will be created with a non-NULL references for the UI components
	 * that requires interaction (e.g. button click, or others).
	 */
	private void initEventHandlers() {
		initMainScreenHandlers();
		initLineChartScreenHandlers();
		btSampleLineChart.setOnAction(e -> {
			//BarChart_UI  chartuibc = new BarChart_UI();
			if(SCENE_INDEX == 2) {
				System.out.println(ChartObject.size());
				chartuibc.populateDataToBarChartUI(tempdta);
			}else if (SCENE_INDEX == 3) {
				//chartuisc.populateDataToScatterChartUI(tempdta);
			}
			 
//			System.out.println(chartbc.getDTA());
//			System.out.println(((BarChart_)ChartObject.get(0)));
//			System.out.println(ChartObject.size());
//		    this.chartbc.populateDataToBarChart();
			putSceneOnStage(SCENE_INDEX);
			
		});
	}

	/**
	 * Initialize event handlers of the line chart screen
	 */
	private void initLineChartScreenHandlers() {

		// click handler
		btLineChartBackMain.setOnAction(e -> {
			putSceneOnStage(SCENE_MAIN_SCREEN);
		});
	}


	/**
	 * Populate sample data table values to the chart view
	 */
	private void populateSampleDataTableValuesToChart(String seriesName) {

		// Get 2 columns
		DataColumn xCol = sampleDataTable.getCol("X");
		DataColumn yCol = sampleDataTable.getCol("W");

		// Ensure both columns exist and the type is number
		if (xCol != null && yCol != null && xCol.getTypeName().equals(DataType.TYPE_NUMBER)
				&& yCol.getTypeName().equals(DataType.TYPE_NUMBER)) {

			lineChart.setTitle("Sample Line Chart");
			xAxis.setLabel("X");
			yAxis.setLabel("Y");

			// defining a series
			XYChart.Series series = new XYChart.Series();

			series.setName(seriesName);

			// populating the series with data
			// As we have checked the type, it is safe to downcast to Number[]
			Number[] xValues = (Number[]) xCol.getData();
			Number[] yValues = (Number[]) yCol.getData();

			// In DataTable structure, both length must be the same
			int len = xValues.length;

			for (int i = 0; i < len; i++) {
				series.getData().add(new XYChart.Data(xValues[i], yValues[i]));
			}

			// clear all previous series
			lineChart.getData().clear();

			// add the new series as the only one series for this line chart
			lineChart.getData().add(series);

		}

	}

	/**
	 * Initialize event handlers of the main screen
	 */
	private void initMainScreenHandlers() {

		// click handler
		btSampleLineChartData.setOnAction(e -> {

			// In this example, we invoke SampleDataGenerator to generate sample data
			sampleDataTable = SampleDataGenerator.generateSampleLineData();
			lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
					sampleDataTable.getNumCol()));

			populateSampleDataTableValuesToChart("Sample 1");

		});

		// click handler
		btSampleLineChartDataV2.setOnAction(e -> {

			// In this example, we invoke SampleDataGenerator to generate sample data
			sampleDataTable = SampleDataGenerator.generateSampleLineDataV2();
			lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
					sampleDataTable.getNumCol()));

			populateSampleDataTableValuesToChart("Sample 2");

		});

		// click handler
//		btSampleLineChart.setOnAction(e -> {
//			System.out.println(ChartObject.size());
//		    this.chartbc.populateDataToBarChart();
//			putSceneOnStage(SCENE_INDEX);
//			
//		});

		btSelectFile.setOnAction(e ->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("CSV", "*.csv")
					); 
			fileChooser.setTitle("Open Resource File");
			File file = null;
			file = fileChooser.showOpenDialog(stage);
			//DataSetList.getItems().add(file.getName());
			if(file!=null){
			try {
				dataTableList.add(LoadData.ToDataTable(file.getAbsolutePath()));
//				dataTableName.add(file.getName());
//				DataSetList.getItems().add(file.getName());
				path.add(file.getAbsolutePath());
				flist.add(file);
			} catch (DataTableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print("IO ERROR");
				e1.printStackTrace();
			}
			
			
			
			TextInputDialog dialog = new TextInputDialog(file.toString());
			dialog.setTitle("Enter Name");
			dialog.setHeaderText("Enter the name");
			dialog.setContentText("Enter the name for the selected dataset");

			// 传统的获取输入值的方法
			Optional result = dialog.showAndWait();
			if (result.isPresent()) {
				DataSetList.getItems().add(result.get().toString());
				dataTableName.add(result.get().toString());
			   System.out.println(result.get());
			}
			
//						TextInputDialog dialog = new TextInputDialog(file.getName()); 
//						dialog.setTitle("Enter the DataSet Name");
//						dialog.setHeaderText("Enter the DataSet Name");
//						dialog.setContentText("");
//						dialog.show();
//						
//						String savename = "";
//						Optional<String> result = dialog.showAndWait();
//						if (result.isPresent()){
//						    System.out.println("Your name: " + result.get());
//						}
						
			}});

		btGenerateChart.setOnAction(e -> {

			//			ChartList.getItems().add("chart");
			//			charName.add("chart");

			ArrayList<String> choices = new ArrayList<>();
			choices.add("BarChart");
			choices.add("ScatterChart");
			choices.add("AnimateChart");

			ChoiceDialog<String> dialog = new ChoiceDialog<>("BarChart", choices);
			dialog.setTitle("Choice Dialog");
			dialog.setHeaderText("Look, a Choice Dialog");
			dialog.setContentText("Choose your letter:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				System.out.println("Your choice: " + result.get());
			}
			
			
			TextInputDialog dialog2 = new TextInputDialog("chart");
			dialog2.setTitle("Enter Name");
			dialog2.setHeaderText("Enter the name");
			dialog2.setContentText("Enter the name for the chart");

			// 传统的获取输入值的方法
			Optional result2 = dialog2.showAndWait();
			if (result.isPresent()) {
				charName.add(result2.get().toString());
				ChartList.getItems().add(result2.get().toString());
				
			   System.out.println(result.get());
			}
			
		
			DataTable dttemp = new DataTable();
			if(result.isPresent()) {
				if(result.get() == "BarChart") {
					for (CheckBox cb: ColumnList.getItems()) {
						if(cb.isSelected()) {
							String strtemp = cb.getText().substring(0, cb.getText().indexOf(' '));
							System.out.println(strtemp);
							try {
								dttemp.addCol(cb.getText(), sampleDataTable.getCol(strtemp));
							} catch (DataTableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					//sampleDataTable = dttemp;
					BarChart_  x = new BarChart_(dttemp);
					//x.getUI().setBackButton(this.btLineChartBackMain);
					ChartObject.add(x);
					//ChartList.getItems().add("chart");
					//charName.add("chart");
					x.populateDataToChart();
					DTALIST.add(x.getDTA());
					chartList.add(dttemp);
					//System.out.println(ChartObject.size());
				}
				else if(result.get() =="ScatterChart") {
					ScatterChart_ x =new ScatterChart_(sampleDataTable);
					//					scenes[SCENE_SCATTER_CHART] = new Scene(x.paneChart("X", "y", "HELLO"), 800, 600); 
					//					x.populateDataToChart();
					//					SCENE_INDEX = SCENE_SCATTER_CHART;
					//x.btLineChartBackMain = this.btLineChartBackMain;
					ChartObject.add(x);
					ChartList.getItems().add("chart");
					charName.add("chart");
				}
			}

		});

		//
		LoadProject.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("comp3311", "*.comp3311")
					);
			fileChooser.setTitle("LoadProject");
			File file = null;
			file = fileChooser.showOpenDialog(stage);
			DataPack dp;
			if(file!=null) {
			try {
				dp = ToProject.LoadProject(file.getAbsolutePath());

				
				for(File str: dp.flist) {
					if(!str.exists()) {
						System.out.println("error");
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("Source file miss");
						alert.setContentText("Please make source dataset in the right path");

						alert.showAndWait();
						break;
					}
					dataTableList.add(LoadData.ToDataTable(str.getAbsolutePath()));
					dataTableName.add(file.getName());
					
					//path.add(file.getAbsolutePath());
				}
				
				for(String str: dp.dataTableName) {
					DataSetList.getItems().add(str);
				}
//				for(DataTable dt: dp.dataTableList) {
//				dataTableList.add(dt);
//			}
			//dataTableList = dp.dataTableList;
			for(DataTable dt: dp.chartList) {
				chartList.add(dt);
			}
			//chartList = dp.chartList;
			for(String str: dp.charName) {
				charName.add(str);
			}
			//charName = dp.charName;
//			dataTableName = new ArrayList<String>();
//			for(String str: dp.dataTableName) {
//				dataTableName.add(str);
//			}
			//dataTableName = dp.dataTableName;
//			ChartObject = new ArrayList<Chart>();
			for(DataTableArray str: dp.DTALIST) {
				DTALIST.add(str);
			}
				
				
				
				//DTALIST = dp.DTALIST;
			} catch (ClassNotFoundException | DataTableException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
//			dataTableList = new ArrayList<DataTable>();
//			dataTableList = dp.dataTableList;
//			chartList = dp.chartList;
//			charName = dp.charName;
//			dataTableName = dp.dataTableName;
//			ChartObject = new ArrayList<Chart>();
//			DTALIST = dp.DTALIST;
			
			
//			for(String str : dataTableName) {
//				DataSetList.getItems().add(str);
//				//sampleDataTable = dataTableList.get(i);
//			}
			//System.out.println(dataTableList.get(0).getNumCol());
			
			for(String str: charName) {
				ChartList.getItems().add(str);
				//System.out.println(charName.get(i));
			}

			//dsd
			
			for(DataTable dt: chartList) {
				BarChart_ y = new BarChart_(dt);
				ChartObject.add(y);
				//sampleDataTable = chartList.get(i);
			}
			
//			System.out.println(dp.dataTableName.get(0));
			}});

		SaveProject.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("comp3311", "*.comp3311")
					);
			fileChooser.setTitle("Save File");
			fileChooser.setInitialFileName("comp3311");
			File file = fileChooser.showSaveDialog(stage);
			//File file = fileChooser.showOpenDialog(stage);
			//dataTableList.add(SampleDataGenerator.generateSampleLineData());
			System.out.print("CharList");
			System.out.println(chartList.size());
			if (file != null) {
			DataPack  dp = new DataPack(dataTableList, chartList, dataTableName, charName, DTALIST, flist);
				
				
				System.out.println(file.toString());
				ToProject.SaveProject(dp, file.toString());       			
			}
			//    			DataPack  dp = new DataPack(dataTableList, chartList, dataTableName, charName);
			//    			System.out.println(dataTableName.get(0));
			//    			ToProject.SaveProject(dp, file.getPath());            
		});

		btSplitTable.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				DataTable table = dataTableList.get(DataSetList.getSelectionModel().getSelectedIndex());
				try {
					TextInputDialog getDelimiter = new TextInputDialog("");
					getDelimiter.setTitle("Split A dataTable to two dataTable");
					getDelimiter.setHeaderText(" Input:30 denotes 30% split into the first Table, and 70% split into the second Table");
					getDelimiter.setContentText("Please input the percentage of the first Table split");
					Optional<String> result = getDelimiter.showAndWait();
					int partition1 = Integer.parseInt(result.get());
					DataTable[] buffer = SplitTable.splitDataTable(table, partition1);
					for(DataTable dt: buffer) {
						dataTableList.add(dt);
						String name= dataTableName.get(DataSetList.getSelectionModel().getSelectedIndex());
						DataSetList.getItems().add(name);
					}

				} catch (DataTableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		
		
		
		btSplitColumn_delimiter.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {

				TextInputDialog getDelimiter = new TextInputDialog("");
				getDelimiter.setTitle("Delimiter Input Dialog");
				getDelimiter.setHeaderText("Input delimiter and select OK");
				getDelimiter.setContentText("Please input delimiter");
				// Traditional way to get the response value.
				Optional<String> result = getDelimiter.showAndWait();
				if (result.isPresent()){
					System.out.println( result.get());
				}
				
				String target = result.get();

				String colName = "";
				DataColumn selectCol = new DataColumn();
				for(CheckBox SelectCol: ColumnList.getItems()) {
					if(SelectCol.isSelected())
						colName = SelectCol.getText().substring(0, SelectCol.getText().indexOf(' '));
					selectCol = sampleDataTable.getCol(colName);
				}
				DataColumn[] results = SplitTextColumn_delimiter.splitDataColumn(selectCol, target);
				
				for(int i = 0; i < results.length; i++) {
						try {
							sampleDataTable.addCol((colName + String.valueOf(i+1)) , results[i]);
						} catch (DataTableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(results[i]+"added");//test if added
				}
			}
		});
		
		
		

		btSplitColumn_fixedWidth.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {

				TextInputDialog getfixedWidth = new TextInputDialog("e.g. 1,3,4");
				getfixedWidth.setTitle("Input a list of the fixed points");
				getfixedWidth.setHeaderText("e.g. For Text [testing], Input:1,2 Outputs: [t], [e], [sting]");
				getfixedWidth.setContentText("Please input fixed points separate with comma in increasing order");
				// Traditional way to get the response value.
				Optional<String> result = getfixedWidth.showAndWait();
				if (result.isPresent()){
					System.out.println( result.get());
				}
				String[] inputs = result.get().split(",");
				int[] widths = new int[inputs.length];
				for(int i = 0 ; i< inputs.length; i++) {
					widths[i] = Integer.parseInt(inputs[i]);
				}

				String colName = "";
				DataColumn ColSelected = new DataColumn();
				for(CheckBox SelectCol: ColumnList.getItems()) {
					if(SelectCol.isSelected())
						colName = SelectCol.getText().substring(0, SelectCol.getText().indexOf(' '));
					ColSelected = sampleDataTable.getCol(colName);
				}
				DataColumn[] results = SplitTextColumn_fixedWidth.splitDataColumn(ColSelected, widths);
				
				for(int i = 0; i < results.length; i++) {
						try {
							sampleDataTable.addCol((colName + String.valueOf(i+1)) , results[i]);
						} catch (DataTableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(results[i]+"added");
				}
			}
		});

	}

	/**
	 * Create the line chart screen and layout its UI components
	 * 
	 * @return a Pane component to be displayed on a scene
	 */
	private Pane paneLineChartScreen() {

		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		btLineChartBackMain = new Button("Back");

		xAxis.setLabel("undefined");
		yAxis.setLabel("undefined");
		lineChart.setTitle("An empty line chart");

		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(lineChart, btLineChartBackMain);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	} 

	/**
	 * Creates the main screen and layout its UI components
	 * 
	 * @return a Pane component to be displayed on a scene
	 */
	private Pane paneMainScreen() {

		lbMainScreenTitle = new Label("Fanta-COMP3111");
		btSampleLineChartData = new Button("Sample 1");
		btSampleLineChartDataV2 = new Button("Sample 2");
		btSampleLineChart = new Button("Sample Line Chart");
		lbSampleDataTable = new Label("DataTable: empty");
		btSelectFile = new Button("Select DataSet");
		btGenerateChart = new Button("Transfer to Chart");
		LoadProject = new Button("LoadProject");
		SaveProject = new Button("SaveProject");
		btSplitColumn_fixedWidth = new Button("Split Column(fixedWidth)");
		btSplitColumn_delimiter = new Button("Split Column(delimiter)");
		btSplitTable = new Button("Split Table");


		// Layout the UI components

		DataSetList =  new ListView<>(FXCollections.observableArrayList()); 
		for(int i=0; i<=dataTableList.size()-1;i++) {
			DataSetList.setItems(FXCollections.observableArrayList(dataTableName));
		}

		ChartList =  new ListView<>(FXCollections.observableArrayList());
		for(int i=0; i<=charName.size()-1;i++) {
			ChartList.setItems(FXCollections.observableArrayList(charName));
		}



		DataSetList.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				ColumnList.getItems().clear();
				sampleDataTable = dataTableList.get(new_value.intValue());
				//System.out.println(sampleDataTable.getNumCol());
				//        	  lbSampleDataTable.setText(ov.getValue().toString());
				lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
						sampleDataTable.getNumCol()));
				populateSampleDataTableValuesToChart("Sample2");
				//DataTemp = ov.getValue().toString();
				ColumnName.clear();
				Set<String> set = sampleDataTable.getDC().keySet();
				for(String s : set) {
					s =  s+"    "+"<"+sampleDataTable.getCol(s).getTypeName().substring(10, sampleDataTable.getCol(s).getTypeName().length())+">";
					ColumnList.getItems().add(new CheckBox(s));
					ColumnName.add(s);
				}

				//ColumnList.getItems().add(new CheckBox("Second"));
				
			}
		});

		ColumnList.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
			}
		});


		ChartList.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				Chart chart =  ChartObject.get(new_value.intValue());
				if(chart instanceof BarChart_) {
					BarChart_UI chart1UI = new BarChart_UI();
					chart1UI.btLineChartBackMain = btLineChartBackMain;
					tempdta = DTALIST.get(new_value.intValue());
					System.out.println(DTALIST.get(new_value.intValue()));
					chartuibc = chart1UI;	
					scenes[SCENE_BAR_CHART] = new Scene(chartuibc.paneBarChartScreen("X", "y", "HELLO"), 800, 600); 
					SCENE_INDEX = SCENE_BAR_CHART;
					
				}
				else  if(chart instanceof ScatterChart_) {
//					ScatterChart_  chart1  = (ScatterChart_)ChartObject.get(new_value.intValue());
//					scenes[SCENE_SCATTER_CHART] = new Scene(chart1.paneChart("X", "y", "HELLO"), 800, 600); 
//					chart1.populateDataToChart();
//					SCENE_INDEX = SCENE_SCATTER_CHART;
//					chart1.getSC();
				}


//				lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
//						sampleDataTable.getNumCol()));
//				//       	  populateSampleDataTableValuesToChart(DataTemp);

			}
		});

		if(SCENE_INDEX == SCENE_BAR_CHART) {
			chartbc.populateDataToChart();
			
		}

		//		HBox hc = new HBox(20);
		//		hc.setAlignment(Pos.CENTER);
		//		hc.getChildren().addAll(btSampleLineChartData, btSampleLineChartDataV2);
		//		Button x = new Button("x");
		//		System.out.println("string");
		//		for (int i = 0; i < buttonList.size(); i++) {	
		//			hc.getChildren().addAll(buttonList.get(i));
		//			System.out.println(buttonList.get(i));
		//		}

		HBox data = new HBox(DataSetList);
		data.setAlignment(Pos.CENTER);
		HBox chart = new HBox(ChartList);
		chart.setAlignment(Pos.CENTER);
		HBox column = new HBox(ColumnList);
		chart.setAlignment(Pos.CENTER);


		HBox hc = new HBox(data, column,chart);
		//hc.setAlignment(Pos.CENTER);
		//hc.getChildren().addAll(DataSetList, ChartList);


		HBox hc2 = new HBox(10);
		hc2.setAlignment(Pos.CENTER);
		hc2.getChildren().addAll(btSelectFile, LoadProject, SaveProject);

		HBox hc3 = new HBox(10);
		hc3.setAlignment(Pos.CENTER);
		hc3.getChildren().addAll(btSplitTable, btSplitColumn_delimiter, btSplitColumn_fixedWidth);

		HBox hc4 = new HBox(10);
		hc4.setAlignment(Pos.CENTER);
		hc4.getChildren().addAll(btSampleLineChart, btGenerateChart);

		VBox container = new VBox(20);
		container.getChildren().addAll(lbMainScreenTitle, hc, lbSampleDataTable, new Separator(), hc4, hc3, new Separator(), hc2);
		container.setAlignment(Pos.CENTER);


		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply style to the GUI components
		btSampleLineChart.getStyleClass().add("menu-button");
		lbMainScreenTitle.getStyleClass().add("menu-title");
		pane.getStyleClass().add("screen-background");

		return pane;
	}

	/**
	 * This method is used to pick anyone of the scene on the stage. It handles the
	 * hide and show order. In this application, only one active scene should be
	 * displayed on stage.
	 * 
	 * @param sceneID
	 *            - The sceneID defined above (see SCENE_XXX)
	 */
	private void putSceneOnStage(int sceneID) {

		// ensure the sceneID is valid
		if (sceneID < 0 || sceneID >= SCENE_NUM)
			return;
		stage.hide();
		stage.setTitle(SCENE_TITLES[sceneID]);
		stage.setScene(scenes[sceneID]);
		stage.setResizable(true);
		stage.show();
	}

	/**
	 * All JavaFx GUI application needs to override the start method You can treat
	 * it as the main method (i.e. the entry point) of the GUI application
	 */
	@Override
	public void start(Stage primaryStage) {


		try {

			stage = primaryStage; // keep a stage reference as an attribute
			initScenes(); // initialize the scenes
			initEventHandlers(); // link up the event handlers
			putSceneOnStage(SCENE_MAIN_SCREEN); // show the main screen

		} catch (Exception e) {

			e.printStackTrace(); // exception handling: print the error message on the console
		} 



	}


	/**
	 * main method - only use if running via command line
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}