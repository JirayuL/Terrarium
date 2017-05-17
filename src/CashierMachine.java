import java.util.Observable;

public class CashierMachine extends Observable{
	private double subtotal;
	private double total;
	private double vat;
	private double cash;
	
	/**
	 * Initialize a 
	 */
	public CashierMachine() {
		subtotal = 0 ;
		total = 0 ;
		vat = 0 ;
		cash = 0;
	}
	
	public void add(double price){
		subtotal += price;
		setChanged();
		notifyObservers();
	}

}
