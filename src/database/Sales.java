package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Sales class for insert data of sales to database
 * @author Wanchanapon Thanwaranurak
 * @version 24.5.17
 */

public class Sales {
	public static void main(String[] args) {
		try { 
			String url = "jdbc:msql://localhost:3306/Terrarium"; 
			
			// 1. Get a connection to database
			Connection conn =  DriverManager.getConnection("jdbc:mysql://10.0.1.69:3306/Terrarium", "root", "1234");
			
			// 2. Create a statement
			Statement st = conn.createStatement(); 
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			String e = dateformat.format(new Date());
			st.executeUpdate(String.format("INSERT INTO sales VALUES (null,'%s', %d, %d)",e,1,2)); 


			conn.close(); 
		} catch (Exception e) { 
			System.err.println("Got an exception! "); 
			System.err.println(e.getMessage()); 
		}
	}
}

	