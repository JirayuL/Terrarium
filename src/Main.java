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

import application.CashierMachine;
import database.Store;
import terrariumGUI.PaymentGUI;
import terrariumGUI.TerrariumGUI;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {

		Store store = Store.getInstance();
		CashierMachine cashier = new CashierMachine();
		PaymentGUI paymentGUI = new PaymentGUI(cashier);
		TerrariumGUI terrariumGUI = new TerrariumGUI(store, cashier, paymentGUI);
		cashier.addObserver(terrariumGUI);
		cashier.addObserver(paymentGUI);
		terrariumGUI.run();
	}
}