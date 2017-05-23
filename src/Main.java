import application.CashierMachine;
import database.Store;
import terrariumGUI.PaymentGUI;
import terrariumGUI.TerrariumGUI;

public class Main {
	public static void main(String[] args) {
		Store store = Store.getInstance();
		CashierMachine cashier = new CashierMachine();
		PaymentGUI paymentGUI = new PaymentGUI(cashier);
		TerrariumGUI terrariumGUI = new TerrariumGUI(store, cashier, paymentGUI);
		cashier.addObserver(terrariumGUI);
		cashier.addObserver(paymentGUI);
		terrariumGUI.run();
	}
}