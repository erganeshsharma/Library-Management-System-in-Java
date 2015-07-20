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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell user
 */
public class SignUp extends JFrame {
   private JLabel lblUsername, lblPasswd,lblConfirmPasswd;
    public JTextField txtUser;
    private JPasswordField txtPasswd,txtConfirmPasswd;
    private JButton btnSubmit,btnCancel;
    private Connection conn=null;
    private Statement stmt;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Library";
    private static final String USER_NAME="root";
    private static final String PASSWORD="nielit";
    //private Statement stat=null;
    //private ResultSet resultSet=null;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); 
public SignUp(){
super("System Signup Window");
this.getContentPane().setLayout(null);
        this.setSize(450, 270);
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lblUsername = new JLabel("Username");
        lblPasswd = new JLabel("Password");
        lblConfirmPasswd = new JLabel("Confirm Password");
        txtUser = new JTextField();
        txtPasswd = new JPasswordField();
        txtConfirmPasswd = new JPasswordField();
        btnSubmit = new JButton("Create");
        btnCancel = new JButton("Cancel");
        
        lblUsername.setBounds(40, 30, 100, 25);
        lblPasswd.setBounds(40, 65, 100, 25);
        lblConfirmPasswd.setBounds(40, 100, 200, 25);
        txtUser.setBounds(230, 30, 190, 25);
        txtPasswd.setBounds(230, 65, 190, 25);
        txtConfirmPasswd.setBounds(230, 100, 190, 25);
        btnSubmit.setBounds(110, 160, 100, 25);
        btnCancel.setBounds(260, 160, 100, 25);
        
        lblUsername.setFont(new Font("monospaced", Font.BOLD, 16));
        lblPasswd.setFont(new Font("monospaced", Font.BOLD, 16));
        lblConfirmPasswd.setFont(new Font("monospaced", Font.BOLD, 16));
        txtUser.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        txtPasswd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        txtConfirmPasswd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        
        this.add(lblUsername);
        this.add(txtUser);
        this.add(lblPasswd);
        this.add(txtPasswd);
        this.add(lblConfirmPasswd);
        this.add(txtConfirmPasswd);
        this.add(btnSubmit);
        this.add(btnCancel);
        
        
         ButtonListener listener = new ButtonListener();
        btnSubmit.addActionListener(listener);
        btnCancel.addActionListener(listener);
                
        /*try {
            
            /*stmt.execute("SELECT * FROM Login");
            ResultSet rst = stmt.getResultSet();
            boolean datafound = rst.next();
            if (datafound) {
                btnSignup.setVisible(false);
		}
        }*/
		
        //con = DBConnection.getDBConnection();
        
    } //constructor closed 

    public void Sign() {
        String username = txtUser.getText();
        String password = new String(txtPasswd.getPassword());
        String confirmPassword = new String(txtConfirmPasswd.getPassword());
        String SQL;
        SQL = "INSERT INTO Login VALUES('"+username+"',"+"'"+password+"')";
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(DATABASE_URL,USER_NAME,PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
            conn.close();
            EventQueue.invokeLater(new Main(new JLibrary()));
                this.dispose();
            }
        catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.toString());
		}
        catch (SQLException SQLe) {
			System.out.println(SQLe.toString());
		}
         catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error on Signup operation", "Signup Error", JOptionPane.ERROR_MESSAGE);
        }//try catch closed
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Error on establishing database connection", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//Sign() closed

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSubmit) {
                String password = new String(txtPasswd.getPassword());
                String confirmPassword = new String(txtConfirmPasswd.getPassword());
                if (txtUser.getText() == null || txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter username", "Missing field", JOptionPane.INFORMATION_MESSAGE);
                    txtUser.requestFocus();
                    return;
                }
                if (password == null || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password", "Missing field", JOptionPane.INFORMATION_MESSAGE);
                    txtPasswd.requestFocus();
                    return;
                }
                if (confirmPassword == null || confirmPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter confirm password", "Missing field", JOptionPane.INFORMATION_MESSAGE);
                    txtConfirmPasswd.requestFocus();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password mismatch !", "Mismatch", JOptionPane.WARNING_MESSAGE);
                    txtPasswd.setText("");
                    txtConfirmPasswd.setText("");
                    txtPasswd.requestFocus();
                    return;
                }
                Sign();
            } else if (e.getSource() == btnCancel) {
                System.exit(0);
            }//if else closed
        }//actionPerformed() closed
    }
}