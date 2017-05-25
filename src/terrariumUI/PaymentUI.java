package terrariumUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CashierMachine;
import database.Sales;

/**
 * PaymentGUI is a GUI of calculate a total and input a cash customer
 * 
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class PaymentUI extends JFrame implements Observer {
	/** Define the null Object */
	private static final PaymentUI NOOP = null;
	/** Define the PaymentUI */
	private static PaymentUI instance = NOOP;
	private CashierMachine cashier;
	private JFrame frame;
	private JTextField textCash;
	private JButton button1Baht, button2Baht, button5Baht, button10Baht, button20Baht, button50Baht, button100Baht,
			button500Baht, button1000Baht, btnEnter;
	private Image img1Baht, img2Baht, img5Baht, img10Baht, img20Baht, img50Baht, img100Baht, img500Baht, img1000Baht;
	private Image resize1Baht, resize2Baht, resize5Baht, resize10Baht, resize20Baht, resize50Baht, resize100Baht,
			resize500Baht, resize1000Baht;
	private JLabel lblSubTotal, lblVat, lblTotal, lblCash, lblShowSubTotal, lblShowVAT, lblShowTotal;
	private double oneBaht = 1, twoBaht = 2, fiveBaht = 5, tenBaht = 10, twentyBaht = 20, fiftyBaht = 50,
			hundredBaht = 100, fivehundredBaht = 500, thousandBaht = 1000;
	private JButton btnCancel;

	/**
	 * Constructor of the application.
	 */
	public PaymentUI() {
		this.cashier = CashierMachine.getInstance();
		try {
			initialize();
		} catch (IOException e) {

		}
	}

	/**
	 * Get all the information of the PaymentGUI.
	 * 
	 * @return the PaymentGUI.
	 */
	public static PaymentUI getInstance() {
		if (instance == null)
			instance = new PaymentUI();
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(700, 30, 680, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		button1Baht = new JButton();
		button1Baht.setBounds(10, 10, 75, 70);
		img1Baht = ImageIO.read(getClass().getResource("/image/1coin.png"));
		resize1Baht = img1Baht.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		button1Baht.setIcon(new ImageIcon(resize1Baht));
		button1Baht.addActionListener((e) -> {
			this.cashier.addCash(oneBaht);
		});
		frame.getContentPane().add(button1Baht);

		button2Baht = new JButton();
		button2Baht.setBounds(97, 10, 75, 70);
		img2Baht = ImageIO.read(getClass().getResource("/image/2coin.png"));
		resize2Baht = img2Baht.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		button2Baht.setIcon(new ImageIcon(resize2Baht));
		button2Baht.addActionListener((e) -> {
			this.cashier.addCash(twoBaht);
		});
		frame.getContentPane().add(button2Baht);

		button5Baht = new JButton();
		button5Baht.setBounds(184, 10, 75, 70);
		img5Baht = ImageIO.read(getClass().getResource("/image/5coin.png"));
		resize5Baht = img5Baht.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		button5Baht.setIcon(new ImageIcon(resize5Baht));
		button5Baht.addActionListener((e) -> {
			this.cashier.addCash(fiveBaht);
		});
		frame.getContentPane().add(button5Baht);

		button10Baht = new JButton();
		button10Baht.setBounds(271, 10, 75, 70);
		img10Baht = ImageIO.read(getClass().getResource("/image/10coin.png"));
		resize10Baht = img10Baht.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		button10Baht.setIcon(new ImageIcon(resize10Baht));
		button10Baht.addActionListener((e) -> {
			this.cashier.addCash(tenBaht);
		});
		frame.getContentPane().add(button10Baht);

		button20Baht = new JButton();
		button20Baht.setBounds(10, 85, 162, 80);
		img20Baht = ImageIO.read(getClass().getResource("/image/20bank.png"));
		resize20Baht = img20Baht.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		button20Baht.setIcon(new ImageIcon(resize20Baht));
		button20Baht.addActionListener((e) -> {
			this.cashier.addCash(twentyBaht);
		});
		frame.getContentPane().add(button20Baht);

		button50Baht = new JButton();
		button50Baht.setBounds(184, 85, 162, 80);
		img50Baht = ImageIO.read(getClass().getResource("/image/50bank.png"));
		resize50Baht = img50Baht.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		button50Baht.setIcon(new ImageIcon(resize50Baht));
		button50Baht.addActionListener((e) -> {
			this.cashier.addCash(fiftyBaht);
		});
		frame.getContentPane().add(button50Baht);

		button100Baht = new JButton();
		button100Baht.setBounds(10, 170, 162, 80);
		img100Baht = ImageIO.read(getClass().getResource("/image/100bank.png"));
		resize100Baht = img100Baht.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		button100Baht.setIcon(new ImageIcon(resize100Baht));
		button100Baht.addActionListener((e) -> {
			this.cashier.addCash(hundredBaht);
		});
		frame.getContentPane().add(button100Baht);

		button500Baht = new JButton();
		button500Baht.setBounds(184, 170, 162, 80);
		img500Baht = ImageIO.read(getClass().getResource("/image/500bank.png"));
		resize500Baht = img500Baht.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		button500Baht.setIcon(new ImageIcon(resize500Baht));
		button500Baht.addActionListener((e) -> {
			this.cashier.addCash(fivehundredBaht);
		});
		frame.getContentPane().add(button500Baht);

		button1000Baht = new JButton();
		button1000Baht.setBounds(77, 255, 210, 90);
		img1000Baht = ImageIO.read(getClass().getResource("/image/1000bank.png"));
		resize1000Baht = img1000Baht.getScaledInstance(200, 80, Image.SCALE_SMOOTH);
		button1000Baht.setIcon(new ImageIcon(resize1000Baht));
		button1000Baht.addActionListener((e) -> {
			this.cashier.addCash(thousandBaht);
		});
		frame.getContentPane().add(button1000Baht);

		lblSubTotal = new JLabel("SUB TOTAL");
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblSubTotal.setBounds(358, 26, 124, 46);
		frame.getContentPane().add(lblSubTotal);

		lblVat = new JLabel("VAT");
		lblVat.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblVat.setBounds(358, 85, 120, 46);
		frame.getContentPane().add(lblVat);

		lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblTotal.setBounds(358, 143, 120, 47);
		frame.getContentPane().add(lblTotal);

		lblCash = new JLabel("CASH");
		lblCash.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblCash.setBounds(358, 202, 120, 38);
		frame.getContentPane().add(lblCash);

		lblShowSubTotal = new JLabel("0.00");
		lblShowSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShowSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblShowSubTotal.setBounds(490, 33, 162, 35);
		frame.getContentPane().add(lblShowSubTotal);

		lblShowVAT = new JLabel("0.00");
		lblShowVAT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShowVAT.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblShowVAT.setBounds(490, 92, 162, 35);
		frame.getContentPane().add(lblShowVAT);

		lblShowTotal = new JLabel("0.00");
		lblShowTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShowTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblShowTotal.setBounds(490, 150, 162, 35);
		frame.getContentPane().add(lblShowTotal);

		textCash = new JTextField("0.00");
		textCash.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		textCash.setHorizontalAlignment(SwingConstants.RIGHT);
		textCash.setBounds(490, 202, 173, 36);
		textCash.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				cashier.setCash(0);
			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
		frame.getContentPane().add(textCash);

		btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEnter.setBounds(482, 265, 180, 70);
		btnEnter.addActionListener((e) -> {
			cashier.setCash(Double.parseDouble(textCash.getText()));
			if (Sales.getInstance().insertSaleToDatabase(TerrariumUI.getInstance().getSaleMap()) && cashier.getCash() >= cashier.getTotal()) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChangeUI changeGUI = ChangeUI.getInstance();
							changeGUI.run();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Insufficient fund.");
			}
		});
		frame.getContentPane().add(btnEnter);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnCancel.setBounds(353, 265, 110, 70);
		btnCancel.addActionListener((e) -> {
			close();
			cashier.setCash(0);
		});
		frame.getContentPane().add(btnCancel);

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
	 * Update of all label, when cashierMachine change
	 */
	@Override
	public void update(Observable subject, Object info) {
		if (info != null)
			System.out.println(info);
		if (subject instanceof CashierMachine) {
			CashierMachine cashierMachine = (CashierMachine) subject;
			lblShowSubTotal.setText(String.format("%.2f", cashierMachine.getSubtotal()));
			lblShowVAT.setText(String.format("%.2f", cashierMachine.getVat()));
			lblShowTotal.setText(String.format("%.2f", cashierMachine.getTotal()));
			textCash.setText(String.format("%.2f", cashierMachine.getCash()));
		}
	}
}
