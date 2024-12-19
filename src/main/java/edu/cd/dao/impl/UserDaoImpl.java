package edu.cd.dao.impl;

import edu.cd.dao.UserDao;
import edu.cd.entity.Users;
import edu.cd.exception.IdIsNullException;
import edu.cd.util.Base64Util;
import edu.cd.util.C3P0Util;
import edu.cd.util.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

	@Override
	public int addUser(Users users) {
		int result = -1;
		try {
			String sql = "INSERT INTO users (name, nickName, pwd, gender, birthday, hobby, tel, email, grade, description) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] params = {
					users.getName(),
					users.getNickName(),
					Base64Util.encode(users.getPwd()),  // 假设密码是经过 Base64 编码的
					users.getGender(),
					users.getBirthday().toString(),  // 这里假设 birthday 是 Date 类型
					users.getHobby(),
					users.getTel(),
					users.getEmail(),
					String.valueOf(users.getGrade()),
					users.getDescription()
			};

			result = DBHelper.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delUserById(Integer id) throws IdIsNullException {

		if(id==null)
			throw new IdIsNullException("根据id删除单个用户时，id不能为空！");

		int res = -1;

		try {
			String sql = "delete from users where id=?";
			Integer[] params = { id };
			res = DBHelper.executeUpdate(sql, params);
		} catch (Exception e) {
			// ...
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int updateUser(Users users, Integer id) {
		int result = -1;
		try {
			String sql = "UPDATE users SET name=?, nickName=?, pwd=?, gender=?, birthday=?, hobby=?, tel=?, email=?, grade=?, description=? WHERE id=?";
			String[] params = {
					users.getName(),
					users.getNickName(),
					Base64Util.encode(users.getPwd()),  // 假设密码是经过 Base64 编码的
					users.getGender(),
					users.getBirthday().toString(),
					users.getHobby(),
					users.getTel(),
					users.getEmail(),
					String.valueOf(users.getGrade()),
					users.getDescription(),
					String.valueOf(id)  // 通过 id 来定位更新的用户
			};

			result = DBHelper.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public boolean checkUser(String name, String pwd) {

		boolean res = false;

		try {
			// 1.加载驱动
			// 2.获取数据库连接
			// 3.创建表示sql的语句对象
			// 4.执行sql，如果有结果，写入结果集
			// Statement stmt = conn.createStatement();
			String sql = "select * from users where name=? and pwd=?";
			String[] params = { name, Base64Util.encode(pwd) };
			ResultSet rs = DBHelper.executeQuery(sql, params);
			// 5.遍历结果集

			Users user = null;

			if (rs.next()) {

				// 封装user
				user = new Users();

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
//			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
			C3P0Util.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			// ...
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

			if(rs.next()){
				users=new ArrayList<Users>();

				do{
					// 封装user
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
//			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
			C3P0Util.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			// ...
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public ArrayList<Users> getPageUsers(int pageNow, int pageSize) {
		ArrayList<Users> users = null;

		try {
			String sql = "select * from users limit ?,?";
			Integer[] params = {(pageNow-1)*pageSize,pageSize};
			ResultSet rs = DBHelper.executeQuery(sql, params);
			// 5.遍历结果集

			if(rs.next()){
				users=new ArrayList<Users>();

				do{
					// 封装user
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
//			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
			C3P0Util.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			// ...
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public int getRecordCount() {
		int res = -1;

		try {
			String sql = "select count(*) from users";
			Integer[] params = null;
			ResultSet rs = DBHelper.executeQuery(sql, params);
			// 5.遍历结果集

			if(rs.next()){
				res=rs.getInt(1);
			}

			// 6.关闭资源
//			JdbcUtil.release(DBHelper.getConn(), DBHelper.getPs(), rs);
			C3P0Util.release(DBHelper.getConn(), DBHelper.getPs(), rs);
		} catch (Exception e) {
			// ...
			e.printStackTrace();
		}

		return res;
	}
}
