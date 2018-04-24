package core.comp3111;

import java.util.ArrayList;

public class DataPack implements java.io.Serializable{

	public ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
	public ArrayList<DataTable> chartList = new ArrayList<DataTable>();
	
	public DataPack(ArrayList<DataTable> dtl, ArrayList<DataTable> ctl) {
		dtl = dataTableList;
		ctl = chartList;
	}
}
