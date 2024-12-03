package edu.cd.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static Connection getConn() {
		return conn;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static int executeUpdate(String sql, Object[] params) {

		conn = JdbcUtil.getConn();
		int res = -1;

		try {
			ps=conn.prepareStatement(sql);
			// 给？赋值
			if(params!=null&&params.length>0){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			res = ps.executeUpdate();
			JdbcUtil.release(conn, ps, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static ResultSet executeQuery(String sql, Object[] params) {

		conn = JdbcUtil.getConn();

		try {
			ps=conn.prepareStatement(sql);
			// 给？赋值
			if(params!=null&&params.length>0){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
//			JdbcUtil.release(conn, ps, null);//延迟关闭
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

//	public static ArrayList<T> executeQuery2(String sql, Object[] params) {
//		return null;
//	}
}
