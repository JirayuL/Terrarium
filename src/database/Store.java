package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for pull data from the database.
 * 
 * @author Jirayu Laungwilawan
 * @version 14.5.17
 */
public class Store {
	/** Define the null Object */
	private static final Store NOOP = null;
	/** Define the Store */
	private static Store store = NOOP;
	/** Map of the productMap */
	Map<String, List<String>> productMap;

	/**
	 * Initialize the constructor.
	 */
	private Store() {
		productMap = new HashMap<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://158.108.141.127:3306/Terrarium", "root", "1234");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from products");

			// 4. Process the result set
			while (myRs.next()) {
				// For check data in the database
				System.out.println(
						myRs.getString("product_id") + ", " + myRs.getString("name") + ", " + myRs.getString("price"));

				// 5. Put the data into map.
				productMap.put(myRs.getString("product_id"),
						new ArrayList<String>(Arrays.asList(myRs.getString("name"), myRs.getString("price"))));
			}
		} catch (Exception exc) {
			// Do nothing
		} finally {
			// For check the database in List
			// for (ProductLine productLine : line) {
			// System.out.println(productLine);
			// }
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
	}

	/**
	 * Return all information of the Store.
	 * 
	 * @return the Store
	 */
	public static Store getInstance() {
		if (store == null)
			store = new Store();
		return store;

	}

	/**
	 * Return the map of the product in database.
	 * 
	 * @return map of the product in database.
	 */
	public Map<String, List<String>> getProductMap() {
		return productMap;
	}

}
