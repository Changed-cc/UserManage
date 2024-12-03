package edu.cd.service.impl;

import edu.cd.dao.UserDao;
import edu.cd.dao.impl.UserDaoImpl;
import edu.cd.entity.Users;
import edu.cd.exception.IdIsNullException;
import edu.cd.service.UserService;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

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
		return userDao.checkUser(name, pwd);
	}

	@Override
	public ArrayList<Users> getAllUsers() {
		return userDao.getAllUsers();
	}

}
