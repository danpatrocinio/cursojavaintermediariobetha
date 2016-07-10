package com.curso.preco.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static Connection INSTANCE;
	private static final String senha = "root";
	private static final String url = "jdbc:mysql://localhost:3306/precodb";
	private static final String usuario = "root";
	private java.sql.Connection conn;

	private Connection() {
		try {
			Class.forName(Connection.driverClass);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Connection get() {
		if (Connection.INSTANCE == null) {
			Connection.INSTANCE = new Connection();
		}
		return Connection.INSTANCE;
	}

	public PreparedStatement getParamStm(String sql) throws SQLException {
		getConn();
		return conn.prepareStatement(sql);
	}

	public Statement getStm() throws SQLException {
		getConn();
		return conn.createStatement();
	}

	private void getConn() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(Connection.url, Connection.usuario,
			        Connection.senha);
		}
		if (conn.isClosed()) {
			conn = DriverManager.getConnection(Connection.url, Connection.usuario,
			        Connection.senha);
		}
	}
}
