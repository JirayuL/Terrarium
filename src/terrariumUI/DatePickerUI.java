package terrariumUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class DatePickerUI{
	
	//create object of JLabel with alignment
	private JLabel label ;
	private String day = "";
	private JDialog dialog;
	private JButton[] button ;
	private JButton next,previous;
	private int month = 0, year = 0;
	private JPanel panel1,panel2;

	public DatePickerUI(JFrame parent) {
		month = Calendar.getInstance().get(Calendar.MONTH);
		year = Calendar.getInstance().get(Calendar.YEAR);
		initialize(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame parent) {
		label = new JLabel("", JLabel.CENTER);
		button = new JButton[49];
		dialog = new JDialog();
		dialog.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		panel1 = new JPanel(new GridLayout(7, 7));
		panel1.setPreferredSize(new Dimension(430, 120));

		for (int x = 0; x < button.length; x++) {		
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6){
				button[x].addActionListener((e) ->{
					day = button[selection].getActionCommand();
					dialog.dispose();
				});
			}
			if (x < 7)
			{
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			panel1.add(button[x]);
		}
		panel2 = new JPanel(new GridLayout(1, 3));
		previous = new JButton("<< Previous");
		previous.addActionListener((e) ->{
			month--;
			displayDate();
		});
		panel2.add(previous);
		panel2.add(label);
		next = new JButton("Next >>");
		next.addActionListener((e) ->{
			month++;
			displayDate();
		});
		panel2.add(next);
		dialog.add(panel1, BorderLayout.CENTER);
		dialog.add(panel2, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		displayDate();
		dialog.setVisible(true);
	}
	
	public void displayDate() {
		for (int x = 7; x < button.length; x++) button[x].setText("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		Calendar cal = Calendar.getInstance();			
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) button[x].setText("" + day);
		label.setText(sdf.format(cal.getTime()));
		dialog.setTitle("Date Picker");
	}

	public String setPickedDate() {
		if (day.equals("")) return day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		return sdf.format(cal.getTime());
	}
}


