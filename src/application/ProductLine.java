package application;

/**
 * Object for product contains id, name and price.
 * 
 * @author Jirayu Laungwilawan
 * @version 23.5.17
 */
public class ProductLine implements Comparable<ProductLine> {
	/** Id of the product */
	private int id;
	/** Name of the product */
	private String name;
	/** Price of the product */
	private double price;

	/**
	 * Initialize construtor for ProductLine.
	 * 
	 * @param id
	 *            of the product.
	 * @param name
	 *            of the product.
	 * @param price
	 *            of the product.
	 */
	public ProductLine(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	/**
	 * Return id of the product.
	 * 
	 * @return id of the product.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return name of the product.
	 * 
	 * @return name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return price of the product.
	 * 
	 * @return price of the product.
	 */
	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return String.format("%d %s %.2f", this.id, this.name, this.price);
	}

	@Override
	public int compareTo(ProductLine o) {
		return (int) Math.signum(this.id - o.id);
	}

}
