package application;

import java.util.Observable;

public class CashierMachine extends Observable {
	private final double VAT = 0.07;
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

	public double getSubtotal() {
		return subtotal;
	}
	
	public void subtractSubtotal(double money) {
		this.subtotal -= money;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}

	public double getTotal() {
		return total;
	}

	public double getVat() {
		return vat;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
		setChanged();
		notifyObservers();
	}

	public void addCash(double cash) {
		this.cash += cash;
		setChanged();
		notifyObservers();
	}

	private void calculateTotal() {
		total = subtotal + vat;
	}

	public void add(double price, int qty) {
		subtotal += price * qty;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}

}
