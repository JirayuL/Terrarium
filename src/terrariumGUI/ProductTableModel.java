package terrariumGUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import database.Store;

public class ProductTableModel extends AbstractTableModel {
	private Store store;
	private String[] COLUMN_NAMES = new String[] { "Product ID", "Name", "Quantity", "Total", "Cancel" };
	private List<ProductLine> productLine = new ArrayList<ProductLine>();

	@Override
	public int getRowCount() {
		return this.productLine.size();
	}

	@Override
	public int getColumnCount() {
		return this.COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return String.class;
		}
		case 1: {
			return String.class;
		}
		case 2: {
			return Integer.class;
		}
		case 3: {
			return Double.class;
		}
		case 4: {
			return JButton.class;
		}
		default:
			return String.class;
		}
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
			return line.getId();
		}
		case 1: {
			return line.getName();
		}
		case 2: {
			return line.getPrice();
		}
		case 3:
			return line.getPrice();
		default:
			return "";
		}
	}

}
