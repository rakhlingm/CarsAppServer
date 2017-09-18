package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConnectionSystemException;

public class ConnectionPool {

	private List<Connection> cons;
	private List<Connection> cons2;
	private int size = 5;
	private String dbname = "dbCars";
	private String dbUrl = "jdbc:derby://localhost:1527/" + dbname;
	
	private final static ConnectionPool singleton = new ConnectionPool();

	private ConnectionPool() {
		cons = new ArrayList<>();
		cons2 = new ArrayList<>();
		try {
			for (int i = 0; i < size; i++) {
//				System.out.println("Before getConnection");
				Connection con = DriverManager.getConnection(dbUrl);
				cons.add(con);
				cons2.add(con);
			}
			System.out.println("Connection pool initialized all connection: " + cons.size());
		} catch (SQLException e) {
			e.printStackTrace();
		//	System.out.println("Connection is not stable. I'm here!");
		}
	}

	public static ConnectionPool getInstance() {
		return singleton;
	}

	public synchronized Connection getconnection() throws ConnectionSystemException {
		while (cons.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new ConnectionSystemException("Connection is not stable. Contact us");
			}
		}
		return cons.remove(0);
	}

	public synchronized void returnConnection(Connection connection) {
		cons.add(connection);
		notifyAll();
	}


	public synchronized void closeAllConnections()throws ConnectionSystemException {

		for (Connection con : cons2) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new  ConnectionSystemException("Connection isn't closed");
			}
		}
		System.out.println("connection pool shutdown - closed " + cons.size() + " connections");
		if (cons.size() != size) {
			System.out.println("Not all of the connections are closed");
		}
	}
}
