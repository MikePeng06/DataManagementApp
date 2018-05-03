package core.comp3111;

import java.util.ArrayList;

public class DataPack implements java.io.Serializable{

	public ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
	public ArrayList<DataTable> chartList = new ArrayList<DataTable>();
	public ArrayList<String> dataTableName = new ArrayList<String>();
	public ArrayList<String> charName = new ArrayList<String>();
//	public ArrayList<DataTable> Chartdatatable = new ArrayList<DataTable>();
//	public  ArrayList<String> ChartType = new ArrayList<String>();
//	
	public DataPack(ArrayList<DataTable> dtl, ArrayList<DataTable> ctl, ArrayList<String> dtn, ArrayList<String> cn) {
		dataTableList = dtl;
		chartList = ctl;
		dataTableName = dtn;
		charName = cn;
//		Chartdatatable = cdt;
//		ChartType = ct;
	}
}
