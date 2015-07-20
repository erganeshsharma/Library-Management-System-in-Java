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
public class SearchBooksAndMembers extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("Search for Books and Members");

	//for creating the center
	private JPanel center = new JPanel();

	//for creating the Center Panel
	private JPanel centerBooksPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchBooksPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchBooksButtonPanel = new JPanel();

	//for creating the table
	private JLabel searchBooksLabel = new JLabel(" Search by: ");
	//for creating JComboBox
	private JComboBox searchBooksTypes;
	//for creating String[]
	private String[] booksTypes = {"BookID", "Subject", "Title", "Author", "Publisher", "ISBN"};
	//for creating the label
	private JLabel booksKey = new JLabel(" Write the Keyword: ");
	//for cearting the text field
	private JTextField booksKeyTextField = new JTextField();
	//for creating the button
	private JButton searchBooksButton = new JButton("Search");

	//for creating the Center Panel
	private JPanel centerMembersPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchMembersPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchMembersButtonPanel = new JPanel();

	//for creating the table
	private JLabel searchMembersLabel = new JLabel(" Search by: ");
	//for creating JComboBox
	private JComboBox searchMembersTypes;
	//for creating String[]
	private String[] membersTypes = {"MemberID", "Name", "EMail", "Major"};
	//for creating the label
	private JLabel membersKey = new JLabel(" Write the Keyword: ");
	//for cearting the text field
	private JTextField membersKeyTextField = new JTextField();
	//for creating the button
	private JButton searchMembersButton = new JButton("Search");

	//for creating the south panel
	private JPanel southPanel = new JPanel();
	//for creating the button
	private JButton cancelButton = new JButton("Cancel");

	//for creating an array of string to store the data
	private String[] booksData;
	private String[] membersData;
	//create objects from another classes for using them in the ActionListener
	private ListSearchBooks listBooks;
	private ListSearchMembers listMembers;
	private Books book;
	private Members member;

	//for checking the information from the text field
	public boolean isBooksDataCorrect() {
		booksData = new String[2];
		booksData[0] = searchBooksTypes.getSelectedItem().toString();
		for (int i = 1; i < booksData.length; i++) {
			if (!booksKeyTextField.getText().equals("")) {
				if (searchBooksTypes.getSelectedItem().toString().equals("BookID")) {
					booksData[i] = booksKeyTextField.getText();
				}
				else
					booksData[i] = "'%" + booksKeyTextField.getText() + "%'";
			}
			else
				return false;
		}
		return true;
	}

	//for checking the information from the text field
	public boolean isMembersDataCorrect() {
		membersData = new String[2];
		membersData[0] = searchMembersTypes.getSelectedItem().toString();
		for (int i = 1; i < membersData.length; i++) {
			if (!membersKeyTextField.getText().equals("")) {
				if (searchMembersTypes.getSelectedItem().toString().equals("MemberID")) {
					membersData[i] = membersKeyTextField.getText();
				}
				else
					membersData[i] = "'%" + membersKeyTextField.getText() + "%'";
			}
			else
				return false;
		}
		return true;
	}

	//constructor of searchBooksAndMembers
	public SearchBooksAndMembers() {
		//for setting the title for the internal frame
		super("Search", false, true, false, true);
		//for setting the icon
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Find16.gif")));
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//for setting the font
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		//for adding the label
		northPanel.add(title);
		//for adding the north panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		center.setLayout(new BorderLayout());

		//for setting the layout
		centerBooksPanel.setLayout(new BorderLayout());
		//for setting the layout
		searchBooksPanel.setLayout(new GridLayout(2, 2, 1, 1));
		//for adding the label
		searchBooksPanel.add(searchBooksLabel);
		//for adding the JComboBos[]
		searchBooksPanel.add(searchBooksTypes = new JComboBox(booksTypes));
		//for adding the label
		searchBooksPanel.add(booksKey);
		//for adding the text field
		searchBooksPanel.add(booksKeyTextField);
        booksKeyTextField.addKeyListener(new keyListener());
		//for adding the internal panel to the panel
		centerBooksPanel.add("North", searchBooksPanel);

		//for setting the layout
		searchBooksButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		searchBooksButtonPanel.add(searchBooksButton);
		//for adding the internal panel to the center panel
		centerBooksPanel.add("South", searchBooksButtonPanel);
		//for setting the border
		centerBooksPanel.setBorder(BorderFactory.createTitledBorder("Search for a books:"));
		//for adding center panel to the center
		center.add("West", centerBooksPanel);

		//for setting the layout
		centerMembersPanel.setLayout(new BorderLayout());
		//for setting the layout
		searchMembersPanel.setLayout(new GridLayout(2, 2, 1, 1));
		//for adding the label
		searchMembersPanel.add(searchMembersLabel);
		//for adding the JComboBos[]
		searchMembersPanel.add(searchMembersTypes = new JComboBox(membersTypes));
		//for adding the label
		searchMembersPanel.add(membersKey);
		//for adding the text field
		searchMembersPanel.add(membersKeyTextField);
        membersKeyTextField.addKeyListener(new keyListener2());
		//for adding the internal panel to the panel
		centerMembersPanel.add("North", searchMembersPanel);

		//for setting the layout
		searchMembersButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		searchMembersButtonPanel.add(searchMembersButton);
		//for adding the internal panel to the center panel
		centerMembersPanel.add("South", searchMembersButtonPanel);
		//for setting the border
		centerMembersPanel.setBorder(BorderFactory.createTitledBorder("Search for a members:"));
		//for adding center panel to the center
		center.add("East", centerMembersPanel);

		//for adding the center to the container
		cp.add("Center", center);

		/**
		 *for setting the font to the lables & buttons
		 */
		searchBooksLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchBooksTypes.setFont(new Font("Tahoma", Font.BOLD, 11));
		booksKey.setFont(new Font("Tahoma", Font.BOLD, 11));
		booksKeyTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchBooksButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchMembersLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchMembersTypes.setFont(new Font("Tahoma", Font.BOLD, 11));
		membersKey.setFont(new Font("Tahoma", Font.BOLD, 11));
		membersKeyTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchMembersButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		//for setting the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		southPanel.add(cancelButton);
		//for setting the border
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for adding the south panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isBooksDataCorrect()) {
					book = new Books();
					String bookQuery="SELECT * FROM Books WHERE "+ booksData[0] + " LIKE " + booksData[1];
                    String bookListQuery = "SELECT BookID, Subject, Title, Author, Publisher," +
					        "Copyright, Edition, Pages, NumberOfBooks,ISBN,Library,Availble,ShelfNo FROM Books" +
					        " WHERE " + booksData[0] + " LIKE " + booksData[1];
					book.connection(bookQuery);
					int bookID = book.getBookID();
					if (bookID != 0) {
						listBooks = new ListSearchBooks(bookListQuery);
						getParent().add(listBooks);
						try {
							listBooks.setSelected(true);
						}
						catch (java.beans.PropertyVetoException e) {
						}
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "No Match(es)", "Error", JOptionPane.ERROR_MESSAGE);
						booksKeyTextField.setText(null);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchMembersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (isMembersDataCorrect()) {
					member = new Members();
					String memberQuery="SELECT * FROM Members WHERE "+ membersData[0] + " LIKE " + membersData[1];
                    String memberListQuery = "SELECT MemberID, RegNo, Name, EMail, Major, ValidUpto" +
					        " FROM Members WHERE " + membersData[0] + " LIKE " + membersData[1];
					member.connection(memberQuery);
					int memberID = member.getMemberID();
					if (memberID != 0) {
						listMembers = new ListSearchMembers(memberListQuery);
						getParent().add(listMembers);
						try {
							listMembers.setSelected(true);
						}
						catch (java.beans.PropertyVetoException e) {
						}
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "No Match(es)", "Error", JOptionPane.ERROR_MESSAGE);
						membersKeyTextField.setText(null);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		//for adding the action listener for the button to dispose the frame
		cancelButton.addActionListener(new ActionListener() {
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
            if(searchBooksTypes.getSelectedItem().toString().equals("BookID"))
            {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only Accept Integer Number", "WARNING",JOptionPane.DEFAULT_OPTION);
                    e.consume();
                 }
            }
        }
    }

    class keyListener2 extends KeyAdapter {

        public void keyTyped(KeyEvent e) {
            if(searchMembersTypes.getSelectedItem().toString().equals("MemberID"))
            {
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
        }
    }//inner class closed
}