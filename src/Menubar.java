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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.management.JMException;

public class Menubar extends JMenuBar {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the JMenu for the program
    public JMenu fileMenu,  bookMenu,  memberMenu,  searchMenu,  loanMenu,  toolsMenu,  helpMenu;
    //for creating the JMenuItem for JMenu
    public JMenuItem /*printBook,*/  exit,  addBook,  listBook,  listAvailbleBook,  listBorrowedBook;
    public JMenuItem editBook,  removeBook, /*bookInformation, */ addMember,  listMember,  editMember;
    public JMenuItem  removeMember,  /*memberInformation,*/  searchBooksAndMembers,  borrowBook,  returnBook;
    public JMenuItem listissuedbooks,notepad,calculator,changePassword,deleteLibrarian,about;
    //for creating an imageIcon
    public ImageIcon[] icons;
    //for creating the name of the image file 16*16
    public String[] imageName16 = {"images/Exit16.gif",
        "images/Add16.gif", "images/List16.gif",
        "images/Edit16.gif", "images/Delete16.gif",
        "images/Search16.jpg", "images/Export16.gif",
        "images/Import16.gif","images/Notepad16.png",
        "images/Calculator16.png","images/EditLibrarian16.gif",
        "images/DeleteLibrarian16.gif","images/About16.gif"
    };

    public Menubar() {
        //for adding book, member, search, loan & help Menus to the menu bar
        this.add(fileMenu = new JMenu("File"));
        this.add(bookMenu = new JMenu("Books"));
        this.add(memberMenu = new JMenu("Members"));
        this.add(searchMenu = new JMenu("Search"));
        this.add(loanMenu = new JMenu("Loan"));
        this.add(toolsMenu = new JMenu("Tools"));
        this.add(helpMenu = new JMenu("Help"));

        /**
         *for setting the Mnemonic
         */
        fileMenu.setMnemonic('f');
        bookMenu.setMnemonic('b');
        memberMenu.setMnemonic('m');
        searchMenu.setMnemonic('s');
        loanMenu.setMnemonic('l');
        toolsMenu.setMnemonic('t');
        helpMenu.setMnemonic('h');

        //for setting the image icons
        icons = new ImageIcon[13];
        for (int i = 0; i < imageName16.length; i++) {
            icons[i] = new ImageIcon(ClassLoader.getSystemResource(imageName16[i]));
        }

        //for adding print books & exit
        //fileMenu.add(printBook = new JMenuItem("Print Books", icons[0]));
        fileMenu.add(changePassword = new JMenuItem("Change Password", icons[10]));
        fileMenu.add(deleteLibrarian = new JMenuItem("Delete Librarian", icons[11]));
        fileMenu.add(exit = new JMenuItem("Exit", icons[0]));

        //for adding add, list, listAvailble, listBorrowed, edit & remove Books and book information to the bookMenu
        bookMenu.add(addBook = new JMenuItem("Add Book", icons[1]));
        bookMenu.add(listBook = new JMenuItem("List All Books", icons[2]));
        bookMenu.add(listAvailbleBook = new JMenuItem("List Available Books", icons[2]));
        bookMenu.add(listBorrowedBook = new JMenuItem("List Borrowed Books", icons[2]));
        bookMenu.add(editBook = new JMenuItem("Edit Books", icons[3]));
        bookMenu.add(removeBook = new JMenuItem("Remove Book", icons[4]));
        //bookMenu.add(bookInformation = new JMenuItem("Book Information", icons[6]));

        //for adding add, list, edit & remove Members and member information to the memberMenu
        memberMenu.add(addMember = new JMenuItem("Add Member", icons[1]));
        memberMenu.add(listMember = new JMenuItem("List All Members", icons[2]));
        memberMenu.add(editMember = new JMenuItem("Edit Members", icons[3]));
        memberMenu.add(removeMember = new JMenuItem("Remove Member", icons[4]));
       // memberMenu.add(memberInformation = new JMenuItem("Member Information", icons[6]));

        //for adding add, list & remove Members to the memberMenu
        searchMenu.add(searchBooksAndMembers = new JMenuItem("Search", icons[5]));

        //for adding borrow & return books to the loanMenu
        loanMenu.add(borrowBook = new JMenuItem("Borrow a Book", icons[6]));
        loanMenu.add(returnBook = new JMenuItem("Return a Book", icons[7]));
        loanMenu.add(listissuedbooks=new JMenuItem("Issued book details",icons[2]));
        //Reserve a book  
        //reserveMenu.add(reserveBook = new JMenuItem("Reserve a Book", icons[9]));
        toolsMenu.add(notepad = new JMenuItem("Notepad", icons[8]));
        toolsMenu.add(calculator = new JMenuItem("Calculator", icons[9]));
        //for adding help & about to the helpMenu
        //helpMenu.add(help = new JMenuItem("Help", icons[11]));
        helpMenu.add(about = new JMenuItem("About", icons[12]));

        //for setting the shortcut
        //printBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        changePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        deleteLibrarian.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        searchBooksAndMembers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        addBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        listBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        listAvailbleBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        listBorrowedBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        editBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        removeBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        addMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        listMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        editMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        removeMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        borrowBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        returnBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        listissuedbooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
       // help.setAccelerator(KeyStroke.getKeyStroke("F1"));
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
    }
}
