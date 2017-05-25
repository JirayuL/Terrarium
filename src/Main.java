import java.awt.EventQueue;

import application.CashierMachine;
import terrariumUI.ChangeUI;
import terrariumUI.PaymentUI;
import terrariumUI.TerrariumUI;

/**
 * Main class for run, show main page of Terrarium application
 * 
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class Main {
	public static void main(String[] args) {
		CashierMachine cashier = CashierMachine.getInstance();
		TerrariumUI terrariumGUI = TerrariumUI.getInstance();
		PaymentUI paymentGUI = PaymentUI.getInstance();
		ChangeUI changeGUI = ChangeUI.getInstance();
		cashier.addObserver(terrariumGUI);
		cashier.addObserver(paymentGUI);
		cashier.addObserver(changeGUI);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					terrariumGUI.run();
				} catch (Exception e) {
					// DO nothing
				}
			}
		});
	}
}