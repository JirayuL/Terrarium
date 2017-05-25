package controller;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Abstract class for handling the TableAction.
 * 
 * @author Jirayu Laungwilawan
 * @version 23.5.17
 * @param <T>
 *            is the sub class of the JTable.
 * @param <M>
 *            is the sub class of the TableModel
 */
public abstract class AbstractTableAction<T extends JTable, M extends TableModel> extends AbstractAction {

	/**
	 * First version of the TerrariumGUI.
	 */
	private static final long serialVersionUID = 1L;
	private T table;
	private M model;

	/**
	 * Initialize the constructor.
	 * 
	 * @param table
	 *            that extends JTable
	 * @param model
	 *            that extends TableModel
	 */
	public AbstractTableAction(T table, M model) {
		this.table = table;
		this.model = model;
	}

	/**
	 * Return table that extends JTable.
	 * 
	 * @return table
	 */
	public T getTable() {
		return table;
	}

	/**
	 * Return model that extends TableModel.
	 * 
	 * @return model
	 */
	public M getModel() {
		return model;
	}

}
