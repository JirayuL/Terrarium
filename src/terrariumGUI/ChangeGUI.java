package terrariumGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;

public class ChangeGUI {

	private JFrame frame;
	private JLabel lblChange, lblShowChange;
	private JButton btnEnd;

	/**
	 * Create the application.
	 */
	public ChangeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 330, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblChange.setBounds(110, 30, 113, 48);
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblChange);
		
		lblShowChange = new JLabel("0.00");
		lblShowChange.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblShowChange.setBounds(50, 110, 210, 48);
		lblShowChange.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblShowChange);
		
		btnEnd = new JButton("End");
		btnEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEnd.setBounds(94, 184, 129, 41);
		frame.getContentPane().add(btnEnd);
	}
	
	public void run(){
		frame.setVisible(true);
	}
}