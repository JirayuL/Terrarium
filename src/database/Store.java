package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import terrariumGUI.ProductLine;

public class Store {
	private static Store store = null;
	HashMap<String, List<String>> myMap;
	List<ProductLine> productLine = new ArrayList<>();
	Map<String, List<String>> productMap = new HashMap<>();

	private Store() throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
//			myConn = DriverManager.getConnection("jdbc:mysql://158.108.137.58:3306/Terrarium", "root", "1234");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Terrarium", "", "");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from products");

			// 4. Process the result set
			while (myRs.next()) {
				// For check data in the database
				// System.out.println(
				// myRs.getString("product_id") + ", " + myRs.getString("name")
				// + ", " + myRs.getString("price"));
				myMap.put(myRs.getString("product_id"),
						new ArrayList<String>(Arrays.asList(myRs.getString("name"), myRs.getString("price"))));
				productLine.add(
						new ProductLine(myRs.getInt("product_id"), myRs.getString("name"), myRs.getDouble("price")));
			}
		} catch (Exception exc) {
			// exc.printStackTrace();
		} finally {
			// For check the database in List
			// for (ProductLine productLine : line) {
			// System.out.println(productLine);
			// }
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}
	}

	public static Store getInstance() {
		if (store == null)
			try {
				store = new Store();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return store;

	}

	public List<ProductLine> getProductLine() {
		return productLine;
	}

	public Map<String, List<String>> getProductMap() {
		return productMap;
	}

}
