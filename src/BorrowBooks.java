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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *A public class
 */
public class BorrowBooks extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("BOOK INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel informationPanel = new JPanel();
	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[4];
	//for creating an array of String
	private String[] informationString = {" Write the Book ID:", " Write the Member ID:",
	                                      " The Current Date:", " The Return Date:"};
	//for creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[2];
	//for creating the date in the String
	//private String date = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new java.util.Date());
    //private String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
    //for creating an array of string to store the data
	private String[] data;
private DateButton current_date;
private DateButton return_date;
	//for creating an Internal Panel in the center panel
	private JPanel borrowButtonPanel = new JPanel();
	//for creating the button
	private JButton borrowButton = new JButton("Borrow");

	//for creating South Panel
	private JPanel southPanel = new JPanel();
	//for creating the button
	private JButton cancelButton = new JButton("Cancel");

	//for creating an object
	private Books book;
	private Members member;
	private Borrow borrow;

	//for checking the information from the text field
	public boolean isCorrect() {
		data = new String[4];
		for (int i = 0; i < informationLabel.length; i++) {
            if(i==0 || i==1)
            {if (!informationTextField[i].getText().equals(""))
             {
              data[i] = informationTextField[i].getText();
             }
             else
					return false;
            }
            if(i==2)
            {
			if (!current_date.getText().equals(""))
            {
				data[i] = current_date.getText();
            }
			else
				return false;
            }
            if(i==3)
            {
                if (!return_date.getText().equals(""))
                {
				data[i] = return_date.getText();
                }
			else
				return false;
            }
		}
		return true;
	}

	//for setting the array of JTextField to null
	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++)
			//if (i != 2)
				informationTextField[i].setText(null);
	}

	//constructor of borrowBooks
	public BorrowBooks() {
		//for setting the title for the internal frame
		super("Borrow Books", false, true, false, true);
		//for setting the icon
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Export16.gif")));
		//for getting the graphical user interface components display area
		Container cp = getContentPane();
        current_date = new DateButton();
        current_date.setForeground(Color.red);
        return_date = new DateButton();
        return_date.setForeground(Color.red);
		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//for setting the font
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		//for adding the label to the panel
		northPanel.add(title);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout for the internal panel
		informationPanel.setLayout(new GridLayout(4, 2, 1, 1));

		/***********************************************************************
		 * for adding the strings to the labels, for setting the font 		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			informationPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			if (i == 2) {
				//informationPanel.add(informationTextField[i] = new JTextField(date));
                informationPanel.add(current_date);
				//informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
				//informationTextField[i].setEnabled(false);
                current_date.setEnabled(false);
			}
            else if(i==3)
            {
                informationPanel.add(return_date);
            }
			else {
				informationPanel.add(informationTextField[i] = new JTextField());
				informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
                if(i==0 || i==1)
                    informationTextField[i].addKeyListener(new keyListener());
			}
		}
		centerPanel.add("Center", informationPanel);

		//for setting the layout
		borrowButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for setting the font to the button
		borrowButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		//for adding the button to the panel
		borrowButtonPanel.add(borrowButton);
		//for adding the panel to the center panel
		centerPanel.add("South", borrowButtonPanel);
		//for setting the border to the panel
		centerPanel.setBorder(BorderFactory.createTitledBorder("Borrow a book:"));
		//for adding the panel to the container
		cp.add("Center", centerPanel);

		//for adding the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for setting the font to the button
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		//for adding the button to the panel
		southPanel.add(cancelButton);
		//for setting the border to the panel
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for adding the panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {
					Thread runner = new Thread() {
                        public void run() {
                            //Date presentDate=current_date.getDate();
                            Date presentDate=new Date();
                            Date returnDate= new Date();
                            returnDate=return_date.getDate();
                            if(presentDate.before(returnDate))
                            {
							book = new Books();
							member = new Members();
							borrow = new Borrow();
                            borrow.connection("SELECT * FROM Borrow WHERE BookID="+data[0]+" AND MemberID="+data[1]);
                            int bookid=borrow.getBookID();
                            int memberid=borrow.getMemberID();
                            if((bookid!=Integer.parseInt(data[0])) && (memberid!=Integer.parseInt(data[1])))
                            {
							book.connection("SELECT * FROM Books WHERE BookID = " + data[0]);
							member.connection("SELECT * FROM Members WHERE MemberID = " + data[1]);
                            Date expiryDate= new Date();
                            expiryDate=member.getValidUpto();
                            if(presentDate.before(expiryDate))
                            {
                            int bookID=book.getBookID();
                            int memberID=member.getMemberID();
                            if(bookID>=1 && memberID>=1)
                            {
							int numberOfAvailbleBooks = book.getNumberOfAvailbleBooks();
							int numberOfBorrowedBooks = 1 + book.getNumberOfBorrowedBooks();
							int numberOfBooks = 1 + member.getNumberOfBooks();
							//for checking if there is one book in the database
							if (numberOfAvailbleBooks == 1) {
								numberOfAvailbleBooks -= 1;
								book.update("UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks +
								        ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + ",Availble = false WHERE BookID =" + data[0]);
								member.update("UPDATE Members SET NumberOfBooks = " + numberOfBooks + " WHERE MemberID = " + data[1]);
								borrow.update("INSERT INTO Borrow (BookID, MemberID, DayOfBorrowed, DayOfReturn) VALUES (" +
								        data[0] + "," + data[1] + ",'" + data[2] + "','" + data[3] + "')");
								//for setting the array of JTextField to null
								//clearTextField();
                                dispose();
							}
							else if (numberOfAvailbleBooks > 1) {
								numberOfAvailbleBooks -= 1;
								book.update("UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks +
								        ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + " WHERE BookID =" + data[0]);
								member.update("UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1]);
								borrow.update("INSERT INTO Borrow (BookID, MemberID, DayOfBorrowed, DayOfReturn) VALUES (" +
								        data[0] + "," + data[1] + ",'" + data[2] + "','" + data[3] + "')");
								//for setting the array of JTextField to null
								//clearTextField();
                                dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "The book is borrowed", "Warning", JOptionPane.WARNING_MESSAGE);

                            }
                            else
                                 JOptionPane.showMessageDialog(null, "Member ID or Book ID entered not found on databse");
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Member is Expired", "Warning", JOptionPane.WARNING_MESSAGE);
                            }

                            else
                            {
                                JOptionPane.showMessageDialog(null, "The book is already borrowed by this member", "Warning", JOptionPane.WARNING_MESSAGE);
                                clearTextField();
                            }
                        }
                         else
                                JOptionPane.showMessageDialog(null, "Return Date is invalid", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
					};
					runner.start();
				}
				//if there is a missing data, then display Message Dialog
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
