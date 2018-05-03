package core.comp3111;

import java.util.ArrayList;

/**
 * The DataPack class of this GUI application
 * It is used for pack the needed data when save and load the project
 * (Do serialize and deserialize)
 * 
 * @author Zeyang Bao
 *
 */

public class DataPack implements java.io.Serializable{

	public ArrayList<DataTable> dataTableList = new ArrayList<DataTable>(); 
	public ArrayList<DataTable> chartList = new ArrayList<DataTable>();
	public ArrayList<String> dataTableName = new ArrayList<String>();
	public ArrayList<String> charName = new ArrayList<String>();
	public ArrayList<DataTableArray> DTALIST = new ArrayList<DataTableArray>();
	
	public DataPack(ArrayList<DataTable> dtl, ArrayList<DataTable> ctl, ArrayList<String> dtn, ArrayList<String> cn, ArrayList<DataTableArray> dl) {
		dataTableList = dtl; 
		chartList = ctl; 
		dataTableName = dtn;
		charName = cn; 
		DTALIST = dl;
//		Chartdatatable = cdt;
//		ChartType = ct;
	}
}
