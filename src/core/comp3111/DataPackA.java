package core.comp3111;

import java.util.ArrayList;

public class DataPackA implements java.io.Serializable{
	public ArrayList<DataTable> chartList;
	public ArrayList<String> charName;
	public ArrayList<String> path;
	
	public DataPackA(ArrayList<DataTable> ctl, ArrayList<String> cn, ArrayList<String> p) {
		chartList = ctl; 
		charName = cn; 
		path = p;
	} 
	
}
