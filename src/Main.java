import java.awt.EventQueue;

import application.CashierMachine;
import terrariumGUI.PaymentGUI;
import terrariumGUI.TerrariumGUI;

public class Main {
	public static void main(String[] args) {
		CashierMachine cashier = CashierMachine.getInstance();
		PaymentGUI paymentGUI = PaymentGUI.getInstance();
		TerrariumGUI terrariumGUI = TerrariumGUI.getInstance();
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