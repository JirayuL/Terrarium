package terrariumGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import application.CashierMachine;
import database.Store;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class TerrariumGUI extends JFrame implements Observer {

	private Store store;
	private JFrame frame;
	private JTextField idField, quantityField;
	private JTable table;
	private JLabel labelSubtotal;
	private PaymentGUI paymentGUI;
	// private List<ProductLine> productLine;
	private Map<Integer, ProductLine> productMap;
	private CashierMachine cashier;
	private DefaultTableModel dmodel;
	private int number = 0;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public TerrariumGUI(Store store, CashierMachine cashier, PaymentGUI paymentGUI) throws IOException {
		this.store = store;
		this.cashier = cashier;
		// productLine = new ArrayList<ProductLine>();
		productMap = new HashMap<Integer, ProductLine>();
		paymentGUI = new PaymentGUI(cashier);
		this.paymentGUI = paymentGUI;
		initComponents();
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
		frame.setBounds(30, 30, 700, 400);
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
		table.setEnabled(false);

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
			paymentGUI.run();
		});
		btnCheckout.setBounds(506, 294, 144, 78);
		panel.add(btnCheckout);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener((e) -> {
			String id = idField.getText();
			if (InMap(id) && quantityField != null) {
				number += 1;
				int qty = Integer.parseInt(quantityField.getText());
				double getPrice = Double.parseDouble(store.getProductMap().get(id).get(1));
				String name = store.getProductMap().get(id).get(0);
				// productLine.add(new ProductLine(Integer.parseInt(id), name,
				// getPrice));
				productMap.put(number, new ProductLine(Integer.parseInt(id), name, getPrice));
				cashier.add(getPrice, qty);
				dmodel.addRow(new String[] { String.format("%d", number), id, name, String.format("%d", qty),
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
	}

	private void initTable(DefaultTableModel dmodel) {
		String[] COLUMN_NAMES = new String[] { "#", "Product ID", "Name", "Quantity", "Total" };
		for (String string : COLUMN_NAMES)
			dmodel.addColumn(string);
	}

	private boolean InMap(String id) {
		return store.getProductMap().containsKey(id);
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
}
