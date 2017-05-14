import java.sql.*;

public class Main {
	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://158.108.140.34:3306/Terrarium", "root" , "1234");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from products");

			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("product_id") + ", "+ myRs.getString("name") + ", " + myRs.getString("price"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
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
		
		TerrariumProject ui = new TerrariumProject();
		ui.run();
	}
}