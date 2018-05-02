package core.comp3111;

public class ModifiedDT implements java.io.Serializable {
	
	public String typename;
	public DataTable datatable;
	
	public ModifiedDT(String str, DataTable dt) {
		this.typename = str;
		this.datatable = dt;
	}
	
	
}
