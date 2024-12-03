//如何使用Java操作MySQL数据库以插入一条数据。
package edu.cd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcDemo {

	public static void main(String[] args) {

		try {
			// 1、加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2、获得数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test1?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true"
					, "root", "mySQL@123456");

			// 3、创建表示sql的语句对象 执行sql，如果有结果，写入结果集
			String sql = "insert into users(name,nickName,pwd,gender,birthday,hobby,tel,email,grade,description) "
					+ "values('小枝','老大','123','1','2023-01-01','吃饭,睡觉,写程序','13900000001','001@qq.com','11','无')";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4、更新
			int res = pstmt.executeUpdate();
			// 5、遍历结果集
			System.out.println("影响了" + res + "行.");
			// 6、关闭资源
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;	//垃圾回收，上！
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}