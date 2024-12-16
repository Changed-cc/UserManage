package edu.cd.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource("mysql");

	static {

		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test1?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=false");
			cpds.setUser("root");
			cpds.setPassword("mySQL@123456");
		} catch (Exception e) {
		}
	}

	public static Connection getConn() {

		Connection conn = null;

		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void release(Connection conn, Statement ps, ResultSet rs) {

		try {
			// 6.关闭资源
			if (rs != null) {
				rs.close();
				rs = null; // 垃圾回收，上！
			}
			if (ps != null) {
				ps.close();
				ps = null; // 垃圾回收，上！
			}
			if (conn != null) {
				conn.close();
				conn = null; // ?
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(getConn());
	}

}