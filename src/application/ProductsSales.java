package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.Sales;
import database.Store;

/**
 * ProductsSales for calculate and check date that user choose.
 * @author Wanchanapon Thanwaranurak
 * @version 25/5/2017
 */
public class ProductsSales {
	private static ProductsSales instance;
	private Map<String, List<String>> productSales;
	private Map<String, List<String>> productMap;
	private Map<String, List<String>> mapProductsDay;
	private double total;
	
	/**
	 * Create constructor 
	 */
	ProductsSales(){
		productSales = Sales.getInstance().getSalesDatabase();
		productMap = Store.getInstance().getProductMap();
		mapProductsDay = new HashMap<>();
	}
	
	/**
	 * Get all the information of the ProductsSales.
	 * 
	 * @return the ProductsSales
	 */
	public static ProductsSales getInstance() {
		if (instance == null) instance = new ProductsSales();
		return instance;

	}
	
	/**
	 * Get all products of day
	 * @param day is a day that customer choose.
	 * @return Map of product information.   
	 */
	public Map<String, List<String>> getProductsDay(String day){
		mapProductsDay.clear();
		for(Map.Entry<String, List<String> > entry : productSales.entrySet()){
			String id = entry.getValue().get(1);
			String name = productMap.get(id).get(0);
			String price = productMap.get(id).get(1);
			String qty = entry.getValue().get(2);
			if(entry.getValue().get(0).equals(day)){
				if(!mapProductsDay.containsKey(id)){
					mapProductsDay.put(id, new ArrayList<>(Arrays.asList(name,qty,calculate(price,qty)+"")));
					total += calculate(price,qty);
				}
				else{
					mapProductsDay.get(id).set(1, Integer.parseInt(mapProductsDay.get(id).get(1))+Integer.parseInt(qty)+"");
					mapProductsDay.get(id).set(2, Double.parseDouble(mapProductsDay.get(id).get(2))+(calculate(price,qty))+"");
					total += calculate(price,qty);
				}
			}
		}
		return mapProductsDay;
	}
	
	/**
	 * Calculate cost of each product.
	 * @param price is a price of product.
	 * @param qty is quantity of product that buy.
	 * @return cost of products.
	 */
	public double calculate(String price,String qty){
		return Double.parseDouble(price)*Double.parseDouble(qty);
	}

	/**
	 * Get total cost of products in sale at day.
	 * @return total cost of product.
	 */
	public double getTotal() {
		return total;
	}	
	
	/**
	 * Reset total to zero.
	 */
	public void resetTotal() {
		total = 0;
	}
}
