package terrariumUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.CashierMachine;
import controller.DeleteRowFromTableAction;
import database.Store;

/**
 * TerrariumGUI class is a main page of Terrarium project
 * 
 * @author Wanchanapon Thanwaranurak, Jirayu Laungwilawan
 * @version 14.5.17
 */
public class TerrariumUI extends JFrame implements Observer {
	/**
	 * First version of the TerrariumGUI.
	 */
	private static final long serialVersionUID = 1L;
	private static TerrariumUI instance;
	private Store store;
	private JPanel panel;
	private JFrame frame;
	private JTextField idField, quantityField;
	private JTable table;
	private JLabel labelSubtotal, lblProductID, lblQuantity, lblTotal;
	private Map<String, Integer> saleMap;
	private CashierMachine cashier;
	private int number;
	private JToolBar toolBar;
	private DefaultTableModel dmodel;
	private JButton clearAll, deleteSelected, btnCheckout, btnAdd, btnStatistic;
	private JScrollPane scPane;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public TerrariumUI() {
		this.number = 0;
		this.store = Store.getInstance();
		this.cashier = CashierMachine.getInstance();
		this.saleMap = new HashMap<String, Integer>();
		initComponents();
	}

	/**
	 * Get all the information of the TerrariumGUI.
	 * 
	 * @return the TerrariumGUI
	 */
	public static TerrariumUI getInstance() {
		if (instance == null)
			instance = new TerrariumUI();
		return instance;
	}

	/**
	 * Set the visibility of the GUI to true.
	 */
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

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblProductID = new JLabel("Product ID :");
		lblProductID.setBounds(41, 27, 74, 16);
		lblProductID.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblProductID);

		idField = new JTextField();
		idField.setBounds(142, 22, 130, 26);
		panel.add(idField);
		idField.setColumns(10);

		lblQuantity = new JLabel("Quantity :");
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

		scPane = new JScrollPane(table);
		scPane.setBounds(41, 60, 609, 222);
		panel.add(scPane);

		lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(42, 301, 159, 77);
		panel.add(lblTotal);

		btnCheckout = new JButton("Check Out");
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnCheckout.addActionListener((e) -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						PaymentUI.getInstance().run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		btnCheckout.setBounds(506, 294, 144, 78);
		panel.add(btnCheckout);

		btnAdd = new JButton("ADD");
		btnAdd.addActionListener((e) -> {
			String id = idField.getText();
			if (InMap(id) && quantityField != null) {
				int qty = Integer.parseInt(quantityField.getText());
				double getPrice = Double.parseDouble(store.getProductMap().get(id).get(1));
				String name = store.getProductMap().get(id).get(0);
				cashier.add(getPrice, qty);
				if (saleMap.containsKey(id)) {
					qty += saleMap.get(id);
					updateTable(id);
				}
				saleMap.put(id, qty);
				dmodel.addRow(new String[] { String.format("%d", ++number), id, name, String.format("%d", qty),
						String.format("%.2f", getPrice * qty) });
			}
			updateNumber();
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

		btnStatistic = new JButton("Statistic");
		btnStatistic.addActionListener((e) -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StatisticUI.getInstance().run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});

		toolBar = new JToolBar();
		toolBar.add(clearAll);
		toolBar.add(deleteSelected);
		toolBar.add(btnStatistic);
		frame.add(toolBar, BorderLayout.NORTH);

	}

	/**
	 * Initialize the header of the table.
	 * 
	 * @param dmodel
	 *            is a TableModel to add the header.
	 */
	private void initTable(DefaultTableModel dmodel) {
		String[] COLUMN_NAMES = new String[] { "#", "Product ID", "Name", "Quantity", "Total" };
		for (String string : COLUMN_NAMES)
			dmodel.addColumn(string);
	}

	/**
	 * Check whether it in the map.
	 * 
	 * @param id
	 *            of the product.
	 * @return true if it's in the map otherwise false.
	 */
	private boolean InMap(String id) {
		return store.getProductMap().containsKey(id);
	}

	/**
	 * Clear all table model data.
	 */
	public void clearAllTable() {
		int rowCount = dmodel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dmodel.removeRow(i);
		}
		setNumbertoZero();
		updateNumber();
	}

	/**
	 * Set the number to zero.
	 */
	public void setNumbertoZero() {
		number = 0;
	}

	/**
	 * Update the data when add the same product.
	 * 
	 * @param id
	 *            of the product.
	 */
	private void updateTable(String id) {
		for (int i = 0; i < dmodel.getRowCount(); i++) {
			if (dmodel.getValueAt(i, 1).equals(id)) {
				dmodel.removeRow(i);
				break;
			}
		}
	}

	/**
	 * Clear all data in the map.
	 */
	public void clearMap() {
		saleMap.clear();
	}

	/**
	 * Update column # to be the actual one.
	 */
	public void updateNumber() {
		for (int i = 0; i < dmodel.getRowCount(); i++) {
			dmodel.setValueAt(i + 1, i, 0);
		}
	}

	/**
	 * Get sales of product
	 * 
	 * @return Map of sales
	 */
	public Map<String, Integer> getSaleMap() {
		return saleMap;
	}

	/**
	 * Update of labelSubtotal, when total change
	 */
	@Override
	public void update(Observable subject, Object info) {
		if (info != null)
			System.out.println(info);
		if (subject instanceof CashierMachine) {
			CashierMachine cashierMachine = (CashierMachine) subject;
			labelSubtotal.setText(String.format("%.2f", cashierMachine.getSubtotal()));
		}
	}
}
