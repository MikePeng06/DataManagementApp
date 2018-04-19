package core.comp3111;

public class BarChart {
	// Defualt Constructor
	public BarChart() {
		DataColumn numCol = new DataColumn();
		DataColumn textCol = new DataColumn();
	}
	
	// Conversion Constructor
	public BarChart(DataTable dataset) {
		int numKey = dataset.getNumCol();
		DataColumn[] dataColumns = new DataColumn[numKey];
		int i = 0;
		for (Object key:dataset.getDC().keySet()) {
			String temp = (String) dataset.getDC().get(key);
			dataColumns[i] = dataset.getCol(temp);
			i++;
		}
		
	}
	
}

	