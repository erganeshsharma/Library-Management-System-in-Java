/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */

import javax.swing.table.AbstractTableModel;
import java.sql.*;

/**
 *ResultSet rows and columns are counted from 1 and JTable
 *rows and columns are counted from 0. When processing
 *ResultSet rows or columns for use in a JTable, it is
 *necessary to add 1 to the row or column number to manipulate
 *the appropriate ResultSet column (i.e., JTable column 0 is
 *ResultSet column 1 and JTable row 0 is ResultSet row 1)
 */
public class ResultSetTableModel extends AbstractTableModel {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	// keep track of database connection status
	private boolean connectedToDatabase = false;

	// initialize resultSet and obtain its meta data object;
	// determine number of rows
	public ResultSetTableModel(String driver, String url,String username,String password, String query) throws SQLException, ClassNotFoundException {
		Class.forName(driver); // load database driver class
		connection = DriverManager.getConnection(url,username,password); // connect to database
		// create Statement to query database
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		connectedToDatabase = true; // update database connection status
		setQuery(query); // set query and execute it
	}
	// get class that represents column type
	public Class getColumnClass(int column) throws IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		// determine Java class of column
		try {
			String className = metaData.getColumnClassName(column + 1);
			return Class.forName(className); // return Class object that represents className
		}
		        // catch SQLExceptions and ClassNotFoundExceptions
		catch (Exception exception) {
			exception.printStackTrace();
		}
		// if problems occur above, assume type Object
		return Object.class;
	}

	// get number of columns in ResultSet
	public int getColumnCount() throws IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		// determine number of columns
		try {
			return metaData.getColumnCount();
		}
		        // catch SQLExceptions and print error message
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		// if problems occur above, return 0 for number of columns
		return 0;
	}

	// get name of a particular column in ResultSet
	public String getColumnName(int column) throws IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		// determine column name
		try {
			return metaData.getColumnName(column + 1);
		}
		        // catch SQLExceptions and print error message
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		// if problems, return empty string for column name
		return "";
	}

	// return number of rows in ResultSet
	public int getRowCount() throws IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		return numberOfRows;
	}

	// obtain value in particular row and column
	public Object getValueAt(int row, int column) throws IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		// obtain a value at specified ResultSet row and column
		try {
			resultSet.absolute(row + 1);
			return resultSet.getObject(column + 1);
		}
		        // catch SQLExceptions and print error message
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		// if problems, return empty string object
		return "";
	}

	// set new database query string
	public void setQuery(String query) throws SQLException, IllegalStateException {
		// ensure database connection is available
		if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
		// specify query and execute it
		resultSet = statement.executeQuery(query);
		// obtain meta data for ResultSet
		metaData = resultSet.getMetaData();
		// determine number of rows in ResultSet
		resultSet.last(); // move to last row
		numberOfRows = resultSet.getRow(); // get row number
		fireTableStructureChanged(); // notify JTable that model has changed
	}

	// close Statement and Connection
	public void disconnectFromDatabase() {
		// close Statement and Connection
		try {
			statement.close();
			connection.close();
		}
		        // catch SQLExceptions and print error message
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		        // update database connection status
		finally {
			connectedToDatabase = false;
		}
	}
} // end class ResultSetTableModel