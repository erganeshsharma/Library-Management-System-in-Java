/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import java.sql.*;

public class LoginScreen extends JFrame {

    private JLabel lblUsername,  lblPasswd;
    public JTextField txtUser;
    private JPasswordField txtPasswd;
    private JButton btnLogin,btnCancel,btnSignup;
    private Connection conn=null;
    //private Statement stat=null;
    //private ResultSet resultSet=null;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginScreen() {
        super("System Login");
        this.getContentPane().setLayout(null);
        this.setSize(370, 250);
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lblUsername = new JLabel("Username");
        lblPasswd = new JLabel("Password");
        txtUser = new JTextField();
        txtPasswd = new JPasswordField();
        btnLogin = new JButton("Login", new ImageIcon(ClassLoader.getSystemResource("images\\Login.png")));
        btnCancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images\\Cancel.png")));
        //btnSignup = new JButton("Signup", new ImageIcon(ClassLoader.getSystemResource("images\\Cancel.png")));
        btnSignup = new JButton(new ImageIcon(ClassLoader.getSystemResource("images\\Signup.png")));

        lblUsername.setBounds(40, 30, 100, 25);
        lblPasswd.setBounds(40, 65, 100, 25);
        txtUser.setBounds(150, 30, 160, 25);
        txtPasswd.setBounds(150, 65, 160, 25);
        btnLogin.setBounds(130, 150, 100, 25);
        btnCancel.setBounds(240, 150, 100, 25);
        btnSignup.setBounds(20, 150, 100, 25);

        lblUsername.setFont(new Font("monospaced", Font.BOLD, 16));
        lblPasswd.setFont(new Font("monospaced", Font.BOLD, 16));
        txtUser.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        txtPasswd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        this.add(lblUsername);
        this.add(txtUser);
        this.add(lblPasswd);
        this.add(txtPasswd);
        this.add(btnSignup);
        this.add(btnLogin);
        this.add(btnCancel);
       // this.add(btnLogin);
       // this.add(btnCancel);

        ButtonListener listener = new ButtonListener();
        btnSignup.addActionListener(listener);
        btnLogin.addActionListener(listener);
        btnCancel.addActionListener(listener);
        
        try {
			Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","nielit");
            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Login");
            ResultSet rst = stmt.getResultSet();
            boolean datafound = rst.next();
            if (datafound) {
                btnSignup.setVisible(false);
		}
        }
		catch (ClassNotFoundException cnfe) {
			System.out.println("LoginScreen.java\n" + cnfe.toString());
		}
        catch (SQLException SQLe) {
			System.out.println("Books.java\n:" + SQLe.toString());
		}
		catch (Exception e) {
			System.out.println("LoginScreen.java\n" + e.toString());
		}
        //con = DBConnection.getDBConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Error on establishing database connection", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//constructor closed

    public void login() {
        String username = txtUser.getText();
        String password = new String(txtPasswd.getPassword());
        String SQL;
        SQL = "SELECT * FROM Login WHERE username='" + username + "'  AND password='" +
                password + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(SQL);
            ResultSet rs = stmt.getResultSet();
            boolean recordfound = rs.next();
            if (recordfound) {
                   EventQueue.invokeLater(new Main(new JLibrary()));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "The system could not log you in.\n" +
                        " Please make sure your username and password are correct", "Login Failure", JOptionPane.INFORMATION_MESSAGE);
                txtUser.setText("");
                txtPasswd.setText("");
                txtUser.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error on login operation", "Login Error", JOptionPane.ERROR_MESSAGE);
        }//try catch closed
    }//Login() closed

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLogin) {
                if (txtUser.getText() == null || txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter username", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtUser.requestFocus();
                    return;
                }
                if (txtPasswd.getPassword() == null || txtPasswd.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtPasswd.requestFocus();
                    return;
                }
                login();
            } else if (e.getSource() == btnCancel) {
                System.exit(0);
            }//if else closed
            else if(e.getSource()== btnSignup){
            dispose();
             EventQueue.invokeLater(new Main(new SignUp()));
            //JOptionPane.showMessageDialog(null,"signup button pressed","singup window", JOptionPane.INFORMATION_MESSAGE);
            }
        }//actionPerformed() closed
    }//ButtonListner class closed
}