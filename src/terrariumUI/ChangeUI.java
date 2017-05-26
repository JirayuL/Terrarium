package terrariumUI;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.CashierMachine;
import controller.ProductsSales;

/**
 * ChangeGUI is a GUI of show a change to return customer
 * 
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class ChangeUI implements Observer{
	/** Define the null Object */
	private static final ChangeUI NOOP = null;
	/** Define the ChangeUI */
	private static ChangeUI instance = NOOP;
	private CashierMachine cashier;
	private JFrame frame;
	private JLabel lblChange, lblShowChange;
	private JButton btnEnd;
	

	/**
	 * Create the application.
	 */
	public ChangeUI() {
		this.cashier = CashierMachine.getInstance();
		initialize();
	}

	/**
	 * Get all the information of the ChangeGUI.
	 * 
	 * @return the ChangeGUI
	 */
	public static ChangeUI getInstance() {
		if (instance == null)
			instance = new ChangeUI();
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 300, 330, 280);
		frame.setTitle("Change");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblChange.setBounds(105, 31, 113, 48);
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblChange);

		lblShowChange = new JLabel(String.format("%.2f", cashier.getChange()));
		lblShowChange.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblShowChange.setBounds(60, 110, 210, 48);
		lblShowChange.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblShowChange);

		btnEnd = new JButton("End");
		btnEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEnd.setBounds(95, 184, 129, 41);
		btnEnd.addActionListener((e) -> {
			PaymentUI.getInstance().close();
			TerrariumUI.getInstance().clearAllTable();
			TerrariumUI.getInstance().clearMap();
			cashier.resetCashierMachine();
			ProductsSales.getInstance().updateProductSales();
			close();
		});
		frame.getContentPane().add(btnEnd);
	}

	/**
	 * Set the visibility to true.
	 */
	public void run() {
		frame.setVisible(true);
	}

	/**
	 * Set the visibility to false.
	 */
	public void close() {
		frame.setVisible(false);
	}

	/**
	 * Update of lblShowChange, when change money change
	 */
	@Override
	public void update(Observable subject, Object info) {
		if (info != null)
			System.out.println(info);
		if (subject instanceof CashierMachine) {
			CashierMachine cashierMachine = (CashierMachine) subject;
			lblShowChange.setText(String.format("%.2f", cashierMachine.getChange()));
		}
	}
}
