package application;

import java.awt.EventQueue;

import controller.CashierMachine;
import terrariumUI.ChangeUI;
import terrariumUI.PaymentUI;
import terrariumUI.TerrariumUI;

/**
 * Main class for run, show main page of Terrarium application
 * 
 * @author Wanchanapon Thanwaranurak
 * @version 14.5.17
 */
public class Main {
	public static void main(String[] args) {
		CashierMachine cashier = CashierMachine.getInstance();
		cashier.addObserver(TerrariumUI.getInstance());
		cashier.addObserver(PaymentUI.getInstance());
		cashier.addObserver(ChangeUI.getInstance());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerrariumUI.getInstance().run();
				} catch (Exception e) {
					// DO nothing
				}
			}
		});
	}
}