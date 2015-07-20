/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */
//import the packages for using the classes in them into the program

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *A public class
 */
public class RemoveMembers extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("MEMBER INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel removePanel = new JPanel();
	//for creating the label
	private JLabel removeLabel = new JLabel(" Write the Member ID: ");
	//for creating the text field
	private JTextField removeTextField = new JTextField();
	//for creating string to store the data
	private String data;
	//for creating an Internal Panel in the center panel
	private JPanel removeMemberPanel = new JPanel();
	//for creating the button
	private JButton removeButton = new JButton("Remove");

	//for creating the South Panel
	private JPanel southPanel = new JPanel();
	//for adding the button
	private JButton exitButton = new JButton("Exit");

	//create objects from another classes for using them in the ActionListener
	private Members member;

	//for checking the information from the text field
	public boolean isCorrect() {
		if (!removeTextField.getText().equals("")) {
			data = removeTextField.getText();
			return true;
		}
		else
			return false;
	}

	//constructor of removeMembers
	public RemoveMembers() {
		//for setting the title for the internal frame
		super("Remove Members", false, true, false, true);
		//for setting the icon
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Delete16.gif")));
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//for setting the font
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		//for adding the label
		northPanel.add(title);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout
		removePanel.setLayout(new GridLayout(1, 2, 1, 1));
		//for adding the label
		removePanel.add(removeLabel);
		//for adding the text field
		removePanel.add(removeTextField);
        removeTextField.addKeyListener(new keyListener());
		//for adding the internal panel to the panel
		centerPanel.add("Center", removePanel);

		//for setting the layout
		removeMemberPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		removeMemberPanel.add(removeButton);
		//for addint the internal panel to the center panel
		centerPanel.add("South", removeMemberPanel);
		//for setting the border
		centerPanel.setBorder(BorderFactory.createTitledBorder("Remove a member:"));
		//for adding the center panel to the container
		cp.add("Center", centerPanel);

		/**
		 *for setting the font for the label & buttons
		 */
		removeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		//for setting the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		southPanel.add(exitButton);
		//for setting the border
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for add the south panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and make the connection for database,     *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {
					Thread runner = new Thread() {
						public void run() {
							member = new Members();
							member.connection("SELECT * FROM Members WHERE MemberID = " + data);
							//for checking if the user borrowed any book form the library
							int numberOfBooks = member.getNumberOfBooks();
                            int memberid=member.getMemberID();
                            if(memberid>=1)
                            {
							if (numberOfBooks == 0) {
								member.update("DELETE FROM Members WHERE MemberID = " + data);
								//for setting the JTextField to null
								//removeTextField.setText(null);
                                dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "Book(s) borrowed by the member", "Warning", JOptionPane.WARNING_MESSAGE);
						}
                            else
                            {
                            JOptionPane.showMessageDialog(null, "The MemberID is wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                            removeTextField.setText(null);
                            }
                        }
					};
					runner.start();
				}
				//if there is a missing data, then display Message Dialog
				else {
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		//for adding the action listener for the button to dispose the frame
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		//for setting the visible to true
		setVisible(true);
		//show the internal frame
		pack();
	}

    class keyListener extends KeyAdapter {

        public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_ENTER) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only Accept Integer Number", "WARNING",JOptionPane.DEFAULT_OPTION);
                    e.consume();
                 }
            }
    }//inner class closed

}