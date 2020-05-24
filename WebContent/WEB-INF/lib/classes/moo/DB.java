package moo;

import java.sql.*;

public class DB {
	
	private static DB instance = null;
	
	private DB() {}
	
	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		
		return instance;
	}

	
	private Connection currentConnection = null;
	
	public ResultSet query(String statement, Object... args) {
		Connection connection = getConnection();
		if (connection == null) {
			System.out.println("query: no connection!");
			return null;
		}
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		setArguments(preparedStatement, args);
		
		try {
			ResultSet result = preparedStatement.executeQuery();

			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Connection getConnection() {
		if (currentConnection != null) {
			return currentConnection;
		}
		
		String driver="oracle.jdbc.driver.OracleDriver"; 
		// TODO change port to localhost:1521:orcl
		String url="jdbc:oracle:thin:@localhost:49161:xe";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,"jjh634","11");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		currentConnection = connection;
		
		return connection;
	}

	private void setArguments(PreparedStatement ps, Object[] args) {
		try {
			for (int i = 0; i < args.length; ++i) {
				Object arg = args[i];
				
				if (arg instanceof String) {
					ps.setString(i+1, (String)arg);
				} else if (arg instanceof Integer) {
					ps.setInt(i+1, ((Integer)arg).intValue());
				} else {
					System.err.println("Unsupported type of argument: " + arg.toString());
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
