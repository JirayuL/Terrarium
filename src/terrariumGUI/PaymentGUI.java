package terrariumGUI;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import application.CashierMachine;

public class PaymentGUI implements Observer{

	private JFrame frame;
	private JTextField textField;

	

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public PaymentGUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button1Baht = new JButton();
		button1Baht.setBounds(10, 10, 75, 70);
		Image img1Baht = ImageIO.read(getClass().getResource("/image/1coin.png"));
		Image resize1Baht = img1Baht.getScaledInstance( 60, 60,  Image.SCALE_SMOOTH ) ; 
		button1Baht.setIcon(new ImageIcon(resize1Baht));
		frame.getContentPane().add(button1Baht);
		
		JButton button2Baht = new JButton();
		button2Baht.setBounds(97, 10, 75, 70);
		Image img2Baht = ImageIO.read(getClass().getResource("/image/2coin.png"));
		Image resize2Baht = img2Baht.getScaledInstance( 60, 60,  Image.SCALE_SMOOTH ) ; 
		button2Baht.setIcon(new ImageIcon(resize2Baht));
		frame.getContentPane().add(button2Baht);
		
		JButton button5Baht = new JButton();
		button5Baht.setBounds(184, 10, 75, 70);
		Image img5Baht = ImageIO.read(getClass().getResource("/image/5coin.png"));
		Image resize5Baht = img5Baht.getScaledInstance( 70, 70,  Image.SCALE_SMOOTH ) ; 
		button5Baht.setIcon(new ImageIcon(resize5Baht));
		frame.getContentPane().add(button5Baht);
		
		JButton button10Baht = new JButton();
		button10Baht.setBounds(271, 10, 75, 70);
		Image img10Baht = ImageIO.read(getClass().getResource("/image/10coin.png"));
		Image resize10Baht = img10Baht.getScaledInstance( 60, 60,  Image.SCALE_SMOOTH ) ; 
		button10Baht.setIcon(new ImageIcon(resize10Baht));
		frame.getContentPane().add(button10Baht);
		
		JButton button20Baht = new JButton();
		button20Baht.setBounds(10, 85, 162, 80);
		Image img20Baht = ImageIO.read(getClass().getResource("/image/20bank.png"));
		Image resize20Baht = img20Baht.getScaledInstance( 150, 70,  Image.SCALE_SMOOTH ) ; 
		button20Baht.setIcon(new ImageIcon(resize20Baht));
		frame.getContentPane().add(button20Baht);
		
		JButton button50Baht = new JButton();
		button50Baht.setBounds(184, 85, 162, 80);
		Image img50Baht = ImageIO.read(getClass().getResource("/image/50bank.png"));
		Image resize50Baht = img50Baht.getScaledInstance( 150, 70,  Image.SCALE_SMOOTH ) ; 
		button50Baht.setIcon(new ImageIcon(resize50Baht));
		frame.getContentPane().add(button50Baht);
		
		JButton button100Baht = new JButton();
		button100Baht.setBounds(10, 170, 162, 80);
		Image img100Baht = ImageIO.read(getClass().getResource("/image/100bank.png"));
		Image resize100Baht = img100Baht.getScaledInstance( 150, 70,  Image.SCALE_SMOOTH ) ; 
		button100Baht.setIcon(new ImageIcon(resize100Baht));
		frame.getContentPane().add(button100Baht);
		
		JButton button500Baht = new JButton();
		button500Baht.setBounds(184, 170, 162, 80);
		Image img500Baht = ImageIO.read(getClass().getResource("/image/500bank.png"));
		Image resize500Baht = img500Baht.getScaledInstance( 150, 70,  Image.SCALE_SMOOTH ) ; 
		button500Baht.setIcon(new ImageIcon(resize500Baht));
		frame.getContentPane().add(button500Baht);
		
		JButton button1000Baht = new JButton();
		button1000Baht.setBounds(77, 255, 210, 90);
		Image img1000Baht = ImageIO.read(getClass().getResource("/image/1000bank.png"));
		Image resize1000Baht = img1000Baht.getScaledInstance( 200, 80,  Image.SCALE_SMOOTH ) ; 
		button1000Baht.setIcon(new ImageIcon(resize1000Baht));
		frame.getContentPane().add(button1000Baht);
		
		JLabel lblSubTotal = new JLabel("SUB TOTAL");
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblSubTotal.setBounds(358, 26, 124, 46);
		frame.getContentPane().add(lblSubTotal);
		
		JLabel lblVat = new JLabel("VAT");
		lblVat.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblVat.setBounds(358, 85, 120, 46);
		frame.getContentPane().add(lblVat);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblTotal.setBounds(358, 143, 120, 47);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblCash = new JLabel("CASH");
		lblCash.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblCash.setBounds(358, 202, 120, 38);
		frame.getContentPane().add(lblCash);
		
		JLabel lblNewLabel = new JLabel("0.00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel.setBounds(490, 33, 162, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("0.00");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel1.setBounds(490, 92, 162, 35);
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("0.00");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel2.setBounds(490, 150, 162, 35);
		frame.getContentPane().add(lblNewLabel2);
		
		textField = new JTextField("0.00");
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(490, 202, 173, 36);
		frame.getContentPane().add(textField);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEnter.setBounds(415, 265, 181, 70);
		btnEnter.addActionListener((e) -> {
			frame.setVisible(false);
		});
		frame.getContentPane().add(btnEnter);
		
	}
	
	public void run() {
		frame.setVisible(true);
	}

	@Override
	public void update(Observable subject, Object info) {
		if (info != null) System.out.println(info);
        if (subject instanceof CashierMachine) {
        	CashierMachine cashierMachine = (CashierMachine) subject;
//            labelBalance.setText((int) purse.getBalance() + " Baht");
        }
		
	}

	
}
