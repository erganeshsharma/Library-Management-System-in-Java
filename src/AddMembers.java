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
import java.util.Arrays;
import java.util.Date;

/**
 *A public class
 */
public class AddMembers extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creaing the North Label
	private JLabel northLabel = new JLabel("MEMBER INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel informationLabelPanel = new JPanel();
	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[7];
	//for creating an array of String
	/*private String[] informaionString = {" Member ID: ", " The Password: ", " Rewrite the password: ",
	                                     " The Name: ", " E-MAIL: ", " Major:", " Expired: "};*/
    private String[] informaionString = {" Reg. No: ", " The Password: ", " Rewrite the password: ",
	                                     " The Name: ", " E-Mail: ", " Major: ", " Valid Upto: "};
	//for creating an Internal Panel in the center panel
	private JPanel informationTextFieldPanel = new JPanel();
	//for creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[4];
	//for creating an array of JPasswordField
	private JPasswordField[] informationPasswordField = new JPasswordField[2];

	//for creating an Internal Panel in the center panel
	private JPanel insertInformationButtonPanel = new JPanel();
	//for creating a button
	private JButton insertInformationButton = new JButton("Insert the Information");

	//for creating the South Panel
	private JPanel southPanel = new JPanel();
	//for creating a button
	private JButton OKButton = new JButton("Exit");

	//create objects from another classes for using them in the ActionListener
	private Members member;
	//for creating an array of string to store the data
	private String[] data;

    private DateButton expiry_date;

	//for checking the password
	public boolean isPasswordCorrect() {
        if (Arrays.equals(informationPasswordField[0].getPassword(),informationPasswordField[1].getPassword()))
            data[1] = new String(informationPasswordField[1].getPassword());
        else if(!Arrays.equals(informationPasswordField[0].getPassword(),informationPasswordField[1].getPassword()))
            return false;
        return true;
		/*if (informationPasswordField[0].getText().equals(informationPasswordField[1].getText()))
			data[1] = informationPasswordField[1].getText();
		else if (!informationPasswordField[0].getText().equals(informationPasswordField[1].getText()))
			return false;

		return true;*/
	}

	//for checking the information from the text field
	public boolean isCorrect() {
		data = new String[6];
		for (int i = 0; i < informationLabel.length; i++) {
			if (i == 0) {
				if (!informationTextField[i].getText().equals("")) {
					data[i] = informationTextField[i].getText();
				}
				else
					return false;
			}
			if (i == 1 || i == 2) {
				//if (informationPasswordField[i - 1].getText().equals(""))
                if (informationPasswordField[i-1].getPassword().length==0)
					return false;
			}
			if (i == 3 || i == 4 || i == 5) {
				if (!informationTextField[i - 2].getText().equals("")) {
					data[i - 1] = informationTextField[i - 2].getText();
				}
				else
					return false;
			}
            if(i==6)
            {
                if(!expiry_date.getText().equals(""))
                {
                data[i-1]=expiry_date.getText();
                }
                else
                    return false;
            }
		}
		return true;
	}

	//for setting the array of JTextField & JPasswordField to null
	public void clearTextField() {
		for (int i = 0; i < informationLabel.length; i++) {
			if (i == 0)
				informationTextField[i].setText(null);
			if (i == 1 || i == 2)
				informationPasswordField[i - 1].setText(null);
                //informationPasswordField[i - 1].setPassword("");
			if (i == 3 || i == 4 || i == 5)
				informationTextField[i - 2].setText(null);
		}
	}
	//constructor of addMembers
	public AddMembers() {
		//for setting the title for the internal frame
		super("Add Members", false, true, false, true);
		//for setting the icon
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Add16.gif")));
       // setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		//for getting the graphical user interface components display area
		Container cp = getContentPane();
        expiry_date = new DateButton();
        expiry_date.setForeground(Color.red);

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//for setting the font
		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		//for adding the label to the panel
		northPanel.add(northLabel);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the border to the panel
		centerPanel.setBorder(BorderFactory.createTitledBorder("Add a new member:"));
		//for setting the layout
		informationLabelPanel.setLayout(new GridLayout(7, 1, 1, 1));
		//for setting the layout
		informationTextFieldPanel.setLayout(new GridLayout(7, 1, 1, 1));
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informaionString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		//for adding the panel to the centerPanel
		centerPanel.add("West", informationLabelPanel);

		/***********************************************************************
		 * for adding the JTextField and JPasswordField to the panel and       *
		 * setting the font to the JTextField and JPasswordField. Finally      *
		 * adding the panel to the centerPanel                                 *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			if (i == 1 || i == 2) {
				informationTextFieldPanel.add(informationPasswordField[i - 1] = new JPasswordField(25));
				informationPasswordField[i - 1].setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
			if (i == 0) {
				informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
				informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
                informationTextField[i].addKeyListener(new keyListener());
			}
			if (i == 3 || i == 4 || i == 5) {
				informationTextFieldPanel.add(informationTextField[i - 2] = new JTextField(25));
				informationTextField[i - 2].setFont(new Font("Tahoma", Font.PLAIN, 11));
                }
            if(i==6)
            {
                informationTextFieldPanel.add(expiry_date);
            }
		}
		centerPanel.add("East", informationTextFieldPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * and adding the button to the panel.								   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		insertInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		insertInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		insertInformationButtonPanel.add(insertInformationButton);
		centerPanel.add("South", insertInformationButtonPanel);
		cp.add("Center", centerPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * adding the button to the panel & setting the border.				   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		OKButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(OKButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		insertInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {
					if (isPasswordCorrect()) {
						Thread runner = new Thread() {
							public void run() {
                                Date expiryDate= new Date();
                                expiryDate=expiry_date.getDate();
                                Date presentDate=new Date();
                                if(presentDate.before(expiryDate))
                               {
                                member = new Members();
								//for checking if there is no same information in the database
								member.connection("SELECT * FROM Members WHERE RegNo = " + data[0]);
								int regNo = member.getRegNo();
								if (Integer.parseInt(data[0]) != regNo) {
									member.update("INSERT INTO Members (RegNo,Password,Name,EMail,Major,ValidUpto) VALUES (" +
									        data[0] + ", '" + data[1] + "','" + data[2] + "','" +
									        data[3] + "','" + data[4] + "','" + data[5] + "')");
									//for setting the array of JTextField & JPasswordField to null
									//clearTextField();
                                    dispose();
								}
								else
									JOptionPane.showMessageDialog(null, "Member is in the Library", "Error", JOptionPane.ERROR_MESSAGE);
							}
                                else
                                    JOptionPane.showMessageDialog(null, "Expiry Date is invalid", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
						};
						runner.start();
					}
					//if the password is wrong
					else
						JOptionPane.showMessageDialog(null, "the passowrd is wrong", "Error", JOptionPane.ERROR_MESSAGE);
				}
				//if there is a missing data, then display Message Dialog
				else
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		//for adding the action listener for the button to dispose the frame
		OKButton.addActionListener(new ActionListener() {
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