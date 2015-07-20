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

public class Members {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private int memberID;
	private int regNo;
	private String password;
	private String name;
	private String email;
	private String major;
	private int numberOfBooks;
	//private int mony;
	private Date validUpto;
	//private String URL = "jdbc:odbc:JLibrary";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Library";
    private static final String USER_NAME="root";
    private static final String PASSWORD="nielit";
	public Members() {
	}
	/*public Members(int memberID, int ID, String password, String name, String email, String major, int numberOfBooks, int mony, Date expired) {
		this.memberID = memberID;
		this.ID = ID;
		this.password = password;
		this.name = name;
		this.email = email;
		this.major = major;
		this.numberOfBooks = numberOfBooks;
		this.mony = mony;
		this.expired = expired;
	}*/

	public int getMemberID() {
		return memberID;
	}

	public int getRegNo() {
		return regNo;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMajor() {
		return major;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	/*public int getMony() {
		return mony;
	}*/

	public Date getValidUpto() {
		return validUpto;
	}

	public void connection(String Query) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Members.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Members.java\n" + e.toString());
		}
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			connection = DriverManager.getConnection(DATABASE_URL,USER_NAME,PASSWORD);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				memberID = resultSet.getInt(1);
				regNo = resultSet.getInt(2);
				password = resultSet.getString(3);
				name = resultSet.getString(4);
				email = resultSet.getString(5);
				major = resultSet.getString(6);
				numberOfBooks = resultSet.getInt(7);
				//mony = resultSet.getInt(8);
				validUpto = resultSet.getDate(8);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Members.java inside result set\n" + SQLe.toString());
		}
	}

	public void update(String Query) {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");p
             Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Members.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Members.java\n" + e.toString());
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
			System.out.println("Members.java\n" + SQLe.toString());
		}
	}
}