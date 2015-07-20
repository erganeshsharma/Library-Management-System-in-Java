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
import java.awt.event.KeyListener;

/**
 *A public class
 */
public class EditBooks extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creaing the North Label
	private JLabel northLabel = new JLabel("BOOK INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating the edit Panel
	private JPanel editPanel = new JPanel();
	//for creating the edit information Panel
	private JPanel editInformationPanel = new JPanel();
	//for creating the edit label panel
	private JPanel editInformationLabelPanel = new JPanel();
	//for creating the edit textField panel
	private JPanel editInformationTextFieldPanel = new JPanel();
	//for creating the edit button panel
	private JPanel editInformationButtonPanel = new JPanel();

	//for creating the label
	private JLabel editLabel = new JLabel("BookID: ");
	//for creating the textField
	private JTextField editTextField = new JTextField(25);
	//for creating the button
	private JButton editButton = new JButton("Edit");

	//for creating the information Panel
	private JPanel informationPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel informationLabelPanel = new JPanel();

	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[10];
	//for creating an array of String
	private String[] informationString = {
		" The book subject: ", " The book title: ",
		" The name of the Author(s): ", " The name of the Publisher: ",
		" Copyright year for the book: ", " The edition number: ", " The number of Pages: ",
		" ISBN for the book: ", " The number of copies: ", " The name of the Library: "
	};
	//for creating an Internal Panel in the center panel
	private JPanel informationTextFieldPanel = new JPanel();
	//for creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[10];

	//for creating an Internal Panel in the center panel
	private JPanel updateInformationButtonPanel = new JPanel();
	//for creating a button
	private JButton updateInformationButton = new JButton("Update the Information");

	//for creating South Panel
	private JPanel southPanel = new JPanel();
	//for creating a button
	private JButton exitButton = new JButton("Exit");

	//create objects from another classes for using them in the ActionListener
	private Books book;
	//for creating an array of string to store the data
	private String[] data;
	//for setting availble option to true
	private boolean availble;
    private int numberofavailblebooks;

	//for checking the information from the text field
	public boolean isCorrect() {
		data = new String[10];
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals("")) {
				data[i] = informationTextField[i].getText();
			}
			else
				return false;
		}
		return true;
	}

	//for checking the information from the text field
	public boolean isEditCorrect() {
		if (editTextField.getText().equals(""))
			return false;
		return true;
	}

	//for setting the array of JTextField to empty
	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			editTextField.setText(null);
			informationTextField[i].setText(null);
		}
	}

	//constructor of addBooks
	public EditBooks() {
		//for setting the title for the internal frame
		super("Edit Books", false, true, false, true);
		//for setting the icon
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Edit16.gif")));
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//for setting the font for the North Panel
		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		//for adding the label in the North Panel
		northPanel.add(northLabel);
		//for adding the north panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout
		editPanel.setLayout(new BorderLayout());
		//for setting the border to the panel
		editPanel.setBorder(BorderFactory.createTitledBorder("BookID: "));
		//for setting the layout
		editInformationPanel.setLayout(new BorderLayout());
		//for setting the layout
		editInformationLabelPanel.setLayout(new GridLayout(1, 1, 1, 1));
		//for adding the label to the panel
		editInformationLabelPanel.add(editLabel);
		//for setting the font to the label
		editLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		//for adding the editInformationLabelPanel to the editInformationLabel
		editInformationPanel.add("West", editInformationLabelPanel);
		//for setting the layout
		editInformationTextFieldPanel.setLayout(new GridLayout(1, 1, 1, 1));
		//for adding the textField to the panel
		editInformationTextFieldPanel.add(editTextField);
		//for setting the font to the textField
		editTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
        editTextField.addKeyListener(new keyListener());
		//for adding the editInformationTextField to the editInformationPanel
		editInformationPanel.add("East", editInformationTextFieldPanel);
		//for adding the editInformationPanel to the editPanel
		editPanel.add("North", editInformationPanel);
		//for setting the layout
		editInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button to the panel
		editInformationButtonPanel.add(editButton);
		//for setting the fonr to the button
		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		//for adding the editInformationButtonPanel to the editPanel
		editPanel.add("Center", editInformationButtonPanel);
		//for adding the editPanel to the centerPanel
		centerPanel.add("North", editPanel);

		//for setting the layout
		informationPanel.setLayout(new BorderLayout());
		//for setting the border to the panel
		informationPanel.setBorder(BorderFactory.createTitledBorder("Edit a book: "));
		//for setting the layout
		informationLabelPanel.setLayout(new GridLayout(10, 1, 1, 1));
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font 		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		informationPanel.add("West", informationLabelPanel);

		//for setting the layout
		informationTextFieldPanel.setLayout(new GridLayout(10, 1, 1, 1));
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font 		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
            if(i==4 || i==5 || i==6 || i==8 )
                informationTextField[i].addKeyListener(new keyListener());
		}
		informationPanel.add("East", informationTextFieldPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * and adding the button to the panel.								   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		updateInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		updateInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateInformationButtonPanel.add(updateInformationButton);
		informationPanel.add("South", updateInformationButtonPanel);
		centerPanel.add("Center", informationPanel);
		cp.add("Center", centerPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * adding the button to the panel & setting the border.				   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(exitButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isEditCorrect()) {
					Thread runner = new Thread() {
						public void run() {
                            book = new Books();
							//for checking if there is no same information in the database
							book.connection("SELECT * FROM Books WHERE BookID = " + editTextField.getText());
							int copyright = book.getCopyright();
							if (copyright > 0) {
								informationTextField[0].setText(book.getSubject());
								informationTextField[1].setText(book.getTitle());
								informationTextField[2].setText(book.getAuthor());
								informationTextField[3].setText(book.getPublisher());
								informationTextField[4].setText(book.getCopyright() + "");
								informationTextField[5].setText(book.getEdition() + "");
								informationTextField[6].setText(book.getPages() + "");
								informationTextField[7].setText(book.getISBN());
								informationTextField[8].setText(book.getNumberOfBooks() + "");
								informationTextField[9].setText(book.getLibrary());
							}
							else {
								JOptionPane.showMessageDialog(null, "Please, write a correct BookID", "Error", JOptionPane.ERROR_MESSAGE);
								editTextField.setText(null);
								clearTextField();
							}
						}
					};
					runner.start();
				}
				//if there is a missing data, then display Message Dialog
				else
					JOptionPane.showMessageDialog(null, "Please, write the BookID", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		updateInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {
					Thread runner = new Thread() {
						public void run() {
							book = new Books();
                            book.connection("SELECT * FROM Books WHERE BookID = " + editTextField.getText());
                            int numberofborrowedbooks=book.getNumberOfBorrowedBooks();
                            if((Integer.parseInt(data[8]))>=numberofborrowedbooks)
                            {
                                
                              if((Integer.parseInt(data[8]))>numberofborrowedbooks)
                              {
                                  availble = true;
                                  numberofavailblebooks=((Integer.parseInt(data[8]))-numberofborrowedbooks);
                              }
                              else
                              {
                                  availble = false;
                                  numberofavailblebooks=0;
                              }
                              book.update("UPDATE Books SET Subject = '" + data[0] + "',Title = '" + data[1] + "',Author = '" + data[2] +
							        "',Publisher = '" + data[3] + "',Copyright =" + data[4] + ",Edition =" + data[5] +
							        ",Pages =" + data[6] + ",ISBN = '" + data[7] + "',NumberOfBooks =" + data[8] +
							        ",NumberOfAvailbleBooks =" + numberofavailblebooks + ",Library = '" + data[9] + "',Availble =" + availble +
							        " WHERE BookID =" + editTextField.getText());
							//for setting the array of JTextField to empty
							//clearTextField();
                            dispose();
                                  
                              }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Number of copies must be larger, as some books are borrowed", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                             /*//for checking if there is no same information in the database
							if (Integer.parseInt(data[8]) == 0)
								availble = false;
							else
								availble = true;*/
							
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
    }

}