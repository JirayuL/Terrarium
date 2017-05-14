
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TerrariumProject extends JFrame{

	private JFrame frame;
	private JTextField idField;
	private JTextField quantityField;
	private JList list;
	private static int number = 0;

	/**
	 * Create the application.
	 */
	public TerrariumProject() {
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
		frame.setBounds(100, 100, 700 , 400);
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

		list = new JList<>();
		list.setBounds(41, 60, 609, 222);
		panel.add(list);

		JLabel lblNewLabel_1 = new JLabel("TOTAL");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(41, 306, 159, 65);
		panel.add(lblNewLabel_1);

		JButton btnCheckout = new JButton("Check Out");
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckout.setBounds(506, 294, 144, 78);
		panel.add(btnCheckout);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener((e) -> {
			addToList(idField.getText(), quantityField.getText());
			idField.setText("");
			quantityField.setText("");
		});
		btnAdd.setBounds(533, 22, 117, 29);
		panel.add(btnAdd);
	}

	private void addToList(String id, String qty) {
		list.add(new JTable(1, 5));
	}
}
