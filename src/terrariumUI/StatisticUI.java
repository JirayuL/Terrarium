package terrariumUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class StatisticUI {

	private JFrame frame;
	//add JPanel to the contentPane
	private JPanel contentPane;
	//declare variable
	private JTextField txtDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticUI window = new StatisticUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatisticUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Statistic");
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 105, 438, 164);
		frame.getContentPane().add(scrollPane);

		JLabel lblStatistic = new JLabel("Statistic");
		lblStatistic.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblStatistic.setBounds(207, 6, 88, 42);
		frame.getContentPane().add(lblStatistic);

		JLabel lblRevenueAtDate = new JLabel("Revenue at date");
		lblRevenueAtDate.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblRevenueAtDate.setBounds(27, 77, 147, 16);
		frame.getContentPane().add(lblRevenueAtDate);


		//create text field
		txtDate = new JTextField();
		txtDate.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		//set bounds of text field
		txtDate.setBounds(186, 73, 129, 20);
		//add text field to contentPane
		frame.getContentPane().add(txtDate);
		//set columns
		txtDate.setColumns(20);

		//create button and there object
		JButton btnNewButton = new JButton("...");
		//perform action listener
		btnNewButton.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date 
				txtDate.setText(new DatePickerUI(f).setPickedDate());
			}
		});
		//set button bound
		btnNewButton.setBounds(327, 70, 27, 23);
		//add button in contentPane
		frame.getContentPane().add(btnNewButton);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblTotal.setBounds(51, 281, 71, 34);
		frame.getContentPane().add(lblTotal);

		JLabel label = new JLabel("0.00");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label.setBounds(148, 281, 206, 30);
		frame.getContentPane().add(label);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		btnClose.setBounds(366, 281, 99, 34);
		frame.getContentPane().add(btnClose);
	}
}
