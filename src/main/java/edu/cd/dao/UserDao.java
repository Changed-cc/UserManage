package edu.cd.dao;

import edu.cd.entity.Users;
import edu.cd.exception.IdIsNullException;

import java.util.ArrayList;

public interface UserDao {
	/**
	 * 添加单个用户
	 * @param users
	 * @return
	 */
	public int addUser(Users users);

	/**
	 * 根据id删除单个用户
	 * @param id
	 * @return
	 * @throws IdIsNullException
	 */
	public int delUserById(Integer id) throws IdIsNullException;

	/**
	 * 修改单个用户
	 * @param users
	 * @return
	 */
	public int updateUser(Users users);

	/**
	 * 用户登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean checkUser(String name,String pwd);

	/**
	 * 查询所有用户
	 * @return
	 */
	public ArrayList<Users> getAllUsers();

	/**
	 * 查询分页用户
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Users> getPageUsers(int pageNow,int pageSize);

}
