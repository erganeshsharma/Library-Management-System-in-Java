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

import java.sql.*;
import javax.swing.JOptionPane;

public class Borrow {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private int bookID;
	private int memberID;
	private Date dayOfBorrowed;
	private Date dayOfReturn;
	//private String URL = "jdbc:odbc:JLibrary";
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/Library";
    private String USER_NAME="root";
    private String PASSWORD="nielit";

	public Borrow() {
	}

	public int getBookID() {
		return bookID;
	}

	public int getMemberID() {
		return memberID;
	}

	public Date getDayOfBorrowed() {
		return dayOfBorrowed;
	}

	public Date getDayOfReturn() {
		return dayOfReturn;
	}

	public void connection(String Query) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Borrow.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Borrow.java\n" + e.toString());
		}
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			connection = DriverManager.getConnection(DATABASE_URL,USER_NAME,PASSWORD);
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT * FROM Borrow");
            resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				bookID = resultSet.getInt(1);
				memberID = resultSet.getInt(2);
				dayOfBorrowed = resultSet.getDate(3);
				dayOfReturn = resultSet.getDate(4);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Borrow.java\n" + SQLe.toString());
		}
	}

	public void update(String Query) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Borrow.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Borrow.java\n" + e.toString());
		}
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			connection = DriverManager.getConnection(DATABASE_URL,USER_NAME,PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate(Query);
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Borrow.java\n" + SQLe.toString());
         }
	}
}
