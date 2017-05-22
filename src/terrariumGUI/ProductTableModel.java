package terrariumGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import database.Store;

public class ProductTableModel extends AbstractTableModel implements Observer {
	private Store store;
	private String[] COLUMN_NAMES = new String[] { "#", "Product ID", "Name", "Quantity", "Total", "Cancel" };
	private List<ProductLine> productLine = new ArrayList<ProductLine>();

	public ProductTableModel(List<ProductLine> productLines) {
		this.productLine = productLines;
	}

	@Override
	public int getRowCount() {
		return this.productLine.size();
	}

	@Override
	public int getColumnCount() {
		return this.COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex > getRowCount()) {
			return null;
		}
		if (columnIndex < 0 || columnIndex > this.COLUMN_NAMES.length) {
			return null;
		}
		ProductLine line = this.productLine.get(rowIndex);
		switch (columnIndex) {
		case 0: {
			// return line.getUser();
		}
		case 1: {
			return line.getId();
		}
		case 2: {
			return line.getName();
		}
		case 3:
			return line.getPrice();
		default:
			return "";
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
