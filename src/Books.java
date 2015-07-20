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

public class Books {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private int bookID;
	private String subject;
	private String title;
	private String author;
	private String publisher;
	private int copyright;
	private int edition;
	private int pages;
	private String ISBN;
	private int numberOfBooks;
	private int numberOfAvailbleBooks;
	private int numberOfBorrowedBooks;
	private String library;
	private boolean availble;
	//private String URL = "jdbc:mysql://localhost:3306/Library,root,doeacc";

	public Books() {
	}

	public int getBookID() {
		return bookID;
	}

	public String getSubject() {
		return subject;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getCopyright() {
		return copyright;
	}

	public int getEdition() {
		return edition;
	}

	public int getPages() {
		return pages;
	}

	public String getISBN() {
		return ISBN;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public int getNumberOfAvailbleBooks() {
		return numberOfAvailbleBooks;
	}

	public int getNumberOfBorrowedBooks() {
		return numberOfBorrowedBooks;
	}

	public String getLibrary() {
		return library;
	}

	public boolean getAvailble() {
		return availble;
	}

	public void connection(String Query) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Books.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Books.java\n" + e.toString());
		}
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","nielit");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				bookID = resultSet.getInt(1);
				subject = resultSet.getString(2);
				title = resultSet.getString(3);
				author = resultSet.getString(4);
				publisher = resultSet.getString(5);
				copyright = resultSet.getInt(6);
				edition = resultSet.getInt(7);
				pages = resultSet.getInt(8);
				ISBN = resultSet.getString(9);
				numberOfBooks = resultSet.getInt(10);
				numberOfAvailbleBooks = resultSet.getInt(11);
				numberOfBorrowedBooks = resultSet.getInt(12);
				library = resultSet.getString(13);
				availble = resultSet.getBoolean(14);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Books.java" +"\n" + SQLe.toString());
		}
	}

	public void update(String Query) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("Books.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("Books.java\n" + e.toString());
		}
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			//connection = DriverManager.getConnection("jdbc:odbc:JLibrary2");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","nielit");
			statement = connection.createStatement();
			statement.executeUpdate(Query);
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Books.java\n:" + SQLe.toString());
		}
	}
}
