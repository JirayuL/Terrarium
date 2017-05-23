package terrariumGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.CashierMachine;
import database.Store;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;

public class TerrariumGUI extends JFrame {

	private Store store;
	private JFrame frame;
	private JTextField idField;
	private JTextField quantityField;
	private List list;
	private JTable table;
	private PaymentGUI paymentGUI;
	private java.util.List<ProductLine> productLine = new ArrayList<ProductLine>();
	private CashierMachine cashier = new CashierMachine();

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public TerrariumGUI(Store store) throws IOException {
		this.store = store;
		paymentGUI = new PaymentGUI();
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
		frame.setBounds(100, 100, 700, 400);
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

		quantityField = new JTextField();
		quantityField.setBounds(379, 22, 130, 26);
		panel.add(quantityField);
		quantityField.setColumns(10);

		list = new List();
		list.setBounds(41, 60, 609, 222);
		panel.add(list);

		JLabel lblNewLabel_1 = new JLabel("TOTAL");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(41, 306, 159, 65);
		panel.add(lblNewLabel_1);

		JButton btnCheckout = new JButton("Check Out");
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnCheckout.addActionListener((e) -> {
			System.out.println("eiei");
			paymentGUI.run();
		});
		btnCheckout.setBounds(506, 294, 144, 78);
		panel.add(btnCheckout);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener((e) -> {
			String id = idField.getText();
			int qty = Integer.parseInt(quantityField.getText());
			if (InMap(id)) {
				double getPrice = Double.parseDouble(store.getProductMap().get(id).get(1));
				String name = store.getProductMap().get(id).get(0);
				productLine.add(new ProductLine(Integer.parseInt(id), name, getPrice));
				cashier.add(getPrice, qty);
			}
			idField.setText("");
			quantityField.setText("");
		});
		btnAdd.setBounds(533, 22, 117, 29);
		panel.add(btnAdd);
	}

	private boolean InMap(String id) {
		return store.getProductMap().containsKey(id);
	}

	private void putInList() {

	}
}
