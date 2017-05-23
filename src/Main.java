import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import database.Store;
import terrariumGUI.TerrariumGUI;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {

		Store store = Store.getInstance();

		TerrariumGUI ui = new TerrariumGUI(store);
		ui.run();
		
//		System.out.println(Integer.parseInt(null));
	}
}