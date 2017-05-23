package application;

import java.util.List;
import java.util.Observable;

public class CashierMachine extends Observable {
	private final double VAT = 0.007;
	private double subtotal;
	private double total;
	private double vat;
	private double cash;

	/**
	 * Initialize a
	 */
	public CashierMachine() {
		subtotal = 0;
		total = 0;
		vat = 0;
		cash = 0;
	}

	private void calculateVAT() {
		vat = subtotal * VAT;
	}

	private void calculateTotal() {
		total = subtotal + VAT;
	}

	public void add(double price, int qty) {
		subtotal += price * qty;
//		calculateVAT();
//		calculateTotal();
//		setChanged();
//		notifyObservers();
	}

}
