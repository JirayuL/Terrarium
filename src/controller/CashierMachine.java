package controller;

import java.util.Observable;

/**
 * CashierMachine class for calculate money of customer
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class CashierMachine extends Observable {
	private static CashierMachine instance;
	/**
	 * VAT of Thailand
	 */
	private final double VAT = 0.07;
	private double subtotal, total, vat, cash, change;

	/**
	 * Initialize a attribute of CashierMachine.
	 */
	public CashierMachine() {
		subtotal = 0;
		total = 0;
		vat = 0;
		cash = 0;
	}

	/**
	 * Get all the information of the CashierMachine.
	 *
	 * @return CashierMachine
	 */
	public static CashierMachine getInstance() {
		if (instance == null)
			instance = new CashierMachine();
		return instance;
	}

	/**
	 * Calculate VAT of subtotal
	 */
	private void calculateVAT() {
		vat = subtotal * VAT;
	}

	/**
	 * Subtract subtotal of CashierMachine
	 * @param money is a money will subtract
	 */
	public void subtractSubtotal(double money) {
		this.subtotal -= money;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}

	/**
	 * Set subtotal to new subtotal
	 * @param subtotal is new subtotal for change.
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Get total money of cashier machine
	 * @return total of cashier machine
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * Get subtotal of CashierMachine.
	 * @return subtotal of CashierMachine.
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * Get vat of subtotal.
	 * @return vat of settotal.
	 */
	public double getVat() {
		return vat;
	}

	/**
	 * Get cash of customer that pay.
	 * @return cash of customer that pay.
	 */
	public double getCash() {
		return cash;
	}
	
	/**
	 * Get change to return customer
	 * @return change to return customer
	 */
	public double getChange() {
		calculateChange();
		return change;
	}

	/**
	 * Set cash of CashierMachine
	 * @param cash of customer that pay.
	 */
	public void setCash(double cash) {
		this.cash = cash;
		setChanged();
		notifyObservers();
	}

	/**
	 * Add cash to CashierMachine
	 * @param cash of customer 
	 */
	public void addCash(double cash) {
		this.cash += cash;
		setChanged();
		notifyObservers();
	}

	/**
	 * Calculate total to return customer
	 */
	private void calculateTotal() {
		total = subtotal + vat;
	}

	/**
	 * Calculate change to return customer
	 */
	private void calculateChange() {
		change = cash - total;
	}

	/**
	 * Add a price and quantity to calculate
	 * @param price is a price of product
	 * @param qty is a quantity of product
	 */
	public void add(double price, int qty) {
		subtotal += price * qty;
		calculateVAT();
		calculateTotal();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Reset all attribute
	 */
	public void resetCashierMachine() {
		subtotal = 0;
		total = 0;
		vat = 0;
		cash = 0;
		setChanged();
		notifyObservers();
	}
}
