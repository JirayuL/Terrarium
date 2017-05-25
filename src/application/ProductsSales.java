package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.Sales;
import database.Store;

public class ProductsSales {
	private static ProductsSales instance;
	private Map<String, List<String>> productSales;
	private Map<String, List<String>> productMap;
	private Map<String, List<String>> mapProductsDay;
	private double total;
	ProductsSales(){
		productSales = Sales.getInstance().getSalesDatabase();
		productMap = Store.getInstance().getProductMap();
		mapProductsDay = new HashMap<>();
	}
	
	public static ProductsSales getInstance() {
		if (instance == null)
			instance = new ProductsSales();
		return instance;

	}
	
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
	
	public double calculate(String price,String qty){
		return Double.parseDouble(price)*Double.parseDouble(qty);
	}

	public double getTotal() {
		return total;
	}	
	
	public void resetTotal() {
		total = 0;
	}
}
