import java.awt.EventQueue;

import application.CashierMachine;
import terrariumGUI.PaymentGUI;
import terrariumGUI.TerrariumGUI;

/**
 * Main class for run, show main page of Terrarium application
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class Main {
	public static void main(String[] args) {
		CashierMachine cashier = CashierMachine.getInstance();
		TerrariumGUI terrariumGUI = TerrariumGUI.getInstance();
		PaymentGUI paymentGUI = PaymentGUI.getInstance();
		cashier.addObserver(terrariumGUI);
		cashier.addObserver(paymentGUI);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					terrariumGUI.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}