package edu.cd.dao.impl;

import edu.cd.dao.UserDao;
import edu.cd.entity.Users;
import edu.cd.exception.IdIsNullException;
import edu.cd.util.Base64Util;
import edu.cd.util.DBHelper;
import edu.cd.util.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

	@Override
	public int addUser(Users users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delUserById(Integer id) throws IdIsNullException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(Users users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkUser(String name, String pwd) {

		boolean res = false;

		try {
			// 登录验证(database)
			// 1.加载驱动

			// 2.获取数据库连接
			// 3.创建表示sql的语句对象
			// 4.执行sql，如果有结果，写入结果集
			String sql = "select * from users where name=? and pwd=?";
			String[] params = { name, Base64Util.encode(pwd) };
			ResultSet rs = DBHelper.executeQuery(sql, params);
			// 5.遍历结果集
			// 封装user

			if (rs.next()) {

				Users user = new Users();

				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setNickName(rs.getString("nickName"));
				user.setPwd(rs.getString("pwd"));
				user.setGender(rs.getString("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setHobby(rs.getString("hobby"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setGrade(rs.getInt("grade"));
				user.setDescription(rs.getString("description"));

				res = true;
			} else {
				res = false;
			}
			// 6.关闭资源
			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public ArrayList<Users> getAllUsers() {
		ArrayList<Users> users = null;

		try {
			// 1.加载驱动
			// 2.获取数据库连接
			// 3.创建表示sql的语句对象
			// 4.执行sql，如果有结果，写入结果集
			String sql = "select * from users";
			String[] params = null;
			ResultSet rs = DBHelper.executeQuery(sql, params);
			// 5.遍历结果集
			// 封装user
			if(rs.next()){
				users=new ArrayList<Users>();
				do  {
					Users user = new Users();

					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setNickName(rs.getString("nickName"));
					user.setPwd(rs.getString("pwd"));
					user.setGender(rs.getString("gender"));
					user.setBirthday(rs.getDate("birthday"));
					user.setHobby(rs.getString("hobby"));
					user.setTel(rs.getString("tel"));
					user.setEmail(rs.getString("email"));
					user.setGrade(rs.getInt("grade"));
					user.setDescription(rs.getString("description"));

					users.add(user);
				}while(rs.next());
			}
			// 6.关闭资源
			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

}
