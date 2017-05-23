package terrariumGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.CashierMachine;

import java.awt.Font;
import javax.swing.JButton;
/**
 * 
 * @author PaiizZ
 *
 */
public class ChangeGUI {
	private CashierMachine cashier;
	private JFrame frame;
	private JLabel lblChange, lblShowChange;
	private JButton btnEnd;
	private static ChangeGUI instance;

	/**
	 * Create the application.
	 * @param cashier 
	 * @param runnable 
	 */
	public ChangeGUI() {
		this.cashier = CashierMachine.getInstance();
		initialize();
	}
	
	public static ChangeGUI getInstance(){
		if(instance == null ) instance = new ChangeGUI();
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 300, 330, 280);
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
			
			PaymentGUI.getInstance().close();
			TerrariumGUI gui = TerrariumGUI.getInstance();
			gui.clearAllTable();
			cashier.setSubtotal(0);
			close();
		});
		frame.getContentPane().add(btnEnd);
	}
	
	public void run(){
		frame.setVisible(true);
	}
	
	public void close(){
		frame.setVisible(false);
	}
}
