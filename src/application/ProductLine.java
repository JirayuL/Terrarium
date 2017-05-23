package application;

public class ProductLine implements Comparable<ProductLine> {
	private int id;
	private String name;
	private double price;

	public ProductLine(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
