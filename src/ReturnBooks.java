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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;

/**
 *A public class
 */
public class ReturnBooks extends JInternalFrame implements ActionListener {

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
    private JLabel[] informationLabel = new JLabel[2];
    //for creating an array of String
    private String[] informationString = {" Write the Book ID:", " Write the Member ID:"};
    //for creating an array of JTextField
    private JTextField[] informationTextField = new JTextField[2];
    //for creating an array of string to store the data
    private String[] data;
    private JLabel lblFinePerDay = new JLabel(" Fine per Day");
    private JTextField txtFinePerDay = new JTextField();
    private JLabel lblTotalFineAmt = new JLabel(" Total fine amount");
    private JTextField txtTotalFineAmt = new JTextField();
    //for creating an Internal Panel in the center panel
    private JPanel returnButtonPanel = new JPanel();
    //for creating the buton
    private JButton returnButton = new JButton("Return");

    //for creating the panel
    private JPanel southPanel = new JPanel();
    //for creating the button
    private JButton cancelButton = new JButton("Cancel");

    //for creating an object
    private Books book;
    private Members member;
    private Borrow borrow;


    //for checking the information from the text field
    public boolean isCorrect() {
        data = new String[2];
        for (int i = 0; i < informationLabel.length; i++) {
            if (!informationTextField[i].getText().equals("")) {
                data[i] = informationTextField[i].getText();
            } else {
                return false;
            }
        }
        return true;
    }

    //for setting the array of JTextField to null
    public void clearTextField() {
        for (int i = 0; i < informationTextField.length; i++) {
            if (i != 2) {
                informationTextField[i].setText(null);
            }
            txtFinePerDay.setText(null);
            txtTotalFineAmt.setText(null);
        }
    }

    //constructor of returnBooks
    public ReturnBooks() {
        //for setting the title for the internal frame
        super("Return books", false, true, false, true);
        //for setting the icon
        setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Import16.gif")));
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
            informationPanel.add(informationTextField[i] = new JTextField());
            informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
            informationTextField[i].addKeyListener(new keyListener());
        }
        informationPanel.add(lblFinePerDay);
        informationPanel.add(txtFinePerDay);
        informationPanel.add(lblTotalFineAmt);
        informationPanel.add(txtTotalFineAmt);
        txtTotalFineAmt.setEditable(false);
        txtFinePerDay.addKeyListener(new keyListener());
        //txtFinePerDay.addKeyListener(this);
        centerPanel.add("Center", informationPanel);
        //for setting the layout
        returnButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //for adding the button
        returnButtonPanel.add(returnButton);
        //for setting the font to the button
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        //for adding the internal panel to the panel
        centerPanel.add("South", returnButtonPanel);
        //for setting the border
        centerPanel.setBorder(BorderFactory.createTitledBorder("Return a book:"));
        //for adding the center panel to the container
        cp.add("Center", centerPanel);

        //for setting the layout
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //for adding the button

        southPanel.add(cancelButton);
        //for setting the font to the button
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        //for setting the border
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        //for adding the south panel to the container
        cp.add("South", southPanel);

