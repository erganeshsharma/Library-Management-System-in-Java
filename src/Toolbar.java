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

public class Toolbar extends JToolBar {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the buttons to use them in ToolBar
	public JButton[] button;
	//for creating the name of the image file 24*24
	public String[] imageName24 = {"images/Add24.gif", "images/List24.gif",
	                               "images/List24.gif", "images/List24.gif",
	                               "images/Edit24.gif", "images/Delete24.gif",
	                               "images/Add24.gif",  "images/List24.gif",
                                   "images/Edit24.gif","images/Delete24.gif",
	                               "images/Search24.jpg", "images/Export24.gif",
	                               "images/Import24.gif","images/List24.gif",
                                   "images/Notepad24.png","images/Calculator24.png",
                                   "images/EditLibrarian24.gif","images/DeleteLibrarian24.gif",
                                   "images/About24.gif","images/Exit24.gif"};
	//for creating the tipText for the
	public String[] tipText = {"Add Books", "List All Books", "List Available Books",
	                           "List Borrowed Books", "Edit Books", "Remove Books",
	                           "Add Members", "List Members", "Edit Members", "Remove Members",
	                           "Search", "Borrow Books", "Return Books","Issued Book Details",
                                   "Notepad", "Calculator","Change Password","Delete Librarian", "About", "Exit"};

	public Toolbar() {
		button = new JButton[20];
		for (int i = 0; i < imageName24.length; i++) {
			if (i == 6 || i == 10 || i == 11 || i == 14 || i==16)
			//for adding separator to the toolBar
				addSeparator();
			//for adding the buttons to toolBar
			add(button[i] = new JButton(new ImageIcon(ClassLoader.getSystemResource(imageName24[i]))));
			//for setting the ToolTipText to the button
			button[i].setToolTipText(tipText[i]);
		}
	}
}