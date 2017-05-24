package terrariumGUI;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import application.CashierMachine;

/**
 * Class for delete selected item action of the table to perform.
 * 
 * @author Jirayu Laungwilawan
 * @version 23.5.17
 */
public class DeleteRowFromTableAction extends AbstractTableAction<JTable, DefaultTableModel> {

	/**
	 * First version of the TerrariumGUI.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the constructor.
	 * 
	 * @param table
	 * @param model
	 */
	public DeleteRowFromTableAction(JTable table, DefaultTableModel model) {
		super(table, model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setEnabled(getTable().getSelectedRowCount() > 0);
			}
		});
		setEnabled(getTable().getSelectedRowCount() > 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTable table = getTable();
		if (table.getSelectedRowCount() > 0) {
			List<Vector<?>> selectedRows = new ArrayList<>(25);
			DefaultTableModel model = getModel();
			Vector<?> rowData = model.getDataVector();
			for (int row : table.getSelectedRows()) {
				int modelRow = table.convertRowIndexToModel(row);
				Vector<?> rowValue = (Vector<?>) rowData.get(modelRow);
				selectedRows.add(rowValue);
			}

			for (Vector<?> rowValue : selectedRows) {
				int rowIndex = rowData.indexOf(rowValue);
				CashierMachine.getInstance().subtractSubtotal(Double.parseDouble(model.getValueAt(rowIndex, 4) + ""));
				model.removeRow(rowIndex);
			}
		}
		TerrariumGUI instance = TerrariumGUI.getInstance();
		instance.updateNumber();
		instance.setNumbertoZero();
	}

}