import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

class NewEventGui extends JFrame {
	private JPanel mainPanel;
	private JButton okButton,cancelButton;
	private JTextField name, categories, openDate, openTime, closeDate, closeTime, recurrence, 			reminder,description;

	public NewEventGui() {
		this.setTitle("New Event");
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
		this.add(mainPanel);
		this.setVisible(true);
	}

	private void init() {
		//clickListener cl = new clickListener();
		JLabel nameLabel = new JLabel("Name:");
		JLabel categoriesLabel = new JLabel("Categories:");
		JLabel assignedLabel = new JLabel("Assigned:");
		JLabel dueDateLabel = new JLabel("Due Date:");
		JLabel recurrenceLabel = new JLabel("Recurrence:");	
		JLabel reminderLabel = new JLabel("Reminder:");	
		JLabel descriptionLabel = new JLabel("Description:");	
		mainPanel = new JPanel();
		MigLayout ml = new MigLayout(
			"wrap 3",
			"[][]10[]",  //Column constraints
			"[]10[]10[]10[]10[]10[]10[][][][][]20[]"); //Row constratints
		//Initialize the event window columns.
		mainPanel.setLayout(ml);
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		//okButton.addMouseListener(cl);
		//cancelButton.addMouseListener(cl);
		name = new JTextField();
		categories = new JTextField();
		openDate = new JTextField();
		openTime = new JTextField();
		closeDate = new JTextField();
		closeTime = new JTextField();
		recurrence = new JTextField();//No it's not.
		reminder = new JTextField(); //Still no.
		description = new JTextField();

		mainPanel.add(nameLabel);
		mainPanel.add(name, "span 2");
		mainPanel.add(categoriesLabel);
		mainPanel.add(categories,"span 2");
		mainPanel.add(assignedLabel);
		mainPanel.add(openDate);
		mainPanel.add(openTime);
		mainPanel.add(dueDateLabel);
		mainPanel.add(closeDate);
		mainPanel.add(closeTime);
		mainPanel.add(recurrenceLabel);
		mainPanel.add(recurrence,"span 2");
		mainPanel.add(reminderLabel);
		mainPanel.add(reminder,"span 2");
		mainPanel.add(descriptionLabel, "span 1 6");
		mainPanel.add(description,"span 2 5");
		mainPanel.add(okButton);
		mainPanel.add(cancelButton);
		
		
	}
	
/*	private class clickListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
      			if ((JButton)e.getComponent()).getText().equals("Cancel") {
      				this.dispose();
      			}
			else {
				//TODO Construct an event
				this.dispose();
			}
    		}
	}
}
*/
}
