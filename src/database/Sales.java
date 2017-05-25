package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sales class for insert data of sales to database
 * @author Wanchanapon Thanwaranurak
 * @version 24.5.17
 */
public class Sales {
	/** Define the null Object */
	private static final Sales NOOP = null;
	/** Define the Sales */
	private static Sales instance = NOOP;
	private Map<String, List<String>> productSales;
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	private Sales(){
		this.productSales = new HashMap<>();

	}

	/**
	 * Return all information of the Sales.
	 * 
	 * @return the Store
	 */
	public static Sales getInstance() {
		if (instance == null)
			instance = new Sales();
		return instance;

	}

	/**
	 * Insert data that sales to database
	 * @param saleMap is Map that have a data for insert to database.
	 * @return true when insert to database finish.
	 */
	public boolean insertSaleToDatabase(Map<String, Integer> saleMap){ 
		try { 
			// 1. Get a connection to database
			myConn =  DriverManager.getConnection("jdbc:mysql://158.108.141.127:3306/Terrarium", "root", "1234");

			// 2. Create a statement
			myStmt = myConn.createStatement(); 

			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = dateformat.format(new Date());
			for(Map.Entry<String, Integer> entry : saleMap.entrySet()){
				myStmt.executeUpdate(String.format("INSERT INTO sales (Sale_date,Sale_id,Sale_quantity) VALUES ('%s', %d, %d)",nowDate,Integer.parseInt(entry.getKey()),entry.getValue())); 
			}
			myConn.close(); 
			return true;
		} catch (Exception e) { 
			System.err.println("Got an exception! "); 
			System.err.println(e.getMessage()); 
		}
		return false;
	}

	/**
	 * Get sales information from database
	 * @return Map that has information that get from database
	 */
	public Map<String, List<String>> getSalesDatabase(){
		String id,sale_date,sale_id,sale_qty;
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://158.108.141.127:3306/Terrarium", "root", "1234");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT * FROM `sales`");
			// 4. Process the result set
			while (myRs.next()) {
				id = myRs.getString("ID");
				sale_date = myRs.getString("Sale_date");
				sale_id = myRs.getString("Sale_id");
				sale_qty = myRs.getString("Sale_quantity");

				// 5. Put the data into map.
				productSales.put(id,new ArrayList<String>(Arrays.asList(sale_date, sale_id, sale_qty)));
			}
		} catch (Exception exc) {
			// Do nothing
		} finally {
			try {
				if (myRs != null) {
					myRs.close();
				}

				if (myStmt != null) {
					myStmt.close();
				}

				if (myConn != null) {
					myConn.close();
				}
			} catch (Exception e) {
				// Do nothing
			}
		}
		return productSales;
	}

}

