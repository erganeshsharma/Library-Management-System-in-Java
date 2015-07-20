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
import java.sql.PreparedStatement;

/**
 *A public class
 */
public class AddBooks extends JInternalFrame {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the North Panel
    private JPanel northPanel = new JPanel();
    //for creaing the North Label
    private JLabel northLabel = new JLabel("BOOK INFORMATION");

    //for creating the Center Panel
    private JPanel centerPanel = new JPanel();
    //for creating an Internal Panel in the center panel
    private JPanel informationLabelPanel = new JPanel();

    //for creating an array of JLabel
    private JLabel[] informationLabel = new JLabel[10];
    private JLabel lblShelfNo = new JLabel(" Shelf No");
    private JTextField txtShelfNo = new JTextField();
    //for creating an array of String
    private String[] informationString = {
        " The book subject: ", " The book title: ",
        " The name of the Author(s): ", " The name of the Publisher: ",
        " Copyright year for the book: ", " The edition number: ", " The number of Pages: ",
        " ISBN for the book: ", " The number of copies: ", " The name of the Library: "};
    //for creating an Internal Panel in the center panel
    private JPanel informationTextFieldPanel = new JPanel();
    //for creating an array of JTextField
    private JTextField[] informationTextField = new JTextField[10];

    //for creating an Internal Panel in the center panel
    private JPanel insertInformationButtonPanel = new JPanel();
    //for creating a button
    private JButton insertInformationButton = new JButton("Insert the Information");

    //for creating South Panel
    private JPanel southPanel = new JPanel();
    //for creating a button
    private JButton OKButton = new JButton("Exit");

    //create objects from another classes for using them in the ActionListener
    private Books book;
    //for creating an array of string to store the data
    private String[] data;
    //for setting availble option to true
    private boolean availble = true;

    //for checking the information from the text field
    public boolean isCorrect() {
        data = new String[10];
        for (int i = 0; i < informationLabel.length; i++) {
            if (!informationTextField[i].getText().equals("")) {
                data[i] = informationTextField[i].getText();
            } else {
                return false;
            }
        }
        return true;
    }

    //for setting the array of JTextField to empty
    public void clearTextField() {
        for (int i = 0; i < informationTextField.length; i++) {
            informationTextField[i].setText(null);
        }
        txtShelfNo.setText(null);
    }

    //constructor of addBooks
    public AddBooks() {
        //for setting the title for the internal frame
        super("Add Books", false, true, false, true);
        //for setting the icon
        setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Add16.gif")));
        //for getting the graphical user interface components display area
        Container cp = getContentPane();
        cp.setBackground(Color.white);
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
        //for setting the border to the panel
        centerPanel.setBorder(BorderFactory.createTitledBorder("Add a new book:"));
        //for setting the layout
        informationLabelPanel.setLayout(new GridLayout(11, 1, 1, 1));
        /***********************************************************************
         * for adding the strings to the labels, for setting the font 		   *
         * and adding these labels to the panel.							   *
         * finally adding the panel to the container						   *
         ***********************************************************************/
        for (int i = 0; i < informationLabel.length; i++) {
            informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
            informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
        }
        centerPanel.add("West", informationLabelPanel);

        //for setting the layout
        informationTextFieldPanel.setLayout(new GridLayout(11, 1, 1, 1));
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
        lblShelfNo.setFont(new Font("Tahoma", Font.BOLD, 11));
        informationLabelPanel.add(lblShelfNo);
        txtShelfNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        informationTextFieldPanel.add(txtShelfNo);
        txtShelfNo.addKeyListener(new keyListener());
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
                    Thread runner = new Thread() {

                        public void run() {
                            book = new Books();
                            //for checking if there is no double information in the database
                            //book.connection("SELECT BookID FROM Books WHERE ISBN = '" + data[7] + "'");
                            book.connection("SELECT * FROM Books WHERE ISBN = '" + data[7] + "'");
                            String ISBN = book.getISBN();
                            if (!data[7].equalsIgnoreCase(ISBN)) {
                                try{
                                    String sql="INSERT INTO Books (Subject,Title,Author,Publisher,Copyright," +
                                        "Edition,Pages,ISBN,NumberOfBooks,NumberOfAvailbleBooks,Library,Availble,ShelfNo) VALUES "+
                                        " (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                                        Class.forName("org.gjt.mm.mysql.Driver");
                                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","nielit");
                                        PreparedStatement ps=con.prepareStatement(sql);
                                        ps.setString(1, data[0]);
                                        ps.setString(2, data[1]);
                                        ps.setString(3, data[2]);
                                        ps.setString(4, data[3]);                                        
                                        ps.setInt(5, Integer.parseInt(data[4]));
                                        ps.setInt(6,Integer.parseInt(data[5]));
                                        ps.setInt(7, Integer.parseInt(data[6]));
                                        ps.setString(8, data[7]);
                                        ps.setInt(9, Integer.parseInt(data[8]));
                                        ps.setInt(10, Integer.parseInt(data[8]));
                                        ps.setString(11, data[9]);
                                        ps.setBoolean(12, availble);
                                        ps.setInt(13, Integer.parseInt(txtShelfNo.getText()));
                                        ps.executeUpdate();      
                                }catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, ex.toString());
                                }
                                       
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "The book is in the library", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    };
                    runner.start();
                } //if there is a missing data, then display Message Dialog
                else {
                    JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
                }
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