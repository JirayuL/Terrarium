package terrariumGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import application.CashierMachine;
import application.ProductLine;
import database.Store;

import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTable;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class TerrariumGUI extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private static TerrariumGUI instance;
	private Store store;
	private JFrame frame;
	private JTextField idField, quantityField;
	private JTable table;
	private JLabel labelSubtotal;
	private PaymentGUI paymentGUI;
	private List<ProductLine> productLine = new ArrayList<ProductLine>();
	private CashierMachine cashier;
	private int number = 0;
	private JToolBar toolBar;
	private DefaultTableModel dmodel;
	private JButton clearAll;
	private JButton deleteSelected;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public TerrariumGUI() {
		this.store = Store.getInstance();
		this.cashier = CashierMachine.getInstance();
		this.paymentGUI = PaymentGUI.getInstance();
		initComponents();
	}
	
	public static TerrariumGUI getInstance(){
		if(instance == null ) instance = new TerrariumGUI();
		return instance;
	}

	public void run() {
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Terrarium");
		frame.setResizable(false);
		frame.setBounds(30, 30, 700, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Product ID :");
		lblNewLabel.setBounds(41, 27, 74, 16);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel);

		idField = new JTextField();
		idField.setBounds(142, 22, 130, 26);
		panel.add(idField);
		idField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(305, 27, 62, 16);
		panel.add(lblQuantity);

		quantityField = new JTextField("1");
		quantityField.setBounds(379, 22, 130, 26);
		panel.add(quantityField);
		quantityField.setColumns(10);
		quantityField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// Do nothing
			}

			@Override
			public void focusGained(FocusEvent e) {
				quantityField.setText("");
			}
		});

		dmodel = new DefaultTableModel();
		initTable(dmodel);

		table = new JTable();
		table.setModel(dmodel);

		JScrollPane scPane = new JScrollPane(table);
		scPane.setBounds(41, 60, 609, 222);
		panel.add(scPane);

		JLabel lblNewLabel_1 = new JLabel("TOTAL");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(42, 301, 159, 77);
		panel.add(lblNewLabel_1);

		JButton btnCheckout = new JButton("Check Out");
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnCheckout.addActionListener((e) -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						paymentGUI.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		btnCheckout.setBounds(506, 294, 144, 78);
		panel.add(btnCheckout);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener((e) -> {
			String id = idField.getText();
			if (InMap(id) && quantityField != null) {
				int qty = Integer.parseInt(quantityField.getText());
				double getPrice = Double.parseDouble(store.getProductMap().get(id).get(1));
				String name = store.getProductMap().get(id).get(0);
				productLine.add(new ProductLine(Integer.parseInt(id), name, getPrice));
				cashier.add(getPrice, qty);
				dmodel.addRow(new String[] { String.format("%d", ++number), id, name, String.format("%d", qty),
						String.format("%.2f", getPrice * qty) });
			}
			idField.setText("");
			quantityField.setText("1");
		});
		btnAdd.setBounds(533, 22, 117, 29);
		panel.add(btnAdd);

		labelSubtotal = new JLabel("0.00");
		labelSubtotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		labelSubtotal.setBounds(213, 301, 239, 77);
		labelSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelSubtotal);

		deleteSelected = new JButton("Delete selected rows");
		deleteSelected.addActionListener(new DeleteRowFromTableAction(table, dmodel));

		clearAll = new JButton("Clear all");
		clearAll.addActionListener((e) -> {
			clearAllTable();
			this.cashier.setSubtotal(0);
		});

		toolBar = new JToolBar();
		toolBar.add(clearAll);
		toolBar.add(deleteSelected);
		frame.add(toolBar, BorderLayout.NORTH);

	}

	private void initTable(DefaultTableModel dmodel) {
		String[] COLUMN_NAMES = new String[] { "#", "Product ID", "Name", "Quantity", "Total" };
		for (String string : COLUMN_NAMES)
			dmodel.addColumn(string);
	}

	private boolean InMap(String id) {
		return store.getProductMap().containsKey(id);
	}

	public void clearAllTable() {
		int rowCount = dmodel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dmodel.removeRow(i);
		}
	}

	@Override
	public void update(Observable subject, Object info) {
		if (info != null)
			System.out.println(info);
		if (subject instanceof CashierMachine) {
			CashierMachine cashierMachine = (CashierMachine) subject;
			labelSubtotal.setText(String.format("%.2f", cashierMachine.getSubtotal()));
			// labelSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		}
	}

	public abstract class AbstractTableAction<T extends JTable, M extends TableModel> extends AbstractAction {

		private T table;
		private M model;

		public AbstractTableAction(T table, M model) {
			this.table = table;
			this.model = model;
		}

		public T getTable() {
			return table;
		}

		public M getModel() {
			return model;
		}

	}

	public class DeleteRowFromTableAction extends AbstractTableAction<JTable, DefaultTableModel> {

		public DeleteRowFromTableAction(JTable table, DefaultTableModel model) {
			super(table, model);
			putValue(NAME, "Delete selected rows");
			putValue(SHORT_DESCRIPTION, "Delete selected rows");
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
				List<Vector> selectedRows = new ArrayList<>(25);
				DefaultTableModel model = getModel();
				Vector rowData = model.getDataVector();
				for (int row : table.getSelectedRows()) {
					int modelRow = table.convertRowIndexToModel(row);
					Vector rowValue = (Vector) rowData.get(modelRow);
					selectedRows.add(rowValue);
				}

				for (Vector rowValue : selectedRows) {
					int rowIndex = rowData.indexOf(rowValue);
					cashier.subtractSubtotal(Double.parseDouble(model.getValueAt(rowIndex, 4)+""));
					model.removeRow(rowIndex);
				}
			}
		}

	}
}