        /***********************************************************************
         * for adding the action listener to the button,first the text will be *
         * taken from the JTextField and make the connection for database,     *
         * after that update the table in the database with the new value      *
         ***********************************************************************/
        returnButton.addActionListener(this);
        //for adding the action listener for the button to dispose the frame
        cancelButton.addActionListener(this);
        //for setting the visible to true
        setVisible(true);
        //show the internal frame
        pack();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == returnButton) {
            //for checking if there is a missing information
            if (isCorrect()) {
                Thread runner = new Thread() {

                    public void run() {
                        book = new Books();
                        member = new Members();
                        borrow = new Borrow();
                        borrow.connection("SELECT * FROM Borrow WHERE BookID="+data[0]+" AND MemberID="+data[1]);
                            int bookid=borrow.getBookID();
                            int memberid=borrow.getMemberID();
                            if((bookid==Integer.parseInt(data[0])) && (memberid==Integer.parseInt(data[1])))
                            {
                        book.connection("SELECT * FROM Books WHERE BookID = " + data[0]);
                        member.connection("SELECT * FROM Members WHERE MemberID = " + data[1]);
                        int numberOfAvailbleBooks = book.getNumberOfAvailbleBooks();
                        int numberOfBorrowedBooks = book.getNumberOfBorrowedBooks() - 1;
                        int numberOfBooks = member.getNumberOfBooks();
                        //for checking if there is no same information in the database
                        if (numberOfAvailbleBooks == 0 && numberOfBooks > 0) {
                            numberOfAvailbleBooks += 1;
                            numberOfBooks -= 1;
                            book.update("UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks +
                                    ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + ",Availble = true WHERE BookID =" + data[0]);
                            member.update("UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1]);
                            borrow.update("DELETE FROM Borrow WHERE BookID =" + data[0] + " AND MemberID =" + data[1]);
                            //for setting the array of JTextField to null
                            //clearTextField();
                            dispose();
                        } else if (numberOfAvailbleBooks > 0 && numberOfBooks > 0) {
                            numberOfAvailbleBooks += 1;
                            numberOfBooks -= 1;
                            book.update("UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks +
                                    ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + " WHERE BookID =" + data[0]);
                            member.update("UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1]);
                            borrow.update("DELETE FROM Borrow WHERE BookID =" + data[0] + " AND MemberID =" + data[1]);
                            //for setting the array of JTextField to null
                            //clearTextField();
                            dispose();
                        } /*else {
                            JOptionPane.showMessageDialog(null, "The book is not borrowed", "Warning", JOptionPane.WARNING_MESSAGE);
                        }*/
                            }
                           else {
                            JOptionPane.showMessageDialog(null, "The book is not borrowed", "Warning", JOptionPane.WARNING_MESSAGE);
                            clearTextField();
                        }
                    }
                };
                runner.start();
            } //if there is a missing data, then display Message Dialog
            else {
                JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (ae.getSource() == cancelButton) {
            dispose();
        }
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

        public void keyPressed(KeyEvent k) {
            java.sql.Date da = null;
            if ((k.getKeyCode() == KeyEvent.VK_TAB) || (k.getKeyCode() == KeyEvent.VK_ENTER)) {
                try {
                    int fineamt = Integer.parseInt(txtFinePerDay.getText());
                    Class.forName("org.gjt.mm.mysql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","nielit");
                    Statement st = con.createStatement();
                    int bookid = Integer.parseInt(informationTextField[0].getText());
                    int memid = Integer.parseInt(informationTextField[1].getText());
                    try {
                        String sql = "SELECT DayOfReturn from Borrow where MemberID=" + memid + " and BookID=" + bookid;
                        ResultSet rs = st.executeQuery(sql);
                        if (rs.next()) {
                            
                            da = rs.getDate(1);                            
                            java.util.Date today = new java.util.Date();
                            /*java.util.Date retdate=new java.util.Date(da.getYear(),da.getMonth(),da.getDate());
                            JOptionPane.showMessageDialog(null, "today=" + today + "\nRet date=" + retdate);*/
                            
                            //System.out.println(today.after(da));
                            
                            if (today.after(da)) {
                                long finedays = today.getTime() - da.getTime();
                                int days = (int) (finedays / (1000 * 60 * 60 * 24));
                                //System.out.println(days);
                                txtTotalFineAmt.setText(String.valueOf(fineamt * days));
                            } else {
                                txtTotalFineAmt.setText("0");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Member ID or Book ID entered not found on databse");
                        }
                        
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(null, "Error, Cannot retrieve date value from table" + ex1.toString());
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error, cannot connect to database" + ex.toString());
                }
            }
        }
    }//inner class closed
}//class closed
