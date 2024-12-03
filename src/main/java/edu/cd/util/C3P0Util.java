package edu.cd.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {

	private static String driverClass;
	private static String url;
	private static String name;
	private static String pwd;
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	static {

		try {


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		Connection conn = null;

		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void release(Connection conn, Statement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null; // 垃圾回收，上！
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null; // 垃圾回收，上！
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}